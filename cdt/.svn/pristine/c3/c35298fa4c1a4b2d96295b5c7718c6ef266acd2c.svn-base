package vn.chodientu.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.ShopBanner;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.ShopBannerType;
import vn.chodientu.entity.form.ShopBannerForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.ShopBannerRepository;

/**
 * Business service cho Danh mục
 *
 * @author Phuongdt
 * @since Jun 14, 2013
 */
@Service
public class ShopBannerService {

    @Autowired
    private ShopBannerRepository shopBannerRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Viewer viewer;

    public Response add(ShopBannerForm shopBannerForm) throws Exception {
        Map<String, String> errors = validator.validate(shopBannerForm);
        ShopBanner shopBanner = new ShopBanner();
        if (shopBannerForm.getType() == ShopBannerType.HEART) {
            if (!shopBannerForm.getBannerType().equals("") && shopBannerForm.getBannerType().equals("type-image")) {
                if (shopBannerForm.getImage() == null || shopBannerForm.getImage().isEmpty()) {
                    errors.put("image", "Bạn chưa chọn ảnh");
                } else {
                    if (shopBannerForm.getImage().getSize() > 1536) {
//                        errors.put("image", "Dung lượng file up tối đa 1,5MB");
                    }
                    if (shopBannerForm.getUrl().equals("") || shopBannerForm.getUrl().equals("http://")) {
                        errors.put("url", "Bạn chưa nhập link baner");
                    }
                    if (!shopBannerForm.getUrl().contains("chodientu.vn")) {
                        errors.put("url", "Link bắt buộc phải nằm trong hệ thống chợ điện tử");
                    }

                }
                shopBanner.setUrl(shopBannerForm.getUrl());
            } else {
                if (shopBannerForm.getEmbedCode() == null || shopBannerForm.getEmbedCode().equals("")) {
                    errors.put("embedCode", "Mã nhúng video không được để trống");
                } else {
                    shopBanner.setEmbedCode(shopBannerForm.getEmbedCode());
                }
            }
            shopBanner.setType(ShopBannerType.HEART);
        } else {
            if (!shopBannerForm.getBannerType().equals("") && shopBannerForm.getBannerType().equals("type-image")) {
                if (shopBannerForm.getImage() == null || shopBannerForm.getImage().isEmpty()) {
                    errors.put("image", "Bạn chưa chọn ảnh");
                } else {
                    if (shopBannerForm.getImage().getSize() > 1536) {
                        //errors.put("image", "Dung lượng file up tối đa 1,5MB");
                    }
                    if (shopBannerForm.getUrl().equals("") || shopBannerForm.getUrl().equals("http://")) {
                        errors.put("url", "Bạn chưa nhập link baner");
                    }
                    if (!shopBannerForm.getUrl().contains("chodientu.vn")) {
                        errors.put("url", "Link bắt buộc phải nằm trong hệ thống chợ điện tử");
                    }
                }
                shopBanner.setUrl(shopBannerForm.getUrl());
            } else {
                if (shopBannerForm.getEmbedCode() == null || shopBannerForm.getEmbedCode().equals("")) {
                    errors.put("embedCode", "Mã nhúng video không được để trống");
                } else {
                    shopBanner.setEmbedCode(shopBannerForm.getEmbedCode());
                }
            }
            shopBanner.setType(shopBannerForm.getType());
        }

        if (!errors.isEmpty()) {
            return new Response(false, null, errors);
        } else {

            String genId = shopBannerRepository.genId();
            shopBanner.setId(genId);
            shopBanner.setTitle(shopBannerForm.getTitle());
            shopBanner.setPosition(1);
            shopBanner.setActive(true);

            shopBanner.setSellerId(viewer.getUser().getId());
            shopBannerRepository.save(shopBanner);
            if (shopBannerForm.getImage().getSize() > 0) {
                Response upload = imageService.upload(shopBannerForm.getImage(), ImageType.SHOP_BANNER, genId);
                if (upload == null || !upload.isSuccess()) {
                    return new Response(false, upload.getMessage());
                }
            }
        }
        return new Response(true, "Đã thêm thành công");
    }

