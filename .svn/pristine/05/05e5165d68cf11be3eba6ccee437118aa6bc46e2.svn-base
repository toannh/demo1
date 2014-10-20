package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.VipItem;

@Repository
public class VipItemRepository extends BaseRepository<VipItem> {

    public VipItemRepository() {
        super(VipItem.class);
    }

    public List<VipItem> list(Criteria cri, int pageSize, int offset) {
        Query query = new Query(cri);
        return getMongo().find(query.limit(pageSize).skip(offset).with(new Sort(Sort.Direction.DESC, "createTime")), getEntityClass());
    }

}
