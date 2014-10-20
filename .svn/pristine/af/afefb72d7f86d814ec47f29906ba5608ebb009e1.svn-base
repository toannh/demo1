package vn.chodientu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.UserLock;
import vn.chodientu.entity.input.UserLockSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.repository.UserLockRepository;

/**
 * @since May 15, 2014
 * @author Phu
 */
@Service
public class UserLockService {

    @Autowired
    private UserLockRepository userLockRepository;

    /**
     * Tìm kiếm userlock trong trang tất cả userlock
     *
     * @param search
     * @return
     */
    public DataPage<UserLock> search(UserLockSearch search) {
        Criteria criteria = new Criteria();
        if (search.getRun() > 0) {
            criteria.and("run").is(search.getRun() == 1);
        }
        if (search.getDone() > 0) {
            criteria.and("done").is(search.getDone() == 1);
        }
        if (search.getUserId() != null && !search.getUserId().equals("")) {
            criteria.and("userId").is(search.getUserId());
        }

        if (search.getCreateTimeFrom() > 0 && search.getCreateTimeTo() > 0) {
            criteria.and("createTime").gte(search.getCreateTimeFrom()).lt(search.getCreateTimeTo());
        } else if (search.getCreateTimeFrom() > 0) {
            criteria.and("createTime").gte(search.getCreateTimeFrom());
        } else if (search.getCreateTimeTo() > 0) {
            criteria.and("createTime").lt(search.getCreateTimeTo());
        }

        if (search.getUpdateTimeFrom() > 0 && search.getUpdateTimeTo() > 0) {
            criteria.and("updateTime").gte(search.getUpdateTimeTo()).lt(search.getUpdateTimeTo());
        } else if (search.getUpdateTimeTo() > 0) {
            criteria.and("updateTime").gte(search.getUpdateTimeTo());
        } else if (search.getUpdateTimeTo() > 0) {
            criteria.and("updateTime").lt(search.getUpdateTimeTo());
        }

        if (search.getStartTime() > 0 && search.getEndTime() > 0) {
            criteria.and("startTime").gte(search.getStartTime());
            criteria.and("endTime").lte(search.getEndTime());
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "createTime"));
        query.skip(search.getPageIndex() * search.getPageSize()).limit(search.getPageSize());

        DataPage<UserLock> page = new DataPage<>();
        page.setPageSize(search.getPageSize());
        page.setPageIndex(search.getPageIndex());
        if (page.getPageSize() <= 0) {
            page.setPageSize(5);
        }
        page.setDataCount(userLockRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }

        page.setData(userLockRepository.find(query));
        return page;
    }
    

    /**
     * Bỏ khóa user
     *
     * @param id
     * @return
     * @throws Exception
     */
    public UserLock stopRun(String id) throws Exception {
        UserLock find = userLockRepository.find(id);
        if (find == null) {
            throw new Exception("Không tìm thấy user nào!");
        }
        if (find.isDone()) {
            throw new Exception("User này không bị khóa!");
        }
        find.setDone(true);
        find.setRun(false);
        find.setUpdateTime(System.currentTimeMillis());
        userLockRepository.save(find);
        return find;
    }

    public UserLock getLockIsRunByUserId(String id) {
        return userLockRepository.getLockIsRunByUserId(id);
    }

}
