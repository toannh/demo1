/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.entity.enu;

import java.io.Serializable;

/**
 *
 * @author Vu Van Thanh
 */
public enum SellerPolicyType implements Serializable {

    /**
     * Chính sách bảo hành - đổi trả
     */
    WARRANT,
    /**
     * Chính sách vận chuyển - giao hàng
     */
    SHIPPING,
    /**
     * Chính sách lắp đặt - thi công
     */
    INSTALLATION,
    /**
     * Chính sách hậu mãi & chăm sóc khách hàng
     */
    SUPPORT
}
