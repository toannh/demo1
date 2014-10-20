package vn.chodientu.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

/**
 * @since May 20, 2014
 * @author Phu
 */
@Component
public class NlClient {

    private final String serviceUrl = "https://www.nganluong.vn/checkout.api.nganluong.post.php";
    private final String merchantId = "15643";
    private final String merchantPassword = "70d95bdcc3aa1348923a6d88a6cdf605";
    private final String version = "3.1";

    public MakePaymentResponse makePayment(MakePaymentRequest request) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("receiver_email", request.receiverEmail);
        params.put("order_code", request.orderId);
        params.put("order_description", request.orderDesctiption);
        params.put("total_amount", request.totalAmount);
        params.put("discount_amount", request.discountAmount);
        params.put("fee_shipping", request.shippingFee);
        params.put("payment_method", request.paymentMethod);
        params.put("bank_code", request.bankCode);
        params.put("order_code", request.orderId);
        params.put("return_url", request.returnUrl);
        params.put("cancel_url", request.cancelUrl);
        params.put("buyer_name", request.buyerName);
        params.put("buyer_email", request.buyerEmail);
        params.put("buyer_mobile", request.buyerPhone);
        params.put("buyer_address", request.buyerAddress);
        params.put("total_item", request.items.size());
        int i = 1;
        for (OrderItem item : request.items) {
            params.put("item_name" + i, item.getName());
            params.put("item_url" + i, item.getUrl());
            params.put("item_quantity" + i, item.getQuantity());
            params.put("item_amount" + i, item.getPrice());
            i++;
        }

        String resp = call("SetExpressCheckout", params);
        resp = resp.replaceAll("<checkout_url>(.*)</checkout_url>", "<checkout_url><![CDATA[$1]]></checkout_url>");
        JSONObject xmlJSONObj = XML.toJSONObject(resp).getJSONObject("result");

