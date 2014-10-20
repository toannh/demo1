package vn.chodientu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.data.CrawlProperty;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemCrawl;
import vn.chodientu.entity.db.ItemDetail;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.ShopCategory;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.ItemSource;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.CategoryRepository;
import vn.chodientu.repository.ItemCrawlRepository;
import vn.chodientu.repository.ItemDetailRepository;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.SellerRepository;
import vn.chodientu.repository.ShopCategoryRepository;
import vn.chodientu.repository.ShopRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;

@Service
public class CrawlService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemDetailRepository detailRepository;
    @Autowired
    private SearchIndexService searchIndexService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ShopCategoryRepository shopCategoryRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ItemCrawlRepository crawlRepository;

    /**
     * build data crawl
     *
     * @param s
     * @return
     */
    public String decode(String s) {
        return StringUtils.newStringUtf8(Base64.decodeBase64(s));
    }

    public Item update(List<CrawlProperty> data) throws Exception {
        if (data == null || data.isEmpty()) {
            throw new Exception("Data không tồn tại dữ liệu");
        }
        String url = null;
        for (CrawlProperty property : data) {
            if (property.getName().equals("url")) {
                url = this.decode(property.getValue());
                break;
            }
        }
        Item item = itemRepository.getBySellerSku(url);
        HashMap<String, String> error = new HashMap<>();
        if (item == null) {
            error.put("itemId", "Sản phẩm không tồn tại sản phẩm, Không thể cập nhật");
            this.crawlLog(item, data, "UPDATE", error);
            return item;
        }
        if (item.getSource() == null || !item.getSource().equals(ItemSource.CRAWL)) {
            error.put("itemId", "Sản phẩm không phải sản phẩm crawl");
            this.crawlLog(item, data, "UPDATE", error);
            return item;
        }
        if (item.getEndTime() == 0) {
            item.setEndTime(item.getStartTime());
        }
        item.setEndTime(item.getEndTime() + 30 * 12 * 60 * 60 * 1000);
        if (item.getEndTime() < System.currentTimeMillis()) {
            item.setEndTime(System.currentTimeMillis() + 30 * 12 * 60 * 60 * 1000);
        }
        item.setUpdateTime(System.currentTimeMillis());
        int usd = 1;
        for (CrawlProperty crawlProperty : data) {
            String value = this.decode(crawlProperty.getValue());
            switch (crawlProperty.getName()) {
                case "price_buynow":
                    try {
                        long price = Long.parseLong(value);
                        if (price > 0) {
                            item.setSellPrice(price);
                        }
                    } catch (NumberFormatException e) {
                    }
                    break;
                case "price_buynow1":
                    try {
                        long price = Long.parseLong(value);
                        if (price > 0) {
                            item.setStartPrice(price);
                        }
                    } catch (NumberFormatException e) {
                    }

                    break;
                case "currency_id": //Tỉ giá
                    try {
                        if (Integer.parseInt(value) == 3) {
                            usd = 21000;
                        }
                    } catch (Exception e) {
                    }
                    this.buildcurrency(item, value);
                    break;
            }
        }
        item.setStartPrice(item.getStartPrice() * usd);
        item.setSellPrice(item.getSellPrice() * usd);

        if (item.getSellPrice() == 0 && item.getStartPrice() > 0) {
            item.setSellPrice(item.getStartPrice());
        }
        this.validate(item);
        itemRepository.save(item);
        searchIndexService.processIndexItem(item);
        this.crawlLog(item, data, "UPDATE", error);
        return item;
    }

    public Item save(List<CrawlProperty> data) throws Exception {
        if (data == null || data.isEmpty()) {
            throw new Exception("Data không tồn tại dữ liệu");
        }
        String url = null;
        for (CrawlProperty property : data) {
            if (property.getName().equals("url")) {
                url = this.decode(property.getValue());
                break;
            }
        }
        Item item = itemRepository.getBySellerSku(url);
        ItemDetail itemDetail = new ItemDetail();
        HashMap<String, String> error = new HashMap<>();
        if (item != null) {
//            error.put("itemId", "Đã tồn tại sản phẩm, Không thể thêm mới");
//            this.crawlLog(item, data, "CREATE", error);
            this.update(data);
            return item;
        }
        item = new Item();
        item.setId(itemRepository.genId());
        item.setQuantity(10);
        item.setListingType(ListingType.BUYNOW);
        item.setStartTime(System.currentTimeMillis());
        item.setEndTime(item.getStartTime() + 30 * 12 * 60 * 60 * 1000);
        item.setSellerSku(url);
        itemDetail.setItemId(item.getId());
        item.setApproved(true);
        item.setCompleted(true);
        item.setActive(true);
        item.setCreateTime(System.currentTimeMillis());
        item.setSource(ItemSource.CRAWL);
        for (CrawlProperty crawlProperty : data) {
            try {
                this.build(item, itemDetail, crawlProperty);
            } catch (Exception e) {
                error.put(crawlProperty.getName(), e.getMessage());
            }
        }

        if (item.getSellPrice() == 0 && item.getStartPrice() > 0) {
            item.setSellPrice(item.getStartPrice());
        }
        this.validate(item);
        detailRepository.save(itemDetail);
        item.setUpdateTime(System.currentTimeMillis());
        item.setUpTime(0);
        itemRepository.save(item);
        searchIndexService.processIndexItem(item);
        this.crawlLog(item, data, "CREATE", error);
        return item;
    }

    private void build(Item item, ItemDetail itemDetail, CrawlProperty property) throws Exception {
        String value = this.decode(property.getValue()).trim();
        int usd = 1;
        switch (property.getName()) {
            case "title":
                if (value != null && !value.equals("")) {
                    item.setName(value);
                }
                break;
            case "price_buynow":
                try {
                    long price = Long.parseLong(value);
                    if (price > 0) {
                        item.setSellPrice(price);
                    }
                } catch (NumberFormatException e) {
                }
                break;
            case "price_buynow1":
                try {
                    long price = Long.parseLong(value);
                    if (price > 0) {
                        item.setStartPrice(price);
                    }
                } catch (NumberFormatException e) {
                }

                break;
            case "desc":
                this.buildDescription(item, itemDetail, value);
                break;
            case "url":
                break;
            case "seller":
                this.buildSeller(item, value);
                break;
            case "currency_id": //Tỉ giá
                try {
                    if (Integer.parseInt(value) == 3) {
                        usd = 21000;
                    }
                } catch (Exception e) {
                }
                this.buildcurrency(item, value);
                break;
            case "category_id":
                this.buildCategory(item, value);
                break;
            case "e_category_id":
                this.buildShopCategory(item, value);
                break;
            default:
                if (property.getName().contains("image_url")) {
                    this.buildImage(item, value);
                }
                break;
        }
        item.setStartPrice(item.getStartPrice() * usd);
        item.setSellPrice(item.getSellPrice() * usd);
    }

    public void buildDescription(Item item, ItemDetail itemDetail, String desc) {
        Pattern p = null;
        Matcher m = null;
        String img = null;

        p = Pattern.compile(".*<img[^>]*src=\"([^\"]*)", Pattern.CASE_INSENSITIVE);
        m = p.matcher(desc);
        while (m.find()) {
            img = m.group(1);
            try {
                desc = desc.replaceAll(m.group(1), imageService.getUrl(this.buildImage(item, img)).getUrl());
            } catch (Exception e) {
            }
        }
        itemDetail.setDetail(desc);
    }

    private String buildImage(Item item, String imgUrl) throws Exception {
        if (imgUrl == null || imgUrl.equals("")) {
            return null;
        }
        if (!imgUrl.startsWith("http")) {
            if (!imgUrl.startsWith("/")) {
                imgUrl = "/" + imgUrl;
            }
            imgUrl = "http://" + TextUtils.getDomainName(item.getSellerSku()) + imgUrl;
        }
        if (item.getImages() == null || item.getImages().isEmpty()) {
            item.setImages(new ArrayList<String>());
        }
        Response resp = imageService.download(imgUrl, ImageType.ITEM, item.getId());
        if (resp.isSuccess()) {
            String imgId = (String) resp.getData();
            item.getImages().add(imgId);
            return imgId;
        }
        return null;
    }

    private void buildCategory(Item item, String categoryId) throws Exception {
        Category category = categoryRepository.find(categoryId);
        if (category == null) {
            throw new Exception("Danh mục không tồn tại");
        }
        item.setCategoryId(category.getId());
        item.setCategoryName(category.getName());
        item.setCategoryPath(category.getPath());
    }

    private void buildShopCategory(Item item, String scategoryId) throws Exception {
        ShopCategory category = shopCategoryRepository.find(scategoryId);
        if (category == null) {
            throw new Exception("Danh mục shop không tồn tại");
        }
        item.setShopCategoryId(category.getId());
        item.setShopCategoryPath(category.getPath());
    }

    /**
     * Tỉ giá (1: vnd, 3: $)
     *
     * @param item
     * @param currency
     * @throws Exception
     */
    private void buildcurrency(Item item, String currency) throws Exception {

    }

    private void buildSeller(Item item, String sellerName) throws Exception {
        User user = userRepository.findByUsername(sellerName);
        if (user == null) {
            user = userRepository.find(sellerName);
            if (user == null) {
                throw new Exception("Không tìm thấy người bán");
            }
        }
        item.setSellerId(user.getId());
        Seller seller = sellerRepository.getById(user.getId());
        if (seller != null) {
            item.setCod(seller.isScIntegrated());
            item.setOnlinePayment(seller.isNlIntegrated());
            item.setShipmentPrice(seller.getShipmentPrice());
            item.setShipmentType(seller.getShipmentType());
        }
        Shop shop = shopRepository.findByUser(user.getId());
        if (shop != null) {
            item.setShopDescription(shop.getDescription());
            item.setShopName(shop.getTitle());
        }
    }

    private void crawlLog(Item item, List<CrawlProperty> data, String type, HashMap<String, String> error) {
        ItemCrawl itemCrawl = new ItemCrawl();
        itemCrawl.setData(data);
        itemCrawl.setCreateTime(System.currentTimeMillis());
        itemCrawl.setType(type != null && !type.equals("") ? type : "UPDATE");
        itemCrawl.setError(error);
        if (item != null) {
            itemCrawl.setItemId(item.getId());
        }
        crawlRepository.save(itemCrawl);
    }

    private void validate(Item item) {
        boolean appro = true;
        if (item.getName() == null || item.getName().equals("")) {
            appro = false;
        }
        if (item.getEndTime() < System.currentTimeMillis()) {
            item.setEndTime(System.currentTimeMillis() + 12 * 24 * 60 * 60 * 1000);
        }
        if (item.getSellPrice() <= 0) {
            appro = false;
        }
        List<String> images = imageService.get(ImageType.ITEM, item.getId());
        if (images == null || images.size() == 0) {
            appro = false;
        }
        ItemDetail itemDetail = detailRepository.find(item.getId());
        if (itemDetail == null) {
            appro = false;
        }
        item.setApproved(appro);
    }
}
