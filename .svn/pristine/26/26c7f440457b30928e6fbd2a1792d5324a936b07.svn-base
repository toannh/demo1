/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.entity.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public class ManufacturerForm {
    
    private String id;
    
    @NotEmpty(message = "Tên thương hiệu không được để trống")
    @Length(max = 128, message = "Tên thương hiệu không được dài quá 128 ký tự")
    private String name;    
    private MultipartFile image;    
    private boolean active;    
    private boolean global;
    @Length(max = 1024, message = "Mô tả thương hiệu không được dài quá 1024 ký tự")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
