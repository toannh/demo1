/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
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
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.db.ModelLog;
import vn.chodientu.entity.db.ModelProperty;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.ModelForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ModelService;

/**
 *
 * @author thunt
 */
@Controller("cpEditModelService")
@RequestMapping(value = "/cpservice/editmodel")
public class EditModelController extends BaseRest {

    @Autowired
    private ModelService modelService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Gson gson;

    /**
     * Sửa model
     *
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@RequestBody ModelForm form) throws Exception {
        String id = "test";
        if (viewer != null && viewer.getAdministrator() != null) {
            id = viewer.getAdministrator().getId();
        }
        Model model = new Model();
        model.setCategoryId(form.getCategoryId());
        model.setId(form.getId());
        model.setManufacturerId(form.getManufacturerId());
        model.setEbayKeyword(form.getEbayKeyword());
        model.setName(form.getName());
        if (form.getActive() > 0) {
            model.setActive(form.getActive() == ModelForm.TRUE);
        }

        return modelService.edit(model, id);
    }

    /**
     * Thêm mới ảnh model
     *
     * @param modelForm
     * @return
     */
    @RequestMapping(value = "/addimage", method = RequestMethod.POST)
    @ResponseBody
    public Response addImage(@ModelAttribute ModelForm modelForm) {
        List<String> images = new ArrayList<>();
        Response resp;
        if (modelForm.getImage() != null && modelForm.getImage().getSize() > 0) {
            resp = imageService.upload(modelForm.getImage(), ImageType.MODEL, modelForm.getId());
            if (resp == null || !resp.isSuccess()) {
                return new Response(false, resp.getMessage());
            }
            images.add((String) resp.getData());
        } else if (modelForm.getImageUrl() != null && !modelForm.getImageUrl().trim().equals("")) {
            resp = imageService.download(modelForm.getImageUrl(), ImageType.MODEL, modelForm.getId());
            if (resp == null || !resp.isSuccess()) {
                return new Response(false, resp.getMessage());
            }
            images.add((String) resp.getData());
        } else {
            return new Response(false, "Bạn phải chọn ảnh để thêm");
        }
        try {
            String data = (String) resp.getData();
            //String url = imageService.getUrl(data).getUrl();
            ArrayList<String> url = new ArrayList<String>();
            List<String> get = imageService.get(ImageType.MODEL, modelForm.getId());
            for (String imgs : get) {
                url.add(imageService.getUrl(imgs).thumbnail(100, 100, "outbound").getUrl());
            }
            return new Response(true, "Đã thêm ảnh thành công", url);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * Xóa 1 ảnh model
     *
     * @param id
     * @param imageName
     * @return
     */
    @RequestMapping(value = "/delimage", method = RequestMethod.GET)
    @ResponseBody
    public Response delImage(@RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "name", defaultValue = "") String imageName) {
        if (imageName == null || imageName.trim().equals("")) {
            return new Response(false, "Phải chọn ảnh để xóa");
        }
        try {
            return modelService.deleteImage(id, imageName);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }
    /**
     * Báo cáo đã sửa xong
     *
     * @param modelLog
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/successedit", method = RequestMethod.POST)
    @ResponseBody
    public Response successedit(@RequestBody ModelLog modelLog) throws Exception {

        String idUser = viewer.getAdministrator().getId();
        try {
            modelService.submitEdit(modelLog.getModelId(), idUser, modelLog.getMessage());
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }

        return new Response(true, "Báo cáo sửa hoàn tất");
    }

    /**
     * Sửa thông tin thuộc tính model theo id
     *
     * @param propertys
     * @return
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/editproperties", method = RequestMethod.POST)
    @ResponseBody
    public Response editProperties(@RequestBody HashMap<String, String> propertys) throws Exception {
        try {
            String idModel = propertys.get("id");
            List<ModelProperty> propertysx = gson.fromJson(propertys.get("propertys"), new TypeToken<List<ModelProperty>>() {
            }.getType());
            modelService.updateProperties(idModel, propertysx);
            return new Response(true, "Cập nhật thành công thuộc tính");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }

    }

}
