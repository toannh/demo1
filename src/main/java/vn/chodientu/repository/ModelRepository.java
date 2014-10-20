package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Model;
import vn.chodientu.util.ListUtils;

@Repository
public class ModelRepository extends BaseRepository<Model> {
    
    public ModelRepository() {
        super(Model.class);
    }

    /**
     *
     * @param ids
     * @return
     */
    public List<Model> get(List<String> ids) {
        return ListUtils.orderByArray(find(new Query(new Criteria("id").in(ids))), ids);
    }

    /**
     * Lấy thời gian update lớn nhất của tất cả model, dùng để convert, convert
     * xong sẽ xóa
     *
     * @return
     */
    public long getLastUpdate() {
        Model m = getMongo().findOne(new Query().with(new Sort(Sort.Direction.DESC, "updateTime")), getEntityClass());
        return m == null ? 0 : m.getUpdateTime();
    }

    /**
     * Đếm tổng số lương model theo thương hiệu
     *
     * @param manufacturerId
     * @return
     */
    public long countByManufacturer(String manufacturerId) {
        return count(new Query(new Criteria("manufacturerId").is(manufacturerId)));
    }

    /**
     * Đếm tổng số lượng model theo danh mục
     *
     * @param categoryId
     * @return
     */
    public long countByCategory(String categoryId) {
        return count(new Query(new Criteria("categoryId").is(categoryId)));
    }

    /**
     * Lấy model có ảnh cũ, sau này sẽ xóa
     *
     * @return
     */
    public List<Model> getOldImage() {
        return find(new Query(new Criteria("oldImage").is(true)).limit(1000));
    }

    /**
     * Lấy danh sách model theo trang
     *
     * @param pageRequest
     * @return
     */
    public List<Model> list(PageRequest pageRequest) {
        return getMongo().find(new Query().with(pageRequest), getEntityClass());
    }

    /**
     * Lấy danh sách model theo Id thương hiệu
     *
     * @param manufacturerId
     * @return
     */
    public List<Model> getModelByManufactureId(String manufacturerId) {
        Criteria criteria = new Criteria();
        criteria.and("manufacturerId").is(manufacturerId);
        return getMongo().find(new Query(criteria).limit(10), getEntityClass());
        
    }
}
