package vn.chodientu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vn.chodientu.component.NlClient;
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.db.CashHistory;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.PaymentMethod;
import vn.chodientu.entity.enu.PaymentStatus;
import vn.chodientu.entity.enu.SellerHistoryType;
import vn.chodientu.entity.input.CashSearch;
import vn.chodientu.entity.input.CashTransactionSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.CashHistoryRepository;
import vn.chodientu.repository.CashRepository;
import vn.chodientu.repository.CashTransactionRepository;
import vn.chodientu.repository.OrderRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;

/**
 * @since May 20, 2014
 * @author Phu
 */
@Service
public class CashService {
    
    @Autowired
    private CashRepository cashRepository;
    @Autowired
    private CashTransactionRepository cashTransactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NlClient nlClient;
    @Autowired
    private Viewer viewer;
    @Autowired
    private SellerHistoryService sellerHistoryService;
    @Autowired
    private CashHistoryRepository cashHistoryRepository;
    @Autowired
    private OrderRepository orderRepository;

    /**
     * Tạo mới và thanh toán nạp xèng qua ngân lượng
     *
     * @param cashTransaction
     * @param returnUrl
     * @param cancelUrl
     * @return
     * @throws Exception
     */
    public CashTransaction createTopupNL(CashTransaction cashTransaction, String returnUrl, String cancelUrl) throws Exception {
        User user = userRepository.find(cashTransaction.getUserId());
        if (user == null) {
            throw new Exception("Người dùng không tồn tại");
        }
        if (cashTransaction.getId() == null || cashTransaction.getId().equals("")) {
            cashTransaction.setId(cashTransactionRepository.genId());
            cashTransaction.setTime(System.currentTimeMillis());
        } else {
            cashTransaction = cashTransactionRepository.find(cashTransaction.getId());
        }
        if (cashTransaction.getAmount() <= 0 || cashTransaction.getSpentQuantity() <= 0) {
            throw new Exception("Gói xèng không hợp lệ");
        }
        
        List<NlClient.OrderItem> items = new ArrayList<>();
        long amount = cashTransaction.getAmount() / cashTransaction.getSpentQuantity();
        items.add(nlClient.new OrderItem("", "Thanh toán gói xèng tại hệ thống chodientu.vn", cashTransaction.getSpentQuantity(), amount));
        NlClient.MakePaymentRequest request = nlClient.new MakePaymentRequest();
        request.setOrderId(cashTransaction.getId());
        request.setOrderDesctiption("Mua " + cashTransaction.getSpentQuantity() + " gói xèng " + amount + " tại hệ thống chodientu.vn");
        request.setPaymentMethod(cashTransaction.getPaymentMethod().toString());
        request.setReturnUrl(returnUrl + cashTransaction.getId());
        request.setCancelUrl(cancelUrl);
        request.setReceiverEmail("sales@chodientu.vn");
        request.setTotalAmount(cashTransaction.getAmount());
        request.setDiscountAmount(cashTransaction.getDiscount());
        request.setShippingFee(0);
        request.setBuyerName(user.getName());
        request.setBuyerEmail(user.getEmail());
        request.setBuyerAddress(user.getAddress());
        request.setBuyerPhone(user.getPhone());
        request.setItems(items);
        
        NlClient.MakePaymentResponse makePayment = nlClient.makePayment(request);
        
        cashTransaction.setNlStatus(2);
        cashTransaction.setNlCheckoutUrl(makePayment.getCheckoutUrl());
        cashTransaction.setNlToken(makePayment.getToken());
        cashTransactionRepository.save(cashTransaction);
        
        return cashTransaction;
    }

