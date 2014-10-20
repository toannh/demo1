package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.CategoryAlias;

@Repository
public class CategoryAliasRepository extends BaseRepository<CategoryAlias> {

    public CategoryAliasRepository() {
        super(CategoryAlias.class);
    }

    public List<CategoryAlias> getAll(int active) {
        Criteria cri = new Criteria();
        if (active > 0) {
            cri.and("active").is(active == 1);
        }
        return getMongo().find(new Query(cri).with(new Sort(Sort.Direction.ASC, "position")).limit(8), getEntityClass());
    }
}
