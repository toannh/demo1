/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.CashHistory;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.db.TopUp;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.db.UserLock;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CashHistoryService;
import vn.chodientu.service.CashService;
import vn.chodientu.service.TopUpService;
import vn.chodientu.service.UserLockService;
import vn.chodientu.service.UserService;

/**
 *
 * @author Phuongdt
 */
@Controller("cpTopUpController")
@RequestMapping(value = "/cpservice/topup")
public class TopUpController extends BaseRest {

    @Autowired
    private TopUpService topUpService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getcard", method = RequestMethod.POST)
    @ResponseBody
    public Response getCard(@RequestParam(value = "id") String id) {
        try {
            TopUp byId = topUpService.getById(id);
            return new Response(true, "", byId);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/getbyids", method = RequestMethod.POST)
    @ResponseBody
    public Response getByIds(@RequestBody List<String> userIds) {
        try {
            List<User> userByIds = userService.getUserByIds(userIds);
            return new Response(true, "", userByIds);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }
}