    /**
     * Lấy ra cash cho user
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public Cash getCash(String userId) throws Exception {
        if (!userRepository.exists(userId)) {
            throw new Exception("Người dùng không tồn tại");
        }
        Cash cash = cashRepository.getCash(userId);
        return cash;
    }

    /**
     * Lấy danh sách giao dịch đã thực hiện
     *
     * @param transactionSearch
     * @return
     */
    public DataPage<CashTransaction> search(CashTransactionSearch transactionSearch) {
        Criteria buildCriteria = this.buildCriteria(transactionSearch);
        DataPage<CashTransaction> transactionPage = new DataPage<>();
        transactionPage.setDataCount(cashTransactionRepository.count(new Query(buildCriteria)));
        transactionPage.setPageIndex(transactionSearch.getPageIndex());
        transactionPage.setPageSize(transactionSearch.getPageSize());
        transactionPage.setPageCount(transactionPage.getDataCount() / transactionSearch.getPageSize());
        if (transactionPage.getDataCount() % transactionSearch.getPageSize() != 0) {
            transactionPage.setPageCount(transactionPage.getPageCount() + 1);
        }
        List<CashTransaction> list = cashTransactionRepository.list(buildCriteria, transactionSearch.getPageSize(), transactionSearch.getPageIndex());
        transactionPage.setData(list);
        return transactionPage;
    }
    
    public Criteria buildCriteria(CashTransactionSearch transactionSearch) {
        Criteria cri = new Criteria();
        if (transactionSearch.getId() != null && !transactionSearch.getId().equals("")) {
            cri.and("id").is(transactionSearch.getId());
        }
        if (transactionSearch.getUserId() != null && !transactionSearch.getUserId().equals("")) {
            cri.and("userId").is(transactionSearch.getUserId());
        }
        if (transactionSearch.getSupport() != null && !transactionSearch.getSupport().equals("")) {
            cri.and("support").is(transactionSearch.getSupport());
        }
        if (transactionSearch.getTransactionStatus() > 0) {
            if (transactionSearch.getTransactionStatus() == 1) {
                Criteria cri1 = new Criteria("type").is(CashTransactionType.TOPUP_NL.toString());
                Criteria cri2 = new Criteria("type").is(CashTransactionType.SMS_NAP.toString());
                cri.orOperator(cri1, cri2);
            } else if (transactionSearch.getTransactionStatus() == 2) {
                Criteria cri1 = new Criteria("type").is(CashTransactionType.SPENT_UPITEM.toString());
                Criteria cri2 = new Criteria("type").is(CashTransactionType.SPENT_VIPITEM.toString());
                Criteria cri3 = new Criteria("type").is(CashTransactionType.SPENT_EMAIL.toString());
                Criteria cri4 = new Criteria("type").is(CashTransactionType.SPENT_SMS.toString());
                Criteria cri5 = new Criteria("type").is(CashTransactionType.ACTIVE_MARKETING.toString());
                Criteria cri6 = new Criteria("type").is(CashTransactionType.ACTIVE_QUICK_SUBMIT.toString());
                Criteria cri7 = new Criteria("type").is(CashTransactionType.CLOSE_ADV.toString());
                cri.orOperator(cri1, cri2, cri3, cri4, cri5, cri6, cri7);
            } else {
                Criteria cri1 = new Criteria("type").is(CashTransactionType.COMMENT_MODEL_REWARD.toString());
                Criteria cri2 = new Criteria("type").is(CashTransactionType.COMMENT_ITEM_REWARD.toString());
                Criteria cri3 = new Criteria("type").is(CashTransactionType.SELLER_POST_NEWS.toString());
                Criteria cri4 = new Criteria("type").is(CashTransactionType.VIEW_PAGE.toString());
                Criteria cri5 = new Criteria("type").is(CashTransactionType.SIGNIN.toString());
                Criteria cri6 = new Criteria("type").is(CashTransactionType.REGISTER.toString());
                Criteria cri7 = new Criteria("type").is(CashTransactionType.PAYMENT_SUSSESS_NL.toString());
                Criteria cri8 = new Criteria("type").is(CashTransactionType.PAYMENT_SUSSESS_COD.toString());
                Criteria cri9 = new Criteria("type").is(CashTransactionType.INTEGRATED_NL.toString());
                Criteria cri10 = new Criteria("type").is(CashTransactionType.INTEGRATED_COD.toString());
                Criteria cri11 = new Criteria("type").is(CashTransactionType.SELLER_POST_ITEM.toString());
                Criteria cri12 = new Criteria("type").is(CashTransactionType.OPEN_SHOP.toString());
                Criteria cri13 = new Criteria("type").is(CashTransactionType.SELLER_CREATE_PROMOTION.toString());
                Criteria cri14 = new Criteria("type").is(CashTransactionType.BROWSE_LADING.toString());
                Criteria cri15 = new Criteria("type").is(CashTransactionType.EMAIL_VERIFIED.toString());
                Criteria cri16 = new Criteria("type").is(CashTransactionType.PHONE_VERIFIED.toString());
                cri.orOperator(cri1, cri2, cri3, cri4, cri5, cri6, cri7, cri8, cri9, cri10, cri11, cri12, cri13, cri14, cri15, cri16);
            }
        } else {
            if (transactionSearch.getType() != null) {
                cri.and("type").is(transactionSearch.getType().toString());
            }
        }
        
        if (transactionSearch.getStatus() == 1) {
            cri.and("nlStatus").ne(2);
        }
        if (transactionSearch.getStatus() == 2) {
            cri.and("nlStatus").is(2);
        }
        if (transactionSearch.getEmail() != null && !transactionSearch.getEmail().equals("")) {
            List<User> find = userRepository.find(new Query(new Criteria("email").regex(transactionSearch.getEmail(), "i")));
            List<String> ids = new ArrayList<>();
            for (User userId : find) {
                ids.add(userId.getId());
            }
            cri.and("userId").in(ids);
            
        }
        
        if (transactionSearch.getStartTime() > 0 && transactionSearch.getEndTime() > 0) {
            cri.and("time").gte(transactionSearch.getStartTime()).lte(transactionSearch.getEndTime());
        } else if (transactionSearch.getStartTime() > 0) {
            cri.and("time").gte(transactionSearch.getStartTime());
        } else if (transactionSearch.getEndTime() > 0) {
            cri.and("time").lte(transactionSearch.getEndTime());
        }
        return cri;
    }

