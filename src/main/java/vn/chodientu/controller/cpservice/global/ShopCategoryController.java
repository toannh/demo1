package vn.chodientu.controller.cpservice.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ShopCategoryService;

/**
 * @since May 10, 2014
 * @author Phu
 */
@Controller("cpGlobalShopCategoryService")
@RequestMapping("/cpservice/global/shopcategory")
public class ShopCategoryController extends BaseRest {

    @Autowired
    private ShopCategoryService shopCategoryService;

    /**
     * Lấy danh mục cấp con theo id
     *
     * @param id
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getchilds", method = RequestMethod.GET)
    public Response getChilds(@RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "userId", defaultValue = "") String userId) throws Exception {
        if(id.trim().equals("")){
            id = null;
        }
        return new Response(true, "ok", shopCategoryService.getChilds(id, userId));
    }
    /**
     * Lấy danh mục chi tiết
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Response get(@RequestParam(value = "id", defaultValue = "") String id){
        return new Response(true, "ok", shopCategoryService.get(id));
    }
}
