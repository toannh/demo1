package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Image;
import vn.chodientu.entity.enu.ImageType;

/**
 * @since May 28, 2014
 * @author Phu
 */
@Repository
public class ImageRepository extends BaseRepository<Image> {

    public ImageRepository() {
        super(Image.class);
    }

    public int getLastPosition(ImageType type, String targetId) {
        Image img = getMongo().findOne(new Query(new Criteria("type").is(type).and("targetId").is(targetId)).with(new Sort(Sort.Direction.DESC, "position")), getEntityClass());
        return img == null ? 0 : img.getPosition() + 1;
    }

    /**
     * Lấy ảnh theo đối tượng
     *
     * @param type
     * @param targetId
     * @return
     */
    public List<Image> get(ImageType type, String targetId) {
        return find(new Query(new Criteria("type").is(type).and("targetId").is(targetId)).with(new Sort(Sort.Direction.ASC, "position")));
    }

    /**
     * Lấy ảnh theo nhiều đối tượng
     *
     * @param type
     * @param targetIds
     * @return
     */
    public List<Image> get(ImageType type, List<String> targetIds) {
        return find(new Query(new Criteria("type").is(type).and("targetId").in(targetIds)).with(new Sort(Sort.Direction.ASC, "position")));
    }

    /**
     * Xóa ảnh theo đối tượng đâu
     *
     * @param type
     * @param targetId
     */
    public void delete(ImageType type, String targetId) {
        delete(new Query(new Criteria("type").is(type).and("targetId").is(targetId)));
    }

    /**
     * Lấy ảnh theo đối tượng và mã ảnh
     *
     * @param type
     * @param targetId
     * @param imageId
     */
    public void delete(ImageType type, String targetId, String imageId) {
        getMongo().remove(new Query(new Criteria("type").is(type).and("targetId").is(targetId).and("imageId").is(imageId)).with(new Sort(Sort.Direction.ASC, "position")).limit(1), getEntityClass());
    }

    /**
     * Đếm số ảnh theo id
     *
     * @param imageId
     * @return
     */
    public long count(String imageId) {
        return count(new Query(new Criteria("imageId").is(imageId)));
    }

    /**
     * Hàm list tất cả ảnh theo type
     *
     * @param type
     */
    public List<Image> list(String type) {
        Criteria criteria = new Criteria();
        criteria.and("type").is(type);
        return getMongo().find(new Query(criteria), getEntityClass());
    }

    /**
     * Lấy ảnh theo đối tượng và mã ảnh và kiểu ảnh
     *
     * @param type
     * @param targetId
     * @param imageId
     * @return
     */
    public Image get(ImageType type, String targetId, String imageId) {
        return getMongo().findOne(new Query(new Criteria("type").is(type).and("targetId").is(targetId).and("imageId").is(imageId)).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }

    /**
     * Lấy ảnh có vị trí đầu tiên
     *
     * @param type
     * @param targetId
     * @return
     */
    public Image getFirstImage(ImageType type, String targetId) {
        Image img = getMongo().findOne(new Query(new Criteria("type").is(type).and("targetId").is(targetId)).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
        return img;
    }
    /**
     * Lấy ảnh có vị trí lớn nhất(cuối cùng)
     *
     * @param type
     * @param targetId
     * @return
     */
    public Image getLastImage(ImageType type, String targetId) {
        Image img = getMongo().findOne(new Query(new Criteria("type").is(type).and("targetId").is(targetId)).with(new Sort(Sort.Direction.DESC, "position")), getEntityClass());
        return img;
    }
}
