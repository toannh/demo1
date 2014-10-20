package vn.chodientu.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ItemWarning;
import vn.chodientu.entity.enu.ItemWarningType;

@Repository
public class ItemWarningRepository extends BaseRepository<ItemWarning> {

    public ItemWarningRepository() {
        super(ItemWarning.class);
    }

    /**
     * lần cuối báo
     *
     * @param type
     * @param itemId
     * @return
     */
    public ItemWarning lastWarning(ItemWarningType type, String itemId) {
        Criteria cri = new Criteria();
        cri.and("type").is(type);
        cri.and("itemId").is(itemId);
        return getMongo().findOne(new Query(cri).with(new Sort(Sort.Direction.DESC, "createTime")), getEntityClass());
    }
}
