package vn.chodientu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.chodientu.component.EmailClient;
import vn.chodientu.entity.data.SellerPolicy;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.input.SellerSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.SellerRepository;
import vn.chodientu.repository.UserRepository;

@Service
public class SellerService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private CashService cashService;
    private final long active_quick_submit_item_price = 100000;
    @Autowired
    private ItemService itemService;
    @Autowired
    private EmailClient emailClient;

    public void migrate(List<Seller> sellers) {
        for (Seller s : sellers) {
            if (userRepository.exists(s.getUserId())) {
                sellerRepository.save(s);
            }
        }
    }

    /**
     * Lấy thông tin người bán theo ID
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    public Seller getById(String id) {
        Seller seller = sellerRepository.find(id);
        if (seller == null) {
            seller = new Seller();
            seller.setUserId(id);
            seller.setNlIntegrated(false);
            seller.setScIntegrated(false);
            sellerRepository.save(seller);
        }
        return sellerRepository.getById(id);
    }

    /**
     * Lấy thông tin người bán theo ID
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    public Seller getSellerNLS(String id) throws Exception {
        return sellerRepository.getByNLSC(id);
    }

    /**
     * Thêm mới phí vận chuyển
     *
     * @param seller
     * @return
     * @throws java.lang.Exception
     */
    public Response addShippingFee(Seller seller) throws Exception {
        Seller userId = sellerRepository.getById(seller.getUserId());
        if (userId == null) {
            throw new Exception("Người bán không tồn tại");
        }
        userId.setShipmentPrice(seller.getShipmentPrice());
        userId.setShipmentType(seller.getShipmentType());
        sellerRepository.save(userId);
        return new Response(true, "Đã thêm thành công", userId);
    }

    /**
     * Sửa phí vận chuyển
     *
     * @param seller
     * @return
     */
    public Response update(Seller seller) {
        Seller userId = sellerRepository.getById(seller.getUserId());
        if (userId == null) {
            return new Response(false, "Không tồn tại người bán");
        }
        sellerRepository.save(seller);
        return new Response(true, "Đã thêm thành công", userId);
    }

    /**
     * Khi người dùng chưa là người bán thì sẽ tạo mới 1 người bán với thông tin
     * cơ bản
     *
     * @param user
     * @return
     */
    public Seller createSeller(User user) {
        Seller seller = sellerRepository.find(user.getId());
        if (seller == null) {
            seller = new Seller();
            seller.setUserId(user.getId());
            seller.setNlIntegrated(false);
            seller.setScIntegrated(false);
            sellerRepository.save(seller);
        }
        return seller;
    }

    /**
     * Lưu thông tin chính sách bán hàng cho người bán
     *
     * @param userId
     * @param sellerPolicy
     * @return
     */
    public Response saveSellerPolicy(String userId, List<SellerPolicy> sellerPolicy) {
        Seller seller = sellerRepository.find(userId);
        if (seller == null) {
            seller = new Seller();
            seller.setUserId(userId);
            seller.setNlIntegrated(false);
            seller.setScIntegrated(false);
        }
        seller.setSalesPolicy(sellerPolicy);
        sellerRepository.save(seller);
        return new Response(true, "Cấu hình chính sách bán hàng thành công!", seller);
    }

    /**
     * kích hoạt đăng bán nhanh
     *
     * @param user
     * @return
     */
    public Response activeItemSubmitQuick(User user) {
        try {
            Seller seller = sellerRepository.find(user.getId());
            if (seller == null) {
                seller = new Seller();
            }
            if (seller.isQuickSubmitItem()) {
                return new Response(false, "Tài khoản đã được kích hoạt chức năng đăng bán nhanh");
            }
            CashTransaction cashTransaction = new CashTransaction();
            cashTransaction.setUserId(user.getId());
            cashTransaction.setSpentId("QuickSubmitItem");
            Response resp = cashService.createActiveQuickSubmit(cashTransaction, active_quick_submit_item_price);
            if (resp.isSuccess()) {
                seller.setQuickSubmitItem(true);
                sellerRepository.save(seller);
            }
            return resp;
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @Async
    public void processUpdateOnlinePaymentItem(String userId, boolean onlinePayment) throws Exception {
        long total = itemService.countBySeller(userId);
        int totalPage = (int) total / 100;
        if (total % 100 != 0) {
            totalPage++;
        }
        for (int i = 0; i <= totalPage; i++) {
            List<Item> items = itemService.getBySeller(new PageRequest(i, 100), userId);
            if (items != null && items.size() > 0) {
                for (Item item : items) {
                    item.setOnlinePayment(onlinePayment);
                    itemService.edit(item, null);
                }
            }
        }
    }

    @Async
    public void processUpdateCodPaymentItem(String userId, boolean cod) throws Exception {
        long total = itemService.countBySeller(userId);
        int totalPage = (int) total / 100;
        if (total % 100 != 0) {
            totalPage++;
        }
        for (int i = 0; i <= totalPage; i++) {
            List<Item> items = itemService.getBySeller(new PageRequest(i, 100), userId);
            if (items != null && items.size() > 0) {
                for (Item item : items) {
                    item.setCod(cod);
                    itemService.edit(item, null);
                }
            }
        }
    }

    public DataPage<Seller> search(SellerSearch search) {
        DataPage<Seller> dataPage = new DataPage<>();
        Criteria cri = new Criteria();
        if (search.getUserId() != null && !search.getUserId().trim().equals("")) {
            cri.and("userId").is(search.getUserId());
        }
        if (search.getNlIntegrated() > 0) {
            if (search.getNlIntegrated() == 1) {
                cri.and("nlIntegrated").is(true);
            } else {
                cri.and("nlIntegrated").is(false);
            }
        }
        if (search.getScIntegrated() > 0) {
            if (search.getScIntegrated() == 1) {
                cri.and("scIntegrated").is(true);
            } else {
                cri.and("scIntegrated").is(false);
            }
        }
        if (search.getPushC()> 0) {
            if (search.getPushC() == 1) {
                cri.and("pushC").is(true);
            } else {
                cri.and("pushC").is(false);
            }
        }

        Query query = new Query(cri);
        dataPage.setDataCount(sellerRepository.count(query));
        dataPage.setPageIndex(search.getPageIndex());
        dataPage.setPageSize(search.getPageSize());
        dataPage.setPageCount(dataPage.getDataCount() / search.getPageSize());
        if (dataPage.getDataCount() % search.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        dataPage.setData(sellerRepository.find(query.limit(search.getPageSize()).skip(search.getPageIndex() * search.getPageSize())));
        return dataPage;
    }

    /**
     * Đóng quảng cáo trừ 30k xèng
     *
     * @param sellerId
     * @return
     * @throws java.lang.Exception
     */
    public Seller closeAdv(String sellerId) throws Exception {
        Seller seller = sellerRepository.getById(sellerId);
        if (seller == null) {
            throw new Exception("Không tìm thấy người bán yêu cầu");
        }
        if (seller.isCloseAdv()) {
            throw new Exception("Tài khoản của bạn đã được đóng quảng cáo");
        }
        CashTransaction cash = new CashTransaction();
        cash.setUserId(sellerId);
        cash.setSpentQuantity(1);
        cashService.createMarketingPayment(cash, 30000, CashTransactionType.CLOSE_ADV);
        seller.setCloseAdv(true);
        sellerRepository.save(seller);
        return seller;
    }

    /**
     * *
     * Lấy ra danh sách người bán
     *
     * @param ids
     * @return
     * @throws Exception
     */
    public List<Seller> get(List<String> ids) throws Exception {
        return sellerRepository.get(ids);
    }
}
