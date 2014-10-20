/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
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
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemDetail;
import vn.chodientu.entity.db.ItemProperty;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.ItemForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;

/**
 *
 * @author thunt
 */
@Controller("cpReviewItemService")
@RequestMapping(value = "/cpservice/reviewitem")
public class ReviewItemController extends BaseRest {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Gson gson;

    /**
     * Sửa thông tin cơ bản
     *
     * @param itemForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@RequestBody ItemForm itemForm) throws Exception {
        String adminId = "test";
        if (viewer != null && viewer.getAdministrator() != null) {
            adminId = viewer.getAdministrator().getId();
        }
        Item item = itemService.get(itemForm.getId());
        if(item == null){
            item = new Item();
        }
        convertToItem(item, itemForm);
        return itemService.edit(item,adminId);
    }

    /**
     * *
     * Duyệt sản phẩm lên sàn
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/submitedit", method = RequestMethod.GET)
    @ResponseBody
    public Response submitEdit(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        String adminId = "test";
        if (viewer != null && viewer.getAdministrator() != null) {
            adminId = viewer.getAdministrator().getId();
        }
        Item item = itemService.get(id);
        return itemService.submitEdit(item, adminId);
    }

    /**
     * Sửa thuộc tính của sản phẩm
     *
     * @param properties
     * @return
     */
    @RequestMapping(value = "/editproperties", method = RequestMethod.POST)
    @ResponseBody
    public Response editProperties(@RequestBody Map<String, String> properties) {
        String itemId = properties.get("id");
        List<ItemProperty> listProperties = gson.fromJson(properties.get("properties"), new TypeToken<List<ItemProperty>>() {
        }.getType());
        try {
            itemService.updateProperties(itemId, listProperties);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
        return new Response(true, "Sửa thuộc tính thành công");
    }

    /**
     * Sửa phí vận chuyển
     *
     * @param itemForm
     * @return
     */
    @RequestMapping(value = "/editfeeship", method = RequestMethod.POST)
    @ResponseBody
    public Response editFeeShip(@RequestBody ItemForm itemForm) {
        String adminId = "test";
        if (viewer != null && viewer.getAdministrator() != null) {
            adminId = viewer.getAdministrator().getId();
        }
        try {
            Item item = itemService.get(itemForm.getId());
            item.setShipmentType(itemForm.getShipmentType());
            item.setShipmentPrice(itemForm.getShipmentPrice());
            Response submitEdit = itemService.edit(item, adminId);
            if (!submitEdit.isSuccess()) {
                return submitEdit;
            }
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
        return new Response(true, "Sửa phí vận chuyển thành công");
    }

    /**
     * Thêm 1 ảnh Item
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/addimage", method = RequestMethod.POST)
    @ResponseBody
    public Response addImage(@ModelAttribute ItemForm form){
        Item item;
        try {
            item = itemService.get(form.getId());
        } catch (Exception ex) {
            return new Response(false, "Sản phẩm không tồn tại");
        }
        List<String> images = item.getImages();
        List<String> itemImages = new ArrayList<String>();
        
        if(images!=null && !images.isEmpty()){
            for (String img : images) {
                itemImages.add(imageService.getUrl(img).thumbnail(100, 100, "inset").getUrl());
            }
        }
        if (images == null) {
            images = new ArrayList<>();
        }
        if (form.getImageUrl() != null && !form.getImageUrl().equals("")) {
            Response<String> resp = imageService.download(form.getImageUrl().trim(), ImageType.ITEM, item.getId());
            if (resp == null || !resp.isSuccess()) {
                return new Response(false, resp.getMessage());
            }
            itemImages.add(imageService.getUrl(resp.getData()).thumbnail(100, 100, "inset").getUrl());
        } else if (form.getImage() != null && form.getImage().getSize() > 0) {
            Response resp = imageService.upload(form.getImage(), ImageType.ITEM, item.getId());
            if (resp == null || !resp.isSuccess()) {
                return new Response(false, resp.getMessage());
            }
            itemImages.add(imageService.getUrl((String)resp.getData()).thumbnail(100, 100, "inset").getUrl());
        }
        item.setImages(itemImages);
        return new Response(true, "Thêm ảnh thành công", item);
    }

    /**
     * Xóa 1 ảnh Item
     *
     * @param id
     * @param imageName
     * @return
     */
    
    @RequestMapping(value = "/delimage", method = RequestMethod.GET)
    @ResponseBody
    public Response delImage(@RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "name", defaultValue = "") String imageName) throws Exception {
        Item item = itemService.get(id);
        if (imageName == null || imageName.trim().equals("")) {
            return new Response(false, "Phải chọn ảnh để xóa");
        }
        imageService.deleteByUrl(ImageType.ITEM, item.getId(), imageName);
        try {
            return new Response(true, "Xóa ảnh thành công", item);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    private void convertToItem(Item item, ItemForm itemForm) {
        item.setId(itemForm.getId());
        item.setName(itemForm.getName());
        item.setCategoryId(itemForm.getCategoryId());
        item.setManufacturerId(itemForm.getManufacturerId());
        item.setModelId(itemForm.getModelId());
        item.setListingType(itemForm.getListingType());
        item.setStartPrice(itemForm.getStartPrice());
        item.setSellPrice(itemForm.getSellPrice());
        item.setQuantity(itemForm.getQuantity());
        item.setCondition(itemForm.getCondition());
        if (itemForm.getCityId() != null && !"".equals(itemForm.getCityId())) {
            item.setCityId(itemForm.getCityId());
        }
        item.setActive(itemForm.isActive());
        item.setWeight(itemForm.getWeight());
        item.setStartTime(itemForm.getStartTime());
        item.setEndTime(itemForm.getStartTime() + itemForm.getEndTime() * 24 * 60 * 60 * 1000l);
        item.setSellerName(itemForm.getSellerName());

    }

    /**
     * lấy detail của sản phẩm
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getdetail", method = RequestMethod.GET)
    @ResponseBody
    public Response getDetail(@RequestParam(value = "id", defaultValue = "") String id) {
        try {
            ItemDetail detail = itemService.getDetail(id);
            return new Response(true, null, detail);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }

    }

    /**
     * Sửa detail của sản phẩm
     *
     * @param itemDetail
     * @return
     */
    @RequestMapping(value = "/editdetail", method = RequestMethod.POST)
    @ResponseBody
    public Response getdetail(@RequestBody ItemDetail itemDetail) {
        try {
            itemService.updateDetail(itemDetail);
            return new Response(true, "Đã sửa thành công");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }

    }
}
