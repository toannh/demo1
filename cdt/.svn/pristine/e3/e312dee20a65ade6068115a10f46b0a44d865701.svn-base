/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.form.NewsHomeBoxForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.NewsHomeBoxService;

/**
 *
 * @author Phuongdt
 */
@Controller("cpNewsHomeBoxService")
@RequestMapping(value = "/cpservice/newshomebox")
public class NewsHomeBoxController extends BaseRest {
    
    @Autowired
    private NewsHomeBoxService newsHomeBoxService;

    /**
     * Thêm tin tức mới và box trang chủ
     *
     * @param homeBoxForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody NewsHomeBoxForm homeBoxForm) throws Exception {
        return newsHomeBoxService.addNewHomeBox(homeBoxForm);
    }

    /**
     * Sửa trạng thái tin tức
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response del(@RequestParam String id) throws Exception {
        return newsHomeBoxService.del(id);
        
    }
    
}
