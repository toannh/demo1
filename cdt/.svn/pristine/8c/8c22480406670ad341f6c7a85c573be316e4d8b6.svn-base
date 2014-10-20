package vn.chodientu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.elasticsearch.index.query.AndFilterBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.NestedFilterBuilder;
import org.elasticsearch.index.query.QueryFilterBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.RangeFilterBuilder;
import org.elasticsearch.index.query.TermFilterBuilder;
import org.elasticsearch.index.query.TermsFilterBuilder;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.filter.FilterFacet;
import org.elasticsearch.search.facet.filter.FilterFacetBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.facet.request.NativeFacetRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.data.ModelMig;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.CategoryManufacturer;
import vn.chodientu.entity.db.CategoryProperty;
import vn.chodientu.entity.db.CategoryPropertyValue;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.db.ModelApprove;
import vn.chodientu.entity.db.ModelLog;
import vn.chodientu.entity.db.ModelProperty;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.ModelApproveStatus;
import vn.chodientu.entity.enu.ModelLogStatus;
import vn.chodientu.entity.enu.PropertyOperator;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.input.ModelSearch;
import vn.chodientu.entity.input.PropertySearch;
import vn.chodientu.entity.output.CategoryHistogram;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.ManufacturerHistogram;
import vn.chodientu.entity.output.PropertyHistogram;
import vn.chodientu.entity.output.PropertyValueHistogram;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.search.FacetResult;
import vn.chodientu.repository.CategoryManufacturerRepository;
import vn.chodientu.repository.CategoryPropertyRepository;
import vn.chodientu.repository.CategoryPropertyValueRepository;
import vn.chodientu.repository.CategoryRepository;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.ManufacturerRepository;
import vn.chodientu.repository.ModelApproveRepository;
import vn.chodientu.repository.ModelLogRepository;
import vn.chodientu.repository.ModelPropertyRepository;
import vn.chodientu.repository.ModelRepository;
import vn.chodientu.repository.ModelSearchRepository;
import vn.chodientu.util.TextUtils;

/**
 *
 * @author Admin
 */
@Service
public class ModelService {

    @Autowired
    private Validator validator;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryPropertyRepository categoryPropertyRepository;
    @Autowired
    private CategoryPropertyValueRepository categoryPropertyValueRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private ModelPropertyRepository modelPropertyRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ModelLogRepository modelLogRepository;
    @Autowired
    private ModelApproveRepository modelApproveRepository;
    @Autowired
    private ModelSearchRepository modelSearchRepository;
    @Autowired
    private CategoryManufacturerRepository categoryManufacturerRepository;
    @Autowired
    private SearchIndexService searchIndexService;

    /**
     * thêm mới model
     *
     * @param model
     * @param administratorId id người sửa
     * @return Kết quả kèm object Model mới thêm hoặc báo lỗi
     * @throws Exception
     */
    public Response add(Model model, String administratorId) throws Exception {
        Manufacturer manufacturer = null;
        Category category = null;

        model.setId(modelRepository.genId());

        Map<String, String> error = validator.validate(model);

        if (model.getManufacturerId() == null || model.getManufacturerId().equals("")) {
            error.put("manufacturerId", "Thương hiệu không được để trống");
        } else {
            manufacturer = manufacturerRepository.find(model.getManufacturerId());
            if (manufacturer == null) {
                error.put("manufacturerId", "Thương hiệu không tồn tại");
            }
        }

        if (model.getCategoryId() == null) {
            error.put("categoryId", "Danh mục không được để trống");
        } else {
            category = categoryRepository.find(model.getCategoryId());
            if (category == null) {
                error.put("categoryId", "Danh mục không tồn tại");
            } else if (!category.isActive()) {
                error.put("categoryId", "Danh mục đang không hoạt động");
            } else if (!category.isLeaf()) {
                error.put("categoryId", "Danh mục không phải là danh mục lá");
            }
        }

        if (error.isEmpty() && category != null) {
            model.setCategoryPath(category.getPath());
            long time = System.currentTimeMillis();
            model.setCreateTime(time);
            model.setUpdateTime(time);
            model.setApproved(false);
            modelRepository.save(model);

            model.setCategoryName(category.getName());
            model.setManufacturerName(manufacturer.getName());
            searchIndexService.processIndexModel(model);

            ModelApprove approve = new ModelApprove();
            approve.setModelId(model.getId());
            approve.setStatus(ModelApproveStatus.STATUS_CREATING);
            approve.setAdministratorId(administratorId);
            approve.setUpdateTime(time);
            modelApproveRepository.save(approve);

            return new Response(true, "Thêm mới model thành công", model);
        } else {
            return new Response(false, "Thông tin không hợp lệ", error);
        }
    }

