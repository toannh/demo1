package vn.chodientu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.CategoryProperty;
import vn.chodientu.entity.db.CategoryPropertyValue;
import vn.chodientu.entity.db.Image;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemProperty;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.db.ModelProperty;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.repository.CategoryPropertyRepository;
import vn.chodientu.repository.CategoryPropertyValueRepository;
import vn.chodientu.repository.CategoryRepository;
import vn.chodientu.repository.ItemPropertyRepository;
import vn.chodientu.repository.ItemSearchRepository;
import vn.chodientu.repository.ManufacturerRepository;
import vn.chodientu.repository.ModelPropertyRepository;
import vn.chodientu.repository.ModelRepository;
import vn.chodientu.repository.ModelSearchRepository;

/**
 * @since Jun 23, 2014
 * @author Phu
 */
@Service
public class SearchIndexService {

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
    private ModelSearchRepository modelSearchRepository;
    @Autowired
    private ItemPropertyRepository itemPropertyRepository;
    @Autowired
    private ItemSearchRepository itemSearchRepository;
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ImageService imageService;

    @Async
    public void processIndexPageModel(List<Model> models) {
        List<String> manufacturerIds = new ArrayList<>();
        List<String> categoryIds = new ArrayList<>();
        List<String> modelIds = new ArrayList<>();

        for (Model m : models) {
            modelIds.add(m.getId());
            if (m.getCategoryId() != null) {
                categoryIds.add(m.getCategoryId());
            }
            if (m.getManufacturerId() != null) {
                manufacturerIds.add(m.getManufacturerId());
            }
        }

        List<Manufacturer> manufacturers = manufacturerRepository.get(manufacturerIds);
        List<Category> categories = categoryRepository.get(categoryIds);
        List<ModelProperty> modelProperties = modelPropertyRepository.getByModels(modelIds);
        List<CategoryProperty> categoryProperties = categoryPropertyRepository.getByCategories(categoryIds);
        List<CategoryPropertyValue> categoryPropertyValues = categoryPropertyValueRepository.getByCategories(categoryIds);

        for (Model model : models) {
            model.setProperties(new ArrayList<ModelProperty>());
            if (modelProperties != null) {
                for (ModelProperty mp : modelProperties) {
                    if (model.getId().equals(mp.getModelId())) {
                        ModelProperty modelPro = new ModelProperty();
                        if (categoryProperties != null) {
                            for (CategoryProperty cp : categoryProperties) {
                                if (mp.getCategoryPropertyId().equals(cp.getId())) {
                                    modelPro.setCategoryPropertyId(cp.getName());
                                    modelPro.setCategoryPropertyValueIds(new ArrayList<String>());
                                    model.getProperties().add(modelPro);
                                    break;
                                }
                            }
                        }
                        for (String v : mp.getCategoryPropertyValueIds()) {
                            if (categoryPropertyValues != null) {
                                for (CategoryPropertyValue cpv : categoryPropertyValues) {
                                    if (v.equals(cpv.getId())) {
                                        modelPro.getCategoryPropertyValueIds().add(cpv.getValue() + "");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (manufacturers != null) {
                for (Manufacturer man : manufacturers) {
                    if (model.getManufacturerId() != null && model.getManufacturerId().equals(man.getId())) {
                        model.setManufacturerName(man.getName());
                        break;
                    }
                }
            }
            if (categories != null) {
                for (Category cat : categories) {
                    if (model.getCategoryId() != null && model.getCategoryId().equals(cat.getId())) {
                        model.setCategoryName(cat.getName());
                        break;
                    }
                }
            }
        }
        modelSearchRepository.index(models);
    }

    @Async
    public void processIndexModel(Model model) {
        Manufacturer manu = manufacturerRepository.find(model.getManufacturerName());
        if (manu != null) {
            model.setManufacturerName(manu.getName());
        }
        Category cat = categoryRepository.find(model.getCategoryId());
        if (cat != null) {
            model.setCategoryName(cat.getName());
        }
        List<ModelProperty> modelProperties = modelPropertyRepository.getByModel(model.getId());
        List<CategoryProperty> categoryProperties = categoryPropertyRepository.getByCategory(cat.getId());
        List<CategoryPropertyValue> categoryPropertyValues = categoryPropertyValueRepository.getByCategory(cat.getId());
        model.setProperties(new ArrayList<ModelProperty>());
        if (modelProperties != null) {
            for (ModelProperty mp : modelProperties) {
                ModelProperty modelPro = new ModelProperty();
                if (categoryProperties != null) {
                    for (CategoryProperty cp : categoryProperties) {
                        if (mp.getCategoryPropertyId().equals(cp.getId())) {
                            modelPro.setCategoryPropertyId(cp.getName());
                            modelPro.setCategoryPropertyValueIds(new ArrayList<String>());
                            model.getProperties().add(modelPro);
                            break;
                        }
                    }
                }
                for (String v : mp.getCategoryPropertyValueIds()) {
                    if (categoryPropertyValues != null) {
                        for (CategoryPropertyValue cpv : categoryPropertyValues) {
                            if (v.equals(cpv.getId())) {
                                modelPro.getCategoryPropertyValueIds().add(cpv.getValue() + "");
                                break;
                            }
                        }
                    }
                }
            }
        }
        modelSearchRepository.index(model);
    }

    @Async
    public void processIndexPageItem(List<Item> items) {
        try {
            List<String> manufacturerIds = new ArrayList<>();
            List<String> categoryIds = new ArrayList<>();
            List<String> modelIds = new ArrayList<>();
            List<String> itemIds = new ArrayList<>();

            for (Item i : items) {
                itemIds.add(i.getId());
                if (i.getCategoryId() != null) {
                    categoryIds.add(i.getCategoryId());
                }
                if (i.getManufacturerId() != null) {
                    manufacturerIds.add(i.getManufacturerId());
                }
                if (i.getModelId() != null) {
                    modelIds.add(i.getModelId());
                }
            }

            List<Manufacturer> manufacturers = manufacturerRepository.get(manufacturerIds);
            List<Category> categories = categoryRepository.get(categoryIds);
            List<Model> models = modelRepository.get(modelIds);

            List<ItemProperty> itemPropreties = itemPropertyRepository.getByItems(itemIds);
            List<CategoryProperty> categoryProperties = categoryPropertyRepository.getByCategories(categoryIds);
            List<CategoryPropertyValue> categoryPropertyValues = categoryPropertyValueRepository.getByCategories(categoryIds);

            for (Item item : items) {
                item.setProperties(new ArrayList<ItemProperty>());
                if (itemPropreties != null) {
                    for (ItemProperty ip : itemPropreties) {
                        if (item.getId().equals(ip.getItemId())) {
                            ItemProperty itemPro = new ItemProperty();
                            if (categoryProperties != null) {
                                for (CategoryProperty cp : categoryProperties) {
                                    if (ip.getCategoryPropertyId().equals(cp.getId())) {
                                        itemPro.setCategoryPropertyId(cp.getName());
                                        itemPro.setCategoryPropertyValueIds(new ArrayList<String>());
                                        item.getProperties().add(itemPro);
                                        break;
                                    }
                                }
                            }
                            for (String v : ip.getCategoryPropertyValueIds()) {
                                if (categoryPropertyValues != null) {
                                    for (CategoryPropertyValue cpv : categoryPropertyValues) {
                                        if (v.equals(cpv.getId())) {
                                            itemPro.getCategoryPropertyValueIds().add(cpv.getValue() + "");
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (manufacturers != null) {
                    for (Manufacturer man : manufacturers) {
                        if (item.getManufacturerId() != null && item.getManufacturerId().equals(man.getId())) {
                            item.setManufacturerName(man.getName());
                            break;
                        }
                    }
                }
                if (categories != null) {
                    for (Category cat : categories) {
                        if (item.getCategoryId() != null && item.getCategoryId().equals(cat.getId())) {
                            item.setCategoryName(cat.getName());
                            break;
                        }
                    }
                }
                if (models != null) {
                    for (Model mo : models) {
                        if (item.getModelId() != null && item.getModelId().equals(mo.getId())) {
                            item.setModelName(mo.getName());
                            break;
                        }
                    }
                }
            }
            itemSearchRepository.index(items);
        } catch (Exception ex) {
            Logger.getLogger("item_index").log(Level.ERROR, ex.getMessage(), ex);
        }
    }

    @Async
    public void processIndexItem(Item item) {
        try {
            Manufacturer manu = manufacturerRepository.find(item.getManufacturerId());
            if (manu != null) {
                item.setManufacturerName(manu.getName());
            }
            Category cat = categoryRepository.find(item.getCategoryId());
            if (cat != null) {
                item.setCategoryName(cat.getName());
            }
            Model mod = modelRepository.find(item.getModelId());
            if (mod != null) {
                item.setModelName(mod.getName());
            }
            List<ItemProperty> itemProperties = itemPropertyRepository.getByItem(item.getId());
            if (item.getCategoryId() != null) {
                List<CategoryProperty> categoryProperties = categoryPropertyRepository.getByCategory(item.getCategoryId());
                List<CategoryPropertyValue> categoryPropertyValues = categoryPropertyValueRepository.getByCategory(item.getCategoryId());
                item.setProperties(new ArrayList<ItemProperty>());
                if (itemProperties != null) {
                    for (ItemProperty ip : itemProperties) {
                        ItemProperty itemPro = new ItemProperty();
                        if (categoryProperties != null) {
                            for (CategoryProperty cp : categoryProperties) {
                                if (ip.getCategoryPropertyId().equals(cp.getId())) {
                                    itemPro.setCategoryPropertyId(cp.getName());
                                    itemPro.setCategoryPropertyValueIds(new ArrayList<String>());
                                    item.getProperties().add(itemPro);
                                    break;
                                }
                            }
                        }
                        for (String v : ip.getCategoryPropertyValueIds()) {
                            if (categoryPropertyValues != null) {
                                for (CategoryPropertyValue cpv : categoryPropertyValues) {
                                    if (v.equals(cpv.getId())) {
                                        itemPro.getCategoryPropertyValueIds().add(cpv.getValue() + "");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            itemSearchRepository.index(item);
        } catch (Exception ex) {
            Logger.getLogger("item_index").log(Level.ERROR, ex.getMessage(), ex);
        }
    }
}
