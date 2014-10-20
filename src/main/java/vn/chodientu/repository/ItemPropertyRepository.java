package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ItemProperty;

/**
 *
 * @author Phu
 */
@Repository
public class ItemPropertyRepository extends BaseRepository<ItemProperty> {

    public ItemPropertyRepository() {
        super(ItemProperty.class);
    }

    /**
     * Xóa thuộc tính ở tất cả item khi xóa thuộc tính danh mục
     *
     * @param categoryPropertyId
     */
    public void deleteWithCategoryProperty(String categoryPropertyId) {
        delete(new Query(new Criteria("categoryPropertyId").is(categoryPropertyId)));
    }

    /**
     * Xóa giá trị thuộc tính ở tất cả item khi xóa ở danh mục
     *
     * @param categoryPropertyId
     * @param categoryPropertyValueId
     */
    public void deleteWithCategoryPropertyValue(String categoryPropertyId, String categoryPropertyValueId) {
        getMongo().updateMulti(new Query(new Criteria("categoryPropertyId").is(categoryPropertyId)), new Update().pull("categoryPropertyValueIds", "categoryPropertyValueId"), getEntityClass());
    }

    /**
     * Lấy toàn bộ thuộc tính của sản phẩm
     *
     * @param itemId
     * @return
     */
    public List<ItemProperty> getByItem(String itemId) {
        return find(new Query(new Criteria("itemId").is(itemId)));
    }

    /**
     *
     * @param itemIds
     * @return
     */
    public List<ItemProperty> getByItems(List<String> itemIds) {
        return find(new Query(new Criteria("itemId").in(itemIds)));
    }

    /**
     * Xóa thuộc tính theo sản phẩm
     *
     * @param itemId
     */
    public void deleteByItem(String itemId) {
        delete(new Query(new Criteria("itemId").is(itemId)));
    }
}
