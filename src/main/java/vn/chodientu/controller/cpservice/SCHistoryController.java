/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.ScHistory;
import vn.chodientu.service.SCHistoryService;

/**
 *
 * @author Admin
 */
@Controller("cpHistoryService")
@RequestMapping(value = "/cpservice/schistory")
public class SCHistoryController extends BaseRest {

    @Autowired
    private SCHistoryService historyService;

    @ResponseBody
    @RequestMapping(value = "/gethistory", method = RequestMethod.GET)
    public ScHistory getHistory(@RequestParam("id") String id) {
        return historyService.get(id);
    }

}
