/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.component.Validator;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.enu.EmailOutboxType;
import vn.chodientu.entity.form.ShopContactForm;
import vn.chodientu.entity.form.UserContactForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.EmailService;
import vn.chodientu.service.ShopContactService;
import vn.chodientu.service.ShopService;

/**
 *
 * @author Phuongdt
 */
@Controller("serviceShopContact")
@RequestMapping("/shopcontact")
public class ShopContactController extends BaseRest {

    @Autowired
    private ShopService shopService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private Validator validator;
    @Autowired
    private ShopContactService contactService;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public Response send(@RequestBody ShopContactForm contactForm) throws Exception {
        Map<String, String> error = validator.validate(contactForm);
        if (!error.isEmpty()) {
            return new Response(false, null, error);
        } else {
            Shop byAlias = shopService.getByAlias(contactForm.getAlias());
            Map<String, Object> data = new HashMap<>();
            data.put("name_shop", byAlias.getAlias());
            data.put("username", contactForm.getName());
            data.put("phone", contactForm.getPhone());
            data.put("content", contactForm.getContent());
            data.put("email", contactForm.getEmail());

            emailService.send(EmailOutboxType.SHOP_CONTACT, byAlias.getEmail(), "Liên hệ từ khách hàng", "shopcontact", data);
            return new Response(true, "Gửi liên hệ thành công tới chủ Shop");
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody UserContactForm form) {
        return contactService.addUserContact(form);
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response delete(@RequestParam String id) {
        contactService.del(id);
        return new Response(true, "Xóa thành công!");
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam String id) throws Exception {
        return new Response(true, "", contactService.getContact(id));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@RequestBody UserContactForm contactForm) throws Exception {
        return contactService.editUserContact(contactForm);
    }

}
