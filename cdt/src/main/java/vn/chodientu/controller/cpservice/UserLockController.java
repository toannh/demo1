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
import vn.chodientu.entity.db.CashHistory;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.db.UserLock;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CashHistoryService;
import vn.chodientu.service.CashService;
import vn.chodientu.service.UserLockService;

/**
 *
 * @author Phuongdt
 */
@Controller("cpUserLockController")
@RequestMapping(value = "/cpservice/userlock")
public class UserLockController extends BaseRest {

    @Autowired
    private UserLockService userLockService;

    @RequestMapping(value = "/stoprun", method = RequestMethod.POST)
    @ResponseBody
    public Response unLock(@RequestParam(value = "id") String id) {
        try {
            UserLock stopRun = userLockService.stopRun(id);
            return new Response(true, "Tài khoản được mở khóa", stopRun);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

}
