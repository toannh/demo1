package vn.chodientu.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.chodientu.component.EmailClient;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.Email;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.Gender;
import vn.chodientu.entity.enu.PaymentStatus;
import vn.chodientu.entity.enu.ShipmentStatus;
import vn.chodientu.entity.input.EmailSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.EmailRepository;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.OrderRepository;
import vn.chodientu.repository.SellerRepository;
import vn.chodientu.repository.UserRepository;

/**
 * @since May 16, 2014
 * @author TheHoa
 */
@Service
public class EmailNewsletterService {

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private EmailClient emailClient;
    @Autowired
    private Validator validator;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Scheduled(fixedDelay = 12 * 60 * 60 * 1000)
    public void doSend() {
        this.processOrder();
        this.processSeller();
        this.processEmail();
        this.processUser(); 
    }

    @Async
    private void processUser() {
        while (true) {
            User user = userRepository.addContact();
            if (user == null) {
                break;
            }
            if (!user.isEmailVerified()) {
                continue;
            }

            if (user.getGender() == Gender.FEMALE) {
                try {
                    //Toàn bộ thành viên nữ ChợĐiệnTử
                    emailClient.addEmail(user.getEmail(), 17);
                } catch (Exception e) {
                }
            }
            if (user.getGender() == Gender.MALE) {
                try {
                    //Toàn bộ thành viên Nam ChợĐiệnTử
                    emailClient.addEmail(user.getEmail(), 18);
                } catch (Exception e) {
                }
            }

            if (user.getUpdateTime() / 1000 > 1325371435) {
                try {
                    //Toàn bộ thành viên ChợĐiệnTử có đăng nhập 2 năm gần đây
                    emailClient.addEmail(user.getEmail(), 19);
                } catch (Exception e) {
                }
            }

            try {
                //Toàn bộ thành viên ChợĐiệnTử
                emailClient.addEmail(user.getEmail(), 16);
            } catch (Exception e) {
            }
        }
    }

    @Async
    private void processOrder() {
        while (true) {
            Order order = orderRepository.pushPayment();
            if (order == null) {
                break;
            }
            User user = userRepository.find(order.getSellerId());
            // Toàn bộ người bán có giao dịch thành công (đã có đơn thanh toán online hoặc đơn CoD, đơn vận chuyển thành công)
            if (user != null && user.isEmailVerified() && (order.getPaymentStatus() == PaymentStatus.PAID || order.getShipmentStatus() == ShipmentStatus.DELIVERED)) {
                try {
                    emailClient.addEmail(user.getEmail(), 23);
                } catch (Exception e) {
                }
            }
            //Toàn bộ người mua ChợĐiệnTử đã thanh toán (thanh toán Online và thanh toán CoD thành công)
            user = userRepository.find(order.getBuyerId());
            if (user != null && user.isEmailVerified() && (order.getPaymentStatus() == PaymentStatus.PAID || order.getShipmentStatus() == ShipmentStatus.DELIVERED)) {
                try {
                    emailClient.addEmail(user.getEmail(), 21);
                } catch (Exception e) {
                }
            }

        }

        while (true) {
            Order order = orderRepository.addContact();
            if (order == null) {
                break;
            }
            User user = userRepository.find(order.getSellerId());
            //Toàn bộ người bán
            if (user != null && user.isEmailVerified()) {
                try {
                    emailClient.addEmail(user.getEmail(), 15);
                } catch (Exception e) {
                }
            }
            //Toàn bộ người mua ChợĐiệnTử (đặt hàng, có thể là chưa thanh toán)
            user = userRepository.find(order.getBuyerId());
            if (user != null && user.isEmailVerified()) {
                try {
                    emailClient.addEmail(user.getEmail(), 20);
                } catch (Exception e) {
                }
            }

        }
    }

    @Async
    private void processSeller() {

        while (true) {
            Seller seller = sellerRepository.pushIntegrated();
            if (seller == null) {
                break;
            }
            User user = userRepository.find(seller.getUserId());
            if (user == null || !user.isEmailVerified()) {
                continue;
            }
            if (seller.isNlIntegrated() && seller.isScIntegrated()) {
                try {
                    //Toàn bộ người bán có tích hợp CoD và Ngân lượng
                    emailClient.addEmail(user.getEmail(), 24);
                } catch (Exception e) {
                }
            } else {
                try {
                    //Toàn bộ người bán chưa tích hợp CoD và Ngân lượng
                    emailClient.addEmail(user.getEmail(), 25);
                } catch (Exception e) {
                }
            }
        }

        while (true) {
            Seller seller = sellerRepository.addContact();
            if (seller == null) {
                break;
            }
            User user = userRepository.find(seller.getUserId());
            if (user == null || !user.isEmailVerified()) {
                continue;
            }
            if (itemRepository.countBySeller(seller.getUserId()) < 0) {
                continue;
            }
            Item item = itemRepository.findOneBySeller(seller.getUserId());
            if (item != null && (item.getCreateTime() / 1000 > 1388509200)) {
                try {
                    emailClient.addEmail(user.getEmail(), 14);
                } catch (Exception e) {
                }
            }
            try {
                emailClient.addEmail(user.getEmail(), 13);
            } catch (Exception e) {
            }
        }
    }

    @Async
    private void processEmail() {
        while (true) {
            Email email = emailRepository.addContact();
            if (email == null) {
                break;
            }
            try {
                emailClient.addEmail(email.getEmail(), 2);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Đăng kí email nhận tin tức từ CĐT
     *
     * @param email
     * @return
     */
    public Response addEmail(Email email) {
        Map<String, String> error = validator.validate(email);
        if (emailRepository.exists(email.getEmail())) {
            error.put("email", "Email này đã được đăng ký!");
        }
        if (error.isEmpty()) {
            email.setCreateTime(System.currentTimeMillis());
            email.setUpdateTime(System.currentTimeMillis());
            emailRepository.save(email);
            return new Response(true, "Đăng ký email nhận tin thành công!", email);
        }
        return new Response(false, "", error);
    }

    public DataPage<Email> search(EmailSearch search) {
        DataPage<Email> dataPage = new DataPage<>();
        Criteria cri = new Criteria();
        if (search.getEmail() != null && !search.getEmail().trim().equals("")) {
            cri.and("email").regex(search.getEmail());
        }

        Query query = new Query(cri);
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        dataPage.setDataCount(emailRepository.count(query));
        dataPage.setPageIndex(search.getPageIndex());
        dataPage.setPageSize(search.getPageSize());
        dataPage.setPageCount(dataPage.getDataCount() / search.getPageSize());
        if (dataPage.getDataCount() % search.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        dataPage.setData(emailRepository.find(query.limit(search.getPageSize()).skip(search.getPageIndex() * search.getPageSize())));
        return dataPage;
    }

    public Response delEmail(String email) {
        Email em = emailRepository.find(email);
        if (em == null) {
            return new Response(false, "Không tồn tại email này!");
        }
        emailRepository.delete(em);
        return new Response(true, "Xóa thành công!");
    }
}
