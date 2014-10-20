package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.AdvBanner;
import vn.chodientu.entity.enu.AdvBannerType;

/**
 *
 * @author Phu
 */
@Repository
public class AdvBannerRepository extends BaseRepository<AdvBanner> {

    public AdvBannerRepository() {
        super(AdvBanner.class);
    }

    /**
     * Lấy banner cho quảng cáo
     *
     * @param cateId
     * @param type
     * @return
     */
    public AdvBanner getBanner(String cateId, AdvBannerType type) {
        return getMongo().findOne(new Query(new Criteria("categoryId").is(cateId).and("active").is(true)).with(new Sort(Sort.Direction.DESC, "createTime")), getEntityClass());
    }

    /**
     * Lấy 1 banner quảng cáo theo type
     *
     * @param type
     * @return
     */
    public AdvBanner getBannerByType(AdvBannerType type) {
        return getMongo().findOne(new Query(new Criteria("position").is(type).and("active").is(true)).with(new Sort(Sort.Direction.DESC, "createTime")), getEntityClass());
    }

    /**
     * Lấy tất cả có phân trang
     *
     * @param cri
     * @param page
     * @return
     */
    public List<AdvBanner> getAll(Criteria cri, Pageable page) {
        return getMongo().find(new Query(cri).with(new Sort(Sort.Direction.DESC, "createTime")).with(page), getEntityClass());
    }

}
