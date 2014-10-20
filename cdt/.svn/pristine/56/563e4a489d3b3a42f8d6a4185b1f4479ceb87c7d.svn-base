package vn.chodientu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.News;
import vn.chodientu.entity.db.NewsHomeBox;
import vn.chodientu.entity.form.NewsHomeBoxForm;
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
public class NewsHomeBoxService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsHomeBoxRepository newsHomeBoxRepository;
    @Autowired
    private Validator validator;

    /**
     * *
     * Thêm tin tức vào box ngoài trang Home
     *
     * @param newsHomeBoxForm
     * @return
     * @throws Exception
     */
    public Response addNewHomeBox(NewsHomeBoxForm newsHomeBoxForm) throws Exception {
        Map<String, String> errors = validator.validate(newsHomeBoxForm);
        News news = newsRepository.find(newsHomeBoxForm.getId());
        if (news == null) {
            errors.put("id", "Không tồn tại tin tức trên hệ thống");
        }
        if (!errors.isEmpty()) {
            return new Response(false, null, errors);
        } else {
            NewsHomeBox homeBox = newsHomeBoxRepository.getOne();
            NewsHomeBox newsHomeBox = null;
            if (homeBox == null) {
                newsHomeBox = new NewsHomeBox();
                newsHomeBox.setId(newsHomeBoxRepository.genId());
                newsHomeBoxRepository.save(newsHomeBox);
            }
            List<String> itemIds = homeBox.getItemIds();
            if (itemIds != null && itemIds.size() > 0) {
                if (itemIds.contains(newsHomeBoxForm.getId())) {
                    throw new Exception("Đã tồn tại tin tức trong box");
                } else {
                    itemIds.add(newsHomeBoxForm.getId());
                    homeBox.setItemIds(itemIds);
                }
            } else {
                homeBox.setItemIds(new ArrayList<String>());
                homeBox.getItemIds().add(newsHomeBoxForm.getId());
            }
            newsHomeBoxRepository.save(homeBox);
            return new Response(true, "Đã thêm thành công!");
        }

    }

    @Cacheable(value = "buffercache", key = "'NewsHomeBox'")
    public NewsHomeBox getAll() {
        return newsHomeBoxRepository.getOne();

    }

    public Response del(String id) {
        News find = newsRepository.find(id);
        if (find == null) {
            return new Response(false, "Không tìm thấy tin tức");
        }
        NewsHomeBox newsHomeBox = newsHomeBoxRepository.getOne();
        List<String> itemIdN = new ArrayList<>();
        if (newsHomeBox != null) {
            List<String> itemIds = newsHomeBox.getItemIds();
            if (itemIds != null && itemIds.size() > 0) {
                for (String str : itemIds) {
                    if (!str.equals(id)) {
                        itemIdN.add(str);
                    }
                }
            }
            newsHomeBox.setItemIds(itemIdN);
            newsHomeBoxRepository.save(newsHomeBox);
        }
        return new Response(true, "Đã xóa thành công");
    }
}
