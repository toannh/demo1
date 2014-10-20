package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Administrator;

@Repository
public class AdministratorRepository extends BaseRepository<Administrator> {

    public AdministratorRepository() {
        super(Administrator.class);
    }

    public Administrator findByEmail(String email) {
        return getMongo().findOne(new Query(new Criteria("email").is(email)), getEntityClass());
    }

    /**
     * Tìm kiếm phân trang
     *
     * @param cri
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<Administrator> search(Criteria cri, int pageIndex, int pageSize) {
        Query query = new Query(cri).with(new Sort(Sort.Direction.DESC, "joinTime"));
        if (pageIndex >= 0 && pageSize > 0) {
            query.skip(pageIndex * pageSize).limit(pageSize);
        }
        return getMongo().find(query, getEntityClass());
    }
}
