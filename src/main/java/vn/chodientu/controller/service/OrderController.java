package vn.chodientu.controller.service;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.component.NlClient;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.data.OrderSubItem;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.OrderItem;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.PaymentMethod;
import vn.chodientu.entity.enu.PaymentStatus;
import vn.chodientu.entity.enu.ShipmentStatus;
import vn.chodientu.entity.db.Lading;
import vn.chodientu.entity.db.Message;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.MessageService;
import vn.chodientu.service.OrderService;
import vn.chodientu.service.SellerService;

/**
 *
 * @author thanhvv
 */
@Controller("serviceOrder")
@RequestMapping("/order")
public class OrderController extends BaseRest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private NlClient nlClient;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private Gson gson;

    @RequestMapping(value = "/addtocart", method = RequestMethod.GET)
    @ResponseBody
    public Response addToCart(@RequestParam(value = "itemId", defaultValue = "") String itemId,
            @RequestParam(value = "quantity", defaultValue = "0") int quantity,
            @RequestParam(value = "subItem", defaultValue = "") String sitem) {
        try {
            OrderSubItem subItem;
            try {
                subItem = gson.fromJson(StringUtils.newStringUtf8(Base64.decodeBase64(sitem)), OrderSubItem.class);
            } catch (Exception e) {
                subItem = null;
            }
            Response resp = orderService.addToCart(itemId, quantity, subItem);
            resp.setData(this.getCart());
            return resp;
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    private List<Order> getCart() {
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
        }
        return viewer.getCart();
    }

    @RequestMapping(value = "/removeitem", method = RequestMethod.GET)
    @ResponseBody
    public Response removeItem(@RequestParam(value = "id", defaultValue = "") String id) {
        try {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(id);
            orderService.removeFromCart(orderItem);
            return new Response(true, "Sản phẩm đã được xóa khỏi giỏ hàng", this.getCart());
        } catch (Exception e) {
            return new Response(true, e.getMessage(), this.getCart());
        }
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    @ResponseBody
    public Response clear() {
        viewer.setCart(new ArrayList<Order>());
        return new Response(true);
    }

    @RequestMapping(value = "/updatecart", method = RequestMethod.GET)
    @ResponseBody
    public Response updateCart(@RequestParam(value = "orderId", defaultValue = "") String orderId,
            @RequestParam(value = "orderitemid", defaultValue = "") String orderItemId,
            @RequestParam(value = "quantity", defaultValue = "0") int quantity) {
        try {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(orderItemId);
            orderItem.setOrderId(orderId);
            orderItem.setQuantity(quantity);
            List<OrderItem> ois = new ArrayList<>();
            ois.add(orderItem);
            orderService.updateCart(ois);
            return new Response(true, "Sản phẩm đã được cập nhật số lượng", this.getCart());
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/calculator", method = RequestMethod.POST)
    @ResponseBody
    public Response calculator(@RequestBody Order order,
            @RequestParam(value = "all", defaultValue = "false") boolean all,
            @RequestParam(value = "protec", defaultValue = "false") boolean protec,
            @RequestParam(value = "weight", defaultValue = "0") int weight,
            @RequestParam(value = "sellerDistrictId", defaultValue = "") String sellerDistrictId,
            @RequestParam(value = "sellerCityId", defaultValue = "") String sellerCityId,
            @RequestParam(value = "price", defaultValue = "0") long price) {
        try {
            return new Response(true, "Thông tin order", orderService.calculator(order, all, weight, price, sellerCityId, sellerDistrictId, protec));
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam(value = "id", defaultValue = "") String id) {
        try {
            return new Response(true, "Thông tin order", orderService.getOrder(id));
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
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
            List<Order> orders = viewer.getCart();
            for (Order cart : orders) {
                if (cart.getId().equals(order.getId())) {
                    for (OrderItem citem : cart.getItems()) {
                        for (OrderItem item : order.getItems()) {
                            if (item.getItemId().equals(citem.getItemId())) {
                                item.setSubItem(citem.getSubItem());
                                break;
                            }
                        }
                    }
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

    @RequestMapping(value = "/createlading", method = RequestMethod.POST)
    @ResponseBody
    public Response createLadingCod(@RequestBody Lading form) {
        try {
            return orderService.createLading(form);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/savenote", method = RequestMethod.POST)
    @ResponseBody
    public Response saveNote(@RequestParam(value = "id") String id, @RequestParam String note) throws Exception {
        return orderService.note(id, note, false);
    }

    @RequestMapping(value = "/removeorder", method = RequestMethod.GET)
    @ResponseBody
    public Response removeOrder(@RequestParam(value = "id") String id) throws Exception {
        return orderService.remove(id);
    }

    @RequestMapping(value = "/savenoteseller", method = RequestMethod.POST)
    @ResponseBody
    public Response saveNoteSeller(@RequestParam(value = "id") String id, @RequestParam String note) throws Exception {
        return orderService.note(id, note, true);
    }

    @RequestMapping(value = "/markpaymentstatus", method = RequestMethod.GET)
    @ResponseBody
    public Response markPaymentStatus(@RequestParam String orderId, @RequestParam boolean seller) throws Exception {
        return orderService.markPaymentStatus(orderId, PaymentStatus.PAID, seller);
    }

    @RequestMapping(value = "/markshipmentstatus", method = RequestMethod.GET)
    @ResponseBody
    public Response markShipmentStatus(@RequestParam String orderId, @RequestParam boolean seller) throws Exception {
        return orderService.markShipmentStatus(orderId, ShipmentStatus.DELIVERED, seller);
    }

    @RequestMapping(value = "/unmarkpaymentstatus", method = RequestMethod.GET)
    @ResponseBody
    public Response unmarkPaymentStatus(@RequestParam String orderId, @RequestParam boolean seller) throws Exception {
        return orderService.markPaymentStatus(orderId, PaymentStatus.NEW, seller);
    }

    @RequestMapping(value = "/unmarkshipmentstatus", method = RequestMethod.GET)
    @ResponseBody
    public Response unmarkShipmentStatus(@RequestParam String orderId, @RequestParam boolean seller) throws Exception {
        return orderService.markShipmentStatus(orderId, ShipmentStatus.NEW, seller);
    }

    @RequestMapping(value = "/refund", method = RequestMethod.GET)
    @ResponseBody
    public Response refund(@RequestParam String orderId) throws Exception {
        return orderService.refund(orderId);
    }

    @RequestMapping(value = "/sendmessge", method = RequestMethod.POST)
    @ResponseBody
    public Response sendMessge(@RequestBody Message message) throws Exception {
        try {
            return messageService.send(message.getFromEmail(), message.getSubject(), message.getContent(), message.getOrderId(), null);
        } catch (Exception e) {
            return new Response(false, null);
        }
    }

    @RequestMapping(value = "/findbyids", method = RequestMethod.POST)
    @ResponseBody
    public Response findByIds(@RequestBody List<String> ids) {
        try {
            return new Response(true, "Danh sách đơn hàng", orderService.findByIds(ids));
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

}
