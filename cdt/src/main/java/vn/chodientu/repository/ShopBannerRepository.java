package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ShopBanner;
import vn.chodientu.entity.enu.ShopBannerType;

/**
 * @since Jun 2, 2014
 * @author Phuongdt
 */
@Repository
public class ShopBannerRepository extends BaseRepository<ShopBanner> {

    public ShopBannerRepository() {
        super(ShopBanner.class);
    }

    public List<ShopBanner> getAll(ShopBannerType bannerType,String sellerId) {
        Criteria criteria = new Criteria();
        if(bannerType == ShopBannerType.HEART){
            criteria.and("type").is(bannerType);
        }else{
             criteria.and("type").ne(ShopBannerType.HEART);
        }
        criteria.and("sellerId").is(sellerId);
        return getMongo().find(new Query(criteria), getEntityClass());

    }
    public List<ShopBanner> getAll(String sellerId,int active) {
        Criteria criteria = new Criteria();
        if(active==1){
            criteria.and("active").is(true);
        }
        criteria.and("sellerId").is(sellerId);
        return getMongo().find(new Query(criteria), getEntityClass());

    }

}
