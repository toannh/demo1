package vn.chodientu.controller.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.output.ModelInfo;
import vn.chodientu.entity.db.CategoryProperty;
import vn.chodientu.entity.db.CategoryPropertyValue;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.db.ModelReviewLike;
import vn.chodientu.entity.db.ModelProperty;
import vn.chodientu.entity.db.ModelReview;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.input.ModelReviewSearch;
import vn.chodientu.entity.input.ModelSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ManufacturerService;
import vn.chodientu.service.ModelReviewLikeService;
import vn.chodientu.service.ModelReviewService;
import vn.chodientu.service.ModelService;
import vn.chodientu.service.UserService;

@Controller("serviceModel")
@RequestMapping("/model")
public class ModelController extends BaseRest {

    @Autowired
    private ModelService modelService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private ModelReviewService modelReviewService;
    @Autowired
    private ModelReviewLikeService commentLikeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/findbycatmf", method = RequestMethod.GET)
    @ResponseBody
    public Response findByCatMf(ModelInfo model,
            @RequestParam(value = "categoryId", defaultValue = "0") String categoryId,
            @RequestParam(value = "mfId", defaultValue = "0") String mfId,
            @RequestParam(value = "keyword", defaultValue = "") String keyword) throws Exception {
        ModelSearch modelSearch = new ModelSearch();
        modelSearch.setPageIndex(0);
        modelSearch.setPageSize(1000);
        if (!categoryId.equals("0")) {
            modelSearch.setCategoryId(categoryId);
        }
        if (!mfId.equals("0")) {
            modelSearch.setManufacturerIds(new ArrayList<String>());
            modelSearch.getManufacturerIds().add(mfId);
        }
        if(!keyword.equals("")) {
            modelSearch.setKeyword(keyword);
        }
        DataPage<Model> search = modelService.search(modelSearch);
        return new Response(true, "Danh sách model theo thương hiệu và model", search.getData());
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(ModelInfo model,
            @RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        Model mod = modelService.getModel(id);
        if (mod == null) {
            return new Response(false, "Không tìm thấy model yêu cầu");
        }
        ModelInfo modelMap = new ModelInfo();
        modelMap.setModel(mod);
        List<ModelProperty> properties = modelService.getProperties(id);
        List<String> categoryPropertyId = new ArrayList<>();
        List<String> categoryPropertyValueId = new ArrayList<>();
        for (ModelProperty modelProperty : properties) {
            if (!categoryPropertyId.contains(modelProperty.getCategoryPropertyId())) {
                categoryPropertyId.add(modelProperty.getCategoryPropertyId());
            }
            if (modelProperty.getCategoryPropertyValueIds() != null && !modelProperty.getCategoryPropertyValueIds().isEmpty()) {
                for (String cpValue : modelProperty.getCategoryPropertyValueIds()) {
                    if (!categoryPropertyValueId.contains(cpValue)) {
                        categoryPropertyValueId.add(cpValue);
                    }
                }
            }
        }

        List<CategoryProperty> categoryPropertys = categoryService.getPropertyById(categoryPropertyId.toArray(new String[0]));
        List<CategoryPropertyValue> categoryPropertyValues = categoryService.getPropertyValue(categoryPropertyValueId.toArray(new String[0]));
        List<ModelInfo.ModelProperty> modelPropertys = new ArrayList<>();

        for (ModelProperty modelProperty : properties) {
            ModelInfo.ModelProperty mProperty = new ModelInfo.ModelProperty();
            for (CategoryProperty categoryProperty : categoryPropertys) {
                if (modelProperty.getCategoryPropertyId().equals(categoryProperty.getId())) {
                    mProperty.setCategoryProperty(categoryProperty);
                    break;
                }
            }
            if (modelProperty.getCategoryPropertyValueIds() != null && !modelProperty.getCategoryPropertyValueIds().isEmpty()) {
                List<CategoryPropertyValue> cpvs = new ArrayList<>();
                for (String cpValue : modelProperty.getCategoryPropertyValueIds()) {
                    for (CategoryPropertyValue categoryPropertyValue : categoryPropertyValues) {
                        if (cpValue.equals(categoryPropertyValue.getId())) {
                            cpvs.add(categoryPropertyValue);
                            break;
                        }
                    }
                }
                mProperty.setCategoryPropertyValue(categoryPropertyValues);
                mProperty.setInputValue(modelProperty.getInputValue());
            }
            modelPropertys.add(mProperty);
        }
        modelMap.setPropertis(modelPropertys);
        return new Response(true, "Chi tiết model", modelMap);
    }

    @RequestMapping(value = "/getinfo", method = RequestMethod.GET)
    @ResponseBody
    public Response getInfo(ModelInfo model,
            @RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        Model mod = modelService.getModel(id);
        if (mod == null) {
            return new Response(false, "Không tìm thấy model yêu cầu");
        }
        Manufacturer manufacturer = manufacturerService.getManufacturer(mod.getManufacturerId());
        if (manufacturer == null) {
            return new Response(false, "Không tìm thấy thương hiệu yêu cầu");
        }
        mod.setManufacturerName(manufacturer.getName());
        List<String> images = imageService.get(ImageType.MODEL, mod.getId());
        mod.setImages(new ArrayList<String>());
        for (String img : images) {
            try {
                mod.getImages().add(imageService.getUrl(img).thumbnail(300, 300, "outbound").getUrl());
            } catch (Exception e) {
            }
        }
        List<ModelProperty> properties = modelService.getProperties(id);
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("model", mod);
        hm.put("properties", properties);
        return new Response(true, "Chi tiết model", hm);
    }

    /**
     * Lấy danh sách bình luận đánh giá của model
     *
     * @param modelReviewSearch
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getmodelreview", method = RequestMethod.POST)
    @ResponseBody
    public Response getModelReview(@RequestBody ModelReviewSearch modelReviewSearch) throws Exception {
        List<String> userIds = new ArrayList<>();
        DataPage<ModelReview> search = modelReviewService.search(modelReviewSearch);
        if (search.getData() != null && !search.getData().isEmpty()) {
            for (ModelReview review : search.getData()) {
                if (review.getUserId() != null && !userIds.contains(review.getUserId())) {
                    userIds.add(review.getUserId());
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        List<User> userByIds = userService.getUserByIds(userIds);
        if (userByIds != null && !userByIds.isEmpty()) {
            for (User user : userByIds) {
                List<String> getImg = null;
                getImg = imageService.get(ImageType.AVATAR, user.getId());
                if (getImg != null && !getImg.isEmpty()) {
                    user.setAvatar(imageService.getUrl(getImg.get(0)).thumbnail(70, 65, "inset").getUrl());
                }
            }
        }
        map.put("reviewers", userByIds);
        map.put("modelReviews", search);
        return new Response(true, "Chi tiết model", map);
    }

    /**
     * thêm mới bình luận đánh giá
     *
     * @param modelReview
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sendreview", method = RequestMethod.POST)
    @ResponseBody
    public Response sendReview(@RequestBody ModelReview modelReview) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn cần đăng nhập để đăng bình luận");
        }
        modelReview.setUserId(viewer.getUser().getId());
        return modelReviewService.add(modelReview);
    }

    /**
     * like comment
     *
     * @param commentLike
     * @return
     */
    @RequestMapping(value = "/likecommnet", method = RequestMethod.POST)
    @ResponseBody
    public Response likeComment(@RequestBody ModelReviewLike commentLike) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn cần đăng nhập để like bình luận");
        }
        commentLike.setUserId(viewer.getUser().getId());
        return commentLikeService.add(commentLike);
    }

}
