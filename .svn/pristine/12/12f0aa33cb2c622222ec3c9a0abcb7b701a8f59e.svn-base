/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.elasticsearch.index.query.AndFilterBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.TermFilterBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.CategoryManufacturer;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.input.ManufacturerSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.CategoryManufacturerRepository;
import vn.chodientu.repository.CategoryRepository;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.ManufacturerRepository;
import vn.chodientu.component.Validator;
import vn.chodientu.repository.ManufacturerSearchRepository;
import vn.chodientu.util.TextUtils;
import vn.chodientu.entity.enu.ImageType;

/**
 *
 * @author Admin
 */
@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryManufacturerRepository categoryManufacturerRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private ImageService imageService;

    @Autowired
    private ManufacturerSearchRepository manufacturerSearchRepository;

    public Response migrate(Manufacturer manufacturer) {
        Map<String, String> errors = validator.validate(manufacturer);

        if (!errors.isEmpty()) {
            return new Response(false, "Thêm thương hiệu thất bại", errors);
        } else {
            manufacturerRepository.save(manufacturer);
            if (manufacturer.getImageUrl() != null) {
                Response resp = imageService.download(manufacturer.getImageUrl(), ImageType.MANUFACTURER, manufacturer.getId());
            }
            return new Response(true, "Thêm thương hiệu thành công", manufacturer);
        }
    }

    /**
     * Thêm mới thương hiệu
     *
     * @param manufacturer
     * @return
     */
    public Response addManufacturer(Manufacturer manufacturer) {
        Map<String, String> errors = validator.validate(manufacturer);

        if (!errors.isEmpty()) {
            return new Response(false, "Thêm thương hiệu thất bại", errors);
        } else {
            if (manufacturerRepository.countByName(null, manufacturer.getName()) > 0) {
                return new Response(false, "Tên thương hiệu đã tồn tại");
            }
            manufacturer.setId(manufacturerRepository.genId());
            manufacturerRepository.save(manufacturer);
            manufacturerSearchRepository.index(manufacturer);
            return new Response(true, "Thêm thương hiệu thành công", manufacturer);
        }
    }

    /**
     * Sửa thương hiệu
     *
     * @param manufacturer
     * @return
     * @throws Exception
     */
    public Response editManufacturer(Manufacturer manufacturer) throws Exception {
        Map<String, String> errors = validator.validate(manufacturer);
        Manufacturer oldManufacturer = manufacturerRepository.find(manufacturer.getId());
        if (oldManufacturer == null) {
            imageService.delete(ImageType.MANUFACTURER, manufacturer.getId());
            throw new Exception("Thương hiệu chưa tồn tại");
        }
        if (!errors.isEmpty()) {
            imageService.delete(ImageType.MANUFACTURER, manufacturer.getId());
            return new Response(false, "Sửa thương hiệu thất bại", errors);
        } else {
            if (manufacturerRepository.countByName(manufacturer.getId(), manufacturer.getName()) > 0) {
                throw new Exception("Tên thương hiệu đã tồn tại");
            }

            if (!manufacturer.getName().equals(oldManufacturer.getName())) {
                categoryManufacturerRepository.updateManufacturerName(manufacturer.getId(), manufacturer.getName());
            }
            manufacturerRepository.save(manufacturer);

            List<String> cateIds = new ArrayList<>();
            List<CategoryManufacturer> listMap = categoryManufacturerRepository.searchByManufacturerId(manufacturer.getId());
            for (CategoryManufacturer categoryManufacturer : listMap) {
                cateIds.add(categoryManufacturer.getCategoryId());
            }
            manufacturer.setMappedCategoryIds(cateIds);
            manufacturerSearchRepository.index(manufacturer);
            return new Response(true, "ok", manufacturer);
        }
    }

    /**
     * Xóa thương hiệu
     *
     * @param id
     * @return
     * @throws Exception
     */
    public void removeManufacturer(String id) throws Exception {
        Manufacturer oldManufacturer = manufacturerRepository.find(id);
        if (oldManufacturer == null) {
            throw new Exception("Thương hiệu chưa tồn tại");
        }
        if (itemRepository.countByManufacturer(id) > 0) {
            throw new Exception("Thương hiệu đã có sản phẩm, không được xóa");
        }

        manufacturerRepository.delete(oldManufacturer);
        imageService.delete(ImageType.MANUFACTURER, id);
        categoryManufacturerRepository.deleteByCategoryIdAndManufacturerId(null, id);
        manufacturerSearchRepository.delete(id);
    }

    /**
     * map thương hiệu với danh mục
     *
     * @param map
     * @return
     * @throws Exception
     */
    public Response addMap(CategoryManufacturer map) throws Exception {
        Manufacturer manufacturer = manufacturerRepository.find(map.getManufacturerId());

        if (map.getCategoryId() == null || !categoryRepository.exists(map.getCategoryId())) {
            throw new Exception("Danh mục không tồn tại");
        }
        if (map.getManufacturerId() == null || manufacturer == null) {
            throw new Exception("Thương hiệu không tồn tại");
        }
        if (categoryManufacturerRepository.countByCategoryIdAndManufacturerId(map.getCategoryId(), map.getManufacturerId()) > 0) {
            throw new Exception("Danh mục và thương hiệu này đã map rồi");
        }
        map.setId(categoryManufacturerRepository.genId());
        map.setManufacturerName(manufacturer.getName());
        categoryManufacturerRepository.save(map);

        List<String> cateIds = new ArrayList<>();
        List<CategoryManufacturer> listMap = categoryManufacturerRepository.searchByManufacturerId(map.getManufacturerId());
        for (CategoryManufacturer categoryManufacturer : listMap) {
            cateIds.add(categoryManufacturer.getCategoryId());
        }
        manufacturer.setMappedCategoryIds(cateIds);
        manufacturerSearchRepository.index(manufacturer);
        return new Response(true, "Map thương hiệu thành công", map);
    }

    /**
     * Xóa map thương hiệu và danh mục
     *
     * @param map
     * @return
     * @throws Exception
     */
    public Response removeMap(CategoryManufacturer map) throws Exception {
        Manufacturer manufacturer = manufacturerRepository.find(map.getManufacturerId());
        if (map.getId() != null) {
            CategoryManufacturer findMap = categoryManufacturerRepository.find(map.getId());
            if (findMap == null) {
                throw new Exception("Thương hiệu không tồn tại");
            }
            manufacturer.setId(findMap.getId());
            categoryManufacturerRepository.delete(map.getId());
        } else {
            if (categoryManufacturerRepository.countByCategoryIdAndManufacturerId(map.getCategoryId(), map.getManufacturerId()) <= 0) {
                throw new Exception("Map thương hiệu không tồn tại");
            }
            manufacturer.setId(map.getManufacturerId());
            categoryManufacturerRepository.deleteByCategoryIdAndManufacturerId(map.getCategoryId(), map.getManufacturerId());
        }

        List<String> cateIds = new ArrayList<>();
        List<CategoryManufacturer> listMap = categoryManufacturerRepository.searchByManufacturerId(map.getManufacturerId());
        for (CategoryManufacturer cm : listMap) {
            cateIds.add(cm.getCategoryId());
        }
        manufacturer.setMappedCategoryIds(cateIds);
        manufacturerSearchRepository.index(manufacturer);
        return new Response(true);
    }

    /**
     * Tìm kiếm phân trang
     *
     * @param manufacturerSearch
     * @return
     */
    public DataPage<Manufacturer> search(ManufacturerSearch manufacturerSearch) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        if (manufacturerSearch.getName() != null && manufacturerSearch.getName().trim().equals("")) {
            manufacturerSearch.setName(null);
        }

        List<FilterBuilder> filters = new ArrayList<>();
        if (manufacturerSearch.getCategoryId() != null && !manufacturerSearch.getCategoryId().trim().equals("")) {
            filters.add(new TermFilterBuilder("categoryIds", manufacturerSearch.getCategoryId()));
        }
        if (manufacturerSearch.getManufacturerId() != null && !manufacturerSearch.getManufacturerId().trim().equals("")) {
            filters.add(new TermFilterBuilder("id", manufacturerSearch.getManufacturerId()));
        }
        filters.add(new TermFilterBuilder("active", true));

        if (manufacturerSearch.getName() != null && manufacturerSearch.getName().trim().length() > 1) {
            queryBuilder.withQuery(new QueryStringQueryBuilder(TextUtils.removeDiacritical(manufacturerSearch.getName())));
        } else {
            queryBuilder.withQuery(new MatchAllQueryBuilder());
        }
        if (!filters.isEmpty()) {
            queryBuilder.withFilter(new AndFilterBuilder(filters.toArray(new FilterBuilder[0])));
        }

        queryBuilder.withFields("id");
        queryBuilder.withSort(new FieldSortBuilder("name"));
        queryBuilder.withPageable(new PageRequest(manufacturerSearch.getPageIndex(), manufacturerSearch.getPageSize()));

        DataPage<Manufacturer> dataPage = new DataPage<>();
        try {
            FacetedPage<vn.chodientu.entity.search.ManufacturerSearch> page = manufacturerSearchRepository.search(queryBuilder.build());

            dataPage.setDataCount(page.getTotalElements());
            dataPage.setPageCount(page.getTotalPages());
            dataPage.setPageSize(page.getSize());
            dataPage.setPageIndex(page.getNumber());

            List<String> ids = new ArrayList<>();

            for (vn.chodientu.entity.search.ManufacturerSearch ms : page.getContent()) {
                ids.add(ms.getId());
            }
            dataPage.setData(manufacturerRepository.get(ids));
        } catch (Exception ex) {
        }
        return dataPage;
    }

    /**
     * Lấy danh sách danh mục theo thương hiệu
     *
     * @param manufacturerId
     * @return
     * @throws Exception
     */
    public List<Category> listCategoryByManufacturer(String manufacturerId) throws Exception {
        List<String> cateIds = new ArrayList<>();
        List<CategoryManufacturer> listMap = categoryManufacturerRepository.searchByManufacturerId(manufacturerId);
        if (listMap != null && !listMap.isEmpty()) {
            for (CategoryManufacturer categoryManufacturer : listMap) {
                cateIds.add(categoryManufacturer.getCategoryId());
            }
            if (!cateIds.isEmpty()) {
                return categoryRepository.get(cateIds);
            }
        }
        return null;
    }

    /**
     * Lấy chi tiết thương hiệu
     *
     * @param manufacturerId
     * @return
     * @throws Exception
     */
    public Manufacturer getManufacturer(String manufacturerId) throws Exception {
        Manufacturer manufacturer = manufacturerRepository.find(manufacturerId);
        if (manufacturer == null) {
            throw new Exception("Danh mục không tồn tại");
        }
        return manufacturer;
    }

    /**
     * Lấy danh sách thương hiệu theo list ids
     *
     * @param ids
     * @return
     */
    public List<Manufacturer> getManufacturers(List<String> ids) {
        return manufacturerRepository.get(ids);
    }

    /**
     * đếm số lượng thương hiệu đã được index
     *
     * @return
     */
    public long countElastic() {
        return manufacturerSearchRepository.count();
    }

    /**
     * đếm số lượng thương hiệu hiện có
     *
     * @return
     */
    public long count() {
        return manufacturerRepository.count();
    }

    /**
     * Index toàn bộ thương hiệu vào elasticsearch
     *
     */
    @Async
    public void index() {
        long total = manufacturerRepository.count();
        int totalPage = (int) total / 1000;
        if (total % 1000 != 0) {
            totalPage++;
        }
        for (int i = 0; i < totalPage; i++) {
            processIndex(i);
        }
    }

    @Async
    private void processIndex(int i) {
        List<Manufacturer> manufacturers = manufacturerRepository.list(new PageRequest(i, 1000));
        for (Manufacturer manuf : manufacturers) {
            List<CategoryManufacturer> maps = categoryManufacturerRepository.searchByManufacturerId(manuf.getId());
            List<String> cateIds = new ArrayList<>();
            if (maps != null && !maps.isEmpty()) {
                for (CategoryManufacturer map : maps) {
                    cateIds.add(map.getCategoryId());
                }
            }
            manuf.setMappedCategoryIds(cateIds);
        }
        manufacturerSearchRepository.index(manufacturers);

    }

    /**
     * Xóa toàn bộ index
     *
     */
    public void unindex() {
        manufacturerSearchRepository.preIndex();
    }


    /**
     *Lấy danh sách thương hiệu theo ids
     * @param ids
     * @return
     */
    public List<Manufacturer> get(List<String> ids) {
       return manufacturerRepository.get(ids);
    }

}
