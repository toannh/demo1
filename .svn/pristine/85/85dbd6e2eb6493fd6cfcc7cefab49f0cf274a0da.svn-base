/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.chodientu.entity.db.HomeBanner;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.service.HomeBannerService;
import vn.chodientu.service.ImageService;

/**
 *
 * @author toannh
 */
@Controller("cpHomeBanner")
@RequestMapping("/cp/homebanner")
public class HomeBannerController extends BaseCp {

    @Autowired
    private ImageService imageService;
    @Autowired
    private HomeBannerService homebannerService;

    @RequestMapping(method = RequestMethod.GET)
    public String homebanner(ModelMap map) throws Exception {
        List<HomeBanner> lst = homebannerService.getHomeBanner();
        for (HomeBanner homeBanner : lst) {
            List<String> images = imageService.get(ImageType.HOME_BANNER, homeBanner.getId());
            if (images != null && images.size()>0) {
                
                homeBanner.setImage(imageService.getUrl(images.get(0)).thumbnail(200, 93, "outbound").getUrl());
            }
        }
        map.put("lst", lst);
        map.put("clientScript","homebanner.initHomeCategory();");
        return "cp.home.banner";
    }
}
