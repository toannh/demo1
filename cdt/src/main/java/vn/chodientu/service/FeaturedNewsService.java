package vn.chodientu.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.FeaturedNews;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.FeaturedNewsForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.NewsHomeBoxRepository;
import vn.chodientu.repository.NewsRepository;
import vn.chodientu.repository.FeaturedNewsRepository;

/**
 * Business service cho Danh mục
 *
 * @author Phuongdt
 * @since Jun 14, 2013
 */
@Service
public class FeaturedNewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private FeaturedNewsRepository featuredNewsRepository;
    @Autowired
    private NewsHomeBoxRepository newsHomeBoxRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private ImageService imageService;

    /**
     * *
     * Thêm tin tức vào box ngoài trang Home
     *
     * @param otherNewsForm
     * @return
     * @throws Exception
     */
    public Response add(FeaturedNewsForm otherNewsForm) throws Exception {
        Map<String, String> errors = validator.validate(otherNewsForm);
        if (otherNewsForm.getType() <= 0) {
            errors.put("type", "Bạn chưa chọn danh mục");
        } else {
            if (otherNewsForm.getType() == 1) {
                if (otherNewsForm.getTitle().equals("")) {
                    errors.put("title", "Bạn chưa nhập tiêu đề");
                }
            } 
        }
        if (otherNewsForm.getContent().equals("")) {
            errors.put("content", "Bạn chưa nhập nội dung");
        }

        if (otherNewsForm.getName().equals("")) {
            errors.put("name", "Bạn chưa họ tên");
        }
        if (otherNewsForm.getImage() == null || otherNewsForm.getImage().isEmpty()) {
            errors.put("image", "Bạn chưa chọn ảnh");
        }

        if (!errors.isEmpty()) {
            return new Response(false, null, errors);
        } else {
            FeaturedNews otherNews = new FeaturedNews();
            String genId = featuredNewsRepository.genId();
            otherNews.setId(genId);
            otherNews.setContent(otherNewsForm.getContent());
            otherNews.setName(otherNewsForm.getName());
            otherNews.setTitle(otherNewsForm.getTitle());
            otherNews.setNameCompany(otherNewsForm.getNameCompany());
            otherNews.setNameShop(otherNewsForm.getNameShop());
            otherNews.setType(otherNewsForm.getType());
            otherNews.setUrl(otherNewsForm.getUrl());
            otherNews.setTimeCreate(new Date().getTime());
            otherNews.setActive(otherNewsForm.isActive());
            if (otherNewsForm.getImage().getSize() > 0) {
                Response upload = imageService.upload(otherNewsForm.getImage(), ImageType.FEATURED_NEWS, genId);
                if (upload == null || !upload.isSuccess()) {
                    return new Response(false, upload.getMessage());
                }
            }

            featuredNewsRepository.save(otherNews);
            return new Response(true, "Đã thêm thành công");
        }

    }

    @Cacheable(value = "buffercache", key="'FeaturedNews-'.concat(#active)")
    public List<FeaturedNews> getAll(boolean active) {
        return featuredNewsRepository.getAll(active);

    }

    public Response getById(String id) {
        FeaturedNews find = featuredNewsRepository.find(id);
        if (find == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        List<String> get = imageService.get(ImageType.FEATURED_NEWS, id);
        if(get!=null && get.size()>0){
            find.setImage(imageService.getUrl(get.get(0)).getUrl());
        }
        return new Response(true, null, find);

    }
    /***
     * Xóa tin tức nổi bật theo ID
     * @param id
     * @return 
     */
    public Response del(String id) {
        FeaturedNews find = featuredNewsRepository.find(id);
        if (find == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        imageService.delete(ImageType.FEATURED_NEWS, id);
        featuredNewsRepository.delete(id);
        return new Response(true, "Đã xóa thành công tin tức nổi bật");

    }

    /**
     * *
     * Thêm tin tức vào box ngoài trang Home
     *
     * @param otherNewsForm
     * @return
     * @throws Exception
     */
    public Response edit(FeaturedNewsForm otherNewsForm) throws Exception {
        FeaturedNews find = featuredNewsRepository.find(otherNewsForm.getId());
        if (find == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        Map<String, String> errors = validator.validate(otherNewsForm);
        if (otherNewsForm.getType() <= 0) {
            errors.put("type", "Bạn chưa chọn danh mục");
        } else {
            if (otherNewsForm.getType() == 1) {
                if (otherNewsForm.getTitle().equals("")) {
                    errors.put("title", "Bạn chưa nhập tiêu đề");
                }
            } 
        }
        if (otherNewsForm.getContent().equals("")) {
            errors.put("content", "Bạn chưa nhập nội dung");
        }

        if (otherNewsForm.getName().equals("")) {
            errors.put("name", "Bạn chưa họ tên");
        }

        if (!errors.isEmpty()) {
            return new Response(false, null, errors);
        } else {

            find.setContent(otherNewsForm.getContent());
            find.setName(otherNewsForm.getName());
            find.setTitle(otherNewsForm.getTitle());
            find.setNameCompany(otherNewsForm.getNameCompany());
            find.setNameShop(otherNewsForm.getNameShop());
            find.setType(otherNewsForm.getType());
            find.setUrl(otherNewsForm.getUrl());
            find.setTimeCreate(new Date().getTime());
            find.setActive(otherNewsForm.isActive());
            if (otherNewsForm.getImage().getSize() > 0) {
                imageService.delete(ImageType.FEATURED_NEWS, find.getId());
                Response upload = imageService.upload(otherNewsForm.getImage(), ImageType.FEATURED_NEWS, find.getId());
                if (upload == null || !upload.isSuccess()) {
                    return new Response(false, upload.getMessage());
                }
            }
            featuredNewsRepository.save(find);
            return new Response(true, "Đã thêm thành công");
        }

    }

}