    /**
     * Lấy ra số tiền đã sử dụng và đã nạp của người dùng
     *
     * @param transactionSearch
     * @return
     */
    public Map<String, Double> totalMoneyTransaction(CashTransactionSearch transactionSearch) {
        Criteria cri = new Criteria("userId").is(transactionSearch.getUserId()).and("nlStatus").ne(2);
        Map<String, Double> map = new HashMap<>();
        map.put("spentTotal", (double) cashTransactionRepository.userAmountSumary(new Criteria().andOperator(new Criteria("type").is(2), cri)));
        map.put("topupTotal", (double) cashTransactionRepository.userAmountSumary(new Criteria().andOperator(new Criteria("type").is(1), cri)));
        return map;
    }

    /**
     * Kiểm tra tính đúng đắn của giao dịch Ngân Lượng trả về,Cộng tiền vào tài
     * khoản nếu thành công
     *
     * @param transId
     * @param nlId
     * @return
     * @throws Exception
     */
    public CashTransaction checkNLDataReturn(String transId, String nlId) throws Exception {
        CashTransaction cashTransaction = cashTransactionRepository.find(transId);
        if (cashTransaction == null) {
            throw new Exception("Giao dịch không tồn tại");
        }
        NlClient.CheckPaymentResponse checkPayment;
        if (nlId == null || nlId.equals("")) {
            checkPayment = nlClient.checkPayment(cashTransaction.getNlToken());
        } else {
            checkPayment = nlClient.new CheckPaymentResponse();
            checkPayment.setTotalAmount(cashTransaction.getAmount());
            checkPayment.setStatus(1);
            checkPayment.setTransactionId(nlId);
        }
        if (checkPayment.getTotalAmount() != cashTransaction.getAmount()
                || checkPayment.getDiscountAmount() != 0 || checkPayment.getShippingFee() != 0) {
            throw new Exception("Số tiền trong giao dịch không khớp");
        }
        if (checkPayment.getStatus() != 1 && checkPayment.getStatus() != 0) {
            throw new Exception("Giao dịch chưa được thanh toán");
        }
        
        cashTransaction.setNlPayTime(System.currentTimeMillis());
        cashTransaction.setNlStatus(checkPayment.getStatus());
        cashTransaction.setNlTransactionId(checkPayment.getTransactionId());
        
        Cash topupPaymentDone = cashRepository.topupPaymentDone(cashTransaction.getUserId(), cashTransaction.getAmount());
        
        cashTransaction.setNewBalance(topupPaymentDone.getBalance());
        cashTransactionRepository.save(cashTransaction);
        sellerHistoryService.create(SellerHistoryType.XENG, cashTransaction.getId(), true, 0, null);
        return cashTransaction;
    }

