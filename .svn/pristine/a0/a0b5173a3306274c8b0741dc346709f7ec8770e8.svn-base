package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.HotdealItem;

/**
 *
 * @author thunt
 */
@Repository
public class HotdealItemRepository extends BaseRepository<HotdealItem> {

    public HotdealItemRepository() {
        super(HotdealItem.class);
    }

    public List<HotdealItem> getByCategory(String cateId, Pageable page) {
        Criteria cri = new Criteria("hotdealCategoryPath").is(cateId).and("home").is(false);
        Query query = new Query(cri);
        if (page != null && (page.getPageNumber() > 0 || page.getPageSize() > 0)) {
            query.with(page);
        }
        return getMongo().find(query, getEntityClass());
    }

    public long countByCategory(String cateId) {
        Criteria cri = new Criteria("hotdealCategoryPath").is(cateId).and("home").is(false);
        Query query = new Query(cri);
        return getMongo().count(query, getEntityClass());
    }

    public List<HotdealItem> getHomeByCategory(String cateId) {
        Criteria cri = new Criteria("home").is(true);
        if (cateId != null) {
            cri.and("hotdealCategoryId").is(cateId);
        }
        return getMongo().find(new Query(cri), getEntityClass());
    }

    public void removeByCategory(String cateId) {
        Criteria cri = new Criteria("hotdealCategoryPath").is(cateId);
        getMongo().remove(new Query(cri), getEntityClass());
    }

    public HotdealItem getByItem(String id, boolean home) {
        Criteria cri = new Criteria("itemId").is(id).and("home").is(home);
        return getMongo().findOne(new Query(cri), getEntityClass());
    }
}
