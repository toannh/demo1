package vn.chodientu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.BestDealBox;
import vn.chodientu.entity.db.HotDealBox;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.BestDealBoxForm;
import vn.chodientu.entity.form.HotDealBoxForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.HotDealBoxRepository;

/**
 * @since Jun 6, 2014
 * @author Phuongdt
 */
@Service
public class HotDealBoxService {

    @Autowired
    private HotDealBoxRepository hotDealBoxRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ItemService itemService;

    /**
     * Thêm mới heartBanner
     *
     * @param hotDealBoxForm
     * @return
     */
    public Response add(HotDealBoxForm hotDealBoxForm) throws Exception {
        Map<String, String> error = validator.validate(hotDealBoxForm);
        if (!error.isEmpty()) {
            return new Response(false, null, error);
        } else {
            List<HotDealBox> dealBox = hotDealBoxRepository.getAll();
            List<String> ids = new ArrayList<>();
            for (HotDealBox hotDealBox : dealBox) {
                ids.add(hotDealBox.getItemId());
            }

            if (ids.isEmpty()) {
                ids = new ArrayList();
            }
            Item get = itemService.get(hotDealBoxForm.getItemId());
            if (get == null) {
                throw new Exception("Sản phẩm không tồn tại");
            }
            if (ids != null && ids.size() > 0) {
                if (ids.contains(hotDealBoxForm.getItemId())) {
                    throw new Exception("Đã tồn tại Sản phảm trong hotdeal box");
                }
            }
            HotDealBox box = new HotDealBox();
            box.setId(hotDealBoxRepository.genId());
            box.setItemId(hotDealBoxForm.getItemId());
            box.setTitle(get.getName());
            box.setActive(true);
            hotDealBoxRepository.save(box);
            return new Response(true, "Đã thêm sản phẩm vào hotdeal thành công", box);
        }

    }

    public Response edit(HotDealBoxForm hotDealBoxForm) throws Exception {

        List<String> get = imageService.get(ImageType.HOT_DEAL_BOX, hotDealBoxForm.getId());
        if (get == null || get.size() <= 0) {
            return new Response(false, "Bạn đã chọn ảnh đâu");
        }
        if (hotDealBoxForm.getWidth() > 0 && hotDealBoxForm.getHeight() > 0) {
            String url = imageService.getUrl(get.get(0)).crop(hotDealBoxForm.getX(), hotDealBoxForm.getY(), hotDealBoxForm.getWidth(), hotDealBoxForm.getHeight()).thumbnail(350, 350, "outbound").getUrl();
            imageService.download(url, ImageType.HOT_DEAL_BOX, hotDealBoxForm.getId());
            imageService.deleteById(ImageType.HOT_DEAL_BOX, hotDealBoxForm.getId(), get.get(0));
        } else {

            String url = imageService.getUrl(get.get(0)).thumbnail(350, 350, "outbound").getUrl();
            imageService.download(url, ImageType.HOT_DEAL_BOX, hotDealBoxForm.getId());
            imageService.deleteById(ImageType.HOT_DEAL_BOX, hotDealBoxForm.getId(), get.get(0));
        }

        return new Response(true, "Đã thêm sản phẩm vào hotdeal thành công");

    }

    /**
     * Đổi tiêu dề của hotdeail box
     *
     * @param hotDealBoxForm
     * @return
     */
    public Response editName(HotDealBoxForm hotDealBoxForm) {
        HotDealBox byCate = hotDealBoxRepository.getByItemId(hotDealBoxForm.getItemId());
        if (byCate == null) {
            return new Response(false, "Không tồn tại trong box");
        }
        if (hotDealBoxForm.getTitle().equals("")) {
            return new Response(false, "Tên danh mục không được để trống");
        } else {
            byCate.setTitle(hotDealBoxForm.getTitle());
            hotDealBoxRepository.save(byCate);
        }
        return new Response(true, null, byCate);

    }

    /**
     * Thay đổi trạng thái hotdeail box
     *
     * @param id
     * @return
     */
    public Response editStatus(String id) {
        HotDealBox byCate = hotDealBoxRepository.getByItemId(id);
        if (byCate == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        byCate.setActive(!byCate.isActive());
        hotDealBoxRepository.save(byCate);
        return new Response(true, "Sửa trạng thái thành công", byCate);

    }

    /**
     * Thêm ảnh banner cho hotdealbox
     *
     * @param hotDealBoxForm
     * @return
     * @throws Exception
     */
    public Response addImageBanner(HotDealBoxForm hotDealBoxForm) throws Exception {
        if (hotDealBoxForm.getImages() != null && !hotDealBoxForm.getImages().isEmpty()) {
            imageService.delete(ImageType.HOT_DEAL_BOX, "hotdealbox");
            imageService.upload(hotDealBoxForm.getImages(), ImageType.HOT_DEAL_BOX, "hotdealbox");
        }
        return new Response(true, "Đã banner cho HotDealBoxForm");

    }

    public List<HotDealBox> getAll() {
        return hotDealBoxRepository.getAll();
    }

    /**
     * *
     * Lấy tất cả danh sách hotdeailbox được active
     *
     * @return
     */
    @Cacheable(value = "buffercache", key = "'HotDealBox'")
    public List<HotDealBox> list() {
        return hotDealBoxRepository.list();
    }

    /**
     * *
     * Lấy 1 bản ghi hotdeailbox theo itemId
     *
     * @param id
     * @return
     */
    public HotDealBox getById(String id) {
        return hotDealBoxRepository.getByItemId(id);
    }

    /**
     * Xóa item trong hotdealbox
     *
     * @param id
     * @return
     */
    public Response del(String id) {
        HotDealBox find = hotDealBoxRepository.find(id);
        if (find == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        imageService.delete(ImageType.HOT_DEAL_BOX, find.getItemId());
        hotDealBoxRepository.delete(id);
        return new Response(true, "Đã xóa sản phẩm hotdeal thành công");

    }

}
