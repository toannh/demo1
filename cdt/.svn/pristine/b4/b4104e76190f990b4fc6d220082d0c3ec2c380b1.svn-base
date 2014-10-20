package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ItemReviewLike;

@Repository
public class ItemReviewLikeRepository extends BaseRepository<ItemReviewLike> {

    public ItemReviewLikeRepository() {
        super(ItemReviewLike.class);
    }

    /**
     * Get like theo user and comment
     *
     * @param id
     * @param userId
     * @return
     */
    public ItemReviewLike getByItemReviewIdAndUserId(String id, String userId) {
        Criteria cri = new Criteria();
        cri.and("userId").is(userId);
        cri.and("commentId").is(id);
        return getMongo().findOne(new Query(cri), getEntityClass());
    }
    public List<ItemReviewLike> getByItemReviewId(String id) {
        Criteria cri = new Criteria();
        cri.and("commentId").is(id);
        return getMongo().find(new Query(cri), getEntityClass());
    }

}
