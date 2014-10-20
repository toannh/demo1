/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.ShopNews;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.ShopNewsForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ShopNewsService;

/**
 *
 * @author TheHoa
 */
@Controller("serviceShopNews")
@RequestMapping("/shopnews")
public class ShopNewsController extends BaseRest {

    @Autowired
    private ShopNewsService shopNewsService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@ModelAttribute ShopNewsForm newsForm) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        ShopNews news = new ShopNews();
        news.setTitle(newsForm.getTitle());
        news.setCategoryId(newsForm.getCategoryId());
        news.setDetail(newsForm.getDetail());
        news.setActive(true);
        Response add = shopNewsService.add(news, viewer.getUser());
        if (newsForm.getImage() != null && !newsForm.getImage().isEmpty()) {
            shopNewsService.addImage(news.getId(), newsForm.getImage());
        }
        return add;
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response del(@RequestParam String id) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        return shopNewsService.remove(id, viewer.getUser());
    }

    @RequestMapping(value = "/delall", method = RequestMethod.GET)
    @ResponseBody
    public Response delAll(@RequestParam List<String> ids) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        return shopNewsService.remove(ids, viewer.getUser());
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@ModelAttribute ShopNewsForm newsForm) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        ShopNews news = new ShopNews();
        news.setId(newsForm.getId());
        news.setUserId(viewer.getUser().getId());
        news.setTitle(newsForm.getTitle());
        news.setCategoryId(newsForm.getCategoryId());
        news.setDetail(newsForm.getDetail());
        news.setActive(newsForm.isActive());
        news.setUpdateTime(System.currentTimeMillis());
        Response edit = shopNewsService.edit(news, viewer.getUser());
        if (newsForm.getImage() != null && !newsForm.getImage().isEmpty()) {
            List<String> get = imageService.get(ImageType.SHOP_NEWS, newsForm.getId());
            if (get != null && get.size() > 0) {
                imageService.deleteById(ImageType.SHOP_NEWS, newsForm.getId(), get.get(0));
            }
            imageService.upload(newsForm.getImage(), ImageType.SHOP_NEWS, newsForm.getId());
        }
        return edit;
    }
}
