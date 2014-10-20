package vn.chodientu.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.SellerCustomer;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.PaymentStatus;
import vn.chodientu.entity.input.SellerCustomerSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.CashRepository;
import vn.chodientu.repository.OrderRepository;
import vn.chodientu.repository.SellerCustomerRepository;
import vn.chodientu.repository.SellerRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;

@Service
public class SellerCustomerService {

    @Autowired
    private SellerCustomerRepository customerRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private CashRepository cashRepository;
    @Autowired
    private CashService cashService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Validator validator;
    private final long xeng_active_marketing = 300000;

    public void addCustomer(Order order) {
        User user = userRepository.find(order.getBuyerId());
        SellerCustomer customer;
        if (user != null) {
            customer = customerRepository.get(user.getEmail(), user.getPhone(), order.getSellerId());
        } else {
            customer = customerRepository.get(order.getBuyerEmail(), order.getBuyerPhone(), order.getSellerId());
        }
        if (customer == null) {
            customer = new SellerCustomer();
            customer.setCreateTime(System.currentTimeMillis());
            customer.setSellerId(order.getSellerId());
            customer.setUserId(order.getBuyerId());
            if (user == null) {
                customer.setAddress(order.getBuyerAddress());
                customer.setEmail(order.getBuyerEmail());
                customer.setEmailVerified(true);
                customer.setName(order.getBuyerName());
                customer.setPhone(order.getBuyerPhone());
                customer.setPhoneVerified(true);
            } else {
                customer.setAddress(user.getAddress());
                customer.setEmail(user.getEmail());
                customer.setEmailVerified(user.isEmailVerified());
                customer.setName(user.getName());
                customer.setPhone(user.getPhone() == null ? order.getBuyerPhone() : user.getPhone());
                customer.setPhoneVerified(true);
            }
        }
        customer.setUpdateTime(System.currentTimeMillis());
        customer.setOrder(customer.getOrder() + 1);
        customer.setOrderPrice(customer.getOrderPrice() + (order.getFinalPrice() > 0 ? order.getFinalPrice() : order.getTotalPrice()));
        if (order.getPaymentStatus() == PaymentStatus.PAID) {
            customer.setOrderPayment(customer.getOrderPayment() + 1);
            customer.setOrderPricePayment(customer.getOrderPayment() + (order.getFinalPrice() > 0 ? order.getFinalPrice() : order.getTotalPrice()));
        }
        customerRepository.save(customer);
    }

