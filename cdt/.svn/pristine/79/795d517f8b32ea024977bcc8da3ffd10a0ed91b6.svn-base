/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.PromotionItem;

/**
 *
 * @author ThienPhu
 */
@Repository
public class PromotionItemRepository extends BaseRepository<PromotionItem> {

    public PromotionItemRepository() {
        super(PromotionItem.class);
    }

    public List<PromotionItem> get(String promotionId) {
        Query query = new Query(new Criteria("promotionId").is(promotionId));
        return getMongo().find(query, getEntityClass());
    }
    
    public PromotionItem getPromotionItem(String promotionId, String itemId) {
        return getMongo().findOne(new Query(new Criteria("promotionId").is(promotionId).and("itemId").is(itemId)), getEntityClass());
    }

    public List<PromotionItem> getAll() {
        return getMongo().find(new Query(new Criteria("discountPrice").gte(0)), getEntityClass());
    }

    public void remove(String promotionId) {
        getMongo().remove(new Query(new Criteria("promotionId").is(promotionId)), getEntityClass());
    }

    public List<PromotionItem> getList(List<String> proIds) {
        return getMongo().find(new Query(new Criteria("promotionId").in(proIds)), getEntityClass());
    }
    
    public List<PromotionItem> get(String promotionId, Pageable pageable) {
        Query query = new Query(new Criteria("promotionId").is(promotionId));
        if(pageable != null && pageable.getPageSize() > 0){
            query.with(pageable);
        }
        return getMongo().find(query, getEntityClass());
    }
    
     public long count(String promotionId) {
        Query query = new Query(new Criteria("promotionId").is(promotionId));
        return getMongo().count(query, getEntityClass());
    }

}
