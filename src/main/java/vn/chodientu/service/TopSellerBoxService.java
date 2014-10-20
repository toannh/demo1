package vn.chodientu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.TopSellerBox;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.TopSellerBoxCropForm;
import vn.chodientu.entity.form.TopSellerBoxForm;
import vn.chodientu.entity.form.TopSellerBoxItemForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.TopSellerBoxRepository;

/**
 * @since Jun 6, 2014
 * @author Phuongdt
 */
@Service
public class TopSellerBoxService {

    @Autowired
    private TopSellerBoxRepository topSellerBoxRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private ImageService imageService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private ItemService itemService;

    /**
     * Thêm mới TopSellerBox
     *
     * @param topSellerBoxForm
     * @return
     */
    public Response add(TopSellerBoxForm topSellerBoxForm) throws Exception {
        Map<String, String> error = validator.validate(topSellerBoxForm);
        if (topSellerBoxForm.getSellerId() == null || topSellerBoxForm.getSellerId().equals("")) {
            error.put("sellerId", "Bạn chưa nhập mã người bán");
        } else {
            Seller byId = sellerService.getById(topSellerBoxForm.getSellerId());
            if (byId == null) {
                error.put("sellerId", "Người bán không tồn tại vui lòng chọn lại");
            }
            List<String> sellerIds = new ArrayList<>();
            List<TopSellerBox> all = topSellerBoxRepository.getAll();
            if (all != null && all.size() > 0) {
                for (TopSellerBox topSellerBox : all) {
                    sellerIds.add(topSellerBox.getSellerId());
                }
                if (sellerIds.contains(topSellerBoxForm.getSellerId())) {
                    error.put("sellerId", "Đã tồn tại người bán trong box");
                }
            }

        }
        if (topSellerBoxForm.getImage() == null || topSellerBoxForm.getImage().isEmpty()) {
            error.put("image", "Bạn chưa chọn ảnh");
        }
        if (!error.isEmpty()) {
            return new Response(false, null, error);
        } else {
            TopSellerBox topSellerBox = new TopSellerBox();
            String genId = topSellerBoxRepository.genId();
            topSellerBox.setId(genId);
            topSellerBox.setActive(true);
            topSellerBox.setPosition(1);
            topSellerBox.setSellerId(topSellerBoxForm.getSellerId());
            imageService.upload(topSellerBoxForm.getImage(), ImageType.TOP_SELLER_BOX, genId);
            topSellerBoxRepository.save(topSellerBox);
            return new Response(true, "Đã thêm thành công");
        }

    }

    /**
     * Thêm sản phẩm
     *
     * @param topSellerBoxForm
     * @return
     * @throws Exception
     */
    public Response addItem(TopSellerBoxForm topSellerBoxForm) throws Exception {
        Map<String, String> errors = validator.validate(topSellerBoxForm);
        if (topSellerBoxForm.getId() == null || topSellerBoxForm.getId().equals("")) {
            throw new Exception("Bạn chưa nhập ID");
        }
        Item item = itemService.get(topSellerBoxForm.getId());
        if (item.getSellerId() == null || !item.getSellerId().equals(topSellerBoxForm.getSellerId())) {
            throw new Exception("Sản phẩm không thuộc người bán này");
        }
        if (topSellerBoxRepository.exitesItemBySeller(topSellerBoxForm.getSellerId(), topSellerBoxForm.getId())) {
            throw new Exception("Đã tồn tại sản phẩm trong box người bán rồi");
        }
        TopSellerBox bySellerId = topSellerBoxRepository.getBySellerId(topSellerBoxForm.getSellerId());
        TopSellerBoxItemForm boxItemForm = new TopSellerBoxItemForm();
        boxItemForm.setItemId(topSellerBoxForm.getId());
        boxItemForm.setActive(true);
        boxItemForm.setPosition(1);
        boxItemForm.setTitle(item.getName());

        if (bySellerId != null) {
            if (bySellerId.getTopSellerBoxItemForms() != null && bySellerId.getTopSellerBoxItemForms().size() > 0) {
                bySellerId.getTopSellerBoxItemForms().add(boxItemForm);
            } else {
                bySellerId.setTopSellerBoxItemForms(new ArrayList<TopSellerBoxItemForm>());
                bySellerId.getTopSellerBoxItemForms().add(boxItemForm);
            }
        }
        topSellerBoxRepository.save(bySellerId);
        return new Response(true, "Thêm sản phẩm thành công vào box người bán nổi bật");
    }

    public List<TopSellerBox> getAll() {
        return topSellerBoxRepository.getAll();
    }

    @Cacheable(value = "buffercache", key = "'TopSellerBox'")
    public List<TopSellerBox> list() {
        return topSellerBoxRepository.list();
    }

    public TopSellerBox getBySellerId(String sellerId) {
        return topSellerBoxRepository.getBySellerId(sellerId);
    }

