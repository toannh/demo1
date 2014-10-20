package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ItemApprove;
import vn.chodientu.entity.enu.ItemApproveStatus;

/**
 * @since May 9, 2014
 * @author Phu
 */
@Repository
public class ItemApproveRepository extends BaseRepository<ItemApprove> {

    public ItemApproveRepository() {
        super(ItemApprove.class);
    }

    public ItemApprove getLastAppoved(String id) {
        return getMongo().findOne(new Query(new Criteria("itemId").is(id)).with(new Sort(Sort.Direction.DESC, "time")), getEntityClass());
    }

    /**
     * Lấy danh sách item không được duyệt theo ids
     *
     * @param ids
     * @return
     */
    public List<ItemApprove> getNoteApproveByIds(List<String> ids) {
        return getMongo().find(new Query(new Criteria("itemId").in(ids).and("status").is(ItemApproveStatus.DISAPPROVED)).with(new Sort(Sort.Direction.ASC,"time")), getEntityClass());
    }

}
