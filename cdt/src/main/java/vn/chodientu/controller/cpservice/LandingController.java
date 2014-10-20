/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

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
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Landing;
import vn.chodientu.entity.db.LandingCategory;
import vn.chodientu.entity.db.LandingItem;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.ImageForm;
import vn.chodientu.entity.form.LandingForm;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.LandingService;

/**
 *
 * @author Admin
 */
@Controller("cpLandingService")
@RequestMapping(value = "/cpservice/landing")
public class LandingController extends BaseRest {

    @Autowired
    private LandingService landingService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/addlanding", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@ModelAttribute LandingForm landingForm) throws Exception {
        return landingService.addLanding(landingForm);
    }
    @RequestMapping(value = "/addlandingcategory", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody LandingCategory landingCategory) throws Exception {
        return landingService.addCategory(landingCategory);
    }

    @RequestMapping(value = "/addlandingitem", method = RequestMethod.POST)
    @ResponseBody
    public Response addItem(@RequestBody LandingItem landingItem) throws Exception {
        return landingService.addItem(landingItem);
    }

    @RequestMapping(value = "/deletelanding", method = RequestMethod.GET)
    @ResponseBody
    public Response deleteLanding(@RequestParam(value = "id", defaultValue = "") String id) {
        return landingService.deleteLanding(id);
    }
    
    @RequestMapping(value = "/deletecategory", method = RequestMethod.GET)
    @ResponseBody
    public Response deleteCategory(@RequestParam(value = "id", defaultValue = "") String id) {
        return landingService.deleteCategory(id);
    }

    @RequestMapping(value = "/deleteitem", method = RequestMethod.GET)
    @ResponseBody
    public Response deleteItem(@RequestParam(value = "id", defaultValue = "") String id) {
        return landingService.deleteItem(id);
    }

    @RequestMapping(value = "/changeposition", method = RequestMethod.GET)
    @ResponseBody
    public Response changePosition(@RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "position", defaultValue = "0") int position) {
        LandingCategory landingCategory = landingService.getCategory(id);
        if (landingCategory != null) {
            landingCategory.setPosition(position);
            landingService.addCategory(landingCategory);
            return new Response(true, "Sửa vị trí thành công");
        } else {
            return new Response(false, "Danh mục landing không tồn tại");
        }
    }

    @RequestMapping(value = "/getlanding", method = RequestMethod.GET)
    @ResponseBody
    public Response getLanding(@RequestParam(value = "id", defaultValue = "") String id) {
        Landing landing = landingService.getLanding(id);
        return new Response(true, "ok", landing);
    }
    @RequestMapping(value = "/getcategory", method = RequestMethod.GET)
    @ResponseBody
    public Response getCategory(@RequestParam(value = "id", defaultValue = "") String id) {
        LandingCategory landingCategory = landingService.getCategory(id);
        return new Response(true, "ok", landingCategory);
    }
    
    @RequestMapping(value = "/getitembycate", method = RequestMethod.GET)
    @ResponseBody
    public Response getItemByCategory(@RequestParam(value = "id", defaultValue = "") String id) {
        DataPage<LandingItem> listItems = landingService.getItemByCategory(id, null);
        return new Response(true, "ok", listItems.getData());
    }
    @RequestMapping(value = "/changeitemspecial", method = RequestMethod.GET)
    @ResponseBody
    public Response changeItemSpecial(@RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "special", defaultValue = "false") boolean special) {
        LandingItem landingItem = landingService.getItem(id);
        if (landingItem != null) {
            landingItem.setSpecial(special);
            landingService.addItem(landingItem);
            return new Response(true, "Sửa vị trí thành công");
        } else {
            return new Response(false, "Sản phẩm không tồn tại");
        }
    }

    
    @RequestMapping(value = "/cropimage", method = RequestMethod.POST)
    @ResponseBody
    public Response addItem(@RequestBody ImageForm imageForm) throws Exception {
        ImageUrl crop = imageService.getUrl(imageForm.getImageId()).crop(imageForm.getX(), imageForm.getY(), imageForm.getWidth(), imageForm.getHeight());
        if (crop != null) {
            Response download = imageService.download(crop.getUrl(), ImageType.LANDING, imageForm.getTargetId());
            if (download.isSuccess()) {
                Map<String, String> map = new HashMap<>();
                map.put("imageId", download.getData().toString());
                map.put("imageUrl", imageService.getUrl(download.getData().toString()).getUrl());
                imageService.deleteById(ImageType.LANDING, imageForm.getTargetId(), imageForm.getImageId());

                return new Response(true, "Thành công", map);
            }
        }
        return new Response(false, "Có lỗi xảy ra trong quá trình cắt ảnh");
    }
    
    @RequestMapping(value = "/uploadimage", method = RequestMethod.POST)
    @ResponseBody
    public Response uploadimageItem(@ModelAttribute ImageForm imageForm) throws Exception {
        if (imageForm.getTargetId() != null && !imageForm.getTargetId().equals("")) {
            List<String> images = imageService.get(ImageType.LANDING, imageForm.getTargetId());
            Response upload = imageService.upload(imageForm.getImage(), ImageType.LANDING, imageForm.getTargetId());
            if (upload == null || !upload.isSuccess()) {
                return new Response(false, "Lỗi up ảnh: " + upload.getMessage());
            }
            for (String image : images) {
                imageService.deleteById(ImageType.LANDING, image, imageForm.getTargetId());
            }
            Map<String, String> map = new HashMap<>();
            map.put("imageId", upload.getData().toString());
            map.put("imageUrl", imageService.getUrl(upload.getData().toString()).getUrl());
            return new Response(true, "upload thành công", map);
        }
        return new Response(false, "Sản phẩm hotdeal phải được chọn");
    }
    
    @RequestMapping(value = "/downloadimage", method = RequestMethod.POST)
    @ResponseBody
    public Response downloadImage(@RequestBody ImageForm imageForm) throws Exception {
        if (imageForm.getTargetId() != null && !imageForm.getTargetId().equals("")) {
            List<String> images = imageService.get(ImageType.LANDING, imageForm.getTargetId());
            Response download = imageService.download(imageForm.getImageUrl(), ImageType.LANDING, imageForm.getTargetId());
            if (download == null || !download.isSuccess()) {
                return new Response(false, "Lỗi download ảnh: " + download.getMessage());
            }
            for (String image : images) {
                imageService.deleteById(ImageType.LANDING, image, imageForm.getTargetId());
            }
            Map<String, String> map = new HashMap<>();
            map.put("imageId", download.getData().toString());
            map.put("imageUrl", imageService.getUrl(download.getData().toString()).getUrl());
            return new Response(true, "upload thành công", map);
        }
        return new Response(false, "Sản phẩm hotdeal phải được chọn");
    }
}