        if (xmlJSONObj.getInt("error_code") == 0) {
            MakePaymentResponse response = new MakePaymentResponse();
            response.setSuccess(true);
            response.setToken(xmlJSONObj.getString("token"));
            response.setCheckoutUrl(xmlJSONObj.getString("checkout_url"));
            return response;
        } else {
            throw new Exception("Ngân Lượng đang bị lỗi, mã lỗi: " + xmlJSONObj.getInt("error_code"));
        }
    }

    public CheckPaymentResponse checkPayment(String token) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);

        String resp = call("GetTransactionDetail", params);
        JSONObject xmlJSONObj = XML.toJSONObject(resp).getJSONObject("result");

        CheckPaymentResponse response = new CheckPaymentResponse();
        if (xmlJSONObj.getInt("error_code") == 0) {
            try {
                response.setStatus(xmlJSONObj.getInt("transaction_status"));
                response.setTransactionId(xmlJSONObj.getString("transaction_id"));
                response.setTotalAmount(xmlJSONObj.getInt("total_amount"));
                response.setDiscountAmount(xmlJSONObj.getInt("discount_amount"));
                response.setShippingFee(xmlJSONObj.getInt("shipping_fee"));
            } catch (Exception ex) {
            }
        } else {
            throw new Exception("Ngân Lượng đang bị lỗi, mã lỗi: " + xmlJSONObj.getInt("error_code"));
        }

        return response;
    }

    public String call(String method, Map<String, Object> params) throws IOException {
        params.put("merchant_id", merchantId);
        params.put("merchant_password", merchantPassword);
        params.put("version", version);
        params.put("function", method);

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(serviceUrl);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        List<NameValuePair> urlParameters = new ArrayList<>();

        for (String key : params.keySet()) {
            if (params.get(key) != null) {
                urlParameters.add(new BasicNameValuePair(key, params.get(key).toString()));
            }
        }

        post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));

        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder text = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            text.append(line);
        }
        return text.toString();
    }

    public Bank[] getBanks() {
        return new Bank[]{
            new Bank("VCB", "Ngân hàng TMCP Ngoại Thương Việt Nam (Vietcombank)", new String[]{"atm_online", "atm_offline", "nh_offline"}),
            new Bank("DAB", "Ngân hàng TMCP Đông Á (DongA Bank)", new String[]{"atm_online", "atm_offline", "nh_offline"}),
            new Bank("TCB", "Ngân hàng TMCP Kỹ Thương (Techcombank)", new String[]{"atm_online", "atm_offline", "nh_offline"}),
            new Bank("MB", "Ngân hàng TMCP Quân Đội (MB)", new String[]{"atm_online", "atm_offline", "nh_offline"}),
            new Bank("VIB", "Ngân hàng TMCP Quốc tế (VIB)", new String[]{"atm_online", "nh_offline"}),
            new Bank("VTB", "Ngân hàng TMCP Công Thương (VietinBank)", new String[]{"atm_online", "atm_offline", "nh_offline"}),
            new Bank("EXB", "Ngân hàng TMCP Xuất Nhập Khẩu (Eximbank)", new String[]{"atm_online"}),
            new Bank("ACB", "Ngân hàng TMCP Á Châu (ACB)", new String[]{"atm_online", "atm_offline", "nh_offline"}),
            new Bank("HDB", "Ngân hàng TMCP Phát Triển Nhà TP. Hồ Chí Minh (HDBank)", new String[]{"atm_online"}),
            new Bank("NVB", "Ngân hàng TMCP Nam Việt (NaviBank)", new String[]{"atm_online"}),
            new Bank("VAB", "Ngân hàng TMCP Việt Á (VietA Bank)", new String[]{"atm_online"}),
            new Bank("VPB", "Ngân hàng TMCP Việt Nam Thịnh Vượng  (VPBank)", new String[]{"atm_online"}),
            new Bank("SCB", "Ngân hàng TMCP Sài Gòn Thương Tính (Sacombank)", new String[]{"atm_online", "atm_offline", "nh_offline"}),
            new Bank("GPB", "Ngân hàng TMCP Dầu Khí (GPBank)", new String[]{"atm_online"}),
            new Bank("AGB", "Ngân hàng Nông nghiệp và Phát triển Nông thôn (Agribank)", new String[]{"atm_online", "atm_offline", "nh_offline"}),
            new Bank("BIDV", "Ngân hàng Đầu tư và Phát triển Việt Nam (BIDV)", new String[]{"atm_online", "atm_offline", "nh_offline"}),
            new Bank("OJB", "Ngân hàng TMCP Đại Dương (OceanBank)", new String[]{"atm_online"}),
            new Bank("PGB", "Ngân Hàng TMCP Xăng Dầu Petrolimex (PGBank)", new String[]{"atm_online", "atm_offline", "nh_offline"}),
            new Bank("SHB", "Ngân hàng TMCP Sài Gòn - Hà Nội (SHB)", new String[]{"atm_offline"}),
            new Bank("SB", "Ngân hàng TMCP Đông Nam Á (SeaBank)", new String[]{"nh_offline"}),
            new Bank("TPB", "Ngân hàng TMCP Tiên Phong (TienPhong Bank)", new String[]{"nh_offline"}),
            new Bank("VISA", "Visa card", new String[]{"visa"}),
            new Bank("MASTER", "Master card", new String[]{"visa"}),
            new Bank("HN", "Văn phòng Ngân Lượng Hà Nội", new String[]{"ttvp"}),
            new Bank("HCM", "Văn phòng Ngân Lượng Thành phố Hồ Chí Minh", new String[]{"ttvp"})
        };
    }

    public class MakePaymentRequest {

        private String receiverEmail;
        private String orderId;
        private String orderDesctiption;
        private long totalAmount;
        private double discountAmount;
        private long shippingFee;
        private String paymentMethod;
        private String bankCode;
        private String returnUrl;
        private String cancelUrl;
        private String buyerName;
        private String buyerEmail;
        private String buyerPhone;
        private String buyerAddress;
        private List<OrderItem> items;

        public String getReceiverEmail() {
            return receiverEmail;
        }

        public void setReceiverEmail(String receiverEmail) {
            this.receiverEmail = receiverEmail;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderDesctiption() {
            return orderDesctiption;
        }

        public void setOrderDesctiption(String orderDesctiption) {
            this.orderDesctiption = orderDesctiption;
        }

        public long getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(long totalAmount) {
            this.totalAmount = totalAmount;
        }

        public double getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(double discountAmount) {
            this.discountAmount = discountAmount;
        }

        public long getShippingFee() {
            return shippingFee;
        }

        public void setShippingFee(long shippingFee) {
            this.shippingFee = shippingFee;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getReturnUrl() {
            return returnUrl;
        }

        public void setReturnUrl(String returnUrl) {
            this.returnUrl = returnUrl;
        }

        public String getCancelUrl() {
            return cancelUrl;
        }

        public void setCancelUrl(String cancelUrl) {
            this.cancelUrl = cancelUrl;
        }

        public String getBuyerName() {
            return buyerName;
        }

        public void setBuyerName(String buyerName) {
            this.buyerName = buyerName;
        }

        public String getBuyerEmail() {
            return buyerEmail;
        }

        public void setBuyerEmail(String buyerEmail) {
            this.buyerEmail = buyerEmail;
        }

        public String getBuyerPhone() {
            return buyerPhone;
        }

        public void setBuyerPhone(String buyerPhone) {
            this.buyerPhone = buyerPhone;
        }

        public String getBuyerAddress() {
            return buyerAddress;
        }

        public void setBuyerAddress(String buyerAddress) {
            this.buyerAddress = buyerAddress;
        }

        public List<OrderItem> getItems() {
            return items;
        }

        public void setItems(List<OrderItem> items) {
            this.items = items;
        }

    }

    public class MakePaymentResponse {

        private boolean success;
        private String token;
        private String checkoutUrl;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getCheckoutUrl() {
            return checkoutUrl;
        }

        public void setCheckoutUrl(String checkoutUrl) {
            this.checkoutUrl = checkoutUrl;
        }

    }

    public class CheckPaymentResponse {

        private int status;
        private String transactionId;
        private long totalAmount;
        private long discountAmount;
        private long shippingFee;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public long getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(long totalAmount) {
            this.totalAmount = totalAmount;
        }

        public long getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(long discountAmount) {
            this.discountAmount = discountAmount;
        }

        public long getShippingFee() {
            return shippingFee;
        }

        public void setShippingFee(long shippingFee) {
            this.shippingFee = shippingFee;
        }

    }

    public class Bank {

        private String code;
        private String name;
        private String[] methods;

        public Bank(String code, String name, String[] methods) {
            this.code = code;
            this.name = name;
            this.methods = methods;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String[] getMethods() {
            return methods;
        }

        public void setMethods(String[] methods) {
            this.methods = methods;
        }
    }

    public class OrderItem {

        private String url;
        private String name;
        private int quantity;
        private long price;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public long getPrice() {
            return price;
        }

        public void setPrice(long price) {
            this.price = price;
        }

        public OrderItem() {
        }

        public OrderItem(String url, String name, int quantity, long price) {
            this.url = url;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

    }
}