    /**
     * Tạo mới và thành toán xèng phần uptin
     *
     * @param cashTransaction
     * @return
     * @throws Exception
     */
    public Response createSpentUpItem(CashTransaction cashTransaction) throws Exception {
        Cash cash = getCash(cashTransaction.getUserId());
        if (cash.getBalance() <= 0) {
            throw new Exception("Tài khoản xèng không đủ để thanh toán cho giao dịch này");
        }
        if (cashTransaction.getId() == null || cashTransaction.getId().equals("")) {
            cashTransaction.setId(cashTransactionRepository.genId());
            cashTransaction.setTime(System.currentTimeMillis());
        }
        cashTransaction.setType(CashTransactionType.SPENT_UPITEM);
        cashTransaction.setSpentId(cashTransaction.getSpentId());
        long monney = cashTransaction.getAmount() * cashTransaction.getSpentQuantity() * -1;
        if (cash.getBalance() + monney < 0) {
            throw new Exception("Tài khoản xèng không đủ để thanh toán cho giao dịch này");
        }
        Cash topupPaymentDone = cashRepository.topupPaymentDone(cashTransaction.getUserId(), monney);
        cashTransaction.setNewBalance(topupPaymentDone.getBalance());
        cashTransactionRepository.save(cashTransaction);
        return new Response(true, "Trừ xèng thành công phần uptin");
        
    }

    /**
     * Nạp xèng bằng tin nhắn
     *
     * @param userId
     * @param monney
     * @return
     */
    public Response createSmsInbox(String userId, long monney) {
        CashTransaction cashTransaction = new CashTransaction();
        cashTransaction.setId(cashTransactionRepository.genId());
        cashTransaction.setAmount(monney);
        cashTransaction.setUserId(userId);
        cashTransaction.setTime(System.currentTimeMillis());
        cashTransaction.setNlPayTime(System.currentTimeMillis());
        cashTransaction.setType(CashTransactionType.SMS_NAP);
        cashTransaction.setSpentQuantity(1);
        Cash topupPaymentDone = cashRepository.topupPaymentDone(userId, monney);
        cashTransaction.setNewBalance(topupPaymentDone.getBalance());
        cashTransactionRepository.save(cashTransaction);
        return new Response(true, "Nạp tiền cho tài khoản bằng tin nhắn thành công");
        
    }

    /**
     * Tạo mới và thành toán xèng phần uptin
     *
     * @param cashTransaction
     * @return
     * @throws Exception
     */
    public Response createSpentVipItem(CashTransaction cashTransaction) throws Exception {
        if (!userRepository.exists(cashTransaction.getUserId())) {
            throw new Exception("Người dùng không tồn tại");
        }
        if (cashTransaction.getId() == null || cashTransaction.getId().equals("")) {
            cashTransaction.setId(cashTransactionRepository.genId());
            cashTransaction.setTime(System.currentTimeMillis());
        }
        cashTransaction.setType(CashTransactionType.SPENT_VIPITEM);
        long monney = cashTransaction.getAmount() * cashTransaction.getSpentQuantity() * -1;
        Cash topupPaymentDone = cashRepository.topupPaymentDone(cashTransaction.getUserId(), monney);
        cashTransaction.setNewBalance(topupPaymentDone.getBalance());
        cashTransactionRepository.save(cashTransaction);
        return new Response(true, "Trừ xèng thành công phần uptin");
    }

