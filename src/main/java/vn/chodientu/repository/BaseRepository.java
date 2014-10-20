package vn.chodientu.repository;

import com.mongodb.WriteConcern;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Nguồn dữ liệu mẫu
 *
 * @author Phu
 * @since Jun 14, 2013
 */
public abstract class BaseRepository<E> {

    @Autowired
     MongoTemplate mongoTemplate;
    private Class<E> entityClass;

    public BaseRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Lấy về đối tượng database để thực hiện các hành động
     *
     * @return Đối tượng database
     */
    protected MongoTemplate getMongo() {
        return mongoTemplate;
    }

    /**
     * Lấy về entity class để dùng trong các query
     *
     * @return Entity class
     */
    protected Class<E> getEntityClass() {
        return entityClass;
    }

    /**
     * Random id cho đối tượng
     *
     * @return
     */
    public String genId() {
        String id = "";
        do {
            id = RandomStringUtils.randomNumeric(12);
            while (id.startsWith("0")) {
                id = id.substring(1);
            }
        } while (this.exists(id));
        return id;
    }

    /**
     * Lưu object vào database, nếu không có id hệ thống sẽ tự random id
     *
     * @param object object cần lưu
     */
    public <S extends E> void save(S object) {
        mongoTemplate.save(object);
    }

    /**
     * Tìm object theo id
     *
     * @param id Id cần tìm
     * @return Object có id tương ứng hoặc null nếu không tồn tại
     */
    public E find(String id) {
        return mongoTemplate.findById(id, entityClass);
    }

    /**
     * Kiểm tra object có tồn tại không
     *
     * @param id Id cần kiểm tra
     * @return True / false: có tồn tại hay không
     */
    public boolean exists(String id) {
        return this.find(id) != null;
    }

    /**
     * Đếm tổng số object trong collection
     *
     * @return Tổng số object
     */
    public long count() {
        return mongoTemplate.count(new Query(), entityClass);
    }

    public long total(Criteria cri) {
        return mongoTemplate.count(new Query(cri), entityClass);
    }

    /**
     * Tìm kiếm theo query
     *
     * @param query
     * @return
     */
    public List<E> find(Query query) {
        return getMongo().find(query, entityClass);
    }

    /**
     * Đếm theo query
     *
     * @param query
     * @return
     */
    public long count(Query query) {
        return mongoTemplate.count(query, entityClass);
    }

    /**
     * Xóa object theo id
     *
     * @param id Id cần xóa
     */
    public void delete(String id) {
        mongoTemplate.remove(new Query(new Criteria("id").is(id)), entityClass);
    }

    /**
     * Xóa theo query
     *
     * @param query
     */
    public void delete(Query query) {
        mongoTemplate.remove(query, entityClass);
    }

    /**
     * Xóa object
     *
     * @param entity object cần xóa
     */
    public void delete(E entity) {
        mongoTemplate.remove(entity);
    }
}
