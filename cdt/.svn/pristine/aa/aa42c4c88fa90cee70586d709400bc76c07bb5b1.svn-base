package vn.chodientu.repository;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.TmpImage;

/**
 * @since Jun 3, 2014
 * @author Phu
 */
@Repository
public class TmpImageRepository extends BaseRepository<TmpImage> {

    public TmpImageRepository() {
        super(TmpImage.class);
    }
    
    public TmpImage getForGet() {
        return getMongo().findAndModify(new Query(), new Update(), new FindAndModifyOptions().returnNew(true).remove(true), getEntityClass());
    }

}