    /**
     * Cộng xèng thưởng cho user comment
     *
     * @param type
     * @param userId
     * @param objectId
     * @param url
     * @return
     * @throws Exception
     */
    public Response reward(CashTransactionType type, String userId, String objectId, String url, String message) throws Exception {
        //Tạo lịch sử giao dịch xèng
        CashTransaction cashTransaction = new CashTransaction();
        cashTransaction.setId(cashTransactionRepository.genId());
        cashTransaction.setTime(System.currentTimeMillis());
        cashTransaction.setType(type);
        cashTransaction.setUserId(userId);
        cashTransaction.setSpentQuantity(1);
        //Save histoty
        CashHistory history = new CashHistory();
        history.setId(cashHistoryRepository.genId());
        history.setCreateTime(System.currentTimeMillis());
        history.setCashTransactionId(cashTransaction.getId());
        history.setObjectId(objectId);
        history.setType(type);
        history.setUrl(url);
        history.setUserId(userId);
        history.setMessage(message);
        
        long blance = 0, maxQuantity = 0;
        long turn = cashHistoryRepository.totalCash(TextUtils.getTime(System.currentTimeMillis(), false),
                TextUtils.getTime(System.currentTimeMillis(), true), userId, type, 0);
        
        if (type == CashTransactionType.COMMENT_MODEL_REWARD || type == CashTransactionType.COMMENT_ITEM_REWARD) {
            blance = 200;
            maxQuantity = 5;
        } else if (type == CashTransactionType.SELLER_POST_NEWS) {
            blance = 50;
            maxQuantity = 3;
        } else if (type == CashTransactionType.SIGNIN) {
            blance = 100;
            maxQuantity = 1;
        } else if (type == CashTransactionType.REGISTER) {
            blance = 2000;
            maxQuantity = 1;
        } else if (type == CashTransactionType.PAYMENT_SUSSESS_COD || type == CashTransactionType.PAYMENT_SUSSESS_NL) {
            Order order = orderRepository.find(objectId);
            if (order == null || order.getPaymentStatus() != PaymentStatus.PAID || order.getPaymentMethod() == PaymentMethod.NONE) {
                throw new Exception("Đơn hàng " + objectId + " không đủ điều kiện nhận xèng");
            }
            blance = (long) Math.ceil(order.getFinalPrice() * 0.01);
            maxQuantity = -1;
        } else if (type == CashTransactionType.INTEGRATED_COD || type == CashTransactionType.INTEGRATED_NL) {
            long q = cashHistoryRepository.totalCash(-1, -1, userId, type, 0);
            if (q > 1) {
                throw new Exception("Liên kết ngân lượng hoặc ship chung chỉ tính phí lần đầu tiên");
            }
            blance = 2000;
            maxQuantity = 1;
        } else if (type == CashTransactionType.SELLER_POST_ITEM) {
            blance = 100;
            maxQuantity = 5;
        } else if (type == CashTransactionType.OPEN_SHOP) {
            long q = cashHistoryRepository.totalCash(-1, -1, userId, type, 0);
            if (q > 1) {
                throw new Exception("Mở shop chỉ tính phí lần đầu tiên");
            }
            blance = 2000;
            maxQuantity = -1;
        } else if (type == CashTransactionType.SELLER_CREATE_PROMOTION) {
            blance = 1000;
            maxQuantity = 1;
        } else if (type == CashTransactionType.BROWSE_LADING) {
            blance = 100;
            maxQuantity = 20;
        } else if (type == CashTransactionType.EMAIL_VERIFIED || type == CashTransactionType.PHONE_VERIFIED) {
            blance = 1000;
            maxQuantity = -1;
        } else {
            throw new Exception("Không tìm thấy thông tin trên hệ thống");
        }
        
        if (maxQuantity >= 0 && turn >= maxQuantity) {
            throw new Exception("Số lượng miễn phí đã quá giới hạn");
        }

        //Cộng xèng
        cashTransaction.setAmount(blance);
        long monney = cashTransaction.getAmount() * cashTransaction.getSpentQuantity();
        Cash topupPaymentDone = cashRepository.topupPaymentDone(cashTransaction.getUserId(), monney);
        cashTransaction.setNewBalance(topupPaymentDone.getBalance());
        
        history.setBalance(blance);
        history.setTurn(turn + 1);
        history.setFineBalance((long) Math.ceil(blance * 1.5));
        
        cashTransactionRepository.save(cashTransaction);
        cashHistoryRepository.save(history);
        return new Response(true, "Bạn vừa được cộng " + blance + " xèng vào tài khoản");
    }
    
