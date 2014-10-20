package vn.chodientu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.entity.db.HotdealCategory;
import vn.chodientu.entity.db.HotdealItem;
import vn.chodientu.entity.db.Image;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.HotdealCategoryRepository;
import vn.chodientu.repository.HotdealItemRepository;
import vn.chodientu.repository.ItemRepository;

/**
 * @since Jul 14, 2014
 * @author Account
 */
@Service
public class HotdealService {

    @Autowired
    private HotdealCategoryRepository hotdealCategoryRepository;
    @Autowired
    private HotdealItemRepository hotdealItemRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private ImageService imageService;

    /**
     * Lấy danh sách danh mục
     *
     * @param parentId
     * @param active
     * @return
     */
    public List<HotdealCategory> getCategories(String parentId, int active) {
        List<HotdealCategory> hotdealCategories = hotdealCategoryRepository.getChilds(parentId, active);
        return hotdealCategories;
    }

    /**
     * Lấy danh sách danh mục
     *
     * @param active
     * @return
     */
    public List<HotdealCategory> getAll(int active) {
        List<HotdealCategory> hotdealCategories = hotdealCategoryRepository.getAll(active);
        return hotdealCategories;
    }

    /**
     * Lấy 1 danh mục
     *
     * @param id
     * @return
     */
    public HotdealCategory getCategory(String id) {
        HotdealCategory hotdealCategory = hotdealCategoryRepository.find(id);
        return hotdealCategory;
    }

    /**
     * Lấy danh sách sản phẩm
     *
     * @param cateId
     * @param page
     * @return
     */
    public DataPage<HotdealItem> getByCategory(String cateId, Pageable page) {

        DataPage<HotdealItem> dataPage = new DataPage<>();

        List<HotdealItem> hotdealItems = hotdealItemRepository.getByCategory(cateId, page);
        List<String> ids = new ArrayList<>();
        for (HotdealItem hi : hotdealItems) {
            ids.add(hi.getItemId());
        }
        List<Item> listItems = itemRepository.get(ids, 0);
        for (Item item : listItems) {
            List<String> image = imageService.get(ImageType.ITEM, item.getId());
            List<String> images = new ArrayList<>();
            if (image != null && !image.isEmpty()) {
                for (String img : image) {
                    ImageUrl url = imageService.getUrl(img).thumbnail(350, 350, "inset");
                    if (url != null) {
                        images.add(url.compress(100).getUrl());
                    }
                }
            }
            item.setImages(images);
            for (HotdealItem hItems : hotdealItems) {
                if (hItems.getItemId().equals(item.getId())) {
                    hItems.setItem(item);
                }
            }
        }

        dataPage.setData(hotdealItems);
        dataPage.setDataCount(hotdealItemRepository.countByCategory(cateId));
        if (page != null) {
            dataPage.setPageIndex(page.getPageNumber());
            dataPage.setPageSize(page.getPageSize());
            dataPage.setPageCount(page.getPageSize() > 0 ? (dataPage.getDataCount() / dataPage.getPageSize()) : 0);
            if (dataPage.getPageSize() > 0 && dataPage.getDataCount() % dataPage.getPageSize() != 0) {
                dataPage.setPageCount(dataPage.getPageCount() + 1);
            }
        }
        return dataPage;
    }

    /**
     * Thêm mới danh mục hotdeal
     *
     * @param hotdealCategory
     * @return
     */
    public Response addCategory(HotdealCategory hotdealCategory) {
        Map<String, String> error = validator.validate(hotdealCategory);
        HotdealCategory old = null;
        if (hotdealCategory.getId() == null || hotdealCategory.getId().equals("")) {
            hotdealCategory.setId(hotdealCategoryRepository.genId());
            hotdealCategory.setCreateTime(System.currentTimeMillis());
            hotdealCategory.setLeaf(true);
        } else {
            old = hotdealCategoryRepository.find(hotdealCategory.getId());
            hotdealCategory.setCreateTime(old.getCreateTime());
        }
        if (error.isEmpty()) {
            if (hotdealCategory.getStartTime() <= 0) {
                hotdealCategory.setStartTime(System.currentTimeMillis());
            }
            if (hotdealCategory.getEndTime() <= 0) {
                hotdealCategory.setEndTime(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000l);
            }
            if (hotdealCategory.getParentId() != null && !hotdealCategory.getParentId().equals("")) {
                HotdealCategory parent = hotdealCategoryRepository.find(hotdealCategory.getParentId());
                if (parent != null) {
                    parent.setLeaf(false);
                    hotdealCategoryRepository.save(parent);
                }
            } else {
                hotdealCategory.setParentId(null);
                if (old != null && old.getParentId() != null && !old.getParentId().equals("")) {
                    long countChilds = hotdealCategoryRepository.countChilds(old.getParentId(), 0);
                    if (countChilds <= 0) {
                        HotdealCategory oldParent = hotdealCategoryRepository.find(old.getParentId());
                        if (oldParent != null) {
                            oldParent.setLeaf(true);
                            hotdealCategoryRepository.save(oldParent);
                        }
                    }
                }
            }
            hotdealCategoryRepository.save(hotdealCategory);
            return new Response(true, "Thêm/Sửa danh mục hotdeal thành công");
        }
        return new Response(false, "Thêm/Sửa danh mục hotdeal thất bại", error);
    }

