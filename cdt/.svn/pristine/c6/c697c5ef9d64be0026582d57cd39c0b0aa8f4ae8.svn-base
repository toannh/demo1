package vn.chodientu.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemHistory;
import vn.chodientu.entity.enu.ItemHistoryType;
import vn.chodientu.entity.input.ItemHistorySearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.ItemHistoryRepository;
import vn.chodientu.util.TextUtils;

@Service
public class ItemHistoryService {

    @Autowired
    private ItemHistoryRepository itemHistoryRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private Viewer viewer;

    /**
     * Log sản phẩm
     *
     * @param item
     * @param admin
     * @param type
     */
    public void create(Item item, boolean admin, ItemHistoryType type) {
        try {
            ItemHistory itemHistory = new ItemHistory();
            itemHistory.setItemId(item.getId());
            itemHistory.setItemName(item.getName());
            itemHistory.setCreateTime(System.currentTimeMillis());
            if (viewer.getAdministrator() != null) {
                itemHistory.setAdminEmail(viewer.getAdministrator().getEmail());
            }
            itemHistory.setAdmin(admin);
            itemHistory.setIp(TextUtils.getClientIpAddr(request));
            itemHistory.setItemHistoryType(type);
            itemHistory.setSellerId(item.getSellerId());
            itemHistory.setSellerName(item.getSellerName());
            itemHistory.setSellerSku(item.getSellerSku());
            itemHistory.setSource(item.getSource());
            itemHistoryRepository.save(itemHistory);
        } catch (Exception e) {
        }
    }

    /**
     * Tìm kiếm log
     *
     * @param search
     * @return
     */
    public DataPage<ItemHistory> search(ItemHistorySearch search) {
        DataPage<ItemHistory> dataPage = new DataPage<>();
        Criteria cri = new Criteria();
        if (search.getItemId() != null && !search.getItemId().equals("")) {
            cri.and("itemId").is(search.getItemId());
        }

        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Query query = new Query(cri);
        dataPage.setDataCount(itemHistoryRepository.count(query));
        dataPage.setPageIndex(search.getPageIndex());
        dataPage.setPageSize(search.getPageSize());
        dataPage.setPageCount(dataPage.getPageCount());

        query.with(new PageRequest(search.getPageIndex(), search.getPageSize(), sort));
        dataPage.setData(itemHistoryRepository.find(query));
        dataPage.setPageCount(dataPage.getDataCount() / dataPage.getPageSize());
        if (dataPage.getDataCount() % dataPage.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        return dataPage;
    }

}
