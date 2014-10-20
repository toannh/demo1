package vn.chodientu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.Bid;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.UserAuction;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.BidRepository;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.UserAuctionRepository;
import vn.chodientu.util.ExpiringMap;
import vn.chodientu.util.TextUtils;

/**
 *
 * @author Phu
 */
@Service
public class AuctionService {

    @Autowired
    private Viewer viewer;
    private final ExpiringMap<String, Object> locks;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private SearchIndexService searchIndexService;
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private UserAuctionRepository userAuctionRepository;

    public AuctionService() {
        locks = new ExpiringMap<>(60 * 60 * 1000);
    }

    /**
     * Đặt giá
     *
     * @param itemId
     * @param price
     * @param auto
     * @return
     * @throws Exception
     */
    public Response bid(String itemId, long price, boolean auto) throws Exception {
        if (viewer.getUser() == null) {
            throw new Exception("Bạn phải đăng nhập mới có thể tham gia đấu giá!");
        }
        Item item = itemRepository.find(itemId);
        if (item == null) {
            throw new Exception("Sản phẩm không tồn tại!");
        }
        if (viewer.getUser().getId().equals(item.getSellerId())) {
            throw new Exception("Bạn không được tham gia đấu giá sản phẩm của chính mình!");
        }
        if (item.getListingType() != ListingType.AUCTION) {
            throw new Exception("Đây không phải sản phẩm đấu giá!");
        }
        if (item.getEndTime() < System.currentTimeMillis()) {
            throw new Exception("Đấu giá đã hết hạn!");
        }
        Object lock = locks.get(itemId);
        if (lock == null) {
            lock = new Object();
            locks.put(itemId, lock);
        }

        synchronized (lock) {
            Bid highest = bidRepository.getHighest(itemId);
            if (highest == null) {
                highest = new Bid();
            }
            if (highest.getBid() >= price) {
                throw new Exception("Xin lỗi, trước đó đã có người đặt giá cao hơn bạn, hãy đặt giá tối thiểu: " + TextUtils.numberFormat(item.getBidStep() + highest.getBid() + 0d) + " đ");
            }
            if (highest.getBid() + item.getBidStep() > price) {
                throw new Exception("Xin lỗi, giá bạn đặt thấp hơn bước giá tối thiểu, hãy đặt giá tối thiểu: " + TextUtils.numberFormat(item.getBidStep() + highest.getBid() + 0d) + " đ");
            }
            if (highest.getBid() <= 0 && item.getStartPrice() + item.getBidStep() > price) {
                throw new Exception("Xin lỗi, giá bạn đặt thấp hơn giá khởi điểm, hãy đặt giá tối thiểu: " + TextUtils.numberFormat(item.getStartPrice() + item.getBidStep() + 0d) + " đ");
            }
            Bid highestAuto = bidRepository.getHighestAuto(itemId);
            if (highestAuto == null) {
                highestAuto = new Bid();
            }
            if (highestAuto.getBid() >= price) {
                Bid bid = new Bid();
                bid.setTime(System.currentTimeMillis());
                bid.setBiderId(viewer.getUser().getId());
                bid.setItemId(itemId);
                bid.setBid(price);
                bid.setAutoBiding(auto);
                bidRepository.save(bid);
                if (auto) {
                    bid.setId(null);
                    bid.setAuto(auto);
                    bid.setAutoBiding(false);
                    bidRepository.save(bid);
                }
                highestAuto.setId(null);
                highestAuto.setBid(price + item.getBidStep() > highestAuto.getBid() ? highestAuto.getBid() : price + item.getBidStep());
                highestAuto.setTime(System.currentTimeMillis());
                highestAuto.setAuto(false);
                highestAuto.setAutoBiding(true);
                bidRepository.save(highestAuto);
                item.setHighestBid(highestAuto.getBid());
                item.setHighestBider(highestAuto.getBiderId());
            } else {
                if (highestAuto.getBid() > highest.getBid()) {
                    highestAuto.setId(null);
                    highestAuto.setTime(System.currentTimeMillis());
                    highestAuto.setAuto(false);
                    highestAuto.setAutoBiding(true);
                    bidRepository.save(highestAuto);
                }

                Bid bid = new Bid();
                bid.setTime(System.currentTimeMillis());
                bid.setBiderId(viewer.getUser().getId());
                bid.setItemId(itemId);
                bid.setBid(price);
                bid.setAutoBiding(false);
                bid.setAuto(auto);
                bidRepository.save(bid);
                if (auto) {
                    bid.setId(null);
                    bid.setAuto(false);
                    bid.setAutoBiding(true);
                    bid.setBid((highestAuto.getBid() == 0 ? item.getStartPrice() : highestAuto.getBid()) + item.getBidStep());
                    bidRepository.save(bid);
                }
                item.setHighestBid(bid.getBid());
                item.setHighestBider(bid.getBiderId());
            }

            item.setBidCount(bidRepository.count(itemId));
            itemRepository.save(item);

            UserAuction itemAution = userAuctionRepository.getItemAution(viewer.getUser().getId(), itemId);
            if (itemAution == null) {
                itemAution = new UserAuction();
                itemAution.setItemId(itemId);
                itemAution.setUserId(viewer.getUser().getId());
                itemAution.setEndTime(item.getEndTime());
                itemAution.setBidTime(System.currentTimeMillis());
                itemAution.setItemName(item.getName());
            }
            itemAution.setPrice(price);
            userAuctionRepository.save(itemAution);
        }

        searchIndexService.processIndexItem(item);
        return new Response(true, "Đặt giá thành công!");
    }

    /**
     * Lấy lịch sử đặt giá của sản phẩm
     *
     * @param itemId
     * @return
     * @throws Exception
     */
    public List<Bid> bidHistory(String itemId) throws Exception {
        Item item = itemRepository.find(itemId);
        if (item == null) {
            throw new Exception("Sản phẩm không tồn tại!");
        }
        List<Bid> bidHistory = bidRepository.bidHistory(itemId);
        return bidHistory;
    }

    /**
     * Dừng sản phẩm đấu giá khi xuất hiện hóa đơn mua ngay thanh toán
     *
     * @param itemId
     * @return
     * @throws Exception
     */
    public void endBidByPayment(String itemId) throws Exception {
        Item item = itemRepository.find(itemId);
        if (item == null) {
            throw new Exception("Sản phẩm không tồn tại!");
        }
        if (item.getListingType() == ListingType.AUCTION) {
            item.setEndTime(System.currentTimeMillis());
            itemRepository.save(item);
            searchIndexService.processIndexItem(item);
        }

    }

}
