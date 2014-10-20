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
import vn.chodientu.entity.db.CashHistory;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CashHistoryService;

/**
 *
 * @author Phuongdt
 */
@Controller("cpCashHistoryService")
@RequestMapping(value = "/cpservice/cashhistory")
public class CashHistoryController extends BaseRest {

    @Autowired
    private CashHistoryService cashHistoryService;

    @RequestMapping(value = "/fine", method = RequestMethod.POST)
    @ResponseBody
    public Response fine(@RequestParam(value = "id") String id, @RequestParam(value = "note", defaultValue = "") String note, @RequestParam(value = "unappro", defaultValue = "false") boolean unappro) {
        try {
            CashHistory fine = cashHistoryService.fine(id, note, unappro);
            return new Response(true, "Phạt xèng thành công", fine);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/getfine", method = RequestMethod.POST)
    @ResponseBody
    public Response getFine(@RequestParam(value = "id") String id) {
        try {
            long fine = cashHistoryService.getfine(id);
            return new Response(true, "", fine);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

}
