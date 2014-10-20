package vn.chodientu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.ItemCrawl;
import vn.chodientu.entity.db.ItemFollow;
import vn.chodientu.entity.input.ItemCrawlSearch;
import vn.chodientu.entity.input.UserItemFollowSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.repository.ItemCrawlRepository;

@Service
public class ItemCrawlService {

    @Autowired
    private ItemCrawlRepository itemCrawlRepository;

    /**
     * Tìm kiếm sản phẩm đang quan tâm của người dùng
     *
     * @param search
     * @return
     */
    public DataPage<ItemCrawl> search(ItemCrawlSearch search) {
        Criteria cri = new Criteria();
        if (search.getItemId() != null && !search.getItemId().equals("")) {
            cri.and("itemId").is(search.getItemId());
        }
        if (search.getType() != null && !search.getType().equals("")) {
            cri.and("type").is(search.getType());
        }

        if (search.getCreateTimeFrom() > 0 && search.getCreateTimeTo() > 0) {
            cri.and("createTime").gte(search.getCreateTimeFrom()).lt(search.getCreateTimeTo());
        } else if (search.getCreateTimeFrom() > 0) {
            cri.and("createTime").gte(search.getCreateTimeFrom());
        } else if (search.getCreateTimeTo() > 0) {
            cri.and("createTime").lt(search.getCreateTimeTo());
        }

        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        Query query = new Query(cri);
        DataPage<ItemCrawl> dataPage = new DataPage<>();

        dataPage.setDataCount(itemCrawlRepository.count(query));
        dataPage.setPageIndex(search.getPageIndex());
        dataPage.setPageSize(search.getPageSize());
        dataPage.setPageCount(dataPage.getDataCount() / dataPage.getPageSize());
        if (dataPage.getDataCount() % dataPage.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        dataPage.setData(itemCrawlRepository.find(query.limit(search.getPageSize()).skip(search.getPageIndex() * search.getPageSize()).with(sort)));
        return dataPage;
    }
}
