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
import vn.chodientu.service.EmailNewsletterService;

@Controller("cpEmailNewsletterService")
@RequestMapping(value = "/cpservice/emailnewsletter")
public class EmailController extends BaseRest {

    @Autowired
    private EmailNewsletterService emailNewsletterService;

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response delEmail(@RequestParam(value = "email", defaultValue = "") String email) {
        return emailNewsletterService.delEmail(email);
    }

}
