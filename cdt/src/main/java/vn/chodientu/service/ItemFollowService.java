package vn.chodientu.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemFollow;
import vn.chodientu.entity.input.UserItemFollowSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.ItemFollowRepository;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.util.TextUtils;
import vn.chodientu.util.UrlUtils;

@Service
public class ItemFollowService {
    
    @Autowired
    private ItemFollowRepository itemFollowRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private Viewer viewer;

    /**
     * Đếm số quan tâm theo sản phẩm
     *
     * @param itemId
     * @return
     */
    public long countByItem(String itemId) {
        return itemFollowRepository.countByItem(itemId);
    }

    /**
     * hành động quan tâm hoặc bỏ quan tâm
     *
     * @param itemId
     * @param request
     * @return
     * @throws Exception
     */
    public long action(String itemId, HttpServletRequest request) throws Exception {
        Item item = itemRepository.find(itemId);
        if (item == null) {
            throw new Exception("Sản phẩm không tồn tại trên hệ thống ChoDienTu");
        }
        if (viewer.getUser() == null) {
            throw new Exception("Bạn cần <a href='javascript:;' onclick=\"auth.login('" + UrlUtils.item(item.getId(), item.getName()) + "')\" >đăng nhập</a> để thực hiện thao tác này ");
        }
        ItemFollow interest = itemFollowRepository.find(itemId, viewer.getUser().getId());
        if (interest == null) {
            interest = new ItemFollow();
            interest.setId(itemFollowRepository.genId());
            interest.setActive(true);
            interest.setCreateTime(System.currentTimeMillis());
            interest.setSellerId(item.getSellerId());
            interest.setUserId(viewer.getUser().getId());
            interest.setItemId(item.getId());
            interest.setItemName(item.getName());
        } else {
            interest.setActive(!interest.isActive());
        }
        interest.setUpdateTime(System.currentTimeMillis());
        interest.setIp(TextUtils.getClientIpAddr(request));
        itemFollowRepository.save(interest);
        item.setFollow(this.countByItem(itemId));
        itemRepository.save(item);
        return item.getFollow();
    }

    /**
     * Xóa sản phẩm theo dõi theo danh sách id
     *
     * @param ids
     */
    public void remove(List<String> ids) {
        List<Item> items = itemRepository.find(new Query(new Criteria("_id").in(ids)));
        for (Item item : items) {
            item.setFollow(this.countByItem(item.getId()));
            itemRepository.save(item);
        }
        itemFollowRepository.remove(ids, viewer.getUser().getId());
    }

    /**
     * Ghi chú
     *
     * @param id
     * @param note
     * @return
     * @throws Exception
     */
    public ItemFollow note(String id, String note) throws Exception {
        ItemFollow interest = itemFollowRepository.find(id);
        if (interest == null) {
            throw new Exception("Không tìm thấy sản phẩm quan tâm bạn yêu cầu");
        }
        interest.setNote(note);
        interest.setUpdateTime(System.currentTimeMillis());
        itemFollowRepository.save(interest);
        return interest;
    }

    /**
     * Tìm kiếm sản phẩm đang quan tâm của người dùng
     *
     * @param search
     * @return
     */
    public DataPage<ItemFollow> search(UserItemFollowSearch search) {
        DataPage<ItemFollow> dataPage = new DataPage<>();
        Criteria cri = new Criteria();
        if (search.getUserId() != null && !search.getUserId().equals("")) {
            cri.and("userId").is(search.getUserId());
        }
        if (search.getKeyword() != null && !search.getKeyword().equals("")) {
            cri.and("itemName").regex(search.getKeyword(), "i");
        }
        
        List<ItemFollow> list = itemFollowRepository.getList(cri, new PageRequest(search.getPageIndex(), search.getPageSize()));
        dataPage.setPageIndex(search.getPageIndex());
        dataPage.setPageSize(search.getPageSize());
        dataPage.setPageCount(dataPage.getPageCount());
        dataPage.setDataCount(itemFollowRepository.count(new Query(cri)));
        dataPage.setData(list);
        dataPage.setPageCount(dataPage.getDataCount() / dataPage.getPageSize());
        if (dataPage.getDataCount() % dataPage.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        return dataPage;
    }
    
}
