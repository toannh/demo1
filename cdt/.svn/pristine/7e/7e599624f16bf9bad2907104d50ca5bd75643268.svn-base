package vn.chodientu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.ShopNews;
import vn.chodientu.entity.db.ShopNewsCategory;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.input.ShopNewsSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.ShopNewsCategoryRepository;
import vn.chodientu.repository.ShopNewsRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;

/**
 * @since Jun 6, 2014
 * @author Phu
 */
@Service
public class ShopNewsService {

    @Autowired
    private ShopNewsRepository shopNewsRepository;
    @Autowired
    private ShopNewsCategoryRepository shopNewsCategoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Validator validator;
    @Autowired
    private CashService cashService;
    @Autowired
    private ShopNewsCategoryRepository categoryRepository;
    @Autowired
    private Viewer viewer;
    @Autowired
    private ShopService shopService;

    @Async
    public void migrate(List<ShopNews> shopNewses) {
        for (ShopNews shopnew : shopNewses) {
            if (userRepository.exists(shopnew.getUserId())) {
                if (shopNewsCategoryRepository.exists(shopnew.getCategoryId())) {
                    shopnew.setImage(null);
                    shopNewsRepository.save(shopnew);
                    imageService.addTmp(shopnew.getImage(), ImageType.SHOP_NEWS, shopnew.getId());
                }
            }

        }
    }

    /**
     * Lấy tin tức theo danh mục
     *
     * @param newsCategoryId
     * @param page
     * @param id
     * @param active
     * @return
     */
    public DataPage<ShopNews> getByNewsCategory(String newsCategoryId, Pageable page, String id, int active) {
        DataPage<ShopNews> news = new DataPage<>();
        news.setDataCount(shopNewsRepository.countShopNewsCategory(newsCategoryId, active));
        news.setPageIndex(page.getPageNumber());
        news.setPageSize(page.getPageSize());
        news.setPageCount(news.getDataCount() / page.getPageSize());
        if (news.getDataCount() % page.getPageSize() != 0) {
            news.setPageCount(news.getPageCount() + 1);
        }
        List<ShopNews> list = shopNewsRepository.getByShopNewsCategory(newsCategoryId, page, id, active);
        for (ShopNews shopNews : list) {
            List<String> image = imageService.get(ImageType.SHOP_NEWS, shopNews.getId());
            if (image != null && !image.isEmpty()) {
                shopNews.setImage(image.get(0));
            }
        }
        news.setData(list);
        return news;
    }

    /**
     * Lấy chi tiết tin tức
     *
     * @param newsId
     * @return
     */
    public ShopNews get(String newsId) {
        ShopNews news = shopNewsRepository.find(newsId);
        return news;
    }

    /**
     * Thêm mới tin tức
     *
     * @param news
     * @param user
     * @return
     */
    public Response add(ShopNews news, User user) {
        Map<String, String> error = validator.validate(news);
        if (news.getCategoryId() != null && !news.getCategoryId().equals("")) {
            ShopNewsCategory cate = categoryRepository.find(news.getCategoryId());
            if (cate == null) {
                error.put("categoryId", "Danh mục tin tức không tồn tại");
            } else {
                news.setCategoryPath(cate.getPath());
            }
        }
        if (!error.isEmpty()) {
            return new Response(false, "Dữ liệu chưa chính xác, không thể thêm mới tin tức", error);
        }
        news.setUserId(user.getId());
        news.setCreateTime(new Date().getTime());
        news.setUpdateTime(new Date().getTime());
        shopNewsRepository.save(news);
        try {
            Shop shop = shopService.getShop(user.getId());
            cashService.reward(CashTransactionType.SELLER_POST_NEWS, viewer.getUser().getId(), news.getId(), "/" + (shop != null ? shop.getAlias() : "") + "/news/" + news.getId() + "/" + TextUtils.createAlias(news.getTitle()) + ".html", null);
            return new Response(true, "Bài tin đã được thêm thành công trên hệ thống", news);
        } catch (Exception e) {
            return new Response(true, "POST_NEWS_FAIL", news);
        }

    }

