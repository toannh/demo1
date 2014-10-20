/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.entity.form;

import org.springframework.web.multipart.MultipartFile;

public class UserAvatarForm {

    private String userId;
    private MultipartFile avatar;
    private String itemIdDel;
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public String getItemIdDel() {
        return itemIdDel;
    }

    public void setItemIdDel(String itemIdDel) {
        this.itemIdDel = itemIdDel;
    }
    

}
