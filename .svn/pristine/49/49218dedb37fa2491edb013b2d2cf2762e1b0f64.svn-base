package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.SellerFollow;

@Repository
public class SellerFollowRepository extends BaseRepository<SellerFollow> {

    public SellerFollowRepository() {
        super(SellerFollow.class);
    }

    /**
     * Số quan tâm theo người bán
     *
     * @param sellerId
     * @return
     */
    public long countBySellerId(String sellerId) {
        Criteria cri = new Criteria();
        cri.and("sellerId").is(sellerId);
        cri.and("active").is(true);
        return getMongo().count(new Query(cri), getEntityClass());
    }

    /**
     *
     * @param userId
     * @param sellerId
     * @return
     */
    public SellerFollow find(String userId, String sellerId) {
        Criteria cri = new Criteria();
        cri.and("sellerId").is(sellerId);
        cri.and("userId").is(userId);
        return getMongo().findOne(new Query(cri), getEntityClass());
    }

    /**
     *
     * @param sellerIds
     * @param userId
     */
    public void remove(List<String> sellerIds, String userId) {
        Criteria cri = new Criteria();
        cri.and("sellerId").in(sellerIds);
        cri.and("userId").is(userId);
        this.delete(new Query(cri));
    }

    /**
     *
     * @param cri
     * @param pageable
     * @return
     */
    public List<SellerFollow> getList(Criteria cri, Pageable pageable) {
        cri.and("active").is(true);
        Query query = new Query(cri).with(pageable);
        query.with(new Sort(Sort.Direction.DESC, "createTime"));
        return getMongo().find(query, getEntityClass());
    }

}
