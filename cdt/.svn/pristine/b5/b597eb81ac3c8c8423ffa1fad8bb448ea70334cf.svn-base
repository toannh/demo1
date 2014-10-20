package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Message;

@Repository
public class MessageRepository extends BaseRepository<Message> {

    public MessageRepository() {
        super(Message.class);
    }

    /**
     * Đọc tin nhắn
     *
     * @param id
     * @param ip
     * @return
     */
    public Message findMessage(String id, String ip) {
        return getMongo().findAndModify(new Query(new Criteria("_id").is(id))
                .with(new Sort(Sort.Direction.DESC, "time")),
                new Update().set("read", true)
                .set("updateTime", System.currentTimeMillis())
                .set("lastView", System.currentTimeMillis())
                .set("lastIp", ip),
                new FindAndModifyOptions().returnNew(true), getEntityClass());
    }

    /**
     * Cập nhật trạng thái chưa đọc
     *
     * @param ids
     * @param ip
     * @return
     */
    public List<Message> markedUnread(List<String> ids, String ip) {
        getMongo().updateMulti(new Query(new Criteria("_id").in(ids)),
                new Update().set("read", false)
                .set("updateTime", System.currentTimeMillis())
                .set("lastView", System.currentTimeMillis())
                .set("lastIp", ip), getEntityClass());
        return getMongo().find(new Query(new Criteria("_id").in(ids)).with(new Sort(Sort.Direction.DESC, "updateTime")), getEntityClass());
    }

    /**
     * Xóa logic
     *
     * @param ids
     * @param ip
     */
    public void delete(List<String> ids, String ip) {
        getMongo().updateMulti(new Query(new Criteria("_id").in(ids)),
                new Update().set("remove", true)
                .set("updateTime", System.currentTimeMillis())
                .set("lastView", System.currentTimeMillis())
                .set("lastIp", ip), getEntityClass());
    }

    /**
     * Xóa khỏi database
     *
     * @param ids
     */
    public void delete(List<String> ids) {
        delete(new Query(new Criteria("_id").in(ids)));
    }

}
