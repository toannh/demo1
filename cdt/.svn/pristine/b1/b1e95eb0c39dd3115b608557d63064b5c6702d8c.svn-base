package vn.chodientu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.data.CategoryBannerHome;
import vn.chodientu.entity.data.CategoryItemHome;
import vn.chodientu.entity.data.CategoryManufacturerHome;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.FeaturedCategory;
import vn.chodientu.entity.db.FeaturedCategorySub;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.FeaturedCategoryForm;
import vn.chodientu.entity.form.FeaturedCategorySubForm;
import vn.chodientu.entity.input.ModelSearch;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.FeaturedCategoryRepository;
import vn.chodientu.repository.FeaturedCategorySubRepository;
import vn.chodientu.repository.ManufacturerRepository;

/**
 * @since Jun 6, 2014
 * @author Phuongdt
 */
@Service
public class FeaturedCategorySubService {

    @Autowired
    private FeaturedCategorySubRepository featuredCategorySubRepository;
    @Autowired
    private FeaturedCategoryRepository featuredCategoryRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ModelService modelService;

    /**
     *
     * @return
     */
    public List<FeaturedCategorySub> getall(boolean active) throws Exception {
        return featuredCategorySubRepository.list(active);
    }

    public List<FeaturedCategorySub> getByCategoryId(String id) {
        return featuredCategorySubRepository.getByCategoryId(id);
    }

    public List<FeaturedCategorySub> getByCategoryIdActive(String id) {
        return featuredCategorySubRepository.getByCategoryIdActive(id);
    }

    /**
     * Thêm mới danh mục nổi bật
     *
     * @param featuredCategorySubForm
     * @return
     * @throws java.lang.Exception
     */
    public Response add(FeaturedCategorySubForm featuredCategorySubForm) throws Exception {
        Map<String, String> errors = validator.validate(featuredCategorySubForm);
        if (featuredCategorySubForm.getCategorySubId().equals("0")) {
            errors.put("categorySubId", "Bạn chưa chọn danh mục con");
        }
        for (FeaturedCategorySub obj : this.getall(false)) {
            if (obj.getCategorySubId().contains(featuredCategorySubForm.getCategorySubId())) {
                errors.put("categorySubId", "Danh mục này đã tồn tại");
                break;
            }
        }
        if (!errors.isEmpty()) {
            return new Response(false, null, errors);
        } else {
            Category category = categoryService.get(featuredCategorySubForm.getCategorySubId());
            FeaturedCategorySub fcs = new FeaturedCategorySub();
            fcs.setId(featuredCategorySubRepository.genId());
            fcs.setCategorySubId(featuredCategorySubForm.getCategorySubId());
            fcs.setFeaturedCategororyId(featuredCategorySubForm.getFeaturedCategororyId());
            fcs.setActive(true);
            fcs.setPosition(2);
            if (category != null) {
                fcs.setCategorySubName(category.getName());
            }
            featuredCategorySubRepository.save(fcs);
            return new Response(true, "Đã thêm danh mục con thành công");
        }

    }

