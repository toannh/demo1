package vn.chodientu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ShopHomeItem;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.input.ShopHomeItemSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.ShopHomeItemRepository;

@Service
public class ShopHomeItemService {

    @Autowired
    private ShopHomeItemRepository shopHomeItemRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private Validator validator;

    /**
     * Danh sách box sản phẩm trang chủ
     *
     * @param search
     * @return
     */
    public DataPage<ShopHomeItem> search(ShopHomeItemSearch search) {
        Criteria criteria = new Criteria();
        criteria.and("sellerId").is(search.getSellerId());

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.ASC, "position"));
        query.skip(search.getPageIndex() * search.getPageSize()).limit(search.getPageSize());
        DataPage<ShopHomeItem> page = new DataPage<>();
        page.setPageSize(search.getPageSize());
        page.setPageIndex(search.getPageIndex());
        if (page.getPageSize() <= 0) {
            page.setPageSize(5);
        }
        page.setDataCount(shopHomeItemRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }
        page.setData(shopHomeItemRepository.find(query));
        return page;
    }

    /**
     * Thêm mới box sản phẩm
     *
     * @param form
     * @param seller
     * @return
     */
    public Response add(ShopHomeItem form, User seller) {
        Map<String, String> error = validator.validate(form);
        if (!error.isEmpty()) {
            return new Response(false, "Dữ liệu chưa chính xác, không thể thêm mới box sản phẩm", error);
        }

        if (form.getItemIds() == null || form.getItemIds().isEmpty()) {
            form.setActive(false);
        }
        form.setItemIds(convertItemIds(form.getItemIds(), seller));
        form.setSellerId(seller.getId());
        form.setPosition(form.getPosition() > 0 ? form.getPosition() : 1);
        shopHomeItemRepository.save(form);
        return new Response(true, "Thêm mới thành công box sản phẩm", form);
    }

    /**
     * Cập nhật thông tin shop
     *
     * @param form
     * @param seller
     * @return
     */
    public Response edit(ShopHomeItem form, User seller) {
        ShopHomeItem shopHomeItem = shopHomeItemRepository.find(form.getId());
        if (shopHomeItem == null) {
            return new Response(false, "Không tìm thấy box sản phẩm yêu cầu", form);
        }
        Map<String, String> error = validator.validate(form);
        if (!error.isEmpty()) {
            return new Response(false, "Dữ liệu chưa chính xác, không thể cập nhật box sản phẩm", error);
        }

        if (form.getItemIds() == null || form.getItemIds().isEmpty()) {
            form.setActive(false);
        }
        form.setItemIds(convertItemIds(form.getItemIds(), seller));
        
        shopHomeItem.setItemIds(null);
        shopHomeItem.setItemIds(form.getItemIds());
//        shopHomeItem.setActive(form.isActive());
        shopHomeItem.setIcon(form.getIcon());
        shopHomeItem.setName(form.getName());
        shopHomeItem.setPosition(form.getPosition() > 0 ? form.getPosition() : 1);
        shopHomeItemRepository.save(shopHomeItem);
        return new Response(true, "Cập nhật thành công box sản phẩm", form);
    }

    /**
     * Xóa box sản phẩm
     *
     * @param id
     * @param seller
     * @return
     */
    public Response remove(String id, User seller) {
        shopHomeItemRepository.delete(new Query(new Criteria("_id").is(id).and("sellerId").is(seller.getId())));
        return new Response(true, "Box sản phẩm đã được xóa thành công");
    }

    /**
     * Xóa theo nhóm
     *
     * @param ids
     * @param seller
     * @return
     */
    public Response remove(List<String> ids, User seller) {
        shopHomeItemRepository.delete(new Query(new Criteria("_id").in(ids).and("sellerId").is(seller.getId())));
        return new Response(true, "Box sản phẩm đã được xóa thành công");
    }

    /**
     * Change active
     *
     * @param id
     * @param seller
     * @return
     */
    public Response changeActive(String id, User seller) {
        ShopHomeItem box = shopHomeItemRepository.find(id);
        box.setActive(!box.isActive());
        shopHomeItemRepository.save(box);
        return new Response(true, "Box sản phẩm đã được thay đổi trạng thái", box);
    }

    private List<String> convertItemIds(List<String> is, User seller) {
        if (is == null || is.isEmpty()) {
            return null;
        }
        List<Item> items = itemRepository.get(is);
        is = new ArrayList<>();
        for (Item item : items) {
            if (!is.contains(item.getId().trim()) && item.getSellerId().equals(seller.getId())) {
                is.add(item.getId().trim());
            }
        }
        return is;
    }
    /**
     * List danh sách box sản phẩm nổi bật
     * @param sellerId
     * @return 
     */
    public List<ShopHomeItem> getAll(String sellerId) {
        return shopHomeItemRepository.list(sellerId);
    }
    
}
