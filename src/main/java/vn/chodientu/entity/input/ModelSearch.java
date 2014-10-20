package vn.chodientu.entity.input;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import vn.chodientu.util.UrlUtils;

/**
 *
 * @author thanhvv
 */
public class ModelSearch implements Serializable {

    private String modelId;
    private String categoryId;
    private String manufacturerId;
    private List<String> manufacturerIds;
    private List<PropertySearch> properties;
    private String keyword;
    private int status; // 1.active,approved -- 2.inactive -- 3.unapproved
    private int pageIndex;
    private int pageSize;
    private int orderBy;

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getManufacturerIds() {
        return manufacturerIds;
    }

    public void setManufacturerIds(List<String> manufacturerIds) {
        this.manufacturerIds = manufacturerIds;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<PropertySearch> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertySearch> properties) {
        this.properties = properties;
    }

    public String getKey() throws IOException {
        ModelSearch modelSearch;
        try {
            modelSearch = (ModelSearch) BeanUtils.cloneBean(this);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(UrlUtils.class.getName()).log(Level.SEVERE, null, ex);
            modelSearch = new ModelSearch();
        }
        modelSearch.setPageIndex(0);
        modelSearch.setPageSize(0);
        modelSearch.setOrderBy(0);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(modelSearch);
        oos.close();
        return DigestUtils.md5Hex(baos.toByteArray());
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

}
