package vn.chodientu.component;

import java.util.UUID;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import vn.chodientu.component.sms.SendMT;
import vn.chodientu.component.sms.SendMTPortType;
import vn.chodientu.util.TextUtils;

/**
 * @since May 9, 2014
 * @author Phu
 */
@Component
public class SmsClient {

    public static int TYPE_G_Com1 = 1;
    public static int TYPE_G_Com2_3 = 2;
    public static int TYPE_G_Ad1 = 4;
    public static int TYPE_G_Ad2 = 10;

    public static int TEMP_NONE = 0;
    public static int TEMP_CDT = 1;
    public static int TEMP_CHODIENTU = 2;

    public String sendSms(String content, String receiver, int type) {
        return sendSms(content, receiver, type, 0);
    }

    public String sendSms(String content, String receiver, int type, int temp) {
        String username = "peacesoft";
        String password = "peacesoft!net";
        SendMT service = new SendMT();
        SendMTPortType port = service.getSendMTHttpSoap12Endpoint();
        String brandName = "ChoDienTu";
        if (type == TYPE_G_Com2_3) {
            if (temp == 1) {
                brandName += "#507";
                content = "CDT: " + content;
            }
            if (temp == 2) {
                brandName += "#506";
                content = "ChoDienTu: " + content;
            }
        }
        if (type == TYPE_G_Ad1 || type == TYPE_G_Ad2) {
            brandName = null;
        }
        content = TextUtils.removeDiacritical(content);
        return port.sendSMS(username, password, receiver, TextUtils.removeDiacritical(content), type, brandName, DigestUtils.md5Hex(content + receiver + type + temp));
    }
}