    /**
     * Sửa model
     *
     * @param model
     * @param administratorId
     * @return Kết quả kèm object Model mới thêm hoặc báo lỗi
     * @throws Exception
     */
    public Response edit(Model model, String administratorId) throws Exception {
        Model oldModel = modelRepository.find(model.getId());

        if (oldModel == null) {
            throw new Exception("Model không tồn tại");
        }

        ModelApprove app = modelApproveRepository.find(model.getId());
        if (!(app.getStatus() == ModelApproveStatus.STATUS_CREATING || app.getStatus() == ModelApproveStatus.STATUS_EDITING) || !app.getAdministratorId().equals(administratorId)) {
            throw new Exception("Bạn không được phân công sửa model này");
        }

        Manufacturer manufacturer = null;
        Category category = null;
        Map<String, String> error = validator.validate(model);
        if (model.getManufacturerId() == null || model.getManufacturerId().equals("")) {
            error.put("manufacturerId", "Thương hiệu không được để trống");
        } else {
            manufacturer = manufacturerRepository.find(model.getManufacturerId());
            if (manufacturer == null) {
                error.put("manufacturerId", "Thương hiệu không tồn tại");
            }
        }
        if (model.getCategoryId() == null) {
            error.put("categoryId", "Danh mục không được để trống");
        } else {
            category = categoryRepository.find(model.getCategoryId());
            if (category == null) {
                error.put("categoryId", "Danh mục không tồn tại");
            } else if (!category.isActive()) {
                error.put("categoryId", "Danh mục đang bị khoá");
            } else if (!category.isLeaf()) {
                error.put("categoryId", "Danh mục không phải là danh mục lá");
            }
        }
        if (error.isEmpty() && category != null) {
            oldModel.setCategoryPath(category.getPath());
            long time = System.currentTimeMillis();
            oldModel.setUpdateTime(time);

            oldModel.setName(model.getName());
            oldModel.setCategoryId(category.getId());
            oldModel.setCategoryPath(category.getPath());
            oldModel.setManufacturerId(manufacturer.getId());
            oldModel.setEbayKeyword(model.getEbayKeyword());

            modelRepository.save(oldModel);

            oldModel.setCategoryName(category.getName());
            oldModel.setManufacturerName(manufacturer.getName());
            searchIndexService.processIndexModel(model);

            return new Response(true, "Sửa model thành công", model);
        } else {
            return new Response(false, "Thông tin không hợp lệ", error);
        }
    }

    /**
     * Xóa model, không xóa được khi dm có dm con hoặc model, item, khi xóa sẽ
     * xóa cả property
     *
     * @param id
     * @throws Exception
     */
    public void remove(String id) throws Exception {
        Model model = modelRepository.find(id);
        if (model == null) {
            throw new Exception("Model không tồn tại");
        }
        if (itemRepository.countByModel(id) > 0) {
            throw new Exception("Model còn chứa sản phẩm không được xóa");
        }

        if (model.getImages() != null && model.getImages().isEmpty()) {
            List<String> images = model.getImages();
            for (String img : images) {
                try {
                    imageService.deleteById(ImageType.MODEL, model.getId(), img);
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }

            }
        }
        modelPropertyRepository.deleteByModel(id);
        modelRepository.delete(id);
        modelSearchRepository.delete(id);
    }

