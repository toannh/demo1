package vn.chodientu.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.FeaturedCategory;
import vn.chodientu.entity.db.FeaturedCategorySub;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.FeaturedCategoryForm;
import vn.chodientu.entity.form.FeaturedCategoryImageForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.FeaturedCategoryRepository;

/**
 * @since Jun 6, 2014
 * @author Phuongdt
 */
@Service
public class FeaturedCategoryService {

    @Autowired
    private FeaturedCategoryRepository featuredCategoryRepository;
    @Autowired
    private FeaturedCategorySubService categorySubService;
    @Autowired
    private Validator validator;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageService imageService;

    /**
     *
     * @return
     */
    public List<FeaturedCategory> getall(boolean active) {
        return featuredCategoryRepository.list(active);
    }

    /**
     * Thêm mới danh mục nổi bật
     *
     * @param featuredCategoriesForm
     * @return
     */
    public Response add(FeaturedCategoryForm featuredCategoriesForm) throws Exception {
        Map<String, String> error = validator.validate(featuredCategoriesForm);
        if (featuredCategoriesForm.getCategoryId().equals("0")) {
            error.put("categoryId", "Bạn chưa chọn danh mục");
        }
        List<FeaturedCategory> list = featuredCategoryRepository.list(false);
        for (FeaturedCategory fc : list) {
            if (fc.getCategoryId().equals(featuredCategoriesForm.getCategoryId())) {
                error.put("categoryId", "Danh mục này đã tồn tại ở box danh mục nổi bật");
                break;
            }
        }
        if (featuredCategoriesForm.getTemplate().equals("0")) {
            error.put("template", "Bạn chưa chọn template");
        }
        if (!error.isEmpty()) {
            return new Response(false, null, error);
        } else {
            Category category = categoryService.get(featuredCategoriesForm.getCategoryId());
            FeaturedCategory fc = new FeaturedCategory();
            fc.setId(featuredCategoryRepository.genId());
            fc.setTemplate(featuredCategoriesForm.getTemplate());
            fc.setCategoryId(featuredCategoriesForm.getCategoryId());
            if (category != null) {
                fc.setCategoryName(category.getName());
            }
            fc.setActive(true);
            fc.setPosition(2);

            featuredCategoryRepository.save(fc);
            return new Response(true, "Đã thêm sản phẩm vào hotdeal thành công", fc);
        }

    }

    /**
     * Lấy danh sách danh mục con theo ID category
     *
     * @return
     */
    public List<Category> getCategoriesChilds(String id) throws Exception {
        List<Category> childs = categoryService.getChilds(id);
        if (childs == null) {
            throw new Exception("Không tồn tại danh mục con nào");
        }
        return childs;

    }

    /**
     * Lấy danh mục nổi bật theo categoryId
     *
     * @param id
     * @return
     */
    public FeaturedCategory getByCategoryId(String id) {
        return featuredCategoryRepository.getByCate(id);
    }

    /**
     * Upload ảnh item từ máy
     *
     * @param categoryImageForm
     * @return
     */
    public Response uploadImageItem(FeaturedCategoryImageForm categoryImageForm) {
        String data = "";
        if (categoryImageForm.getItemIdDel() != null) {
            List<String> getImg = imageService.get(ImageType.FEATURED_CATEGORY, categoryImageForm.getItemIdDel());
            if (getImg.size() > 0) {
                imageService.deleteById(ImageType.FEATURED_CATEGORY, categoryImageForm.getItemIdDel(), getImg.get(0));
            }
        }
        String genId = featuredCategoryRepository.genId();
        Response upload = imageService.upload(categoryImageForm.getImage(), ImageType.FEATURED_CATEGORY, genId);
        if (upload == null || !upload.isSuccess()) {
            return new Response(false, "Lỗi up ảnh: " + upload.getMessage());
        }
        data = (String) upload.getData();
        //String urlImg = imageService.getUrl(data).thumbnail(categoryImageForm.getWidth() + 20, categoryImageForm.getHeight() + 20, "outbound").getUrl();
        String urlImg = imageService.getUrl(data).getUrl();
        Map<String, String> puts = new HashMap<>();
        puts.put("image", urlImg);
        puts.put("itemId", genId);
        return new Response(true, null, puts);

    }

