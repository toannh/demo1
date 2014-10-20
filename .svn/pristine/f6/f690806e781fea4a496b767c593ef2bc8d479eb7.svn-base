package vn.chodientu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.service.SearchIndexService;

/**
 *
 * @author Phu
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    
    @Autowired
    private SearchIndexService indexService;
    @Autowired
    private ItemRepository itemRepository;
    
    @RequestMapping(value = "/suggest", method = RequestMethod.GET)
    @ResponseBody
    public void suggest() throws Exception {
        indexService.processIndexItem(itemRepository.find("740614135158"));
    }
    
}
