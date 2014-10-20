/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.PromotionCategory;

/**
 *
 * @author ThienPhu
 */
@Repository
public class PromotionCategoryRepository extends BaseRepository<PromotionCategory> {
    
    public PromotionCategoryRepository() {
        super(PromotionCategory.class);
    }
    
    public List<PromotionCategory> get(String promotionId) {
        return getMongo().find(new Query(new Criteria("promotionId").is(promotionId)), getEntityClass());
    }
    public void remove(String promotionId) {
        getMongo().remove(new Query(new Criteria("promotionId").is(promotionId)), getEntityClass());
    }

    public PromotionCategory getPromotionCategory(String promotionId, String categoryId) {
        return getMongo().findOne(new Query(new Criteria("categoryId").is(categoryId).and("promotionId").is(promotionId)), getEntityClass());
    }

    public List<PromotionCategory> getList(List<String> proIds) {
        return getMongo().find(new Query(new Criteria("promotionId").in(proIds)), getEntityClass());
    }
}
