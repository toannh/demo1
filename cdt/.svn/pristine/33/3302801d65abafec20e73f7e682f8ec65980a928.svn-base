/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.entity.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class UserChangePassForm {

    @NotBlank(message = "Mật khẩu cũ không được để trống!")
    @Length(min = 6, max = 25, message = "Mật khẩu phải có tối thiểu 6 kí tự!")
    private String oldPass;
    @NotBlank(message = "Mật khẩu mới không được để trống")
    @Length(min = 6, max = 25, message = "Mật khẩu phải có tối thiểu 6 kí tự!")
    private String newPass;
    @NotBlank(message = "Xác nhận mật khẩu không được để trống!")
    private String confirmPass;
    private String email;

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
}
