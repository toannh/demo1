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
import vn.chodientu.entity.db.EmailOutbox;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.EmailOutboxRepository;
import vn.chodientu.service.EmailService;

/**
 *
 * @author thunt
 */
@Controller("cpEmailOutBoxService")
@RequestMapping(value = "/cpservice/emailoutbox")
public class EmailOutboxController extends BaseRest {

    @Autowired
    private EmailOutboxRepository emailOutboxRepository;
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        EmailOutbox email = emailOutboxRepository.find(id);
        if (email == null) {
            return new Response(false, "Không tìm thấy email có mã" + id);
        }
        return new Response(true, "Thông tin tài khoản", email);
    }
    @RequestMapping(value = "/resent", method = RequestMethod.GET)
    @ResponseBody
    public Response reSend(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        return emailService.reSend(id);
    }

}
