/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.TopSellerBox;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.HotDealBoxForm;
import vn.chodientu.entity.form.HotDealBoxImgForm;
import vn.chodientu.entity.form.TopSellerBoxCropForm;
import vn.chodientu.entity.form.TopSellerBoxForm;
import vn.chodientu.entity.form.TopSellerBoxImgForm;
import vn.chodientu.entity.form.TopSellerBoxItemForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.TopSellerBoxService;

/**
 *
 * @author Phuongdt
 */
@Controller("cpTopSellerBoxService")
@RequestMapping(value = "/cpservice/topsellerbox")
public class TopSellerBoxController extends BaseRest {

    @Autowired
    private TopSellerBoxService topSellerBoxService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ItemService itemService;

    /**
     * Thêm HotDeal
     *
     * @param topSellerBoxForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@ModelAttribute TopSellerBoxForm topSellerBoxForm) throws Exception {
        return topSellerBoxService.add(topSellerBoxForm);
    }

    @RequestMapping(value = "/getimagecrop", method = RequestMethod.GET)
    @ResponseBody
    public Response getImageCrop(@RequestParam String id) throws Exception {
        List<String> get = imageService.get(ImageType.TOP_SELLER_BOX, id);
        String url = null;
        List<String> img = new ArrayList<>();
        if (get != null && get.size() > 0) {
            url = imageService.getUrl(get.get(0)).getUrl();
            img.add(url);
        }
        Map<String, String> map = new HashMap<>();
        map.put("image", img.get(0));
        map.put("id", id);
        return new Response(true, null, map);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@ModelAttribute TopSellerBoxCropForm hotDealBoxForm) throws Exception {
        return topSellerBoxService.edit(hotDealBoxForm);
    }

    /**
     * Thêm mới ảnh model
     *
     * @param topSellerBoxForm
     * @return
     */
    @RequestMapping(value = "/addimage", method = RequestMethod.POST)
    @ResponseBody
    public Response addImage(@ModelAttribute TopSellerBoxForm topSellerBoxForm) {
        Response resp;
        if (topSellerBoxForm.getImage() != null && topSellerBoxForm.getImage().getSize() > 0) {
            List<String> get = imageService.get(ImageType.TOP_SELLER_BOX, topSellerBoxForm.getId());
            if (get != null && get.size() > 0) {
                imageService.delete(ImageType.TOP_SELLER_BOX, topSellerBoxForm.getId());
            }
            resp = imageService.upload(topSellerBoxForm.getImage(), ImageType.TOP_SELLER_BOX, topSellerBoxForm.getId());
            if (resp == null || !resp.isSuccess()) {
                return new Response(false, resp.getMessage());
            }

        } else {
            return new Response(false, "Bạn phải chọn ảnh để thêm");
        }
        try {
            ArrayList<String> url = new ArrayList<String>();
            List<String> get = imageService.get(ImageType.TOP_SELLER_BOX, topSellerBoxForm.getId());
            for (String imgs : get) {
                url.add(imageService.getUrl(imgs).getUrl());
            }
            return new Response(true, "Đã thêm ảnh thành công", url);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * Xóa người bán trong topseller
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response del(@RequestParam String id) throws Exception {
        return topSellerBoxService.del(id);

    }

    @RequestMapping(value = "/gettopseller", method = RequestMethod.GET)
    @ResponseBody
    public Response getTopSellerById(@RequestParam String id) throws Exception {
        return topSellerBoxService.getTopSellerBox(id);

    }

    @RequestMapping(value = "/getlisttopseller", method = RequestMethod.GET)
    @ResponseBody
    public List<TopSellerBox> getListTopSeller(@RequestParam String id) throws Exception {
        return topSellerBoxService.list();

    }

    @RequestMapping(value = "/additem", method = RequestMethod.POST)
    @ResponseBody
    public Response additem(@RequestParam String sellerId, @RequestBody TopSellerBoxForm topSellerBoxForm) throws Exception {
        topSellerBoxForm.setSellerId(sellerId);
        return topSellerBoxService.addItem(topSellerBoxForm);

    }

    /**
     * Đổi tiêu đề trong hotdealbox
     *
     * @param topSellerBoxForm
     * @return
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/changenameitem", method = RequestMethod.POST)
    @ResponseBody
    public Response changenameitem(@RequestBody TopSellerBoxForm topSellerBoxForm) throws Exception {
        return topSellerBoxService.editName(topSellerBoxForm);

    }

    /**
     * Đổi trạng thái trong hotdeallbox
     *
     * @param topSellerBoxForm
     * @return
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/changestatusitem", method = RequestMethod.POST)
    @ResponseBody
    public Response changestatusitem(@RequestBody TopSellerBoxForm topSellerBoxForm) throws Exception {
        return topSellerBoxService.editStatusItem(topSellerBoxForm);

    }

    /**
     * Đổi trạng thái trong hotdeallbox
     *
     * @param topSellerBoxForm
     * @return
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/changepositionitem", method = RequestMethod.POST)
    @ResponseBody
    public Response changepositionitem(@RequestBody TopSellerBoxForm topSellerBoxForm) throws Exception {
        return topSellerBoxService.editPositionItem(topSellerBoxForm);

    }

    @RequestMapping(value = "/getimageitemcrop", method = RequestMethod.GET)
    @ResponseBody
    public Response getImageItemCrop(@RequestParam String id) throws Exception {
        Item item = itemService.get(id);
        List<String> get = imageService.get(ImageType.TOP_SELLER_BOX, id);
        String url = null;
        List<String> img = new ArrayList<>();
        if (get != null && get.size() > 0) {
            url = imageService.getUrl(get.get(0)).getUrl();
            img.add(url);
        }
        item.setImages(img);
        return new Response(true, null, item);
    }

    /**
     * Thêm mới ảnh sản phẩm topsellerbox
     *
     * @param hotDealBoxForm
     * @return
     */
    @RequestMapping(value = "/addimageitem", method = RequestMethod.POST)
    @ResponseBody
    public Response addImageItem(@ModelAttribute TopSellerBoxImgForm hotDealBoxForm) {
        Response resp;
        if (hotDealBoxForm.getImages() != null && hotDealBoxForm.getImages().getSize() > 0) {
            List<String> get = imageService.get(ImageType.TOP_SELLER_BOX, hotDealBoxForm.getItemId());
            if (get != null && get.size() > 0) {
                imageService.delete(ImageType.TOP_SELLER_BOX, hotDealBoxForm.getItemId());
            }
            resp = imageService.upload(hotDealBoxForm.getImages(), ImageType.TOP_SELLER_BOX, hotDealBoxForm.getItemId());
            if (resp == null || !resp.isSuccess()) {
                return new Response(false, resp.getMessage());
            }

        } else {
            return new Response(false, "Bạn phải chọn ảnh để thêm");
        }
        try {
            ArrayList<String> url = new ArrayList<String>();
            List<String> get = imageService.get(ImageType.TOP_SELLER_BOX, hotDealBoxForm.getItemId());
            for (String imgs : get) {
                url.add(imageService.getUrl(imgs).getUrl());
            }
            return new Response(true, "Đã thêm ảnh thành công", url);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * Thay đổi ảnh sản phẩm ở box người bán uy tín
     * @param topSellerBoxItemForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edititem", method = RequestMethod.POST)
    @ResponseBody
    public Response edititem(@ModelAttribute TopSellerBoxItemForm topSellerBoxItemForm) throws Exception {
        return topSellerBoxService.editItem(topSellerBoxItemForm);
    }
      /**
     * Xóa sản phẩm của người bán trong topseller
     *
     * @param itemId
     * @param shellerId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delitem", method = RequestMethod.GET)
    @ResponseBody
    public Response delitem(@RequestParam String itemId,@RequestParam String shellerId) throws Exception {
        return topSellerBoxService.delItem(itemId,shellerId);

    }
    /**
     * Sửa trạng thái người bán trong box topseller
     * @param sellerId
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/editstatus", method = RequestMethod.GET)
    @ResponseBody
    public Response editstatus(@RequestParam String sellerId) throws Exception {
        return topSellerBoxService.editStatus(sellerId);

    }
    /***
     * Sửa vị trí sắp xếp người bán trong box topseller
     * @param sellerId
     * @param value
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/editposition", method = RequestMethod.GET)
    @ResponseBody
    public Response editPosition(@RequestParam String sellerId,@RequestParam int value) throws Exception {
        return topSellerBoxService.editPosition(sellerId,value);

    }

}
