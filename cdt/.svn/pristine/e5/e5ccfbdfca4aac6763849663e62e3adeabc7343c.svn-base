/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.util.ListUtils;

/**
 *
 * @author Admin
 */
@Repository
public class ManufacturerRepository extends BaseRepository<Manufacturer> {

    public ManufacturerRepository() {
        super(Manufacturer.class);
    }

    /**
     * Kiểm tra tên thương hiệu tồn tại
     *
     * @param id
     * @param name
     * @return
     */
    public long countByName(String id, String name) {
        Query query = new Query(new Criteria("name").is(name).and("id").ne(id));
        return getMongo().count(query, getEntityClass());
    }

    /**
     * Lấy danh sách thương hiệu theo ids
     *
     * @param ids
     * @return
     */
    public List<Manufacturer> get(List<String> ids) {
        Query query = new Query(new Criteria("id").in(ids));
        return ListUtils.orderByArray(getMongo().find(query, getEntityClass()), ids);
    }

    /**
     * Lấy danh sách thương hiệu theo trang
     *
     * @param pageRequest
     * @return
     */
    public List<Manufacturer> list(PageRequest pageRequest) {
        return getMongo().find(new Query().with(pageRequest), getEntityClass());
    }

}
