package vn.chodientu.controller.cpservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import vn.chodientu.controller.service.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.component.ImageClient;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Image;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemDetail;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.ItemSource;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.form.ItemForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserService;

@Controller("cpserviceItemQuick")
@RequestMapping("/cpservice/itemquick")
public class ItemQuickController extends BaseRest {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;

    @Autowired
    private SellerService sellerService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ImageClient imageClient;
    @Autowired
    private Gson gson;

    /**
     * Tạo 1 thêm nhanh
     *
     * @param item
     * @param cId
     * @param sId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateproductquick", method = RequestMethod.POST)
    public Response createProductQuick(@RequestBody(required = false) Item item) {
        if (item == null) {
            item = new Item();
            item.setCreateTime(System.currentTimeMillis());
        }
        if (viewer.getAdministrator() != null) {
            item.setSellerId(viewer.getAdministrator().getId());
        } else {
            item.setSellerId("683884");
        }
        return itemService.add(item);
    }

    /**
     * Xóa lưu tạm
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/removeproductquick", method = RequestMethod.GET)
    public Response removeProductQuick(@RequestParam(value = "id", defaultValue = "") String id) {
        try {
            itemService.remove(id);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }

        return new Response(true, "Xóa thành công");
    }

    /**
     * Update detail
     *
     * @param itemDetail
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatedetail", method = RequestMethod.POST)
    public Response updateItemDetail(@RequestBody ItemDetail itemDetail) {
        try {
            itemService.updateDetail(itemDetail);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }

        return new Response(false);
    }

    @ResponseBody
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Response submit(@RequestBody List<Item> listItem) {
        if (viewer.getAdministrator() == null) {
            return new Response(false, "Bạn cần đăng nhập lại để thực hiện thao tác");
        }
        if (listItem != null && !listItem.isEmpty()) {
            if (listItem.get(0).getSellerName() == null || listItem.get(0).getSellerName().equals("")) {
                return new Response(false, "Bạn chưa nhập username cần đăng bán");
            }
            Response respUser = userService.getByUsername(listItem.get(0).getSellerName());
            if (!respUser.isSuccess() || !((User) respUser.getData()).isActive()) {
                if (!respUser.isSuccess()) {
                    respUser = userService.getByEmail(listItem.get(0).getSellerName());
                    if (!respUser.isSuccess() || !((User) respUser.getData()).isActive()) {
                        return new Response(false, "Tài khoản cần đăng bán không tồn tại hoặc đã bị khóa");
                    }
                } else {
                    return new Response(false, "Tài khoản cần đăng bán đang bị khóa");
                }
            }
            User user = (User) respUser.getData();
            Shop shop = shopService.getShop(user.getId());
            if (shop == null) {
                return new Response(false, "Tài khoản cần có shop để đăng bán nhanh");
            }

            if (user.getPhone() == null || user.getPhone().equals("")) {
                return new Response(false, "Tài khoản cần có số điện thoại mới được đăng bán nhanh");
            }
            if (shop.getCityId() == null || shop.getCityId().equals("") || shop.getDistrictId() == null || shop.getDistrictId().equals("")) {
                return new Response(false, "Tài khoản cần có địa chỉ mới được đăng bán nhanh");
            }

            Seller seller = sellerService.getById(user.getId());
            if (!seller.isNlIntegrated() || !seller.isScIntegrated()) {
                return new Response(false, "Tài khoản cần tích hợp đủ NgânLượng và ShipChung");
            }
            for (Item item : listItem) {
                item.setSellerId(user.getId());
                item.setShipmentPrice(seller.getShipmentPrice());
                item.setShipmentType(seller.getShipmentType());
                item.setCod(seller.isScIntegrated());
                item.setOnlinePayment(seller.isNlIntegrated());
                item.setListingType(ListingType.BUYNOW);
                item.setQuantity(10);
                item.setSource(ItemSource.SELLER);
                item.setActive(true);
                item.setApproved(true);
                item.setCompleted(true);
                try {
                    Response submitAdd = itemService.submitAdd(item, viewer.getAdministrator() == null ? null : viewer.getAdministrator().getId());
                    if (!submitAdd.isSuccess()) {
                        return submitAdd;
                    }
                } catch (Exception ex) {
                    return new Response(false, ex.getMessage());
                }
            }
            return new Response(true, "Đăng nhanh " + listItem.size() + " sản phẩm thành công");
        } else {
            return new Response(false, "Phải có ít nhất 1 sản phẩm đăng bán nhanh");
        }

    }

    @ResponseBody
    @RequestMapping(value = "/downloadfromeditor", method = RequestMethod.GET)
    public Response processDownloadImageFromEditor(@RequestParam(value = "imgs") String jsonImgs, @RequestParam(value = "id") String itemId) {
        if (jsonImgs.equals("")) {
            return new Response(false, "FAILED", jsonImgs);
        } else {
            List<String> imgs = gson.fromJson(jsonImgs, new TypeToken<List<String>>() {
            }.getType());
            Map<String, String> returnImgs = new HashMap<>();
            Map<String, Object> dataReturn = new HashMap<>();
            Map<String, String> newImgs = new HashMap<>();
            for (String img : imgs) {
                if (imageService.checkImage(img, null, 300, 300, false) && !img.contains(imageClient.getServerUrls()[0])) {
                    Response<String> resp = imageService.download(img.trim(), ImageType.ITEM, itemId);
                    if (resp.isSuccess()) {
                        returnImgs.put(img, imageService.getUrl(resp.getData()).getUrl());
                        newImgs.put(resp.getData(), imageService.getUrl(resp.getData()).getUrl());
                    }
                }
            }
            dataReturn.put("images", newImgs);
            dataReturn.put("imagesMapping", returnImgs);
            return new Response(true, "OK", dataReturn);
        }
    }

    /**
     * set ảnh đại diện cho sản phẩm
     *
     * @param imageId
     * @param itemId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/setdefaultimage", method = RequestMethod.GET)
    public Response setDefaultImage(@RequestParam(value = "imageId") String imageId, @RequestParam(value = "id") String itemId) {
        if (imageId != null && !imageId.equals("") && itemId != null && !itemId.equals("")) {
            imageService.resetFirstImage(imageId, itemId, ImageType.ITEM);
            Image firstImage = imageService.getFirstImage(itemId, ImageType.ITEM);
            if (firstImage != null) {
                return new Response(true, "", firstImage.getImageId());
            }
        }
        return new Response(false, "Chọn ảnh đại diện thất bại. Vui lòng thử lại");
    }

    /**
     * lấy ảnh đại diện cho sản phẩm
     *
     * @param itemId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getdefaultimage", method = RequestMethod.GET)
    public Response getDefaultImage(@RequestParam(value = "id") String itemId) {
        if (itemId != null && !itemId.equals("")) {
            Image firstImage = imageService.getFirstImage(itemId, ImageType.ITEM);
            if (firstImage != null) {
                return new Response(true, "", firstImage.getImageId());
            }
        }
        return new Response(false, "Sản phẩm không tồn tại hoặc chưa có ảnh");
    }

    @ResponseBody
    @RequestMapping(value = "/genimages", method = RequestMethod.GET)
    public Response genImages(@RequestParam(value = "ids", defaultValue = "") String ids) throws Exception {
        HashMap<String, String> images = new HashMap<>();
        List<String> imgIds = gson.fromJson(ids, ArrayList.class);
        for (String imgId : imgIds) {
            images.put(imgId, imageService.getUrl(imgId).getUrl());
        }
        return new Response(true, "Danh sách link ảnh", images);
    }

    /**
     * Thêm mới ảnh
     *
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addimage", method = RequestMethod.POST)
    @ResponseBody
    public Response addImage(@ModelAttribute ItemForm form) throws Exception {
        Item item = itemService.get(form.getId());
        if (item == null) {
            return new Response(false, "Sản phẩm chưa được tạo, bạn vui lòng thử lại sau vài giây");
        }
        List<String> images = item.getImages();
        if (images == null) {
            images = new ArrayList<>();
        }
        if (form.getImageUrl() != null && !form.getImageUrl().equals("")) {
            if (imageService.checkImage(form.getImageUrl(), null, 300, 300, false)) {
                Response<String> resp = imageService.download(form.getImageUrl().trim(), ImageType.ITEM, item.getId());
                if (resp == null || !resp.isSuccess()) {
                    return new Response(false, resp.getMessage());
                }
                images.add(resp.getData());
            } else {
                return new Response(false, "Ảnh phải có kích thước tối thiểu 1 chiều là 300px");
            }
        } else if (form.getImage() != null && form.getImage().getSize() > 0) {
            if (imageService.checkImage(null, form.getImage(), 300, 300, false)) {
                Response resp = imageService.upload(form.getImage(), ImageType.ITEM, item.getId());
                if (resp == null || !resp.isSuccess()) {
                    return new Response(false, resp.getMessage());
                }
                images.add((String) resp.getData());
            } else {
                return new Response(false, "Ảnh phải có kích thước tối thiểu 1 chiều là 300px");
            }
        }
        item.setImages(images);
        return new Response(true, "Chi tiết sản phẩm", item);
    }

    /**
     * Xóa 1 ảnh
     *
     * @param id
     * @param imageName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delimage", method = RequestMethod.GET)
    @ResponseBody
    public Response delImage(@RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "name", defaultValue = "") String imageName) throws Exception {
        Item item = itemService.get(id);
        if (item == null) {
            return new Response(false, "Sản phẩm chưa được tạo, bạn vui lòng thử lại sau vài giây");
        }
        if (imageName == null || imageName.trim().equals("")) {
            return new Response(false, "Phải chọn ảnh để xóa");
        }
        imageService.deleteById(ImageType.ITEM, item.getId(), imageName);
        try {
            return new Response(true, "Chi tiết sản phẩm", item);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

}
