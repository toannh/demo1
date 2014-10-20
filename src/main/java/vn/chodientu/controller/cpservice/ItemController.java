/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ItemService;
import vn.chodientu.entity.db.ItemApprove;
import vn.chodientu.entity.db.UpSchedule;
import vn.chodientu.service.UpScheduleService;

/**
 *
 * @author thunt
 */
@Controller("cpItemService")
@RequestMapping(value = "/cpservice/item")
public class ItemController extends BaseRest {

    @Autowired
    private ItemService itemService;

    /**
     * Xóa sản phẩm
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response del(@RequestParam(value = "id") String id) {
        try {
            itemService.remove(id);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
        return new Response(true, "Xóa sản phẩm thành công");
    }

    /**
     * Yêu cầu sửa lại sản phẩm
     *
     * @param itemApprove
     * @return
     */
    @RequestMapping(value = "/disapproved", method = RequestMethod.POST)
    @ResponseBody
    public Response disapproved(@RequestBody ItemApprove itemApprove) {
        String adminId = "test";
        if (viewer != null && viewer.getAdministrator() != null) {
            adminId = viewer.getAdministrator().getId();
        }
        try {
            itemService.disapproved(itemApprove.getItemId(), adminId, itemApprove.getMessage());
            return new Response(true, "Yêu cầu sửa thành công");
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * Index toàn bộ sản phẩm
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public Response index() throws Exception {
        itemService.index();
        return new Response(true, "Đang xử lý index....");
    }

    /**
     * Xóa toàn bộ index
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unindex")
    @ResponseBody
    public Response unindex() throws Exception {
        itemService.unIndex();
        return new Response(true, "Xóa index thành công");
    }

}
