/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.component.ImageClient;
import vn.chodientu.controller.cp.BaseCp;
import vn.chodientu.entity.data.ItemImageForm;
import vn.chodientu.entity.db.HomeBanner;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.HomeBannerForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.HomeBannerService;
import vn.chodientu.service.ImageService;

/**
 *
 * @author toannh
 */
@Controller
@RequestMapping("/cpservice/homebanner")
public class HomeBannerController extends BaseCp {

    @Autowired
    private ImageClient imageClient;
    @Autowired
    private HomeBannerService homebannerService;
    @Autowired
    private Gson gson;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Response save(@ModelAttribute HomeBannerForm homebannerForm) throws Exception {
        return homebannerService.save(homebannerForm);
    }

    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @ResponseBody
    public Response getHomebannerById(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        return homebannerService.get(id);
    }
    @RequestMapping(value = "/changestatus", method = RequestMethod.GET)
    @ResponseBody
    public Response changeStatus(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        return homebannerService.changeStatus(id);
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Response delete(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        return homebannerService.delete(id);
    }
}