    /**
     * Xoá ảnh cho model
     *
     * @param modelId
     * @param imageUri
     * @return Kết quả kèm object Model mới thêm hoặc báo lỗi
     * @throws Exception
     */
    public Response deleteImage(String modelId, String imageUri) throws Exception {
        if (imageUri == null || imageUri.isEmpty()) {
            throw new Exception("Ảnh model chưa được nhập");
        }
        imageService.deleteByUrl(ImageType.MODEL, modelId, imageUri);
        return new Response(true, "Đã xóa thành công", getModel(modelId));
    }

    /**
     * Lấy toàn bộ property của model
     *
     * @param modelId
     * @return
     */
    public List<ModelProperty> getProperties(String modelId) {
        return modelPropertyRepository.getByModel(modelId);
    }

    /**
     * Cập nhật thuộc tính model
     *
     * @param modelId
     * @param properties
     * @throws Exception
     */
    public void updateProperties(String modelId, List<ModelProperty> properties) throws Exception {
        Model model = modelRepository.find(modelId);
        if (model == null) {
            throw new Exception("Model không tồn tại");
        }
        modelPropertyRepository.deleteByModel(modelId);

        for (ModelProperty property : properties) {
            CategoryProperty catP = categoryPropertyRepository.find(property.getCategoryPropertyId());
            if (catP != null) {
                property.setCategoryPropertyId(catP.getId());
                List<String> ids = new ArrayList<>();
                for (String v : property.getCategoryPropertyValueIds()) {
                    CategoryPropertyValue catV = categoryPropertyValueRepository.find(v);
                    if (catV != null) {
                        ids.add(catV.getId());
                    }
                }
                property.setModelId(modelId);
                property.setCategoryPropertyValueIds(ids);
                modelPropertyRepository.save(property);
            }
        }
        searchIndexService.processIndexModel(model);
    }

    /**
     * Báo sửa xong để đợi duyệt
     *
     * @param modelId
     * @param administratorId
     * @param message
     * @throws java.lang.Exception
     */
    public void submitEdit(String modelId, String administratorId, String message) throws Exception {
        Model model = modelRepository.find(modelId);
        ModelLog log = modelLogRepository.getLastLog(modelId);

        if (model == null) {
            throw new Exception("Model không tồn tại");
        }
        if (log == null) {
            throw new Exception("Bạn không được phân công sửa model này");
        }
        if (!log.getNextUpdaterId().equals(administratorId)) {
            throw new Exception("Bạn không được phân công sửa model này");
        }

        ModelLog newLog = new ModelLog();
        newLog.setTime(System.currentTimeMillis());
        newLog.setUpdaterId(administratorId);
        newLog.setModelId(modelId);
        newLog.setLastUpdaterId(log.getUpdaterId());
        newLog.setStatus(ModelLogStatus.EDITED);
        newLog.setMessage(message);
        modelLogRepository.save(newLog);

        ModelApprove approve = modelApproveRepository.find(modelId);
        approve.setUpdateTime(System.currentTimeMillis());
        approve.setStatus(ModelApproveStatus.STATUS_EDITED);
        modelApproveRepository.save(approve);

    }

    /**
     * Báo tạo xong để đợi duyệt
     *
     * @param modelId
     * @param administratorId
     * @param message
     * @throws java.lang.Exception
     */
    public void submitAdd(String modelId, String administratorId, String message) throws Exception {
        Model model = modelRepository.find(modelId);
        ModelLog log = modelLogRepository.getLastLog(modelId);

        if (model == null) {
            throw new Exception("Model không tồn tại");
        }
        ModelLog newLog = new ModelLog();
        newLog.setTime(System.currentTimeMillis());
        newLog.setUpdaterId(administratorId);
        newLog.setModelId(modelId);
        if (log != null) {
            newLog.setLastUpdaterId(log.getUpdaterId());
        }
        newLog.setStatus(ModelLogStatus.CREATED);
        newLog.setMessage(message);
        modelLogRepository.save(newLog);
        ModelApprove approve = modelApproveRepository.find(modelId);
        approve.setUpdateTime(System.currentTimeMillis());
        approve.setStatus(ModelApproveStatus.STATUS_CREATED);
        modelApproveRepository.save(approve);
    }

