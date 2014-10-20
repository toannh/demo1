package vn.chodientu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.input.NewsCategorySearch;
import vn.chodientu.entity.db.News;
import vn.chodientu.entity.db.NewsCategory;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.NewsCategoryRepository;
import vn.chodientu.repository.NewsRepository;

@Service
public class NewsCategoryService {

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private Validator validator;

    /**
     * Lấy danh mục theo list id
     *
     * @param ids
     * @return @throws Exception
     */
    public List<NewsCategory> get(String[] ids) throws Exception {
        return newsCategoryRepository.get(ids);
    }

    /**
     * Lấy danh mục theo id
     *
     * @param id
     * @return
     * @throws Exception
     */
    public NewsCategory getById(String id) throws Exception {
        NewsCategory categoryNews = newsCategoryRepository.find(id);
        if (categoryNews == null) {
            throw new Exception("Danh mục tin không tồn tại");
        }
        return categoryNews;
    }

    /**
     * Thêm mới dành mục tin tức
     *
     * @param newsCategory
     * @return
     */
    public Response add(@RequestBody NewsCategory newsCategory) {

        Map<String, String> error = validator.validate(newsCategory);
        NewsCategory parentNewCategory = null;
        if (newsCategory.getParentId() != null) {
            parentNewCategory = newsCategoryRepository.find(newsCategory.getParentId());
            if (parentNewCategory == null) {
                //error.put("parentId", "Danh mục cha không tồn tại");
            } else {
                newsCategory.setLevel(parentNewCategory.getLevel() + 1);
            }
        } else {
            newsCategory.setLevel(1);
        }

        if (error.isEmpty()) {
            newsCategory.setId(newsCategoryRepository.genId());
            newsCategoryRepository.save(newsCategory);
            if (parentNewCategory == null) {
                newsCategory.setPath(new ArrayList<String>());
                newsCategory.getPath().add(newsCategory.getId());
            } else {
                newsCategoryRepository.save(parentNewCategory);
                newsCategory.setPath(parentNewCategory.getPath());
                newsCategory.getPath().add(newsCategory.getId());
            }
            newsCategory.setCreateTime(new Date().getTime());
            newsCategory.setUpdateTime(new Date().getTime());
            newsCategoryRepository.save(newsCategory);
            return new Response(true, "Thêm danh mục tin tức thành công", newsCategory);
        }

        return new Response(true, "Có lỗi khi thêm mới danh mục", error);

    }

    public Response edit(NewsCategory newsCategory) {
        Map<String, String> error = validator.validate(newsCategory);
        NewsCategory oldNewsCategory = newsCategoryRepository.find(newsCategory.getId());
        if (oldNewsCategory == null) {
            error.put("id", "Danh mục không tồn tại");
        } else {
            if (oldNewsCategory.getParentId() == null ? newsCategory.getParentId() != null : !oldNewsCategory.getParentId().equals(newsCategory.getParentId())) {
                if (newsCategory.getParentId() != null && !"0".equals(newsCategory.getParentId())) {
                    NewsCategory parentNewsCategory = newsCategoryRepository.find(newsCategory.getParentId());
                    if (parentNewsCategory == null) {
                        error.put("parentId", "Danh mục cha không tồn tại");
                    } else {
                        if (!newsCategory.getId().equals(newsCategory.getParentId())) {
                            List<NewsCategory> descendantCategory = newsCategoryRepository.getDescendants(newsCategory.getId());
                            for (NewsCategory c : descendantCategory) {
                                c.setParentId(null);
                                c.setLevel(1);
                                c.setPath(null);
                                newsCategoryRepository.save(c);
                            }
                            newsCategory.setLevel(parentNewsCategory.getLevel() + 1);
                            newsCategory.setPath(parentNewsCategory.getPath());
                            newsCategory.getPath().add(newsCategory.getId());
                        } else {
                            newsCategory.setLevel(oldNewsCategory.getLevel());
                            newsCategory.setPath(oldNewsCategory.getPath());
                            newsCategory.setParentId(oldNewsCategory.getParentId());
                        }
                    }
                } else {
                    newsCategory.setLevel(1);
                    newsCategory.setPath(new ArrayList<String>());
                    newsCategory.getPath().add(newsCategory.getId());
                }
            } else {
                newsCategory.setLevel(oldNewsCategory.getLevel());
                newsCategory.setPath(oldNewsCategory.getPath());
            }
        }

        if (error.isEmpty()) {
            Date time = new Date();
            newsCategory.setCreateTime(oldNewsCategory.getCreateTime());
            newsCategory.setUpdateTime(time.getTime());
            newsCategoryRepository.save(newsCategory);
            return new Response(true, "Cập nhật danh mục thành công", newsCategory);
        } else {
            return new Response(false, "Thông tin không hợp lệ", error);
        }

    }

