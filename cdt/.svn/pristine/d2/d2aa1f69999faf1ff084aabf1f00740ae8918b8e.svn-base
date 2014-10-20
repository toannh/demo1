package vn.chodientu.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.OrderItem;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.OrderItemRepository;
import vn.chodientu.repository.OrderRepository;

/**
 * @since May 20, 2014
 * @author PhuongDt
 */
@Service
public class InvoiceService {

    @Autowired
    private Viewer viewer;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderService orderService;

    public String getIdCode() {
        return orderRepository.genId();
    }

    public Response addToInvoice(List<String> itemIds) throws Exception {
        List<Item> items = itemService.list(itemIds);
        List<OrderItem> orderItems = new ArrayList<>();
        Order invoice = viewer.getInvoice();
        if (items != null && !items.isEmpty()) {
            for (Item item : items) {
                if (item == null) {
                    throw new Exception("Sản phẩm bạn muốn mua không tồn tại");
                }
                if (!item.isActive() || !item.isApproved() || !item.isCompleted()) {
                    throw new Exception("Sản phẩm không đủ điều kiện thanh toán");
                }
                if (item.getStartTime() > System.currentTimeMillis()) {
                    throw new Exception("Sản phẩm chưa đến hạn đăng bán, không thể giao dịch");
                }
                if (item.getEndTime() < System.currentTimeMillis()) {
                    throw new Exception("Sản phẩm đã hết hạn đăng bán, không thể giao dịch");
                }
                OrderItem orderItem = new OrderItem();
                orderItem.setId(orderItemRepository.genId());
                orderItem.setItemId(item.getId());
                orderItem.setCategoryPath(item.getCategoryPath());
                orderItem.setItemName(item.getName());
                orderItem.setQuantity(1);
                orderItem.setItemPrice(orderService.getItemPrice(item));
                orderItem.setDiscountPrice(item.getDiscountPrice());
                orderItem.setDiscountPercent(item.getDiscountPercent());
                orderItem.setGiftDetail(item.getGiftDetail());
                orderItem.setShipmentPrice(item.getShipmentPrice());
                orderItem.setShipmentType(item.getShipmentType());
                orderItem.setWeight(item.getWeight());
                orderItems.add(orderItem);
            }
            if (invoice != null) {
                if (invoice.getItems() != null && !invoice.getItems().isEmpty()) {
                    for (OrderItem orderItem : invoice.getItems()) {
                        for (OrderItem oTS : orderItems) {
                            if (oTS.getItemId().equals(orderItem.getItemId())) {
                                oTS.setQuantity(orderItem.getQuantity());
                                oTS.setId(orderItem.getId());
                                break;
                            }
                        }
                    }
                }
            }
        }
        return new Response(true, null, orderItems);
    }

    public Response addToInvoiceSeries(List<String> itemIds, String orderId) throws Exception {
        List<Item> items = itemService.list(itemIds);
        List<OrderItem> orderItems = new ArrayList<>();
        List<Order> invoiceSeries = viewer.getInvoiceSeries();
        Order invoice = null;
        boolean fag = false;
        if (invoiceSeries != null && !invoiceSeries.isEmpty()) {
            for (Order invoiceSery : invoiceSeries) {
                if (invoiceSery.getId().equals(orderId)) {
                    invoice = invoiceSery;
                    break;
                } else {
                    fag = true;
                }
            }
        }
        if (fag) {
           invoice = new Order();
           invoice.setId(orderId);
        }
        if (items != null && !items.isEmpty()) {
            for (Item item : items) {
                if (item == null) {
                    throw new Exception("Sản phẩm bạn muốn mua không tồn tại");
                }
                if (!item.isActive() || !item.isApproved() || !item.isCompleted()) {
                    throw new Exception("Sản phẩm không đủ điều kiện thanh toán");
                }
                if (item.getStartTime() > System.currentTimeMillis()) {
                    throw new Exception("Sản phẩm chưa đến hạn đăng bán, không thể giao dịch");
                }
                if (item.getEndTime() < System.currentTimeMillis()) {
                    throw new Exception("Sản phẩm đã hết hạn đăng bán, không thể giao dịch");
                }
                OrderItem orderItem = new OrderItem();
                orderItem.setId(orderItemRepository.genId());
                orderItem.setItemId(item.getId());
                orderItem.setCategoryPath(item.getCategoryPath());
                orderItem.setItemName(item.getName());
                orderItem.setQuantity(1);
                orderItem.setItemPrice(orderService.getItemPrice(item));
                orderItem.setDiscountPrice(item.getDiscountPrice());
                orderItem.setDiscountPercent(item.getDiscountPercent());
                orderItem.setGiftDetail(item.getGiftDetail());
                orderItem.setShipmentPrice(item.getShipmentPrice());
                orderItem.setShipmentType(item.getShipmentType());
                orderItem.setWeight(item.getWeight());
                orderItems.add(orderItem);
            }
            if (invoice != null) {
                if (invoice.getItems() != null && !invoice.getItems().isEmpty()) {
                    for (OrderItem orderItem : invoice.getItems()) {
                        for (OrderItem oTS : orderItems) {
                            if (oTS.getItemId().equals(orderItem.getItemId())) {
                                oTS.setQuantity(orderItem.getQuantity());
                                oTS.setId(orderItem.getId());
                                break;
                            }
                        }
                    }
                }
                invoice.setItems(orderItems);
            }
        }
        List<Order> orders = new ArrayList<>();
        if (invoiceSeries != null && !invoiceSeries.isEmpty()) {
            for (Order invoiceSery : invoiceSeries) {
                if (!invoiceSery.getId().equals(invoice.getId())) {
                    orders.add(invoiceSery);
                }
            }
            orders.add(invoice);
            viewer.setInvoiceSeries(orders);
        }
        return new Response(true, null, orderItems);
    }

    public void createInvoiceTmp(Order order) {
        viewer.setInvoice(order);
    }

    public void updateInvoice(List<OrderItem> items) {
        Order orders = viewer.getInvoice();
        if (orders != null) {
            for (OrderItem oi : orders.getItems()) {
                for (OrderItem i : items) {
                    if (oi.getId().equals(i.getId())) {
                        oi.setQuantity(i.getQuantity() > 0 ? i.getQuantity() : 1);
                        break;
                    }
                }
            }
        }
    }

    public void updateInvoiceSeries(List<OrderItem> items, String orderId) {
        List<Order> invoiceSeries = viewer.getInvoiceSeries();
        Order orders = null;
        if (!invoiceSeries.isEmpty()) {
            for (Order order : invoiceSeries) {
                if (order.getId().equals(orderId)) {
                    orders = order;
                    break;
                }
            }
        }
        if (orders != null) {
            for (OrderItem oi : orders.getItems()) {
                for (OrderItem i : items) {
                    if (oi.getItemId().equals(i.getId())) {
                        oi.setQuantity(i.getQuantity() > 0 ? i.getQuantity() : 1);
                        break;
                    }
                }
            }
        }
        if (!invoiceSeries.isEmpty() && orders != null) {
            for (Order order : invoiceSeries) {
                if (order.getId().equals(orders.getId())) {
                    order = orders;
                    break;
                }
            }
        }
    }

}