    /**
     * Duyệt model
     *
     * @param modelId
     * @param administatorId id người duyệt
     * @param asignedId id người giao sửa tiếp nếu có
     * @param message
     * @param approved có duyệt hay không duyệt
     * @throws java.lang.Exception
     */
    public void approve(String modelId, String administatorId, String asignedId, String message, boolean approved) throws Exception {
        Model model = modelRepository.find(modelId);
        if (model == null) {
            throw new Exception("Model không tồn tại");
        }
        ModelLog oldLog = modelLogRepository.getLastLog(modelId);
        ModelLog log = new ModelLog();
        log.setModelId(modelId);
        log.setLastUpdaterId(oldLog == null ? null : oldLog.getUpdaterId());
        log.setMessage(message);
        log.setUpdaterId(administatorId);
        log.setNextUpdaterId(asignedId);
        log.setStatus(approved ? ModelLogStatus.APPROVED : ModelLogStatus.UNAPPROVED);
        log.setTime(System.currentTimeMillis());
        modelLogRepository.save(log);

        model.setApproved(approved);
        modelRepository.save(model);
        searchIndexService.processIndexModel(model);

        ModelApprove approve = modelApproveRepository.find(modelId);
        approve.setUpdateTime(System.currentTimeMillis());
        approve.setStatus(approved ? ModelApproveStatus.STATUS_ACTIVE : ModelApproveStatus.STATUS_EDITING);
        approve.setAdministratorId(asignedId);
        modelApproveRepository.save(approve);
    }

    /**
     * Yêu cầu sửa model đang hoạt động
     *
     * @param modelId
     * @param administatorId
     * @param asignedId
     * @param message
     * @throws Exception
     */
    public void disapprove(String modelId, String administatorId, String asignedId, String message) throws Exception {
        Model model = modelRepository.find(modelId);
        if (model == null) {
            throw new Exception("Model không tồn tại");
        }

        ModelLog oldLog = modelLogRepository.getLastLog(modelId);

        ModelLog log = new ModelLog();
        log.setModelId(modelId);
        log.setLastUpdaterId(oldLog == null ? null : oldLog.getUpdaterId());
        log.setMessage(message);
        log.setUpdaterId(administatorId);
        log.setNextUpdaterId(asignedId);
        log.setStatus(ModelLogStatus.DISAPPROVED);
        log.setTime(System.currentTimeMillis());
        modelLogRepository.save(log);

        model.setApproved(false);
        modelRepository.save(model);
        searchIndexService.processIndexModel(model);

        ModelApprove approve = modelApproveRepository.find(modelId);
        if (approve == null) {
            approve = new ModelApprove();
            approve.setModelId(modelId);
        }
        approve.setUpdateTime(System.currentTimeMillis());
        approve.setStatus(ModelApproveStatus.STATUS_EDITING);
        approve.setAdministratorId(asignedId);
        modelApproveRepository.save(approve);
    }

    /**
     * Tìm kiếm model trong trang tất cả model
     *
     * @param modelSearch
     * @return
     */
    @Cacheable(value = "buffercache", key = "'modelsearch-'.concat(#modelSearch.getKey())")
    public DataPage<Model> search(ModelSearch modelSearch) {
        NativeSearchQueryBuilder queryBuilder = buildSearchCondition(modelSearch, false);

        switch (modelSearch.getOrderBy()) {
            case 1:
                queryBuilder.withSort(new FieldSortBuilder("price").order(SortOrder.ASC));
                break;
            default:
                queryBuilder.withSort(new FieldSortBuilder("time").order(SortOrder.DESC));
                break;
        }

        queryBuilder.withFields("id");
        queryBuilder.withPageable(new PageRequest(modelSearch.getPageIndex(), modelSearch.getPageSize()));

        DataPage<Model> dataPage = new DataPage<>();
        try {
            FacetedPage<vn.chodientu.entity.search.ModelSearch> page = modelSearchRepository.search(queryBuilder.build());

            dataPage.setDataCount(page.getTotalElements());
            dataPage.setPageCount(page.getTotalPages());
            dataPage.setPageSize(page.getSize());
            dataPage.setPageIndex(page.getNumber());

            List<String> ids = new ArrayList<>();

            for (vn.chodientu.entity.search.ModelSearch ms : page.getContent()) {
                ids.add(ms.getId());
            }

            dataPage.setData(modelRepository.get(ids));

            Map<String, List<String>> images = imageService.get(ImageType.MODEL, ids);
            for (Map.Entry<String, List<String>> entry : images.entrySet()) {
                String modelId = entry.getKey();
                List<String> imgs = entry.getValue();
                for (Model model : dataPage.getData()) {
                    if (model.getId().equals(modelId)) {
                        model.setImages(imgs);
                        break;
                    }
                }
            }

        } catch (Exception ex) {
        }
        return dataPage;
    }

