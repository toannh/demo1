/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.OrderItem;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.enu.PaymentMethod;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CashService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.InvoiceService;
import vn.chodientu.service.OrderService;
import vn.chodientu.service.SellerService;

/**
 *
 * @author PhuongDT
 */
@Controller("serviceInvoice")
@RequestMapping("/invoice")
public class InvoiceController extends BaseRest {

    @Autowired
    private CashService cashService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private Gson gson;

    @RequestMapping(value = "/updateinvoice", method = RequestMethod.GET)
    @ResponseBody
    public Response updateinvoice(@RequestParam(value = "orderId", defaultValue = "") String orderId,
            @RequestParam(value = "orderitemid", defaultValue = "") String orderItemId,
            @RequestParam(value = "quantity", defaultValue = "0") int quantity) {
        try {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(orderItemId);
            orderItem.setOrderId(orderId);
            orderItem.setQuantity(quantity);
            List<OrderItem> ois = new ArrayList<>();
            ois.add(orderItem);
            invoiceService.updateInvoice(ois);
            Order invoice = viewer.getInvoice();
            return new Response(true, "Sản phẩm đã được cập nhật số lượng", invoice.getItems());
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/getorderitem", method = RequestMethod.GET)
    @ResponseBody
    public Response getOrderItem(@RequestParam(value = "itemIds", defaultValue = "") String itemIds) {
        try {
            List<String> items = gson.fromJson(itemIds, new TypeToken<List<String>>() {
            }.getType());
            return invoiceService.addToInvoice(items);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/getinvoice", method = RequestMethod.GET)
    @ResponseBody
    public Response getinvoice(@RequestParam(value = "orderId", defaultValue = "") String orderId) {
        Order invoice = viewer.getInvoice();
        if (invoice != null) {
            return new Response(true, null, invoice);
        } else {
            return new Response(false, "Không tìm thấy đơn hàng");
        }
    }

    @RequestMapping(value = "/gencodeinvoice", method = RequestMethod.GET)
    @ResponseBody
    public Response gencodeinvoice() {
        return new Response(true, "Mã hóa đơn hàng loạt", invoiceService.getIdCode());

    }

    @RequestMapping(value = "/calculatorinvoice", method = RequestMethod.POST)
    @ResponseBody
    public Response calculator(@RequestBody Order order,
            @RequestParam(value = "all", defaultValue = "false") boolean all,
            @RequestParam(value = "protec", defaultValue = "false") boolean protec,
            @RequestParam(value = "weight", defaultValue = "0") int weight,
            @RequestParam(value = "sellerDistrictId", defaultValue = "") String sellerDistrictId,
            @RequestParam(value = "sellerCityId", defaultValue = "") String sellerCityId,
            @RequestParam(value = "price", defaultValue = "0") long price) {
        try {
            Order calculator = orderService.calculator(order, all, weight, price, sellerCityId, sellerDistrictId, protec);
            List<Order> invoiceSeries = viewer.getInvoiceSeries();
            List<Order> orders = new ArrayList<>();
            if (invoiceSeries != null && !invoiceSeries.isEmpty()) {
                for (Order invoiceSery : invoiceSeries) {
                    if (!invoiceSery.getId().equals(calculator.getId())) {
                        orders.add(invoiceSery);
                    }
                }
            } else {
                viewer.setInvoiceSeries(new ArrayList<Order>());
                invoiceSeries = new ArrayList<>();
                invoiceSeries.add(calculator);

            }
            orders.add(calculator);
            viewer.setInvoiceSeries(orders);
            return new Response(true, "Thông tin order", orders);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/getorderitemseries", method = RequestMethod.GET)
    @ResponseBody
    public Response getorderitemseries(@RequestParam(value = "itemIds", defaultValue = "") String itemIds, @RequestParam(value = "orderId", defaultValue = "") String orderId) {
        try {
            List<String> items = gson.fromJson(itemIds, new TypeToken<List<String>>() {
            }.getType());
            return invoiceService.addToInvoiceSeries(items, orderId);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/updateinvoiceseries", method = RequestMethod.GET)
    @ResponseBody
    public Response updateinvoiceseries(@RequestParam(value = "orderId", defaultValue = "") String orderId,
            @RequestParam(value = "orderitemid", defaultValue = "") String orderItemId,
            @RequestParam(value = "quantity", defaultValue = "0") int quantity) {
        try {
            Order order = null;
            if (orderItemId != null && !orderItemId.equals("")) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(orderItemId);
                orderItem.setOrderId(orderId);
                orderItem.setQuantity(quantity);
                List<OrderItem> ois = new ArrayList<>();
                ois.add(orderItem);
                invoiceService.updateInvoiceSeries(ois, orderId);
            }
            List<Order> invoiceSeries = viewer.getInvoiceSeries();
            if (!invoiceSeries.isEmpty()) {
                for (Order invoiceSery : invoiceSeries) {
                    if (invoiceSery.getId().equals(orderId)) {
                        order = invoiceSery;
                        break;
                    }
                }
            }

            return new Response(true, "Sản phẩm đã được cập nhật số lượng", order.getItems());
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/createseries", method = RequestMethod.POST)
    @ResponseBody
    public Response createSeries(@RequestBody List<Order> orders) {
        Response resp = null;
        HashMap<String, Object> error = new HashMap<String, Object>();
        if (orders != null && !orders.isEmpty()) {
            for (Order order : orders) {
                if (viewer.getUser() != null) {
                    order.setBuyerId(viewer.getUser().getId());
                }
                try {
                    resp = orderService.createOrder(order);
                    error.put(order.getId(), resp);
                } catch (Exception ex) {
                    error.put(order.getId(), new Response(false, ex.getMessage()));
                }
                if (resp.isSuccess()) {
                    orderService.sendMessageCreateOrder(order, baseUrl);
                    
                }

            }
        }
        return new Response(true, null, error);

    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Response create(@RequestBody Order order,
            @RequestParam(value = "copyId", defaultValue = "") String copyId) {
        try {
            if (viewer.getUser() != null) {
                order.setBuyerId(viewer.getUser().getId());
            }
            if (!copyId.equals("")) {
                try {
                    Order oldOrder = orderService.get(copyId);
                    if (oldOrder != null) {
                        order.setBuyerId(oldOrder.getBuyerId());
                    }
                } catch (Exception e) {
                    throw new Exception("Đơn hàng yêu cầu sửa không tồn tại trên hệ thống");
                }
            }
            Response resp = orderService.createOrder(order);
            if (resp.isSuccess()) {
                orderService.sendMessageCreateOrder(order, baseUrl);
                if (order.getPaymentMethod().equals(PaymentMethod.COD) || order.getPaymentMethod().equals(PaymentMethod.NONE)) {
                    resp.setData(baseUrl + "/" + order.getId() + "/don-hang-thanh-cong.html");
                } else {
                    Seller seller = sellerService.getById(order.getSellerId());
                    resp.setData(orderService.payment(order, seller, baseUrl));
                }
                viewer.setCart(new ArrayList<Order>());
            }
            return resp;
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }
}
