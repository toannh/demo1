package vn.chodientu.repository;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.NewsHomeBox;


/**
 * @since Jun 2, 2014
 * @author Phuongdt
 */
@Repository
public class NewsHomeBoxRepository extends BaseRepository<NewsHomeBox> {

    public NewsHomeBoxRepository() {
        super(NewsHomeBox.class);
    }
    public NewsHomeBox getOne(){
        return getMongo().findOne(new Query(), getEntityClass());
        
    }
 
}
