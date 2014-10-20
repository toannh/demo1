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
import vn.chodientu.entity.form.NewsForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.NewsService;

/**
 *
 * @author Phuongdt
 */
@Controller("cpNewsService")
@RequestMapping(value = "/cpservice/news")
public class NewsController extends BaseRest {

    @Autowired
    private NewsService newsService;

    /**
     * Thêm tin tức mới
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@ModelAttribute NewsForm news) throws Exception {
        return newsService.add(news);
    }
    
    /**
     * Lấy thông tin tin tức theo ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam String id) throws Exception {
        try {
            return new Response(true,null,newsService.getById(id));
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
        
      
    }
    /**
     * Sửa thông tin tin tức
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@ModelAttribute NewsForm news) throws Exception {
            return newsService.edit(news);

    }
    /**
     * Sửa trạng thái tin tức
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editstatus", method = RequestMethod.GET)
    @ResponseBody
    public Response editstatus(@RequestParam String id) throws Exception {
            return newsService.editStatus(id);

    }
    /**
     * Sửa trạng thái tin tức
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response del(@RequestParam String id) throws Exception {
            return newsService.delete(id);

    }
   

}
