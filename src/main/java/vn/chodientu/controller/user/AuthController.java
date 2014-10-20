package vn.chodientu.controller.user;

import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang.RandomStringUtils;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Permission;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.Gender;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.SellerHistoryType;
import vn.chodientu.entity.form.SignupForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CashService;
import vn.chodientu.service.EmailService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.LocationService;
import vn.chodientu.service.SellerHistoryService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserService;

/**
 * @since May 15, 2014
 * @author Phu
 */
@Controller("userAuth")
@RequestMapping("/user")
public class AuthController extends BaseUser {

    @Autowired
    private UserService userService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private SocialAuthManager socialAuthManager;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CashService cashService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SellerHistoryService sellerHistoryService;

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signin(ModelMap model, @RequestParam(value = "ref", required = false) String ref) {
        if (viewer.getUser() == null) {
            model.put("ref", ref);
            return "user.signin";
        }
        return "redirect:" + baseUrl + "/index.html";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signin(ModelMap model,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(value = "ref", required = false) String ref,
            @RequestParam(value = "checkCookie", defaultValue = "off") String checkCookie,
            HttpServletResponse response
    ) throws Exception {
        model.put("username", username);
        model.put("password", password);
        model.put("ref", ref);
        model.put("checkCookie", checkCookie);
        try {
            Response signin = userService.signin(username, password);
            if (!signin.isSuccess()) {
                model.put("error", signin.getMessage());
            } else {
                User user = (User) signin.getData();
                List<String> getImg = null;
                getImg = imageService.get(ImageType.AVATAR, user.getId());
                if (getImg != null && !getImg.isEmpty()) {
                    user.setAvatar(imageService.getUrl(getImg.get(0)).thumbnail(42, 42, "outbound").getUrl());
                }
                if (checkCookie != null && !checkCookie.equals("off")) {
                    Cookie cookie = userService.setRemember(user.getId());
                    response.addCookie(cookie);
                }
                Cash cash = cashService.getCash(user.getId());
                Shop shop = shopService.getShop(user.getId());
                viewer.setUser(user);
                if (ref != null) {
                    return "redirect:" + ref;
                }

                Cookie c = new Cookie("popIndex", String.valueOf(0));
                c.setPath("/");
                c.setMaxAge(24 * 3600);
                response.addCookie(c);

                model.put("cash", cash);
                model.put("shop", shop);
                model.addAttribute("type", "success");
                model.addAttribute("title", "Đăng nhập ChợĐiệnTử thành công!");
                model.addAttribute("message", "Đăng nhập thành công, về <a href='" + baseUrl + "'>trang chủ</a> để tham gia mua hàng trên ChợĐiệnTử ngay bây giờ!");
                model.put("clientScript", "textUtils.redirect('/index.html', 5000);");
                return "user.msg";
            }
        } catch (Exception e) {
            model.addAttribute("type", "fail");
            model.addAttribute("title", "Đăng nhập ChợĐiệnTử thất bại!");
            model.addAttribute("message", e.getMessage() + ", click <a href='" + baseUrl + "/user/requestverify.html'>vào đây</a> để hệ thống gửi lại email kích hoạt!");
            return "user.msg";
        }
        return "user.signin";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(ModelMap model) {
        model.addAttribute("signupForm", new SignupForm());
        model.addAttribute("cities", locationService.getCities());
        model.addAttribute("districts", locationService.getDistricts());
        model.addAttribute("clientScript", "auth.init();");
        return "user.signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Valid SignupForm signupForm,
            BindingResult result,
            ModelMap model) throws Exception {
        userService.signup(signupForm, result);
        if (!result.hasErrors()) {
            model.addAttribute("type", "success");
            model.addAttribute("title", "Rất cảm ơn bạn đã đăng ký trên ChợĐiệnTử!");
            model.addAttribute("message", "Email xác nhận Tài khoản: " + signupForm.getUsername() + " đã được gửi đến địa chỉ email của bạn. <br/>Nếu không thấy email trong hộp thư đến (Inbox) vui lòng kiểm tra hộp thư Spam hoặc Junk Folder.");
            model.put("clientScript", "textUtils.redirect('/index.html', 5000);");
            return "user.msg";
        }
        model.addAttribute("cities", locationService.getCities());
        model.addAttribute("districts", locationService.getDistricts());
        model.addAttribute("signupForm", signupForm);
        model.put("clientScript", "auth.init();");
        return "user.signup";
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public String verify(ModelMap model, @RequestParam(value = "code") String code) {
        try {
            userService.verify(code);
            model.addAttribute("type", "success");
            model.addAttribute("title", "Kích hoạt tài khoản thành công!");
            model.addAttribute("message", "Tài khoản của bạn đã kích hoạt email thành công, ngay bây giờ bạn có thể <a href=" + baseUrl + "/user/signin.html" + ">đăng nhập để sử dụng ChợĐiệnTử.</a>");
            model.put("clientScript", "textUtils.redirect('/user/signin.html', 5000);");
        } catch (Exception ex) {
            model.addAttribute("type", "fail");
            model.addAttribute("title", "Kích hoạt tài khoản thất bại!");
            model.addAttribute("message", ex.getMessage() + " Bạn có thể <a href=" + baseUrl + "/user/requestverify.html" + ">yêu cầu gửi lại mail kích hoạt</a>.");
        }
        return "user.msg";
    }

    @RequestMapping(value = "/requestverify", method = RequestMethod.GET)
    public String requestVerify(ModelMap model) {
        model.addAttribute("clientScript", "auth.reloadCaptcha();");
        return "user.requestverify";
    }

    @RequestMapping(value = "/requestverify", method = RequestMethod.POST)
    public String requestVerify(ModelMap model,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("captcha") String captcha) throws Exception {
        model.put("email", email);
        try {
            userService.requestVerify(email, password, captcha);
            model.addAttribute("type", "success");
            model.addAttribute("title", "Yêu cầu gửi lại mail kích hoạt thành công!");
            model.addAttribute("message", "Mail kích hoạt tài khoản đã được gửi tới địa chỉ " + email + " của bạn, hãy kiểm tra hòm mail và làm theo hướng dẫn.");
            return "user.msg";
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
            model.addAttribute("clientScript", "auth.reloadCaptcha();");
            return "user.requestverify";
        }
    }

    @RequestMapping(value = "/sso", method = RequestMethod.GET)
    public String sso(@RequestParam("provider") String provider, ModelMap model) {
        try {
            return "redirect:" + socialAuthManager.getAuthenticationUrl(provider, baseUrl + "/user/ssocallback.html", Permission.DEFAULT);
        } catch (Exception ex) {
            model.addAttribute("type", "failed");
            model.addAttribute("title", "Có lỗi trong quá trình đăng nhập!");
            model.addAttribute("message", ex.getMessage());
            return "user.msg";
        }
    }

    @RequestMapping("/ssocallback")
    public String ssoCallback(ModelMap model,
            HttpServletRequest request,
            @RequestParam(value = "error", required = false) String error, HttpServletResponse response) {
        SignupForm signupForm = new SignupForm();
        if (error == null) {
            try {
                AuthProvider provider = socialAuthManager.connect(SocialAuthUtil.getRequestParametersMap(request));
                String email = provider.getUserProfile().getEmail();
                User user = userService.ssoSignin(email);
                if (user != null) {
                    if (!user.isActive()) {
                        model.addAttribute("type", "failed");
                        model.addAttribute("title", "Tài khoản đang bị khóa!");
                        model.addAttribute("message", "Tài khoản " + user.getEmail() + " của bạn đang bị khóa, hãy liên hệ với ban quản trị để biết thêm chi tiết.");
                    } else if (!user.isEmailVerified()) {
                        model.addAttribute("type", "failed");
                        model.addAttribute("title", "Tài khoản chưa xác nhận!");
                        model.addAttribute("message", "Tài khoản " + user.getEmail() + " của bạn chưa được xác nhận email, xin hãy kiểm tra lại hoàn mail hoặc <a href='" + baseUrl + "/user/requestverify.html'>yêu cầu gửi lại mail kích hoạt</a>.");
                    } else {
                        Cookie c = new Cookie("popIndex", String.valueOf(0));
                        c.setPath("/");
                        c.setMaxAge(24 * 3600);
                        response.addCookie(c);
                        viewer.setUser(user);
                        model.addAttribute("type", "success");
                        model.addAttribute("title", "Đăng nhập vào ChợĐiệnTử thành công!");
                        model.addAttribute("message", "Đăng nhập thành công , về <a href='" + baseUrl + "'>trang chủ</a> để tham gia mua hàng trên ChợĐiệnTử ngay bây giờ!");
                        model.put("clientScript", "textUtils.redirect('/index.html', 5000);");
                    }
                } else {

                    Cookie c = new Cookie("popIndex", String.valueOf(0));
                    c.setPath("/");
                    c.setMaxAge(24 * 3600);
                    response.addCookie(c);

                    signupForm.setEmail(email);
                    signupForm.setName(provider.getUserProfile().getFullName());
                    signupForm.setAddress(provider.getUserProfile().getLocation());
                    signupForm.setPassword(RandomStringUtils.randomAlphanumeric(8));
                    signupForm.setConfirm(true);
                    signupForm.setGender(provider.getUserProfile().getGender().equals("male") ? Gender.MALE : provider.getUserProfile().getGender().equals("female") ? Gender.FEMALE : Gender.MALE);

                    Response signupSso = userService.signupSso(signupForm);
                    if (signupSso.isSuccess()) {
                        viewer.setUser((User) signupSso.getData());
                        imageService.addTmp(viewer.getUser().getId(), ImageType.AVATAR, provider.getUserProfile().getProfileImageURL());
                        viewer.getUser().setAvatar(provider.getUserProfile().getProfileImageURL());
                        model.addAttribute("type", "success");
                        model.addAttribute("title", "Đăng nhập vào ChợĐiệnTử thành công!");
                        model.addAttribute("message", "Đăng nhập thành công , về <a href='" + baseUrl + "'>trang chủ</a> để tham gia mua hàng trên ChợĐiệnTử ngay bây giờ!");
                        model.put("clientScript", "textUtils.redirect('/index.html', 5000);");
                    }
                    for (String id : socialAuthManager.getConnectedProvidersIds()) {
                        socialAuthManager.disconnectProvider(id);
                    }
                }
            } catch (Exception ex) {
                model.addAttribute("type", "failed");
                model.addAttribute("title", "Có lỗi trong quá trình đăng nhập!");
                model.addAttribute("message", ex.getMessage());
            }
        } else {
            model.addAttribute("type", "failed");
            model.addAttribute("title", "Bạn đã từ chối đăng nhập bằng tài khoản xã hội!");
            model.addAttribute("message", "Click vào <a href='" + baseUrl + "/user/signin.html" + "'>đây</a> để thử đăng nhập lại.");
        }
        return "user.msg";
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public String forgotPass(ModelMap model) {
        model.addAttribute("clientScript", "auth.reloadCaptcha();");
        return "user.forgot";
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public String forgotPass(ModelMap model, @RequestParam(value = "email") String email,
            @RequestParam(value = "captcha") String captcha) throws Exception {
        try {
            userService.forgotPass(email, captcha);
            model.addAttribute("type", "success");
            model.addAttribute("title", "Yêu cầu lấy lại mật khẩu thành công!");
            model.addAttribute("message", "Một email hướng dẫn lấy lại mật khẩu đã được gửi tới địa chỉ " + email + " của bạn, hãy kiểm tra hòm mail và làm theo hướng dẫn.");
            return "user.msg";
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
            model.addAttribute("clientScript", "auth.reloadCaptcha();");
            return "user.forgot";
        }
    }

    @RequestMapping(value = "/resetpass")
    public String resetPass(ModelMap model, @RequestParam(value = "code", defaultValue = "") String code) {
        try {
            userService.resetPass(code);
            model.addAttribute("type", "success");
            model.addAttribute("title", "Lấy lại mật khẩu thành công!");
            model.addAttribute("message", "Mật khẩu mới đã được gửi đến địa chỉ email của bạn, chúc bạn một ngày vui vẻ!");
            return "user.msg";
        } catch (Exception ex) {
            model.addAttribute("type", "failed");
            model.addAttribute("title", "Lấy lại mật khẩu không thành công!");
            model.addAttribute("message", ex.getMessage());
            return "user.msg";
        }
    }

    @RequestMapping(value = "/signout")
    public String signout(ModelMap model, HttpServletResponse response) {
        try {
            userService.sigoutUpdate(viewer.getUser());
            sellerHistoryService.create(SellerHistoryType.USER, viewer.getUser().getId(), true, 2, null);
            viewer.setUser(null);
            Cookie ck = new Cookie("remember", null);
            ck.setMaxAge(0);
            ck.setPath("/");
            response.addCookie(ck);
        } catch (Exception e) {
        }
//        model.addAttribute("type", "success");
//        model.addAttribute("title", "Đăng xuất thành công!");
//        model.addAttribute("message", "Bạn đã đăng xuất khỏi hệ thống ChợĐiệnTử, về <a href='" + baseUrl + "'>trang chủ</a> để tham gia mua hàng trên Chợ Điện Tử ngay bây giờ!");
//        model.put("clientScript", "textUtils.redirect('/index.html', 1000);");
//        return "user.msg";
        return "redirect:" + baseUrl + "/index.html";
    }
}
