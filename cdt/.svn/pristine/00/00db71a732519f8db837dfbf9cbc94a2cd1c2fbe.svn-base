package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.chodientu.service.CategoryService;

/**
 * @since Aug 22, 2014
 * @author Account
 */
@Controller("cpItemQuick")
@RequestMapping("/cp/itemquick")
public class ItemQuickController extends BaseCp{
    
    @Autowired
    private Gson gson;
    @Autowired
    private CategoryService categoryService;
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String postItemQuick(ModelMap map) {
        map.put("clientScript", "var scates=[]; var cates = " + gson.toJson(categoryService.getChilds(null)) + "; itemquick.init();");
        return "cp.item.quick";
    }
}
