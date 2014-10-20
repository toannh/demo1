package vn.chodientu.component;

import com.google.gson.Gson;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.chodientu.entity.db.ScHistory;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.enu.ShipmentService;
import vn.chodientu.entity.enu.ShipmentStatus;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.ScHistoryRepository;

/**
 *
 * @author Phu
 */
@Component
public class ScClient {

    @Autowired
    private Gson gson;
    @Autowired
    private ScHistoryRepository historyRepository;

    private String call(String url, Map<String, Object> params) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        List<NameValuePair> urlParameters = new ArrayList<>();
        if (params != null) {
            for (String key : params.keySet()) {
                if (params.get(key) != null) {
                    urlParameters.add(new BasicNameValuePair(key, params.get(key).toString()));
                }
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

        // log sc
        ScHistory scHistory = new ScHistory();
        scHistory.setParams(params);
        scHistory.setResp(text.toString());
        scHistory.setUrl(url);
        historyRepository.save(scHistory);

        return text.toString();
    }

    public String getNlId(String email) throws Exception {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("function", "GetNganLuongId");
            params.put("params[email]", email);
            String call = call("http://api.shipchung.vn/ShipChungUser/", params);
            return gson.fromJson(call, params.getClass()).get("user_nl_id").toString();
        } catch (Exception ex) {
            throw new Exception("Tài khoản " + email + " chưa được liên kết (tạo tài khoản) trên shipchung.vn");
        }
    }

    public FeeShip caculateFee(String email, ShipmentService service, String fromCity, String fromDistrict, String toCity, String toDistrict, long price, int weight, boolean isCod, boolean isProtected) throws Exception {
        
        Map<String, Object> params = new HashMap<>();
        params.put("function", "Calculate");

        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("domain", "chodientu.vn");
        jsonParams.put("user_nl_id", 26989);
        if (service == ShipmentService.SLOW) {
            jsonParams.put("service", 1);
        }
        if (service == ShipmentService.FAST) {
            jsonParams.put("service", 2);
        }
        if (service == ShipmentService.RAPID) {
            jsonParams.put("service", 3);
        }

        jsonParams.put("from_city_id", fromCity);
        jsonParams.put("from_district_id", fromDistrict);
        jsonParams.put("to_city_id", toCity);
        jsonParams.put("to_district_id", toDistrict);
        jsonParams.put("item_weight", weight);
        jsonParams.put("item_price", price);
        if (isCod && isProtected) {
            jsonParams.put("vas", new String[]{"cod", "protected"});
        } else if (isCod) {
            jsonParams.put("vas", new String[]{"cod"});
        } else if (isProtected) {
            jsonParams.put("vas", new String[]{"protected"});
        }

        params.put("params", gson.toJson(jsonParams));

        try {
            JSONObject res = new JSONObject(call("http://api.shipchung.vn/ShipChungPrivate/", params));
            FeeShip feeShip = new FeeShip();
            try {
                feeShip.setPcod(res.getJSONObject("feevas").getInt("protected"));
            } catch (Exception e) {
            }
            try {
                feeShip.setShip(res.getJSONObject("cdt_fee").getInt("ship"));
            } catch (Exception e) {
                feeShip.setShip(-1);
            }
            return feeShip;
        } catch (Exception ex) {
            return new FeeShip(-1, 0);
        }
    }

    /**
     *
     * @param email
     * @param scId
     * @param service
     * @param fromName
     * @param fromEmail
     * @param fromPhone
     * @param fromAddress
     * @param fromCity
     * @param fromDistrict
     * @param toName
     * @param toEmail
     * @param toPhone
     * @param toAddress
     * @param toCity
     * @param toDistrict
     * @param itemName
     * @param itemDescription
     * @param price Tổng giá sp
     * @param chargePrice Phí cần thu hộ
     * @param feeShipping
     * @param weight
     * @param isCod
     * @param isProtected
     * @return Nếu success = true, mã vận đơn mà message, phí vc (thu của người
     * bán) là data
     * @throws java.lang.Exception
     */
    public Response createShipment(
            String email,
            String scId,
            ShipmentService service,
            String fromName,
            String fromEmail,
            String fromPhone,
            String fromAddress,
            String fromCity,
            String fromDistrict,
            String toName,
            String toEmail,
            String toPhone,
            String toAddress,
            String toCity,
            String toDistrict,
            String itemName,
            String itemDescription,
            long price,
            long chargePrice,
            long feeShipping,
            int weight,
            boolean isCod,
            boolean isProtected) throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put("function", "CreateLading");

        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("domain", "chodientu.vn");
        if (scId != null && !scId.equals("")) {
            jsonParams.put("user_id", scId);
        } else {
            String nlId = getNlId(email);
            if (nlId == null) {
                return new Response(false, "Tài khoản người bán chưa tích hợp shipchung!");
            }
            jsonParams.put("user_nl_id", nlId);
        }
        if (service == ShipmentService.SLOW) {
            jsonParams.put("service", 1);
        }
        if (service == ShipmentService.FAST) {
            jsonParams.put("service", 2);
        }
        if (service == ShipmentService.RAPID) {
            jsonParams.put("service", 3);
        }

