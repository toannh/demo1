package vn.chodientu.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.BestDealBox;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.BestDealBoxForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.BestDealBoxRepository;

/**
 * @since Jun 6, 2014
 * @author Phuongdt
 */
@Service
public class BestDealBoxService {

    @Autowired
    private BestDealBoxRepository bestDealBoxRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private SellerService sellerService;

    /**
     * Thêm mới BestDealBox
     *
     * @param bestDealBoxForm
     * @return
     */
    public Response add(BestDealBoxForm bestDealBoxForm) throws Exception {
        Map<String, String> error = validator.validate(bestDealBoxForm);
        if (bestDealBoxForm.getItemId() == null || bestDealBoxForm.getItemId().equals("")) {
            error.put("itemId", "Bạn chưa nhập mã sản phẩm");
        }
        Item get = itemService.get(bestDealBoxForm.getItemId());
        if (get == null) {
            error.put("itemId", "Sản phẩm không tồn tại trên hệ thống");
        }
        Seller sellerNLS = sellerService.getSellerNLS(get.getSellerId());
        if (sellerNLS == null) {
            error.put("itemId", "Sản phẩm này đăng bởi người bán chưa được tích hợp Ngân Lượng và Ship Chung");
        }
        BestDealBox byItemId = bestDealBoxRepository.getByItemId(bestDealBoxForm.getItemId());
        if (byItemId != null) {
            error.put("itemId", "Sản phẩm đã tồn tại trong box");
        }
        if (!error.isEmpty()) {
            return new Response(false, null, error);
        } else {
            BestDealBox box = new BestDealBox();
            box.setId(bestDealBoxRepository.genId());
            box.setItemId(get.getId());
            box.setTitle(get.getName());
            box.setActive(true);
            bestDealBoxRepository.save(box);
            return new Response(true, "Đã thêm sản phẩm vào BestDeal thành công");
        }

    }

    public Response edit(BestDealBoxForm bestDealBoxForm) throws Exception {
        try {
            if (bestDealBoxForm.getWidth() > 0 && bestDealBoxForm.getHeight() > 0) {
                List<String> get = imageService.get(ImageType.BEST_DEAL_BOX, bestDealBoxForm.getId());
                String url = imageService.getUrl(get.get(0)).crop(bestDealBoxForm.getX(), bestDealBoxForm.getY(), bestDealBoxForm.getWidth(), bestDealBoxForm.getHeight()).thumbnail(280, 280, "outbound").getUrl();
                imageService.download(url, ImageType.BEST_DEAL_BOX, bestDealBoxForm.getId());
                imageService.deleteById(ImageType.BEST_DEAL_BOX, bestDealBoxForm.getId(), get.get(0));
            } else {
                List<String> get = imageService.get(ImageType.BEST_DEAL_BOX, bestDealBoxForm.getId());
                String url = imageService.getUrl(get.get(0)).thumbnail(280, 280, "outbound").getUrl();
                imageService.download(url, ImageType.BEST_DEAL_BOX, bestDealBoxForm.getId());
                imageService.deleteById(ImageType.BEST_DEAL_BOX, bestDealBoxForm.getId(), get.get(0));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new Response(true, "Đã thêm sản phẩm vào hotdeal thành công");

    }

    public List<BestDealBox> getAll() {
        return bestDealBoxRepository.getAll();
    }

    @Cacheable(value = "buffercache", key = "'BestDealBox'")
    public List<BestDealBox> list() {
        return bestDealBoxRepository.list();
    }

    /**
     * Xóa item trong hotdealbox
     *
     * @param id
     * @return
     */
    public Response del(String id) {
        BestDealBox find = bestDealBoxRepository.find(id);
        if (find == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        List<String> get = imageService.get(ImageType.BEST_DEAL_BOX, find.getItemId());
        if (get != null && get.size() > 0) {
            imageService.delete(ImageType.BEST_DEAL_BOX, find.getItemId());
        }
        bestDealBoxRepository.delete(id);
        return new Response(true, "Đã xóa sản phẩm BestDeal thành công");

    }

    public BestDealBox getByItemId(String id) {
        return bestDealBoxRepository.getByItemId(id);
    }

    /**
     * Đổi tiêu dề của hotdeail box
     *
     * @param bestDealBoxForm
     * @return
     */
    public Response editName(BestDealBoxForm bestDealBoxForm) {
        BestDealBox byCate = bestDealBoxRepository.getByItemId(bestDealBoxForm.getItemId());
        if (byCate == null) {
            return new Response(false, "Không tồn tại danh mục");
        }
        if (bestDealBoxForm.getTitle().equals("")) {
            return new Response(false, "Tên danh mục không được để trống");
        } else {
            byCate.setTitle(bestDealBoxForm.getTitle());
            bestDealBoxRepository.save(byCate);
        }
        return new Response(true, null, byCate);

    }

    /**
     * Thay đổi trạng thái bestdeail box
     *
     * @param id
     * @return
     */
    public Response editStatus(String id) {
        BestDealBox byCate = bestDealBoxRepository.getByItemId(id);
        if (byCate == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        byCate.setActive(!byCate.isActive());
        bestDealBoxRepository.save(byCate);
        return new Response(true, "Sửa trạng thái thành công", byCate);

    }
}
