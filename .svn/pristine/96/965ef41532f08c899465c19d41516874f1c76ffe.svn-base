package vn.chodientu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.entity.db.Image;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Landing;
import vn.chodientu.entity.db.LandingCategory;
import vn.chodientu.entity.db.LandingItem;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.LandingForm;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.LandingCategoryRepository;
import vn.chodientu.repository.LandingItemRepository;
import vn.chodientu.repository.LandingRepository;

/**
 * @since Jul 14, 2014
 * @author Account
 */
@Service
public class LandingService {

    @Autowired
    private LandingCategoryRepository landingCategoryRepository;
    @Autowired
    private LandingItemRepository landingItemRepository;
    @Autowired
    private LandingRepository landingRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private ImageService imageService;

    /**
     * Lấy danh sách danh mục
     *
     * @param landingId
     * @return
     */
    public List<LandingCategory> getCategories(String landingId) {
        List<LandingCategory> landingCategories = landingCategoryRepository.getByLanding(landingId);
        return landingCategories;
    }

    /**
     * Lấy danh sách danh mục
     *
     * @param landingId
     * @return
     */
    public Landing getLanding(String landingId) {
        Landing landing = landingRepository.find(landingId);
        if (landing != null) {
            List<String> logoImages = imageService.get(ImageType.LANDING_LOGO, landing.getId());
            if (logoImages != null && !logoImages.isEmpty()) {
                landing.setLogo(imageService.getUrl(logoImages.get(0)).compress(100).getUrl());
            }
            List<String> backgoundImages = imageService.get(ImageType.LANDING_BACKGROUND, landing.getId());
            if (backgoundImages != null && !backgoundImages.isEmpty()) {
                landing.setBackground(imageService.getUrl(backgoundImages.get(0)).compress(100).getUrl());
            }
        }
        return landing;
    }

    /**
     * Lấy danh sách danh mục
     *
     * @param categoryId
     * @return
     */
    public LandingCategory getCategory(String categoryId) {
        LandingCategory landingCategory = landingCategoryRepository.find(categoryId);
        return landingCategory;
    }

    /**
     * Lấy danh sách landing
     *
     * @param page
     * @return
     */
    public DataPage<Landing> getAll(Pageable page) {
        DataPage<Landing> dataPage = new DataPage<>();
        List<Landing> landings = landingRepository.getAll(page);
        for (Landing landing : landings) {
            List<String> logoImages = imageService.get(ImageType.LANDING_LOGO, landing.getId());
            if (logoImages != null && !logoImages.isEmpty()) {
                landing.setLogo(imageService.getUrl(logoImages.get(0)).compress(100).getUrl());
            }
        }
        dataPage.setData(landings);
        dataPage.setPageSize(page.getPageSize());
        dataPage.setPageIndex(page.getPageNumber());
        dataPage.setDataCount(landingRepository.count());
        dataPage.setPageCount(dataPage.getDataCount() / dataPage.getPageSize());
        if (dataPage.getDataCount() % dataPage.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }

        return dataPage;
    }

