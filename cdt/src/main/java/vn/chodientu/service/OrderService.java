package vn.chodientu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.chodientu.component.NlClient;
import vn.chodientu.component.ScClient;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.data.OrderSubItem;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.Coupon;
import vn.chodientu.entity.db.District;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Lading;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.OrderItem;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.SellerReview;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.EmailOutboxType;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.enu.PaymentMethod;
import vn.chodientu.entity.enu.PaymentStatus;
import vn.chodientu.entity.enu.SellerHistoryType;
import vn.chodientu.entity.enu.ShipmentStatus;
import vn.chodientu.entity.enu.ShipmentType;
import vn.chodientu.entity.enu.SmsOutboxType;
import vn.chodientu.entity.input.OrderSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.LadingRepository;
import vn.chodientu.repository.OrderItemRepository;
import vn.chodientu.repository.OrderRepository;
import vn.chodientu.repository.ShopRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;
import vn.chodientu.util.UrlUtils;

/**
 *
 * @author Phu
 */
@Service
public class OrderService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private NlClient nlClient;
    @Autowired
    private UserService userService;
    @Autowired
    private ScClient scClient;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private Viewer viewer;
    @Autowired
    private CouponService couponService;
    @Autowired
    private CityService cityService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private Validator validator;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private SearchIndexService indexService;
    @Autowired
    private LadingRepository ladingRepository;
    @Autowired
    private SellerCustomerService customerService;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private SellerReviewService sellerReviewService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RealTimeService realTimeService;
    @Autowired
    private SellerHistoryService sellerHistoryService;
    @Autowired
    private CashService cashService;

    /**
     * Thêm item vào giỏ hàng
     *
     * @param itemId
     * @param quantity
     * @param subItem
     * @return
     * @throws Exception
     */
    public Response addToCart(String itemId, int quantity, OrderSubItem subItem) throws Exception {
        Item item = itemRepository.find(itemId);
        Order order = null;
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
        if (viewer.getUser() != null && viewer.getUser().getId().equals(item.getSellerId())) {
            throw new Exception("Không thể tự mua sản phẩm của chính bạn");
        }
        boolean exist = false;
        if (viewer.getCart() == null) {
            viewer.setCart(new ArrayList<Order>());
        }
        List<Order> orders = viewer.getCart();
        for (Order o : orders) {
            if (o.getSellerId().equals(item.getSellerId())) {
                exist = true;
                order = o;
                break;
            }
        }
        if (!exist) {
            order = new Order();
            order.setId(orderRepository.genId());
            order.setSellerId(item.getSellerId());
            if (viewer.getUser() != null) {
                order.setBuyerId(viewer.getUser().getId());
                order.setBuyerEmail(viewer.getUser().getEmail());
                order.setBuyerName(viewer.getUser().getName());
                order.setBuyerPhone(viewer.getUser().getPhone());
                order.setBuyerAddress(viewer.getUser().getAddress());
                order.setBuyerCityId(viewer.getUser().getCityId());
                order.setBuyerDistrictId(viewer.getUser().getDistrictId());
                order.setItems(new ArrayList<OrderItem>());
            }
            orders.add(order);
        }

        exist = false;
        if (order.getItems() == null) {
            order.setItems(new ArrayList<OrderItem>());
        }
        for (OrderItem oi : order.getItems()) {
            if (oi.getItemId().equals(item.getId())) {
                exist = true;
                oi.setQuantity(oi.getQuantity() + (quantity > 0 ? quantity : 1));
                this.convertSubItem(oi, subItem);
                break;
            }
        }

        if (!exist) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(orderItemRepository.genId());
            orderItem.setItemId(item.getId());
            orderItem.setCategoryPath(item.getCategoryPath());
            orderItem.setItemName(item.getName());
            orderItem.setItemPrice(this.getItemPrice(item));
            orderItem.setQuantity(quantity > 0 ? quantity : 1);
            orderItem.setDiscountPrice(item.getDiscountPrice());
            orderItem.setDiscountPercent(item.getDiscountPercent());
            orderItem.setGiftDetail(item.getGiftDetail());
            orderItem.setShipmentPrice(item.getShipmentPrice());
            orderItem.setShipmentType(item.getShipmentType());
            orderItem.setWeight(item.getWeight());
            this.convertSubItem(orderItem, subItem);
            order.getItems().add(orderItem);
        }

        return new Response(true);
    }

    private void convertSubItem(OrderItem item, OrderSubItem subItem) {
        if (subItem == null) {
            return;
        }
        List<OrderSubItem> subItems = item.getSubItem();
        if (subItems == null) {
            subItems = new ArrayList<OrderSubItem>();
        }
        subItem.setColorValueName(subItem.getColorValueName() == null || subItem.getColorValueName().equals("") ? "" : subItem.getColorValueName());
        subItem.setSizeValueName(subItem.getSizeValueName() == null || subItem.getSizeValueName().equals("") ? "" : subItem.getSizeValueName());
        subItem.setAlias(TextUtils.createAlias(subItem.getColorValueName() + subItem.getSizeValueName()));
        boolean exist = false;
        int quantity = 0;
        for (OrderSubItem osi : subItems) {
            if (osi.getAlias().equals(subItem.getAlias())) {
                exist = true;
                osi.setQuantity(subItem.getQuantity());
            }
            quantity += osi.getQuantity();
        }
        if (!exist) {
            subItems.add(subItem);
            quantity += subItem.getQuantity();
        }
        item.setSubItem(subItems);
        item.setQuantity(quantity);
    }

    /**
     * Xóa item trong giỏ hàng
     *
     * @param item
     */
    public void removeFromCart(OrderItem item) {
        if (viewer.getCart() == null) {
            viewer.setCart(new ArrayList<Order>());
        }
        List<Order> orders = viewer.getCart();
        for (Order o : orders) {
            for (int i = 0; i < o.getItems().size(); i++) {
                OrderItem oi = o.getItems().get(i);
                if (oi.getId().equals(item.getId())) {
                    o.getItems().remove(i);
                    break;
                }
            }
        }
        for (Order o : orders) {
            if (o.getItems().isEmpty()) {
                orders.remove(o);
            }
        }
    }

    /**
     * Update giỏ hàng, truyền vào mã order của giỏ hàng và mã của order item +
     * số lượng, k phải mã item nhé
     *
     * @param items
     */
    public void updateCart(List<OrderItem> items) {
        List<Order> orders = viewer.getCart();
        for (Order o : orders) {
            if (!o.getItems().isEmpty()) {
                for (OrderItem oi : o.getItems()) {
                    for (OrderItem i : items) {
                        if (oi.getId().equals(i.getId())) {
                            oi.setQuantity(i.getQuantity() > 0 ? i.getQuantity() : 1);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Lấy giỏ hàng
     *
     * @return
     */
    public List<Order> getCart() {
        return viewer.getCart();
    }

    /**
     * get by id
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    public Order get(String id) throws Exception {
        Order order = orderRepository.find(id);
        if (order == null) {
            throw new Exception("Không tìm thấy đơn hàng yêu cầu");
        }
        return order;
    }

    /**
     * Lấy ra toàn bộ thông tin order, sản phẩm đã lấy id ảnh.
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Order getOrder(String id) throws Exception {
        Order order = orderRepository.find(id);
        if (order == null) {
            throw new Exception("Không tìm thấy đơn hàng yêu cầu");
        }
        List<OrderItem> orderItems = orderItemRepository.getByOrderId(id);
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        List<String> itemIds = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            itemIds.add(orderItem.getItemId());
        }
        Map<String, List<String>> images = imageService.get(ImageType.ITEM, itemIds);
        for (Map.Entry<String, List<String>> entry : images.entrySet()) {
            String orderItemId = entry.getKey();
            List<String> img = entry.getValue();
            try {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getItemId().equals(orderItemId)) {
                        orderItem.setImages(img);
                    }
                }
            } catch (Exception e) {
            }
        }
        User user = userService.get(order.getSellerId());
        List<String> is = imageService.get(ImageType.AVATAR, user.getId());
        if (is != null && !is.isEmpty() && is.size() > 0) {
            user.setAvatar(is.get(0));
        }
        SellerReview sellerReview = sellerReviewService.getByOrderId(id);
        if (sellerReview != null) {
            order.setSellerReview(sellerReview);
        }
        order.setUser(user);
        order.setItems(orderItems);
        return order;
    }

    /**
     * Tính toán đơn hàng
     *
     * @param order
     * @param all
     * @param weightall
     * @param priceall
     * @param sellerCityId
     * @param sellerDistrictId
     * @param protec
     * @return
     * @throws Exception
     */
    public Order calculator(Order order, boolean all, int weightall, long priceall,
            String sellerCityId, String sellerDistrictId, boolean protec) throws Exception {

        long price = 0;
        int weight = 0;
        long shipmentPrice = 0;
        long shipPrice = 0;
        int weightPrice = 0;
        List<String> ids = new ArrayList<>();
        for (OrderItem orderItem : order.getItems()) {
            ids.add(orderItem.getItemId());
        }
        List<Item> items = itemRepository.get(ids);
        for (OrderItem orderItem : order.getItems()) {
            for (Item item : items) {
                if (item.getId().equals(orderItem.getItemId())) {
                    if (orderItem.getQuantity() > item.getQuantity()) {
                        orderItem.setQuantity(orderItem.getQuantity());
                    }
                    break;
                }
            }
        }

        for (OrderItem orderItem : order.getItems()) {
            ids.add(orderItem.getItemId());
            price += orderItem.getQuantity() * orderItem.getItemPrice();
            weight += orderItem.getQuantity() * orderItem.getWeight();
            if (orderItem.getShipmentType() == ShipmentType.FIXED) {
                shipmentPrice += orderItem.getShipmentPrice() > 0 ? orderItem.getQuantity() * orderItem.getShipmentPrice() : 0;
            }
            if (orderItem.getShipmentType() == ShipmentType.BYWEIGHT) {
                shipPrice += orderItem.getQuantity() * orderItem.getItemPrice();
                weightPrice += orderItem.getQuantity() * orderItem.getWeight();
            }
        }
        if (order.getCouponId() != null && !order.getCouponId().equals("")) {
            Response<Coupon> validate = couponService.validate(order.getCouponId(), order.getSellerId());
            if (!validate.isSuccess()) {
                throw new Exception(validate.getMessage());
            }
            Coupon coupon = validate.getData();
            if (price < coupon.getMinOrderValue()) {
                throw new Exception("Giá trị đơn hàng không đủ để áp dụng mã khuyến mại này");
            }
            order.setCouponPrice(coupon.getDiscountPrice() > 0 ? coupon.getDiscountPrice() : (price * coupon.getDiscountPercent() / 100));
        }

        Seller seller = sellerService.getById(order.getSellerId());
        User user = userService.get(order.getSellerId());
        if (all) {
            shipPrice = price;
            weightPrice = weight;
            user.setDistrictId(sellerDistrictId);
            user.setCityId(sellerCityId);
        }
        if (weightall > 0) {
            weightPrice = weightall;
        }
        if (priceall > 0) {
            shipPrice = priceall;
        }
        if (seller.getScEmail() != null && !seller.getScEmail().equals("") && shipPrice > 0 && weightPrice > 0) {
            List<String> local = new ArrayList<>();
            local.add(user.getCityId());
            local.add(order.getReceiverCityId());
            List<City> citys = cityService.getCityByIds(local);
            String rCityId = "";
            String rDistrictId = "";
            for (City city : citys) {
                if (city.getId().equals(user.getCityId())) {
                    user.setCityId(city.getScId());
                    District district = districtService.get(user.getDistrictId());
                    if (district != null) {
                        user.setDistrictId(district.getScId());
                    }
                }
                if (order.getReceiverCityId().equals(city.getId())) {
                    rCityId = city.getScId();
                    District district = districtService.get(order.getReceiverDistrictId());
                    if (district != null) {
                        rDistrictId = district.getScId();
                    }
                }
            }
            long hprice = 0;
            ScClient.FeeShip caculateFee = scClient.caculateFee(seller.getScEmail(),
                    order.getShipmentService(),
                    user.getCityId(), user.getDistrictId(),
                    rCityId, rDistrictId,
                    shipPrice, weightPrice, order.getPaymentMethod() == PaymentMethod.COD, protec);
            try {
                hprice += caculateFee.getShip();
            } catch (Exception e) {
            }
            try {
                order.setShipmentPCod(caculateFee.getPcod());
            } catch (Exception e) {
            }

            //Chương trình khuyến mại
            /**
             * @T1. Giảm toàn bộ phí vận chuyển khi thanh toán ngân lượng linh
             * hoạt theo sc cho đơn hàng klg < 2kg
             * @T2. Giảm tối đa 50k khi thanh toán qua visa cho đơn hàng ngành
             * hàng thời trang
             */
            if (priceall == 0 || (priceall > 0 && order.getPaymentStatus() == PaymentStatus.PAID)) {
                //Tính phí (all = false)
                if (weightPrice <= 2000 && (order.getPaymentMethod() != null && order.getPaymentMethod() != PaymentMethod.NONE && order.getPaymentMethod() != PaymentMethod.COD)) {
                    order.setCdtDiscountShipment(hprice);
                } else {
//                    //chương trình khuyễn mãi 50k phí vận chuyển khi thanh toán qua visa cho ngành hàng thời trang
//                    for (OrderItem orderItem : order.getItems()) {
//                        if (order.getPaymentMethod() == PaymentMethod.VISA && orderItem.getCategoryPath().contains("2924")) {
//                            if (hprice > 0 && hprice <= 50000) {
//                                order.setCdtDiscountShipment(hprice);
//                            } else if (hprice - 50000 > 0) {
//                                order.setCdtDiscountShipment(50000);
//                            }
//                            break;
//                        }
//                    }
                    order.setCdtDiscountShipment(0);
                }
            } else {
                order.setCdtDiscountShipment(0);
            }

            shipmentPrice += hprice;
            if (all) {
                shipmentPrice = caculateFee.getShip();
            }
        }
        order.setShipmentPrice(shipmentPrice);
        order.setTotalPrice(price);
        order.setFinalPrice(order.getTotalPrice() - order.getCouponPrice() + order.getShipmentPrice() - order.getCdtDiscountShipment());
        viewer.setInvoice(order);
        return order;
    }

    /**
     * Tạo đơn hàng
     *
     * @param order
     * @return
     * @throws Exception
     */
    public Response createOrder(Order order) throws Exception {
        Map<String, String> error = validator.validate(order);
        if (order.getSellerId() == null || order.getSellerId().equals("")) {
            throw new Exception("Mã người bán không được để trống");
        }
        if (order.getBuyerCityId() == null || order.getBuyerCityId().equals("0")) {
            error.put("buyerCityId", "Địa chỉ tỉnh,thành phố người mua không được để trống");
        }
        if (order.getBuyerDistrictId() == null || order.getBuyerDistrictId().equals("0")) {
            error.put("buyerDistrictId", "Địa chỉ quận,huyện người mua không được để trống");
        }
        if (order.getReceiverCityId() == null || order.getReceiverCityId().equals("0")) {
            error.put("receiverCityId", "Địa chỉ tỉnh,thành phố người nhận không được để trống");
        }
        if (order.getReceiverDistrictId() == null || order.getReceiverDistrictId().equals("0")) {
            error.put("receiverDistrictId", "Địa chỉ quận,huyện người nhận không được để trống");
        }

        if (!error.isEmpty()) {
            return new Response(false, "Thông tin đơn hàng chưa chính xác", error);
        }
        if (order.getPaymentMethod() == null) {
            order.setPaymentMethod(PaymentMethod.NONE);
        }
        List<OrderItem> orderItems = order.getItems();
        List<String> itemIds = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            itemIds.add(orderItem.getItemId());
        }
        List<Item> items = itemRepository.get(itemIds);
        List<OrderItem> oItems = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            for (Item item : items) {
                if (orderItem.getItemId().equals(item.getId())) {
                    orderItem.setOrderId(order.getId());
                    orderItem.setItemPrice(this.getItemPrice(item));
                    orderItem.setItemStartPrice(item.getSellPrice());
                    orderItem.setCategoryPath(item.getCategoryPath());
                    orderItem.setDiscountPercent(item.getDiscountPercent());
                    orderItem.setDiscountPrice(item.getDiscountPrice());
                    orderItem.setGiftDetail(item.getGiftDetail());
                    orderItem.setItemName(item.getName());
                    orderItem.setShipmentPrice(item.getShipmentPrice());
                    orderItem.setShipmentType(item.getShipmentType());
                    orderItem.setWeight(item.getWeight());
                    oItems.add(orderItem);
                }
            }
        }
        order.setItems(oItems);
        order = calculator(order, false, 0, 0, null, null, false);
        order.setCreateTime(System.currentTimeMillis());
        order.setUpdateTime(System.currentTimeMillis());
        order.setPaymentStatus(PaymentStatus.NEW);
        orderRepository.save(order);
        for (OrderItem orderItem : oItems) {
            orderItemRepository.save(orderItem);
        }
        sellerHistoryService.create(SellerHistoryType.ORDER, order.getId(), true, 0, order.getSellerId());
        customerService.addCustomer(order);
//        sellerReviewService.createDefaultReview(order);
        realTimeService.add("Đơn hàng " + order.getId() + " vừa được đặt hàng", order.getSellerId(), "/" + order.getId() + "/chi-tiet-don-hang.html", "Chi tiết đơn hàng", null);
        return new Response(true, "Đơn đặt hàng của bạn đã được chuyển đến người bán, người bán sẽ liên hệ với bạn trong trong thời gian sớm nhất", error);
    }

    public long getItemPrice(Item item) {
        long price = item.getSellPrice();
        try {
            if (item.getListingType() == ListingType.BUYNOW) {
                if (item.isDiscount()) {
                    if (item.getDiscountPrice() > 0 && price - item.getDiscountPrice() > 0) {
                        price = price - item.getDiscountPrice();
                    } else if (item.getDiscountPercent() <= 100) {
                        price = price * (100 - item.getDiscountPercent()) / 100;
                        price = price * 1000;
                        price = (long) Math.ceil(price);
                        price = price / 1000;
                    }
                }
            } else {
                if (item.getEndTime() < System.currentTimeMillis()) {
                    if (item.getSellPrice() > 0) {
                        price = item.getSellPrice();
                    } else {
                        throw new Exception("Sản phẩm đấu giá không có giá mua ngay");
                    }
                } else if (item.getHighestBider().equals(viewer.getUser().getId())) {
                    price = item.getHighestBid();
                } else {
                    throw new Exception("Bạn không phải người đấu giá thắng của sản phẩm này");
                }
            }
        } catch (Exception e) {
        }

        return price;
    }

    public String payment(Order order, Seller seller, String baseUrl) throws Exception {
        if (!seller.isNlIntegrated()) {
            throw new Exception("Tài khoản của người bán chưa tích hợp NL");
        }
        if (seller.getNlEmail() == null || seller.getNlEmail().equals("")) {
            throw new Exception("Hệ thống không tìm thấy tài khoản ngân lượng của người bán, bạn hãy chọn hình thức thanh toán khác");
        }
        NlClient.MakePaymentRequest request = nlClient.new MakePaymentRequest();
        request.setBankCode(order.getPaymentMethod().toString());
        request.setBuyerAddress(order.getBuyerAddress());
        request.setBuyerEmail(order.getBuyerEmail());
        request.setBuyerName(order.getBuyerName());
        request.setBuyerPhone(order.getBuyerPhone());
        request.setCancelUrl(baseUrl + "/" + order.getId() + "/paymentcallback.html?cancel=true");
        request.setDiscountAmount(order.getCouponPrice());
        request.setItems(new ArrayList<NlClient.OrderItem>());
        long price = 0;
        for (OrderItem orderItem : order.getItems()) {
            price += orderItem.getItemPrice() * orderItem.getQuantity();
            request.getItems().add(nlClient.new OrderItem(UrlUtils.item(orderItem.getItemId(), orderItem.getItemName()), orderItem.getItemName(), orderItem.getQuantity(), orderItem.getItemPrice()));
        }
        request.setOrderDesctiption("Đơn hàng từ hệ thống Chợ điện tử");
        request.setOrderId(order.getId());
        request.setPaymentMethod("ATM_ONLINE");
        if (order.getPaymentMethod() == PaymentMethod.NL) {
            request.setPaymentMethod("NL");
        }
        if (order.getPaymentMethod() == PaymentMethod.VISA) {
            request.setPaymentMethod("VISA");
        }
        if (order.getPaymentMethod() == PaymentMethod.MASTER) {
            request.setPaymentMethod("VISA");
        }
        request.setReceiverEmail(seller.getNlEmail());
        request.setReturnUrl(baseUrl + "/" + order.getId() + "/paymentcallback.html");
        request.setShippingFee(order.getShipmentPrice() - order.getCdtDiscountShipment());
        request.setTotalAmount(price);
        NlClient.MakePaymentResponse makePayment = nlClient.makePayment(request);
        if (!makePayment.isSuccess()) {
            return baseUrl + "/" + order.getId() + "/don-hang-thanh-cong.html";
        }
        return makePayment.getCheckoutUrl();
    }

    /**
     * Thanh toán
     *
     * @param orderId
     * @param token
     * @return
     * @throws Exception
     */
    public Order payment(String orderId, String token) throws Exception {
        Order order = this.get(orderId);
        order.setItems(orderItemRepository.getByOrderId(orderId));
        if (order.getPaymentStatus() == PaymentStatus.PAID) {
            return order;
        }
        NlClient.CheckPaymentResponse checkPayment = nlClient.checkPayment(token);
        order.setNlId(checkPayment.getTransactionId());
        order.setPaymentStatus(PaymentStatus.PAID);
        order.setMarkBuyerPayment(PaymentStatus.PAID);
        order.setMarkSellerPayment(PaymentStatus.PAID);
        order.setPaidTime(System.currentTimeMillis());
        order.setUpdateTime(System.currentTimeMillis());
        orderRepository.save(order);

        List<OrderItem> oItems = orderItemRepository.getByOrderId(orderId);
        if (oItems != null && !oItems.isEmpty()) {
            List<String> itemIds = new ArrayList<>();
            for (OrderItem orderItem : oItems) {
                itemIds.add(orderItem.getItemId());
            }
            List<Item> items = itemRepository.get(itemIds);
            for (Item item : items) {
                for (OrderItem orderItem : oItems) {
                    if (orderItem.getItemId().equals(item.getId())) {
                        if (item.getListingType() == ListingType.AUCTION) {
                            try {
                                auctionService.endBidByPayment(item.getId());
                            } catch (Exception e) {
                            }
                        } else {
                            item.setSoldQuantity(item.getSoldQuantity() + orderItem.getQuantity());
                            item.setQuantity(item.getQuantity() - orderItem.getQuantity());
                            if (item.getQuantity() < 0) {
                                item.setQuantity(0);
                            }
                        }
                        item.setUpdateTime(System.currentTimeMillis());
                        itemRepository.save(item);
                    }
                }
            }
            indexService.processIndexPageItem(items);
        }
        customerService.addCustomer(order);

        realTimeService.add("Đơn hàng " + order.getId() + " vừa được thanh toán", order.getSellerId(), "/" + order.getId() + "/chi-tiet-don-hang.html", "Chi tiết đơn hàng", null);
        sellerHistoryService.create(SellerHistoryType.PAYMENT, order.getId(), true, 0, null);
        try {
            cashService.reward(CashTransactionType.PAYMENT_SUSSESS_NL, viewer.getUser().getId(), order.getId(), "/" + order.getId() + "/chi-tiet-don-hang.html", null);
        } catch (Exception e) {
        }
        return order;
    }

    /**
     * Tìm kiếm danh sách đơn hàng
     *
     * @param search
     * @return
     */
    public DataPage<Order> search(OrderSearch search) {
        Criteria cri = this.buildCriteria(search);
        Sort sort;
        switch (search.getSortOrderBy()) {
            case 1:
                sort = new Sort(Sort.Direction.ASC, "createTime");
                break;
            case 2:
                sort = new Sort(Sort.Direction.DESC, "createTime");
                break;
            case 3:
                sort = new Sort(Sort.Direction.ASC, "shipmentCreateTime");
                cri.and("scId").ne(null);
                break;
            case 4:
                sort = new Sort(Sort.Direction.DESC, "shipmentCreateTime");
                cri.and("scId").ne(null);
                break;
            default:
                sort = new Sort(Sort.Direction.DESC, "updateTime");
                break;
        }
        Query query = new Query(cri);
        DataPage<Order> dataPage = new DataPage<>();
        dataPage.setDataCount(orderRepository.count(query));
        dataPage.setPageIndex(search.getPageIndex());
        dataPage.setPageSize(search.getPageSize());
        dataPage.setPageCount(dataPage.getDataCount() / search.getPageSize());
        if (dataPage.getDataCount() % search.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        List<Order> list = orderRepository.find(query.limit(search.getPageSize()).skip(search.getPageIndex() * search.getPageSize()).with(sort));
        for (Order order : list) {
            List<OrderItem> orderItems = orderItemRepository.getByOrderId(order.getId());
            if (orderItems == null) {
                orderItems = new ArrayList<>();
            }
            List<String> itemIds = new ArrayList<>();
            for (OrderItem orderItem : orderItems) {
                itemIds.add(orderItem.getItemId());
            }
            order.setItems(orderItems);
            Map<String, List<String>> images = imageService.get(ImageType.ITEM, itemIds);
            for (Map.Entry<String, List<String>> entry : images.entrySet()) {
                String orderItemId = entry.getKey();
                List<String> img = entry.getValue();
                for (OrderItem orderItem : order.getItems()) {
                    if (orderItem.getItemId().equals(orderItemId)) {
                        orderItem.setImages(img);
                    }
                }

            }

        }
        dataPage.setData(list);
        return dataPage;
    }

    public Criteria buildCriteria(OrderSearch search) {
        Criteria cri = new Criteria();
        List<String> userIds = new ArrayList<>();
        if (search.getSellerEmail() != null && !search.getSellerEmail().equals("")) {
            List<User> users = userRepository.find(new Query(new Criteria("email").regex(search.getSellerEmail(), "i")));
            for (User user : users) {
                userIds.add(user.getId());
            }
        }
        if (search.getSellerId() != null && !search.getSellerId().equals("")) {
            userIds.add(search.getSellerId());
        }
        if (search.getSellerPhone() != null && !search.getSellerPhone().equals("")) {
            List<User> users = userRepository.find(new Query(new Criteria("phone").is(search.getSellerPhone())));
            for (User user : users) {
                if (!userIds.contains(user.getId())) {
                    userIds.add(user.getId());
                }
            }
        }
        if (search.getSellerCityId() != null && !search.getSellerCityId().equals("0")) {
            Criteria criteria = new Criteria();
            List<String> userIdCheck = new ArrayList<>();
            List<Order> orders = orderRepository.find(new Query(new Criteria()));
            for (Order order : orders) {
                if (!userIdCheck.contains(order.getSellerId())) {
                    userIdCheck.add(order.getSellerId());
                }
            }
            criteria.and("cityId").is(search.getSellerCityId());
            if (search.getSellerDistrictId() != null && !search.getSellerDistrictId().equals("0")) {
                criteria.and("districtId").is(search.getSellerDistrictId());
            }
            criteria.and("_id").in(userIdCheck);
            List<User> users = userRepository.find(new Query(criteria));
            for (User user : users) {
                if (!userIds.contains(user.getId())) {
                    userIds.add(user.getId());
                }
            }
        }
        if (search.getReceiverCityId() != null && !search.getReceiverCityId().equals("0")) {
            cri.and("receiverCityId").is(search.getReceiverCityId());
            if (search.getReceiverDistrictId() != null && !search.getReceiverDistrictId().equals("0")) {
                cri.and("receiverDistrictId").is(search.getReceiverDistrictId());

            }
        }
        if (search.getBuyerCityId() != null && !search.getBuyerCityId().equals("0")) {
            cri.and("buyerCityId").is(search.getBuyerCityId());
            if (search.getBuyerDistrictId() != null && !search.getBuyerDistrictId().equals("0")) {
                cri.and("buyerDistrictId").is(search.getBuyerDistrictId());

            }
        }
        if (userIds != null && !userIds.isEmpty()) {
            cri.and("sellerId").in(userIds);
        }
        if (search.getBuyerId() != null && !search.getBuyerId().equals("")) {
            cri.and("buyerId").is(search.getBuyerId());
        }
        if (search.getItemId() != null && !search.getItemId().equals("")) {
            Criteria criOi = new Criteria();
            criOi.and("itemId").is(search.getItemId());

            List<OrderItem> orderItems = orderItemRepository.find(new Query(criOi));
            List<String> ids = new ArrayList<>();
            if (orderItems != null && !orderItems.isEmpty()) {
                for (OrderItem orderItem : orderItems) {
                    ids.add(orderItem.getOrderId());
                }
            }
            cri.and("id").in(ids);
        }
        if (search.getId() != null && !search.getId().equals("")) {
            cri.and("id").is(search.getId());
        } else if (search.getKeyword() != null && !search.getKeyword().equals("")) {
            Criteria criOi = new Criteria();
            criOi.and("itemName").regex(".*" + search.getKeyword().trim() + ".*", "i");

            List<OrderItem> orderItems = orderItemRepository.find(new Query(criOi));
            List<String> ids = new ArrayList<>();
            ids.add(search.getKeyword().trim());
            if (orderItems != null && !orderItems.isEmpty()) {
                for (OrderItem orderItem : orderItems) {
                    ids.add(orderItem.getOrderId());
                }
            }
            cri.and("id").in(ids);
        }
        if (search.getPaymentStatus() != null) {
            if (search.getPaymentStatus() != PaymentStatus.PAID) {
                if (search.getSellerId() != null && !search.getSellerId().equals("")) {
                    cri.and("markSellerPayment").ne(PaymentStatus.PAID.toString());
                }
                if (search.getBuyerId() != null && !search.getBuyerId().equals("")) {
                    cri.and("markBuyerPayment").ne(PaymentStatus.PAID.toString());
                }
            } else {
                if (search.getSellerId() != null && !search.getSellerId().equals("")) {
                    cri.and("markSellerPayment").is(PaymentStatus.PAID.toString());
                }
                if (search.getBuyerId() != null && !search.getBuyerId().equals("")) {
                    cri.and("markBuyerPayment").is(PaymentStatus.PAID.toString());
                }
            }
        }
        if (search.getShipmentStatus() != null) {
            if (search.getSellerId() != null && !search.getSellerId().equals("")) {
                if (search.getShipmentStatus() != ShipmentStatus.DELIVERED) {
                    cri.and("markSellerShipment").ne(ShipmentStatus.DELIVERED.toString());
                } else {
                    cri.and("markSellerShipment").is(ShipmentStatus.DELIVERED.toString());
                }
            }
            if (search.getBuyerId() != null && !search.getBuyerId().equals("")) {
                if (search.getShipmentStatus() != ShipmentStatus.DELIVERED) {
                    cri.and("markBuyerShipment").ne(ShipmentStatus.DELIVERED.toString());
                } else {
                    cri.and("markBuyerShipment").is(ShipmentStatus.DELIVERED.toString());
                }
            }
        }
        if (search.getRemove() > 0) {
            cri.and("remove").is(search.getRemove() == 1);
        } else if (search.getRemove() == 0) {

        } else {
            cri.and("remove").is(false);
        }
        if (search.getCreateTimeFrom() > 0 && search.getCreateTimeTo() > 0) {
            cri.and("createTime").gte(search.getCreateTimeFrom()).lt(search.getCreateTimeTo());
        }
        if (search.getShipmentCreateTimeFrom() > 0 || search.getShipmentCreateTimeTo() > 0) {
            if (search.getShipmentCreateTimeFrom() > 0 && search.getShipmentCreateTimeTo() > search.getShipmentCreateTimeFrom()) {
                cri.and("shipmentCreateTime").lte(search.getShipmentCreateTimeTo()).gte(search.getShipmentCreateTimeFrom());
            } else if (search.getShipmentCreateTimeFrom() > 0) {
                cri.and("shipmentCreateTime").gte(search.getShipmentCreateTimeFrom());
            } else {
                cri.and("shipmentCreateTime").lte(search.getShipmentCreateTimeTo());
            }
        }
        if (search.getScId() != null && !search.getScId().equals("")) {
            cri.and("scId").is(search.getScId());
        }
        if (search.getNlId() != null && !search.getNlId().equals("")) {
            cri.and("nlId").is(search.getNlId());
        }
        if (search.getPaidTimeFrom() > 0 && search.getPaidTimeTo() > 0) {
            cri.and("paidTime").gte(search.getPaidTimeFrom()).lt(search.getPaidTimeTo());
        }
        if (search.getReceiverEmail() != null && !search.getReceiverEmail().equals("")) {
            cri.and("receiverEmail").is(search.getReceiverEmail());
        }
        if (search.getReceiverPhone() != null && !search.getReceiverPhone().equals("")) {
            cri.and("receiverPhone").is(search.getReceiverPhone());
        }
        if (search.getReceiverName() != null && !search.getReceiverName().equals("")) {
            cri.and("receiverName").is(search.getReceiverName());
        }
        if (search.getBuyEmail() != null && !search.getBuyEmail().equals("")) {
            cri.and("buyerEmail").is(search.getBuyEmail());
        }
        if (search.getBuyPhone() != null && !search.getBuyPhone().equals("")) {
            cri.and("buyerPhone").is(search.getBuyPhone());
        }
        if (search.getBuyName() != null && !search.getBuyName().equals("")) {
            cri.and("buyerName").regex(search.getBuyName());
        }
        if (search.getPaymentMethod() > 0) {
            switch (search.getPaymentMethod()) {
                case 1:
                    cri.and("paymentMethod").is(PaymentMethod.COD.toString());
                    break;
                case 2:
                    cri.and("paymentMethod").is(PaymentMethod.NL.toString());
                    break;
                default:
                    cri.and("paymentMethod").is(PaymentMethod.NONE.toString());
                    break;
            }
        }
        if (search.getPaymentStatusSearch() > 0) {
            switch (search.getPaymentStatusSearch()) {
                case 1:
                    cri.and("paymentStatus").ne(PaymentStatus.PAID.toString());
                    break;
                case 2:
                    cri.and("paymentStatus").is(PaymentStatus.PAID.toString());
                    break;
                case 3:
                    cri.and("paymentStatus").is(PaymentStatus.PENDING.toString());
                    break;
                default:

            }
        }
        if (search.getShipmentStatusSearch() > 0) {
            switch (search.getShipmentStatusSearch()) {
                case 1:
                    cri.and("shipmentStatus").is(ShipmentStatus.NEW.toString());
                    break;
                case 2:
                    cri.and("shipmentStatus").is(ShipmentStatus.STOCKING.toString());
                    break;
                case 3:
                    cri.and("shipmentStatus").is(ShipmentStatus.DELIVERING.toString());
                    break;
                case 4:
                    cri.and("shipmentStatus").is(ShipmentStatus.RETURN.toString());
                    break;
                case 5:
                    cri.and("shipmentStatus").is(ShipmentStatus.DENIED.toString());
                    break;
                case 6:
                    cri.and("shipmentStatus").is(ShipmentStatus.DELIVERED.toString());
                    break;
                default:

            }
        }
        if (search.getRefundStatus() > 0) {
            switch (search.getRefundStatus()) {
                case 1:
                    cri.and("refundStatus").is(false);
                    break;
                case 2:
                    cri.and("refundStatus").is(true);
                    break;
                default:

            }
        }
        return cri;
    }

    /**
     * Xóa hoặc hoàn tác đơn hàng
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    public Response remove(String orderId) throws Exception {
        Order order = this.get(orderId);
        this.authen(order);
        order.setRemove(!order.isRemove());
        order.setUpdateTime(System.currentTimeMillis());
        orderRepository.save(order);
        return new Response(true, "Đơn hàng đã được " + (order.isRemove() ? "đưa vào thùng rác" : "hoàn tác"));
    }

    /**
     * Đánh dáu hoàn tiền
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    public Response refund(String orderId) throws Exception {
        Order order = this.get(orderId);
        this.authen(order);
        order.setRefundStatus(!order.isRefundStatus());
        order.setUpdateTime(System.currentTimeMillis());
        orderRepository.save(order);
        return new Response(true, "Đơn hàng đã được " + (order.isRefundStatus() ? " chuyển sang trạng thái hoàn tiền" : "hoàn tác"));
    }

    public Response refundAPI(String orderId) throws Exception {
        Order order = this.get(orderId);
        order.setRefundStatus(!order.isRefundStatus());
        order.setUpdateTime(System.currentTimeMillis());
        orderRepository.save(order);
        return new Response(true, "Đơn hàng đã được " + (order.isRefundStatus() ? " chuyển sang trạng thái hoàn tiền" : "hoàn tác"));
    }

    private void authen(Order order) throws Exception {
        if (viewer.getUser() == null) {
            throw new Exception("Bạn cần đăng nhập để thực hiện thao tác này");
        }
        if (viewer.getAdministrator() == null && !viewer.getUser().getId().equals(order.getBuyerId()) && !viewer.getUser().getId().equals(order.getSellerId())) {
            throw new Exception("Bạn không có quyền thực hiện thao tác này");
        }
    }

    /**
     * Cập nhật ghi chú cho người bắn hoặc người mua
     *
     * @param orderId
     * @param note
     * @param seller
     * @return
     * @throws Exception
     */
    public Response note(String orderId, String note, boolean seller) throws Exception {
        Order order = this.get(orderId);
        this.authen(order);
        note = note.trim();
        if (seller) {
            order.setSellerNote(note);
        } else {
            order.setNote(note);
        }
        order.setUpdateTime(System.currentTimeMillis());
        orderRepository.save(order);
        return new Response(true, "Ghi chú đã được cập nhật vào đơn hàng", order);
    }

    /**
     * Đánh dấu trạng thái thanh toán
     *
     * @param orderId
     * @param status
     * @param seller
     * @return
     * @throws Exception
     */
    public Response markPaymentStatus(String orderId, PaymentStatus status, boolean seller) throws Exception {
        Order order = this.get(orderId);
        this.authen(order);
        if (seller) {
            order.setMarkSellerPayment(status);
        } else {
            order.setMarkBuyerPayment(status);
        }
        order.setUpdateTime(System.currentTimeMillis());
        orderRepository.save(order);
        return new Response(true, "Trạng thái thanh toán đã được cập nhật vào đơn hàng");
    }

    public Response markPaymentStatusAPI(String orderId, PaymentStatus status, boolean seller) throws Exception {
        Order order = this.get(orderId);
        if (seller) {
            order.setMarkSellerPayment(status);
        } else {
            order.setMarkBuyerPayment(status);
        }
        order.setUpdateTime(System.currentTimeMillis());
        orderRepository.save(order);
        return new Response(true, "Trạng thái thanh toán đã được cập nhật vào đơn hàng");
    }

    /**
     * Dánh dấu trạng thái vận chuyển
     *
     * @param orderId
     * @param status
     * @param seller
     * @return
     * @throws Exception
     */
    public Response markShipmentStatus(String orderId, ShipmentStatus status, boolean seller) throws Exception {
        Order order = this.get(orderId);
        this.authen(order);
        if (seller) {
            order.setMarkSellerShipment(status);
        } else {
            order.setMarkBuyerShipment(status);
        }
        order.setUpdateTime(System.currentTimeMillis());
        orderRepository.save(order);
        return new Response(true, "Trạng thái vận chuyển đã được cập nhật vào đơn hàng");
    }

    public Response markShipmentStatusAPI(String orderId, ShipmentStatus status, boolean seller) throws Exception {
        Order order = this.get(orderId);
        if (seller) {
            order.setMarkSellerShipment(status);
        } else {
            order.setMarkBuyerShipment(status);
        }
        order.setUpdateTime(System.currentTimeMillis());
        orderRepository.save(order);
        return new Response(true, "Trạng thái vận chuyển đã được cập nhật vào đơn hàng");
    }

    public Response sendMessage(String orderId, String message, boolean seller) throws Exception {
        return new Response(false, "Chức năng đang trong quá trình hoàn thành");
    }

    public Order clone(Order order) {
        try {
            Order nOrder = order.clone();
            nOrder.setId(orderRepository.genId());
            for (OrderItem orderItem : nOrder.getItems()) {
                orderItem.setId(orderItemRepository.genId());
            }
            return nOrder;
        } catch (CloneNotSupportedException ex) {
            return order;
        }
    }

    public Response createLading(Lading form) throws Exception {

        Seller seller = sellerService.getById(viewer.getUser().getId());
        if (!seller.isScIntegrated()) {
            throw new Exception("Tài khoản của bạn chưa được tích hợp hình thức vận chuyển qua shipchung");
        }
        if (seller.getScEmail() == null || seller.getScEmail().equals("")) {
            throw new Exception("Tài khoản email tích hợp vận chuyển qua ship chung không tồn tại");
        }
        Order order = this.getOrder(form.getOrderId());
        if (order.getScId() != null && !order.getScId().equals("")) {
            throw new Exception("Đơn hàng của bạn đã được tạo vận đơn trên shipchung, mã vận đơn : " + order.getScId());
        }
        this.authen(order);

        Map<String, String> error = validator.validate(form);
        if (form.getSellerCityId() == null || form.getSellerCityId().equals("0")) {
            error.put("sellerCityId", "Địa chỉ tỉnh,thành phố người bán không được để trống");
        }
        if (form.getSellerDistrictId() == null || form.getSellerDistrictId().equals("0")) {
            error.put("sellerDistrictId", "Địa chỉ quận,huyện người bán không được để trống");
        }
        if (form.getReceiverCityId() == null || form.getReceiverCityId().equals("0")) {
            error.put("receiverCityId", "Địa chỉ tỉnh,thành phố người nhận không được để trống");
        }
        if (form.getReceiverDistrictId() == null || form.getReceiverDistrictId().equals("0")) {
            error.put("receiverDistrictId", "Địa chỉ quận,huyện người nhận không được để trống");
        }
        if (!error.isEmpty()) {
            return new Response(false, "Thông tin chưa đầy đủ", error);
        }

        order.setReceiverAddress(form.getReceiverAddress());
        order.setReceiverCityId(form.getReceiverCityId());
        order.setReceiverDistrictId(form.getReceiverDistrictId());
        order.setReceiverEmail(form.getReceiverEmail());
        order.setReceiverName(form.getReceiverName());
        order.setReceiverPhone(form.getReceiverPhone());
        order.setCodPrice(form.getCodPrice());

        List<String> local = new ArrayList<>();
        local.add(form.getSellerCityId());
        local.add(form.getReceiverCityId());
        List<City> citys = cityService.getCityByIds(local);
        for (City city : citys) {
            if (city.getId().equals(form.getSellerCityId())) {
                form.setSellerCityId(city.getScId());
                District district = districtService.get(form.getSellerDistrictId());
                if (district != null) {
                    form.setSellerDistrictId(district.getScId());
                }
            }
            if (form.getReceiverCityId().equals(city.getId())) {
                form.setReceiverCityId(city.getScId());
                District district = districtService.get(form.getReceiverDistrictId());
                if (district != null) {
                    form.setReceiverDistrictId(district.getScId());
                }
            }
        }

        Response<ScClient.FeeShip> createShipment = scClient.createShipment(seller.getScEmail(), seller.getScId(),
                form.getShipmentService(),
                form.getSellerName(), viewer.getUser().getEmail(),
                form.getSellerPhone(), form.getSellerAddress(),
                form.getSellerCityId(), form.getSellerDistrictId(),
                form.getReceiverName(), form.getReceiverEmail(),
                form.getReceiverPhone(), form.getReceiverAddress(),
                form.getReceiverCityId(), form.getReceiverDistrictId(),
                form.getName(), form.getDescription(),
                order.getTotalPrice(),
                order.getCodPrice(),
                (order.getCdtDiscountShipment() > 0 ? order.getShipmentPrice() - order.getCdtDiscountShipment() : -1),
                form.getWeight(), form.getType() == PaymentMethod.COD, form.isProtec());
        if (!createShipment.isSuccess()) {
            return createShipment;
        }
        try {
            form.setShipmentStatus(ShipmentStatus.NEW);
            order.setMarkBuyerShipment(ShipmentStatus.NEW);
            order.setMarkSellerShipment(ShipmentStatus.NEW);
            form.setScId(createShipment.getMessage());
            form.setShipmentPrice(createShipment.getData().getShip());
            form.setShipmentPCod(createShipment.getData().getPcod());
            form.setCreateTime(System.currentTimeMillis());
            form.setUpdateTime(System.currentTimeMillis());
            ladingRepository.save(form);

            if (form.getType() == PaymentMethod.COD) {
                order.setPaymentMethod(form.getType());
            }
            order.setShipmentCreateTime(System.currentTimeMillis());
            order.setShipmentUpdateTime(System.currentTimeMillis());
            order.setShipmentService(form.getShipmentService());
            order.setScId(createShipment.getMessage());
            order.setShipmentStatus(ShipmentStatus.NEW);
//            order.setShipmentPrice(createShipment.getData().getShip());
            order.setShipmentPCod(createShipment.getData().getPcod());
        } catch (Exception e) {
        }
        orderRepository.save(order);
        sellerHistoryService.create(SellerHistoryType.LADING, order.getId(), true, 0, viewer.getUser().getId());
        try {
            cashService.reward(CashTransactionType.BROWSE_LADING, viewer.getUser().getId(), order.getId(), "/" + order.getId() + "/chi-tiet-don-hang.html", null);
            return new Response(true, "Vận đơn COD đã được gửi sang shipchung", order);
        } catch (Exception e) {
            return new Response(true, "BROWSE_LADING_FAIL", order);
        }
    }

    /**
     * Đếm số lượng hóa đơn đơn hàng theo trạng thái thanh toán
     *
     * @param paymentStatus
     * @param userId
     * @param seller
     * @return
     * @throws Exception
     */
    public long countByPaymentStatus(PaymentStatus paymentStatus, boolean seller, String userId) throws Exception {
        return orderRepository.countByPaymentStatus(paymentStatus, seller, userId);
    }

    /**
     * Đếm số lượng hóa đơn đơn hàng theo trạng thái thanh toán
     *
     * @param shipmentStatus
     * @param userId
     * @param seller
     * @return
     * @throws Exception
     */
    public long countByShipmentStatus(ShipmentStatus shipmentStatus, boolean seller, String userId) throws Exception {
        return orderRepository.countByShipmentStatus(shipmentStatus, seller, userId);
    }

    /**
     * Đếm số lượng hóa đơn đơn hàng theo trạng thái thanh toán
     *
     * @param remove
     * @param userId
     * @param seller
     * @return
     * @throws Exception
     */
    public long countByRemove(boolean remove, boolean seller, String userId) throws Exception {
        return orderRepository.countByRemove(remove, seller, userId);
    }

    /**
     * Đếm số lượng hóa đơn đơn hàng
     *
     * @param userId
     * @param seller
     * @return
     * @throws Exception
     */
    public long countByOrderAll(boolean seller, String userId) throws Exception {
        return orderRepository.countByOrderAll(seller, userId);
    }

    @Scheduled(fixedDelay = 10 * 60 * 1000)
//    @Scheduled(fixedDelay = 1000)
    public void run() {
        while (true) {
            Order order = orderRepository.getThread();
            if (order == null) {
                break;
            }
            this.processShipment(order);
        }
    }

    @Async
    public void processShipment(Order order) {
        try {
            Response<ShipmentStatus> status = scClient.status(order.getScId());
            if (status.isSuccess()) {
                order.setShipmentMessage(status.getMessage());
                order.setShipmentStatus(status.getData());
                order.setMarkBuyerShipment(status.getData());
                order.setMarkSellerShipment(status.getData());
                order.setShipmentUpdateTime(System.currentTimeMillis());
                orderRepository.save(order);

                if (status.getData() == ShipmentStatus.DELIVERED) {
                    List<OrderItem> oItems = orderItemRepository.getByOrderId(order.getId());
                    if (oItems != null && !oItems.isEmpty()) {
                        List<String> itemIds = new ArrayList<>();
                        for (OrderItem orderItem : oItems) {
                            itemIds.add(orderItem.getItemId());
                        }
                        List<Item> items = itemRepository.get(itemIds);
                        for (Item item : items) {
                            for (OrderItem orderItem : oItems) {
                                if (orderItem.getItemId().equals(item.getId())) {
                                    if (item.getListingType() == ListingType.AUCTION) {
                                        try {
                                            auctionService.endBidByPayment(item.getId());
                                        } catch (Exception e) {
                                        }
                                    } else {
                                        item.setSoldQuantity(item.getSoldQuantity() + orderItem.getQuantity());
                                        item.setQuantity(item.getQuantity() - orderItem.getQuantity());
                                        if (item.getQuantity() < 0) {
                                            item.setQuantity(0);
                                        }
                                    }
                                    item.setUpdateTime(System.currentTimeMillis());
                                    itemRepository.save(item);
                                }
                            }
                        }
                        indexService.processIndexPageItem(items);
                    }
                }

                Lading lading = ladingRepository.get(order.getId());
                if (lading != null) {
                    lading.setShipmentStatus(status.getData());
                    lading.setUpdateTime(System.currentTimeMillis());
                    ladingRepository.save(lading);
                }
                if (order.getSellerId() != null && !order.getSellerId().equals("")) {
                    realTimeService.add("Đơn hàng " + order.getId() + " " + order.getShipmentMessage(), order.getSellerId(), "/" + order.getId() + "/chi-tiet-don-hang.html", "Chi tiết đơn hàng", null);
                }
                if (order.getBuyerId() != null && !order.getBuyerId().equals("")) {
                    realTimeService.add("Đơn hàng " + order.getId() + " " + order.getShipmentMessage(), order.getBuyerId(), "/" + order.getId() + "/chi-tiet-don-hang.html", "Chi tiết đơn hàng", null);
                }
            }
        } catch (Exception e) {
        }
    }

    public void sendMessageCreateOrder(Order order, String baseUrl) {
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("username", order.getBuyerName());
            data.put("message", "Bạn vừa tạo thành công 1 đơn hàng trên hệ thống chợ điện tử <br> "
                    + "Mã đơn hàng : <a href='" + baseUrl + "/" + order.getId() + "/chi-tiet-don-hang.html' >" + order.getId().toUpperCase() + "</a>");
            emailService.send(EmailOutboxType.CREATE_ORDER_BUYER,
                    order.getBuyerEmail(),
                    "[Chợ điện tử] Thông báo đặt hàng thành công (" + order.getId() + ")",
                    "createorder", data);

            User seller = userService.get(order.getSellerId());
            data.put("username", seller.getName());
            data.put("message", "Người mua " + order.getBuyerName() + " vừa tạo thành công 1 đơn hàng trên hệ thống chợ điện tử <br> "
                    + "Mã đơn hàng : <a href='" + baseUrl + "/" + order.getId() + "/chi-tiet-don-hang.html' >" + order.getId().toUpperCase() + "</a>");
            emailService.send(EmailOutboxType.CREATE_ORDER_SELLER,
                    seller.getEmail(),
                    "[Chợ điện tử] Người mua " + order.getBuyerName() + " vừa đặt một đơn hàng (" + order.getId() + ")",
                    "createorder", data);

            smsService.send(seller.getPhone().trim(), "Khach hang " + order.getBuyerName() + " vua dat mua 1 don hang co ma hoa don: " + order.getId()
                    + ", gia tri thanh toan "
                    + TextUtils.numberFormat(Double.parseDouble(order.getFinalPrice() + "")) + " d tu ChoDienTu.vn",
                    SmsOutboxType.CREATE_ORDER_SELLER, System.currentTimeMillis() + 30 * 1000, 1);

            smsService.send(order.getBuyerPhone().trim(), "Ban vua dat mua 1 don hang co ma hoa don: " + order.getId()
                    + ", gia tri thanh toan "
                    + TextUtils.numberFormat(Double.parseDouble(order.getFinalPrice() + "")) + " d tu ChoDienTu.vn",
                    SmsOutboxType.CREATE_ORDER_BUYER, System.currentTimeMillis() + 30 * 1000, 1);

        } catch (Exception ex) {
        }

    }

    private void sendMessagePayment(Order order) {
    }

    /**
     * Tính tổng tiền theo điều kiện
     *
     * @param search
     * @return
     */
    public Map<String, Long> sumPrice(OrderSearch search) {
        return orderRepository.sumPrice(this.buildCriteria(search));
    }

    /**
     * Danh sách đơn hàng theo ids
     *
     * @param ids
     * @return
     */
    public List<Order> findByIds(List<String> ids) throws Exception {
        List<Order> orders = orderRepository.find(new Query(new Criteria("_id").in(ids)));
        List<OrderItem> orderItems = orderItemRepository.find(new Query(new Criteria("orderId").in(ids)));
        String error = null;
        for (Order order : orders) {
            if (order.getScId() != null) {
                if (error == null) {
                    error = "Đơn hàng ";
                }
                error += order.getId() + ", ";
            }
            if (order.getItems() == null) {
                order.setItems(new ArrayList<OrderItem>());
            }
            for (OrderItem orderItem : orderItems) {
                if (order.getId().equals(orderItem.getOrderId())) {
                    order.getItems().add(orderItem);
                }
            }
        }
        if (error != null) {
            error += " đã tồn tại mã vận chuyện, bạn vui lòng kiểm tra lại";
            throw new Exception(error);
        }
        return orders;
    }

}
