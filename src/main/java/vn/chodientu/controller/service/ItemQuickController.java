package vn.chodientu.controller.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemDetail;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.ItemSource;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.SellerService;

@Controller("serviceItemQuick")
@RequestMapping("/itemquick")
public class ItemQuickController extends BaseRest {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;

    @Autowired
    private SellerService sellerService;

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
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn cần phải đăng nhập lại để thực hiện tiếp thao tác");
        }
        if (item == null) {
            item = new Item();
            item.setCreateTime(System.currentTimeMillis());
        }
        if (item.getSellerId() != null && !item.getSellerId().equals("") && !item.getSellerId().equals(viewer.getUser().getId())) {
            return new Response(false, "Sản phẩm của người bán khác");
        }
        item.setSellerId(viewer.getUser().getId());
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
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn cần phải đăng nhập lại để thực hiện tiếp thao tác");
        }
        try {
            Item item = itemService.get(id);
            if (item.getSellerId() != null && !item.getSellerId().equals("") && !item.getSellerId().equals(viewer.getUser().getId())) {
                return new Response(false, "Sản phẩm thuộc sở hữu của người bán khác, bạn không có quyền xóa");
            }
            itemService.remove(id);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }

        return new Response(true, "Xóa sản phẩm lưu tạm thành công");
    }

    /**
     * Kích hoạt đăng nhanh
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/activenow", method = RequestMethod.GET)
    public Response activeNow() {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        return sellerService.activeItemSubmitQuick(viewer.getUser());
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
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn cần phải đăng nhập lại để thực hiện tiếp thao tác");
        }
        try {
            Item item = itemService.get(itemDetail.getItemId());
            if (item.getSellerId() != null && !item.getSellerId().equals("") && !item.getSellerId().equals(viewer.getUser().getId())) {
                return new Response(false, "Sản phẩm của người bán khác");
            }
            itemService.updateDetail(itemDetail);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }

        return new Response(false);
    }
    /**
     * Update detail
     *
     * @param itemDetail
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Response submit(@RequestBody List<Item> listItem) {
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn cần phải đăng nhập lại để thực hiện tiếp thao tác");
        }
        Seller seller = sellerService.getById(viewer.getUser().getId());
        for (Item item : listItem) {
            item.setSellerId(viewer.getUser().getId());
            item.setShipmentPrice(seller.getShipmentPrice());
            item.setShipmentType(seller.getShipmentType());
            item.setCod(seller.isScIntegrated());
            item.setOnlinePayment(seller.isNlIntegrated());
            item.setListingType(ListingType.BUYNOW);
            item.setQuantity(1);
            item.setSource(ItemSource.SELLER);
            try {
                Response submitAdd = itemService.submitAdd(item, null);
                if(!submitAdd.isSuccess()){
                    return submitAdd;
                }
            } catch (Exception ex) {
                return new Response(false, ex.getMessage());
            }
        }
        return new Response(true,"Đăng nhanh "+listItem.size()+" sản phẩm thành công");
    }
}
