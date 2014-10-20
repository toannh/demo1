/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.CategoryManufacturer;

/**
 *
 * @author Admin
 */
@Repository
public class CategoryManufacturerRepository extends BaseRepository<CategoryManufacturer> {

    public CategoryManufacturerRepository() {
        super(CategoryManufacturer.class);
    }

    /**
     * Tìm kiếm phân trang
     *
     * @param cri
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<CategoryManufacturer> search(Criteria cri, int pageIndex, int pageSize) {
        Query query = new Query(cri).with(new Sort(new Sort.Order("manufacturerName")));
        if (pageIndex >= 0 && pageSize > 0) {
            query.skip(pageIndex * pageSize).limit(pageSize);
        }
        return getMongo().find(query, getEntityClass());
    }

    /**
     * Xóa map theo thương hiệu
     *
     * @param manufacturerId
     * @param categoryId
     */
    public void deleteByCategoryIdAndManufacturerId(String categoryId, String manufacturerId) {
        Criteria criteria = new Criteria();
        if (manufacturerId != null && !manufacturerId.equals("")) {
            criteria.and("manufacturerId").is(manufacturerId);
        }
        if (categoryId != null && !categoryId.equals("")) {
            criteria.and("categoryId").is(categoryId);
        }
        getMongo().remove(new Query(criteria), getEntityClass());
    }

    /**
     * Đếm theo danh mục và thương hiệu
     *
     * @param categoryId
     * @param manufacturerId
     * @return
     */
    public long countByCategoryIdAndManufacturerId(String categoryId, String manufacturerId) {
        Criteria criteria = new Criteria();
        if (manufacturerId != null && !manufacturerId.equals("")) {
            criteria.and("manufacturerId").is(manufacturerId);
        }
        if (categoryId != null && !categoryId.equals("")) {
            criteria.and("categoryId").is(categoryId);
        }
        return getMongo().count(new Query(criteria), getEntityClass());
    }

    /**
     * cập nhật tên thương hiệu
     *
     * @param manufacturerId
     * @param manufacturerName
     */
    public void updateManufacturerName(String manufacturerId, String manufacturerName) {
        Criteria criteria = new Criteria();
        if (manufacturerId != null && !manufacturerId.equals("")) {
            criteria.and("manufacturerId").is(manufacturerId);
        }
        Update update = new Update();
        update.set("manufacturerName", manufacturerName);
        getMongo().updateMulti(new Query(criteria), update, getEntityClass());
    }

    /**
     * Lấy map theo manufacturerId
     *
     * @param manufacturerId
     * @return
     */
    public List<CategoryManufacturer> searchByManufacturerId(String manufacturerId) {
        return getMongo().find(new Query(new Criteria("manufacturerId").is(manufacturerId)), getEntityClass());
    }
}
