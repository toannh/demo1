/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Email;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.EmailNewsletterService;


@Controller("EmailNewsletter")
@RequestMapping("/email")
public class EmailController extends BaseRest {

    @Autowired
    private EmailNewsletterService emailNewsletterService;

    @RequestMapping(value = "/addemail", method = RequestMethod.POST)
    @ResponseBody
    public Response addEmail(@RequestBody Email email) {
        return emailNewsletterService.addEmail(email);
    }

}
