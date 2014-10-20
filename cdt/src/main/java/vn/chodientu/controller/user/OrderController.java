package vn.chodientu.controller.user;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.OrderItem;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.SellerStock;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.PaymentStatus;
import vn.chodientu.entity.enu.ShipmentStatus;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.input.OrderSearch;
import vn.chodientu.entity.input.PropertySearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.CityService;
import vn.chodientu.service.CouponService;
import vn.chodientu.service.DistrictService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.OrderService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.SellerStockService;
import vn.chodientu.service.UserService;

/**
 * @since May 15, 2014
 * @author Phu
 */
@Controller("userOrder")
@RequestMapping("/user")
public class OrderController extends BaseUser {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private CityService cityService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private UserService userService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private Gson gson;

    @RequestMapping(value = "/{code}/tao-van-don-cod.html", method = RequestMethod.GET)
    public String createLadingCod(ModelMap model, @PathVariable("code") String id) {
        if (viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/" + id + "/tao-van-don-cod.html";
        }
        try {
            Order order = orderService.getOrder(id);
            if (!viewer.getUser().getId().equals(order.getSellerId())) {
                throw new Exception("Đơn hàng này không phải đơn hàng của bạn");
            }
            if (order.getScId() != null && !order.getScId().equals("")) {
                throw new Exception("Đơn hàng này đã duyệt vận đơn Cod, bạn không thể thao tác");
            }
            for (OrderItem orderItem : order.getItems()) {
                List<String> img = new ArrayList<>();
                if (orderItem.getImages() == null) {
                    orderItem.setImages(new ArrayList<String>());
                }
                try {
                    for (String im : orderItem.getImages()) {
                        img.add(imageService.getUrl(im).thumbnail(150, 150, "outbound").getUrl());
                    }
                } catch (Exception e) {
                }
                orderItem.setImages(img);
            }
            model.put("order", order);
            model.put("seller", sellerService.getById(order.getSellerId()));
            model.put("user", userService.get(viewer.getUser().getId()));
            model.put("clientScript", "var citys = " + gson.toJson(cityService.list())
                    + "; var districts = " + gson.toJson(districtService.list())
                    + "; order.initCod();");
            return "user.order.ladingcod";
        } catch (Exception ex) {
            model.addAttribute("type", "fail");
            model.addAttribute("title", "Thông báo từ hệ thống Chợ Điện Tử");
            model.addAttribute("message", ex.getMessage() + ", về <a href='" + baseUrl + "'>trang chủ</a> để tham gia mua hàng trên Chợ Điện Tử ngay bây giờ!");
            model.put("clientScript", "textUtils.redirect('/user/hoa-don-ban-hang.html', 5000);");
            return "user.msg";
        }
    }

    @RequestMapping(value = "/{code}/tao-van-don-van-chuyen.html", method = RequestMethod.GET)
    public String createLading(ModelMap model, @PathVariable("code") String id) {
        if (viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/" + id + "/tao-van-don-cod.html";
        }
        try {
            Order order = orderService.getOrder(id);
            if (!viewer.getUser().getId().equals(order.getSellerId())) {
                throw new Exception("Đơn hàng này không phải đơn hàng của bạn");
            }
            if (order.getScId() != null && !order.getScId().equals("")) {
                throw new Exception("Đơn hàng này đã duyệt vận đơn vận chuyển, bạn không thể thao tác");
            }
            for (OrderItem orderItem : order.getItems()) {
                List<String> img = new ArrayList<>();
                if (orderItem.getImages() == null) {
                    orderItem.setImages(new ArrayList<String>());
                }
                try {
                    for (String im : orderItem.getImages()) {
                        img.add(imageService.getUrl(im).thumbnail(150, 150, "outbound").getUrl());
                    }
                } catch (Exception e) {
                }
                orderItem.setImages(img);
            }
            model.put("order", order);
            model.put("seller", sellerService.getById(order.getSellerId()));
            model.put("user", userService.get(viewer.getUser().getId()));
            model.put("clientScript", "var citys = " + gson.toJson(cityService.list())
                    + "; var districts = " + gson.toJson(districtService.list())
                    + "; order.initCod();");
            return "user.order.lading";
        } catch (Exception ex) {
            model.addAttribute("type", "fail");
            model.addAttribute("title", "Thông báo từ hệ thống Chợ Điện Tử");
            model.addAttribute("message", ex.getMessage() + ", về <a href='" + baseUrl + "'>trang chủ</a> để tham gia mua hàng trên Chợ Điện Tử ngay bây giờ!");
            model.put("clientScript", "textUtils.redirect('/user/hoa-don-ban-hang.html', 5000);");
            return "user.msg";
        }
    }

