package vn.chodientu.service;

import java.util.ArrayList;
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
import vn.chodientu.entity.db.Administrator;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.db.SellerCustomer;
import vn.chodientu.entity.db.SellerEmailMarketing;
import vn.chodientu.entity.db.SellerSmsMarketing;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.EmailOutboxType;
import vn.chodientu.entity.enu.EmailTemplate;
import vn.chodientu.entity.enu.SmsOutboxType;
import vn.chodientu.entity.input.SellerMarketingSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.SellerCustomerRepository;
import vn.chodientu.repository.SellerEmailMarketingRepository;
import vn.chodientu.repository.SellerSmsMarketingRepository;
import vn.chodientu.repository.ShopRepository;
import vn.chodientu.repository.SmsOutboxRepository;
import vn.chodientu.repository.UserRepository;

/**
 * @author thanhvv
 */
@Service
public class SellerMarketingService {

    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SellerMarketingService.class);

    @Autowired
    private SellerEmailMarketingRepository emailMarketingRepository;
    @Autowired
    private SellerSmsMarketingRepository smsMarketingRepository;
    @Autowired
    private SellerCustomerRepository customerRepository;
    @Autowired
    private CashService cashService;
    @Autowired
    private SmsOutboxRepository smsOutboxRepository;
    @Autowired
    private SmsService smsService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Validator validator;
    public final long xeng_sms = 100;
    public final long xeng_email = 20;
    private final long run_time = 0;

    @Scheduled(fixedDelay = 15 * 60 * 1000)
//    @Scheduled(fixedDelay = 1000)
    public void runSms() {
        while (true) {
            SellerSmsMarketing phoneMarketing = smsMarketingRepository.getSmsMarketing(0, System.currentTimeMillis());
            if (phoneMarketing == null) {
                break;
            }
            this.processSmsMarketing(phoneMarketing);
        }
    }

