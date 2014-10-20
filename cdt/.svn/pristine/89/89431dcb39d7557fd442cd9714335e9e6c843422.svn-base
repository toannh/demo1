package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.District;

/**
 * @since May 15, 2014
 * @author Phu
 */
@Repository
public class DistrictRepository extends BaseRepository<District> {

    public DistrictRepository() {
        super(District.class);
    }

    public List<District> getAll() {
        return getMongo().find(new Query(new Criteria()).with(new Sort(new Sort.Order(Sort.Direction.ASC, "position"))), getEntityClass());
    }

    public void deleteByCityId(String cityId) {
        getMongo().remove(new Query(new Criteria("cityId").is(cityId)), getEntityClass());
    }

    public List<District> getAllDistrictByCity(String cityId) {
        Criteria cri = new Criteria();
        cri.and("cityId").in(cityId);
        Query query = new Query(cri);
        return getMongo().find(query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "position"))), getEntityClass());
    }

    public String getDistrictNameById(String districtId) {
        District find = find(districtId);
        return (find == null) ? null : find.getName();
    }

    public District getBySc(String scId) {
        return getMongo().findOne(new Query(new Criteria("scId").is(scId)), getEntityClass());
    }
}
