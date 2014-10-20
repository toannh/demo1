/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.entity.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author PhuongDT
 */
public class CategoryForm {

    @Length(max = 32, message = "Mã danh mục không được dài quá 32 ký tự")
    private String id;

    @Length(max = 32, message = "Mã danh mục cha không được dài quá 32 ký tự")
    private String parentId;

    @NotEmpty(message = "Tên danh mục không được để trống")
    @Length(max = 128, message = "Tên danh mục không được dài quá 128 ký tự")
    private String name;
    @Length(max = 1024, message = "Mô tả danh mục không được dài quá 1024 ký tự")
    private String description;
    private String ebayCategoryId;
    private String ebayKeyword;
    private int weight;
    private String path;
    private int order = 1;
    private boolean active;
    private boolean display;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEbayCategoryId() {
        return ebayCategoryId;
    }

    public void setEbayCategoryId(String ebayCategoryId) {
        this.ebayCategoryId = ebayCategoryId;
    }

    public String getEbayKeyword() {
        return ebayKeyword;
    }

    public void setEbayKeyword(String ebayKeyword) {
        this.ebayKeyword = ebayKeyword;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
}
