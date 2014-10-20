package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.SellerCustomer;

@Repository
public class SellerCustomerRepository extends BaseRepository<SellerCustomer> {

    public SellerCustomerRepository() {
        super(SellerCustomer.class);
    }

    public boolean exitsUniqueEmail(String email, String sellerId, String id) {
        Criteria cri = new Criteria();
        cri.and("sellerId").is(sellerId);
        cri.and("email").is(email);
        cri.and("remove").is(false);
        if (id != null) {
            cri.and("_id").ne(id);
        }
        return getMongo().count(new Query(cri), getEntityClass()) > 0;
    }

    public boolean exitsUniquePhone(String phone, String sellerId, String id) {
        Criteria cri = new Criteria();
        cri.and("sellerId").is(sellerId);
        cri.and("phone").is(phone);
        cri.and("remove").is(false);
        if (id != null) {
            cri.and("_id").ne(id);
        }
        return getMongo().count(new Query(cri), getEntityClass()) > 0;
    }

    /**
     * Xóa khách hàng
     *
     * @param cri
     */
    public void remove(Criteria cri) {
        getMongo().updateMulti(new Query(cri), new Update().set("remove", true), getEntityClass());
    }

    /**
     * Danh sách email theo trạng thái
     *
     * @param emails
     * @param emailVerified
     * @param sellerId
     * @return
     */
    public List<SellerCustomer> getByEmail(List<String> emails, int emailVerified, String sellerId) {
        Criteria criteria = new Criteria();
        criteria.and("email").in(emails);
        criteria.and("sellerId").in(sellerId);
        if (emailVerified > 0) {
            criteria.and("emailVerified").in(emailVerified == 1);
        }
        return getMongo().find(new Query(criteria).with(new Sort(Sort.Direction.ASC, "email")), getEntityClass());
    }

    /**
     * Danh sách phone theo trạng thái
     *
     * @param phones
     * @param phoneVerified
     * @return
     */
    public List<SellerCustomer> getByPhone(List<String> phones, int phoneVerified, String sellerId) {
        Criteria criteria = new Criteria();
        criteria.and("phone").in(phones);
        criteria.and("sellerId").in(sellerId);
        if (phoneVerified > 0) {
            criteria.and("phoneVerified").in(phoneVerified == 1);
        }
        return getMongo().find(new Query(criteria).with(new Sort(Sort.Direction.ASC, "phone")), getEntityClass());
    }

    /**
     * Danh sách khách hàng theo ids
     *
     * @param ids
     * @return
     */
    public List<SellerCustomer> getByIds(List<String> ids) {
        Criteria criteria = new Criteria();
        criteria.and("id").in(ids);
        return getMongo().find(new Query(criteria), getEntityClass());
    }

    /**
     * Danh sách khách hàng theo emails
     *
     * @param emails
     * @return
     */
    public List<SellerCustomer> getByEmails(List<String> emails) {
        Criteria criteria = new Criteria();
        criteria.and("email").in(emails);
        return getMongo().find(new Query(criteria), getEntityClass());
    }

    public SellerCustomer get(String email, String phone, String sellerId) {
        Criteria criteria = new Criteria();
        criteria.and("email").is(email);
        criteria.and("phone").is(phone);
        criteria.and("sellerId").is(sellerId);
        return getMongo().findOne(new Query(criteria), getEntityClass());
    }
}
