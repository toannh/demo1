package vn.chodientu.controller.user;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.District;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CityService;
import vn.chodientu.service.DistrictService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.UserService;

/**
 * @since May 20, 2014
 * @author Phu
 */
@Controller
@RequestMapping("/user")
public class ProfileController extends BaseUser {

    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private Gson gson;
    @Autowired
    private ImageService imageService;

    @RequestMapping("/profile")
    public String profile(ModelMap map, HttpSession session) {
        if (viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/profile.html";
        }
        String id = viewer.getUser().getId();
        Response response = userService.getById(id);
        User user = (User) response.getData();
        List<String> getImg = null;
        getImg = imageService.get(ImageType.AVATAR, user.getId());
        if (getImg != null && !getImg.isEmpty()) {
            user.setAvatar(imageService.getUrl(getImg.get(0)).thumbnail(42, 42, "outbound").getUrl());
        }
        viewer.setUser(user);
        List<City> cities = cityService.list();
        List<District> districts = districtService.list();
        map.put("user", user);
        map.put("clientScript", " var citys = " + gson.toJson(cities) + "; var districts=" + gson.toJson(districts) + " ;var curUser= " + gson.toJson(user) + ";user.initChangeProfile('" + user.getCityId() + "', '" + user.getDistrictId() + "');");
        return "user.profile";
    }
}