    /**
     * Delete categorynews
     *
     * @return
     */
    public Response delCat(String id) throws Exception {
        NewsCategory categorynews = newsCategoryRepository.find(id);
        if (categorynews == null) {
            return new Response(false, "Danh mục không tồn tại");
        }
        if (!newsCategoryRepository.getChilds(categorynews.getId()).isEmpty()) {
            return new Response(true, "Danh mục có danh mục con không được xóa");
        }
        List<News> find = newsRepository.find(new Query(new Criteria("categoryId").is(categorynews.getId())));
        for (News news : find) {
            news.setCategoryId("");
            newsRepository.save(news);
        }
        newsCategoryRepository.delete(id);
        return new Response(true, "Xóa danh mục tin thành công");
    }

    /**
     * Sửa trạng thái hiển thị của danh mục
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Response editStatus(String id) throws Exception {
        NewsCategory find = newsCategoryRepository.find(id);
        find.setActive(!find.isActive());
        newsCategoryRepository.save(find);
        return new Response(true, "Sửa trạng thái thành công", find);
    }

    /**
     * Sửa vị trí hiển thị
     *
     * @param id
     * @param position
     * @return
     */
    public Response changePosition(String id, String position) {
        NewsCategory find = newsCategoryRepository.find(id);
        try {
            find.setPosition(Integer.parseInt(position));
            newsCategoryRepository.save(find);
        } catch (NumberFormatException ex) {
            return new Response(false, "Vị trí phải là số");
        }
        return new Response(true, "Sửa vị trí thành công", find);

    }

    /**
     * Lọc danh sách danh mục tin tức
     *
     * @param categoryNewsSearch
     * @return
     */
    public DataPage<NewsCategory> search(NewsCategorySearch categoryNewsSearch) {
        Criteria cri = new Criteria();
        if (categoryNewsSearch.getId() != null && !categoryNewsSearch.getId().trim().equals("")) {
            cri.and("id").regex(".*" + categoryNewsSearch.getId() + ".*", "i");
        }
        if (categoryNewsSearch.getName() != null && !categoryNewsSearch.getName().trim().equals("")) {
            cri.and("name").regex(".*" + categoryNewsSearch.getName() + ".*", "i");
        }
        if (categoryNewsSearch.getParentId() != null && !categoryNewsSearch.getParentId().trim().equals("0")) {
            cri.and("parentId").regex(".*" + categoryNewsSearch.getParentId() + ".*", "i");
        }
        if (categoryNewsSearch.getActive() == 1) {
            cri.and("active").is(true);
        }
        if (categoryNewsSearch.getActive() == 2) {
            cri.and("active").is(false);
        }
        if (categoryNewsSearch.getLevel() >= 0) {
            cri.and("level").is(categoryNewsSearch.getLevel());
        }
        if (categoryNewsSearch.getPageIndex() < 0) {
            categoryNewsSearch.setPageIndex(0);
        }
        if (categoryNewsSearch.getPageSize() < 1) {
            categoryNewsSearch.setPageSize(1);
        }
        DataPage<NewsCategory> categoryNews = new DataPage<>();
        categoryNews.setDataCount(newsCategoryRepository.count(new Query(cri)));
        categoryNews.setPageIndex(categoryNews.getPageIndex());
        categoryNews.setPageSize(categoryNews.getPageSize());
        categoryNews.setPageCount(categoryNews.getDataCount() / categoryNewsSearch.getPageSize());
        if (categoryNews.getDataCount() % categoryNewsSearch.getPageSize() != 0) {
            categoryNews.setPageCount(categoryNews.getPageCount() + 1);
        }
        List<NewsCategory> list = newsCategoryRepository.list(cri, categoryNewsSearch.getPageSize(), categoryNewsSearch.getPageIndex() * categoryNewsSearch.getPageSize());
        for (NewsCategory newsCategory : list) {

            if (newsCategory.getParentId() != null && !"0".equals(newsCategory.getParentId())) {
                newsCategory.setParentId(newsCategoryRepository.find(newsCategory.getParentId()).getName());
            } else {
                newsCategory.setParentId("");
            }
        }
        categoryNews.setData(list);

        return categoryNews;
    }

    /**
     * Lấy các danh mục con theo id của cha
     *
     * @param id
     * @return
     */
    public Response getChilds(String id) {
        return new Response(true, null, newsCategoryRepository.getChilds(id));
    }

    public List<NewsCategory> getAll() {
        return newsCategoryRepository.getAll();
    }
    /**
     * Get all with position sort
     */
    public List<NewsCategory> getAllWithPositionSort() {
        return newsCategoryRepository.getAllWithPositionSort();
    }
}
