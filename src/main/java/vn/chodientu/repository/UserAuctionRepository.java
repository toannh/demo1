package vn.chodientu.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Bid;
import vn.chodientu.entity.db.UserAuction;
import vn.chodientu.util.ListUtils;

/**
 *
 * @author Phu
 */
@Repository
public class UserAuctionRepository extends BaseRepository<UserAuction> {

    public UserAuctionRepository() {
        super(UserAuction.class);
    }

    /**
     * Lấy item đấu giá của người dùng
     *
     * @param cri
     * @param pageable
     * @return
     */
    public List<UserAuction> getList(Criteria cri, Pageable pageable, int orderBy) {
        Query query = new Query(cri).with(pageable);
        if (orderBy == 1) {
            query.with(new Sort(Sort.Direction.DESC, "endTime"));
        } else if (orderBy == 2) {
            query.with(new Sort(Sort.Direction.DESC, "bidTime"));
        } else {
            query.with(new Sort(Sort.Direction.ASC, "endTime"));
        }
        return getMongo().find(query, getEntityClass());
    }

    /**
     *
     * @param userId
     * @param itemId
     * @return
     */
    public UserAuction getItemAution(String userId, String itemId) {
        return getMongo().findOne(new Query(new Criteria("userId").is(userId).and("itemId").is(itemId)), getEntityClass());
    }

    public List<UserAuction> getList(List<String> userItemIds) {
        List<UserAuction> list = getMongo().find(new Query(new Criteria("id").in(userItemIds)), getEntityClass());
        return ListUtils.orderByArray(list, userItemIds);
    }

    /**
     * Đếm số lượng người đấu giá theo sản phẩm
     *
     * @param itemId
     * @return
     */
    public long count(String itemId) {
        return getMongo().count(new Query(new Criteria("itemId").is(itemId)), getEntityClass());
    }

    public UserAuction getEndBid() {
        return getMongo().findAndModify(new Query(new Criteria("endTime").lt(System.currentTimeMillis()).and("complete").is(false)),
                new Update().update("complete", true), new FindAndModifyOptions().returnNew(true), getEntityClass());
    }
}
