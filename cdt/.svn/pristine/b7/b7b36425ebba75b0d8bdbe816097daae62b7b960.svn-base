package vn.chodientu.controller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.component.NlClient;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.OrderItem;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.PaymentMethod;
import vn.chodientu.entity.enu.PaymentStatus;
import vn.chodientu.entity.enu.ShipmentStatus;
import vn.chodientu.entity.db.Lading;
import vn.chodientu.entity.db.Message;
import vn.chodientu.entity.db.Tag;
import vn.chodientu.entity.input.TagSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.MessageService;
import vn.chodientu.service.OrderService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.TagService;

/**
 *
 * @author thanhvv
 */
@Controller("serviceTag")
@RequestMapping("/tag")
public class TagController extends BaseRest {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/loadtagbyuser", method = RequestMethod.POST)
    @ResponseBody
    public Response loadTagByUser(@RequestBody TagSearch tagSearch) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập trước!");
        }
        tagSearch.setUserId(viewer.getUser().getId());
        DataPage<Tag> search = tagService.search(tagSearch);
        return new Response(true, "Danh sách tag", search);
    }

}
