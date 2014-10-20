package vn.chodientu.component;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.chodientu.util.TextUtils;

/**
 *
 * @author thanhvv
 */
@Component
public class EmailClient {

    @Autowired
    private Gson gson;

    public void addEmail(String email, int service) throws UnsupportedEncodingException, IOException {
        if (!TextUtils.validateEmailString(email)) {
            return;
        }
        String xml = "<xmlrequest>";
        xml += "<username>phugt</username>";
        xml += "<usertoken>e2505c0b25537473bfa383139fdc3ec0b8a95f2e</usertoken>";
        xml += "<requesttype>subscribers</requesttype>";
        xml += "<requestmethod>AddSubscriberToList</requestmethod>";
        xml += "<details>";
        xml += "<emailaddress>" + email + "</emailaddress>";
        xml += "<mailinglist>" + service + "</mailinglist>";
        xml += "<format>html</format>";
        xml += "<confirmed>yes</confirmed>";
        xml += "</details>";
        xml += "</xmlrequest>";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://emm.chodientu.vn/xml.php");
        post.setHeader("Content-Type", "application/xml; charset=UTF-8");
        StringEntity entity = new StringEntity(xml);
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder text = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            text.append(line);
        }
    }

}
