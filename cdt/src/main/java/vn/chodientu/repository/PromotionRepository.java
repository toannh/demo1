/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Promotion;
import vn.chodientu.entity.enu.PromotionTarget;
import vn.chodientu.entity.enu.PromotionType;

/**
 *
 * @author ThienPhu
 */
@Repository
public class PromotionRepository extends BaseRepository<Promotion> {

    public PromotionRepository() {
        super(Promotion.class);
    }

    public List<Promotion> promotionSameTime(long startTime, long endTime, PromotionType type, PromotionTarget target, String sellerId, String promotionId) {
        Criteria criteria = new Criteria("active").is(true).and("id").ne(promotionId)
                .and("target").is(target).and("type").is(type).and("sellerId").is(sellerId);

        criteria.orOperator(
                new Criteria("startTime").lte(startTime).and("endTime").gte(startTime),
                new Criteria("startTime").lte(endTime).and("endTime").gte(endTime),
                new Criteria("startTime").gte(startTime).and("endTime").lte(endTime)
        );

        Query query = new Query(criteria);
        return getMongo().find(query, getEntityClass());
    }

//    public List<Promotion> getForPublic() {
//        Criteria cri = new Criteria("active").is(true).and("published").is(false).and("startTime").lte(new Date().getTime()).and("endTime").gte(new Date().getTime());
//        return getMongo().find(new Query(cri), getEntityClass());
//    }
    public Promotion getForPublic() {
        Criteria cri = new Criteria("active").is(true).and("published").is(false).and("startTime").lte(new Date().getTime()).and("endTime").gte(new Date().getTime());
        return getMongo().findAndModify(new Query(cri).with(new Sort(Sort.Direction.ASC, "startTime")), new Update().set("published", true), getEntityClass());
    }

    public Promotion getForUnPublic() {
        Criteria cri1 = new Criteria("published").is(true).and("endTime").lte(new Date().getTime());
        Criteria cri2 = new Criteria("active").is(false).and("published").is(true);
        Criteria cri = new Criteria().orOperator(cri1, cri2);
        return getMongo().findAndModify(new Query(cri).with(new Sort(Sort.Direction.ASC, "startTime")), new Update().set("published", false), getEntityClass());
    }

    public List<Promotion> getBySeller(String sellerId, PromotionType type, int page) {
        Criteria cri = new Criteria("sellerId").is(sellerId).and("type").is(type);
        return getMongo().find(new Query(cri).skip((page - 1) * 10).limit(10).with(new Sort(Sort.Direction.DESC, "startTime")), getEntityClass());
    }

    public long getCountBySeller(String sellerId, PromotionType type) {
        Criteria cri = new Criteria("sellerId").is(sellerId).and("type").is(type);
        return getMongo().count(new Query(cri), getEntityClass());
    }

    public Long getCountDelBySeller(String sellerId) {
        Criteria cri = new Criteria("sellerId").is(sellerId).and("active").is(false);
        return getMongo().count(new Query(cri), getEntityClass());
    }

    public List<Promotion> getDelBySeller(String sellerId, int page) {
        Criteria cri = new Criteria("sellerId").is(sellerId).and("active").is(false);
        return getMongo().find(new Query(cri).skip((page - 1) * 10).limit(10).with(new Sort(Sort.Direction.DESC, "startTime")), getEntityClass());
    }

    /**
     * Lấy promotion đang chạy của người bán
     *
     * @param sellerId
     * @param type
     * @param published
     * @return
     */
    public List<Promotion> getPromotionBySellerRunning(String sellerId, PromotionType type, int published) {
        Criteria cri = new Criteria("sellerId").is(sellerId).and("active").is(true).and("startTime").lte(System.currentTimeMillis()).and("endTime").gte(System.currentTimeMillis());
        if (type != null) {
            cri.and("type").is(type);
        }
        if (published > 0) {
            cri.and("published").is(published == 1);
        }
        return getMongo().find(new Query(cri), getEntityClass());
    }
}
