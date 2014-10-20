/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Email;
import vn.chodientu.entity.db.Seller;

/**
 *
 * @author ThuNguyen
 */
@Repository
public class SellerRepository extends BaseRepository<Seller> {

    public SellerRepository() {
        super(Seller.class);
    }

    public Seller getById(String id) {
        return getMongo().findOne(new Query(new Criteria("userId").is(id)), getEntityClass());

    }

    public List<Seller> get(List<String> userIds) {
        return find(new Query(new Criteria("userId").in(userIds)));
    }

    /**
     * Lấy lấy ra 1 người bán đã tích hợp ship chung và ngân lượng
     *
     * @param id
     * @return
     */
    public Seller getByNLSC(String id) {
        Criteria criteria = new Criteria();
        criteria.and("_id").is(id);
        criteria.and("nlIntegrated").is(true);
        criteria.and("scIntegrated").is(true);
        return getMongo().findOne(new Query(criteria), getEntityClass());
    }

    /**
     * add contact
     *
     * @return
     */
    public Seller addContact() {
        Criteria cri = new Criteria();
        cri.and("pushC").ne(true);
        return getMongo().findAndModify(new Query(cri),
                new Update().set("pushC", true), new FindAndModifyOptions().returnNew(true), getEntityClass());
    }

    public Seller pushIntegrated() {
        Criteria cri = new Criteria();
        cri.and("pushIntegrated").ne(true);
        return getMongo().findAndModify(new Query(cri),
                new Update().set("pushIntegrated", true), new FindAndModifyOptions().returnNew(true), getEntityClass());
    }
}
