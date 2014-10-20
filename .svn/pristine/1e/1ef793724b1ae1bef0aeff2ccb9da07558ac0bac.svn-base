package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ModelReview;

@Repository
public class ModelReviewRepository extends BaseRepository<ModelReview> {

    public ModelReviewRepository() {
        super(ModelReview.class);
    }

    public List<ModelReview> list(Criteria cri, PageRequest pageRequest) {
        return getMongo().find(new Query(cri).with(pageRequest), getEntityClass());
    }

    public long countUserAndModel(String modelId, String userId) {
        return getMongo().count(new Query(new Criteria("modelId").is(modelId).and("userId").is(userId)), getEntityClass());
    }

}