    /**
     * Lấy danh sách model đang tạo của admin, chắc ít nên khỏi phân trang
     *
     * @param administratorId
     * @return
     */
    public List<Model> getCreatingModel(String administratorId) {
        List<String> ids = new ArrayList<>();
        List<ModelApprove> approves = modelApproveRepository.find(administratorId, ModelApproveStatus.STATUS_CREATING);

        for (ModelApprove ma : approves) {
            ids.add(ma.getModelId());
        }

        return modelRepository.get(ids);
    }

    /**
     * Thay đổi trạng thái của model
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Response changeStatus(String id) throws Exception {
        Model model = modelRepository.find(id);
        if (model == null) {
            throw new Exception("Model không tồn tại");
        }
        model.setActive(!model.isActive());
        modelRepository.save(model);
        searchIndexService.processIndexModel(model);
        return new Response(true, "Thông tin model", model);
    }

    /**
     * Lấy danh sách model đang sửa của admin, chắc ít nên khỏi phân trang
     *
     * @param administratorId
     * @return
     */
    public List<Model> getEditingModel(String administratorId) {
        List<String> ids = new ArrayList<>();
        List<ModelApprove> approves = modelApproveRepository.find(administratorId, ModelApproveStatus.STATUS_EDITING);

        for (ModelApprove ma : approves) {
            ids.add(ma.getModelId());
        }

        return modelRepository.get(ids);
    }

    /**
     * Lấy danh sách model đã sửa xong để duyệt, nếu truyền admin id != null tức
     * là search model của thằng ấy sửa, chắc ít nên khỏi phân trang
     *
     * @param administratorId
     * @return
     */
    public List<Model> getEditedModel(String administratorId) {
        List<String> ids = new ArrayList<>();
        List<ModelApprove> approves = modelApproveRepository.find(administratorId, ModelApproveStatus.STATUS_EDITED);
        for (ModelApprove ma : approves) {
            ids.add(ma.getModelId());
        }
        return modelRepository.get(ids);
    }

    /**
     * Lấy danh sách model mới tạo để duyệt, nếu truyền admin id != null tức là
     * search model của thằng ấy tạo, chắc ít nên khỏi phân trang
     *
     * @param administratorId
     * @return
     */
    public List<Model> getCreatedModel(String administratorId) {
        List<String> ids = new ArrayList<>();
        List<ModelApprove> approves = modelApproveRepository.find(administratorId, ModelApproveStatus.STATUS_CREATED);

        for (ModelApprove ma : approves) {
            ids.add(ma.getModelId());
        }

        return modelRepository.get(ids);
    }

    /**
     * Lấy chi tiết model
     *
     * @param modelId
     * @return
     * @throws Exception
     */
    public Model getModel(String modelId) throws Exception {
        Model model = modelRepository.find(modelId);
        if (model == null) {
            throw new Exception("Model không tồn tại");
        }
        model.setImages(imageService.get(ImageType.MODEL, model.getId()));
        return model;
    }

    /**
     * Lấy danh sách model theo list ids
     *
     * @param ids
     * @return
     */
    public List<Model> getModels(List<String> ids) {
        return modelRepository.get(ids);
    }

    /**
     * index tất cả model trong database
     *
     * @return
     */
    @Async
    public Response index() {
        long total = modelRepository.count();
        int pageSize = 1000;
        int totalPage = (int) total / pageSize;
        if (total % pageSize != 0) {
            totalPage++;
        }
        for (int i = 0; i < totalPage; i++) {
            searchIndexService.processIndexPageModel(modelRepository.list(new PageRequest(i, pageSize)));
        }

        return new Response(true);
    }

