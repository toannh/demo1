/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemProperty;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;

/**
 *
 * @author thunt
 */
@Controller("cpGlobalItemService")
@RequestMapping("/cpservice/global/item")
public class ItemController extends BaseRest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;

    /**
     * Lấy thông tin chi tiết sản phẩm theo id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getitem", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam(value = "id", defaultValue = "") String id) {
        try { 
            Item item = itemService.get(id);
            List<String> images = new ArrayList<>();
            if (item != null && item.getImages() != null && !item.getImages().isEmpty()) {
                for (String img : item.getImages()) {
                    images.add(imageService.getUrl(img).thumbnail(200, 200, "outbound").getUrl());
                }
                item.setImages(images);
            }
            return new Response(true, "Thông tin chi tiết sản phẩm", item);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * Lấy thuộc tính của sản phẩm
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getproperties", method = RequestMethod.GET)
    @ResponseBody
    public Response getProperties(@RequestParam(value = "id") String id) {
        Item item;
        try {
            item = itemService.get(id);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }

        HashMap<String, Object> data = new HashMap<>();
        List<ItemProperty> properties = itemService.getProperties(id);
        data.put("item", item);
        data.put("properties", properties);
        data.put("categoryProperties", categoryService.getProperties(item.getCategoryId()));
        data.put("categoryPropertyValues", categoryService.getPropertyValuesWithCategoryId(item.getCategoryId()));

        return new Response(true, "thuộc tính", data);
    }

}