//    @Scheduled(fixedDelay = 1000)
    @Scheduled(fixedDelay = 1 * 60 * 1000)
    public void runEmail() {
        while (true) {
            SellerEmailMarketing emailMarketing = emailMarketingRepository.getEmailMarketing(0, System.currentTimeMillis());
            if (emailMarketing == null) {
                break;
            }
            this.processEmailMarketing(emailMarketing);
        }
    }

    /**
     * Tìm kiếm
     *
     * @param search
     * @param user
     * @return
     */
    private Criteria seach(SellerMarketingSearch search, User user) {
        Criteria criteria = new Criteria();
        if (search.getDone() > 0) {
            criteria.and("done").is(search.getDone() == 1);
        }
        if (search.getRun() > 0) {
            criteria.and("run").is(search.getRun() == 1);
        }
        if (search.getActive() > 0) {
            criteria.and("active").is(search.getActive() == 1);
        }
        if (search.getFromEmail() != null && !search.getFromEmail().equals("")) {
            criteria.and("fromEmail").is(search.getFromEmail());
        }
        if (search.getFromName() != null && !search.getFromName().equals("")) {
            criteria.and("fromName").is(search.getFromName());
        }
        if (search.getName() != null && !search.getName().equals("")) {
            criteria.and("name").is(search.getName());
        }
        if (search.getId() != null && !search.getId().equals("")) {
            criteria.and("_id").is(search.getId());
        }
        if (search.getSellerId() != null && !search.getSellerId().equals("")) {
            criteria.and("sellerId").is(search.getSellerId());
        } else if (user != null) {
            criteria.and("sellerId").is(user.getId());
        }
        if (search.getSendTimeForm() > 0 && search.getSendTimeTo() > 0) {
            criteria.and("sendTime").gt(search.getSendTimeForm()).lte(search.getSendTimeTo());
        } else if (search.getSendTimeForm() > 0) {
            criteria.and("sendTime").gt(search.getSendTimeForm());
        } else if (search.getSendTimeTo() > 0) {
            criteria.and("sendTime").lte(search.getSendTimeTo());
        }
        return criteria;
    }

    /**
     * Danh sachs email mail template
     *
     * @param search
     * @param user
     * @return
     */
    public DataPage<SellerEmailMarketing> searchEmailMarketing(SellerMarketingSearch search, User user) {
        Criteria criteria = this.seach(search, user);
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "updateTime"));
        query.skip(search.getPageIndex() * search.getPageSize()).limit(search.getPageSize());
        DataPage<SellerEmailMarketing> page = new DataPage<>();
        page.setPageSize(search.getPageSize());
        page.setPageIndex(search.getPageIndex());
        if (page.getPageSize() <= 0) {
            page.setPageSize(5);
        }
        page.setDataCount(emailMarketingRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }
        page.setData(emailMarketingRepository.find(query));
        return page;
    }

    /**
     * Danh sách sms template
     *
     * @param search
     * @param user
     * @return
     */
    public DataPage<SellerSmsMarketing> searchSmsMarketing(SellerMarketingSearch search, User user) {
        Criteria criteria = this.seach(search, user);
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "updateTime"));
        query.skip(search.getPageIndex() * search.getPageSize()).limit(search.getPageSize());
        DataPage<SellerSmsMarketing> page = new DataPage<>();
        page.setPageSize(search.getPageSize());
        page.setPageIndex(search.getPageIndex());
        if (page.getPageSize() <= 0) {
            page.setPageSize(5);
        }
        page.setDataCount(smsMarketingRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }
        page.setData(smsMarketingRepository.find(query));
        return page;
    }

    /**
     * Thêm email marketing
     *
     * @param form
     * @param user
     * @return
     */
    public Response addEmail(SellerEmailMarketing form, User user) {
        try {
            form.setSellerId(user.getId());
            Map<String, String> error = validator.validate(form);
            long time = new Date().getTime();
            if (form.getSendTime() <= time) {
                error.put("sendDate", "Thời gian gửi phải lớn hơn thời gian hiện tại");
            }
            if (form.getTemplate() == null) {
                error.put("template", "Mẫu email không được để trống");
            }
            if (!error.isEmpty()) {
                return new Response(false, "Dữ liệu không chính xác, không thể thêm mới", error);
            }
            form.setCreateTime(time);
            form.setUpdateTime(time);
            form.setFromEmail(user.getEmail());
            form.setFromName(user.getName() != null && !user.getName().equals("") ? user.getName() : user.getEmail());
            form.setRun(false);
            form.setDone(false);
//            form.setActive(true);
            int totalXeng = 0;
            if (form.getEmail() != null && !form.getEmail().isEmpty()) {
                totalXeng = form.getEmail().size();
            }
            CashTransaction cashTransaction = new CashTransaction();
            cashTransaction.setUserId(user.getId());
            cashTransaction.setSpentId(emailMarketingRepository.genId());
            cashTransaction.setSpentQuantity(totalXeng);
            Response resp = cashService.createMarketingPayment(cashTransaction, xeng_email, CashTransactionType.SPENT_EMAIL);
            if (resp.isSuccess()) {
                long xeng = totalXeng * xeng_email;
                form.setBalance(xeng);
            }
            emailMarketingRepository.save(form);
            return new Response(true, "Thông tin cấu hình email marketing được thêm mới thành công", form);
        } catch (Exception e) {
            return new Response(false, "Thông tin chưa được nhập", e.getMessage());
        }
    }

    /**
     * Cập nhật thông tin cấu hình email marketing
     *
     * @param form
     * @param user
     * @return
     */
    public Response editEmail(SellerEmailMarketing form, User user) {
        try {
            SellerEmailMarketing emailMarketing = emailMarketingRepository.find(form.getId());
            if (emailMarketing == null) {
                return new Response(false, "Không tìm thấy thông tin cấu hình email mareting yêu cầu", new HashMap<String, String>());
            }
            if (!emailMarketing.getSellerId().equals(user.getId())) {
                return new Response(false, "Thông tin email mareting này không phải của bạn", new HashMap<String, String>());
            }
            Map<String, String> error = validator.validate(form);
            if (form.getTemplate() == null) {
                error.put("template", "Mẫu email không được để trống");
            }
            long time = new Date().getTime();
            if (form.getSendTime() <= time) {
                error.put("sendTime", "Thời gian gửi phải lớn hơn thời gian hiện tại");
            }
            if (!error.isEmpty()) {
                return new Response(false, "Dữ liệu không chính xác, không thể thêm mới", error);
            }
            emailMarketing.setUpdateTime(time);
            emailMarketing.setContent(form.getContent());
            emailMarketing.setName(form.getName());
            emailMarketing.setSendTime(form.getSendTime());
            emailMarketing.setTemplate(form.getTemplate());
            emailMarketing.setFromEmail(user.getEmail());
            emailMarketing.setFromName(user.getName() != null && !user.getName().equals("") ? user.getName() : user.getEmail());
            emailMarketingRepository.save(emailMarketing);
            return new Response(true, "Thông tin cấu hình email mareting đã được cập nhật lại trên hệ thống", error);
        } catch (Exception e) {
            return new Response(false, "Thông tin chưa được nhập", new HashMap<String, String>());
        }
    }

    /**
     * Add thêm email vào template
     *
     * @param emails
     * @param sellerEmailMarketingId
     * @param user
     * @return
     */
    public Response addEmailCustomer(List<String> emails, String sellerEmailMarketingId, User user) {
        try {
            SellerEmailMarketing emailMarketing = emailMarketingRepository.find(sellerEmailMarketingId);
            if (emailMarketing == null) {
                return new Response(false, "Không tìm thấy thông tin cấu hình email mareting yêu cầu", new HashMap<String, String>());
            }
            if (!emailMarketing.getSellerId().equals(user.getId())) {
                return new Response(false, "Thông tin email mareting này không phải của bạn", new HashMap<String, String>());
            }
            List<String> es = emailMarketing.getEmail();
            if (es == null || es.isEmpty()) {
                es = new ArrayList<>();
            } else {
                emails = addEmailVerified(es, emails, emailMarketing.getSellerId());
            }

            if (emails == null || emails.isEmpty()) {
                return new Response(false, "Danh sách email không tìm thấy email đủ điều kiện gửi email marketing", new HashMap<String, String>());
            }
            if (es != null && !es.isEmpty()) {
                emails.addAll(es);
            }
            int change = emails.size() - es.size();
            if (change <= 0) {
                return new Response(false, "Danh sách số điện thoại không thay đổi", "EMAIL_NOT_CHANGE");
            }
            CashTransaction cashTransaction = new CashTransaction();
            cashTransaction.setUserId(user.getId());
            cashTransaction.setSpentId(emailMarketing.getId());
            cashTransaction.setSpentQuantity(change);
            Response resp = cashService.createMarketingPayment(cashTransaction, cashTransaction.getSpentQuantity() * xeng_email, CashTransactionType.SPENT_EMAIL);
            if (resp.isSuccess()) {
                emailMarketing.setEmail(emails);
                long xeng = emails.size() * xeng_email;
                emailMarketing.setBalance(xeng);
            }
            emailMarketingRepository.save(emailMarketing);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false, e.getMessage(), new HashMap<String, String>());
        }
    }

    /**
     * Danh sách email đủ điều kiện gửi email
     *
     * @param arr
     * @param adds
     * @param sellerId
     * @return
     */
    public List<String> addEmailVerified(List<String> arr, List<String> adds, String sellerId) {
        if (arr == null) {
            arr = new ArrayList<>();
        }
        boolean fag = true;
        List<SellerCustomer> customer = customerRepository.getByEmail(adds, 1, sellerId);
        adds = new ArrayList<>();
        for (SellerCustomer sellerCustomer : customer) {
            if (!arr.contains(sellerCustomer.getEmail())) {
                adds.add(sellerCustomer.getEmail());
            } else {
                fag = false;
                break;
            }
        }
        if (!fag) {
            adds = null;
        }
        return adds;
    }

    /**
     * Danh sách phone đủ điều kiện gửi email
     *
     * @param arr
     * @param adds
     * @return
     */
    public List<String> addPhoneVerified(List<String> arr, List<String> adds, String sellerId) {
        if (arr == null) {
            arr = new ArrayList<>();
        }
        boolean fag = true;
        List<SellerCustomer> customer = customerRepository.getByPhone(adds, 0, sellerId);
        adds = new ArrayList<>();
        for (SellerCustomer sellerCustomer : customer) {
            if (!arr.contains(sellerCustomer.getPhone())) {
                adds.add(sellerCustomer.getPhone());
            } else {
                fag = false;
                break;
            }
        }
        if (!fag) {
            adds = null;
        }

        return adds;
    }

    /**
     * Thêm số điện thoại vào danh sách
     *
     * @param phones
     * @param sellerSmsMarketingId
     * @param user
     * @return
     */
    public Response addPhoneCustomer(List<String> phones, String sellerSmsMarketingId, User user) {
        try {
            SellerSmsMarketing smsMarketing = smsMarketingRepository.find(sellerSmsMarketingId);
            if (smsMarketing == null) {
                return new Response(false, "Không tìm thấy thông tin cấu hình sms mareting yêu cầu", new HashMap<String, String>());
            }
            if (!smsMarketing.getSellerId().equals(user.getId())) {
                return new Response(false, "Thông tin sms mareting này không phải của bạn", new HashMap<String, String>());
            }
            List<String> list = smsMarketing.getPhone();
            if (list == null || list.isEmpty()) {
                list = new ArrayList<>();
            } else {
                phones = addPhoneVerified(list, phones, smsMarketing.getSellerId());
            }

            if (phones == null || phones.isEmpty()) {
                return new Response(false, "Danh sách số điện thoại không tìm thấy số điện thoại đủ điều kiện gửi sms marketing", new HashMap<String, String>());
            }
            if (list != null && !list.isEmpty()) {
                phones.addAll(list);
            }
            int change = phones.size() - list.size();
            if (change <= 0) {
                return new Response(false, "Danh sách số điện thoại không thay đổi", "SMS_NOT_CHANGE");
            }

            CashTransaction cashTransaction = new CashTransaction();
            cashTransaction.setUserId(user.getId());
            cashTransaction.setSpentId(smsMarketing.getId());
            cashTransaction.setSpentQuantity(change);
            Response resp = cashService.createMarketingPayment(cashTransaction, cashTransaction.getSpentQuantity() * xeng_sms, CashTransactionType.SPENT_SMS);
            if (resp.isSuccess()) {
                smsMarketing.setPhone(phones);
                long xeng = phones.size() * xeng_sms;
                smsMarketing.setBalance(xeng);
            }
            smsMarketingRepository.save(smsMarketing);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false, e.getMessage(), new HashMap<String, String>());
        }
    }

    /**
     * Thêm mới phone template
     *
     * @param form
     * @param user
     * @return
     */
    public Response addPhone(SellerSmsMarketing form, User user) {
        try {
            form.setSellerId(user.getId());
            Map<String, String> error = validator.validate(form);
            long time = new Date().getTime();
            if (form.getSendTime() <= 0) {
                error.put("sendTime", "Bạn phải chọn thời gian gửi!");
            }
            if (form.getContent().length() > 150) {
                error.put("content", "Nội dung sms dài quá 150 kí tự!");
            }
            if (!error.isEmpty()) {
                return new Response(false, "Dữ liệu không chính xác, không thể thêm mới", error);
            }
            form.setCreateTime(time);
            form.setUpdateTime(time);
            form.setRun(false);
            form.setDone(false);
//            form.setActive(true);
            int totalXeng = 0;
            if (form.getPhone() != null && !form.getPhone().isEmpty()) {
                totalXeng = form.getPhone().size();
            }
            CashTransaction cashTransaction = new CashTransaction();
            cashTransaction.setUserId(user.getId());
            cashTransaction.setSpentId(smsMarketingRepository.genId());
            cashTransaction.setSpentQuantity(totalXeng);
            Response resp = cashService.createMarketingPayment(cashTransaction, cashTransaction.getSpentQuantity() * xeng_sms, CashTransactionType.SPENT_SMS);
            if (resp.isSuccess()) {
                long xeng = totalXeng * xeng_sms;
                form.setBalance(xeng);
            }
            smsMarketingRepository.save(form);
            return new Response(true, "Thông tin cấu hình sms marketing được thêm mới thành công", form);
        } catch (Exception e) {
            return new Response(false, "Thông tin chưa được nhập", new HashMap<String, String>());
        }
    }

    /**
     * Cập nhật số điện thoại
     *
     * @param form
     * @param user
     * @return
     */
    public Response editPhone(SellerSmsMarketing form, User user) {
        try {
            SellerSmsMarketing phoneMarketing = smsMarketingRepository.find(form.getId());
            if (phoneMarketing == null) {
                return new Response(false, "Không tìm thấy thông tin cấu hình sms mareting yêu cầu", new HashMap<String, String>());
            }
            if (!phoneMarketing.getSellerId().equals(user.getId())) {
                return new Response(false, "Thông tin sms mareting này không phải của bạn", new HashMap<String, String>());
            }
            Map<String, String> error = validator.validate(form);
            long time = new Date().getTime();
            if (form.getSendTime() <= time) {
                error.put("sendTime", "Thời gian gửi phải lớn hơn thời gian hiện tại");
            }
            if (!error.isEmpty()) {
                return new Response(false, "Dữ liệu không chính xác, không thể thêm mới", error);
            }
            phoneMarketing.setUpdateTime(time);
            phoneMarketing.setContent(form.getContent());
            phoneMarketing.setName(form.getName());
            phoneMarketing.setSendTime(form.getSendTime());
            smsMarketingRepository.save(phoneMarketing);
            return new Response(true, "Thông tin cấu hình sms mareting đã được cập nhật lại trên hệ thống", error);
        } catch (Exception e) {
            return new Response(false, "Thông tin chưa được nhập", new HashMap<String, String>());
        }
    }

    /**
     * Xóa sms theo danh sách id
     *
     * @param ids
     * @param seller
     * @return
     */
    public Response removeSms(List<String> ids, User seller) {
        smsMarketingRepository.delete(new Query(new Criteria("id").in(ids).and("sellerId").is(seller.getId())));
        return new Response(true, "Danh sách sms đã được xóa thành công");
    }

    @Async
    public void processEmailMarketing(SellerEmailMarketing marketing) {
        if (marketing.getEmail() != null && !marketing.getEmail().equals("")) {
            for (String email : marketing.getEmail()) {
                if (email != null && !email.equals("")) {
                    try {
                        emailService.send(EmailOutboxType.EMAIL_MARKETING, email,
                                "[Chợ điện tử] " + marketing.getName(),
                                marketing.getTemplate().toString(), this.render(marketing));
                    } catch (Exception e) {
                        logger.info(e.getMessage());
                    }
                }
            }
        }
        marketing.setDone(true);
        marketing.setRun(true);
        marketing.setUpdateTime(System.currentTimeMillis());
        marketing.setSenddone(marketing.getEmail().size());
        emailMarketingRepository.save(marketing);
    }

    @Async
    private void processSmsMarketing(SellerSmsMarketing marketing) {
        if (marketing.getPhone() != null && !marketing.getPhone().equals("")) {
            for (String phone : marketing.getPhone()) {
                if (phone != null && !phone.equals("")) {
                    try {
                        smsService.send(phone, marketing.getContent(), SmsOutboxType.SMS_MARKETING, System.currentTimeMillis(), 2);
                    } catch (Exception e) {
                        logger.info(e.getMessage());
                    }
                }
            }
        }
        marketing.setDone(true);
        marketing.setRun(true);
        marketing.setSenddone(marketing.getPhone().size());
        smsMarketingRepository.save(marketing);
    }

    /**
     * Gen html template
     *
     * @param marketing
     * @param template
     * @return
     */
    private Map<String, Object> render(SellerEmailMarketing marketing) {
        Map<String, Object> data = new HashMap<>();
        data.put("subject", "[Chợ điện tử] " + marketing.getName());
        data.put("body", marketing.getContent());

        try {
            String sellerName = "";
            String address = "";
            String email = "";
            String phone = "";
            Shop shop = shopRepository.findByUser(marketing.getSellerId());
            if (shop != null) {
                sellerName = "Shop " + shop.getTitle();
                address = shop.getAddress() == null || shop.getAddress().equals("") ? "" : shop.getAddress();
                email = shop.getEmail() == null || shop.getEmail().equals("") ? "" : shop.getEmail();
                phone = shop.getPhone() == null || shop.getPhone().equals("") ? "" : shop.getPhone();
            } else {
                User user = userRepository.find(marketing.getSellerId());
                sellerName = "Người bán:";
                if (user != null) {
                    sellerName += user.getName() == null || user.getName().equals("") ? user.getEmail() : user.getName();
                    email = user.getEmail() == null || user.getEmail().equals("") ? "" : user.getEmail();
                    address = user.getAddress() == null || user.getAddress().equals("") ? "" : user.getAddress();
                    phone = user.getPhone() == null || user.getPhone().equals("") ? "" : user.getPhone();
                }
            }
            data.put("sellerName", sellerName);
            data.put("address", address);
            data.put("email", email);
            data.put("phone", phone);
        } catch (Exception e) {
            data.put("sellerName", "");
            data.put("address", "");
            data.put("email", "");
            data.put("phone", "");
        }

        return data;
    }

    public String getTemplate(SellerEmailMarketing marketing, EmailTemplate template) throws Exception {
        Map<String, Object> data = this.render(marketing);
        try {
            return emailService.render(template.toString().toLowerCase(), data);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Duyệt email marketing
     *
     * @param administrator
     * @param id
     * @param active
     * @return Bỏ tạm Administrator param
     */
    public Response activeEmailMarketing(Administrator administrator, String id) throws Exception {
        SellerEmailMarketing emailMarketing = emailMarketingRepository.find(id);
        if (emailMarketing == null) {
            return new Response(false, "Không tìm thấy thông tin cấu hình email marketing", id);
        }
        if (emailMarketing.isDone() || emailMarketing.isRun()) {
            return new Response(false, "Không thể thay đổi trạng thái, email marketing đã chạy hoặc chạy xong");
        } else {
            emailMarketing.setActive(!emailMarketing.isActive());
            emailMarketing.setAdminActive(administrator.getEmail());
            if (emailMarketing.isActive() && emailMarketing.getSendTime() <= System.currentTimeMillis()) {
                this.processEmailMarketing(emailMarketing);
            }
            emailMarketingRepository.save(emailMarketing);
            return new Response(true, "Email marketing đã được duyệt", emailMarketing);
        }
    }

    /**
     * Duyệt phone marketing
     *
     * @param administrator
     * @param id
     * @param active
     * @return Bỏ tạm Administrator param
     */
    public Response activeSmsMarketing(Administrator administrator, String id) throws Exception {
        SellerSmsMarketing smsMarketing = smsMarketingRepository.find(id);
        if (smsMarketing == null) {
            return new Response(false, "Không tìm thấy thông tin cấu hình sms marketing");
        }
        if (smsMarketing.isDone() || smsMarketing.isRun()) {
            return new Response(false, "Không thể thay đổi trạng thái, sms marketing đã chạy hoặc chạy xong");
        } else {
            smsMarketing.setActive(!smsMarketing.isActive());
            smsMarketing.setAdminActive(administrator.getEmail());
            if (smsMarketing.isActive() && smsMarketing.getSendTime() <= System.currentTimeMillis()) {
                this.processSmsMarketing(smsMarketing);
            }
            smsMarketingRepository.save(smsMarketing);
            return new Response(true, "Sms marketing đã được duyệt", smsMarketing);
        }
    }

    /**
     * Lấy email marketing theo id
     *
     * @param id
     * @return
     * @throws Exception
     */
    public SellerEmailMarketing getEmail(String id) throws Exception {
        SellerEmailMarketing email = emailMarketingRepository.find(id);
        if (email == null) {
            throw new Exception("Không tìm thấy email marketing yêu cầu");
        }
        return email;
    }

    /**
     * Lấy sms marketing theo id
     *
     * @param id
     * @return
     * @throws Exception
     */
    public SellerSmsMarketing getSms(String id) throws Exception {
        SellerSmsMarketing sms = smsMarketingRepository.find(id);
        if (sms == null) {
            throw new Exception("Không tìm thấy sms marketing yêu cầu");
        }
        return sms;
    }

    /**
     * Danh sách SellerSmsMarketing chưa chạy, để chọn sms template
     *
     * @param sellerId
     * @return
     */
    public List<SellerSmsMarketing> smsVerified(String sellerId) {
        return smsMarketingRepository.smsVerified(sellerId);
    }

    /**
     * Danh sách SellerEmailMarketing chưa chạy, để chọn email template
     *
     * @param sellerId
     * @return
     */
    public List<SellerEmailMarketing> emailVerified(String sellerId) {
        return emailMarketingRepository.emailVerified(sellerId);
    }

}
