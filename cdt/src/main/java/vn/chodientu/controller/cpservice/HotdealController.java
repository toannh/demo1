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
import vn.chodientu.entity.db.HotdealCategory;
import vn.chodientu.entity.db.HotdealItem;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.FeaturedCategoryImageForm;
import vn.chodientu.entity.form.ImageForm;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.HotdealService;
import vn.chodientu.service.ImageService;

/**
 *
 * @author Admin
 */
@Controller("cpHotdealService")
@RequestMapping(value = "/cpservice/hotdeal")
public class HotdealController extends BaseRest {

    @Autowired
    private HotdealService hotdealService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/addhotdealcategory", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody HotdealCategory hotdealCategory) throws Exception {
        return hotdealService.addCategory(hotdealCategory);
    }

    @RequestMapping(value = "/addhotdealitem", method = RequestMethod.POST)
    @ResponseBody
    public Response addItem(@RequestBody HotdealItem hotdealItem) throws Exception {
        return hotdealService.addItem(hotdealItem);
    }

    @RequestMapping(value = "/cropimage", method = RequestMethod.POST)
    @ResponseBody
    public Response addItem(@RequestBody ImageForm imageForm) throws Exception {
        ImageUrl crop = imageService.getUrl(imageForm.getImageId()).crop(imageForm.getX(), imageForm.getY(), imageForm.getWidth(), imageForm.getHeight());
        if (crop != null) {
            Response download = imageService.download(crop.getUrl(), ImageType.HOTDEAL, imageForm.getTargetId());
            if (download.isSuccess()) {
                Map<String, String> map = new HashMap<>();
                map.put("imageId", download.getData().toString());
                map.put("imageUrl", imageService.getUrl(download.getData().toString()).getUrl());
                imageService.deleteById(ImageType.HOTDEAL, imageForm.getTargetId(), imageForm.getImageId());

                return new Response(true, "Thành công", map);
            }
        }
        return new Response(false, "Có lỗi xảy ra trong quá trình cắt ảnh");
    }

    @RequestMapping(value = "/uploadimage", method = RequestMethod.POST)
    @ResponseBody
    public Response uploadimageItem(@ModelAttribute ImageForm imageForm) throws Exception {
        if (imageForm.getTargetId() != null && !imageForm.getTargetId().equals("")) {
            List<String> images = imageService.get(ImageType.HOTDEAL, imageForm.getTargetId());
            Response upload = imageService.upload(imageForm.getImage(), ImageType.HOTDEAL, imageForm.getTargetId());
            if (upload == null || !upload.isSuccess()) {
                return new Response(false, "Lỗi up ảnh: " + upload.getMessage());
            }
            for (String image : images) {
                imageService.deleteById(ImageType.HOTDEAL, image, imageForm.getTargetId());
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
            List<String> images = imageService.get(ImageType.HOTDEAL, imageForm.getTargetId());
            Response download = imageService.download(imageForm.getImageUrl(), ImageType.HOTDEAL, imageForm.getTargetId());
            if (download == null || !download.isSuccess()) {
                return new Response(false, "Lỗi download ảnh: " + download.getMessage());
            }
            for (String image : images) {
                imageService.deleteById(ImageType.HOTDEAL, image, imageForm.getTargetId());
            }
            Map<String, String> map = new HashMap<>();
            map.put("imageId", download.getData().toString());
            map.put("imageUrl", imageService.getUrl(download.getData().toString()).getUrl());
            return new Response(true, "upload thành công", map);
        }
        return new Response(false, "Sản phẩm hotdeal phải được chọn");
    }

    @RequestMapping(value = "/deletecategory", method = RequestMethod.GET)
    @ResponseBody
    public Response deleteCategory(@RequestParam(value = "id", defaultValue = "") String id) {
        return hotdealService.deleteCategory(id);
    }

    @RequestMapping(value = "/deleteitem", method = RequestMethod.GET)
    @ResponseBody
    public Response deleteItem(@RequestParam(value = "id", defaultValue = "") String id) {
        return hotdealService.deleteItem(id);
    }

    @RequestMapping(value = "/changestatus", method = RequestMethod.GET)
    @ResponseBody
    public Response changeStatus(@RequestParam(value = "id", defaultValue = "") String id) {
        HotdealCategory hotdealCategory = hotdealService.getCategory(id);
        if (hotdealCategory != null) {
            hotdealCategory.setActive(!hotdealCategory.isActive());
            hotdealService.addCategory(hotdealCategory);
            return new Response(true, "Thành công", hotdealCategory);
        } else {
            return new Response(false, "Danh mục hotdeal không tồn tại");
        }
    }

    @RequestMapping(value = "/changespecial", method = RequestMethod.GET)
    @ResponseBody
    public Response changeSpecial(@RequestParam(value = "id", defaultValue = "") String id) {
        HotdealCategory hotdealCategory = hotdealService.getCategory(id);
        if (hotdealCategory != null) {
            hotdealCategory.setSpecial(!hotdealCategory.isSpecial());
            hotdealService.addCategory(hotdealCategory);
            return new Response(true, "Thành công", hotdealCategory);
        } else {
            return new Response(false, "Danh mục hotdeal không tồn tại");
        }
    }

    @RequestMapping(value = "/changeposition", method = RequestMethod.GET)
    @ResponseBody
    public Response changePosition(@RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "position", defaultValue = "0") int position) {
        HotdealCategory hotdealCategory = hotdealService.getCategory(id);
        if (hotdealCategory != null) {
            hotdealCategory.setPosition(position);
            hotdealService.addCategory(hotdealCategory);
            return new Response(true, "Sửa vị trí thành công");
        } else {
            return new Response(false, "Danh mục hotdeal không tồn tại");
        }
    }

    @RequestMapping(value = "/getcategory", method = RequestMethod.GET)
    @ResponseBody
    public Response getCategory(@RequestParam(value = "id", defaultValue = "") String id) {
        HotdealCategory hotdealCategory = hotdealService.getCategory(id);
        return new Response(true, "ok", hotdealCategory);
    }

    @RequestMapping(value = "/getitembycate", method = RequestMethod.GET)
    @ResponseBody
    public Response getItemByCategory(@RequestParam(value = "id", defaultValue = "") String id) {
        DataPage<HotdealItem> listItems = hotdealService.getByCategory(id, null);
        return new Response(true, "ok", listItems.getData());
    }

    @RequestMapping(value = "/getitemhomebycate", method = RequestMethod.GET)
    @ResponseBody
    public Response getItemHomeByCategory(@RequestParam(value = "id", defaultValue = "") String id) {
        List<HotdealItem> listItems = hotdealService.getHomeByCategory(id);
        return new Response(true, "ok", listItems);
    }

    @RequestMapping(value = "/changeitemspecial", method = RequestMethod.GET)
    @ResponseBody
    public Response changeItemSpecial(@RequestParam(value = "id", defaultValue = "") String id) {
        return hotdealService.changeItemSpecial(id);
    }

}
