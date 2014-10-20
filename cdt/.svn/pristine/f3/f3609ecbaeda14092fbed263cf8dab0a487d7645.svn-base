package vn.chodientu.repository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ModelReviewLike;

@Repository
public class ModelReviewLikeRepository extends BaseRepository<ModelReviewLike> {

    public ModelReviewLikeRepository() {
        super(ModelReviewLike.class);
    }

    public ModelReviewLike getLikeByCommnetAndUser(String userId, String commentId) {
        return getMongo().findOne(new Query(new Criteria("userId").is(userId).and("commentId").is(commentId)), getEntityClass());
    }

}
