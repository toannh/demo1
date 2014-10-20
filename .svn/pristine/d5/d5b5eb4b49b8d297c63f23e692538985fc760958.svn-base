package vn.chodientu.controller.cp;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Message;
import vn.chodientu.entity.input.MessageSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.MessageService;

@Controller("message")
@RequestMapping("/cp/message")
public class MessageController extends BaseCp {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String list(ModelMap model, HttpSession session, @RequestParam(value = "page", defaultValue = "0") int page) {
        MessageSearch search = new MessageSearch();

        if (session.getAttribute("search") != null && page != 0) {
            search = (MessageSearch) session.getAttribute("search");
        } else {
            session.setAttribute("search", search);
        }
        search.setPageIndex(page - 1);
        search.setPageSize(50);
        search.setSort(1);
        DataPage<Message> messagePage = messageService.search(search);

        model.put("messagePage", messagePage);
        model.put("clientScript", "message.init();"); 
        model.put("search", search);
        return "cp.message";
    }

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    public String search(ModelMap model,
            HttpSession session,
            @ModelAttribute MessageSearch search) {
        session.setAttribute("search", search);
        search.setPageIndex(0);
        search.setPageSize(50);
        search.setSort(1);
        DataPage<Message> messagePage = messageService.search(search);

        model.put("messagePage", messagePage);
        model.put("search", search);
        model.put("clientScript", "message.init();"); 
        return "cp.message";
    }

}
