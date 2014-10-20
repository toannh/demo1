package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ItemFollow;

@Repository
public class ItemFollowRepository extends BaseRepository<ItemFollow> {

    public ItemFollowRepository() {
        super(ItemFollow.class);
    }

    /**
     * Số quan tâm theo sản phẩm
     *
     * @param itemId
     * @return
     */
    public long countByItem(String itemId) {
        Criteria cri = new Criteria();
        cri.and("itemId").is(itemId);
        cri.and("active").is(true);
        return getMongo().count(new Query(cri), getEntityClass());
    }

    public ItemFollow find(String id, String userId) {
        Criteria cri = new Criteria();
        cri.and("itemId").is(id);
        cri.and("userId").is(userId);
        return getMongo().findOne(new Query(cri), getEntityClass());
    }

    /**
     * Xóa danh sách sản phẩm quan tâm
     *
     * @param ids
     * @param userId
     */
    public void remove(List<String> ids, String userId) {
        Criteria cri = new Criteria();
        cri.and("itemId").in(ids);
        cri.and("userId").is(userId);
        this.delete(new Query(cri));
    }

    /**
     * Lấy item quan tâm của người dùng
     *
     * @param cri
     * @param pageable
     * @return
     */
    public List<ItemFollow> getList(Criteria cri, Pageable pageable) {
        cri.and("active").is(true);
        Query query = new Query(cri).with(pageable);
        query.with(new Sort(Sort.Direction.DESC, "createTime"));
        return getMongo().find(query, getEntityClass());
    }

}
