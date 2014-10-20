package vn.chodientu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.OrderItem;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.UserService;

/**
 *
 * @author phugt
 */
public class BaseWeb {

    @Autowired
    protected Viewer viewer;
    @Autowired
    private ServletContext context;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ItemService itemService;

    protected String baseUrl;
    protected String staticUrl;

    @ModelAttribute
    public void addGlobalAttr(ModelMap map, @CookieValue(value = "remember", defaultValue = "") String remember) {
        switch (request.getRequestURI()) {
            case "/":
                baseUrl = request.getRequestURL().substring(0, request.getRequestURL().length() - 1);
                break;
            case "":
                baseUrl = request.getRequestURL().toString();
                break;
            default:
                baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
                break;
        }
        staticUrl = baseUrl + "/static";

        map.put("baseUrl", baseUrl);
        map.put("staticUrl", staticUrl);

        remember(remember);

        if (viewer.getUser() != null) {
            List<String> avatars = imageService.get(ImageType.AVATAR, viewer.getUser().getId());
            if (avatars.isEmpty()) {
                viewer.getUser().setAvatar(staticUrl + "/lib/no-avatar.png");
            } else {
                viewer.getUser().setAvatar(imageService.getUrl(avatars.get(0)).getUrl());
            }
        }

        map.put("viewer", viewer);
        if (viewer.getCart() != null) {
            List<String> ids = new ArrayList<>();
            for (Order order : viewer.getCart()) {
                try {
                    for (OrderItem orderItem : order.getItems()) {
                        ids.add(orderItem.getItemId());
                    }

                } catch (Exception e) {
                }
            }
            Map<String, List<String>> images = imageService.get(ImageType.ITEM, ids);
            for (Map.Entry<String, List<String>> entry : images.entrySet()) {
                String id = entry.getKey();
                List<String> img = entry.getValue();
                List<String> imgs = new ArrayList<>();
                for (String im : img) {
                    imgs.add(imageService.getUrl(im).thumbnail(100, 100, "outbound").getUrl());
                }
                for (Order order : viewer.getCart()) {
                    try {
                        for (OrderItem orderItem : order.getItems()) {
                            if (orderItem.getItemId().equals(id)) {
                                orderItem.setImages(imgs);
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
            map.put("itemCart", ids.size());
        }
    }

    public void remember(String remember) {
        if (viewer.getUser() == null) {
            try {
                String rememberKey = new String(Base64.decodeBase64(remember));
                String[] split = rememberKey.split("-");
                Response response = userService.getById(split[0]);
                User user = (User) response.getData();
                if (user != null && user.getRememberKey() != null && user.getRememberKey().equals(split[1]) && user.isActive()) {
                    viewer.setUser(user);
                }
            } catch (Exception e) {
            }
        }
    }


    /*
     @ExceptionHandler
     public String handleHttpExceptions(HttpServerErrorException ex) {
     request.setAttribute("title", ex.getStatusCode().toString().equals("404") ? "Không tìm thấy trang" : "Có lỗi xảy ra");
     request.setAttribute("ex", ex);
     request.setAttribute("clientScript", "error.init();");
     request.setAttribute("baseUrl", baseUrl);
     request.setAttribute("staticUrl", staticUrl);
     return ex.getStatusCode().toString().equals("404") ? "index.404" : "index.500";
     }

     @ExceptionHandler
     public String handleExceptions(Exception ex) {
     request.setAttribute("ex", ex);
     request.setAttribute("clientScript", "error.init();");
     request.setAttribute("baseUrl", baseUrl);
     request.setAttribute("staticUrl", staticUrl);
     return "index.500";
     }
     */
}
