package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.SellerApi;

@Repository
public class SellerApiRepository extends BaseRepository<SellerApi> {

    public SellerApiRepository() {
        super(SellerApi.class);
    }

    /**
     * Kiểm tra mã code user
     *
     * @param code
     * @param userId
     * @return
     */
    public SellerApi find(String code, String userId) {
        Criteria cri = new Criteria();
        cri.and("code").is(code);
        if (userId != null && !userId.equals("")) {
            cri.and("userId").is(userId);
        }
        return getMongo().findOne(new Query(cri), getEntityClass());
    }

    public SellerApi findByUserId(String userId) {
        Criteria cri = new Criteria();
        cri.and("userId").is(userId);
        return getMongo().findOne(new Query(cri), getEntityClass());
    }

    /**
     * Lấy ra danh sách người dùng có mã API
     *
     * @param userIds
     * @return
     */
    public List<SellerApi> find(List<String> userIds) {
        Criteria cri = new Criteria();
        if (userIds != null && !userIds.isEmpty()) {
            cri.and("userId").in(userIds);
        }
        return getMongo().find(new Query(cri), getEntityClass());
    }

}
