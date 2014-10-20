package vn.chodientu.controller.user;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Message;
import vn.chodientu.entity.input.MessageSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.MessageService;

/**
 * @since May 20, 2014
 * @author Phu
 */
@Controller
@RequestMapping("/user")
public class MessageController extends BaseUser {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/quan-ly-thu", method = RequestMethod.GET)
    public String list(ModelMap model, @RequestParam(value = "page", defaultValue = "1") int page, HttpSession session,
            @RequestParam(value = "tab", defaultValue = "", required = false) String tab) {

        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/quan-ly-thu.html";
        }
        MessageSearch messageSearch = new MessageSearch();

        if (session.getAttribute("messageSearch") != null && page != 0) {
            messageSearch = (MessageSearch) session.getAttribute("messageSearch");
        } else {
            session.setAttribute("messageSearch", messageSearch);
        }
        messageSearch.setPageIndex(page - 1);
        messageSearch.setPageSize(20);
        messageSearch.setToUserId(viewer.getUser().getId());
        messageSearch.setToEmail(viewer.getUser().getEmail());
        if (!tab.equals("")) {
            switch (tab) {
                case "unread":
                    messageSearch.setRead(2);
                    messageSearch.setFromEmail(null);
                    break;
                case "inbox":
                    messageSearch.setRead(0);
                    messageSearch.setFromEmail(null);
                    break;
                case "sent":
                    messageSearch.setRead(0);
                    messageSearch.setFromEmail(viewer.getUser().getEmail());
                    messageSearch.setToUserId(null);
                    messageSearch.setToEmail(null);
                    break;
                default:
                    messageSearch.setRead(0);
                    messageSearch.setFromEmail(null);
                    break;

            }
        } else {
            messageSearch.setRead(0);
            messageSearch.setFromEmail(null);
        }

        DataPage<Message> search = messageService.search(messageSearch);
        model.put("messages", search);
        model.put("tab", tab);
        model.put("clientScript", "message.init();");
        model.put("messageSearch", messageSearch);
        return "user.message";
    }

    @RequestMapping(value = {"/quan-ly-thu"}, method = RequestMethod.POST)
    public String search(
            @ModelAttribute("messageSearch") MessageSearch messageSearch,
            HttpSession session,
            ModelMap model, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/quan-ly-thu.html";
        }
        if (page > 0) {
            messageSearch.setPageIndex(page - 1);
        } else {
            messageSearch.setPageIndex(0);
        }
        messageSearch.setPageSize(20);
        messageSearch.setToUserId(viewer.getUser().getId());
        messageSearch.setToEmail(viewer.getUser().getEmail());

        DataPage<Message> search = messageService.search(messageSearch);

        model.put("messages", search);
        model.put("clientScript", "message.init();");
        model.put("messageSearch", messageSearch);
        return "user.message";
    }

    @RequestMapping(value = {"/soan-thu"}, method = RequestMethod.GET)
    public String search(ModelMap model) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/soan-thu.html";
        }
        model.put("clientScript", "message.init();");
        return "user.sendmessage";
    }
}
