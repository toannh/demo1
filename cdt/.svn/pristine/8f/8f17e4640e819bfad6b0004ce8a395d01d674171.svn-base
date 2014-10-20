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
import vn.chodientu.entity.db.HeartBanner;
import vn.chodientu.entity.form.HeartBannerForm;
import vn.chodientu.entity.form.NewsForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.HeartBannerService;

/**
 *
 * @author Phuongdt
 */
@Controller("cpHeartBannerService")
@RequestMapping(value = "/cpservice/heartbanner")
public class HeartBannerController extends BaseRest {

    @Autowired
    private HeartBannerService heartBannerService;

    /**
     * Thêm Heart Banner
     *
     * @param heartBanner
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@ModelAttribute HeartBannerForm heartBanner) throws Exception {
        return heartBannerService.add(heartBanner);
    }
    /**
     * Sửa Heart Banner
     *
     * @param heartBanner
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@ModelAttribute HeartBannerForm heartBanner) throws Exception {
        return heartBannerService.edit(heartBanner);
    }

    @RequestMapping(value = "/editstatus", method = RequestMethod.GET)
    @ResponseBody
    public Response editstatus(@RequestParam String id) throws Exception {
        return heartBannerService.editStatus(id);
    }

    /**
     * Lấy thông tin tin tức theo ID
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam String id) throws Exception {
        try {
            return new Response(true,null,heartBannerService.getbyId(id));
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }

    }
    /**
     * Thay đổi vị trí heart banner
     * @param id
     * @param order
     * @return 
     */
     @ResponseBody
    @RequestMapping(value = "/changeorder", method = RequestMethod.GET)
    public Response changeOrder(
            @RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "position", defaultValue = "0") int order
            ){
        return heartBannerService.changeOrder(id, order);
    }
    /**
     * Xóa heart banner theo id
     * @param id
     * @return 
     */
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    @ResponseBody
    public Response remove(@RequestParam(value = "id") String id){      
            return heartBannerService.remove(id);
    }

}