    /**
     * Lấy 1 danh mục
     *
     * @param landingCategoryId
     * @param page
     * @return
     */
    public DataPage<LandingItem> getItemByCategory(String landingCategoryId, Pageable page) {
        List<LandingItem> landingItems = landingItemRepository.getByLandingCategory(landingCategoryId, page, 0);
        DataPage<LandingItem> dataPage = new DataPage<>();

        List<String> ids = new ArrayList<>();
        for (LandingItem hi : landingItems) {
            ids.add(hi.getItemId());
        }
        List<Item> listItems = itemRepository.get(ids, 0);
        for (LandingItem landingItem : landingItems) {
            Image lastImage = imageService.getLastImage(landingItem.getId(), ImageType.LANDING);
            if (lastImage != null) {
                ImageUrl url = imageService.getUrl(lastImage.getImageId()).thumbnail(318, 318, "inset");
                landingItem.setImage(url.compress(100).getUrl());
            }
            for (Item item : listItems) {
                if (landingItem.getItemId().equals(item.getId())) {
                    landingItem.setItem(item);
                    break;
                }
            }
        }

        dataPage.setData(landingItems);

        dataPage.setDataCount(landingItemRepository.countByCategory(landingCategoryId));
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
     * Lấy sản phẩm trong landing
     *
     * @param cateIds
     * @return
     */
    public List<LandingItem> getItemByCategories(List<String> cateIds) {
        List<LandingItem> landingItems = landingItemRepository.getByCategories(cateIds, null, 1);

        List<String> ids = new ArrayList<>();
        for (LandingItem hi : landingItems) {
            ids.add(hi.getItemId());
        }
        List<Item> listItems = itemRepository.get(ids, 0);
        for (LandingItem landingItem : landingItems) {
            Image lastImage = imageService.getLastImage(landingItem.getId(), ImageType.LANDING);
            if (lastImage != null) {
                ImageUrl url = imageService.getUrl(lastImage.getImageId()).thumbnail(318, 318, "inset");
                landingItem.setImage(url.compress(100).getUrl());
            }
            for (Item item : listItems) {
                if (landingItem.getItemId().equals(item.getId())) {
                    landingItem.setItem(item);
                    break;
                }
            }
        }

        return landingItems;
    }

    /**
     * Thêm mới danh mục landing
     *
     * @param landingForm
     * @return
     */
    public Response addLanding(LandingForm landingForm) {
        Map<String, String> error = validator.validate(landingForm);
        List<String> logoImages = imageService.get(ImageType.LANDING_LOGO, landingForm.getId());
        List<String> backgroundImages = imageService.get(ImageType.LANDING_BACKGROUND, landingForm.getId());

        if (landingForm.getId() == null || landingForm.getId().equals("")) {
            landingForm.setId(landingRepository.genId());
        }
        if (logoImages == null && (landingForm.getLogo() == null || landingForm.getLogo().getSize() <= 0)) {
            error.put("logo", "Logo của landing không được để trống");
        }
        if (backgroundImages == null && (landingForm.getBackground() == null || landingForm.getBackground().getSize() <= 0)) {
            error.put("background", "Ảnh nền landing không được để trống");
        }
        if (error.isEmpty()) {
            Landing landing = new Landing();
            landing.setId(landingForm.getId());
            landing.setName(landingForm.getName());
            landing.setColor(landingForm.getColor());
            landingRepository.save(landing);
            if (landingForm.getLogo() != null && landingForm.getLogo().getSize() > 0) {
                imageService.upload(landingForm.getLogo(), ImageType.LANDING_LOGO, landing.getId());
                if (logoImages != null && !logoImages.isEmpty()) {
                    imageService.deleteById(ImageType.LANDING_LOGO, landing.getId(), logoImages.get(0));
                }
            }
            if (landingForm.getBackground() != null && landingForm.getBackground().getSize() > 0) {
                imageService.upload(landingForm.getBackground(), ImageType.LANDING_BACKGROUND, landing.getId());
                if (backgroundImages != null && !backgroundImages.isEmpty()) {
                    imageService.deleteById(ImageType.LANDING_BACKGROUND, landing.getId(), backgroundImages.get(0));
                }
            }
            return new Response(true, "Thêm/Sửa landing thành công");
        }
        return new Response(false, "Thêm/Sửa landing thất bại", error);
    }

    /**
     * Thêm mới danh mục landing
     *
     * @param landingCategory
     * @return
     */
    public Response addCategory(LandingCategory landingCategory) {
        Map<String, String> error = validator.validate(landingCategory);
        if (landingCategory.getId() == null || landingCategory.getId().equals("")) {
            landingCategory.setId(landingCategoryRepository.genId());
        }
        if (error.isEmpty()) {
            landingCategoryRepository.save(landingCategory);
            return new Response(true, "Thêm/Sửa danh mục landing thành công");
        }
        return new Response(false, "Thêm/Sửa danh mục landing thất bại", error);
    }

    /**
     * Thêm mới sản phẩm
     *
     * @param landingItem
     * @return
     */
    public Response addItem(LandingItem landingItem) {
        Map<String, String> error = validator.validate(landingItem);
        LandingCategory landingCategory = landingCategoryRepository.find(landingItem.getLandingCategoryId());
        if (landingCategory == null) {
            error.put("landingCategoryId", "Danh mục landing không tồn tại");
        }
        Item item = itemRepository.find(landingItem.getItemId());
        if (item == null) {
            error.put("itemId", "Sản phẩm không tồn tại");
        }
        if (error.isEmpty()) {
            LandingItem li = landingItemRepository.getByItem(landingItem.getItemId());
            if (landingItem.getId() == null || landingItem.getId().equals("")) {
                if (li != null) {
                    landingItem.setId(li.getId());
                } else {
                    landingItem.setId(landingItemRepository.genId());
                }
            }
            landingItemRepository.save(landingItem);
            Image firstImage = imageService.getFirstImage(item.getId(), ImageType.ITEM);
            if (firstImage != null) {
                ImageUrl url = imageService.getUrl(firstImage.getImageId());
                if (url != null) {
                    item.setImages(new ArrayList<String>());
                    item.getImages().add(firstImage.getImageId());
                    landingItem.setImage(url.getUrl());
                    imageService.delete(ImageType.LANDING, landingItem.getId());
                    imageService.download(url.getUrl(), ImageType.LANDING, landingItem.getId());
                }
            }
            landingItem.setItem(item);
            return new Response(true, "Thêm sản phẩm landing thành công", landingItem);
        }
        return new Response(false, "Thêm sản phẩm landing thất bại", error);
    }

    /**
     * Xóa sản phẩm
     *
     * @param id
     * @return
     */
    public Response deleteItem(String id) {
        landingItemRepository.delete(id);
        imageService.delete(ImageType.LANDING, id);
        return new Response(true, "Xóa sản phẩm thành công");
    }

    /**
     * Xóa danh mục landing
     *
     * @param id
     * @return
     */
    public Response deleteCategory(String id) {
        if (landingCategoryRepository.exists(id)) {
            landingCategoryRepository.delete(id);
            landingItemRepository.removeByCategory(id);
            return new Response(true, "Xóa danh mục thành công");
        } else {
            return new Response(true, "Danh mục không tồn tại");
        }
    }

    /**
     * Xóa landing
     *
     * @param id
     * @return
     */
    public Response deleteLanding(String id) {
        if (landingRepository.exists(id)) {
            landingRepository.delete(id);
            List<LandingCategory> byLanding = landingCategoryRepository.getByLanding(id);
            for (LandingCategory landingCategory : byLanding) {
                landingCategoryRepository.delete(landingCategory.getId());
                landingItemRepository.removeByCategory(landingCategory.getId());
            }

            return new Response(true, "Xóa danh mục thành công");
        } else {
            return new Response(true, "Danh mục không tồn tại");
        }
    }

    public LandingItem getItem(String id) {
        return landingItemRepository.find(id);
    }

}