    public Response edit(TopSellerBoxCropForm topSellerBoxForm) throws Exception {

        List<String> get = imageService.get(ImageType.TOP_SELLER_BOX, topSellerBoxForm.getId());
        if (get != null && get.size() > 0) {
            if (topSellerBoxForm.getWidth() > 0 && topSellerBoxForm.getHeight() > 0) {
                String url = imageService.getUrl(get.get(0)).crop(topSellerBoxForm.getX(), topSellerBoxForm.getY(), topSellerBoxForm.getWidth(), topSellerBoxForm.getHeight()).thumbnail(50, 50, "outbound").getUrl();
                imageService.download(url, ImageType.TOP_SELLER_BOX, topSellerBoxForm.getId());
                imageService.deleteById(ImageType.TOP_SELLER_BOX, topSellerBoxForm.getId(), get.get(0));
            } else {
                String url = imageService.getUrl(get.get(0)).thumbnail(50, 50, "outbound").getUrl();
                imageService.download(url, ImageType.TOP_SELLER_BOX, topSellerBoxForm.getId());
                imageService.deleteById(ImageType.TOP_SELLER_BOX, topSellerBoxForm.getId(), get.get(0));
            }
            return new Response(true, "Đã thêm sản phẩm vào hotdeal thành công");
        } else {
            return new Response(false, "Bạn chưa chọn ảnh up lên");
        }

    }

    /**
     * Xóa item trong hotdealbox
     *
     * @param id
     * @return
     */
    public Response del(String id) {
        TopSellerBox one = topSellerBoxRepository.find(id);
        List<String> get = imageService.get(ImageType.TOP_SELLER_BOX, id);
        if (get != null && get.size() > 0) {
            imageService.delete(ImageType.TOP_SELLER_BOX, id);
        }

        topSellerBoxRepository.delete(one);
        return new Response(true, "Đã xóa người bán thành công");

    }

    /**
     * Lấy Top Seller box theo id
     *
     * @param id
     * @return
     */
    public Response getTopSellerBox(String id) {
        TopSellerBox find = topSellerBoxRepository.find(id);
        if (find == null) {
            return new Response(false, "Không tồn tại bản ghi");
        } else {
            return new Response(true, null, find);
        }
    }

