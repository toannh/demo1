package vn.chodientu.repository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Tag;

@Repository
public class TagRepository extends BaseRepository<Tag> {

    public TagRepository() {
        super(Tag.class);
    }

    public Tag find(String alias, String userId) {
        return getMongo().findOne(new Query(new Criteria("_id").is(alias).and("userId").is(userId)), getEntityClass());
    }

}
