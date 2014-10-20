/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Message;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.input.UserSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.MessageService;
import vn.chodientu.service.UserService;

/**
 *
 * @author TheHoa
 */
@Controller("serviceMessage")
@RequestMapping("/message")
public class MessageController extends BaseRest {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/sendquestion", method = RequestMethod.POST)
    @ResponseBody
    public Response sendQuestion() {

        return null;
    }

    @RequestMapping(value = "/sendmessage", method = RequestMethod.POST)
    @ResponseBody
    public Response sendMessage(@RequestBody Message message) {
        if (viewer == null || viewer.getAdministrator() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        try {
            return messageService.send(message.getToEmail(), message.getSubject(), message.getContent(), null, null);
        } catch (Exception e) {
            return new Response(false, null, e.getMessage());
        }
    }

    @RequestMapping(value = "/replymessage", method = RequestMethod.POST)
    @ResponseBody
    public Response replyMessage(@RequestBody Message message) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        try {
            String itemId = message.getItemId();
            if (itemId == null || itemId.equals("")) {
                itemId = null;
            }
            return messageService.send(message.getToEmail(), message.getSubject(), message.getContent(), null, itemId);
        } catch (Exception e) {
            return new Response(false, null, e.getMessage());
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam String id) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        try {
            Message read = messageService.read(id);
            if (read.getItemId() != null && !read.getItemId().equals("")) {
                Item item = itemService.get(read.getItemId());
                if (item != null) {
                    List<String> get = imageService.get(ImageType.ITEM, item.getId());
                    if (get != null && !get.isEmpty()) {
                        item.setImages(new ArrayList<String>());
                        item.getImages().add(imageService.getUrl(get.get(0)).thumbnail(68, 68, "outbound").getUrl());
                    }
                    if (item.getSellerName() == null || item.getSellerName().equals("")) {
                        User user = userService.get(item.getSellerId());
                        item.setSellerName(user.getName());
                    }
                    read.setItem(item);
                }
            }
            return new Response(true, null, read);
        } catch (Exception e) {
            return new Response(false, null, e.getMessage());
        }
    }

    @RequestMapping(value = "/getreport", method = RequestMethod.GET)
    @ResponseBody
    public Response getreport() {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        try {
            HashMap<String, Long> report = messageService.report(viewer.getUser().getId());
            return new Response(true, null, report);
        } catch (Exception e) {
            return new Response(false, null, e.getMessage());
        }
    }

    @RequestMapping(value = "/markedunread", method = RequestMethod.POST)
    @ResponseBody
    public Response markedUnread(@RequestBody List<String> ids) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        List<Message> markedUnread = messageService.markedUnread(ids);
        return new Response(true, null, markedUnread);
    }

    @RequestMapping(value = "/deletemess", method = RequestMethod.POST)
    @ResponseBody
    public Response deletemess(@RequestBody List<String> ids) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        messageService.delete(ids, false);
        return new Response(true, "Đã xóa thành công");
    }

    @RequestMapping(value = "/getbyorderids", method = RequestMethod.POST)
    @ResponseBody
    public Response getbyorderids(@RequestBody List<String> ids, @RequestParam String fromUserId) throws Exception {
        List<Message> messages = messageService.getByOrderIds(ids, fromUserId);
        List<String> orderIds = new ArrayList<>();
        for (Message message : messages) {
            if (!orderIds.contains(message.getOrderId())) {
                orderIds.add(message.getOrderId());
            }
        }
        return new Response(true, null, orderIds);
    }

    @RequestMapping(value = "/getuserbyemail", method = RequestMethod.GET)
    @ResponseBody
    public Response getuserbyemail(@RequestParam String email) {
        UserSearch userSearch = new UserSearch();
        userSearch.setEmail(email);
        userSearch.setActive(1);
        userSearch.setEmailVerified(1);
        userSearch.setPageSize(10);
        userSearch.setPageIndex(0);
        DataPage<User> search = userService.search(userSearch);
        return new Response(true, null, search);
    }

}
