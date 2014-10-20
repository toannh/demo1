package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ShopNews;

/**
 * @since Jun 2, 2014
 * @author Phu
 */
@Repository
public class ShopNewsRepository extends BaseRepository<ShopNews> {
    
    public ShopNewsRepository() {
        super(ShopNews.class);
    }

    /**
     * Lấy tin tức theo danh mục có phân trang
     *
     * @param categoryId
     * @param page
     * @param id
     * @param active
     * @return
     */
    public List<ShopNews> getByShopNewsCategory(String categoryId, Pageable page, String id, int active) {
        Criteria cri = new Criteria("categoryPath").is(categoryId).and("id").ne(id);
        if (active == 1) {
            cri.and("active").is(true);
        }
        return getMongo().find(new Query(cri).with(page).with(new Sort(Sort.Direction.DESC, "updateTime")), getEntityClass());
    }

    /**
     * Đếm số tin tức trong danh mục
     *
     * @param categoryId
     * @param active
     * @return
     */
    public long countShopNewsCategory(String categoryId, int active) {
        Criteria cri = new Criteria("categoryPath").is(categoryId);
        if (active == 1) {
            cri.and("active").is(true);
        }
        return getMongo().count(new Query(cri), getEntityClass());
    }

    /**
     * Lấy toàn bộ bài tin của shop có phân trang
     *
     * @param cri
     * @param pageSize
     * @param offset
     * @return
     */
    public List<ShopNews> list(String userId, int pageSize, int offset) {
        return getMongo().find(new Query(new Criteria("userId").is(userId)).limit(pageSize).skip(offset).with(new Sort(new Sort.Order(Sort.Direction.ASC, "createTime"))), getEntityClass());
    }
}
