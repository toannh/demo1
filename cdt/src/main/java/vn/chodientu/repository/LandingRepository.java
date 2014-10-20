package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Landing;

/**
 *
 * @author thunt
 */
@Repository
public class LandingRepository extends BaseRepository<Landing> {

    public LandingRepository() {
        super(Landing.class);
    }

    public List<Landing> getAll(Pageable pageable) {
        return getMongo().find(new Query().with(pageable), getEntityClass());
    }
}
