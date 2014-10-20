package vn.chodientu.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.component.TopUpClient;
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.db.TopUp;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.EmailOutboxType;
import vn.chodientu.entity.input.TopUpSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.repository.CashRepository;
import vn.chodientu.repository.CashTransactionRepository;
import vn.chodientu.repository.TopUpRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;

@Service
public class TopUpService {

    @Autowired
    private TopUpRepository topUpRepository;
    @Autowired
    private TopUpClient topUpClient;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CashRepository cashRepository;
    @Autowired
    private CashTransactionRepository cashTransactionRepository;
    @Autowired
    private SmsService smsService;
    @Autowired
    private RealTimeService realTimeService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    /**
     * Nạp trực tiếp vào điện thoại
     *
     * @param phone
     * @param userId
     * @param amount
     * @return
     * @throws Exception
     */
    public TopUp topupTelco(String phone, String userId, TopUpClient.Amount amount) throws Exception {
        User user = userRepository.find(userId);
        if (user == null) {
            throw new Exception("Không tìm thấy tài khoản trên hệ thống");
        }
//        if (user.getPhone() == null || user.getPhone().isEmpty()) {
//            throw new Exception("Tài khoản của bạn chưa có số điện thoại");
//        }
//        if (!user.isPhoneVerified()) {
//            throw new Exception("Số điện thoại " + user.getPhone() + " chưa được xác thực");
//        }
        if (!TextUtils.validatePhoneNumber(phone)) {
            throw new Exception("Số điện thoại không hợp lệ");
        }
        long nAmount = this.getAmount(amount);
        Criteria criteria = new Criteria();
        criteria.and("createTime").gt(TextUtils.firstDayOfMonth(System.currentTimeMillis())).lte(TextUtils.lastDayOfMonth(System.currentTimeMillis()));
        criteria.and("success").is(true);
        long tAmount = topUpRepository.totalAmount(criteria);
        this.blance(userId, amount);
        if (tAmount + nAmount > 200000) {
            throw new Exception("Bạn chỉ được đổi tối đa 200.000 vnđ trong một tháng");
        }
        TopUp topupTelco = topUpClient.topupTelco(userId, phone, amount);
        if (topupTelco.isSuccess()) {
            this.sendAction(topupTelco);
        }
        return topupTelco;
    }

    /**
     * Lấy mã thẻ điện thoại
     *
     * @param userId
     * @param amount
     * @param service
     * @return
     * @throws Exception
     */
    public TopUp buyCardTelco(String userId, TopUpClient.Amount amount, TopUpClient.Service service) throws Exception {
        User user = userRepository.find(userId);
        if (user == null) {
            throw new Exception("Không tìm thấy tài khoản trên hệ thống");
        }
        long nAmount = this.getAmount(amount);
        Criteria criteria = new Criteria();
        criteria.and("createTime").gte(TextUtils.firstDayOfMonth(System.currentTimeMillis())).lte(TextUtils.lastDayOfMonth(System.currentTimeMillis()));
        criteria.and("success").is(true);
        long tAmount = topUpRepository.totalAmount(criteria);
        if (tAmount + nAmount > 200000) {
            throw new Exception("Bạn chỉ được đổi tối đa 200.000 vnđ trong một tháng");
        }

        this.blance(userId, amount);
        TopUp buyCardTelco = topUpClient.buyCardTelco(userId, amount, service);
        if (buyCardTelco.isSuccess()) {
            this.sendAction(buyCardTelco);
        }
        return buyCardTelco;
    }

    private long getAmount(TopUpClient.Amount card) {
        long amount = 10000;
        try {
            String[] amounts = card.toString().split("_");
            amount = Long.parseLong(amounts[1]);
        } catch (Exception e) {
        }
        return amount;
    }

    /**
     * Tính toán cộng xèng
     *
     * @param userId
     * @param card
     * @throws Exception
     */
    private long blance(String userId, TopUpClient.Amount card) throws Exception {
        if (card == null) {
            throw new Exception("Bạn cần chọn mệnh giá thẻ");
        }
        long amount = this.getAmount(card);
        Cash cash = cashRepository.getCash(userId);
        if (cash.getBalance() < amount * 2) {
            throw new Exception("Số xèng trong tài khoản không đủ để thực hiện giao dịch này");
        }

        CashTransaction cashTransaction = new CashTransaction();
        cashTransaction.setId(cashTransactionRepository.genId());
        cashTransaction.setTime(System.currentTimeMillis());
        cashTransaction.setType(CashTransactionType.TOP_UP);
        cashTransaction.setSpentQuantity(1);
        cashTransaction.setUserId(userId);
        cashTransaction.setAmount(amount * 2);
        long monney = cashTransaction.getAmount() * cashTransaction.getSpentQuantity() * -1;
        Cash topupPaymentDone = cashRepository.topupPaymentDone(cashTransaction.getUserId(), monney);
        cashTransaction.setNewBalance(topupPaymentDone.getBalance());
        cashTransactionRepository.save(cashTransaction);
        return amount;
    }

