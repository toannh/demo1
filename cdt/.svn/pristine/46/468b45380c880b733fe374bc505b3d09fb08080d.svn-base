package vn.chodientu.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.AdvBanner;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.enu.AdvBannerType;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.AdvBannerForm;
import vn.chodientu.entity.input.AdvBannerSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.AdvBannerRepository;
import vn.chodientu.repository.CategoryRepository;

/**
 * @since Jul 14, 2014
 * @author Account
 */
@Service
public class AdvBannerService {

    @Autowired
    private AdvBannerRepository bannerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private ImageService imageService;

    /**
     * Lấy chi tiết 1 banner
     *
     * @param bannerId
     * @return
     */
    public AdvBanner getAdvBanner(String bannerId) {
        AdvBanner banner = bannerRepository.find(bannerId);
        if (banner != null) {
            List<String> bannerImages = imageService.get(ImageType.ADVBANNER, banner.getId());
            if (bannerImages != null && !bannerImages.isEmpty()) {
                banner.setBanner(imageService.getUrl(bannerImages.get(0)).thumbnail(100, 100, "inset").getUrl());
            }
        }
        return banner;
    }

    /**
     * Lấy banner quảng cáo hiển thị backend user
     *
     * @return
     */
    public AdvBanner getAdvBannerBackEndUser() {
        AdvBanner banner = bannerRepository.getBannerByType(AdvBannerType.BACKEND_USER);
        if (banner != null) {
            List<String> bannerImages = imageService.get(ImageType.ADVBANNER, banner.getId());
            if (bannerImages != null && !bannerImages.isEmpty()) {
                banner.setBanner(imageService.getUrl(bannerImages.get(0)).thumbnail(1200, 90, "inset").getUrl());
            }
        }
        return banner;
    }

    /**
     * Lấy danh sách banner
     *
     * @param bannerSearch
     * @return
     */
    public DataPage<AdvBanner> search(AdvBannerSearch bannerSearch) {
        DataPage<AdvBanner> dataPage = new DataPage<>();
        Criteria criteria = new Criteria();

        if (bannerSearch.getId() != null && !bannerSearch.getId().equals("")) {
            criteria.and("id").is(bannerSearch.getId());
        }
        if (bannerSearch.getCategoryId() != null && !bannerSearch.getCategoryId().equals("")) {
            criteria.and("categoryId").is(bannerSearch.getCategoryId());
        }
        if (bannerSearch.getActive() > 0) {
            criteria.and("active").is(bannerSearch.getActive() == 1);
        }
        if (bannerSearch.getPosition() != null) {
            criteria.and("position").is(bannerSearch.getPosition());
        }

        List<AdvBanner> banners = bannerRepository.getAll(criteria, new PageRequest(bannerSearch.getPageIndex(), bannerSearch.getPageSize()));
        for (AdvBanner banner : banners) {
            List<String> bannerImages = imageService.get(ImageType.ADVBANNER, banner.getId());
            if (bannerImages != null && !bannerImages.isEmpty()) {
                banner.setBanner(imageService.getUrl(bannerImages.get(0)).getUrl());
            }
        }
        dataPage.setData(banners);
        dataPage.setPageSize(bannerSearch.getPageSize());
        dataPage.setPageIndex(bannerSearch.getPageIndex());
        dataPage.setDataCount(bannerRepository.count(new Query(criteria)));
        dataPage.setPageCount(dataPage.getDataCount() / dataPage.getPageSize());
        if (dataPage.getDataCount() % dataPage.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }

        return dataPage;
    }

    /**
     * Thêm mới banner
     *
     * @param bannerForm
     * @return
     */
    public Response addAdvBanner(AdvBannerForm bannerForm) {
        Map<String, String> error = validator.validate(bannerForm);
        List<String> bannerImages = imageService.get(ImageType.ADVBANNER, bannerForm.getId());
        AdvBanner banner = new AdvBanner();
        if (bannerForm.getId() == null || bannerForm.getId().equals("")) {
            bannerForm.setId(bannerRepository.genId());
            banner.setCreateTime(System.currentTimeMillis());
        }
        if (bannerImages == null && (bannerForm.getBanner() == null || bannerForm.getBanner().getSize() <= 0)) {
            error.put("banner", "Ảnh của banner không được để trống");
        }
        if (bannerForm.getPosition() == null) {
            error.put("position", "Vị trí của banner phải được chọn");
        }

        if (bannerForm.getPosition() == AdvBannerType.BROWSE_CONTENT && (bannerForm.getCategoryId() == null || bannerForm.getCategoryId().equals(""))) {
            error.put("categoryId", "Banner danh mục phải chọn danh mục đăng");
        }

        if (bannerForm.getCategoryId() != null && !bannerForm.getCategoryId().equals("")) {
            Category find = categoryRepository.find(bannerForm.getCategoryId());
            if (find == null) {
                error.put("categoryId", "Danh mục sản phẩm không tồn tại");
            }
        }
        if (error.isEmpty()) {
            banner.setId(bannerForm.getId());
            banner.setTitle(bannerForm.getTitle());
            banner.setCategoryId(bannerForm.getCategoryId());
            banner.setLink(bannerForm.getLink());
            banner.setActive(bannerForm.isActive());
            banner.setPosition(bannerForm.getPosition());

            bannerRepository.save(banner);
            if (bannerForm.getBanner() != null && bannerForm.getBanner().getSize() > 0) {
                imageService.upload(bannerForm.getBanner(), ImageType.ADVBANNER, banner.getId());
                if (bannerImages != null && !bannerImages.isEmpty()) {
                    imageService.deleteById(ImageType.ADVBANNER, banner.getId(), bannerImages.get(0));
                }
            }
            return new Response(true, "Thêm/Sửa banner thành công");
        }
        return new Response(false, "Thêm/Sửa banner thất bại", error);
    }

    public Response delete(String id) {
        if (bannerRepository.exists(id)) {
            bannerRepository.delete(id);
            imageService.delete(ImageType.ADVBANNER, id);
            return new Response(true, "Xóa banner thành công");
        } else {
            return new Response(true, "Banner không tồn tại");
        }
    }

    public Response changeStatus(String id) {
        AdvBanner advBanner = bannerRepository.find(id);
        if (advBanner != null) {
            advBanner.setActive(!advBanner.isActive());
            bannerRepository.save(advBanner);
            return new Response(true, "Thành công", advBanner);
        } else {
            return new Response(false, "Banner không tồn tại");
        }
    }

}
