package vn.chodientu.controller.cpservice.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.input.UserSearch;
import vn.chodientu.service.UserService;

/**
 * @since May 10, 2014
 * @author Phu
 */
@Controller("cpGlobalUserService")
@RequestMapping("/cpservice/global/user")
public class UserController extends BaseRest {
    
    @Autowired
    private UserService userService;

    /**
     * autocomplate user
     *
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/searchuser", method = RequestMethod.GET)
    @ResponseBody
    public Response searchmodel(@RequestParam(value = "keyword", defaultValue = "") String keyword) {
        
        UserSearch search = new UserSearch();
        search.setUsername(keyword);
        
        search.setPageIndex(0);
        search.setPageSize(100);
        DataPage<User> dataPage = userService.search(search);
        return new Response(true, "Danh sách seller", dataPage);
    }
    
}
