package vn.chodientu.repository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Footerkeyword;

/**
 * @since Aug 25, 2014
 * @author Phuongdt
 */
@Repository
public class FooterKeywordRepository extends BaseRepository<Footerkeyword> {

    public FooterKeywordRepository() {
        super(Footerkeyword.class);
    }

    /**
     * Kiểm tra sự tồn tại của từ khóa
     *
     * @param keyword
     * @return
     */
    public boolean checkKeyword(String keyword) {
        return getMongo().count(new Query(new Criteria("keyword").is(keyword)), getEntityClass()) > 0;
    }
}
