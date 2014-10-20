package vn.chodientu.controller.cpservice.global;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ManufacturerService;

/**
 * @since May 10, 2014
 * @author Phu
 */
@Controller("cpGlobalManufacturerService")
@RequestMapping("/cpservice/global/manufacturer")
public class ManufacturerController extends BaseRest {

    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private ImageService imageService;

    /**
     * service lấy chi tiết thương hiệu
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam String id) {
        try {
            Manufacturer manufacturer = manufacturerService.getManufacturer(id);
            List<String> images = imageService.get(ImageType.MANUFACTURER, id);
            if (images.size() > 0) {
                manufacturer.setImageUrl(imageService.getUrl(images.get(0)).thumbnail(100, 100, "outbound").getUrl());
            }
            return new Response(true, "ok", manufacturer);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * Lấy các danh mục đã được map theo thương hiệu
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getmaps", method = RequestMethod.GET)
    @ResponseBody
    public Response maps(@RequestParam String id) throws Exception {
        List<Category> listCategoryByManufacturer = manufacturerService.listCategoryByManufacturer(id);
        return new Response(true, "ok", listCategoryByManufacturer);
    }

}