    public Response edit(ShopBannerForm shopBannerForm) throws Exception {
        Map<String, String> errors = validator.validate(shopBannerForm);
        if (!shopBannerRepository.exists(shopBannerForm.getId())) {
            throw new Exception("Không tồn tại bản ghi");
        }
        ShopBanner shopBanner = shopBannerRepository.find(shopBannerForm.getId());
        if (shopBannerForm.getType() != ShopBannerType.HEART) {
            if (!shopBannerForm.getBannerType().equals("") && shopBannerForm.getBannerType().equals("type-image")) {
                if (shopBannerForm.getImage().getSize() > 1536) {
                    //errors.put("image", "Dung lượng file up tối đa 1,5MB");
                }
                List<String> get = imageService.get(ImageType.SHOP_BANNER, shopBannerForm.getId());
                if (get == null || get.isEmpty()) {
                    if (shopBannerForm.getImage() == null || shopBannerForm.getImage().isEmpty()) {
                        errors.put("image", "Bạn chưa chọn ảnh");
                    }
                }
                if (shopBannerForm.getUrl().equals("") || shopBannerForm.getUrl().equals("http://")) {
                    errors.put("url", "Bạn chưa nhập đường dẫn ảnh");
                } else {
                    shopBanner.setUrl(shopBannerForm.getUrl());
                }
                shopBanner.setEmbedCode(null);
            } else {
                if (shopBannerForm.getEmbedCode() == null || shopBannerForm.getEmbedCode().equals("")) {
                    errors.put("embedCode", "Mã nhúng video không được để trống");
                } else {
                    shopBanner.setEmbedCode(shopBannerForm.getEmbedCode());
                }
                imageService.delete(ImageType.SHOP_BANNER, shopBannerForm.getId());
                shopBanner.setUrl(null);
            }
            shopBanner.setType(shopBannerForm.getType());
        } else {
            if (!shopBannerForm.getBannerType().equals("") && shopBannerForm.getBannerType().equals("type-image")) {
                if (shopBannerForm.getImage().getSize() > 1536) {
                    //errors.put("image", "Dung lượng file up tối đa 1,5MB");
                }
                List<String> get = imageService.get(ImageType.SHOP_BANNER, shopBannerForm.getId());
                if (get == null || get.isEmpty()) {
                    if (shopBannerForm.getImage() == null || shopBannerForm.getImage().isEmpty()) {
                        errors.put("image", "Bạn chưa chọn ảnh");
                    }
                }
                if (shopBannerForm.getUrl().equals("") || shopBannerForm.getUrl().equals("http://")) {
                    errors.put("url", "Bạn chưa nhập đường dẫn ảnh");
                } else {
                    shopBanner.setUrl(shopBannerForm.getUrl());
                }
                shopBanner.setEmbedCode(null);

            } else {
                if (shopBannerForm.getEmbedCode() == null || shopBannerForm.getEmbedCode().equals("")) {
                    errors.put("embedCode", "Mã nhúng video không được để trống");
                } else {
                    shopBanner.setEmbedCode(shopBannerForm.getEmbedCode());
                }
                imageService.delete(ImageType.SHOP_BANNER, shopBannerForm.getId());
                shopBanner.setUrl(null);
            }
            shopBanner.setType(ShopBannerType.HEART);
        }
        if (!errors.isEmpty()) {
            return new Response(false, null, errors);
        } else {
            shopBanner.setTitle(shopBannerForm.getTitle());
            shopBanner.setSellerId(viewer.getUser().getId());
            shopBannerRepository.save(shopBanner);
            if (shopBannerForm.getImage().getSize() > 0) {
                imageService.delete(ImageType.SHOP_BANNER, shopBanner.getId());
                Response upload = imageService.upload(shopBannerForm.getImage(), ImageType.SHOP_BANNER, shopBannerForm.getId());
                if (upload == null || !upload.isSuccess()) {
                    return new Response(false, upload.getMessage());
                }
            }
        }
        return new Response(true, "Đã sửa thành công");
    }

    /**
     * *
     * List tất cả
     *
     * @param bannerType
     * @param sellerId
     * @return
     */
    public List<ShopBanner> getAll(ShopBannerType bannerType, String sellerId) {
        return shopBannerRepository.getAll(bannerType, sellerId);

    }

    /**
     * *
     * List tất cả
     *
     * @param bannerType
     * @param sellerId
     * @return
     */
    public List<ShopBanner> getAll(String sellerId, int active) {
        return shopBannerRepository.getAll(sellerId, active);

    }

    /**
     * *
     * Thay đổi trạng thái của Shopbanner
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Response changeStatus(String id) throws Exception {
        if (!shopBannerRepository.exists(id)) {
            throw new Exception("Không tồn tại bản ghi");
        }
        ShopBanner shopBanner = shopBannerRepository.find(id);
        shopBanner.setActive(!shopBanner.isActive());
        shopBannerRepository.save(shopBanner);
        return new Response(true, null, shopBanner);
    }

    /**
     * *
     * Xóa Shopbanner theo Id
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Response del(String id) throws Exception {
        if (!shopBannerRepository.exists(id)) {
            throw new Exception("Không tồn tại bản ghi");
        }
        imageService.delete(ImageType.SHOP_BANNER, id);
        shopBannerRepository.delete(id);
        return new Response(true, "Xóa thành công");

    }

    public ShopBanner getById(String id) throws Exception {
        if (!shopBannerRepository.exists(id)) {
            throw new Exception("Không tồn tại bản ghi");
        }
        return shopBannerRepository.find(id);

    }

}
