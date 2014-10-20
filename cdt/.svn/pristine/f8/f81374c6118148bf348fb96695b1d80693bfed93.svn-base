/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.CategoryManufacturer;
import vn.chodientu.entity.form.ManufacturerForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ManufacturerService;

/**
 *
 * @author thunt
 */
@Controller("cpManufacturerService")
@RequestMapping(value = "/cpservice/manufacturer")
public class ManufacturerController extends BaseRest {

    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private ImageService imageService;

    /**
     * service xóa thương hiệu
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response delete(@RequestParam String id) {
        try {
            manufacturerService.removeManufacturer(id);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
        return new Response(true);
    }

    /**
     * service thêm mới thương hiệu
     *
     * @param manufacturerForm
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@ModelAttribute ManufacturerForm manufacturerForm) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(manufacturerForm.getName());
        manufacturer.setDescription(manufacturerForm.getDescription());
        manufacturer.setActive(manufacturerForm.isActive());
        manufacturer.setGlobal(manufacturerForm.isGlobal());

        Response resp = manufacturerService.addManufacturer(manufacturer);

        if (resp.isSuccess() && manufacturerForm.getImage().getSize() > 0) {
            imageService.upload(manufacturerForm.getImage(), ImageType.MANUFACTURER, manufacturer.getId());
        }

        return resp;
    }

    /**
     * Sửa thương hiệu
     *
     * @param manufacturerForm
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@ModelAttribute ManufacturerForm manufacturerForm) {
        Manufacturer manufacturer = new Manufacturer();
        if (manufacturerForm.getImage().getSize() > 0) {
            imageService.delete(ImageType.MANUFACTURER, manufacturerForm.getId());
            imageService.upload(manufacturerForm.getImage(), ImageType.MANUFACTURER, manufacturerForm.getId());
        }
        manufacturer.setId(manufacturerForm.getId());
        manufacturer.setName(manufacturerForm.getName());
        manufacturer.setDescription(manufacturerForm.getDescription());
        manufacturer.setActive(manufacturerForm.isActive());
        manufacturer.setGlobal(manufacturerForm.isGlobal());
        try {
            return manufacturerService.editManufacturer(manufacturer);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * active thương hiệu
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    @ResponseBody
    public Response active(@RequestParam String id) {
        try {
            Manufacturer manufacturer = manufacturerService.getManufacturer(id);
            manufacturer.setActive(!manufacturer.isActive());

            return manufacturerService.editManufacturer(manufacturer);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * Xóa 1 map thương hiệu và danh mục
     *
     * @param manuId
     * @param cateId
     * @return
     */
    @RequestMapping(value = "/delmap", method = RequestMethod.GET)
    @ResponseBody
    public Response delMap(@RequestParam(value = "manuId", defaultValue = "") String manuId,
            @RequestParam(value = "cateId", defaultValue = "") String cateId) {
        CategoryManufacturer map = new CategoryManufacturer();
        map.setCategoryId(cateId);
        map.setManufacturerId(manuId);
        try {
            return manufacturerService.removeMap(map);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * map mới 1 thương hiệu và danh mục
     *
     * @param manuId
     * @param cateId
     * @return
     */
    @RequestMapping(value = "/addmap", method = RequestMethod.GET)
    @ResponseBody
    public Response addMap(@RequestParam(value = "manuId", defaultValue = "") String manuId,
            @RequestParam(value = "cateId", defaultValue = "") String cateId) {
        CategoryManufacturer map = new CategoryManufacturer();
        map.setCategoryId(cateId);
        map.setManufacturerId(manuId);
        try {
            return manufacturerService.addMap(map);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * Index toàn bộ thương hiệu
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public Response index() throws Exception {
        manufacturerService.index();
        return new Response(true, "Đang xử lý index");
    }

    /**
     * Xóa toàn bộ index
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unindex")
    @ResponseBody
    public Response unindex() throws Exception {
        manufacturerService.unindex();
        return new Response(true, "Xóa index thành công");
    }
}
