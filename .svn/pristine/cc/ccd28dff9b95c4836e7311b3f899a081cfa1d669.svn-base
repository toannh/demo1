package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.User;
import vn.chodientu.util.ListUtils;

@Repository
public class UserRepository extends BaseRepository<User> {

    public UserRepository() {
        super(User.class);
    }

    /**
     * Kiểm tra tên đăng nhập đã được đăng ký chưa
     *
     * @param username
     * @return
     */
    public boolean existsUsername(String username) {
        return count(new Query(new Criteria("username").is(username))) > 0;
    }

    /**
     * Kiểm tra email đã được dùng chưa
     *
     * @param email
     * @return
     */
    public boolean existsEmail(String email) {
        return count(new Query(new Criteria("email").is(email))) > 0;
    }

    /**
     * Tìm user theo email
     *
     * @param email
     * @return
     */
    public User getByEmail(String email) {
        return getMongo().findOne(new Query(new Criteria("email").is(email)), getEntityClass());
    }

    /**
     * Tìm user theo username
     *
     * @param username
     * @return user
     */
    public User findByUsername(String username) {
        return getMongo().findOne(new Query(new Criteria("username").is(username)), getEntityClass());
    }

    public User findByUsernameLowerCase(String username) {
        return getMongo().findOne(new Query(new Criteria("username").regex("^" + username + "$", "i")), getEntityClass());
    }

    /**
     * lấy danh sách người dùng theo list id
     *
     * @param ids
     * @return
     */
    public List<User> get(List<String> ids) {
        Query query = new Query(new Criteria("id").in(ids));
        return ListUtils.orderByArray(getMongo().find(query, getEntityClass()), ids);
    }

    public User addContact() {
        Criteria cri = new Criteria();
        cri.and("pushC").ne(true);
        return getMongo().findAndModify(new Query(cri),
                new Update().set("pushC", true), new FindAndModifyOptions().returnNew(true), getEntityClass());
    }

}
