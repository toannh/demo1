package vn.chodientu.controller.api;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.entity.api.Request;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.input.ManufacturerSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ApiHistoryService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ManufacturerService;

@Controller("manufacturerApiController")
@RequestMapping(value = "/api/manufacturer")
public class ManufacturerController extends BaseApi {

    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private Gson gson;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ApiHistoryService apiHistoryService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Response list(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            ManufacturerSearch search = gson.fromJson(data.getParams(), ManufacturerSearch.class);
            if (search == null) {
                return new Response(false, "Tham số chưa chính xác", "PARAM_EMPTY");
            }
            if (search.getPageSize() <= 0) {
                search.setPageSize(100);
            }
            DataPage<Manufacturer> dataPage = manufacturerService.search(search);
            for (Manufacturer m : dataPage.getData()) {
                if (m.getImageUrl() != null && !m.getImageUrl().equals("")) {
                    m.setImageUrl(imageService.getUrl(m.getImageUrl()).getUrl());
                }
            }
            response = new Response(true, "Danh sách thương hiệu", dataPage);
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }

}
