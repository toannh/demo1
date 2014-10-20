package vn.chodientu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.SmsService;

/**
 *
 * @author thanhvv
 */
@Controller("smsApi")
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    /**
     * Nhận kết quả trả từ đầu số 8x55
     *
     * @param sender
     * @param message
     * @param receiver
     * @param messageid
     * @return
     */
    @RequestMapping(value = "/8x55", method = RequestMethod.GET)
    @ResponseBody
    public Response addSms(@RequestParam(value = "sender", defaultValue = "") String sender,
            @RequestParam(value = "message", defaultValue = "") String message,
            @RequestParam(value = "receiver", defaultValue = "") String receiver,
            @RequestParam(value = "messageid", defaultValue = "") String messageid) {

        if (messageid.equals("")) {
            return new Response(false, "messageid chua duoc nhap");
        }

        if (sender.equals("")) {
            return new Response(false, "Khong ton tai nguoi nhan tin");
        }

        if (receiver.equals("")) {
            return new Response(false, "Dau so khong duoc de trong");
        }
        if (message.equals("")) {
            return new Response(false, "Ban chua nhap cu phap");
        }

        try {
            String split = sender.substring(0, 2);
            String number = sender.substring(2);
            if (split.equals("84")) {
                sender = "0" + number;
            }
        } catch (Exception e) {
        }

        if (receiver.equals("8255")) {
            return smsService.addSms8255(message, messageid, sender);
        }

        if (receiver.equals("8755")) {
            return smsService.addSms8755(message, messageid, sender);
        }

        if (receiver.equals("8755")) {
            return smsService.addSms8755(message, messageid, sender);
        }

        if (receiver.equals("8155")) {
            return smsService.addSms8155(message, messageid, sender);
        }
        return new Response(false, "Dau so khong nam trong he thong chodientu.vn");
    }
}