    /**
     * Sửa bài viết
     *
     * @param news
     * @param user
     * @return
     */
    public Response edit(ShopNews news, User user) {
        ShopNews shopnews = shopNewsRepository.find(news.getId());
        if (shopnews == null) {
            return new Response(false, "Không tìm thấy tin tức trên hệ thống", news);
        }

        Map<String, String> error = validator.validate(news);
        if (news.getCategoryId() != null && !news.getCategoryId().equals("")) {
            ShopNewsCategory cate = categoryRepository.find(news.getCategoryId());
            if (cate == null) {
                error.put("categoryId", "Danh mục tin tức không tồn tại");
            } else {
                news.setCategoryPath(cate.getPath());
            }
        }
        if (!error.isEmpty()) {
            return new Response(false, "Dữ liệu chưa chính xác, không thể cập nhật thông tin", error);
        }
        shopnews.setActive(news.isActive());
        shopnews.setCategoryId(news.getCategoryId());
        shopnews.setCategoryPath(news.getCategoryPath());
        shopnews.setDetail(news.getDetail());
        shopnews.setTitle(news.getTitle());
        shopnews.setUserId(user.getId());
        shopnews.setUpdateTime(System.currentTimeMillis());
        shopNewsRepository.save(news);
        return new Response(true, "Bài tin đã được cập nhật thành công trên hệ thống", news);
    }

    /**
     * Xóa theo danh sách id
     *
     * @param ids
     * @param seller
     * @return
     */
    public Response remove(List<String> ids, User seller) {
        shopNewsRepository.delete(new Query(new Criteria("_id").in(ids).and("userId").is(seller.getId())));
        for (String id : ids) {
            imageService.delete(ImageType.SHOP_NEWS, id);
        }
        return new Response(true, "Danh sách tin tức đã được xóa thành công");
    }

    /**
     * Xóa tin tức theo id
     *
     * @param id
     * @param seller
     * @return
     */
    public Response remove(String id, User seller) {
        shopNewsRepository.delete(new Query(new Criteria("_id").is(id).and("userId").is(seller.getId())));
        imageService.delete(ImageType.SHOP_NEWS, id);
        return new Response(true, "Tin tức đã được xóa thành công");
    }

    /**
     * Thêm mới ảnh
     *
     * @param id
     * @param file
     * @return
     */
    public Response addImage(String id, MultipartFile file) {
        return imageService.upload(file, ImageType.SHOP_NEWS, id);
    }

    /**
     * Xóa hình ảnh
     *
     * @param id
     * @param file
     * @return
     */
    public Response editImage(String id, MultipartFile file) {
        List<String> images = imageService.get(ImageType.SHOP_NEWS, id);
        Response resp = imageService.upload(file, ImageType.SHOP_NEWS, id);
        if (resp.isSuccess()) {
            for (String img : images) {
                imageService.delete(ImageType.SHOP_NEWS, img);
            }
        }
        return resp;
    }

    /**
     * Danh sách bài tin của shop có phân trang
     *
     * @param search
     * @return
     */
    public DataPage<ShopNews> search(ShopNewsSearch search) {
        Criteria cri = new Criteria();
        cri.and("userId").is(search.getUserId());
        if (search.getTitle() != null && !search.getTitle().equals("")) {
            cri.and("title").regex(search.getTitle());
        }

        if (search.getCreateTimeTo() <= search.getCreateTimeFrom()) {
            if (search.getCreateTimeFrom() > 0) {
                cri.and("createTime").gte(search.getCreateTimeFrom());
            }
        } else if (search.getCreateTimeTo() > search.getCreateTimeFrom()) {
            cri.and("createTime").gte(search.getCreateTimeFrom()).lte(search.getCreateTimeTo());
        }

        if (search.getCategoryId() != null && !search.getCategoryId().equals("")) {
            List<ShopNewsCategory> categorys = categoryRepository.getDescendants(search.getCategoryId());
            List<String> ids = new ArrayList<String>();
            for (ShopNewsCategory cate : categorys) {
                ids.add(cate.getId());
            }
            cri.and("categoryId").in(ids.toArray());
        }
        Query query = new Query(cri);
        query.with(new Sort(Sort.Direction.ASC, "createTime"));
        query.skip(search.getPageIndex() * search.getPageSize()).limit(search.getPageSize());
        DataPage<ShopNews> page = new DataPage<>();
        page.setDataCount(shopNewsRepository.count(query));
        page.setPageSize(search.getPageSize());
        page.setPageIndex(search.getPageIndex());
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }
        List<ShopNews> listnews = shopNewsRepository.find(query);

        page.setData(listnews);
        return page;
    }
}
