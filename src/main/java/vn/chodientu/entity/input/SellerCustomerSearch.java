/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.entity.input;

import java.io.Serializable;

/**
 *
 * @author thanhvv
 */
public class SellerCustomerSearch implements Serializable {

    /**
     * Kí tự bắt đầu : A | B |C ...
     */
    private String cname;
    private String name;
    private String email;
    private String phone;
    /**
     * @1.Khách hàng mới
     * @4.Khách hàng mới sửa gần đây
     * @2.Danh sách email đủ điều kiện
     * @3.Danh sách phone đủ điều kiện
     */
    private int option;
    private int pageIndex;
    private int pageSize;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

}