    @RequestMapping(value = "/don-hang-cua-toi.html", method = RequestMethod.GET)
    public String orderBuyer(ModelMap model,
            @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
            @RequestParam(value = "timeselect", defaultValue = "0", required = false) int timeselect,
            @RequestParam(value = "tab", defaultValue = "", required = false) String tab,
            @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
        if (viewer.getUser() == null || viewer == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/don-hang-cua-toi.html";
        }
        OrderSearch search = new OrderSearch();

        search.setPageIndex(page - 1);
        search.setPageSize(15);
        search.setRemove(-1);
        search.setBuyerId(viewer.getUser().getId());
        if (!keyword.equals("")) {
            search.setKeyword(keyword);
        }
        if (timeselect > 0) {
            long timeNow = new Date().getTime();
            long dayNow = 24 * 60 * 60 * 1000 * 3;
            search.setCreateTimeFrom(timeNow - dayNow);
            search.setCreateTimeTo(timeNow);
        }
        if (!tab.equals("")) {
            switch (tab) {
                case "paymentNEW":
                    search.setPaymentStatus(PaymentStatus.NEW);
                    break;
                case "paymentPAID":
                    search.setPaymentStatus(PaymentStatus.PAID);
                    break;
                case "shipmentNEW":
                    search.setShipmentStatus(ShipmentStatus.NEW);
                    break;
                case "shipmentDELIVERED":
                    search.setShipmentStatus(ShipmentStatus.DELIVERED);
                    break;
                case "recycleBin":
                    search.setRemove(1);
                    break;
                default:
            }
        }
        DataPage<Order> pageOrder = orderService.search(search);
        List<Order> listOrder = pageOrder.getData();
        List<String> orderIds = new ArrayList<>();
        List<String> cateIds = new ArrayList<>();
        for (Order order : listOrder) {
            for (OrderItem orderItem : order.getItems()) {
                cateIds.add(orderItem.getCategoryPath().get(0));
                List<String> img = new ArrayList<>();
                if (orderItem.getImages() == null) {
                    orderItem.setImages(new ArrayList<String>());
                } else {
                    try {
                        for (String im : orderItem.getImages()) {
                            img.add(imageService.getUrl(im).thumbnail(80, 80, "outbound").getUrl());
                        }
                    } catch (Exception e) {
                    }
                }
                orderItem.setImages(img);
            }
            User user;
            try {
                user = userService.get(order.getSellerId());
            } catch (Exception e) {
                user = new User();
            }
            order.setUser(user);
            orderIds.add(order.getId());
        }
        List<Category> categorys = categoryService.get(cateIds);
        for (Order order : listOrder) {
            for (OrderItem orderItem : order.getItems()) {
                for (Category category : categorys) {
                    if (category.getId().equals(orderItem.getCategoryPath().get(0))) {
                        orderItem.setNameCategory(category.getName());
                        break;
                    }
                }
            }
        }
        ItemSearch itSearch = new ItemSearch();
        itSearch.setManufacturerIds(new ArrayList<String>());
        itSearch.setModelIds(new ArrayList<String>());
        itSearch.setCityIds(new ArrayList<String>());
        itSearch.setProperties(new ArrayList<PropertySearch>());
        model.put("itemSearch", itSearch);
        //Đếm count các tab trạng thái đơn hàng
        Map<String, Long> sumPrice = orderService.sumPrice(search);
        model.put("sumPrice", sumPrice);
        model.put("countPaymentNew", orderService.countByPaymentStatus(PaymentStatus.NEW, false, viewer.getUser().getId()));
        model.put("countOrder", orderService.countByOrderAll(false, viewer.getUser().getId()));
        model.put("countPaymentPaid", orderService.countByPaymentStatus(PaymentStatus.PAID, false, viewer.getUser().getId()));
        model.put("countShipmentNew", orderService.countByShipmentStatus(ShipmentStatus.NEW, false, viewer.getUser().getId()));
        model.put("countShipmentDelivered", orderService.countByShipmentStatus(ShipmentStatus.DELIVERED, false, viewer.getUser().getId()));
        model.put("countRecycleBin", orderService.countByRemove(true, false, viewer.getUser().getId()));
        model.put("pageOrder", pageOrder);
        model.put("search", search);
        model.put("keyword", keyword);
        model.put("tab", tab);
        model.put("timeselect", timeselect);
        model.put("clientScript", " var orderIds=" + gson.toJson(orderIds) + "; var userId=" + viewer.getUser().getId() + "; order.initOrderBuyer();");
        model.put("inOrder", true);
        return "user.order.buyer";
    }

