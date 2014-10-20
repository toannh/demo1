package vn.chodientu.controller.api;

import com.github.cage.GCage;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.chodientu.entity.web.Captcha;

/**
 * @since May 15, 2014
 * @author Phu
 */
@Controller
public class CaptchaController {  
    @Autowired
    private Captcha captcha;

    @RequestMapping(value = "/captcha")
    public void gen(HttpServletResponse resp) throws IOException {
        GCage cage = new GCage();
        String token = RandomStringUtils.randomAlphanumeric(5);
        captcha.setToken(token);
        cage.draw(token, resp.getOutputStream());
    }
}
