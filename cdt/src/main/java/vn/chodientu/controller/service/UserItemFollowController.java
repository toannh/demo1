/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ItemFollowService;
import vn.chodientu.service.SellerFollowService;

@Controller("serviceUserItemFollow")
@RequestMapping("/useritemfollow")
public class UserItemFollowController extends BaseRest {

    @Autowired
    private ItemFollowService itemFollowService;
    @Autowired
    private SellerFollowService sellerFollowService;

    @RequestMapping(value = "/savenote", method = RequestMethod.GET)
    @ResponseBody
    public Response saveNote(@RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "note", defaultValue = "") String note) {
        try {
            return new Response(true, "OK", itemFollowService.note(id, note));
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Response del(@RequestBody List<String> userItemIds) {
        try {
            itemFollowService.remove(userItemIds);
            return new Response(true, "Xóa thành công danh sách được lựa chọn");
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    @RequestMapping(value = "/delsellerfollow", method = RequestMethod.POST)
    @ResponseBody
    public Response delSellerFollow(@RequestBody List<String> sellerIds) {
        try {
            sellerFollowService.remove(sellerIds);
            return new Response(true, "Xóa thành công danh sách được lựa chọn");
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }
}
