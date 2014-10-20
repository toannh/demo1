package vn.chodientu.repository;

import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.SellerHistory;

@Repository
public class SellerHistoryRepository extends BaseRepository<SellerHistory> {

    public SellerHistoryRepository() {
        super(SellerHistory.class);
    }

}
