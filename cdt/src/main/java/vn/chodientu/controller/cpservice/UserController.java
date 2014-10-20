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
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.form.EditUserForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopCategoryService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserService;

/**
 *
 * @author thunt
 */
@Controller("cpUserService")
@RequestMapping(value = "/cpservice/user")
public class UserController extends BaseRest {

    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    @RequestMapping(value = "/changeemailverified", method = RequestMethod.GET)
    @ResponseBody
    public Response changeEmailVerified(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        Response<User> errors = userService.getById(id);
        if (!errors.isSuccess()) {
            return new Response(false, "Không tìm thấy thành viên có mã" + id);
        }
        User data = errors.getData();
        return userService.changeEmailVerified(id, !data.isEmailVerified());
    }

    @RequestMapping(value = "/changephoneverified", method = RequestMethod.GET)
    @ResponseBody
    public Response changePhoneVerified(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        Response<User> user = userService.getById(id);
        if (!user.isSuccess()) {
            return new Response(false, "Không tìm thấy thành viên có mã" + id);
        }
        User data = user.getData();
        return userService.changePhoneVerified(id, !data.isPhoneVerified());
    }

    @RequestMapping(value = "/changeactive", method = RequestMethod.GET)
    @ResponseBody
    public Response changeActive(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        Response<User> user = userService.getById(id);
        if (!user.isSuccess()) {
            return new Response(false, "Không tìm thấy thành viên có mã" + id);
        }
        User data = user.getData();
        return userService.changeActive(id, !data.isActive());
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        Response<User> user = userService.getById(id);
        if (!user.isSuccess()) {
            return new Response(false, "Không tìm thấy thành viên có mã" + id);
        }
        User data = user.getData();
        return new Response(true, "Thông tin tài khoản", data);
    }

    @RequestMapping(value = "/getbyids", method = RequestMethod.POST)
    @ResponseBody
    public Response getByIds(@RequestBody List<String> userIds) {
        try {
            List<User> userByIds = userService.getUserByIds(userIds);
            return new Response(true, "", userByIds);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@RequestBody EditUserForm userForm) {
        userForm.setPhone(userForm.getPhone().substring(1));
        try {
            return userService.edit(userForm);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    /**
     * Lấy người bán và shop kiểm tra cho đăng bán nhanh
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/checkseller", method = RequestMethod.GET)
    @ResponseBody
    public Response checkSeller(@RequestParam(value = "username", defaultValue = "") String username) {
        username = username.trim();
        Response<User> user = userService.getByUsername(username);
        if (!user.isSuccess()) {
            user = userService.getByEmail(username);
            if (!user.isSuccess()) {
                return new Response(false, "User không tồn tại");
            }
        }
        User data = user.getData();
        Shop shop = shopService.getShop(data.getId());
        Seller seller = sellerService.getById(data.getId());
        Map map = new HashMap<String, Object>();

        map.put("user", data);
        map.put("shop", shop);
        map.put("seller", seller);
        map.put("scates", shopCategoryService.getChilds(null, data.getId()));

        return new Response(true, "Thông tin tài khoản", map);
    }
}
