package vn.chodientu.controller.service;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseWeb;
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.ShopAvatarImageForm;
import vn.chodientu.entity.form.UserAvatarForm;
import vn.chodientu.entity.form.UserChangePassForm;
import vn.chodientu.entity.form.UserChangeProfileForm;
import vn.chodientu.entity.input.UserSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CashService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.SellerFollowService;
import vn.chodientu.service.ShopNewsCategoryService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserService;

@Controller("serviceUser")
@RequestMapping("/user")
public class UserController extends BaseWeb {

    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopNewsCategoryService shopNewsCategoryService;
    @Autowired
    private CashService cashService;
    @Autowired
    private SellerFollowService sellerFollowService;
    @Autowired
    private ImageService imageService;

    @ResponseBody
    @RequestMapping("/auth")
    public Response auth() {
        if (viewer.getUser() == null) {
            return new Response(false);
        } else {
            try {
                Cash cash = cashService.getCash(viewer.getUser().getId());
                viewer.getUser().setBalance(cash.getBalance());
                Shop shop = shopService.getShop(viewer.getUser().getId());
                viewer.getUser().setShop(shop);
            } catch (Exception e) {
            }
            return new Response(true, null, viewer.getUser());
        }
    }

    @RequestMapping(value = "/changepass", method = RequestMethod.POST)
    @ResponseBody
    public Response changePassword(@RequestBody UserChangePassForm changePassForm) {
        return userService.changePassword(changePassForm);
    }

    @RequestMapping(value = "/updateprofile", method = RequestMethod.POST)
    @ResponseBody
    public Response updateProfile(@RequestBody UserChangeProfileForm profileForm) {
        try {
            return userService.updateProfile(profileForm);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/updateavatar", method = RequestMethod.POST)
    @ResponseBody
    public Response updateAvatar(@ModelAttribute UserAvatarForm avatarForm) throws IOException {
        return userService.uploadAvatar(avatarForm);
    }

    @RequestMapping(value = "/uploadShopLogo", method = RequestMethod.POST)
    @ResponseBody
    public Response uploadShopLogo(@ModelAttribute ShopAvatarImageForm shopImageForm) throws Exception {
        shopImageForm.setShopId(viewer.getUser().getId()); // user id is shop id too
        return shopService.uploadShopLogo(shopImageForm);
    }

    @RequestMapping(value = "/cropShopLogo", method = RequestMethod.POST)
    @ResponseBody
    public Response cropShopLogo(@ModelAttribute ShopAvatarImageForm shopImageForm, @RequestParam(value = "width") float width, @RequestParam(value = "height") float height, @RequestParam(value = "x1") float x1, @RequestParam(value = "y1") float y1) throws Exception {
        shopImageForm.setHeight(height);
        shopImageForm.setWidth(width);
        shopImageForm.setX1(x1);
        shopImageForm.setY1(y1);
        shopImageForm.setShopId(viewer.getUser().getId());
        return shopService.cropShopLogo(shopImageForm);
    }

    @RequestMapping(value = "/updatephone", method = RequestMethod.GET)
    @ResponseBody
    public Response updatePhone(@RequestParam String userId, @RequestParam String phone) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập trước!");
        }
        Response resp = userService.updatePhone(userId, phone);
        if (resp.isSuccess()) {
            viewer.setUser((User) resp.getData());
        }
        return resp;
    }

    @RequestMapping(value = "/checkphoneverified", method = RequestMethod.GET)
    @ResponseBody
    public Response checkPhoneVerified() {
        try {
            User user = userService.get(viewer.getUser().getId());
            viewer.setUser(user);
            return new Response(user.isPhoneVerified(), "Đã kích hoạt số điện thoại");
        } catch (Exception e) {
            return new Response(false, "Chưa kích hoạt số điện thoại");
        }
    }

    @RequestMapping(value = "/findbyemail", method = RequestMethod.GET)
    @ResponseBody
    public Response findByEmail(@RequestParam(value = "email", defaultValue = "") String email) throws Exception {
        UserSearch search = new UserSearch();
        search.setPageIndex(0);
        search.setPageSize(20);
        if (!email.equals("")) {
            search.setEmail(email);
        }
        DataPage<User> pageUser = userService.search(search);
        return new Response(true, "Danh sách email", pageUser.getData());
    }

    /**
     * theo dõi người dùng
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/follow", method = RequestMethod.GET)
    @ResponseBody
    public Response follow(@RequestParam(value = "id", defaultValue = "") String id, HttpServletRequest request) {
        try {
            long count = sellerFollowService.action(id, request);
            return new Response(true, "ok", count);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * *
     * Lấy 1 user theo Id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam String id) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập trước!");
        }
        User user = (User) userService.getById(id).getData();
        List<String> avatar = imageService.get(ImageType.AVATAR, user.getId());
        if (avatar != null && !avatar.isEmpty()) {
            user.setAvatar(imageService.getUrl(avatar.get(0)).getUrl());
        }
        return new Response(true, null, user);
    }

    /**
     * Upload image avatar
     *
     * @param userAvatarForm
     * @return
     */
    @RequestMapping(value = "/uploadimageavatar", method = RequestMethod.POST)
    @ResponseBody
    public Response uploadImageAvatar(@ModelAttribute UserAvatarForm userAvatarForm) {
        return userService.uploadImageAvatar(userAvatarForm);
    }

    /**
     * download image avatar from url
     *
     * @param url
     * @param userId
     * @return
     */
    @RequestMapping(value = "/downloadimageavatar", method = RequestMethod.GET)
    @ResponseBody
    public Response downloadImageAvatar(@RequestParam String url, @RequestParam String userId) {
        return userService.downloadImageAvatar(url, userId);
    }

    /**
     * Đổi Avatar crop
     *
     * @param userId
     * @param width
     * @param height
     * @param x
     * @param y
     * @return
     */
    @RequestMapping(value = "/changeavatar", method = RequestMethod.GET)
    @ResponseBody
    public Response changeAvatar(@RequestParam String userId, @RequestParam int width, @RequestParam int height, @RequestParam int x, @RequestParam int y) {
        return userService.changeAvatarCrop(userId, width, height, x, y);
    }

}
