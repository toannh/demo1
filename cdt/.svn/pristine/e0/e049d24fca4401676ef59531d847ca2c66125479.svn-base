package vn.chodientu.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.entity.data.CrawlProperty;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CrawlService;

/**
 *
 * @author thanhvv
 */
@Controller("crawlApi")
public class CrawlController {

    @Autowired
    private CrawlService crawlService;

    @RequestMapping(value = "/crawl", method = RequestMethod.POST)
    @ResponseBody
    public Response received(@RequestBody List<CrawlProperty> data) {
        try {
            return new Response(true, "Thêm sản phẩm crawl", crawlService.save(data));
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/crawl", method = RequestMethod.PUT)
    @ResponseBody
    public Response update(@RequestBody List<CrawlProperty> data) {
        try {
            return new Response(true, "Cập nhật sản phẩm crawl", crawlService.update(data));
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }
}
