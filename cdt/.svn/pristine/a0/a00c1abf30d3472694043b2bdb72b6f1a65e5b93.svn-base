package vn.chodientu.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.input.ManufacturerSearch;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ManufacturerService;

@Controller("serviceManufacturer")
@RequestMapping("/manufacturer")
public class ManufacturerController extends BaseRest {

    @Autowired
    private ManufacturerService manufacturerService;

    @RequestMapping(value = "/searchbyname", method = RequestMethod.GET)
    @ResponseBody
    public Response searchByName(ModelMap model,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "categoryId", defaultValue = "0") String categoryId) throws Exception {
        ManufacturerSearch search = new ManufacturerSearch();
        search.setPageIndex(0);
        search.setPageSize(1000);
        if (!keyword.equals("")) {
            search.setName(keyword);
        }
        if (!categoryId.equals("0")) {
            search.setCategoryId(categoryId);
        }
        return new Response(true, "Danh sách thương hiệu", manufacturerService.search(search));
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Response search(ModelMap model,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "categoryId", defaultValue = "0") String categoryId) throws Exception {
        ManufacturerSearch search = new ManufacturerSearch();
        search.setPageIndex(page);
        search.setPageSize(24);
        if (!keyword.equals("")) {
            search.setName(keyword);
        }
        if (!categoryId.equals("0")) {
            search.setCategoryId(categoryId);
        }
        return new Response(true, "Danh sách thương hiệu", manufacturerService.search(search));
    }

}
