package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import vn.chodientu.entity.db.Coupon;
import org.springframework.stereotype.Repository;


@Repository
public class CouponRepository extends BaseRepository<Coupon> {

    public CouponRepository() {
        super(Coupon.class);
    }
    
    /**
     * get list coupon cua 1 seller
     * @param sellerId
     * @return list Coupon
     */
    public List<Coupon> getListCouponBySellerId(String sellerId) {
        Query query = new Query(new Criteria("sellerId").is(sellerId));
        query.with(new Sort(Sort.Direction.DESC, "startTime"));
        return getMongo().find(query, getEntityClass());
    }
    
    /**
     * get coupon dang chay trong khoang thoi gian
     * @param sellerId
     * @param timeCheck
     * @return listcoupon
     */
    public Coupon getCouponByTime(String sellerId, long timeCheck) {
        Criteria filter = new Criteria();
        filter.and("startTime").lte(timeCheck);
        filter.and("endTime").gte(timeCheck);
        filter.and("sellerId").is(sellerId);
        filter.and("active").is(true);
        Query query = new Query(filter);
        return getMongo().findOne(query, getEntityClass());
    }
    
    /**
     * get coupon by code
     * @param code
     * @return Coupon
     */
    public Coupon findByCode(String code){
        return getMongo().findOne(new Query(new Criteria("code").is(code)), getEntityClass());
    }
      
}