        jsonParams.put("from_name", fromName);
        jsonParams.put("from_email", fromEmail);
        jsonParams.put("from_phone", fromPhone);
        jsonParams.put("from_address", fromAddress);
        jsonParams.put("from_city_id", fromCity);
        jsonParams.put("from_district_id", fromDistrict);
        jsonParams.put("to_name", toName);
        jsonParams.put("to_email", toEmail);
        jsonParams.put("to_phone", toPhone);
        jsonParams.put("to_address", toAddress);
        jsonParams.put("to_city_id", toCity);
        jsonParams.put("to_district_id", toDistrict);
        jsonParams.put("item_name", itemName);
        jsonParams.put("item_description", itemDescription);
        jsonParams.put("item_weight", weight);
        jsonParams.put("item_price", price);
        jsonParams.put("collect_money", chargePrice);
        if (feeShipping > -1) {
            jsonParams.put("fee_shipping", feeShipping);
        }
        //pug
//        jsonParams.put("debug", 1);

        if (isCod && isProtected) {
            jsonParams.put("vas", new String[]{"cod", "protected"});
        } else if (isCod) {
            jsonParams.put("vas", new String[]{"cod"});
        } else if (isProtected) {
            jsonParams.put("vas", new String[]{"protected"});
        }

        params.put("params", gson.toJson(jsonParams));

        try {
            JSONObject res = new JSONObject(call("http://api.shipchung.vn/ShipChungPrivate/", params));
            FeeShip feeShip = new FeeShip(res.getJSONObject("ShowFee").getInt("pvc"), res.getJSONObject("ShowFee").getInt("pcod"));
            return new Response(true, res.getString("code"), feeShip);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    public Response status(String scId) {
        Map<String, Object> params = new HashMap<>();
        params.put("function", "Journey");
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("sc_code", scId);
        params.put("params", gson.toJson(jsonParams));
        try {
            JSONObject res = new JSONObject(call("http://api.shipchung.vn/ShipChungPrivate/", params));
            String statusCode = res.getJSONObject("lading").getString("status_code");
            ShipmentStatus status = null;
            switch (statusCode) {
                case "NEW_REQUEST":
                    status = ShipmentStatus.NEW;
                    break;
                case "ACCEPT_REQUEST":
                    status = ShipmentStatus.STOCKING;
                    break;
                case "STOCKING":
                    status = ShipmentStatus.STOCKING;
                    break;
                case "DELIVERED_RETURN":
                    status = ShipmentStatus.RETURN;
                    break;
                case "RETURN":
                    status = ShipmentStatus.RETURN;
                    break;
                case "REQUEST_DENIED":
                    status = ShipmentStatus.DENIED;
                    break;
                case "RETURNING":
                    status = ShipmentStatus.RETURN;
                    break;
                case "DELIVERED":
                    status = ShipmentStatus.DELIVERED;
                    break;
                case "STOCKED":
                    status = ShipmentStatus.DELIVERING;
                    break;
                case "DELIVERING":
                    status = ShipmentStatus.DELIVERING;
                    break;
                case "DELIVERY_CANCEL":
                    status = ShipmentStatus.DENIED;
                    break;
                case "CANCEL":
                    status = ShipmentStatus.DENIED;
                    break;
                case "DELIVER_STOCKED":
                    status = ShipmentStatus.DELIVERING;
                    break;
                case "DELIVERY_PROBLEM":
                    status = ShipmentStatus.DELIVERING;
                    break;
            }

            return new Response(true, res.getJSONObject("lading").getString("status"), status);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    public void signin(String code, Seller seller) {
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("grant_type", "authorization_code");
        jsonParams.put("client_id", "cdt_access");
        jsonParams.put("client_secret", "adaodajouq933jdajkdiw");
        jsonParams.put("code", code.trim());

        try {
            JSONObject res = new JSONObject(call("http://api.shipchung.vn/oauth_token.php?grant_type=authorization_code", jsonParams));
            String token = res.getString("access_token");
            res = new JSONObject(call("http://api.shipchung.vn/ShipChungUser/GetUserOpenID?access_token=" + token, jsonParams));
            seller.setScEmail(res.getJSONObject("DATA").getString("email"));
            seller.setScId(res.getJSONObject("DATA").getString("id"));
            seller.setScIntegrated(true);
        } catch (Exception ex) {
        }
    }

    public class FeeShip {

        private int ship;
        private int pcod;

        public FeeShip(int ship, int pcod) {
            this.ship = ship;
            this.pcod = pcod;
        }

        private FeeShip() {

        }

        public int getShip() {
            return ship;
        }

        public void setShip(int ship) {
            this.ship = ship;
        }

        public int getPcod() {
            return pcod;
        }

        public void setPcod(int pcod) {
            this.pcod = pcod;
        }

    }
}
