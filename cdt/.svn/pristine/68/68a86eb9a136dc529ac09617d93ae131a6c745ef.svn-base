package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ModelProperty;

/**
 *
 * @author Phu
 */
@Repository
public class ModelPropertyRepository extends BaseRepository<ModelProperty> {

    public ModelPropertyRepository() {
        super(ModelProperty.class);
    }

    /**
     * Xóa thuộc tính ở tất cả model khi xóa thuộc tính danh mục
     *
     * @param categoryPropertyId
     */
    public void deleteWithCategoryProperty(String categoryPropertyId) {
        delete(new Query(new Criteria("categoryPropertyId").is(categoryPropertyId)));
    }

    /**
     * Lấy thuộc tính theo model
     *
     * @param modelId
     * @return
     */
    public List<ModelProperty> getByModel(String modelId) {
        return find(new Query(new Criteria("modelId").is(modelId)));
    }

    /**
     * Lấy thuộc tính theo danh sách model
     *
     * @param modelIds
     * @return
     */
    public List<ModelProperty> getByModels(List<String> modelIds) {
        return find(new Query(new Criteria("modelId").in(modelIds)));
    }

    /**
     * Xóa thuộc tính theo model
     *
     * @param modelId
     */
    public void deleteByModel(String modelId) {
        delete(new Query(new Criteria("modelId").is(modelId)));
    }

    /**
     * Xóa giá trị thuộc tính ở tất cả model khi xóa ở danh mục
     *
     * @param categoryPropertyId
     * @param categoryPropertyValueId
     */
    public void deleteWithCategoryPropertyValue(String categoryPropertyId, String categoryPropertyValueId) {
        getMongo().updateMulti(new Query(new Criteria("categoryPropertyId").is(categoryPropertyId)), new Update().pull("categoryPropertyValueIds", "categoryPropertyValueId"), getEntityClass());
    }

}
