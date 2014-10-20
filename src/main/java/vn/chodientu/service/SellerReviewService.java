package vn.chodientu.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.SellerReview;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.input.SellerReviewSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.OrderRepository;
import vn.chodientu.repository.SellerReviewRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;

@Service
public class SellerReviewService {

    @Autowired
    private SellerReviewRepository sellerReviewRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Viewer viewer;

    /**
     * Đánh giá ưu tín cho đơn hàng
     *
     * @param orderId
     * @param comment
     * @param point
     * @param request
     * @return
     * @throws Exception
     */
    public SellerReview review(String orderId, String comment, int point, HttpServletRequest request) throws Exception {
        Order order = orderRepository.find(orderId);
        if (order == null) {
            throw new Exception("Đơn hàng không tồn tại trên hệ thống ChoDienTu");
        }
        if (viewer.getUser() == null) {
            throw new Exception("Bạn cần đăng nhập để thực hiện thao tác này ");
        }
        if (!viewer.getUser().getId().equals(order.getSellerId()) && !viewer.getUser().getId().equals(order.getBuyerId())) {
            throw new Exception("Bạn không có quyền thực hiện chức năng này" + viewer.getUser().getId());
        }
        point = point < 0 ? 0 : point;
        point = point > 2 ? 2 : point;
        SellerReview review = sellerReviewRepository.find(orderId, viewer.getUser().getId());
        if (review == null) {
            review = new SellerReview();
            review.setCreateTime(System.currentTimeMillis());
            review.setOrderId(orderId);
            if (viewer.getUser().getId().equals(order.getSellerId())) {
                review.setUserId(order.getSellerId());
                review.setSellerId(order.getBuyerId());
            } else {
                review.setUserId(viewer.getUser().getId());
                review.setSellerId(order.getSellerId());
            }
        }
        review.setUpdateTime(System.currentTimeMillis());
        review.setPoint(point);
        review.setContent(comment);
        review.setActive(true);
        review.setIp(TextUtils.getClientIpAddr(request));
        sellerReviewRepository.save(review);
        return review;
    }

    /**
     * Đánh giá uy tín khi tạo đơn hàng
     *
     * @param order
     */
    public void createDefaultReview(Order order) {
        if (order == null) {
            return;
        }
        if (order.getSellerId() == null || order.getBuyerId() == null) {
            return;
        }
        SellerReview review = new SellerReview();
        review.setUpdateTime(System.currentTimeMillis());
        review.setPoint(1);
        review.setActive(true);
        review.setIp(":01");
        User seller = userRepository.find(order.getSellerId());
        if (seller != null) {
            review.setContent("Người mua " + order.getBuyerName() + " đánh giá bình thường");
            review.setSellerId(seller.getId());
            review.setUserId(order.getBuyerId());
            review.setId(sellerReviewRepository.genId());
            review.setCreateTime(System.currentTimeMillis());
            review.setOrderId(order.getId());
            sellerReviewRepository.save(review);
        }
        review.setContent("Người bán " + (seller.getName() != null ? seller.getName() : seller.getEmail()) + " đánh giá bình thường");
        review.setId(sellerReviewRepository.genId());
        review.setUserId(seller.getId());
        review.setSellerId(order.getBuyerId());
        sellerReviewRepository.save(review);
    }

    /**
     * Thống kê theo người bán
     *
     * @param sellerId
     * @return
     */
    public HashMap<String, Long> report(String sellerId) {
        HashMap<String, Long> data = new HashMap<>();
        data.put("good", sellerReviewRepository.totalPoint(sellerId, 2));
        data.put("normal", sellerReviewRepository.totalPoint(sellerId, 1));
        data.put("bad", sellerReviewRepository.totalPoint(sellerId, 0));
        data.put("total", sellerReviewRepository.totalReview(sellerId));
        data.put("totalPoint", (data.get("good")*2) + data.get("normal"));
        return data;
    }

    /**
     * *
     * Tìm kiếm đánh giá uy tín theo điều khiện
     *
     * @param search
     * @return
     */
    public DataPage<SellerReview> search(SellerReviewSearch search) {

        Criteria cri = new Criteria();
        if (search.getActive() > 0) {
            cri.and("active").is(search.getActive() == 1);
        }
        if (search.getUserId() != null && !search.getUserId().equals("")) {
            cri.and("userId").is(search.getUserId());
        }
        if (search.getSellerId() != null && !search.getSellerId().equals("")) {
            cri.and("sellerId").is(search.getSellerId());
        }
        Sort sort;
        switch (search.getOrderBy()) {
            case 1:
                sort = new Sort(Sort.Direction.DESC, "createTime");
                break;
            case 2:
            default:
                sort = new Sort(Sort.Direction.DESC, "point");
                break;
        }
        DataPage<SellerReview> dataPage = new DataPage<>();
        Query query = new Query(cri);
        dataPage.setDataCount(sellerReviewRepository.count(new Query(cri)));
        dataPage.setPageIndex(search.getPageIndex());
        dataPage.setPageSize(search.getPageSize());
        dataPage.setPageCount(dataPage.getDataCount() / search.getPageSize());
        if (dataPage.getDataCount() % search.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        query.with(new PageRequest(search.getPageIndex(), search.getPageSize(), sort));
        dataPage.setData(sellerReviewRepository.find(query));
        return dataPage;

    }

    /**
     * Đổi trạng thái đánh giá
     *
     * @param id
     * @return
     * @throws Exception
     */
    public SellerReview changeActive(String id) throws Exception {
        SellerReview sellerReview = sellerReviewRepository.find(id);
        if (sellerReview == null) {
            throw new Exception("Không tìm thấy đánh giá yêu cầu");
        }
        sellerReview.setActive(!sellerReview.isActive());
        sellerReview.setUpdateTime(System.currentTimeMillis());
        sellerReviewRepository.save(sellerReview);
        return sellerReview;
    }

    /**
     * *
     * Lấy đánh giá theo mã đơn hàng
     *
     * @param id
     * @return
     * @throws Exception
     */
    public SellerReview getByOrderId(String id) throws Exception {
        SellerReview sellerReview = sellerReviewRepository.getByOrderId(id);
        if (sellerReview == null) {
            return null;
        }
        return sellerReview;
    }

    /**
     * Lấy danh sách đánh giá theo Ids
     *
     * @param ids
     * @param userId
     * @param seller
     * @return
     */
    public List<SellerReview> getByOrderIds(List<String> ids, String userId, boolean seller) {
        Criteria criteria = new Criteria();
        if (seller) {
            criteria.and("sellerId").is(userId);
        } else {
            criteria.and("userId").is(userId);
        }
        criteria.and("orderId").in(ids);
        List<SellerReview> sellerReview = sellerReviewRepository.find(new Query(criteria));
        if (sellerReview != null && !sellerReview.isEmpty()) {
            return sellerReview;
        } else {
            return null;
        }
    }

}
