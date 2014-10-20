package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.SellerSmsMarketing;

@Repository
public class SellerSmsMarketingRepository extends BaseRepository<SellerSmsMarketing> {

    public SellerSmsMarketingRepository() {
        super(SellerSmsMarketing.class);
    }

    /**
     * Lấy theo phone template để gửi theo thời gian
     *
     * @where chưa chạy, được admin duyệt, nằm trong thời gian yêu cầu
     * @update lấy ra thì tự động chuyển thành đã chạy
     * @param startTime
     * @param endTime
     * @return
     */
    public SellerSmsMarketing getSmsMarketing(long startTime, long endTime) {
        Criteria cri = new Criteria();
        cri.and("run").is(false);
        cri.and("active").is(true);
        cri.and("sendTime").gt(startTime).lte(endTime);
        Update update = new Update();
        update.set("run", true);
        update.set("done", true);
        update.set("updateTime", System.currentTimeMillis());
        return getMongo().findAndModify(new Query(cri).with(new Sort(Sort.Direction.DESC, "updateTime")), update, new FindAndModifyOptions().returnNew(true), getEntityClass());
    }

    /**
     * Danh sách SellerSmsMarketing đủ điều kiện
     *
     * @param sellerId
     * @run false
     * @done false
     * @sendTime lớn hơn thời gian hiện tại
     * @return List<SellerSmsMarketing>
     */
    public List<SellerSmsMarketing> smsVerified(String sellerId) {
        Criteria cri = new Criteria();
        cri.and("run").is(false);
        cri.and("done").is(false);
        cri.and("sellerId").is(sellerId);
        cri.and("sendTime").gt(System.currentTimeMillis());
        return getMongo().find(new Query(cri).with(new Sort(Sort.Direction.DESC, "updateTime")), getEntityClass());

    }
}
