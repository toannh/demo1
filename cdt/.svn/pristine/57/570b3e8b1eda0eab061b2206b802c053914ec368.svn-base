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
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.util.ListUtils;

/**
 *
 * @author ThuNguyen
 */
@Repository
public class ShopRepository extends BaseRepository<Shop> {

    public ShopRepository() {
        super(Shop.class);
    }

    public Shop findByUser(String sellerId) {
        return getMongo().findOne(new Query(new Criteria("userId").is(sellerId)), getEntityClass());
    }

    /**
     * Lấy shop theo alias
     *
     * @param alias
     * @return
     */
    public Shop getByAlias(String alias) {
        return getMongo().findOne(new Query(new Criteria("alias").is(alias)), getEntityClass());
    }

    public Shop getByUrl(String url) {
        return getMongo().findOne(new Query(new Criteria("url").regex(url, "i")), getEntityClass());
    }

    public boolean exitsByAlias(String alias) {
        return getMongo().count(new Query(new Criteria("alias").is(alias)), getEntityClass()) > 0;
    }

    public List<Shop> get(List<String> ids) {
        Query query = new Query(new Criteria("userId").in(ids));
        return ListUtils.orderByArray(getMongo().find(query, getEntityClass()), ids);
    }

}
