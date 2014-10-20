package vn.chodientu.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ModelLog;

@Repository
public class ModelLogRepository extends BaseRepository<ModelLog> {

    public ModelLogRepository() {
        super(ModelLog.class);
    }

    /**
     * Lấy log cuối cùng của một model
     *
     * @param modelId
     * @return
     */
    public ModelLog getLastLog(String modelId) {
        return getMongo().findOne(new Query(new Criteria("modelId").is(modelId)).with(new Sort(Sort.Direction.DESC, "time")), getEntityClass());
    }
}
