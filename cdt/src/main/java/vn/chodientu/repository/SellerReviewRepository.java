package vn.chodientu.repository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.SellerReview;

@Repository
public class SellerReviewRepository extends BaseRepository<SellerReview> {

    public SellerReviewRepository() {
        super(SellerReview.class);
    }

    /**
     * Đếm point
     *
     * @param sellerId
     * @param point
     * @return
     */
    public long totalPoint(String sellerId, int point) {
        point = point > 2 ? 2 : point;
        point = point < 0 ? 0 : point;
        return getMongo().count(new Query(new Criteria("sellerId").is(sellerId).and("point").is(point)), getEntityClass());
    }

    public long totalReview(String sellerId) {
        return getMongo().count(new Query(new Criteria("sellerId").is(sellerId)), getEntityClass());
    }

    public SellerReview find(String orderId, String userId) {
        Criteria cri = new Criteria();
        cri.and("orderId").is(orderId);
        cri.and("userId").is(userId);
        return getMongo().findOne(new Query(cri), getEntityClass());
    }
    public SellerReview getByOrderId(String orderId) {
        return getMongo().findOne(new Query(new Criteria("orderId").is(orderId)), getEntityClass());
    }
    
    
}
