package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.City;

/**
 * @since May 15, 2014
 * @author Phu
 */
@Repository
public class CityRepository extends BaseRepository<City> {

    public CityRepository() {
        super(City.class);
    }

    public List<City> getAll() {
        return getMongo().find(new Query(new Criteria()).with(new Sort(new Sort.Order(Sort.Direction.ASC, "position"))), getEntityClass());
    }

    public City getbyId(String id) {
        return getMongo().findOne(new Query(new Criteria("id").is(id)), getEntityClass());
    }

    /**
     *
     * @param ids
     * @return
     */
    public List<City> get(List<String> ids) {
        Query query = new Query(new Criteria("id").in(ids));
        return getMongo().find(query, getEntityClass());
    }

    public City getBySc(String scId) {
        return getMongo().findOne(new Query(new Criteria("scId").is(scId)), getEntityClass());
    }
}