    /**
     * Download ảnh item từ url
     *
     * @param url
     * @param itemId
     * @param height
     * @param width
     * @return
     * @throws java.io.IOException
     */
    public Response downloadImageItem(String url, String itemId, int width, int height) throws IOException {
        String data = "";
        if (!itemId.equals("") && itemId != null) {
            List<String> getImg = imageService.get(ImageType.FEATURED_CATEGORY, itemId);
            if (getImg.size() > 0) {
                imageService.deleteById(ImageType.FEATURED_CATEGORY, itemId, getImg.get(0));
            }
        }
        String genId = featuredCategoryRepository.genId();
        Response download = imageService.download(url, ImageType.FEATURED_CATEGORY, genId);
        if (download == null || !download.isSuccess()) {
            return new Response(false, "Lỗi up ảnh: " + download.getMessage());
        } else {
            data = (String) download.getData();
            // String urlImg = imageService.getUrl(data).thumbnail(width + 20, height + 20, "outbound").getUrl();
            String urlImg = imageService.getUrl(data).getUrl();
            Map<String, String> puts = new HashMap<>();
            puts.put("image", urlImg);
            puts.put("itemId", genId);
            return new Response(true, null, puts);
        }

    }

    /**
     * Lấy danh sách chuyên mục nổi bật ra trang chủ
     *
     * @return
     */
    @Cacheable(value = "buffercache", key = "'FeaturedCategory'")
    public List<FeaturedCategory> list() throws Exception {
        List<FeaturedCategory> list = featuredCategoryRepository.list(true);
        List<FeaturedCategory> featuredCategorys = new ArrayList<>();
        for (FeaturedCategory fc : list) {
            List<FeaturedCategorySub> categorySubs = categorySubService.getByCategoryIdActive(fc.getCategoryId());
            if (categorySubs != null && categorySubs.size() > 0) {
                featuredCategorys.add(fc);
            }
        }
        return featuredCategorys;

    }

    /**
     * Thay đổi vị trí danh mục nổi bật
     *
     * @param idcate
     * @param position
     * @return
     */
    public Response changePosition(String idcate, int position) {
        FeaturedCategory byCate = featuredCategoryRepository.getByCate(idcate);
        if (byCate == null) {
            return new Response(false, "Không tồn tại danh mục");
        }
        byCate.setPosition(position);
        featuredCategoryRepository.save(byCate);
        return new Response(true, null);
    }

    /**
     * Thay đổi trạng thái danh mục nổi bật
     *
     * @param id
     * @return
     */
    public Response editStatus(String id) {
        FeaturedCategory byCate = featuredCategoryRepository.getByCate(id);
        if (byCate == null) {
            return new Response(false, "Không tồn tại danh mục");
        }
        byCate.setActive(!byCate.isActive());
        featuredCategoryRepository.save(byCate);
        return new Response(true, "Sửa trạng thái thành công", byCate);

    }

    /**
     * Thay đổi tên danh mục nổi bật
     *
     * @param featuredCategoryForm
     * @return
     */
    public Response editName(FeaturedCategoryForm featuredCategoryForm) {
        FeaturedCategory byCate = featuredCategoryRepository.getByCate(featuredCategoryForm.getCategoryId());
        if (byCate == null) {
            return new Response(false, "Không tồn tại danh mục");
        }
        if (featuredCategoryForm.getCategoryName().equals("")) {
            return new Response(false, "Tên danh mục không được để trống");
        } else {
            byCate.setCategoryName(featuredCategoryForm.getCategoryName());
            featuredCategoryRepository.save(byCate);
        }
        return new Response(true, null, byCate);

    }

    /**
     * Xóa danh mục nổi bật nếu không tồn tại danh mục con
     *
     * @param id
     * @return
     */
    public Response del(String id) {
        FeaturedCategory byCate = featuredCategoryRepository.find(id);
        if (byCate == null) {
            return new Response(false, "Không tồn tại danh mục");
        }
        List<FeaturedCategorySub> categorySubs = categorySubService.getByCategoryId(byCate.getCategoryId());
        if (categorySubs != null && !categorySubs.isEmpty()) {
            return new Response(false, "Lỗi xóa do vẫn tồn tại danh mục con");
        } else {
            featuredCategoryRepository.delete(id);
            return new Response(true, "Xóa thành công");
        }

    }

}
