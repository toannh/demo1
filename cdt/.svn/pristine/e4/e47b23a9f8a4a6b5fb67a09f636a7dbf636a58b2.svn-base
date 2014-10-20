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
import vn.chodientu.entity.db.Administrator;
import vn.chodientu.entity.db.ModelLog;
import vn.chodientu.entity.enu.ModelLogStatus;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.AdministratorService;
import vn.chodientu.service.ModelLogService;
import vn.chodientu.service.ModelService;

/**
 *
 * @author thunt
 */
@Controller("cpReviewModelService")
@RequestMapping(value = "/cpservice/reviewmodel")
public class ReviewModelController extends BaseRest {

    @Autowired
    private ModelService modelService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private ModelLogService modelLogService;
    
    @RequestMapping(value = "/revieweditmodel", method = RequestMethod.POST)
    @ResponseBody
    public Response revieweditmodel(@RequestBody ModelLog modelLog) throws Exception {
        try {
            ModelLogStatus status = modelLog.getStatus();
            boolean appr = false;
            if (status == status.EDITED) {
                appr = true;
            } else {
                appr = false;
            }
            String userUpdate = modelLog.getNextUpdaterId();
            if (userUpdate != null) {
                Administrator admins = administratorService.findByEmail(userUpdate);
                if (admins == null) {
                    return new Response(false, "Người sửa Model không tồn tại");
                }else{
                    userUpdate = admins.getId();
                }
            }else{
                userUpdate = null;
            }
            modelService.approve(modelLog.getModelId(), viewer.getAdministrator().getId(), userUpdate, modelLog.getMessage(), appr);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }

        return new Response(true, "Báo cáo sửa hoàn tất");
    }
    @RequestMapping(value = "/getdetailmodellog", method = RequestMethod.GET)
    @ResponseBody
    public Response getDetailModelLog(@RequestParam String id) throws Exception {
        ModelLog modelLog = modelLogService.getLastLog(id);
        String updaterEmail = null;
        if(modelLog==null){
            modelLog = null; 
            return new Response(false, "");
        }else{
            updaterEmail = administratorService.getAdministrator(modelLog.getUpdaterId()).getEmail();
        }
        return new Response(true, null,updaterEmail);
    }

}
