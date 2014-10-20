/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.form.SendMessageForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.MessageService;
import vn.chodientu.service.UserService;
import vn.chodientu.util.TextUtils;

@Controller("cpMessageService")
@RequestMapping(value = "/cpservice/message")
public class MessageController extends BaseRest {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/sendmessagecdt", method = RequestMethod.POST)
    @ResponseBody
    public Response sendMessageCDT(@RequestBody SendMessageForm form) throws Exception {
        if (form.getToEmail() != null && !form.getToEmail().trim().equals("")) {
            List<String> emails = new ArrayList<>();
            List<String> unemails = new ArrayList<>();
            for (String email : form.getToEmail().trim().split(",")) {
                if (!TextUtils.validateEmailString(email) || !userService.getByEmail(email).isSuccess()) {
                    if (!unemails.contains(email)) {
                        unemails.add(email);
                    }
                } else {
                    if (!emails.contains(email)) {
                        emails.add(email);
                    }
                }
            }
            Map<String, List<String>> map = new HashMap<>();
            if (emails != null && !emails.isEmpty()) {
                for (String em : emails) {
                    messageService.send(em, form.getSubject(), form.getContent(), "", "");
                }
            }
            map.put("emailSuccess", emails);
            map.put("emailUnSuccess", unemails);
            return new Response(true, "Gửi tin nhắn thành công!", map);
        }
        return new Response(false, "");
    }

}
