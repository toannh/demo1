package vn.chodientu.service;

import static java.lang.Long.parseLong;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemReview;
import vn.chodientu.entity.db.ItemReviewLike;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.input.ItemReviewSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.ItemReviewLikeRepository;
import vn.chodientu.repository.ItemReviewRepository;
import vn.chodientu.util.TextUtils;
import vn.chodientu.util.UrlUtils;

/**
 *
 * @author thanhvv
 */
@Service
public class ItemReviewService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CashService cashService;
    @Autowired
    private ItemReviewRepository itemReviewRepository;
    @Autowired
    private ItemReviewLikeRepository itemReviewLikeRepository;
    @Autowired
    private Viewer viewer;
    @Autowired
    private Validator validator;
    @Autowired
    private SearchIndexService searchIndexService;

    /**
     * Add bình luận item
     *
     * @param itemReview
     * @param request
     * @return
     * @throws java.lang.Exception
     */
    public Response add(ItemReview itemReview, HttpServletRequest request) throws Exception {
        Item item = itemRepository.find(itemReview.getItemId());
        if (item == null) {
            throw new Exception("Sản phẩm không tồn tại trên hệ thống ChoDienTu");
        }
        if (viewer.getUser() == null) {
            throw new Exception("Bạn cần <a href='javascript:;' onclick=\"auth.login('" + UrlUtils.item(item.getId(), item.getName()) + "')\" >đăng nhập</a> để thực hiện thao tác này ");
        }
        if (itemReviewRepository.exitesReviewByUser(viewer.getUser().getId(), itemReview.getItemId())) {
            throw new Exception("Bạn đã đánh giá cho sản phẩm " + item.getName() + " rồi");
        }
        Map<String, String> error = validator.validate(itemReview);
        if (!error.isEmpty()) {
            return new Response(false, "Dữ liệu chưa chính xác", error);
        }
        itemReview.setTitle(TextUtils.removeTags(itemReview.getTitle()));
        itemReview.setContent(TextUtils.removeTags(itemReview.getContent()));
        itemReview.setId(itemReviewRepository.genId());
        itemReview.setCreateTime(System.currentTimeMillis());
        itemReview.setUpdateTime(System.currentTimeMillis());
        itemReview.setIp(TextUtils.getClientIpAddr(request));
        itemReview.setUserId(viewer.getUser().getId());
        itemReview.setActive(true);
        itemReview.setSellerId(item.getSellerId());
        //cộng tiềng xèng cho 1 lần comment

        itemReviewRepository.save(itemReview);
        item.setReview(itemReviewRepository.totalCommentByItem(itemReview.getItemId()));
        itemRepository.save(item);

        try {
            cashService.reward(CashTransactionType.COMMENT_ITEM_REWARD, viewer.getUser().getId(), item.getId(), UrlUtils.item(item.getId(), item.getName()) + "#comment=" + itemReview.getId() + "", itemReview.getContent());
            return new Response(true, "Đánh giá cho sản phẩm thành công", itemReview);
        } catch (Exception e) {
            return new Response(false, "CASH_FAIL", itemReview);
        }

    }

    /**
     * Danh sách bình luận đánh giá theo điều kiện
     *
     * @param search
     * @return
     */
    public DataPage<ItemReview> search(ItemReviewSearch search) {

        Criteria cri = new Criteria();
        if (search.getActive() > 0) {
            cri.and("active").is(search.getActive() == 1);
        }
        /*
         if (search.getPageSize() < 1) {
         search.setPageSize(1);
         }*/
        if (search.getItemId() != null && !"".equals(search.getItemId())) {
            cri.and("itemId").is(search.getItemId());
        }
        if (search.getUserId() != null && !"".equals(search.getUserId())) {
            cri.and("userId").is(search.getUserId());
        }
        if (search.getSellerId() != null && !"".equals(search.getSellerId())) {
            cri.and("sellerId").is(search.getSellerId());
        }

        Sort sort;
        switch (search.getOrderBy()) {
            case 1:
                sort = new Sort(Sort.Direction.DESC, "like");
                break;
            case 2:
            default:
                sort = new Sort(Sort.Direction.DESC, "createTime");
                break;
        }

        Query query = new Query(cri);
        query.with(sort);
        query.skip(search.getPageIndex() * search.getPageSize()).limit(search.getPageSize());
        DataPage<ItemReview> page = new DataPage<>();
        page.setPageSize(search.getPageSize());
        page.setPageIndex(search.getPageIndex());
        if (page.getPageSize() <= 0) {
            page.setPageSize(2);
        }
        page.setDataCount(itemReviewRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }
        page.setData(itemReviewRepository.find(query));
        return page;
    }

    /**
     * Like action
     *
     * @param itemReviewId
     * @param itemId
     * @param request
     * @return
     * @throws Exception
     */
    public ItemReview like(String itemReviewId, String itemId, HttpServletRequest request) throws Exception {
        Item item = itemRepository.find(itemId);
        if (item == null) {
            throw new Exception("Sản phẩm không tồn tại trên hệ thống ChoDienTu");
        }
        if (viewer.getUser() == null) {
            throw new Exception("Bạn cần <a href='javascript:;' onclick=\"auth.login('" + UrlUtils.item(item.getId(), item.getName()) + "')\" >đăng nhập</a> để thực hiện thao tác này ");
        }
        ItemReview itemReview = itemReviewRepository.find(itemReviewId);
        if (itemReview == null) {
            throw new Exception("Không tìm thấy đánh giá yêu cầu");
        }
        if (itemReview.getItemId() == null || !itemReview.getItemId().equals(itemId)) {
            throw new Exception("Đánh giá bạn yêu cầu không thuộc sản phẩm này");
        }
        ItemReviewLike itemReviewLike = itemReviewLikeRepository.getByItemReviewIdAndUserId(itemReview.getId(), viewer.getUser().getId());
        String clientIp = TextUtils.getClientIpAddr(request);
        if (itemReviewLike == null) {
            itemReviewLike = new ItemReviewLike();
            itemReviewLike.setCommentId(itemReview.getId());
            itemReviewLike.setCreateTime(System.currentTimeMillis());
            itemReviewLike.setIp(clientIp);
            itemReviewLike.setLike(true);
            itemReviewLike.setUserId(viewer.getUser().getId());
            itemReview.setLike(itemReview.getLike() + 1);
        } else {
            if (itemReviewLike.isLike()) {
                itemReviewLike.setCreateTime(System.currentTimeMillis());
                itemReviewLike.setIp(clientIp);
                itemReviewLike.setLike(false);
                itemReview.setLike(itemReview.getLike() - 1);
            } else {
                itemReviewLike.setLike(true);
                itemReview.setLike(itemReview.getLike() + 1);
            }
        }
        itemReview.setUpdateTime(System.currentTimeMillis());
        itemReview.setLastLikeIp(clientIp);
        itemReviewLikeRepository.save(itemReviewLike);
        itemReviewRepository.save(itemReview);
        return itemReview;
    }

    /**
     * Thống kê điểm theo item
     *
     * @param itemId
     * @return
     */
    public HashMap<String, Long> info(String itemId) {
        HashMap<String, Long> data = new HashMap<>();
        data.put("comment", itemReviewRepository.totalCommentByItem(itemId));
        data.put("recommended", itemReviewRepository.totalRecommended(itemId));
        data.put("totalPoint", itemReviewRepository.sumPoint(itemId));
        try {
            data.put("point", data.get("totalPoint") / data.get("comment"));
        } catch (Exception e) {
            data.put("point", parseLong("0"));
        }
        data.put("one", itemReviewRepository.totalPoint(itemId, 1));
        data.put("two", itemReviewRepository.totalPoint(itemId, 2));
        data.put("three", itemReviewRepository.totalPoint(itemId, 3));
        data.put("four", itemReviewRepository.totalPoint(itemId, 4));
        data.put("five", itemReviewRepository.totalPoint(itemId, 5));
        return data;
    }

    /**
     * Exites comment
     *
     * @param itemId
     * @return
     */
    public boolean exitsComment(String itemId) {
        if (viewer.getUser() == null) {
            return false;
        }
        return itemReviewRepository.exitesReviewByUser(viewer.getUser().getId(), itemId);
    }

    /**
     * Active của người bán hoặc admin
     *
     * @param itemId
     * @param reviewId
     * @return
     * @throws Exception
     */
    public ItemReview active(String itemId, String reviewId) throws Exception {
        Item item = itemRepository.find(itemId);
        if (item == null) {
            throw new Exception("Sản phẩm không tồn tại trên hệ thống ChoDienTu");
        }
        if (viewer.getAdministrator() == null) {
            if (viewer.getUser() == null) {
                throw new Exception("Bạn cần <a href='javascript:;' onclick=\"auth.login('" + UrlUtils.item(item.getId(), item.getName()) + "')\" >đăng nhập</a> để thực hiện thao tác này ");
            }
            if (!viewer.getUser().getId().equals(item.getSellerId())) {
                throw new Exception("Bạn không có quyền thực hiện chức năng này");
            }
        }
        ItemReview itemReview = itemReviewRepository.find(reviewId);
        if (itemReview == null) {
            throw new Exception("Không tìm thấy đánh giá yêu cầu");
        }
        if (itemReview.getItemId() == null || !itemReview.getItemId().equals(itemId)) {
            throw new Exception("Đánh giá bạn yêu cầu không thuộc sản phẩm này");
        }
        itemReview.setActive(itemReview.isActive());
        itemReview.setUpdateTime(System.currentTimeMillis());
        itemReviewRepository.save(itemReview);
        item.setReview(itemReviewRepository.totalCommentByItem(item.getId()));
        itemRepository.save(item);
        return itemReview;
    }

    public ItemReview changeActive(String itemId, String reviewId) throws Exception {

        ItemReview itemReview = itemReviewRepository.find(reviewId);
        if (itemReview == null) {
            throw new Exception("Không tìm thấy đánh giá yêu cầu");
        }
        if (itemReview.getItemId() == null || !itemReview.getItemId().equals(itemId)) {
            throw new Exception("Đánh giá bạn yêu cầu không thuộc sản phẩm này");
        }
        itemReview.setActive(!itemReview.isActive());
        itemReview.setUpdateTime(System.currentTimeMillis());
        itemReviewRepository.save(itemReview);
        Item item = itemRepository.find(itemId);
        item.setReview(itemReviewRepository.totalCommentByItem(item.getId()));
        itemRepository.save(item);
        return itemReview;
    }

    public List<ItemReviewLike> listItemReviewLike(String reviewId) throws Exception {
        return itemReviewLikeRepository.getByItemReviewId(reviewId);
    }

    /**
     * Lấy số comment của item
     *
     * @param itemId
     * @return
     */
    public long countByItem(String itemId) {
        return itemReviewRepository.totalCommentByItem(itemId);
    }

}