    public List<Cash> list() {
        return cashRepository.listAll();
    }

    /**
     * Lấy danh sách giao dịch đã thực hiện
     *
     * @param cashSearch
     * @return
     */
    public DataPage<Cash> searchCash(CashSearch cashSearch) {
        Criteria cri = new Criteria();
        if (cashSearch.getUserId() != null && !cashSearch.getUserId().equals("")) {
            cri.and("userId").is(cashSearch.getUserId());
        }
        if (cashSearch.getEmail() != null && !cashSearch.getEmail().equals("")) {
            List<User> find = userRepository.find(new Query(new Criteria("email").regex(cashSearch.getEmail(), "i")));
            List<String> ids = new ArrayList<>();
            for (User userId : find) {
                ids.add(userId.getId());
            }
            cri.and("userId").in(ids);
            
        }
        DataPage<Cash> dataPage = new DataPage<>();
        dataPage.setDataCount(cashRepository.count(new Query(cri)));
        dataPage.setPageIndex(cashSearch.getPageIndex());
        dataPage.setPageSize(cashSearch.getPageSize());
        dataPage.setPageCount(dataPage.getDataCount() / cashSearch.getPageSize());
        if (dataPage.getDataCount() % cashSearch.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        List<Cash> list = cashRepository.list(cri, cashSearch.getPageSize(), cashSearch.getPageIndex());
        dataPage.setData(list);
        return dataPage;
    }

    /**
     * Thanh toán xèng khi kích hoạt chức năng danh sách khách hàng
     *
     * @param cashTransaction
     * @param monney
     * @return
     * @throws Exception
     */
    public Response createActiveMarketing(CashTransaction cashTransaction, long monney) throws Exception {
        if (!userRepository.exists(cashTransaction.getUserId())) {
            throw new Exception("Người dùng không tồn tại");
        }
        Cash cash = getCash(cashTransaction.getUserId());
        if (cash.getBalance() <= 0 || cash.getBalance() < monney) {
            throw new Exception("Tài khoản xèng không đủ để thanh toán cho giao dịch này");
        }
        if (cashTransaction.getId() == null || cashTransaction.getId().equals("")) {
            cashTransaction.setId(cashTransactionRepository.genId());
            cashTransaction.setTime(System.currentTimeMillis());
        }
        cashTransaction.setType(CashTransactionType.ACTIVE_MARKETING);
        cashTransaction.setSpentId(cashTransaction.getSpentId());
        cashTransaction.setSpentQuantity(1);
        cashTransaction.setAmount(monney);
        Cash topupPaymentDone = cashRepository.topupPaymentDone(cashTransaction.getUserId(), monney * (-1));
        cashTransaction.setNewBalance(topupPaymentDone.getBalance());
        cashTransactionRepository.save(cashTransaction);
        return new Response(true, "Chức năng danh sách người bán đã được mở");
    }
    
    public Response createMarketingPayment(CashTransaction cashTransaction, long monney, CashTransactionType type) throws Exception {
        if (!userRepository.exists(cashTransaction.getUserId())) {
            throw new Exception("Người dùng không tồn tại");
        }
        Cash cash = getCash(cashTransaction.getUserId());
        cashTransaction.setType(type);
        cashTransaction.setSpentId(cashTransaction.getSpentId());
        cashTransaction.setAmount(monney);
        if (cash.getBalance() <= 0 || cash.getBalance() < cashTransaction.getAmount() * cashTransaction.getSpentQuantity()) {
            throw new Exception("Tài khoản xèng không đủ để thanh toán cho giao dịch này");
        }
        if (cashTransaction.getId() == null || cashTransaction.getId().equals("")) {
            cashTransaction.setId(cashTransactionRepository.genId());
            cashTransaction.setTime(System.currentTimeMillis());
        }
        Cash topupPaymentDone = cashRepository.topupPaymentDone(cashTransaction.getUserId(), cashTransaction.getAmount() * cashTransaction.getSpentQuantity() * (-1));
        cashTransaction.setNewBalance(topupPaymentDone.getBalance());
        cashTransactionRepository.save(cashTransaction);
        return new Response(true, "Bạn đã thanh thanh toán thành công");
    }
    
    @Async
    public void migrate(List<Cash> cashs) {
        for (Cash c : cashs) {
            if (userRepository.exists(c.getUserId())) {
                cashRepository.save(c);
            }
        }
    }
    
    Response createActiveQuickSubmit(CashTransaction cashTransaction, long price) throws Exception {
        if (!userRepository.exists(cashTransaction.getUserId())) {
            throw new Exception("Người dùng không tồn tại");
        }
        Cash cash = getCash(cashTransaction.getUserId());
        if (cash.getBalance() <= 0 || cash.getBalance() < price) {
            throw new Exception("Tài khoản xèng không đủ để thanh toán cho giao dịch này");
        }
        if (cashTransaction.getId() == null || cashTransaction.getId().equals("")) {
            cashTransaction.setId(cashTransactionRepository.genId());
            cashTransaction.setTime(System.currentTimeMillis());
        }
        cashTransaction.setType(CashTransactionType.ACTIVE_QUICK_SUBMIT);
        cashTransaction.setSpentId(cashTransaction.getSpentId());
        cashTransaction.setSpentQuantity(1);
        cashTransaction.setAmount(price);
        Cash topupPaymentDone = cashRepository.topupPaymentDone(cashTransaction.getUserId(), price * (-1));
        cashTransaction.setNewBalance(topupPaymentDone.getBalance());
        cashTransactionRepository.save(cashTransaction);
        return new Response(true, "Chức năng danh sách người bán đã được mở");
    }

    /**
     * Lấy danh sách giao dịch đã thực hiện
     *
     * @param transactionSearch
     * @return
     */
    public Map<String, Long> getSellerSumBlance(CashTransactionSearch transactionSearch) {
        Criteria buildCriteria = this.buildCriteria(transactionSearch);
        return cashTransactionRepository.sellerSumBlance(buildCriteria);
    }
    
    public CashTransaction getTransaction(String id) {
        return cashTransactionRepository.find(id);
    }
    
    public CashTransaction addSupport(String id) throws Exception {
        if (viewer.getAdministrator() == null) {
            throw new Exception("Bạn chưa đăng nhập");
        }
        CashTransaction cashTransaction = cashTransactionRepository.find(id);
        if (cashTransaction == null) {
            throw new Exception("Không tồn tại giao dịch này");
        }
        if (cashTransaction.getSupport() != null && !cashTransaction.getSupport().equals("")) {
            throw new Exception("Giao dịch đang được " + cashTransaction.getSupport() + " chăm sóc");
        }
        cashTransaction.setSupport(viewer.getAdministrator().getEmail());
        cashTransaction.setUpdateTime(new Date().getTime());
        cashTransactionRepository.save(cashTransaction);
        return cashTransaction;
    }

    /**
     * *
     * Cập nhật chú ý vào giao dịch xèng
     *
     * @param id
     * @param note
     * @return
     * @throws Exception
     */
    public CashTransaction addNote(String id, String note) throws Exception {
        if (viewer.getAdministrator() == null) {
            throw new Exception("Bạn chưa đăng nhập");
        }
        CashTransaction cashTransaction = cashTransactionRepository.find(id);
        if (cashTransaction == null) {
            throw new Exception("Không tồn tại giao dịch này");
        }
        if (!viewer.getAdministrator().getEmail().equals(cashTransaction.getSupport())) {
            throw new Exception("Giao dịch đang được " + cashTransaction.getSupport() + " chăm sóc");
        }
        cashTransaction.setNote(note);
        cashTransaction.setUpdateTime(new Date().getTime());
        cashTransactionRepository.save(cashTransaction);
        return cashTransaction;
    }

 
    
    public long sumCashTransation(CashTransactionSearch cashTransactionSearch) {
        Criteria buildCriteria = this.buildCriteria(cashTransactionSearch);
        return cashTransactionRepository.sumForReport(buildCriteria);
    }
    
}