    public Response getTemplateById(String id) throws Exception {
        if (id.equals("0")) {
            throw new Exception("Bạn chưa chọn danh mục con");
        }
        FeaturedCategorySub find = featuredCategorySubRepository.getCategorySubId(id);
        FeaturedCategory fc = featuredCategoryRepository.getByCate(find.getFeaturedCategororyId());
        if (fc == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        List<CategoryItemHome> categoryItemHomes = find.getCategoryItemHomes();
        if (categoryItemHomes != null && categoryItemHomes.size() > 0) {
            for (CategoryItemHome itemHome : categoryItemHomes) {
                String idTarget = find.getId() + itemHome.getItemId();
                List<String> getImg = imageService.get(ImageType.FEATURED_CATEGORY, idTarget);
                if (getImg != null && getImg.size() > 0) {
                    String url = imageService.getUrl(getImg.get(0)).getUrl();
                    itemHome.setImage(url);
                }

            }
            find.setCategoryItemHomes(categoryItemHomes);
        }
        List<CategoryBannerHome> categoryBannerHomes = find.getCategoryBannerHomes();
        if (categoryBannerHomes != null && categoryBannerHomes.size() > 0) {
            for (CategoryBannerHome bannerHome : categoryBannerHomes) {
                List<String> getImg = imageService.get(ImageType.FEATURED_CATEGORY, bannerHome.getId());
                if (getImg != null && getImg.size() > 0) {
                    String url = imageService.getUrl(getImg.get(0)).getUrl();
                    bannerHome.setImage(url);
                }
            }
            find.setCategoryBannerHomes(categoryBannerHomes);
        }
        List<CategoryManufacturerHome> manufacturerHomes = find.getCategoryManufacturerHomes();
        if (manufacturerHomes != null && manufacturerHomes.size() > 0) {
            for (CategoryManufacturerHome home : manufacturerHomes) {
                List<String> getImg = imageService.get(ImageType.MANUFACTURER, home.getManufacturerId());
                if (getImg != null && getImg.size() > 0) {
                    String url = imageService.getUrl(getImg.get(0)).getUrl();
                    home.setImage(url);
                }
            }
            find.setCategoryManufacturerHomes(manufacturerHomes);
        }
        find.setTemplate(fc.getTemplate());
        return new Response(true, null, find);

    }

    public Response getTabById(String id) throws Exception {
        if (id.equals("0")) {
            throw new Exception("Bạn chưa chọn danh mục con");
        }
        FeaturedCategorySub find = featuredCategorySubRepository.getCategorySubId(id);
        FeaturedCategory fc = featuredCategoryRepository.getByCate(find.getFeaturedCategororyId());
        if (fc == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        //List sản phẩm trong box sản phẩm nổi bật
        List<CategoryItemHome> categoryItemHomes = find.getCategoryItemHomes();
        List<String> imageIds = new ArrayList<>();
        List<String> itemIds = new ArrayList<>();
        if (categoryItemHomes != null && categoryItemHomes.size() > 0) {
            for (CategoryItemHome itemHome : categoryItemHomes) {
                String idTarget = find.getId() + itemHome.getItemId();
                imageIds.add(idTarget);
                itemIds.add(itemHome.getItemId());
            }
            List<Item> listItems = itemService.list(itemIds);
            if (listItems != null && !listItems.isEmpty()) {
                find.setItems(listItems);
            }
            if (imageIds != null && !imageIds.isEmpty()) {
                Map<String, List<String>> image = imageService.get(ImageType.FEATURED_CATEGORY, imageIds);
                if (image != null && !image.isEmpty()) {
                    for (Map.Entry<String, List<String>> entry : image.entrySet()) {
                        String tagetId = entry.getKey();
                        List<String> imageId = entry.getValue();
                        for (CategoryItemHome itemHome : categoryItemHomes) {
                            String str = find.getId() + itemHome.getItemId();
                            if (str.equals(tagetId) && imageId != null && imageId.size() > 0) {
                                itemHome.setImage(imageService.getUrl(imageId.get(0)).getUrl());
                            }
                        }
                    }
                    //itemHome.setImage(url);
                }
            }
            find.setCategoryItemHomes(categoryItemHomes);
        }

        //List banner trong box sản phẩm nổi bật
        List<CategoryBannerHome> categoryBannerHomes = find.getCategoryBannerHomes();
        List<String> catebannerList = new ArrayList<>();
        if (categoryBannerHomes != null && categoryBannerHomes.size() > 0) {
            for (CategoryBannerHome bannerHome : categoryBannerHomes) {
                catebannerList.add(bannerHome.getId());
            }
            if (catebannerList != null && !catebannerList.isEmpty()) {
                Map<String, List<String>> image = imageService.get(ImageType.FEATURED_CATEGORY, catebannerList);
                if (image != null && !image.isEmpty()) {
                    for (Map.Entry<String, List<String>> entry : image.entrySet()) {
                        String tagetId = entry.getKey();
                        List<String> imageId = entry.getValue();
                        for (CategoryBannerHome bannerHome : categoryBannerHomes) {
                            String str = bannerHome.getId();
                            if (str.equals(tagetId) && imageId != null && imageId.size() > 0) {
                                bannerHome.setImage(imageService.getUrl(imageId.get(0)).getUrl());
                            }
                        }
                    }
                    //itemHome.setImage(url);
                }
            }
            find.setCategoryBannerHomes(categoryBannerHomes);
        }

        List<CategoryManufacturerHome> manufacturerHomes = find.getCategoryManufacturerHomes();
        if (manufacturerHomes != null && manufacturerHomes.size() > 0) {
            List<String> manufacturerIds = new ArrayList<>();
            for (CategoryManufacturerHome home : manufacturerHomes) {
                manufacturerIds.add(home.getManufacturerId());
            }
            if (manufacturerIds != null && !manufacturerIds.isEmpty()) {
                Map<String, List<String>> image = imageService.get(ImageType.MANUFACTURER, manufacturerIds);
                if (image != null && !image.isEmpty()) {
                    for (Map.Entry<String, List<String>> entry : image.entrySet()) {
                        String tagetId = entry.getKey();
                        List<String> imageId = entry.getValue();
                        for (CategoryManufacturerHome home : manufacturerHomes) {
                            String str = home.getManufacturerId();
                            if (str.equals(tagetId) && imageId != null && imageId.size() > 0) {
                                home.setImage(imageService.getUrl(imageId.get(0)).getUrl());
                            }
                        }
                    }

                }
            }
            find.setCategoryManufacturerHomes(manufacturerHomes);
            if (fc.getTemplate().equals("template3")) {
                CategoryManufacturerHome categoryManufacturerHome = manufacturerHomes.get(0);

                if (categoryManufacturerHome != null) {
                    String manufacturerId = categoryManufacturerHome.getManufacturerId();
                    if (manufacturerId != null && !manufacturerId.equals("")) {
                        List<String> modelIds = categoryManufacturerHome.getModelIds();
                        if (modelIds != null && !modelIds.isEmpty()) {
                            List<Model> models = modelService.getModels(modelIds);
                            if (models != null && models.size() > 0) {
                                for (Model m : models) {
                                    List<String> get = imageService.get(ImageType.MODEL, m.getId());
                                    List<String> image = new ArrayList<>();
                                    if (get != null && get.size() > 0) {
                                        for (String img : get) {
                                            String url = imageService.getUrl(img).getUrl();
                                            image.add(url);
                                        }
                                    }
                                    m.setImages(image);
                                }
                                ModelSearch modelSearch = new ModelSearch();
                                modelSearch.setStatus(1);
                                modelSearch.setModelId(models.get(0).getId());
                                models.get(0).setCountShop(itemService.getItemByModelCount(modelSearch));

                                find.setModels(models);
                            }
                        }

                    }
                }
            }
        }
        find.setTemplate(fc.getTemplate());
        return new Response(true, null, find);

    }

    public FeaturedCategorySub getModelByManufate(String id) throws Exception {
        if (id.equals("0")) {
            throw new Exception("Bạn chưa chọn danh mục con");
        }
        FeaturedCategorySub find = featuredCategorySubRepository.getCategorySubId(id);
        FeaturedCategory fc = featuredCategoryRepository.getByCate(find.getFeaturedCategororyId());
        if (fc == null) {
            throw new Exception("Không tồn tại bản ghi");
        }

        List<CategoryManufacturerHome> manufacturerHomes = find.getCategoryManufacturerHomes();

        find.setTemplate(fc.getTemplate());
        return find;

    }

    public Response addItemTemplate(FeaturedCategorySubForm featuredCategorySub, int tpl) throws Exception {
        Map<String, String> errors = new HashMap<>();
        String id = featuredCategorySub.getId();
        FeaturedCategorySub find = featuredCategorySubRepository.find(id);
        List<CategoryItemHome> categoryItemHomes = featuredCategorySub.getCategoryItemHomes();
        List<CategoryBannerHome> categoryBannerHomes = featuredCategorySub.getCategoryBannerHomes();
        List<CategoryManufacturerHome> categoryManufacturerHomes = featuredCategorySub.getCategoryManufacturerHomes();
        if (categoryItemHomes != null && categoryItemHomes.size() > 0) {
            int position = categoryItemHomes.get(0).getPosition();
            int width = 0;
            int height = 0;
            int positi = 0;
            if (tpl == 1) {
                if (position >= 1 && position <= 2) {
                    width = 298;
                    height = 381;
                    positi = position;
                }
                if (position >= 3 && position <= 8) {
                    width = 199;
                    height = 190;
                    positi = position;
                }
            }
            if (tpl == 2) {
                if (position == 1) {
                    width = 395;
                    height = 375;
                    positi = position;
                }
                if (position >= 2 && position <= 5) {
                    width = 199;
                    height = 190;
                    positi = position;
                }
            }
            if (tpl == 3) {

                if (position >= 1 && position <= 4) {
                    width = 199;
                    height = 190;
                    positi = position;
                }
            }
            if (tpl == 4) {
                if (position == 1) {
                    width = 401;
                    height = 383;
                    positi = position;
                }
                if (position >= 2 && position <= 4) {
                    width = 201;
                    height = 260;
                    positi = position;
                }
                if (position == 5) {
                    width = 185;
                    height = 345;
                    positi = position;
                }
            }
            if (tpl == 5) {
                if (position >= 1 && position <= 4) {
                    width = 199;
                    height = 190;
                    positi = position;
                }
            }
            if (tpl == 6) {
                if (position >= 1 && position <= 4) {
                    width = 199;
                    height = 190;
                    positi = position;
                }
            }

            List<CategoryItemHome> itemHomes = featuredCategorySub.getCategoryItemHomes();
            List<CategoryItemHome> listCIH = new ArrayList<>();
            boolean fag = false;
            CategoryItemHome categoryItemHome = itemHomes.get(0);
            List<CategoryItemHome> cihs = featuredCategorySubRepository.getCategorySubId(featuredCategorySub.getCategorySubId()).getCategoryItemHomes();
            Item item = itemService.get(categoryItemHome.getItemId());
            if (item == null) {
                errors.put("itemId", "Sản phẩm không tồn tại");
            }
            if (categoryItemHome.getTitle().length() > 70) {
                throw new Exception("Tiêu đề SP không vượt quá 70 kí tự: " + categoryItemHome.getTitle().length());
                //errors.put("title", "Tiêu đề SP không vượt quá 70 kí tự - Legth: " + categoryItemHome.getTitle().length());
            }
            if (categoryItemHome.getTitle().trim().equals("")) {
                throw new Exception("Tiêu đề SP không được để trống");
            }
            List<String> idsItem = new ArrayList<>();
            String itemId = "";
            if (cihs != null && cihs.size() > 0) {
                for (CategoryItemHome itemHome : cihs) {
                    idsItem.add(itemHome.getItemId());
                    if (itemHome.getPosition() == positi) {
                        itemId = itemHome.getItemId();
                        break;
                    }
                }
                if (!itemId.equals(categoryItemHome.getItemId())) {
                    if (idsItem != null && idsItem.size() > 0) {
                        if (idsItem.contains(categoryItemHome.getItemId())) {
                            throw new Exception("Sản phẩm đã tồn tại trong box");
                        }
                    }
                }
            }
            if (cihs != null && cihs.size() > 0) {
                for (CategoryItemHome cih : cihs) {
                    if (cih.getPosition() == categoryItemHome.getPosition()) {
                        String url = categoryItemHome.getImage();
                        String idTarget = id + categoryItemHome.getItemId();
                        if (categoryItemHome.getWidth() > 0 && categoryItemHome.getHeight() > 0) {
                            List<String> getImg = imageService.get(ImageType.FEATURED_CATEGORY, categoryItemHome.getIdItemOld());
                            String crop = imageService.getUrl(getImg.get(0)).crop(categoryItemHome.getX(), categoryItemHome.getY(), categoryItemHome.getWidth(), categoryItemHome.getHeight()).thumbnail(width, height, "outbound").getUrl();
                            imageService.download(crop, ImageType.FEATURED_CATEGORY, idTarget);
                            imageService.deleteById(ImageType.FEATURED_CATEGORY, categoryItemHome.getIdItemOld(), getImg.get(0));
                        }
                        if (!categoryItemHome.getIdItemOld().equals("") && !categoryItemHome.getImage().equals("") && categoryItemHome.getWidth() == 0 && categoryItemHome.getHeight() == 0) {
                            List<String> getImg = imageService.get(ImageType.FEATURED_CATEGORY, categoryItemHome.getIdItemOld());
                            String crop = imageService.getUrl(getImg.get(0)).thumbnail(width, height, "outbound").getUrl();
                            imageService.download(crop, ImageType.FEATURED_CATEGORY, idTarget);
                            imageService.deleteById(ImageType.FEATURED_CATEGORY, categoryItemHome.getIdItemOld(), getImg.get(0));
                        }
                        cih.setItemId(categoryItemHome.getItemId());
                        cih.setPosition(positi);
                        cih.setItemId(categoryItemHome.getItemId());
                        cih.setTitle(categoryItemHome.getTitle());
                        find.setCategoryItemHomes(cihs);
                        fag = false;
                        break;
                    } else {
                        fag = true;
                    }
                }
                if (fag) {
                    String url = categoryItemHome.getImage();
                    String idTarget = id + categoryItemHome.getItemId();
                    if (categoryItemHome.getWidth() > 0 && categoryItemHome.getHeight() > 0) {
                        List<String> getImg = imageService.get(ImageType.FEATURED_CATEGORY, categoryItemHome.getIdItemOld());
                        String crop = imageService.getUrl(getImg.get(0)).crop(categoryItemHome.getX(), categoryItemHome.getY(), categoryItemHome.getWidth(), categoryItemHome.getHeight()).thumbnail(width, height, "outbound").getUrl();
                        imageService.download(crop, ImageType.FEATURED_CATEGORY, idTarget);
                        imageService.deleteById(ImageType.FEATURED_CATEGORY, categoryItemHome.getIdItemOld(), getImg.get(0));
                    }
                    if (!categoryItemHome.getIdItemOld().equals("") && !categoryItemHome.getImage().equals("") && categoryItemHome.getWidth() == 0 && categoryItemHome.getHeight() == 0) {
                        List<String> getImg = imageService.get(ImageType.FEATURED_CATEGORY, categoryItemHome.getIdItemOld());
                        String crop = imageService.getUrl(getImg.get(0)).thumbnail(width, height, "outbound").getUrl();
                        imageService.download(crop, ImageType.FEATURED_CATEGORY, idTarget);
                        imageService.deleteById(ImageType.FEATURED_CATEGORY, categoryItemHome.getIdItemOld(), getImg.get(0));
                    }
                    CategoryItemHome home = new CategoryItemHome();
                    home.setItemId(categoryItemHome.getItemId());
                    home.setPosition(positi);
                    home.setItemId(categoryItemHome.getItemId());
                    home.setTitle(categoryItemHome.getTitle());
                    cihs.add(home);
                    find.setCategoryItemHomes(cihs);
                }

            } else {
                String url = categoryItemHome.getImage();
                String idTarget = id + categoryItemHome.getItemId();
                if (categoryItemHome.getWidth() > 0 && categoryItemHome.getHeight() > 0) {
                    List<String> getImg = imageService.get(ImageType.FEATURED_CATEGORY, categoryItemHome.getIdItemOld());
                    String crop = imageService.getUrl(getImg.get(0)).crop(categoryItemHome.getX(), categoryItemHome.getY(), categoryItemHome.getWidth(), categoryItemHome.getHeight()).thumbnail(width, height, "outbound").getUrl();
                    imageService.download(crop, ImageType.FEATURED_CATEGORY, idTarget);
                    imageService.deleteById(ImageType.FEATURED_CATEGORY, categoryItemHome.getIdItemOld(), getImg.get(0));
                }
                if (!categoryItemHome.getIdItemOld().equals("") && !categoryItemHome.getImage().equals("") && categoryItemHome.getWidth() == 0 && categoryItemHome.getHeight() == 0) {
                    List<String> getImg = imageService.get(ImageType.FEATURED_CATEGORY, categoryItemHome.getIdItemOld());
                    String crop = imageService.getUrl(getImg.get(0)).thumbnail(width, height, "outbound").getUrl();
                    imageService.download(crop, ImageType.FEATURED_CATEGORY, idTarget);
                    imageService.deleteById(ImageType.FEATURED_CATEGORY, categoryItemHome.getIdItemOld(), getImg.get(0));
                }
                CategoryItemHome cth = new CategoryItemHome();
                cth.setItemId(categoryItemHome.getItemId());
                cth.setPosition(positi);
                cth.setItemId(categoryItemHome.getItemId());
                cth.setTitle(categoryItemHome.getTitle());
                listCIH.add(cth);
                find.setCategoryItemHomes(listCIH);
            }

        }
        if (categoryBannerHomes != null && categoryBannerHomes.size() > 0) {
            int position = categoryBannerHomes.get(0).getPosition();
            int width = 0;
            int height = 0;
            if (tpl == 1) {
                width = 400;
                height = 381;
            }
            if (tpl == 2) {
                width = 198;
                height = 381;
            }
            if (tpl == 5) {
                width = 399;
                height = 190;
            }
            if (tpl == 4) {
                width = 598;
                height = 122;
            }
            if (tpl == 3) {
                if (position == 4) {
                    width = 398;
                    height = 190;
                } else {
                    width = 199;
                    height = 126;
                }
            }

            CategoryBannerHome categoryBannerHome = categoryBannerHomes.get(0);
            List<CategoryBannerHome> cbhs = featuredCategorySubRepository.getCategorySubId(featuredCategorySub.getCategorySubId()).getCategoryBannerHomes();
            boolean fag = false;
            if (cbhs != null && cbhs.size() > 0) {
                for (CategoryBannerHome bannerHome : cbhs) {
                    if (categoryBannerHome.getUrl().equals("")) {
                        return new Response(false, "Url không được để rỗng");
                    }
                    if (bannerHome.getId().equals(categoryBannerHome.getId())) {
                        if (categoryBannerHome.getWidth() > 0 && categoryBannerHome.getHeight() > 0) {
                            String imageUrl = categoryBannerHome.getImage();
                            List<String> get = imageService.get(ImageType.FEATURED_CATEGORY, categoryBannerHome.getIdBannerOld());
                            String crop = imageService.getUrl(get.get(0)).crop(categoryBannerHome.getX(), categoryBannerHome.getY(), categoryBannerHome.getWidth(), categoryBannerHome.getHeight()).thumbnail(width, height, "outbound").getUrl();
                            imageService.download(crop, ImageType.FEATURED_CATEGORY, bannerHome.getId());
                            imageService.deleteByUrl(ImageType.FEATURED_CATEGORY, categoryBannerHome.getIdBannerOld(), crop);

                        }
                        if (categoryBannerHome.getWidth() == 0 && categoryBannerHome.getHeight() == 0 && !categoryBannerHome.getIdBannerOld().equals("")) {
                            String imageUrl = categoryBannerHome.getImage();
                            List<String> get = imageService.get(ImageType.FEATURED_CATEGORY, categoryBannerHome.getIdBannerOld());
                            String crop = imageService.getUrl(get.get(0)).thumbnail(width, height, "outbound").getUrl();
                            imageService.download(crop, ImageType.FEATURED_CATEGORY, bannerHome.getId());
                            imageService.deleteByUrl(ImageType.FEATURED_CATEGORY, categoryBannerHome.getId(), crop);
                        }
                        bannerHome.setUrl(categoryBannerHome.getUrl());
                        bannerHome.setPosition(position);
                        fag = false;
                        break;
                    } else {
                        fag = true;
                    }

                }
                find.setCategoryBannerHomes(cbhs);
                if (fag == true) {
                    String genId = featuredCategorySubRepository.genId();
                    if (categoryBannerHome.getWidth() > 0 && categoryBannerHome.getHeight() > 0) {
                        String imageUrl = categoryBannerHome.getImage();
                        List<String> get = imageService.get(ImageType.FEATURED_CATEGORY, categoryBannerHome.getIdBannerOld());
                        String crop = imageService.getUrl(get.get(0)).crop(categoryBannerHome.getX(), categoryBannerHome.getY(), categoryBannerHome.getWidth(), categoryBannerHome.getHeight()).thumbnail(width, height, "outbound").getUrl();
                        imageService.download(crop, ImageType.FEATURED_CATEGORY, genId);
                        imageService.deleteByUrl(ImageType.FEATURED_CATEGORY, categoryBannerHome.getIdBannerOld(), crop);

                    }
                    if (categoryBannerHome.getWidth() == 0 && categoryBannerHome.getHeight() == 0 && !categoryBannerHome.getIdBannerOld().equals("")) {
                        String imageUrl = categoryBannerHome.getImage();
                        List<String> get = imageService.get(ImageType.FEATURED_CATEGORY, categoryBannerHome.getIdBannerOld());
                        String crop = imageService.getUrl(get.get(0)).thumbnail(width, height, "outbound").getUrl();
                        imageService.download(crop, ImageType.FEATURED_CATEGORY, genId);
                        imageService.deleteByUrl(ImageType.FEATURED_CATEGORY, categoryBannerHome.getIdBannerOld(), crop);

                    }

                    CategoryBannerHome listCBH = new CategoryBannerHome();
                    listCBH.setId(genId);
                    listCBH.setUrl(categoryBannerHome.getUrl());
                    listCBH.setPosition(position);
                    cbhs.add(listCBH);
                    find.setCategoryBannerHomes(cbhs);
                }
            } else {
                CategoryBannerHome cbh = new CategoryBannerHome();
                List<CategoryBannerHome> listCBH = new ArrayList<>();
                String genId = featuredCategorySubRepository.genId();
                if (categoryBannerHome.getWidth() > 0 && categoryBannerHome.getHeight() > 0) {
                    String imageUrl = categoryBannerHome.getImage();
                    List<String> get = imageService.get(ImageType.FEATURED_CATEGORY, categoryBannerHome.getIdBannerOld());
                    String crop = imageService.getUrl(get.get(0)).crop(categoryBannerHome.getX(), categoryBannerHome.getY(), categoryBannerHome.getWidth(), categoryBannerHome.getHeight()).thumbnail(width, height, "outbound").getUrl();
                    imageService.download(crop, ImageType.FEATURED_CATEGORY, genId);
                    imageService.deleteByUrl(ImageType.FEATURED_CATEGORY, categoryBannerHome.getIdBannerOld(), crop);

                }
                if (categoryBannerHome.getWidth() == 0 && categoryBannerHome.getHeight() == 0 && !categoryBannerHome.getIdBannerOld().equals("")) {
                    String imageUrl = categoryBannerHome.getImage();
                    List<String> get = imageService.get(ImageType.FEATURED_CATEGORY, categoryBannerHome.getIdBannerOld());
                    String crop = imageService.getUrl(get.get(0)).thumbnail(width, height, "outbound").getUrl();
                    imageService.download(crop, ImageType.FEATURED_CATEGORY, genId);
                    imageService.deleteByUrl(ImageType.FEATURED_CATEGORY, categoryBannerHome.getIdBannerOld(), crop);

                }
                cbh.setId(genId);
                cbh.setUrl(categoryBannerHome.getUrl());
                cbh.setPosition(position);
                listCBH.add(cbh);

                find.setCategoryBannerHomes(listCBH);
            }

        }
        if (categoryManufacturerHomes != null && categoryManufacturerHomes.size() > 0) {
            boolean fags = true;
            boolean fagM = true;
            Map<String, String> map = new HashMap<>();
            List<String> modelIds = new ArrayList<>();
            List<String> manufacturerIds = new ArrayList<>();
            for (CategoryManufacturerHome cmh : featuredCategorySub.getCategoryManufacturerHomes()) {
                Manufacturer manufacturer = manufacturerRepository.find(cmh.getManufacturerId());
                if (manufacturer == null) {
                    errors.put("manufacturerId" + cmh.getPosition(), "Mã thương hiệu không tồn tại");
                    fags = false;
                } else {
                    manufacturerIds.add(manufacturer.getId());
                }
                if (cmh.getModelIds() != null && !cmh.getModelIds().isEmpty()) {
                    modelIds.addAll(cmh.getModelIds());
                }
            }
            if (modelIds != null && !modelIds.isEmpty()) {
                List<Model> models = modelService.getModels(modelIds);
                if (models != null && !models.isEmpty()) {
                    for (CategoryManufacturerHome cmh : featuredCategorySub.getCategoryManufacturerHomes()) {
                        for (Model model : models) {
                            if (cmh.getModelIds().contains(model.getId())) {
                                if (!model.getManufacturerId().equals(cmh.getManufacturerId())) {
                                    errors.put("modelIds" + cmh.getPosition(), "ID của model không thuộc thương hiệu này");
                                    fagM = false;
                                }
                            }
                        }
                        List<String> modelIds1 = cmh.getModelIds();
                        List<String> ses = new ArrayList<>();
                        if (modelIds1 != null && !modelIds1.isEmpty() && !modelIds1.get(0).equals("")) {
                            for (String string : modelIds1) {
                                if (string != null && !string.equals("")) {
                                    ses.add(string);
                                }
                            }
                            List<Model> listM = modelService.getModels(ses);
                            if (listM.size() < cmh.getModelIds().size()) {
                                List<String> strings = new ArrayList<>();
                                for (String s : ses) {
                                    for (Model model : listM) {
                                        if (!model.getId().equals(s)) {
                                            strings.add(s);
                                        }
                                    }
                                }
                                errors.put("modelIds" + cmh.getPosition(), "ID Model không tồn tại: " + strings);
                            }
                        }

                    }
                } else {
                    return new Response(false, "Không tồn tại model trên hệ thống", null);
                }
            }

            if (fags && fagM) {
                find.setCategoryManufacturerHomes(featuredCategorySub.getCategoryManufacturerHomes());
            }
        }
        if (!errors.isEmpty()) {
            return new Response(false, null, errors);
        } else {
            featuredCategorySubRepository.save(find);
            return new Response(true, "Thay đổi thành công");
        }
    }

    /**
     * Lấy thông tin của Item edit
     *
     * @param itemId
     * @param idCategory
     * @return
     */
    public Response getItemFeatureCategorySub(String itemId, String idCategory) {
        FeaturedCategorySub fcs = featuredCategorySubRepository.getCategorySubId(idCategory);
        if (fcs == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }

        if (itemId != null && !itemId.equals("0")) {
            List<CategoryItemHome> categoryItemHomes = new ArrayList<>();
            for (CategoryItemHome itemHome : fcs.getCategoryItemHomes()) {
                if (itemHome.getItemId().equals(itemId)) {
                    categoryItemHomes.add(itemHome);
                    break;
                }
            }
            CategoryItemHome categoryItemHome = categoryItemHomes.get(0);
            String idtarget = fcs.getId() + itemId;
            List<String> getImg = imageService.get(ImageType.FEATURED_CATEGORY, idtarget);
            if (getImg.size() > 0) {
                String url = imageService.getUrl(getImg.get(0)).getUrl();
                categoryItemHome.setImage(url);
            }

            fcs.setCategoryItemHomes(categoryItemHomes);
            return new Response(true, null, fcs);
        }

        return new Response(true, null, null);

    }

    public Response getBannerFeatureCategorySub(String bannerId, String idCategory) {
        FeaturedCategorySub fcs = featuredCategorySubRepository.getCategorySubId(idCategory);
        if (fcs == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        if (bannerId != null && !bannerId.equals("0")) {
            List<CategoryBannerHome> bannerHomes = new ArrayList<>();
            for (CategoryBannerHome cbh : fcs.getCategoryBannerHomes()) {
                if (cbh.getId().equals(bannerId)) {
                    bannerHomes.add(cbh);
                    break;
                }
            }
            List<String> getImg = imageService.get(ImageType.FEATURED_CATEGORY, bannerId);
            if (getImg.size() > 0) {
                String url = imageService.getUrl(getImg.get(0)).getUrl();
                bannerHomes.get(0).setImage(url);
            }
            fcs.setCategoryBannerHomes(bannerHomes);
            return new Response(true, null, fcs);
        }
        return new Response(true, null, null);
    }

    public Response getManufacturerFeaturedCategorySub(String idCategory) {
        FeaturedCategorySub fcs = featuredCategorySubRepository.getCategorySubId(idCategory);
        if (fcs == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        List<CategoryManufacturerHome> categoryManufacturerHomes = fcs.getCategoryManufacturerHomes();
        if (categoryManufacturerHomes != null && categoryManufacturerHomes.size() > 0) {
            for (CategoryManufacturerHome cmh : categoryManufacturerHomes) {
                List<String> getImg = imageService.get(ImageType.MANUFACTURER, cmh.getManufacturerId());
                if (getImg != null && getImg.size() > 0) {
                    String url = imageService.getUrl(getImg.get(0)).getUrl();
                    cmh.setImage(url);
                }
            }
            fcs.setCategoryManufacturerHomes(categoryManufacturerHomes);
            return new Response(true, null, fcs);
        }
        return new Response(true, null, null);
    }

    /**
     * Thay đổi vị trí danh mục nổi bật
     *
     * @param idcate
     * @param position
     * @return
     */
    public Response changePosition(String idcate, int position) {
        FeaturedCategorySub byCate = featuredCategorySubRepository.getCategorySubId(idcate);
        if (byCate == null) {
            return new Response(false, "Không tồn tại danh mục");
        }
        byCate.setPosition(position);
        featuredCategorySubRepository.save(byCate);
        return new Response(true, null);
    }

    /**
     * Thay đổi tên danh mục con nổi bật
     *
     * @param featuredCategorySubForm
     * @return
     */
    public Response editName(FeaturedCategorySubForm featuredCategorySubForm) {
        FeaturedCategorySub byCate = featuredCategorySubRepository.getCategorySubId(featuredCategorySubForm.getCategorySubId());
        if (byCate == null) {
            return new Response(false, "Không tồn tại danh mục");
        }
        if (featuredCategorySubForm.getCategorySubName().equals("")) {
            return new Response(false, "Tên danh mục không được để trống");
        } else {
            byCate.setCategorySubName(featuredCategorySubForm.getCategorySubName());
            featuredCategorySubRepository.save(byCate);
        }
        return new Response(true, null);

    }

    /**
     * Thay đổi trạng thái danh mục nổi bật
     *
     * @param id
     * @return
     */
    public Response editStatus(String id) {
        FeaturedCategorySub byCate = featuredCategorySubRepository.getCategorySubId(id);
        if (byCate == null) {
            return new Response(false, "Không tồn tại danh mục");
        }
        byCate.setActive(!byCate.isActive());
        featuredCategorySubRepository.save(byCate);
        return new Response(true, "Sửa trạng thái thành công", byCate);

    }

    /**
     * Xóa danh mục con nổi bật và xóa toàn bộ ảnh trong danh mục đó
     *
     * @param id
     * @return
     */
    public Response del(String id) {
        FeaturedCategorySub byCate = featuredCategorySubRepository.find(id);

        if (byCate == null) {
            return new Response(false, "Không tồn tại danh mục");
        } else {
            List<String> imageIds = new ArrayList<>();
            List<CategoryItemHome> categoryItemHomes = byCate.getCategoryItemHomes();
            List<CategoryBannerHome> categoryBannerHomes = byCate.getCategoryBannerHomes();
            if (categoryItemHomes != null && !categoryItemHomes.isEmpty()) {
                for (CategoryItemHome categoryItemHome : categoryItemHomes) {
                    String idTarget = id + categoryItemHome.getItemId();
                    imageIds.add(idTarget);
                }
            }
            if (categoryBannerHomes != null && !categoryBannerHomes.isEmpty()) {
                for (CategoryBannerHome categoryBannerHome : categoryBannerHomes) {
                    imageIds.add(categoryBannerHome.getId());
                }
            }
            if (imageIds != null && imageIds.size() > 0) {
                for (String img : imageIds) {
                    imageService.delete(ImageType.FEATURED_CATEGORY, img);
                }
            }
            featuredCategorySubRepository.delete(id);
            return new Response(true, "Xóa thành công và toàn bộ ảnh trong đó");
        }
    }

}