    /**
     * Thêm mới sản phẩm
     *
     * @param hotdealItem
     * @return
     */
    public Response addItem(HotdealItem hotdealItem) {
        Map<String, String> error = validator.validate(hotdealItem);
        HotdealCategory hotdealCategory = hotdealCategoryRepository.find(hotdealItem.getHotdealCategoryId());
        if (hotdealCategory == null) {
            error.put("hotdealCategoryId", "Danh mục hotdeal không tồn tại");
        }
        Item item = itemRepository.find(hotdealItem.getItemId());
        if (item == null) {
            error.put("itemId", "Sản phẩm chưa tồn tại");
        }

        if (error.isEmpty()) {
            HotdealItem hi = hotdealItemRepository.getByItem(hotdealItem.getItemId(),hotdealItem.isHome());
            if (hi == null) {
                hotdealItem.setId(hotdealItemRepository.genId());
            } else {
                hotdealItem.setId(hi.getId());
            }
            hotdealItem.setHotdealCategoryPath(new ArrayList<String>());
            if (hotdealCategory.getParentId() != null && !hotdealCategory.getParentId().equals("")) {
                hotdealItem.getHotdealCategoryPath().add(hotdealCategory.getParentId());
            }
            hotdealItem.getHotdealCategoryPath().add(hotdealItem.getHotdealCategoryId());
            hotdealItemRepository.save(hotdealItem);

            Image firstImage = imageService.getFirstImage(item.getId(), ImageType.ITEM);
            if (firstImage != null) {
                ImageUrl url = imageService.getUrl(firstImage.getImageId());
                if (url != null) {
                    item.setImages(new ArrayList<String>());
                    item.getImages().add(firstImage.getImageId());
                    hotdealItem.setImage(url.getUrl());
                    imageService.delete(ImageType.HOTDEAL, hotdealItem.getId());
                    imageService.download(url.getUrl(), ImageType.HOTDEAL, hotdealItem.getId());
                }
            }
            hotdealItem.setItem(item);
            return new Response(true, "Thêm sản phẩm hotdeal thành công", hotdealItem);
        }
        return new Response(false, "Thêm sản phẩm hotdeal thất bại", error);
    }

    /**
     * Xóa sản phẩm
     *
     * @param id
     * @return
     */
    public Response deleteItem(String id) {
        hotdealItemRepository.delete(id);
        imageService.delete(ImageType.HOTDEAL, id);
        return new Response(true, "Xóa sản phẩm thành công");
    }

    /**
     * Xóa sản phẩm
     *
     * @param id
     * @return
     */
    public Response deleteCategory(String id) {
        HotdealCategory category = hotdealCategoryRepository.find(id);
        if (category != null) {
            long countByCategory = hotdealItemRepository.countByCategory(id);
            if (countByCategory <= 0) {
                hotdealCategoryRepository.delete(id);
                hotdealItemRepository.removeByCategory(id);
                if (category.getParentId() != null && !category.getParentId().equals("")) {
                    HotdealCategory oldParent = hotdealCategoryRepository.find(category.getParentId());
                    long countChilds = hotdealCategoryRepository.countChilds(category.getParentId(), 1);
                    if (countChilds <= 0) {
                        if (oldParent != null) {
                            oldParent.setLeaf(true);
                            hotdealCategoryRepository.save(oldParent);
                        }
                    }
                }
                return new Response(true, "Xóa danh mục thành công");
            } else {
                return new Response(false, "Có sản phẩm trong danh mục.Bạn phải xóa sản phẩm trước");
            }

        } else {
            return new Response(true, "Danh mục không tồn tại");
        }
    }

    public List<HotdealItem> getHomeByCategory(String id) {
        List<HotdealItem> hotdealItems = hotdealItemRepository.getHomeByCategory(id);
        List<String> ids = new ArrayList<>();
        for (HotdealItem hi : hotdealItems) {
            ids.add(hi.getItemId());
        }
        List<Item> listItems = itemRepository.get(ids, 0);
        for (HotdealItem hItems : hotdealItems) {
            for (Item item : listItems) {
                if (hItems.getItemId().equals(item.getId())) {
                    if (hItems.isSpecial()) {
                        List<String> image = imageService.get(ImageType.ITEM, item.getId());
                        List<String> images = new ArrayList<>();
                        if (image != null && !image.isEmpty()) {
                            for (String img : image) {
                                ImageUrl url = imageService.getUrl(img).thumbnail(350, 350, "inset");
                                if (url != null) {
                                    images.add(url.compress(100).getUrl());
                                }
                            }
                        }
                        item.setImages(images);
                    }
                    hItems.setItem(item);
                    break;
                }
            }
            Image lastImage = imageService.getLastImage(hItems.getId(),ImageType.HOTDEAL);
            if (lastImage != null) {
                ImageUrl url = imageService.getUrl(lastImage.getImageId()).thumbnail(hItems.isSpecial() ? 446 : 350, hItems.isSpecial() ? 446 : 350, "inset");
                hItems.setImage(url.compress(100).getUrl());
            }
        }
        return hotdealItems;
    }

    public Response changeItemSpecial(String id) {
        HotdealItem hotdealItem = hotdealItemRepository.find(id);
        if (hotdealItem != null) {
            List<HotdealItem> homeByCategory = hotdealItemRepository.getHomeByCategory(hotdealItem.getHotdealCategoryId());
            for (HotdealItem hitem : homeByCategory) {
                if (hitem.isSpecial()) {
                    hitem.setSpecial(false);
                    hotdealItemRepository.save(hitem);
                    break;
                }
            }
            hotdealItem.setSpecial(true);
            hotdealItemRepository.save(hotdealItem);
            return new Response(true, "Thành công");
        } else {
            return new Response(false, "Sản phẩm không tồn tại");
        }
    }

}
