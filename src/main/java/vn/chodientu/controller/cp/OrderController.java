package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.input.OrderSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.CityService;
import vn.chodientu.service.DistrictService;
import vn.chodientu.service.OrderService;
import vn.chodientu.service.UserService;

@Controller("cpOrder")
@RequestMapping("/cp/order")
public class OrderController extends BaseCp {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private Gson gson;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
        OrderSearch orderSearch = new OrderSearch();
        if (session.getAttribute("orderSearch") != null && page != 0) {
            orderSearch = (OrderSearch) session.getAttribute("orderSearch");
        } else {
            session.setAttribute("orderSearch", orderSearch);
        }
        orderSearch.setPageIndex(page - 1);
        orderSearch.setPageSize(30);
        orderSearch.setRemove(0);
        DataPage<Order> dataPage = orderService.search(orderSearch);
        List<Order> data = dataPage.getData();
        List<String> sellerIds = new ArrayList<>();
        if (data != null && !data.isEmpty()) {
            for (Order order : data) {
                sellerIds.add(order.getSellerId());
            }
        }
        List<User> users = userService.getUserByIds(sellerIds);
        if (users != null && !users.isEmpty()) {
            for (Order order : data) {
                for (User user : users) {
                    if (user.getId().equals(order.getSellerId())) {
                        order.setUser(user);
                    }
                }
            }
        }
        Map<String, Long> sumPrice = orderService.sumPrice(orderSearch);
        map.put("orderSearch", orderSearch);
        map.put("sumPrice", sumPrice);
        map.put("dataPage", dataPage);
        map.put("clientScript", "var citys = " + gson.toJson(cityService.list())
                + "; var districts = " + gson.toJson(districtService.list())
                + "; order.init();");
        return "cp.order.list";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(ModelMap map, HttpSession session, @ModelAttribute OrderSearch orderSearch) {
        session.setAttribute("orderSearch", orderSearch);
        orderSearch.setPageIndex(0);
        orderSearch.setPageSize(30);
        orderSearch.setRemove(0);
        DataPage<Order> dataPage = orderService.search(orderSearch);
        List<Order> data = dataPage.getData();
        List<String> sellerIds = new ArrayList<>();

        if (data != null && !data.isEmpty()) {
            for (Order order : data) {
                sellerIds.add(order.getSellerId());
            }
        }
        List<User> users = userService.getUserByIds(sellerIds);
        if (users != null && !users.isEmpty()) {
            for (Order order : data) {
                for (User user : users) {
                    if (user.getId().equals(order.getSellerId())) {
                        order.setUser(user);
                    }
                }
            }
        }
        Map<String, Long> sumPrice = orderService.sumPrice(orderSearch);
        map.put("orderSearch", orderSearch);
        map.put("sumPrice", sumPrice);
        map.put("dataPage", dataPage);
        map.put("clientScript", "var citys = " + gson.toJson(cityService.list())
                + "; var districts = " + gson.toJson(districtService.list())
                + "; order.init();");
        return "cp.order.list";
    }

}