    /**
     * Xóa toàn bộ index
     *
     */
    public void preIndex() {
        modelSearchRepository.preIndex();
    }

    /**
     * Đếm số lượng model đã được index
     *
     * @return
     */
    public long countElastic() {
        return modelSearchRepository.count();

    }

    /**
     * đếm số lượng model hiện có
     *
     * @return
     */
    public long count() {
        return modelRepository.count();
    }

    private NativeSearchQueryBuilder buildSearchCondition(ModelSearch modelSearch, boolean ignoreSearch) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        List<FilterBuilder> filters = new ArrayList<>();

        if (modelSearch.getCategoryId() != null && !modelSearch.getCategoryId().trim().equals("")) {
            filters.add(new TermFilterBuilder("categoryPath", modelSearch.getCategoryId()));
        }

        switch (modelSearch.getStatus()) {
            case 1: {
                filters.add(new TermFilterBuilder("active", true));
                filters.add(new TermFilterBuilder("approved", true));
                break;
            }
            case 2: {
                filters.add(new TermFilterBuilder("active", false));
                break;
            }
            case 3: {
                filters.add(new TermFilterBuilder("approved", false));
                break;
            }
        }

        if (!ignoreSearch) {
            if (modelSearch.getManufacturerIds() != null && !modelSearch.getManufacturerIds().isEmpty()) {
                filters.add(new TermsFilterBuilder("manufacturerId", modelSearch.getManufacturerIds()));
            }
            if (modelSearch.getManufacturerId() != null && !modelSearch.getManufacturerId().equals("")) {
                filters.add(new TermsFilterBuilder("manufacturerId", modelSearch.getManufacturerId()));
            }
            if (modelSearch.getModelId() != null && !modelSearch.getModelId().trim().equals("")) {
                filters.add(new TermFilterBuilder("id", modelSearch.getModelId()));
            }
            List<FilterBuilder> proFilters = new ArrayList<>();
            if (modelSearch.getProperties() != null) {
                for (PropertySearch pro : modelSearch.getProperties()) {
                    FilterBuilder[] prosFilter = new FilterBuilder[2];
                    prosFilter[0] = new TermFilterBuilder("properties.name", pro.getName());
                    if (pro.getOperator() == PropertyOperator.GTE) {
                        prosFilter[1] = new RangeFilterBuilder("value").gte(pro.getValue());
                    } else if (pro.getOperator() == PropertyOperator.LTE) {
                        prosFilter[1] = new RangeFilterBuilder("value").lte(pro.getValue());
                    } else {
                        prosFilter[1] = new TermFilterBuilder("value", pro.getValue());
                    }
                    proFilters.add(new NestedFilterBuilder("properties", new AndFilterBuilder(prosFilter)));
                }
                filters.add(new AndFilterBuilder(proFilters.toArray(new FilterBuilder[0])));
            }
        }
        if (modelSearch.getKeyword() != null && modelSearch.getKeyword().trim().length() > 1) {
            queryBuilder.withQuery(new QueryStringQueryBuilder(TextUtils.removeDiacritical(modelSearch.getKeyword())).defaultOperator(QueryStringQueryBuilder.Operator.AND));
        } else {
            queryBuilder.withQuery(new MatchAllQueryBuilder());
        }
        if (!filters.isEmpty()) {
            queryBuilder.withFilter(new AndFilterBuilder(filters.toArray(new FilterBuilder[0])));
        }