    /**
     * Kích hoạt chức năng danh sách người bán băng xèng
     *
     * @param user
     * @return
     */
    public Response active(User user) {
        try {

            Seller seller = sellerRepository.find(user.getId());
            if (seller == null) {
                seller = new Seller();
            }
            if (seller.isMarketing()) {
                return new Response(false, "Tài khoản đã được kích hoạt chức năng danh sách người bán");
            }
            CashTransaction cashTransaction = new CashTransaction();
            cashTransaction.setUserId(user.getId());
            cashTransaction.setSpentId("activeMarketing");
            Response resp = cashService.createActiveMarketing(cashTransaction, xeng_active_marketing);
            if (resp.isSuccess()) {
                seller.setMarketing(true);
                sellerRepository.save(seller);
            }
            return resp;
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    /**
     * Danh sách khách hàng theo seller
     *
     * @param search
     * @param user
     * @return
     */
    public DataPage<SellerCustomer> search(SellerCustomerSearch search, User user) {
        Criteria criteria = new Criteria();
        criteria.and("sellerId").is(user.getId());
        criteria.and("remove").is(false);
        if (search.getCname() != null && !search.getCname().equals("")) {
            criteria.and("name").regex(search.getCname().trim().toLowerCase() + ".*", "i");
        } else if (search.getName() != null && !search.getName().equals("")) {
            criteria.and("name").regex(search.getName());
        }
        if (search.getEmail() != null && !search.getEmail().equals("")) {
            criteria.and("email").is(search.getEmail());
        }
        if (search.getPhone() != null && !search.getPhone().equals("")) {
            criteria.and("phone").is(search.getPhone());
        }
        Query query = new Query();
        Sort sort = new Sort(Sort.Direction.ASC, "name");
        switch (search.getOption()) {

            case 4:
                sort = new Sort(Sort.Direction.DESC, "updateTime");
                break;
            case 2:
                criteria.and("emailVerified").is(true);
                break;
            case 3:
                criteria.and("phoneVerified").is(true);
                break;
            case 1:
            default:
                sort = new Sort(Sort.Direction.DESC, "createTime");
                break;
        }
        query.with(sort);
        query.addCriteria(criteria);
        query.skip(search.getPageIndex() * search.getPageSize()).limit(search.getPageSize());
        DataPage<SellerCustomer> page = new DataPage<>();
        page.setPageSize(search.getPageSize());
        page.setPageIndex(search.getPageIndex());
        if (page.getPageSize() <= 0) {
            page.setPageSize(5);
        }
        page.setDataCount(customerRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }

        page.setData(customerRepository.find(query));
        return page;
    }

    /**
     * Thêm mới khách hàng, email và phone là duy nhất
     *
     * @param form
     * @param user
     * @return
     */
    public Response add(SellerCustomer form, User user) {
        Map<String, String> error = validator.validate(form);
        try {
            form.setSellerId(user.getId());
            form.setId(customerRepository.genId());

            if (form.getEmail() != null && !form.getEmail().equals("") && customerRepository.exitsUniqueEmail(form.getEmail(), user.getId(), null)) {
                error.put("email", "Địa chỉ email đã tồn tại trong danh sách khách hàng");
            }
            if (form.getPhone() != null && !form.getPhone().equals("") && customerRepository.exitsUniquePhone(form.getPhone(), user.getId(), null)) {
                error.put("phone", "Số điện thoại đã tồn tại trong danh sách khách hàng");
            }
            if (form.getPhone() != null && !form.getPhone().equals("") && !TextUtils.validatePhoneNumber(form.getPhone())) {
                error.put("phone", "Số điện thoại không đúng định dạng");
            }
            User customer = userRepository.getByEmail(form.getEmail());
//            if (customer == null) {
//                error.put("email", "Địa chỉ email không tồn tại trong hệ thống");
//            }
            if (!error.isEmpty()) {
                return new Response(false, null, error);
            }

            if (customer != null) {
                customer.setEmailVerified(true);
                form.setUsername(customer.getUsername());
                form.setEmailVerified(customer.isEmailVerified());
                if (form.getAddress() == null || form.getAddress().equals("")) {
                    form.setAddress(customer.getAddress());
                }
            }

            form.setPhoneVerified(true);
            form.setCreateTime(new Date().getTime());
            form.setUpdateTime(new Date().getTime());
            customerRepository.save(form);
            return new Response(true, "Thông tin khách hàng đã được thêm vào danh sách khách hàng của bạn", form);
        } catch (Exception e) {
            return new Response(false, "Thông tin chưa được nhập", new HashMap<String, String>());
        }
    }

    /**
     * Cập nhật thông tin khách hàng, không cho sửa email
     *
     * @param form
     * @param user
     * @return
     */
    public Response edit(SellerCustomer form, User user) {
        try {
            SellerCustomer customer = customerRepository.find(form.getId());
            if (customer == null) {
                return new Response(false, "Không tìm thấy khách hàng trên hệ thống", new HashMap<String, String>());
            }
            if (!customer.getSellerId().equals(user.getId())) {
                return new Response(false, "Khách hàng này không nằm trong danh sách khách hàng của bạn", new HashMap<String, String>());
            }
            Map<String, String> error = validator.validate(form);
            if (form.getPhone() != null && !form.getPhone().equals("") && customerRepository.exitsUniquePhone(form.getPhone(), user.getId(), customer.getId()) && !form.getPhone().equals(customer.getPhone())) {
                error.put("phone", "Số điện thoại đã tồn tại trong danh sách khách hàng");
            }
            if (!customer.getEmail().equals(form.getEmail())) {
                error.put("email", "Không được sửa email khách hàng");
            }
            if (!error.isEmpty()) {
                return new Response(false, "Dữ liệu không chính xác, không thể cập nhật thông tin khách hàng", error);
            }

            customer.setUpdateTime(new Date().getTime());
            customer.setAddress(form.getAddress());
            customer.setPhone(form.getPhone());
            customer.setName(form.getName());
            customerRepository.save(customer);
            return new Response(true, "Thông tin khách hàng đã được cập nhật vào danh sách khách hàng của bạn", customer);
        } catch (Exception e) {
            return new Response(false, "Thông tin chưa được nhập", new HashMap<String, String>());
        }
    }

    /**
     * Xóa khách hàng
     *
     * @param ids
     * @return
     */
    public Response remove(List<String> ids) {
        Criteria criteria = new Criteria();
        criteria.and("_id").in(ids);
        customerRepository.remove(criteria);
        return new Response(true, "Khách hàng được xóa thành công trên hệ thống", new HashMap<String, String>());
    }

    /**
     * Xóa khách hàng
     *
     * @param id
     * @return
     */
    public Response remove(String id) {
        SellerCustomer customer = customerRepository.find(id);
        if (customer == null) {
            return new Response(false, "Không tìm thấy khách hàng trên hệ thống", new HashMap<String, String>());
        }
        customerRepository.delete(id);
        return new Response(true, "Khách hàng được xóa thành công trên hệ thống", new HashMap<String, String>());
    }

    /**
     * Lấy thông tin từ email
     *
     * @param email
     * @param user
     * @return
     */
    public Response checkByEmail(String email, User user) {
        if (email == null || email.equals("")) {
            return new Response(false, "Địa chỉ email không được để trống");
        }
        if (!TextUtils.validateEmailString(email)) {
            return new Response(false, "Địa chỉ email không đúng định dạng");
        }
        if (customerRepository.exitsUniqueEmail(email, user.getId(), null)) {
            return new Response(false, "Địa chỉ email đã có trong danh sách khách hàng của bạn");
        }
        return new Response(true, "Thông tin lấy ra từ email", userRepository.getByEmail(email));
    }

    /**
     * Kiểm tra số điện thoại
     *
     * @param phone
     * @param user
     * @return
     */
    public Response checkByPhone(String phone, User user) {
        if (phone == null || phone.equals("")) {
            return new Response(false, "Số điện thoại không được để trống");
        }
        if (!TextUtils.validatePhoneNumber(phone)) {
            return new Response(false, "Số điện thoại không đúng định dạng");
        }
        if (customerRepository.exitsUniquePhone(phone, user.getId(), null)) {
            return new Response(false, "Số điện thoại đã có trong danh sách khách hàng của bạn");
        }
        return new Response(true, "Số điện thoại đủ điều kiện", phone);
    }

    /**
     * Lấy thông tin khách hàng trong danh sách theo Id
     *
     * @param id
     * @return
     */
    public Response getById(String id) {
        SellerCustomer sellerCustomer = customerRepository.find(id);
        if (sellerCustomer == null) {
            return new Response(false, "Không tồn tại khách hàng");
        }
        return new Response(true, null, sellerCustomer);
    }

    /**
     * Lấy thông tin khách hàng trong danh sách theo Ids
     *
     * @param ids
     * @return
     */
    public List<SellerCustomer> getById(List<String> ids) {
        return customerRepository.getByIds(ids);
    }

    /**
     * Lấy danh sách mã khách hàng theo Id marketingEmail
     *
     * @param emails
     * @return
     * @throws java.lang.Exception
     */
    public List<SellerCustomer> getCustomerByEmails(List<String> emails) throws Exception {
        return customerRepository.getByEmails(emails);

    }

}