    /**
     * Đổi tiêu dề của topseller box
     *
     * @param hotDealBoxForm
     * @return
     */
    public Response editName(TopSellerBoxForm hotDealBoxForm) throws Exception {
        TopSellerBox byCate = topSellerBoxRepository.getBySellerId(hotDealBoxForm.getSellerId());
        if (byCate == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        List<TopSellerBoxItemForm> topSellerBoxItemForms = hotDealBoxForm.getTopSellerBoxItemForms();
        if (topSellerBoxItemForms != null && topSellerBoxItemForms.size() > 0) {
            String itemId = topSellerBoxItemForms.get(0).getItemId();
            String title = topSellerBoxItemForms.get(0).getTitle();
            if (title == null || title.equals("")) {
                throw new Exception("Tiêu đề không được để trống");
            }
            List<TopSellerBoxItemForm> boxItemForms = byCate.getTopSellerBoxItemForms();
            if (boxItemForms != null && boxItemForms.size() > 0) {
                for (TopSellerBoxItemForm boxItemForm : boxItemForms) {
                    if (boxItemForm.getItemId().equals(itemId)) {
                        boxItemForm.setTitle(title);
                        break;
                    }
                }
                byCate.setTopSellerBoxItemForms(boxItemForms);
            }
            topSellerBoxRepository.save(byCate);
        }
        return new Response(true, null, byCate);

    }

    /**
     * Đổi tiêu dề của topseller box
     *
     * @param hotDealBoxForm
     * @return
     */
    public Response editPositionItem(TopSellerBoxForm hotDealBoxForm) throws Exception {
        TopSellerBox byCate = topSellerBoxRepository.getBySellerId(hotDealBoxForm.getSellerId());
        if (byCate == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        List<TopSellerBoxItemForm> topSellerBoxItemForms = hotDealBoxForm.getTopSellerBoxItemForms();
        if (topSellerBoxItemForms != null && topSellerBoxItemForms.size() > 0) {
            String itemId = topSellerBoxItemForms.get(0).getItemId();
            int position = topSellerBoxItemForms.get(0).getPosition();
            if (position < 0) {
                throw new Exception("Thứ tự phải là số >=0");
            }
            List<TopSellerBoxItemForm> boxItemForms = byCate.getTopSellerBoxItemForms();
            if (boxItemForms != null && boxItemForms.size() > 0) {
                for (TopSellerBoxItemForm boxItemForm : boxItemForms) {
                    if (boxItemForm.getItemId().equals(itemId)) {
                        boxItemForm.setPosition(position);
                        break;
                    }
                }
                byCate.setTopSellerBoxItemForms(boxItemForms);
            }
            topSellerBoxRepository.save(byCate);
        }
        return new Response(true, null, byCate);

    }

    /**
     * Thay đổi trạng thái hotdeail box
     *
     * @param topSellerBoxForm
     * @return
     * @throws java.lang.Exception
     */
    public Response editStatusItem(TopSellerBoxForm topSellerBoxForm) throws Exception {
        TopSellerBox byCate = topSellerBoxRepository.getBySellerId(topSellerBoxForm.getSellerId());
        if (byCate == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        Map<String, String> map = new HashMap<>();
        boolean fag = false;
        String idItem = "";
        List<TopSellerBoxItemForm> topSellerBoxItemForms = topSellerBoxForm.getTopSellerBoxItemForms();
        if (topSellerBoxItemForms != null && topSellerBoxItemForms.size() > 0) {
            String itemId = topSellerBoxItemForms.get(0).getItemId();
            List<TopSellerBoxItemForm> boxItemForms = byCate.getTopSellerBoxItemForms();
            if (boxItemForms != null && boxItemForms.size() > 0) {
                for (TopSellerBoxItemForm boxItemForm : boxItemForms) {
                    if (boxItemForm.getItemId().equals(itemId)) {
                        fag = !boxItemForm.isActive();
                        idItem = boxItemForm.getItemId();
                        boxItemForm.setActive(!boxItemForm.isActive());
                        break;
                    }
                }
                byCate.setTopSellerBoxItemForms(boxItemForms);
            }
            topSellerBoxRepository.save(byCate);
        }
        map.put("itemId", idItem);
        if (fag == true) {
            map.put("active", "true");
        } else {
            map.put("active", "false");
        }
        return new Response(true, null, map);

    }

    /**
     * Thay đổi ảnh item vào box người bán uy tín
     *
     * @param sellerBoxItemForm
     * @return
     * @throws Exception
     */
    public Response editItem(TopSellerBoxItemForm sellerBoxItemForm) throws Exception {
        List<String> get = imageService.get(ImageType.TOP_SELLER_BOX, sellerBoxItemForm.getItemId());
        if (get == null || get.size() <= 0) {
            return new Response(false, "Bạn đã chọn ảnh đâu");
        }
        if (sellerBoxItemForm.getWidth() > 0 && sellerBoxItemForm.getHeight() > 0) {
            String url = imageService.getUrl(get.get(0)).crop(sellerBoxItemForm.getX(), sellerBoxItemForm.getY(), sellerBoxItemForm.getWidth(), sellerBoxItemForm.getHeight()).thumbnail(201, 192, "outbound").getUrl();
            imageService.download(url, ImageType.TOP_SELLER_BOX, sellerBoxItemForm.getItemId());
            imageService.deleteById(ImageType.TOP_SELLER_BOX, sellerBoxItemForm.getItemId(), get.get(0));
        } else {

            String url = imageService.getUrl(get.get(0)).thumbnail(199, 190, "outbound").getUrl();
            imageService.download(url, ImageType.TOP_SELLER_BOX, sellerBoxItemForm.getItemId());
            imageService.deleteById(ImageType.TOP_SELLER_BOX, sellerBoxItemForm.getItemId(), get.get(0));
        }
        return new Response(true, "Lưu thành công");

    }

    /**
     * Thay đổi del sản phẩm ở box người bán uy tín
     *
     * @param itemId
     * @param sellerId
     * @return
     * @throws Exception
     */
    public Response delItem(String itemId, String sellerId) throws Exception {
        TopSellerBox bySellerId = topSellerBoxRepository.getBySellerId(sellerId);
        if (bySellerId == null) {
            return new Response(false, "Không tồn tại người bán");
        }
        boolean fag = false;
        List<TopSellerBoxItemForm> sellerBoxItemForms = new ArrayList<>();
        List<TopSellerBoxItemForm> boxItemForms = bySellerId.getTopSellerBoxItemForms();
        if (boxItemForms != null && boxItemForms.size() > 0) {
            for (TopSellerBoxItemForm boxItemForm : boxItemForms) {
                if (!boxItemForm.getItemId().equals(itemId)) {
                    sellerBoxItemForms.add(boxItemForm);
                } else {
                    fag = true;
                }
            }
        }

        if (sellerBoxItemForms != null && !sellerBoxItemForms.isEmpty()) {
            bySellerId.setTopSellerBoxItemForms(new ArrayList<TopSellerBoxItemForm>());
            bySellerId.setTopSellerBoxItemForms(sellerBoxItemForms);
        }
        if (fag) {
            imageService.delete(ImageType.TOP_SELLER_BOX, itemId);
        }
        topSellerBoxRepository.save(bySellerId);
        return new Response(true, "Xóa thành công");

    }

    /**
     * Thay đổi trạng thái topseller box
     *
     * @param id
     * @return
     */
    public Response editStatus(String id) {
        TopSellerBox byCate = topSellerBoxRepository.getBySellerId(id);
        if (byCate == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        byCate.setActive(!byCate.isActive());
        topSellerBoxRepository.save(byCate);
        return new Response(true, "Sửa trạng thái thành công", byCate);

    }

    /**
     * Thay đổi vị trí người bán trong topseller box
     *
     * @param id
     * @return
     */
    public Response editPosition(String id, int value) {
        TopSellerBox byCate = topSellerBoxRepository.getBySellerId(id);
        if (byCate == null) {
            return new Response(false, "Không tồn tại bản ghi");
        }
        byCate.setPosition(value);
        topSellerBoxRepository.save(byCate);
        return new Response(true, "Sửa vị trí thành công", byCate);

    }
}
