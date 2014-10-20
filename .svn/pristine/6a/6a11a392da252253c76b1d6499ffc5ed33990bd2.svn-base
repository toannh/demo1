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
import vn.chodientu.entity.form.BestDealBoxImgForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.BestDealBoxService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;

/**
 *
 * @author Phuongdt
 */
@Controller("cpBestDealBoxService")
@RequestMapping(value = "/cpservice/bestdealbox")
public class BestDealBoxController extends BaseRest {

    @Autowired
    private BestDealBoxService bestDealBoxService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ItemService itemService;

    /**
     * Thêm HotDeal
     *
     * @param bestDealBoxForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@ModelAttribute BestDealBoxForm bestDealBoxForm) throws Exception {
        return bestDealBoxService.add(bestDealBoxForm);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@ModelAttribute BestDealBoxForm bestDealBoxForm) throws Exception {
        return bestDealBoxService.edit(bestDealBoxForm);
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
        return bestDealBoxService.del(id);

    }
    /**
     * Đổi tiêu đề trong bestdeailbox
     *
     * @param bestDealBoxForm
     * @return
     */
    @RequestMapping(value = "/changename", method = RequestMethod.POST)
    @ResponseBody
    public Response changename(@RequestBody BestDealBoxForm bestDealBoxForm){
        return bestDealBoxService.editName(bestDealBoxForm);

    }
    /**
     * Đổi trạng thái trong bestdeailbox
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/changestatus", method = RequestMethod.GET)
    @ResponseBody
    public Response changestatus(@RequestParam String itemId){
        return bestDealBoxService.editStatus(itemId);

    }

    @RequestMapping(value = "/getimagecrop", method = RequestMethod.GET)
    @ResponseBody
    public Response getImageCrop(@RequestParam String id) throws Exception {
        Item item = itemService.get(id);
        List<String> get = imageService.get(ImageType.BEST_DEAL_BOX, id);
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
     * Thêm mới ảnh bestdealbox
     *
     * @param bestDealBoxImgForm
     * @return
     */
    @RequestMapping(value = "/addimage", method = RequestMethod.POST)
    @ResponseBody
    public Response addImage(@ModelAttribute BestDealBoxImgForm bestDealBoxImgForm) {
        List<String> images = new ArrayList<>();
        Response resp;
        if (bestDealBoxImgForm.getImages() != null && bestDealBoxImgForm.getImages().getSize() > 0) {
            List<String> get = imageService.get(ImageType.BEST_DEAL_BOX, bestDealBoxImgForm.getId());
            if (get != null && get.size() > 0) {
                imageService.delete(ImageType.BEST_DEAL_BOX, bestDealBoxImgForm.getId());
            }
            resp = imageService.upload(bestDealBoxImgForm.getImages(), ImageType.BEST_DEAL_BOX, bestDealBoxImgForm.getId());
            if (resp == null || !resp.isSuccess()) {
                return new Response(false, resp.getMessage());
            }

        } else {
            return new Response(false, "Bạn phải chọn ảnh để thêm");
        }
        try {
            ArrayList<String> url = new ArrayList<String>();
            List<String> get = imageService.get(ImageType.BEST_DEAL_BOX, bestDealBoxImgForm.getId());
            for (String imgs : get) {
                url.add(imageService.getUrl(imgs).getUrl());
            }
            return new Response(true, "Đã thêm ảnh thành công", url);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }
    

}
