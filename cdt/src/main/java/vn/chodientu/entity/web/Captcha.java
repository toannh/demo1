package vn.chodientu.entity.web;

import java.io.Serializable;

/**
 * @since May 15, 2014
 * @author Phu
 */
public class Captcha implements Serializable{

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public boolean validate(String token) {
        if (this.token == null) {
            return false;
        }
        return token.toLowerCase().equals(this.token.toLowerCase());
    }
}
