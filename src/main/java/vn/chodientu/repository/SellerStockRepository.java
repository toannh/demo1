package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.SellerStock;

@Repository
public class SellerStockRepository extends BaseRepository<SellerStock> {

    public SellerStockRepository() {
        super(SellerStock.class);
    }

    /**
     * Danh sách kho
     *
     * @param sellerId
     * @param active
     * @return
     */
    public List<SellerStock> getBySellerId(String sellerId, int active) {
        Criteria cri = new Criteria();
        cri.and("sellerId").is(sellerId);
        if (active > 0) {
            cri.and("active").is(active == 1);
        }
        return getMongo().find(new Query(cri).with(new Sort(Sort.Direction.ASC, "order")), getEntityClass());
    }

    /**
     * Lấy 1 kho theo type
     *
     * @param type
     * @return
     */
    public SellerStock findByType(String sellerId,int type) {
        return getMongo().findOne(new Query(new Criteria("sellerId").is(sellerId).and("type").is(type)), getEntityClass());
    }
}
