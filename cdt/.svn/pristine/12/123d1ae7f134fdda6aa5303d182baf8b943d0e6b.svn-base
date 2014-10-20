/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.form.FeaturedNewsForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.NewsHomeBoxService;
import vn.chodientu.service.FeaturedNewsService;

/**
 *
 * @author Phuongdt
 */
@Controller("cpFeaturedNewsService")
@RequestMapping(value = "/cpservice/featurednews")
public class FeaturedNewsController extends BaseRest {

    @Autowired
    private NewsHomeBoxService newsHomeBoxService;
    @Autowired
    private FeaturedNewsService featuredNewsService;

    /**
     * Thêm tin tức mới vào box trang chủ
     *
     * @param otherNewsForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@ModelAttribute FeaturedNewsForm otherNewsForm) throws Exception {
        return featuredNewsService.add(otherNewsForm);
    }
    /**
     * Thêm tin tức mới vào box trang chủ
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam String id) throws Exception {
        return featuredNewsService.getById(id);
    }
    /**
     * Sửa tin tức nổi bật vào box trang chủ
     *
     * @param otherNewsForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@ModelAttribute FeaturedNewsForm otherNewsForm) throws Exception {
        return featuredNewsService.edit(otherNewsForm);
    }
    /**
     * Thêm tin tức mới vào box trang chủ
     *
     * @param otherNewsForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response del(@RequestParam String id) throws Exception {
        return featuredNewsService.del(id);
    }

}
