package vn.chodientu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.db.UserAuction;
import vn.chodientu.entity.enu.EmailOutboxType;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.enu.SmsOutboxType;
import vn.chodientu.entity.input.UserAuctionSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.UserAuctionRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;
import vn.chodientu.util.UrlUtils;

/**
 *
 * @author Phu
 */
@Service
public class UserAuctionService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserAuctionRepository userAuctionRepository;
    @Autowired
    private SmsService smsService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RealTimeService realTimeService;

    @Scheduled(fixedDelay = 15 * 60 * 1000)
    public void updateAuction() {

        while (true) {
            UserAuction userAuction = userAuctionRepository.getEndBid();
            if (userAuction == null) {
                break;
            }
            this.processEndBid(userAuction);
        }
    }

    @Async
    public void processEndBid(UserAuction userAuction) {
        Item item = itemRepository.find(userAuction.getItemId());
        if (item == null || item.getListingType() == ListingType.BUYNOW || item.getHighestBider() == null) {
            return;
        }
        //Send mail và sms
        if (userAuction.getUserId().equals(item.getHighestBider())) {
            userAuction.setSuccess(true);
            userAuctionRepository.save(userAuction);
            User buyer = userRepository.find(item.getHighestBider());
            Map<String, Object> data;
            if (buyer != null) {
                if (buyer.getPhone() != null) {
                    smsService.send(buyer.getPhone(), "Ban " + (buyer.getUsername() == null ? buyer.getEmail() : buyer.getUsername())
                            + " vua dau gia thang cuoc san pham " + TextUtils.removeDiacritical(item.getName()) + ". Ma sp: " + item.getId()
                            + " tren ChoDienTu.vn",
                            SmsOutboxType.AUCTION_BUYER, System.currentTimeMillis(), 2);
                }
                if (buyer.getEmail() != null && buyer.isEmailVerified()) {
                    try {
                        data = new HashMap<String, Object>();
                        data.put("username", (buyer.getUsername() == null ? buyer.getEmail() : buyer.getUsername()));
                        data.put("message", "Bạn vừa đấu giá thắng cuộc sản phẩm <a href='http://chodientu.vn" + UrlUtils.item(item.getId(), item.getName()) + "'>" + item.getName()
                                + "</a>. Mã sản phẩm: " + item.getId() + " trên ChoDienTu.vn"
                                + "<br/>Bạn có thể xem hướng dẫn đặt mua và thanh toán <a href='http://chodientu.vn/tin-tuc/huong-dan-dat-mua-va-thanh-toan-416060508412.html'>tại đây </a>");
                        emailService.send(EmailOutboxType.AUCTION_BUYER, buyer.getEmail(),
                                "[Chợ Điện Tử] Thông báo đấu giá thắng cuộc", "message", data);
                    } catch (Exception e) {
                    }
                }
            }

            User seller = userRepository.find(item.getSellerId());
            if (seller != null) {
                if (seller.getPhone() != null) {
                    smsService.send(seller.getPhone(), "Khach hang " + (buyer.getUsername() == null ? buyer.getEmail() : buyer.getUsername())
                            + " vua dau gia thang cuoc san pham " + TextUtils.removeDiacritical(item.getName()) + ". Ma sp: " + item.getId()
                            + " tren ChoDienTu.vn",
                            SmsOutboxType.AUCTION_SELLER, System.currentTimeMillis(), 2);
                }
                if (seller.getEmail() != null && seller.isEmailVerified()) {
                    try {
                        data = new HashMap<String, Object>();
                        data.put("username", (seller.getUsername() == null ? seller.getEmail() : seller.getUsername()));
                        data.put("message", "Khách hàng " + (buyer.getUsername() == null ? buyer.getEmail() : buyer.getUsername())
                                + " vừa đấu giá thắng cuộc sản phẩm <a href='http://chodientu.vn" + UrlUtils.item(item.getId(), item.getName()) + "'>" + item.getName()
                                + "</a>. Mã sản phẩm " + item.getId() + " trên ChoDienTu.vn"
                                + "<br/>Bạn có thể xem hướng dẫn đặt mua và thanh toán <a href='http://chodientu.vn/tin-tuc/huong-dan-dat-mua-va-thanh-toan-416060508412.html'>tại đây </a>");
                        emailService.send(EmailOutboxType.AUCTION_SELLER, seller.getEmail(),
                                "[Chợ Điện Tử] Thông báo đấu giá thắng cuộc", "message", data);
                    } catch (Exception e) {
                    }
                }
            }

            //đấu giá thắng cuộc
            realTimeService.add("Bạn đấu giá thắng sản phẩm 1 sản phẩm", userAuction.getUserId(), UrlUtils.item(item.getId(), item.getName()), "Thanh toán ngay", null);
            realTimeService.add("Một sản phẩm được đấu giá thắng", item.getSellerId(), UrlUtils.item(item.getId(), item.getName()), "Thanh toán ngay", null);
        } else {
            //đấu giá thật bại
            realTimeService.add("Đấu giá thất bại (Đã kết thúc) 1 sản phẩm", userAuction.getUserId(), UrlUtils.item(item.getId(), item.getName()), "Chi tiết sản phảm", null);
        }
    }

    public DataPage<UserAuction> search(UserAuctionSearch auctionSearch) {
        DataPage<UserAuction> dataPage = new DataPage<>();
        int orderBy = 0;
        Criteria cri = new Criteria();
        if (auctionSearch.getUserId() != null && !auctionSearch.getUserId().equals("")) {
            cri.and("userId").is(auctionSearch.getUserId());
        }
        if (auctionSearch.getId() != null && !auctionSearch.getId().equals("")) {
            cri.and("id").regex(auctionSearch.getId());
        }
        if (auctionSearch.getKeyword() != null && !auctionSearch.getKeyword().equals("")) {
            cri.and("itemName").regex(auctionSearch.getKeyword(), "i");
        }
        switch (auctionSearch.getTab()) {
            case "running":
                cri.and("delete").is(false);
                cri.and("complete").is(false);
                break;
            case "success":
                cri.and("success").is(true);
                cri.and("delete").is(false);
                cri.and("complete").is(true);
                orderBy = 1;
                break;
            case "unsuccess":
                cri.and("success").is(false);
                cri.and("delete").is(false);
                cri.and("complete").is(true);
                orderBy = 1;
                break;
            case "remove":
                cri.and("delete").is(true);
                break;
            default:
                cri.and("delete").is(false);
                orderBy = 2;
        }
        List<UserAuction> list = userAuctionRepository.getList(cri, new PageRequest(auctionSearch.getPageIndex(), auctionSearch.getPageSize()), orderBy);
        dataPage.setPageIndex(auctionSearch.getPageIndex());
        dataPage.setPageSize(auctionSearch.getPageSize());
        dataPage.setPageCount(dataPage.getPageCount());
        dataPage.setDataCount(userAuctionRepository.count(new Query(cri)));
        dataPage.setData(list);
        dataPage.setPageCount(dataPage.getDataCount() / dataPage.getPageSize());
        if (dataPage.getDataCount() % dataPage.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }

        return dataPage;
    }

    /**
     * Note sản phẩm đấu giá
     *
     * @param id
     * @param note
     * @return
     */
    public Response note(String id, String note) throws Exception {
        UserAuction find = userAuctionRepository.find(id);
        if (find == null) {
            throw new Exception("Sản phẩm này vẫn chưa được theo dõi");
        } else {
            find.setNote(note);
            userAuctionRepository.save(find);
            return new Response(true, "Thêm ghi chú thành công", find);
        }
    }

    public void delete(List<String> userItemIds) {
        List<UserAuction> list = userAuctionRepository.getList(userItemIds);
        for (UserAuction userAuction : list) {
            userAuction.setDelete(true);
            userAuctionRepository.save(userAuction);
        }

    }

    public long countByItem(String itemId) {
        return userAuctionRepository.count(itemId);
    }

    public Map<String, Long> countTab(UserAuctionSearch auctionSearch) {
        Map<String, Long> map = new HashMap<>();

        Criteria cri = new Criteria();
        if (auctionSearch.getUserId() != null && !auctionSearch.getUserId().equals("")) {
            cri.and("userId").is(auctionSearch.getUserId());
        }
        if (auctionSearch.getId() != null && !auctionSearch.getId().equals("")) {
            cri.and("id").regex(auctionSearch.getId());
        }
        if (auctionSearch.getKeyword() != null && !auctionSearch.getKeyword().equals("")) {
            cri.and("itemName").regex(auctionSearch.getKeyword(), "i");
        }

        map.put("running", userAuctionRepository.count(new Query(new Criteria().andOperator(cri, new Criteria("delete").is(false).and("complete").is(false)))));
        map.put("success", userAuctionRepository.count(new Query(new Criteria().andOperator(cri, new Criteria("success").is(true).and("delete").is(false).and("complete").is(true)))));
        map.put("unsuccess", userAuctionRepository.count(new Query(new Criteria().andOperator(cri, new Criteria("success").is(false).and("delete").is(false).and("complete").is(true)))));
        map.put("remove", userAuctionRepository.count(new Query(new Criteria().andOperator(cri, new Criteria("delete").is(true)))));
        map.put("all", userAuctionRepository.count(new Query(new Criteria().andOperator(cri, new Criteria("delete").is(false)))));

        return map;
    }
}
