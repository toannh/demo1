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
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.SmsService;

/**
 *
 * @author thunt
 */
@Controller("cpSmsOutBoxService")
@RequestMapping(value = "/cpservice/smsoutbox")
public class SmsOutboxController extends BaseRest {

    @Autowired
    private SmsService smsService;

    @RequestMapping(value = "/resent", method = RequestMethod.GET)
    @ResponseBody
    public Response reSend(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        return smsService.reSend(id);
    }

}
