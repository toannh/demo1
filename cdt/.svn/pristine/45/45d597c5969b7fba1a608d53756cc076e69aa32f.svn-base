package vn.chodientu.controller.market;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.District;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.OrderItem;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.service.CityService;
import vn.chodientu.service.CouponService;
import vn.chodientu.service.DistrictService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.OrderService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserService;

/**
 * @author thanhvv
 */
@Controller("orderController")
public class OrderController extends BaseMarket {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private CityService cityService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private Gson gson;

    @RequestMapping("/{orderId}/thanh-toan-gio-hang.html")
    public String payment(ModelMap map, @PathVariable("orderId") String orderId,
            @RequestParam(value = "copyId", defaultValue = "") String copyId) {
        Order order = null;
        map.put("title", "Thanh toán đơn hàng");

        try {
            order = orderService.getOrder(orderId);
            order = orderService.clone(order);
            if (viewer.getCart() == null) {
                viewer.setCart(new ArrayList<Order>());
            }
            boolean flag = true;
            for (Order o : viewer.getCart()) {
                if (o.getSellerId().equals(order.getSellerId())) {
                    viewer.getCart().remove(o);
                    viewer.getCart().add(order);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                viewer.getCart().add(order);
            }
            return "redirect:/" + order.getId() + "/thanh-toan-gio-hang.html?copyId=" + orderId;
        } catch (Exception ex) {
            if (viewer.getCart() == null) {
                map.addAttribute("type", "fail");
                map.addAttribute("title", "Thông báo hệ thống chợ điện tử");
                map.addAttribute("message", "Bạn chưa chọn sản phẩm , về <a href='" + baseUrl + "'>trang chủ</a> để tham gia mua hàng trên Chợ Điện Tử ngay bây giờ!");
                map.put("clientScript", "textUtils.redirect('/index.html', 5000);");
                return "user.msg";
            }
            for (Order o : viewer.getCart()) {
                if (o.getId().equals(orderId)) {
                    order = o;
                    break;
                }
            }
            if (order == null) {
                map.addAttribute("type", "fail");
                map.addAttribute("title", "Thông báo hệ thống chợ điện tử");
                map.addAttribute("message", "Không tìm thấy đơn hàng bạn yêu cầu , về <a href='" + baseUrl + "'>trang chủ</a> để tham gia mua hàng trên Chợ Điện Tử ngay bây giờ!");
                map.put("clientScript", "textUtils.redirect('/index.html', 5000);");
                return "user.msg";
            }

            if (viewer.getUser() != null && order.getBuyerId() == null) {
                order.setBuyerId(viewer.getUser().getId());
                order.setBuyerEmail(viewer.getUser().getEmail());
                order.setBuyerName(viewer.getUser().getName());
                order.setBuyerPhone(viewer.getUser().getPhone());
                order.setBuyerAddress(viewer.getUser().getAddress());
                order.setBuyerCityId(viewer.getUser().getCityId());
                order.setBuyerDistrictId(viewer.getUser().getDistrictId());
            }
        }
        try {
            map.put("seller", userService.get(order.getSellerId()));
            map.put("sellerInfo", sellerService.getById(order.getSellerId()));
        } catch (Exception e) {
            map.addAttribute("type", "fail");
            map.addAttribute("title", "Thông báo hệ thống chợ điện tử");
            map.addAttribute("message", "Đơn hàng không tồn tại người bán. Không thể thanh toán , về <a href='" + baseUrl + "'>trang chủ</a> để tham gia mua hàng trên Chợ Điện Tử ngay bây giờ!");
            map.put("clientScript", "textUtils.redirect('/index.html', 5000);");
            return "user.msg";
        }

        List<String> itemIds = new ArrayList();
        for (OrderItem orderItem : order.getItems()) {
            itemIds.add(orderItem.getItemId());
        }
        Map<String, List<String>> images = imageService.get(ImageType.ITEM, itemIds);
        for (Map.Entry<String, List<String>> entry : images.entrySet()) {
            String id = entry.getKey();
            List<String> img = entry.getValue();
            List<String> imgs = new ArrayList<>();
            for (String im : img) {
                imgs.add(imageService.getUrl(im).thumbnail(100, 100, "outbound").getUrl());
            }
            for (OrderItem orderItem : order.getItems()) {
                if (orderItem.getItemId().equals(id)) {
                    orderItem.setImages(imgs);
                }
            }
        }

        map.put("order", order);
        try {
            map.put("coupon", couponService.checkExistCouponByTime(order.getSellerId(), System.currentTimeMillis()));
        } catch (Exception e) {
        }
        map.put("clientScript", "var citys = " + gson.toJson(cityService.list())
                + "; var districts = " + gson.toJson(districtService.list())
                + "; order.initPayment();");

        return "market.order.payment";
    }

    @RequestMapping("/gio-hang.html")
    public String cart(@RequestParam(value = "page", defaultValue = "0") int page, ModelMap map) {
        map.put("title", "Giỏ hàng của bạn");
        if (viewer.getCart() == null) {
            map.addAttribute("type", "fail");
            map.addAttribute("title", "Thông báo hệ thống chợ điện tử");
            map.addAttribute("message", "Bạn chưa chọn sản phẩm , về <a href='" + baseUrl + "'>trang chủ</a> để tham gia mua hàng trên Chợ Điện Tử ngay bây giờ!");
            map.put("clientScript", "textUtils.redirect('/index.html', 5000);");
            return "user.msg";
        }
        this.getCart();
        map.put("clientScript", "order.initViewcart();");
        return "market.order.cart";
    }

    private List<Order> getCart() {
        if (viewer.getCart() != null) {
            List<String> ids = new ArrayList<>();
            List<String> sellerIds = new ArrayList<>();
            for (Order order : viewer.getCart()) {
                try {
                    if (!sellerIds.contains(order.getSellerId())) {
                        sellerIds.add(order.getSellerId());
                    }
                    for (OrderItem orderItem : order.getItems()) {
                        ids.add(orderItem.getItemId());
                    }
                } catch (Exception e) {
                }
            }
            List<Shop> shops = shopService.getShops(sellerIds);
            List<User> users = userService.getUserByIds(sellerIds);
            for (Order order : viewer.getCart()) {
                for (User user : users) {
                    List<String> image = imageService.get(ImageType.AVATAR, user.getId());
                    if (image != null && image.size() > 0) {
                        user.setAvatar(imageService.getUrl(image.get(0)).thumbnail(150, 150, "outbound").getUrl());
                    }
                    if (order.getSellerId().equals(user.getId())) {
                        order.setUser(user);
                        break;
                    }
                }
                for (Shop shop : shops) {
                    List<String> image = imageService.get(ImageType.SHOP_LOGO, shop.getUserId());
                    if (image != null && image.size() > 0) {
                        shop.setLogo(imageService.getUrl(image.get(0)).thumbnail(150, 150, "outbound").getUrl());
                    }
                    if (order.getSellerId().equals(shop.getUserId())) {
                        order.setShop(shop);
                        break;
                    }
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
        }
        return viewer.getCart();
    }

    @RequestMapping("/{orderId}/don-hang-thanh-cong.html")
    public String success(@PathVariable("orderId") String id, ModelMap map) {
        map.put("title", "Tạo đơn hàng thành công");
        try {
            Order order = orderService.get(id);
            map.put("order", order);
        } catch (Exception e) {
            map.addAttribute("type", "fail");
            map.addAttribute("title", "Thông báo hệ thống chợ điện tử");
            map.addAttribute("message", e.getMessage() + ", về <a href='" + baseUrl + "'>trang chủ</a> để tham gia mua hàng trên Chợ Điện Tử ngay bây giờ!");
            map.put("clientScript", "textUtils.redirect('/index.html', 5000);");
            return "user.msg";
        }
        return "market.order.success";
    }

    @RequestMapping("/{orderId}/paymentcallback.html")
    public String paymentCallback(@PathVariable("orderId") String id, ModelMap map,
            @RequestParam(value = "errorCode", defaultValue = "") String errorCode,
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "cancel", defaultValue = "false") boolean cancel) {
        map.put("title", "Thanh toán thành công");

        if (cancel) {
            map.addAttribute("type", "fail");
            map.addAttribute("title", "Thông báo hệ thống chợ điện tử");
            map.addAttribute("message", "Đơn hàng đã được hủy thanh toán, về <a href='" + baseUrl + "'>trang chủ</a> để tham gia mua hàng trên Chợ Điện Tử ngay bây giờ!");
            map.put("clientScript", "textUtils.redirect('/index.html', 5000);");
            return "user.msg";
        }

        try {
            Order order = orderService.payment(id, token);
            map.addAttribute("order", order);
            map.put("clientScript", "googleAnalytics.add(" + gson.toJson(order) + ", 'purchase');");
        } catch (Exception e) {
            map.addAttribute("type", "fail");
            map.addAttribute("title", "Thông báo hệ thống chợ điện tử");
            map.addAttribute("message", e.getMessage() + ", về <a href='" + baseUrl + "'>trang chủ</a> để tham gia mua hàng trên Chợ Điện Tử ngay bây giờ!");
            map.put("clientScript", "textUtils.redirect('/index.html', 5000);");
            return "user.msg";
        }

        return "market.order.success";
    }

    @RequestMapping("/{orderId}/chi-tiet-don-hang.html")
    public String order(@PathVariable("orderId") String id, ModelMap map) throws Exception {
        map.put("title", "Chi tiết đơn hàng");
        Order order = orderService.getOrder(id);
        List<OrderItem> orderItems = order.getItems();
        if (orderItems != null && !orderItems.isEmpty()) {
            for (OrderItem orderItem : orderItems) {
                List<String> img = new ArrayList<>();
                if (orderItem.getImages() == null) {
                    orderItem.setImages(new ArrayList<String>());
                }
                try {
                    for (String im : orderItem.getImages()) {
                        img.add(imageService.getUrl(im).thumbnail(60, 60, "outbound").getUrl());
                    }
                } catch (Exception e) {
                }
                orderItem.setImages(img);
            }
        }
        User user = userService.get(order.getSellerId());
        Shop shop = shopService.getShop(user.getId());
        map.put("seller", user);
        map.put("shop", shop);
        List<City> cities = cityService.list();
        List<District> districts = districtService.list();
        map.put("order", order);
        map.put("cities", cities);
        map.put("districts", districts);
        map.put("clientScript", "order.initInvoice();");
        return "market.order.invoice";
    }

}