    private void sendAction(TopUp topUp) {
        if (topUp.getType().equals("buyCardTelco")) {
            if (!topUp.isSendEmail()) {
                Map<String, Object> data = new HashMap<>();
                data.put("cardCode", topUp.getCardCode());
                data.put("cardSerial", topUp.getCardSerial());
                data.put("cardType", topUp.getService());
                data.put("cardValue", topUp.getCardValue());
                data.put("expiryDate", topUp.getExpiryDate());
                try {
                    User user = userService.get(topUp.getUserId());
                    data.put("username", user.getUsername());
                    data.put("email", user.getEmail());
                    emailService.send(EmailOutboxType.TOPUP_CARD_TEL, user.getEmail(), "Chúc mừng bạn đã đổi thành công mã thẻ điện thoại", "buycardtel", data);
                } catch (Exception e) {
                }
            }
            if (!topUp.isSendInbox()) {
                try {
                    messageService.send(topUp.getUserId(),
                            "Chúc mừng bạn đã đổi thành công mã thẻ điện thoại",
                            "<p> Chúc mừng bạn đã đổi thành công mã thẻ điện thoại</p>"
                            + "<ul><li>- Nhà mạng: " + topUp.getService() + "</li>"
                            + "<li>- Mã thẻ: " + topUp.getCardCode() + "</li>"
                            + "<li>- Số serials: " + topUp.getCardSerial() + "</li>"
                            + "<li>- Mệnh giá: " + TextUtils.numberFormat(Double.parseDouble(topUp.getAmount() + "")) + "vnđ</li></ul>", null, null);
                    topUp.setSendInbox(true);
                } catch (Exception e) {
                }
            }
            if (!topUp.isSendReadTime()) {
                realTimeService.add("Bạn vừa đổi thành công mã thẻ điện thoại (xem chi tiết email)", topUp.getUserId(), null, "Đổi thẻ điện thoại", null);
                topUp.setSendReadTime(true);
            }
        } else {
            if (!topUp.isSendEmail()) {
                Map<String, Object> data = new HashMap<>();
                try {
                    User user = userService.get(topUp.getUserId());
                    data.put("username", user.getUsername());
                    data.put("email", user.getEmail());
                    data.put("phone", topUp.getPhone());
                    data.put("amount", topUp.getAmount());
                    emailService.send(EmailOutboxType.TOPUP_TEL, user.getEmail(), "Chúc mừng bạn vừa nạp thẻ thành công", "buytel", data);
                } catch (Exception e) {
                }
            }
            if (!topUp.isSendInbox()) {
                try {
                    messageService.send(topUp.getUserId(),
                            "Chúc mừng bạn vừa nạp thành công",
                            "<p>Chúc mừng bạn vừa nạp thành công " + TextUtils.numberFormat(Double.parseDouble(topUp.getAmount() + "")) + "vnđ vào số điện thoại " + topUp.getPhone() + "</p>", null, null);
                    topUp.setSendInbox(true);
                } catch (Exception e) {
                }
            }
            if (!topUp.isSendReadTime()) {
                realTimeService.add("Bạn vừa nạp tiền thành công " + TextUtils.numberFormat(Double.parseDouble(topUp.getAmount() + "")) + "vnđ vào số điện thoại " + topUp.getPhone(), topUp.getUserId(), null, "Nạp điện thoại", null);
                topUp.setSendReadTime(true);
            }
        }
    }

    /**
     * Tìm kiếm lịch sử quy đổi xèng
     *
     * @param search
     * @return
     */
    public DataPage<TopUp> search(TopUpSearch search) {
        Criteria cri = new Criteria();
        if (search.getUserId() != null && !search.getUserId().equals("")) {
            cri.and("userId").is(search.getUserId());
        }
        if (search.getType() != null && !search.getType().equals("")) {
            cri.and("type").is(search.getType());
        }
        if (search.getPhone() != null && !search.getPhone().equals("")) {
            cri.and("phone").is(search.getPhone());
        }
        if (search.getRequestId() != null && !search.getRequestId().equals("")) {
            cri.and("requestId").is(search.getRequestId());
        }
        if (search.getCreateTimeFrom() > 0 && search.getCreateTimeTo() > 0) {
            cri.and("createTime").gte(search.getCreateTimeFrom()).lt(search.getCreateTimeTo());
        } else if (search.getCreateTimeFrom() > 0) {
            cri.and("createTime").gte(search.getCreateTimeFrom());
        } else if (search.getCreateTimeTo() > 0) {
            cri.and("createTime").lt(search.getCreateTimeTo());
        }

        Query query = new Query(cri);
        query.with(new Sort(Sort.Direction.DESC, "createTime"));
        query.skip(search.getPageIndex() * search.getPageSize()).limit(search.getPageSize());

        DataPage<TopUp> page = new DataPage<>();
        page.setPageSize(search.getPageSize());
        page.setPageIndex(search.getPageIndex());
        if (page.getPageSize() <= 0) {
            page.setPageSize(5);
        }
        page.setDataCount(topUpRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }

        page.setData(topUpRepository.find(query));
        return page;
    }

    /**
     * Lấy giao dịch theo id
     *
     * @param id
     * @return
     * @throws Exception
     */
    public TopUp getById(String id) throws Exception {
        TopUp topup = topUpRepository.find(id);
        if (topup == null) {
            throw new Exception("Không tìm thấy giao dịch này!");
        }
        return topup;
    }
}
