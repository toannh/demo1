package vn.chodientu.repository;

import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ApiHistory;

@Repository
public class ApiHistoryRepository extends BaseRepository<ApiHistory> {

    public ApiHistoryRepository() {
        super(ApiHistory.class);
    }

}
