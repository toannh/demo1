/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

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
import vn.chodientu.entity.db.Administrator;
import vn.chodientu.entity.db.AdministratorRole;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.form.AdministratorForm;
import vn.chodientu.service.AdministratorService;

/**
 *
 * @author thunt
 */
@Controller("cpAdministratorService")
@RequestMapping(value = "/cpservice/administrator")
public class AdministratorController extends BaseRest {

    @Autowired
    private AdministratorService administratorService;

    /**
     * service lấy tiết
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam String id) {
        try {
            Administrator administrator = administratorService.getAdministrator(id);
            return new Response(true, "ok", administrator);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * service xóa quản trị viên
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response delete(@RequestParam String id) {
        try {
            administratorService.remove(id);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
        return new Response(true);
    }

    /**
     * service thêm mới quản trị viên
     *
     * @param administratorForm
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@ModelAttribute AdministratorForm administratorForm) {
        Administrator administrator = new Administrator();

        administrator.setEmail(administratorForm.getEmail());
        administrator.setDescription(administratorForm.getDescription());
        administrator.setActive(administratorForm.isActive());

        return administratorService.add(administrator);
    }

    /**
     * Sửa quản trị viên
     *
     * @param administratorForm
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@ModelAttribute AdministratorForm administratorForm) {
        Administrator administrator = new Administrator();
        administrator.setId(administratorForm.getId());
        administrator.setEmail(administratorForm.getEmail());
        administrator.setDescription(administratorForm.getDescription());
        administrator.setActive(administratorForm.isActive());
        try {
            return administratorService.edit(administrator);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * active quản trị viên
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    @ResponseBody
    public Response active(@RequestParam String id) {
        try {
            Administrator administrator = administratorService.getAdministrator(id);
            administrator.setActive(!administrator.isActive());

            return administratorService.edit(administrator);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * Lấy danh sách quyền
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getroles", method = RequestMethod.GET)
    @ResponseBody
    public Response getRoles(@RequestParam String id) {
        try {
            return new Response(true, "ok", administratorService.getRoles(id));
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * Thêm/ sửa quyền quản trị
     *
     * @param roles
     * @return
     */
    @RequestMapping(value = "/saveroles", method = RequestMethod.POST)
    @ResponseBody
    public Response saveRoles(@RequestBody List<AdministratorRole> roles) {
        try {
            administratorService.saveRoles(roles);
            return new Response(true);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

}
