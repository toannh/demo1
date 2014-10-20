package vn.chodientu.controller.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.form.UpScheduleForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.UpScheduleService;

@Controller("serviceUpSchedule")
@RequestMapping("/upschedule")
public class UpScheduleController extends BaseRest {

    @Autowired
    private UpScheduleService upScheduleService;
    @Autowired
    private Gson gson;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody UpScheduleForm upScheduleForm) throws Exception {
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn cần đăng nhập lại để thực hiện chức năng này");
        }
        return upScheduleService.add(upScheduleForm, viewer.getUser());
    }

    @RequestMapping(value = "/getbyupScheduleid", method = RequestMethod.GET)
    @ResponseBody
    public Response getByUpScheduleId(@RequestParam("id") String upScheduleid) {
        return upScheduleService.getByUpSchedule(upScheduleid);
    }

    @RequestMapping(value = "/removes", method = RequestMethod.GET)
    @ResponseBody
    public Response removeScheduleByIds(@RequestParam("ids") String ids) {
        try {
            List<String> upIds = gson.fromJson(ids, new TypeToken<List<String>>() {
            }.getType());
            upScheduleService.removesByIds(upIds);
            return new Response(true, "Xóa thành công !");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }
   
}
