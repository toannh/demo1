package vn.chodientu.controller.market;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author thanhvv
 */
@Controller("marketError")
public class ErrorController extends BaseMarket {

    @RequestMapping("/404")
    public String notFound(ModelMap model, HttpServletResponse res) throws IOException {
//        res.setStatus(301);
//        res.sendRedirect(baseUrl);
        model.put("canonical", "/404.html");
        model.put("title", "Không tìm thấy trang");
        model.put("description", "ChoDienTu, không tìm thấy trang");
        return "market.404";
    }

    @RequestMapping("/500")
    public String error(ModelMap model) {
        return "market.500";
    }

}
