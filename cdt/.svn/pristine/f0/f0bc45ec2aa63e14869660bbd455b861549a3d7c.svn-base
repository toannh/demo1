package vn.chodientu.controller.api;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.entity.api.Request;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.SellerApi;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.PaymentStatus;
import vn.chodientu.entity.enu.ShipmentStatus;
import vn.chodientu.entity.input.OrderSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ApiHistoryService;
import vn.chodientu.service.OrderService;

@Controller("orderApiController")
@RequestMapping(value = "/api/order")
public class OrderController extends BaseApi {

    @Autowired
    private OrderService orderService;
    @Autowired
    private Gson gson;
    @Autowired
    private ApiHistoryService apiHistoryService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Response list(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            OrderSearch search = gson.fromJson(data.getParams(), OrderSearch.class);
            if (search == null) {
                return new Response(false, "Tham số chưa chính xác", "PARAM_EMPTY");
            }
            if (search.getPageSize() <= 0) {
                search.setPageSize(100);
            }
            search.setSellerId(user.getId());
            DataPage<Order> dataPage = orderService.search(search);
            response = new Response(true, "Danh sách đơn hàng", dataPage);
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            Order order = gson.fromJson(data.getParams(), Order.class);
            Order get = orderService.get(order.getId());
            if (get != null) {
                if (!get.getSellerId().equals(user.getId())) {
                    response = new Response(false, "Bạn không có quyền cập nhật đơn hàng này");
                } else {
                    if (order.getMarkSellerPayment() == PaymentStatus.PAID) {
                        orderService.markPaymentStatusAPI(order.getId(), PaymentStatus.PAID, true);
                    }
                    if (order.getMarkSellerPayment() == PaymentStatus.NEW) {
                        orderService.markPaymentStatusAPI(order.getId(), PaymentStatus.NEW, true);
                    }
                    if (order.getMarkSellerShipment()== ShipmentStatus.NEW) {
                        orderService.markShipmentStatusAPI(order.getId(), ShipmentStatus.NEW, true);
                    }
                    if (order.getMarkSellerShipment()== ShipmentStatus.DELIVERED) {
                        orderService.markShipmentStatusAPI(order.getId(), ShipmentStatus.DELIVERED, true);
                    }
                    if (order.isRefundStatus()) {
                        orderService.refundAPI(order.getId());
                    }
                }

            }
            response = new Response(true, "Thông tin đơn hàng vừa được cập nhật", orderService.get(order.getId()));
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }

}