        return queryBuilder;
    }

    @Cacheable(value = "datacache", key = "'categoryh-'.concat(#modelSearch.getKey()).concat(#ignoreSearch)")
    public List<CategoryHistogram> getCategoryHistogram(ModelSearch modelSearch, boolean ignoreSearch) {
        NativeSearchQueryBuilder queryBuilder = buildSearchCondition(modelSearch, ignoreSearch);

        List<Category> childCats = categoryRepository.getChilds(modelSearch.getCategoryId());

        for (Category cat : childCats) {
            queryBuilder.withFacet(new NativeFacetRequest(new FilterFacetBuilder("category-" + cat.getId()).filter(new TermFilterBuilder("categoryPath", cat.getId())), true));
        }

        FacetResult result = modelSearchRepository.getFacets(queryBuilder.build());

        List<CategoryHistogram> categoryHistogram = new ArrayList<>();
        for (Category cat : childCats) {
            FilterFacet facet = result.getFacet(FilterFacet.class, "category-" + cat.getId());
            if (facet != null) {
                CategoryHistogram ch = new CategoryHistogram();
                ch.setId(cat.getId());
                ch.setName(cat.getName());
                ch.setCount((int) facet.getCount());
                categoryHistogram.add(ch);
            }
        }

        return categoryHistogram;
    }

    @Cacheable(value = "datacache", key = "'manufacturerh-'.concat(#modelSearch.getKey())")
    public List<ManufacturerHistogram> getManufacturerHistogram(ModelSearch modelSearch) {
        modelSearch.setManufacturerIds(null);
        NativeSearchQueryBuilder queryBuilder = buildSearchCondition(modelSearch, false);

        List<ManufacturerHistogram> manufacturerHistogram = new ArrayList<>();

        if (modelSearch.getCategoryId() == null) {
            return manufacturerHistogram;
        }

        Criteria cri = new Criteria();

        List<CategoryManufacturer> catManus = categoryManufacturerRepository.search(new Criteria("categoryId").is(modelSearch.getCategoryId()).and("filter").is(true), 0, 300);
        for (CategoryManufacturer cm : catManus) {
            queryBuilder.withFacet(new NativeFacetRequest(new FilterFacetBuilder("manufacturer-" + cm.getManufacturerId()).filter(new TermFilterBuilder("manufacturerId", cm.getManufacturerId())), true));
        }

        FacetResult result = modelSearchRepository.getFacets(queryBuilder.build());

        List<String> manuIds = new ArrayList<>();
        for (CategoryManufacturer cm : catManus) {
            manuIds.add(cm.getManufacturerId());
        }
        List<Manufacturer> manus = manufacturerRepository.get(manuIds);
        for (Manufacturer man : manus) {
            FilterFacet facet = result.getFacet(FilterFacet.class, "manufacturer-" + man.getId());
            if (facet != null) {
                ManufacturerHistogram mh = new ManufacturerHistogram();
                mh.setId(man.getId());
                mh.setName(man.getName());
                mh.setCount((int) facet.getCount());
                manufacturerHistogram.add(mh);
            }
        }

        return manufacturerHistogram;

    }

    @Cacheable(value = "datacache", key = "'propertyh-'.concat(#modelSearch.getKey())")
    public List<PropertyHistogram> getPropertyHistogram(ModelSearch modelSearch) {
        NativeSearchQueryBuilder queryBuilder = buildSearchCondition(modelSearch, false);

        List<CategoryProperty> properties = categoryPropertyRepository.getFilter(modelSearch.getCategoryId());
        List<CategoryPropertyValue> propertyValues = categoryPropertyValueRepository.getByCategory(modelSearch.getCategoryId());
        if (properties != null) {
            for (CategoryProperty p : properties) {
                for (CategoryPropertyValue v : propertyValues) {
                    if (p.getId().equals(v.getCategoryPropertyId())) {
                        FilterBuilder[] prosFilter = new FilterBuilder[2];
                        prosFilter[0] = new TermFilterBuilder("properties.name", p.getName());

                        if (p.getOperator() == PropertyOperator.GTE) {
                            prosFilter[1] = new RangeFilterBuilder("value").gte(v.getValue());
                        } else if (p.getOperator() == PropertyOperator.LTE) {
                            prosFilter[1] = new RangeFilterBuilder("value").lte(v.getValue());
                        } else {
                            prosFilter[1] = new TermFilterBuilder("value", v.getValue());
                        }
                        queryBuilder.withFacet(new NativeFacetRequest(new FilterFacetBuilder("properties-" + p.getId() + "-" + v.getId()).filter(new NestedFilterBuilder("properties", new AndFilterBuilder(prosFilter))), true));
                    }
                }
            }
        }

        FacetResult result = modelSearchRepository.getFacets(queryBuilder.build());

        List<PropertyHistogram> propertyHistogram = new ArrayList<>();
        if (properties != null) {
            for (CategoryProperty p : properties) {
                PropertyHistogram ph = new PropertyHistogram();
                ph.setName(p.getName());
                ph.setType(p.getType());
                ph.setCount(0);
                ph.setOperator(p.getOperator());
                ph.setValues(new ArrayList<PropertyValueHistogram>());
                for (CategoryPropertyValue v : propertyValues) {
                    if (p.getId().equals(v.getCategoryPropertyId())) {
                        FilterFacet facet = result.getFacet(FilterFacet.class, "properties-" + p.getId() + "-" + v.getId());
                        if (facet != null) {
                            PropertyValueHistogram pvh = new PropertyValueHistogram();
                            pvh.setName(v.getName());
                            pvh.setValue(v.getValue());
                            pvh.setCount((int) facet.getCount());
                            ph.setCount(ph.getCount() + pvh.getCount());
                            ph.getValues().add(pvh);
                        }
                    }
                }
                propertyHistogram.add(ph);
            }
        }

        return propertyHistogram;
    }

    /**
     * Đếm sản phẩm trong trang model browse
     *
     * @param itemSearch
     * @return
     */
    @Cacheable(value = "datacache", key = "'modelcount-'.concat(#itemSearch.getKey()).concat(#ignoreSearch)")
    public long getModelCountByItem(ItemSearch itemSearch, boolean ignoreSearch) {

        ModelSearch modelSearch = new ModelSearch();
        modelSearch.setStatus(itemSearch.getStatus());
        modelSearch.setKeyword(itemSearch.getKeyword());
        modelSearch.setManufacturerIds(itemSearch.getManufacturerIds());
        if (itemSearch.getCategoryIds() != null && !itemSearch.getCategoryIds().isEmpty()) {
            modelSearch.setCategoryId(itemSearch.getCategoryIds().get(0));
        }

        NativeSearchQueryBuilder queryBuilder = buildSearchCondition(modelSearch, ignoreSearch);
        queryBuilder.withFacet(new NativeFacetRequest(FacetBuilders.filterFacet("modelCount", new QueryFilterBuilder(new MatchAllQueryBuilder())), true));

        FacetResult result = modelSearchRepository.getFacets(queryBuilder.build());

        FilterFacet facet;

        facet = result.getFacet(FilterFacet.class, "modelCount");
        if (facet != null) {
            return facet.getCount();
        }
        return 0;
    }

    /**
     * Lấy danh sách model theo Id thương hiệu
     *
     * @param manufacturerId
     * @return
     */
    public List<Model> getModelByManufactureId(String manufacturerId) {
        return modelRepository.getModelByManufactureId(manufacturerId);

    }

    /**
     * update lại toàn bộ giá từ sản phẩm sang model tương ứng
     *
     * @return
     */
    @Async
    public Response updatePrice() {
        long total = modelRepository.count();
        int pageSize = 1000;
        int totalPage = (int) total / pageSize;
        if (total % pageSize != 0) {
            totalPage++;
        }
        for (int i = 0; i < totalPage; i++) {
            this.updateModels(modelRepository.list(new PageRequest(i, pageSize)));
        }

        return new Response(true);
    }

    @Async
    private void updateModels(List<Model> models) {
        for (Model model : models) {
            this.updateModel(model);
        }
    }

    @Async
    public void updateModel(Model model) {
        model.setNewMaxPrice(itemRepository.getNewMaxPrice(model.getId()));
        model.setNewMinPrice(itemRepository.getNewMinPrice(model.getId()));
        model.setOldMaxPrice(itemRepository.getOldMaxPrice(model.getId()));
        model.setOldMinPrice(itemRepository.getOldMinPrice(model.getId()));
        model.setItemCount(itemRepository.countItemByModel(model.getId()));

        modelRepository.save(model);
        searchIndexService.processIndexModel(model);
    }

}
