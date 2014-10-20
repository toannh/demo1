package vn.chodientu.service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.db.HeartBanner;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.HeartBannerForm;
import vn.chodientu.entity.input.CashTransactionSearch;
import vn.chodientu.entity.input.HeartBannerSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.HeartBannerRepository;

/**
 * @since Jun 6, 2014
 * @author Phu
 */
@Service
public class HeartBannerService {

    @Autowired
    private HeartBannerRepository heartBannerRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private ImageService imageService;

    /**
     * Thêm mới heartBanner
     *
     * @param heartBanner
     * @return
     */
    public Response add(HeartBannerForm heartBanner) {
        Map<String, String> error = validator.validate(heartBanner);
        if (heartBanner.getImage() == null || heartBanner.getImage().getSize() == 0) {
            error.put("photoCover", "Bạn chưa chọn ảnh");
        }
        if (!error.isEmpty()) {
            return new Response(false, null, error);
        } else {

            HeartBanner hb = new HeartBanner();
            String idH = heartBannerRepository.genId();
            hb.setId(idH);
            hb.setPosition(heartBanner.getPosition());
            hb.setName(heartBanner.getName());
            hb.setUrl(heartBanner.getUrl());
            hb.setActive(heartBanner.isActive());
            heartBannerRepository.save(hb);
            imageService.upload(heartBanner.getImage(), ImageType.HEART_BANNER, idH);
            imageService.upload(heartBanner.getImageThumb(), ImageType.HEART_BANNER_THUMB, idH);
            return new Response(true, "Đã thêm Heart Banner thành công!");
        }

    }

    /**
     * Thêm mới heartBanner
     *
     * @param heartBanner
     * @param i
     * @return
     */
    public Response edit(HeartBannerForm heartBanner) {
        Map<String, String> error = validator.validate(heartBanner);
        HeartBanner find = heartBannerRepository.find(heartBanner.getId());
        if (find == null) {
            return new Response(false, "Heart Banner không tồn tại");
        }

        if (!error.isEmpty()) {
            return new Response(false, null, error);
        } else {

            //List<String> get = imageService.get(ImageType.HEART_BANNER, find.getId());
            if (heartBanner.getImage() != null && heartBanner.getImage().getSize() != 0) {
                imageService.delete(ImageType.HEART_BANNER, find.getId());
                imageService.upload(heartBanner.getImage(), ImageType.HEART_BANNER, find.getId());
            }
            if (heartBanner.getImageThumb() != null && heartBanner.getImageThumb().getSize() != 0) {
                imageService.delete(ImageType.HEART_BANNER_THUMB, find.getId());
                imageService.upload(heartBanner.getImageThumb(), ImageType.HEART_BANNER_THUMB, find.getId());
            }
            find.setPosition(heartBanner.getPosition());
            find.setName(heartBanner.getName());
            find.setUrl(heartBanner.getUrl());
            find.setActive(heartBanner.isActive());
            heartBannerRepository.save(find);

            return new Response(true, "Đã sửa Heart Banner thành công!");
        }

    }

    /**
     * Lấy danh sách giao dịch đã thực hiện
     *
     * @param transactionSearch
     * @return
     */
    public DataPage<HeartBanner> search(HeartBannerSearch bannerSearch) {
        Criteria cri = new Criteria();

        DataPage<HeartBanner> dataPage = new DataPage<>();
        dataPage.setDataCount(heartBannerRepository.count(new Query(cri)));
        dataPage.setPageIndex(bannerSearch.getPageIndex());
        dataPage.setPageSize(bannerSearch.getPageSize());
        dataPage.setPageCount(dataPage.getDataCount() / bannerSearch.getPageSize());
        if (dataPage.getDataCount() % bannerSearch.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        List<HeartBanner> list = heartBannerRepository.list(cri, bannerSearch.getPageSize(), bannerSearch.getPageIndex());
        dataPage.setData(list);
        return dataPage;
    }

    /**
     * Thay đổi trạng thái ẩn/ hiện heart banner
     *
     * @param id
     * @return
     */
    public Response editStatus(String id) {
        HeartBanner find = heartBannerRepository.find(id);
        if (find == null) {
            return new Response(false, "Heart Banner không tồn tại");
        } else {
            find.setActive(!find.isActive());
            heartBannerRepository.save(find);
            return new Response(true, null, find);
        }

    }

    /**
     * Thay đổi trạng thái ẩn/ hiện heart banner
     *
     * @param id
     * @return
     */
    public Response getbyId(String id) {
        HeartBanner find = heartBannerRepository.find(id);

        if (find == null) {
            return new Response(false, "Heart Banner không tồn tại");
        } else {
            List<String> get = imageService.get(ImageType.HEART_BANNER, find.getId());
            if (get != null && get.size() > 0) {
                find.setImage(imageService.getUrl(get.get(0)).thumbnail(200, 81, "outbound").getUrl());
            }
            List<String> getImgThumb = imageService.get(ImageType.HEART_BANNER_THUMB, find.getId());
            if (getImgThumb != null && getImgThumb.size() > 0) {
                find.setThumb(imageService.getUrl(getImgThumb.get(0)).thumbnail(200, 81, "outbound").getUrl());
            }
            return new Response(true, null, find);
        }

    }

    /**
     * Thay đổi vị trí heart banner
     *
     * @param id
     * @param order
     * @return
     */
    public Response changeOrder(String id, int order) {
        HeartBanner heartBanner = heartBannerRepository.find(id);
        if (heartBanner != null) {
            heartBanner.setPosition(order);
            heartBannerRepository.save(heartBanner);
            return new Response(true, "Cập nhật thứ tự của banner thành công ", heartBanner);
        } else {
            return new Response(false, "Không tồn tại banner này");
        }
    }

    /**
     * Xóa heart banner theo id
     *
     * @param id
     * @return
     */
    public Response remove(String id) {
        HeartBanner find = heartBannerRepository.find(id);

        if (find == null) {
            return new Response(false, "Không tồn tại heart banner");
        } else {
            imageService.delete(ImageType.HEART_BANNER, id);
            imageService.delete(ImageType.HEART_BANNER_THUMB, id);
            heartBannerRepository.delete(id);
            return new Response(true, "Xóa banner thành công!", null);
        }
    }

    /**
     * List tất cả heartbanner theo active = true
     *
     * @return
     */
    @Cacheable(value = "buffercache", key="'HeartBanner'")
    public List<HeartBanner> list() {
        return heartBannerRepository.getAll();
    }
}
