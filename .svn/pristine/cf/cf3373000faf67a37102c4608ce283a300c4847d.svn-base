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
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.db.SellerApi;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.input.ModelSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ApiHistoryService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ModelService;

@Controller("modelApiController")
@RequestMapping(value = "/api/model")
public class ModelController extends BaseApi {

    @Autowired
    private ModelService modelService;
    @Autowired
    private Gson gson;
    @Autowired
    private ApiHistoryService apiHistoryService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Response list(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            ModelSearch search = gson.fromJson(data.getParams(), ModelSearch.class);
            if (search == null) {
                return new Response(false, "Tham số chưa chính xác", "PARAM_EMPTY");
            }
            if (search.getPageSize() <= 0) {
                search.setPageSize(100);
            }
            DataPage<Model> dataPage = modelService.search(search);

            for (Model m : dataPage.getData()) {
                List<String> img = new ArrayList<>();
                if (m.getImages() != null && !m.getImages().isEmpty()) {
                    for (String image : m.getImages()) {
                        img.add(imageService.getUrl(image).getUrl());
                    }
                }
                m.setImages(img);
            }
            response = new Response(true, "Danh sách model", dataPage);
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }

}
