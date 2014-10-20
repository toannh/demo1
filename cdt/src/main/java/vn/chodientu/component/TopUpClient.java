package vn.chodientu.component;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
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
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Encoder;
import vn.chodientu.entity.db.TopUp;
import vn.chodientu.repository.TopUpRepository;

/**
 * @author thanhv
 */
@Component
public class TopUpClient {

    @Autowired
    private Gson gson;
    @Autowired
    private TopUpRepository topUpRepository;

    private final String affiliateCode = "e30f0167bf65bc61d12f3f0c8ea31852";
    private final String partnerKey = "61d12f3f0c8ea31852DCTDOIXENG";
    private final String encryptKey = "CDT8555b19a822512ba822512b";
    private final String url = "http://thanhtoanonline.vn/topupTelcoPostLive.php";

    public String call(String function, String param) throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("func", function));
        urlParameters.add(new BasicNameValuePair("params", param));
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

    /**
     * Nạp tiền trực tiếp
     *
     * @param userId
     * @param phone
     * @param card
     * @throws Exception
     */
    public TopUp topupTelco(String userId, String phone, Amount card) throws Exception {
        TopUpClient.Service service = this.getService(phone);
        if (service == null) {
            throw new Exception("Số điện thoại " + phone + " không thuộc nhà mạng nào trên hệ thống");
        }
        String serviceID = service.toString();
        String amount = "10000";
        try {
            String[] amounts = card.toString().split("_");
            amount = amounts[1];
        } catch (Exception e) {
        }
        long partnerTransactionID = System.currentTimeMillis();

        String signature = DigestUtils.md5DigestAsHex((affiliateCode + "|" + partnerTransactionID + "|" + serviceID + "|" + phone + "|" + amount + "|" + partnerKey).getBytes());
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("affiliate_code", affiliateCode);
        jsonParams.put("partner_transactionID", partnerTransactionID);
        jsonParams.put("serviceID", serviceID);
        jsonParams.put("partner_account", phone);
        jsonParams.put("amount", amount);
        jsonParams.put("service", 1);
        jsonParams.put("signature", signature);
        String jparam = gson.toJson(jsonParams);
        String paramsBuyCard = StringUtils.newStringUtf8(Base64.encodeBase64(jparam.getBytes()));
        String resp = this.call("TopupTelco", paramsBuyCard);
        resp = StringUtils.newStringUtf8(Base64.decodeBase64(resp));
        JSONObject data = new JSONObject(resp);

        //save log
        TopUp topUp = new TopUp();
        topUp.setService(serviceID);
        topUp.setAmount(Long.parseLong(amount));
        topUp.setCreateTime(System.currentTimeMillis());
        topUp.setUpdateTime(System.currentTimeMillis());
        topUp.setType("topupTelco");
        topUp.setMessage(this.getError(data.getString("error_code")));
        topUp.setRequestId(data.getString("request_id"));
        topUp.setErrorCode(data.getString("error_code"));
        topUp.setPhone(phone);
        topUp.setRequest(jparam);
        topUp.setResponse(resp);
        topUp.setUserId(userId);

        if (topUp.getErrorCode().equals("00")) {
            topUp.setSuccess(true);
        }

        topUpRepository.save(topUp);
        return topUp;
    }

    /**
     * Mua mã thẻ điện thoại
     *
     * @param userId
     * @param cart
     * @param service
     * @throws Exception
     */
    public TopUp buyCardTelco(String userId, Amount card, Service service) throws Exception {
        String serviceID = service.toString();
        int quantity = 1;
        long partnerTransactionID = System.currentTimeMillis();
        String amount = "10000";
        try {
            String[] amounts = card.toString().split("_");
            amount = amounts[1];
        } catch (Exception e) {
        }

        String signature = DigestUtils.md5DigestAsHex((affiliateCode + "|" + partnerTransactionID + "|" + serviceID + "|" + quantity + "|" + amount + "|" + partnerKey).getBytes());
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("affiliate_code", affiliateCode);
        jsonParams.put("partner_transactionID", partnerTransactionID);
        jsonParams.put("serviceID", serviceID);
        jsonParams.put("amount", amount);
        jsonParams.put("quantity", quantity);
        jsonParams.put("signature", signature);
        String jparam = gson.toJson(jsonParams);
        String paramsBuyCard = StringUtils.newStringUtf8(Base64.encodeBase64(jparam.getBytes()));
        String resp = this.call("BuyCardTelco", paramsBuyCard);
        resp = StringUtils.newStringUtf8(Base64.decodeBase64(resp));
        JSONObject data = new JSONObject(resp);
        //save log
        TopUp topUp = new TopUp();
        topUp.setService(serviceID);
        topUp.setAmount(Long.parseLong(amount));
        topUp.setCreateTime(System.currentTimeMillis());
        topUp.setUpdateTime(System.currentTimeMillis());
        topUp.setType("buyCardTelco");
        topUp.setMessage(data.getString("error_text"));
        topUp.setErrorCode(data.getString("error_code"));
        try {
            topUp.setRequestId(data.getString("request_id"));
        } catch (Exception e) {
        }
        topUp.setPhone(null);
        topUp.setRequest(jparam);
        topUp.setResponse(resp);
        topUp.setUserId(userId);
        if (topUp.getErrorCode().equals("00")) {
            topUp.setSuccess(true);
            String decrypt = decrypt(encryptKey, data.getString("cards"));
            decrypt = decrypt.replaceAll("\\[", "").replaceAll("\\]", "");
            JSONObject cards = new JSONObject(decrypt);
            topUp.setCardCode(cards.getString("card_code"));
            topUp.setCardSerial(cards.getString("card_serial"));
            topUp.setCardType(cards.getString("card_type"));
            topUp.setCardValue(cards.getString("card_value"));
            topUp.setExpiryDate(cards.getString("expiryDate"));

        }
        topUpRepository.save(topUp);
        return topUp;
    }

    public enum Amount {

        CARD_10000,
        CARD_20000,
        CARD_30000,
        CARD_50000,
        CARD_100000,
        CARD_200000,
        CARD_300000,
        CARD_500000,
    }

    public enum Service {

        //MobiFone Trả trước
        MOBIFONE,
        //MobiFone Trả sau
        MOBIFONE_LATER,
        //Viettel Trả trước
        VIETTEL,
        //Viettel trả sau
        VIETTEL_LATER,
        //VinaPhone Trả trước
        VINAPHONE,
        //Vinaphone trả sau
        VINAPHONE_LATER,
        //Beeline Trả trước
        GMOBILE,
        //Vietnamobile Trả trước
        VIETNAMMOBILE

    }

    private String getError(String code) {
        HashMap<String, String> error = new HashMap<String, String>();
        error.put("00", "Thành công");
        error.put("01", "Lỗi Telco");
        error.put("03", "Sai mệnh giá tiền");
        error.put("04", "Sai Mã thanh toán");
        error.put("05", "Hệ thống bảo trì");
        error.put("06", "Số lượng mã thẻ >= 1");
        error.put("08", "Time out");
        error.put("09", "Số thanh toán đã tồn tại. Hoặc sai format");
        error.put("11", "Trả sau. Liên hệ với thanhtoanonline để kiểm tra lại dịch vụ");
        error.put("20", "Partner không tồn tại. Hoặc chưa cấp phép sử dụng dịch vụ");
        error.put("22", "Số điện thoại cần nạp không hợp lệ. (format)");
        error.put("23", "Số điện thoại cần nạp không hợp lệ. (đúng format nhưng không tồn tại số điện thoại,…)");
        error.put("24", "Mã yêu cầu không hợp lệ");
        error.put("25", "Địa chỉ IP không được cấp phép truy cập dịch vụ");
        error.put("26", "Giao dịch không tồn tại");
        error.put("51", "Tài khoản không đủ số dư để thực hiện giao dịch");
        error.put("79", "Chữ ký không hợp lệ");
        error.put("80", "Chữ ký không tồn tại");
        error.put("90", "Hệ thống telco tạm ngừng giao dịch");
        error.put("62", "Hết mã thẻ mệnh giá cần mua. (gd mua mã thẻ)");
        error.put("96", "Lỗi hệ thống");
        error.put("99", "Offline để bảo trì hệ thống");
        error.put("999", "Lỗi không xác định");
        return error.get(code);
    }

    public String getMD5(String sMessage) {
        byte[] defaultBytes = sMessage.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException nsae) {
            return null;
        }
    }

    public String encrypt(String key, String data) throws Exception {
        Cipher cipher = Cipher.getInstance("TripleDES");
        String keymd5 = getMD5(key).substring(0, 24);
        SecretKeySpec keyspec = new SecretKeySpec(keymd5.getBytes(), "TripleDES");
        cipher.init(Cipher.ENCRYPT_MODE, keyspec);
        byte[] stringBytes = data.getBytes();
        byte[] raw = cipher.doFinal(stringBytes);
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(raw);
        return base64;
    }

    public String decrypt(String key, String data) throws Exception {
        Cipher cipher = Cipher.getInstance("TripleDES");
        String keymd5 = getMD5(key).substring(0, 24);
        SecretKeySpec keyspec = new SecretKeySpec(keymd5.getBytes(), "TripleDES");
        cipher.init(Cipher.DECRYPT_MODE, keyspec);
        byte[] raw = Base64.decodeBase64(data);
        byte[] stringBytes = cipher.doFinal(raw);
        String result = new String(stringBytes);
        return result;
    }

    private TopUpClient.Service getService(String phone) {
        if (phone.startsWith("091") || phone.startsWith("094")
                || phone.startsWith("0123") || phone.startsWith("0124")
                || phone.startsWith("0125") || phone.startsWith("0127")
                || phone.startsWith("0129")) {
            return TopUpClient.Service.VINAPHONE;
        } else if (phone.startsWith("090") || phone.startsWith("093")
                || phone.startsWith("0120") || phone.startsWith("0121")
                || phone.startsWith("0122") || phone.startsWith("0126")
                || phone.startsWith("0128")) {
            return TopUpClient.Service.MOBIFONE;
        } else if (phone.startsWith("096") || phone.startsWith("097")
                || phone.startsWith("098") || phone.startsWith("0162")
                || phone.startsWith("0163") || phone.startsWith("0164")
                || phone.startsWith("0165") || phone.startsWith("0166")
                || phone.startsWith("0167") || phone.startsWith("0168")
                || phone.startsWith("0169")) {
            return TopUpClient.Service.VIETTEL;
        } else if (phone.startsWith("092") || phone.startsWith("0186")
                || phone.startsWith("0188")) {
            return TopUpClient.Service.VIETNAMMOBILE;
        } else if (phone.startsWith("099") || phone.startsWith("0199")) {
            return TopUpClient.Service.GMOBILE;
        } else {
            return null;
        }
    }

}
