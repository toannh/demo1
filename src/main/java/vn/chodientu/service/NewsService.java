package vn.chodientu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.News;
import vn.chodientu.entity.db.NewsCategory;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.NewsForm;
import vn.chodientu.entity.input.NewsSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.NewsCategoryRepository;
import vn.chodientu.repository.NewsHomeBoxRepository;
import vn.chodientu.repository.NewsRepository;

/**
 * Business service cho Danh mục
 *
 * @author Phuongdt
 * @since Jun 14, 2013
 */
@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsCategoryRepository categoryRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Viewer viewer;

    /**
     * Thêm mới Tin tức
     *
     * @param newsForm
     * @return
     */
    public Response add(NewsForm newsForm) {
        Map<String, String> errors = validator.validate(newsForm);
        String idUser = null;
        if (viewer.getAdministrator() == null) {
            idUser = "test";
        } else {
            idUser = viewer.getAdministrator().getEmail();
        }
        News news = new News();
        String genId = newsRepository.genId();
        news.setId(genId);
        news.setActive(newsForm.isActive());
        if (newsForm.getCategoryId1() != null && !newsForm.getCategoryId1().equals("")) {
            news.setCategoryId(newsForm.getCategoryId1());
        } else {
            if (newsForm.getCategoryId() != null && !newsForm.getCategoryId().trim().equals("")) {
                news.setCategoryId(newsForm.getCategoryId());
            }
        }

        news.setUser(idUser);
        news.setTitle(newsForm.getTitle());
        news.setDetail(newsForm.getDetail());
        news.setKeywords(newsForm.getKeywords());
        news.setCreateTime(new Date().getTime());
        news.setUpdateTime(new Date().getTime());
        if (errors.isEmpty()) {
            if (newsForm.getImagefile().getSize() > 0) {
                imageService.upload(newsForm.getImagefile(), ImageType.NEWS, genId);
            }
            newsRepository.save(news);
            return new Response(true, "Thêm mới tin tức thành công", news);
        } else {
            return new Response(false, "Thêm mới thất bại", errors);
        }
    }

    /**
     * Delete news
     *
     * @param id
     * @return
     */
    public Response delete(String id) throws Exception {
        News oldNews = newsRepository.find(id);
        imageService.deleteById(ImageType.NEWS, oldNews.getImage(), oldNews.getImage());
        newsRepository.delete(oldNews);
        return new Response(true, "Xóa tin thành công");
    }

    /**
     * Sửa tin tức
     *
     * @param editForm
     * @return
     */
    public Response edit(NewsForm editForm) {
        Map<String, String> error = validator.validate(editForm);
        News oldNews = newsRepository.find(editForm.getId());
        if (oldNews == null) {
            error.put("id", "Tin không tồn tại");
        }

        if (error.isEmpty()) {
            if (editForm.getImagefile().getSize() > 0) {
                List<String> get = imageService.get(ImageType.NEWS, editForm.getId());
                if (get != null && get.size() > 0) {
                    imageService.deleteById(ImageType.NEWS, editForm.getId(), get.get(0));
                }
                imageService.upload(editForm.getImagefile(), ImageType.NEWS, editForm.getId());
            }
            if (editForm.getCategoryId1() != null && !editForm.getCategoryId1().equals("")) {
                oldNews.setCategoryId(editForm.getCategoryId1());
            } else {
                if (editForm.getCategoryId() != null && !editForm.getCategoryId().trim().equals("")) {
                    oldNews.setCategoryId(editForm.getCategoryId());
                }
            }
            oldNews.setTitle(editForm.getTitle());
            oldNews.setActive(editForm.isActive());
            oldNews.setDetail(editForm.getDetail());
            oldNews.setKeywords(editForm.getKeywords());
            oldNews.setUpdateTime(new Date().getTime());
            newsRepository.save(oldNews);
            return new Response(true, "Sửa tin thành công", oldNews);
        } else {
            return new Response(false, "Sửa tin thất bại", error);
        }
    }

    /**
     * Thay đổi trạng thái ẩn/ hiện cho 1 bản tin
     *
     * @param editForm
     * @return
     */
    public Response editStatus(String id) {
        Map<String, String> error = new HashMap<>();
        News oldNews = newsRepository.find(id);
        if (oldNews == null) {
            error.put("id", "Tin không tồn tại");
        }

        if (error.isEmpty()) {
            oldNews.setActive(!oldNews.isActive());

            oldNews.setUpdateTime(new Date().getTime());
            newsRepository.save(oldNews);
            return new Response(true, "Sửa trạng thái thành công", oldNews);
        } else {
            return new Response(false, "Sửa trạng thái thất bại", error);
        }
    }

    /**
     * Lấy ra 1 bản tin theo id
     *
     * @param category
     * @return
     * @throws Exception
     */
    public News getById(String id) throws Exception {
        News news = newsRepository.find(id);
        if (news == null) {
            throw new Exception("Tin tức không tồn tại");
        }
        List<String> get = imageService.get(ImageType.NEWS, id);
        if (get != null && get.size() > 0) {
            String url = imageService.getUrl(get.get(0)).thumbnail(100, 100, "outbound").getUrl();
            news.setImage(url);
        }
        NewsCategory getCategory = categoryRepository.get(news.getCategoryId());
        if (getCategory.getLevel() > 0) {
            news.setCategoryId1(news.getCategoryId());
            news.setCategoryId(getCategory.getParentId());
        } else {
            news.setCategoryId1(news.getCategoryId());
            news.setCategoryId(news.getCategoryId());

        }
        return news;
    }

    /**
     * Lấy tin tức theo danh mục
     *
     * @param cids
     * @return
     */
    public List<News> getByCateId(String[] cids) {
        return newsRepository.findByCategory(cids);
    }

    /**
     * Lấy tin tức theo ids
     *
     * @param ids
     * @return
     */
    public List<News> getByIds(String[] ids) {
        return newsRepository.getByIds(ids);
    }

    /**
     * Lấy tất cả tin tức không có điều kiện
     *
     * @return
     */
    public List<News> list() {
        return newsRepository.listAll();
    }

    /**
     * Lọc danh sách tin tức
     *
     * @param newsSearch
     * @return
     */
    public DataPage<News> search(NewsSearch newsSearch) {
        Criteria cri = new Criteria();
        if (newsSearch.getId() != null && !newsSearch.getId().trim().equals("")) {
            cri.and("id").regex(".*" + newsSearch.getId() + ".*", "i");
        }
        if (newsSearch.getTitle() != null && !newsSearch.getTitle().trim().equals("")) {
            cri.and("title").regex(".*" + newsSearch.getTitle() + ".*", "i");
        }
        if (newsSearch.getUser() != null && !newsSearch.getUser().trim().equals("")) {
            cri.and("user").regex(".*" + newsSearch.getUser() + ".*", "i");
        }
        if (newsSearch.getTextSearch() != null && !newsSearch.getTextSearch().trim().equals("")) {
            cri.and("keywords").regex(".*" + newsSearch.getTextSearch() + ".*", "i");
        }
        if (newsSearch.getActive() == 1) {
            cri.and("active").is(true);
        }
        if (newsSearch.getActive() == 2) {
            cri.and("active").is(false);
        }
        if (newsSearch.getPageIndex() < 0) {
            newsSearch.setPageIndex(0);
        }
        if (newsSearch.getPageSize() < 1) {
            newsSearch.setPageSize(1);
        }

        if (newsSearch.getCategoryIds() != null && !newsSearch.getCategoryIds().trim().equals("")) {
            if (newsSearch.getCategoryId1() != null && !newsSearch.getCategoryId1().equals("")) {
                cri.and("categoryId").is(newsSearch.getCategoryId1());
            } else {
                if (newsSearch.getCategoryIds() != null && !newsSearch.getCategoryIds().trim().equals("")) {
                    cri.and("categoryId").regex(".*" + newsSearch.getCategoryIds() + ".*", "i");
                } else {
                    List<NewsCategory> descendants = categoryRepository.getDescendants(newsSearch.getCategoryIds());
                    List<String> ids = new ArrayList<String>();
                    for (NewsCategory cate : descendants) {
                        ids.add(cate.getId());
                    }
                    ids.add(newsSearch.getCategoryIds());
                    cri.and("categoryId").in(ids.toArray());
                }
            }

        }

        DataPage<News> news = new DataPage<>();
        news.setDataCount(newsRepository.count(new Query(cri)));
        news.setPageIndex(news.getPageIndex());
        news.setPageSize(news.getPageSize());
        news.setPageCount(news.getDataCount() / newsSearch.getPageSize());
        if (news.getDataCount() % newsSearch.getPageSize() != 0) {
            news.setPageCount(news.getPageCount() + 1);
        }
        List<News> list = newsRepository.list(cri, newsSearch.getPageSize(), newsSearch.getPageIndex() * newsSearch.getPageSize());
        news.setData(list);
        return news;
    }
}
