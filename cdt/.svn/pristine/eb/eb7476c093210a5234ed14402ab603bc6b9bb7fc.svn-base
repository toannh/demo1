package vn.chodientu.controller.market;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.chodientu.entity.data.CategoryBannerHome;
import vn.chodientu.entity.data.CategoryItemHome;
import vn.chodientu.entity.data.CategoryManufacturerHome;
import vn.chodientu.entity.data.CategorySubHome;
import vn.chodientu.entity.db.BestDealBox;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.FeaturedCategory;
import vn.chodientu.entity.db.FeaturedCategorySub;
import vn.chodientu.entity.db.FeaturedNews;
import vn.chodientu.entity.db.Footerkeyword;
import vn.chodientu.entity.db.HeartBanner;
import vn.chodientu.entity.db.HomeBanner;
import vn.chodientu.entity.db.HotDealBox;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.db.News;
import vn.chodientu.entity.db.NewsHomeBox;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.TopSellerBox;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.TopSellerBoxItemForm;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.input.ModelSearch;
import vn.chodientu.entity.input.PropertySearch;
import vn.chodientu.service.BestDealBoxService;
import vn.chodientu.service.CityService;
import vn.chodientu.service.FeaturedCategoryService;
import vn.chodientu.service.FeaturedCategorySubService;
import vn.chodientu.service.FeaturedNewsService;
import vn.chodientu.service.FooterKeywordService;
import vn.chodientu.service.HeartBannerService;
import vn.chodientu.service.HomeBannerService;
import vn.chodientu.service.HotDealBoxService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.ModelService;
import vn.chodientu.service.NewsHomeBoxService;
import vn.chodientu.service.NewsService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.TopSellerBoxService;
import vn.chodientu.service.UserService;

/**
 * @since Jun 16, 2014
 * @author Phu
 */
@Controller("marketController")
public class IndexController extends BaseMarket {

    @Autowired
    private HotDealBoxService hotDealBoxService;
    @Autowired
    private FeaturedCategoryService featuredCategoryService;
    @Autowired
    private FeaturedNewsService featuredNewsService;
    @Autowired
    private NewsHomeBoxService newsHomeBoxService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private FeaturedCategorySubService featuredCategorySubService;
    @Autowired
    private BestDealBoxService bestDealBoxService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;
    @Autowired
    private TopSellerBoxService topSellerBoxService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private HeartBannerService heartBannerService;
    @Autowired
    private HomeBannerService homeBannerService;
    @Autowired
    private Gson json;

