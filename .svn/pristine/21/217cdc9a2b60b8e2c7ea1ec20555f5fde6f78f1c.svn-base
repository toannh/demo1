package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.CpFunction;

@Repository
public class CpFunctionRepository extends BaseRepository<CpFunction> {

    public CpFunctionRepository() {
        super(CpFunction.class);
    }

    /**
     * Lấy danh sách chức năng theo skip
     *
     * @param skip
     * @return
     */
    public List<CpFunction> findBySkip(boolean skip) {
        return getMongo().find(new Query(new Criteria("skip").is(skip)).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }

}
