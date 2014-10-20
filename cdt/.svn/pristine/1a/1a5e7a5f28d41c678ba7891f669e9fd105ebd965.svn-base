/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import java.util.ArrayList;
import java.util.List;
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
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.BestDealBoxForm;
import vn.chodientu.entity.form.HotDealBoxForm;
import vn.chodientu.entity.form.HotDealBoxImgForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.HotDealBoxService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;

/**
 *
 * @author Phuongdt
 */
@Controller("cpHotDealBoxService")
@RequestMapping(value = "/cpservice/hotdealbox")
public class HotDealBoxController extends BaseRest {

    @Autowired
    private HotDealBoxService hotDealBoxService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ItemService itemService;

    /**
     * Thêm HotDeal
     *
     * @param hotDealBoxForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@ModelAttribute HotDealBoxForm hotDealBoxForm) throws Exception {
        return hotDealBoxService.add(hotDealBoxForm);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@ModelAttribute HotDealBoxForm hotDealBoxForm) throws Exception {
        return hotDealBoxService.edit(hotDealBoxForm);
    }

    /**
     * Sửa banner HotDealbox
     *
     * @param hotDealBoxForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editbanner", method = RequestMethod.POST)
    @ResponseBody
    public Response editbanner(@ModelAttribute HotDealBoxForm hotDealBoxForm) throws Exception {
        return hotDealBoxService.addImageBanner(hotDealBoxForm);
    }

    @RequestMapping(value = "/getbanner", method = RequestMethod.GET)
    @ResponseBody
    public Response getBanner(@ModelAttribute HotDealBoxForm hotDealBoxForm) throws Exception {
        List<String> get = imageService.get(ImageType.HOT_DEAL_BOX, "hotdealbox");
        String url = null;
        if (get != null && get.size() > 0) {
            url = imageService.getUrl(get.get(0)).getUrl();
        }

        return new Response(true, null, url);
    }

    /**
     * Xóa sản phẩm trong hotdeal
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response del(@RequestParam String id) throws Exception {
        return hotDealBoxService.del(id);

    }
    /**
     * Đổi tiêu đề trong hotdealbox
     *
     * @param bestDealBoxForm
     * @return
     */
    @RequestMapping(value = "/changename", method = RequestMethod.POST)
    @ResponseBody
    public Response changename(@RequestBody HotDealBoxForm hotDealBoxForm){
        return hotDealBoxService.editName(hotDealBoxForm);

    }
    /**
     * Đổi trạng thái trong hotdeallbox
     *
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/changestatus", method = RequestMethod.GET)
    @ResponseBody
    public Response changestatus(@RequestParam String itemId){
        return hotDealBoxService.editStatus(itemId);

    }

    @RequestMapping(value = "/getimagecrop", method = RequestMethod.GET)
    @ResponseBody
    public Response getImageCrop(@RequestParam String id) throws Exception {
        Item item = itemService.get(id);
        List<String> get = imageService.get(ImageType.HOT_DEAL_BOX, id);
        String url = null;
        List<String> img = new ArrayList<>();
        if (get != null && get.size() > 0) {
            url = imageService.getUrl(get.get(0)).thumbnail(350, 350, "outbound").getUrl();
            img.add(url);
        }
        item.setImages(img);
        return new Response(true, null, item);
    }

    /**
     * Thêm mới ảnh hotdealbox
     *
     * @param hotDealBoxForm
     * @return
     */
    @RequestMapping(value = "/addimage", method = RequestMethod.POST)
    @ResponseBody
    public Response addImage(@ModelAttribute HotDealBoxImgForm hotDealBoxForm) {
        Response resp;
        if (hotDealBoxForm.getImages() != null && hotDealBoxForm.getImages().getSize() > 0) {
            List<String> get = imageService.get(ImageType.HOT_DEAL_BOX, hotDealBoxForm.getId());
            if (get != null && get.size() > 0) {
                imageService.delete(ImageType.HOT_DEAL_BOX, hotDealBoxForm.getId());
            }
            resp = imageService.upload(hotDealBoxForm.getImages(), ImageType.HOT_DEAL_BOX, hotDealBoxForm.getId());
            if (resp == null || !resp.isSuccess()) {
                return new Response(false, resp.getMessage());
            }

        } else {
            return new Response(false, "Bạn phải chọn ảnh để thêm");
        }
        try {
            ArrayList<String> url = new ArrayList<String>();
            List<String> get = imageService.get(ImageType.HOT_DEAL_BOX, hotDealBoxForm.getId());
            for (String imgs : get) {
                url.add(imageService.getUrl(imgs).getUrl());
            }
            return new Response(true, "Đã thêm ảnh thành công", url);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

}
