package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ScHistory;

@Repository
public class ScHistoryRepository extends BaseRepository<ScHistory> {

    public ScHistoryRepository() {
        super(ScHistory.class);
    }

    public List<ScHistory> list(Criteria cri, PageRequest pageRequest) {
        return getMongo().find(new Query(cri).with(pageRequest), getEntityClass());
    }

}
