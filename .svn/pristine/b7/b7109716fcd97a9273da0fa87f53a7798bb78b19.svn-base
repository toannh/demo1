package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.LandingItem;

/**
 *
 * @author thunt
 */
@Repository
public class LandingItemRepository extends BaseRepository<LandingItem> {

    public LandingItemRepository() {
        super(LandingItem.class);
    }

    public List<LandingItem> getByLandingCategory(String cateId, Pageable page, int special) {
        Criteria cri = new Criteria("landingCategoryId").is(cateId);
        if (special > 0) {
            cri.and("special").is(special == 1);
        }
        Query query = new Query(cri);
        if (page != null && (page.getPageNumber() > 0 || page.getPageSize() > 0)) {
            query.with(page);
        }
        return getMongo().find(query, getEntityClass());
    }
    public List<LandingItem> getByCategories(List<String> cateIds, Pageable page, int special) {
        Criteria cri = new Criteria("landingCategoryId").in(cateIds);
        if (special > 0) {
            cri.and("special").is(special == 1);
        }
        Query query = new Query(cri);
        if (page != null && (page.getPageNumber() > 0 || page.getPageSize() > 0)) {
            query.with(page);
        }
        return getMongo().find(query, getEntityClass());
    }

    public long countByCategory(String cateId) {
        Criteria cri = new Criteria("landingCategoryId").is(cateId);
        Query query = new Query(cri);
        return getMongo().count(query, getEntityClass());
    }

    public void removeByCategory(String cateId) {
        Criteria cri = new Criteria("landingCategoryId").is(cateId);
        getMongo().remove(new Query(cri), getEntityClass());
    }

    public LandingItem getByItem(String itemId) {
        return getMongo().findOne(new Query(new Criteria("itemId").is(itemId)), getEntityClass());
    }
}
