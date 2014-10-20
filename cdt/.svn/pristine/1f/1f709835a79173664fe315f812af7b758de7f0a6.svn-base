package vn.chodientu.controller.cpservice.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.input.ManufacturerSearch;
import vn.chodientu.entity.input.ModelSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.ModelRepository;
import vn.chodientu.service.ManufacturerService;
import vn.chodientu.service.ModelService;
import vn.chodientu.entity.db.ModelProperty;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ImageService;

/**
 * @since May 10, 2014
 * @author Phu
 */
@Controller("cpGlobalModelService")
@RequestMapping("/cpservice/global/model")
public class ModelController extends BaseRest {
    
    @Autowired
    private ImageService imageService;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private ModelService modelService;

    /**
     * Lấy thông tin chi tiết model theo id
     *
     * @param modelId
     * @return
     */
    @RequestMapping(value = "/getmodel", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        Model model = modelService.getModel(id);
        if (model == null) {
            throw new Exception("Model không tồn tại");
        }
        List<String> images = model.getImages();
        List<String> imgs = new ArrayList<>();
        for (String img : images) {
            imgs.add(imageService.getUrl(img).thumbnail(100, 100, "outbound").getUrl());   
        }
        model.setImages(imgs);
        return new Response(true, "Thông tin chi tiết model", model);
    }

    /**
     * autocomplate mf
     *
     * @param keyword
     * @param cateId
     * @return
     */
    @RequestMapping(value = "/searchmf", method = RequestMethod.GET)
    @ResponseBody
    public Response searchmf(@RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "cateId", defaultValue = "") String cateId) {
        
        ManufacturerSearch search = new ManufacturerSearch();
        search.setName(keyword.trim());
        if (cateId != null && !"".equals(cateId.trim()) && !"0".equals(cateId.trim())) {
            search.setCategoryId(cateId);
        }
        
        search.setPageIndex(0);
        search.setPageSize(100);
        DataPage<Manufacturer> dataPage = manufacturerService.search(search);
        return new Response(true, "Danh sách thương hiệu", dataPage);
    }

    /**
     * gen images
     *
     * @param id
     * @throws Exception
     */
    public void genImages(Model model) throws Exception {
        List<String> imgs = new ArrayList<>();
        for (String img : model.getImages()) {
            imgs.add(imageService.getUrl(img).thumbnail(100, 100, "outbound").getUrl());
        }
        model.setImages(imgs);
    }

    /**
     * autocomplate model
     *
     * @param keyword
     * @param cateId
     * @param manufacturerId
     * @return
     */
    @RequestMapping(value = "/searchmodel", method = RequestMethod.GET)
    @ResponseBody
    public Response searchmodel(@RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "categoryId", defaultValue = "") String cateId,
            @RequestParam(value = "manufacturerId", defaultValue = "") String manufacturerId) {
        
        ModelSearch search = new ModelSearch();
        search.setKeyword(keyword);
        if (cateId != null && !"".equals(cateId.trim()) && !"0".equals(cateId.trim())) {
            search.setCategoryId(cateId);
        }
        if (manufacturerId != null && !"".equals(manufacturerId.trim()) && !"0".equals(manufacturerId.trim())) {
            search.setManufacturerIds(new ArrayList<String>());
            search.getManufacturerIds().add(manufacturerId);
        }
        search.setPageIndex(0);
        search.setPageSize(100);
        DataPage<Model> dataPage = modelService.search(search);
        return new Response(true, "Danh sách model", dataPage);
    }

    /**
     * Lấy thông tin thuộc tính model theo id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getproperties", method = RequestMethod.GET)
    @ResponseBody
    public Response getProperties(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        Model model = modelService.getModel(id);
        if (model == null) {
            throw new Exception("Model không tồn tại");
        }
        HashMap<String, Object> data = new HashMap<>();
        List<ModelProperty> properties = modelService.getProperties(id);
        data.put("model", model);
        data.put("properties", properties);
        data.put("categoryProperties", categoryService.getProperties(model.getCategoryId()));
        data.put("categoryPropertyValues", categoryService.getPropertyValuesWithCategoryId(model.getCategoryId()));
        
        return new Response(true, "Thông tin model", data);
    }
    
}
