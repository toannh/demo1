package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.News;

/**
 * @since May 15, 2014
 * @author Phuongdt
 */
@Repository
public class NewsRepository extends BaseRepository<News> {

    public NewsRepository() {
        super(News.class);
    }

    public List<News> list(Criteria cri, int pageSize, int offset) {
        Query query = new Query(cri);
        return getMongo().find(query.limit(pageSize).skip(offset).with(new Sort(Sort.Direction.DESC, "updateTime")), getEntityClass());
    }
    public List<News> listAll(){ 
         return getMongo().find(new Query(new Criteria()).with(new Sort(new Sort.Order(Sort.Direction.DESC, "createTime"))), getEntityClass());
    }

    public List<News> findByCategory(String[] cids) {
        return getMongo().find(new Query(new Criteria("categoryId").in((Object[]) cids).and("active").is(true)),getEntityClass());
    }
    public List<News> getByIds(String[] ids) {
        return getMongo().find(new Query(new Criteria("_id").in((Object[]) ids).and("active").is(true)),getEntityClass());
    }
}
