package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ModelApprove;
import vn.chodientu.entity.enu.ModelApproveStatus;

/**
 * @since May 7, 2014
 * @author Phu
 */
@Repository
public class ModelApproveRepository extends BaseRepository<ModelApprove> {

    public ModelApproveRepository() {
        super(ModelApprove.class);
    }

    /**
     * Lấy danh sách model approve theo admin và tình trạng
     *
     * @param administratorId
     * @param status
     * @return
     */
    public List<ModelApprove> find(String administratorId, ModelApproveStatus status) {
        Criteria cri = new Criteria("status").is(status);
        if (administratorId != null) {
            cri.and("administratorId").is(administratorId);
        }
        return find(new Query(cri).with(new Sort(Sort.Direction.DESC, "updateTime")));
    }
}
