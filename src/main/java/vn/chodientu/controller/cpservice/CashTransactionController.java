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
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CashService;

/**
 *
 * @author Phuongdt
 */
@Controller("cpCashTransactionService")
@RequestMapping(value = "/cpservice/cashtransaction")
public class CashTransactionController extends BaseRest {

    @Autowired
    private CashService cashService;

    /**
     * Thêm support
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addsupport", method = RequestMethod.GET)
    @ResponseBody
    public Response addsupport(@RequestParam(value = "id") String id) throws Exception {
        CashTransaction addSupport = cashService.addSupport(id);
        return new Response(true, null, addSupport);
    }

    /**
     * Thêm note
     *
     * @param cashTransaction
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addnote", method = RequestMethod.POST)
    @ResponseBody
    public Response addnote(@RequestBody CashTransaction cashTransaction) throws Exception {
        CashTransaction addSupport = cashService.addNote(cashTransaction.getId(), cashTransaction.getNote());
        return new Response(true, null, addSupport);
    }
    /**
     * Cập nhật mã ngân lượng vào giao dịch xèng
     *
     * @param cashTransaction
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatenlid", method = RequestMethod.POST)
    @ResponseBody
    public Response updateNLId(@RequestBody CashTransaction cashTransaction) throws Exception {
        CashTransaction transaction = cashService.checkNLDataReturn(cashTransaction.getId(), cashTransaction.getNlTransactionId());
        return new Response(true, null, transaction);
    }

    /**
     * Lấy ra 1 giao dịch
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam(value = "id") String id) throws Exception {
        CashTransaction transaction = cashService.getTransaction(id);
        return new Response(true, null, transaction);
    }

}
