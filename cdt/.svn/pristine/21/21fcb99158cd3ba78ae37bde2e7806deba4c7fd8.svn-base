/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.SellerCustomer;
import vn.chodientu.entity.db.SellerEmailMarketing;
import vn.chodientu.entity.db.SellerSmsMarketing;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.SellerCustomerService;
import vn.chodientu.service.SellerMarketingService;

/**
 *
 * @author Phuc
 */
@Controller("sellerEmailMarketing")
@RequestMapping("/emailmarketing")
public class EmailMarketingController extends BaseRest {

    @Autowired
    private SellerMarketingService sellerMarketingService;
    @Autowired
    private SellerCustomerService customerService;

    /**
     * Tạo email marketing
     *
     * @param sellerEmail
     * @return
     */
    @RequestMapping(value = "/createemail", method = RequestMethod.POST)
    @ResponseBody
    public Response addEmailMarketing(@RequestBody SellerEmailMarketing sellerEmail) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập trước !");
        }
        List<String> email = sellerEmail.getEmail();
        List<String> emails = new ArrayList<>();
        if (email != null && email.size() > 0) {
            List<SellerCustomer> sellerCustomers = customerService.getById(email);
            for (SellerCustomer sellerCustomer : sellerCustomers) {
                emails.add(sellerCustomer.getEmail());
            }
        }
        sellerEmail.setEmail(emails);
        return sellerMarketingService.addEmail(sellerEmail, viewer.getUser());
    }

    /**
     * Câp nhật email marketing
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/updateEmail", method = RequestMethod.POST)
    @ResponseBody
    public Response updateEmailMarketing(@RequestBody SellerEmailMarketing email) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập trước !");
        }
        email.setSellerId(viewer.getUser().getId());
        return sellerMarketingService.editEmail(email, viewer.getUser());
    }

    /**
     * Xem trước email maketing
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    @ResponseBody
    public Response previewEmail(@RequestBody SellerEmailMarketing email) throws Exception {
        if (viewer.getUser() != null) {
            email.setSellerId(viewer.getUser().getId());
        }
        String template = sellerMarketingService.getTemplate(email, email.getTemplate());
        return new Response(true, "ok", template);
    }

    @RequestMapping(value = "/trysendmail", method = RequestMethod.POST)
    @ResponseBody
    public Response trySendMail(@RequestBody SellerEmailMarketing email) throws Exception {
        try {
            email.setSendTime(System.currentTimeMillis() + 1000);
            sellerMarketingService.processEmailMarketing(email);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new Response(true, "Gửi thử Email thành công !");
    }

    @RequestMapping(value = "/createsms", method = RequestMethod.POST)
    @ResponseBody
    public Response addSmsMarketing(@RequestBody SellerSmsMarketing smsMarketing) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập trước !");
        }
        List<String> phone = smsMarketing.getPhone();
        List<String> phones = new ArrayList<>();
        if (phone != null && phone.size() > 0) {
            List<SellerCustomer> sellerCustomers = customerService.getById(phone);
            for (SellerCustomer sellerCustomer : sellerCustomers) {
                phones.add(sellerCustomer.getPhone());
            }
            smsMarketing.setPhone(phones);
        }
        return sellerMarketingService.addPhone(smsMarketing, viewer.getUser());
    }

    @RequestMapping(value = "/editsms", method = RequestMethod.POST)
    @ResponseBody
    public Response editSmsMarketing(@RequestBody SellerSmsMarketing smsMarketing) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập trước !");
        }
        smsMarketing.setSellerId(viewer.getUser().getId());
        return sellerMarketingService.editPhone(smsMarketing, viewer.getUser());
    }

}
