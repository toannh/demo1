package vn.chodientu.repository;

import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ShopSupport;

@Repository
public class ShopSupportRepository extends BaseRepository<ShopSupport> {

    public ShopSupportRepository() {
        super(ShopSupport.class);
    }

}