    @RequestMapping("/index")
    public String index(@CookieValue(value = "popIndex", defaultValue = "0") int popIndex,
            ModelMap model, HttpServletResponse response) throws Exception {
        model.put("canonical", "/");
        model.put("title", "Mua bán, đấu giá sản phẩm thời trang, trang sức, công nghệ, điện tử...");
        model.put("description", "Mua và bán sản phẩm thời trang, trang sức, mỹ phẩm, làm đẹp, điện thoại, máy tính, công nghệ, điện tử, linh phụ kiện v.v.. mọi thứ tại ChợĐiệnTử eBay Việt Nam - Thanh toán online, CoD, giao hàng toàn quốc, bảo vệ người mua.");
        model.put("keywords", "chodientu, quần áo, thời trang, trang sức, phụ kiện, điện tử, xe hơi, đồ thể thao, điện thoại, máy tính, mobile, laptop máy ảnh kỹ thuật số, đồ mẹ bé, ebay, mua bán, đấu giá");

        Cookie c = new Cookie("popIndex", String.valueOf(popIndex + 1));
        c.setPath("/");
        c.setMaxAge(24 * 3600);
        response.addCookie(c);

        model.put("aliasActive", true);
        List<HotDealBox> hotDealBox = hotDealBoxService.list();
        List<String> itemIds = new ArrayList<>();
        for (HotDealBox box : hotDealBox) {
            itemIds.add(box.getItemId());
        }
        List<Item> list = new ArrayList<>();
        if (itemIds != null && itemIds.size() > 0) {
            list = itemService.list(itemIds);
        }
        for (Item item : list) {
            HotDealBox byId = hotDealBoxService.getById(item.getId());
            if (byId != null) {
                item.setName(byId.getTitle());
            }
            List<String> get = imageService.get(ImageType.HOT_DEAL_BOX, item.getId());
            if (get != null && get.size() > 0) {
                List<String> images = new ArrayList<>();
                for (String img : get) {
                    String url = imageService.getUrl(img).compress(100).getUrl();
                    images.add(url);
                }
                item.setImages(images);
            } else {
                item.setImages(null);
            }
        }
        List<String> getImageBanner = imageService.get(ImageType.HOT_DEAL_BOX, "hotdealbox");
        if (getImageBanner != null && getImageBanner.size() > 0) {
            String hotDealBoxBanner = imageService.getUrl(getImageBanner.get(0)).compress(100).getUrl();
            model.put("hotDealBoxBanner", hotDealBoxBanner);
        }
        //Lấy dữ liệu từ topsellerbox
        List<TopSellerBox> listSellerBoxs = topSellerBoxService.list();
        for (TopSellerBox tsb : listSellerBoxs) {
            List<String> listIdItems = new ArrayList<>();
            List<TopSellerBoxItemForm> topSellerBoxItemForms = tsb.getTopSellerBoxItemForms();
            if (topSellerBoxItemForms != null && topSellerBoxItemForms.size() > 0) {
                BeanComparator fieldComparator = new BeanComparator("position");
                Collections.sort(topSellerBoxItemForms, fieldComparator);
                for (TopSellerBoxItemForm topSellerBoxItemForm : topSellerBoxItemForms) {
                    if (topSellerBoxItemForm.isActive()) {
                        listIdItems.add(topSellerBoxItemForm.getItemId());
                    }
                }
            }
            List<Item> items = itemService.list(listIdItems);
            TopSellerBox bySellerId = topSellerBoxService.getBySellerId(tsb.getSellerId());
            if (items != null && !items.isEmpty()) {
                for (Item item : items) {
                    if (bySellerId != null) {
                        for (TopSellerBoxItemForm itemForm : bySellerId.getTopSellerBoxItemForms()) {
                            if (itemForm.getItemId().equals(item.getId())) {
                                item.setName(itemForm.getTitle());
                                break;
                            }
                        }
                    }
                    List<String> get = imageService.get(ImageType.TOP_SELLER_BOX, item.getId());
                    if (get != null && get.size() > 0) {
                        List<String> images = new ArrayList<>();
                        for (String img : get) {
                            String url = imageService.getUrl(img).thumbnail(199, 190, "outbound").compress(100).getUrl();
                            images.add(url);
                        }
                        item.setImages(images);
                    } else {
                        item.setImages(null);
                    }

                }
            }
            tsb.setItems(items);
            //Lấy ảnh cho topsellerbox
            List<String> getT = imageService.get(ImageType.TOP_SELLER_BOX, tsb.getId());
            if (getT != null && getT.size() > 0) {
                String urlT = imageService.getUrl(getT.get(0)).thumbnail(50, 50, "outbound").compress(100).getUrl();
                tsb.setImage(urlT);
            }
            User user = (User) userService.getById(tsb.getSellerId()).getData();
            if (user != null) {
                City data = (City) cityService.getCitybyId(user.getCityId()).getData();
                if (data != null) {
                    tsb.setCity(data.getName());
                }
            }
            Shop shop = shopService.getShop(tsb.getSellerId());
            if (shop != null) {
                tsb.setSellerName(shop.getAlias());
            }
            tsb.setCountItem(itemService.countBySeller(tsb.getSellerId()));
        }
        //Láº¥y dá»¯ liá»‡u tá»« BestDealBox
        List<BestDealBox> allBestDealBoxs = bestDealBoxService.list();
        List<String> itemIdss = new ArrayList<>();
        if (allBestDealBoxs != null && allBestDealBoxs.size() > 0) {
            for (BestDealBox bestDealBox : allBestDealBoxs) {
                itemIdss.add(bestDealBox.getItemId());
            }
        }

        List<Item> lsBestDealBox = itemService.list(itemIdss);
        for (Item item : lsBestDealBox) {
            BestDealBox byItemId = bestDealBoxService.getByItemId(item.getId());
            if (byItemId != null) {
                item.setName(byItemId.getTitle());
            }
            List<String> get = imageService.get(ImageType.BEST_DEAL_BOX, item.getId());
            if (get != null && get.size() > 0) {
                List<String> images = new ArrayList<>();
                for (String img : get) {
                    String url = imageService.getUrl(img).thumbnail(280, 266, "outbound").compress(100).getUrl();
                    images.add(url);
                }
                item.setImages(images);
            } else {
                item.setImages(null);
            }
        }

        //Láº¥y dá»¯ liá»‡u tá»« danh má»¥c ná»•i báº­t
        List<FeaturedCategory> featuredCategorys = featuredCategoryService.list();
        List<String> categoryIds = new ArrayList<>();
        String manufacturerIds = "";
        for (FeaturedCategory featuredCategory : featuredCategorys) {
            categoryIds.add(featuredCategory.getCategoryId());
        }

        for (FeaturedCategory fc : featuredCategorys) {

            List<FeaturedCategorySub> categorySubs = featuredCategorySubService.getByCategoryIdActive(fc.getCategoryId());
            List<String> ids = new ArrayList<>();

            if (categorySubs != null && !categorySubs.isEmpty()) {

                for (FeaturedCategorySub fcs : categorySubs) {
                    List<CategoryItemHome> categoryItemHomes = fcs.getCategoryItemHomes();
                    if (categoryItemHomes != null && categoryItemHomes.size() > 0) {
                        for (CategoryItemHome categoryItemHome : categoryItemHomes) {
                            String idTarget = fcs.getId() + categoryItemHome.getItemId();
                            List<String> getImg = imageService.get(ImageType.FEATURED_CATEGORY, idTarget);
                            if (getImg != null && getImg.size() > 0) {
                                categoryItemHome.setImage(imageService.getUrl(getImg.get(0)).compress(100).getUrl());
                            }
                            ids.add(categoryItemHome.getItemId());
                        }
                    }
                    List<CategoryBannerHome> categoryBannerHomes = fcs.getCategoryBannerHomes();
                    if (categoryBannerHomes != null && categoryBannerHomes.size() > 0) {
                        for (CategoryBannerHome categoryBannerHome : categoryBannerHomes) {
                            List<String> getImg = imageService.get(ImageType.FEATURED_CATEGORY, categoryBannerHome.getId());
                            if (getImg != null && getImg.size() > 0) {
                                categoryBannerHome.setImage(imageService.getUrl(getImg.get(0)).compress(100).getUrl());
                            }
                        }
                    }
                    List<CategoryManufacturerHome> categoryManufacturerHomes = fcs.getCategoryManufacturerHomes();
                    if (categoryManufacturerHomes != null && categoryManufacturerHomes.size() > 0) {
                        int j = 1;
                        for (CategoryManufacturerHome categoryManufacturerHome : categoryManufacturerHomes) {
                            List<String> getImg = imageService.get(ImageType.MANUFACTURER, categoryManufacturerHome.getManufacturerId());
                            if (getImg != null && getImg.size() > 0) {
                                categoryManufacturerHome.setImage(imageService.getUrl(getImg.get(0)).compress(100).getUrl());
                            }
                            if (fc.getTemplate() != null && fc.getTemplate().equals("template3")) {
                                if (j == 1) {
                                    manufacturerIds = categoryManufacturerHome.getManufacturerId();
                                }
                            }
                            j++;
                        }
                    }
                    fcs.setCategoryItemHomes(categoryItemHomes);
                    fcs.setCategoryBannerHomes(categoryBannerHomes);
                    fcs.setCategoryManufacturerHomes(categoryManufacturerHomes);
                }

            }
            List<FeaturedCategorySub> fcses = new ArrayList<>();
            fcses.add(categorySubs.get(0));
            List<Item> listItems = itemService.list(ids);
            fc.setCategorySubs(fcses);
            fc.setItems(listItems);

            //Láº¥y dá»¯ liá»‡u tab chuyÃªn má»¥c con ngoÃ i trang chá»§
            List<String> cateId = new ArrayList<>();
            List<FeaturedCategorySub> featuredCategorySubs = featuredCategorySubService.getByCategoryIdActive(fc.getCategoryId());

            List<CategorySubHome> categorySubHomes = new ArrayList<>();
            int i = 1;
            for (FeaturedCategorySub cate : featuredCategorySubs) {
                CategorySubHome csh = new CategorySubHome();
                csh.setId(cate.getCategorySubId());
                csh.setName(cate.getCategorySubName());
                if (i == 1) {
                    csh.setPrimary(true);
                }
                categorySubHomes.add(csh);
                i++;
            }
            fc.setCategorySubHome(categorySubHomes);
            //Lấy Thương hiệu và model ra người template 3
            if (manufacturerIds != null && !manufacturerIds.equals("")) {
                if (featuredCategorySubs.get(0).getCategoryManufacturerHomes() != null && !featuredCategorySubs.get(0).getCategoryManufacturerHomes().isEmpty()) {
                    List<String> modelIds = featuredCategorySubs.get(0).getCategoryManufacturerHomes().get(0).getModelIds();
                    if (modelIds != null && !modelIds.isEmpty()) {
                        List<Model> models = modelService.getModels(modelIds);
                        if (models != null && models.size() > 0) {
                            int index = 0;
                            for (Model m : models) {
                                if (index > 0) {
                                    break;
                                }
                                index++;
                                List<String> get = imageService.get(ImageType.MODEL, m.getId());
                                List<String> image = new ArrayList<>();
                                if (get != null && get.size() > 0) {
                                    for (String img : get) {
                                        String url = imageService.getUrl(img).compress(100).getUrl();
                                        image.add(url);
                                    }
                                }
                                m.setImages(image);
                            }
                            ModelSearch modelSearch = new ModelSearch();
                            modelSearch.setStatus(1);
                            modelSearch.setModelId(models.get(0).getId());
                            models.get(0).setCountShop(itemService.getItemByModelCount(modelSearch));

                            fc.setModels(models);
                        }
                    }
                }

            }

        }
        //Láº¥y heart banner ra trang chá»§
        List<HeartBanner> heartBanners = heartBannerService.list();
        if (heartBanners != null && heartBanners.size() > 0) {
            for (HeartBanner heartBanner : heartBanners) {
                List<String> getImg = imageService.get(ImageType.HEART_BANNER, heartBanner.getId());
                List<String> getImgThumb = imageService.get(ImageType.HEART_BANNER_THUMB, heartBanner.getId());
                if ((getImg != null && getImg.size() > 0) && (getImgThumb != null && getImgThumb.size() > 0)) {
                    heartBanner.setImage(imageService.getUrl(getImg.get(0)).compress(100).getUrl());
                    heartBanner.setThumb(imageService.getUrl(getImgThumb.get(0)).thumbnail(60, 40, "outbound").compress(100).getUrl());
                }
            }
        }
        //Láº¥y home banner ra trang chá»§ homeBannerService
        List<HomeBanner> homeBanners = homeBannerService.list();
        if (homeBanners != null && homeBanners.size() > 0) {
            for (HomeBanner homeBanner : homeBanners) {
                List<String> getImg = imageService.get(ImageType.HOME_BANNER, homeBanner.getId());
                if (getImg != null && getImg.size() > 0) {
                    homeBanner.setImage(imageService.getUrl(getImg.get(0)).thumbnail(298, 140, "outbound").compress(100).getUrl());
                }
            }
        }
        //Lấy dữ liệu của tin tức nổi bật ra trang chủ
        List<FeaturedNews> featuredNewses = featuredNewsService.getAll(true);
        List<String> feLists = new ArrayList<>();
        if (featuredNewses != null && featuredNewses.size() > 0) {
            for (FeaturedNews featuredNews : featuredNewses) {
                feLists.add(featuredNews.getId());
            }
        }
        if (feLists != null && feLists.size() > 0) {
            Map<String, List<String>> image = imageService.get(ImageType.FEATURED_NEWS, feLists);
            for (Map.Entry<String, List<String>> entry : image.entrySet()) {
                String featuredNewsId = entry.getKey();
                List<String> featuredNewsImages = entry.getValue();
                for (FeaturedNews featuredNews : featuredNewses) {
                    if (featuredNews.getType() == 1) {
                        if (featuredNews.getId().equals(featuredNewsId) && featuredNewsImages != null && featuredNewsImages.size() > 0) {
                            featuredNews.setImage(imageService.getUrl(featuredNewsImages.get(0)).thumbnail(70, 70, "outbound").compress(100).getUrl());
                        }
                    }
                    if (featuredNews.getType() == 2) {
                        if (featuredNews.getId().equals(featuredNewsId) && featuredNewsImages != null && featuredNewsImages.size() > 0) {
                            featuredNews.setImage(imageService.getUrl(featuredNewsImages.get(0)).thumbnail(62, 62, "outbound").compress(100).getUrl());
                        }
                    }

                }
            }
        }
        NewsHomeBox homeBox = newsHomeBoxService.getAll();
        List<News> news = new ArrayList<>();
        if (homeBox != null) {
            List<String> list1 = homeBox.getItemIds();
            if (list1 != null && list1.size() > 0) {
                news = newsService.getByIds(list1.toArray(new String[0]));
            }
        }

        ItemSearch searchSame = new ItemSearch();
        searchSame.setStatus(1);
        searchSame.setCategoryIds(new ArrayList<String>());
        searchSame.getCategoryIds().add(null);
        searchSame.setPriceFrom(0);
        searchSame.setPriceTo(0);
        searchSame.setPageIndex(0);
        searchSame.setPageSize(5);
        searchSame.setModelIds(new ArrayList<String>());
        searchSame.setManufacturerIds(new ArrayList<String>());
        searchSame.setCityIds(new ArrayList<String>());
        searchSame.setProperties(new ArrayList<PropertySearch>());
        model.put("clientScript", "market.init({itemSearch:" + json.toJson(searchSame) + "});");
        model.put("newsHomeBoxs", news);
        model.put("itemSearch", searchSame);
        model.put("featuredNews", featuredNewses);
        model.put("homeBanners", homeBanners);
        model.put("heartBanners", heartBanners);
        model.put("bestDealBox", lsBestDealBox);
        model.put("featuredCategorys", featuredCategorys);
        model.put("topSellerBox", listSellerBoxs);
        model.put("hotDealBox", list);
        return "market.index";
    }
}