    @RequestMapping(value = "/hoa-don-ban-hang.html", method = RequestMethod.GET)
    public String orderSeller(
            ModelMap model, @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
            @RequestParam(value = "tab", defaultValue = "", required = false) String tab,
            @RequestParam(value = "timeForm", defaultValue = "0", required = false) long timeForm,
            @RequestParam(value = "timeTo", defaultValue = "0", required = false) long timeTo,
            @RequestParam(value = "buyerId", defaultValue = "", required = false) String buyerId,
            @RequestParam(value = "sort", defaultValue = "0", required = false) int sort
    ) throws Exception {
        if (viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/hoa-don-ban-hang.html";
        }
        OrderSearch orderSearch = new OrderSearch();
        orderSearch.setPageIndex(page - 1);
        orderSearch.setPageSize(15);
        orderSearch.setRemove(-1);
        orderSearch.setSellerId(viewer.getUser().getId());
        if (!keyword.equals("")) {
            orderSearch.setKeyword(keyword);
        }
        if (!buyerId.equals("")) {
            orderSearch.setBuyerId(buyerId);
        }

        if (sort > 0) {
            orderSearch.setSortOrderBy(sort);
        }
        if (timeForm > 0 && timeTo > 0) {
            orderSearch.setCreateTimeFrom(timeForm);
            orderSearch.setCreateTimeTo(timeTo);
        }
        if (!tab.equals("")) {
            switch (tab) {
                case "paymentNEW":
                    orderSearch.setPaymentStatus(PaymentStatus.NEW);
                    break;
                case "paymentPAID":
                    orderSearch.setPaymentStatus(PaymentStatus.PAID);
                    break;
                case "shipmentDELIVERED":
                    orderSearch.setShipmentStatus(ShipmentStatus.DELIVERED);
                    break;
                case "shipmentNEW":
                    orderSearch.setShipmentStatus(ShipmentStatus.NEW);
                    break;
                case "recycleBin":
                    orderSearch.setRemove(1);
                    break;
                default:
            }
        }
        DataPage<Order> dataPage = orderService.search(orderSearch);
        List<Order> listOrder = dataPage.getData();
        List<String> orderIds = new ArrayList<>();
        for (Order order : listOrder) {
            for (OrderItem orderItem : order.getItems()) {
                List<String> img = new ArrayList<>();
                if (orderItem.getImages() == null) {
                    orderItem.setImages(new ArrayList<String>());
                } else {
                    try {
                        for (String im : orderItem.getImages()) {
                            img.add(imageService.getUrl(im).thumbnail(80, 80, "outbound").getUrl());
                        }
                    } catch (Exception e) {
                    }
                }
                orderItem.setImages(img);
            }
            orderIds.add(order.getId());
        }
        dataPage.setData(listOrder);
        //Đếm count các tab trạng thái đơn hàng
        ItemSearch itSearch = new ItemSearch();
        itSearch.setManufacturerIds(new ArrayList<String>());
        itSearch.setModelIds(new ArrayList<String>());
        itSearch.setCityIds(new ArrayList<String>());
        itSearch.setProperties(new ArrayList<PropertySearch>());
        model.put("itemSearch", itSearch);
        Seller seller = sellerService.getById(viewer.getUser().getId());
        model.put("seller", seller);
        Map<String, Long> sumPrice = orderService.sumPrice(orderSearch);
        model.put("sumPrice", sumPrice);
        model.put("countPaymentNew", orderService.countByPaymentStatus(PaymentStatus.NEW, true, viewer.getUser().getId()));
        model.put("countOrder", orderService.countByOrderAll(true, viewer.getUser().getId()));
        model.put("countPaymentPaid", orderService.countByPaymentStatus(PaymentStatus.PAID, true, viewer.getUser().getId()));
        model.put("countShipmentNew", orderService.countByShipmentStatus(ShipmentStatus.NEW, true, viewer.getUser().getId()));
        model.put("countShipmentDelivered", orderService.countByShipmentStatus(ShipmentStatus.DELIVERED, true, viewer.getUser().getId()));
        model.put("countRecycleBin", orderService.countByRemove(true, true, viewer.getUser().getId()));
        model.put("tab", tab);
        model.put("timeForm", timeForm);
        model.put("timeTo", timeTo);
        model.put("sort", sort);
        model.put("keyword", keyword);
        model.put("orderSellers", dataPage);
        model.put("inOrder", true);
        model.put("clientScript", "order.initSeller();");
        model.put("clientScript", "var citys = " + gson.toJson(cityService.list()) + "; var districts = " + gson.toJson(districtService.list()) + "; var orderIds=" + gson.toJson(orderIds) + "; var userId=" + viewer.getUser().getId() + "; order.initSeller(); invoice.initSeries();");
        viewer.setInvoiceSeries(new ArrayList<Order>());
        return "user.order.seller";
    }

}
