package vn.chodientu.controller.cpservice.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Administrator;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.AdministratorService;

@Controller("cpGlobalAuthService")
@RequestMapping("/cpservice/global/auth")
public class AuthController extends BaseRest {

    @Autowired
    private AdministratorService administratorService;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    public Response signin(@RequestBody Administrator administrator) throws Exception {
        administrator = administratorService.auth(administrator.getEmail());
        if (administrator == null || !administrator.isActive()) {
            return new Response(false, "Bạn không có quyền đăng nhập hệ thống quản trị ChoDienTu.vn");
        }
        viewer.setAdministrator(administrator);
        return new Response(true, "Đăng nhập hệ thống ChoDienTu.vn thành công", "/cp/index.html");
    }

}
