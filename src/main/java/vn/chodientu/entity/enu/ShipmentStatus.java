package vn.chodientu.entity.enu;

import java.io.Serializable;

/**
 *
 * @author Phu
 */
public enum ShipmentStatus implements Serializable {

    /**
     * Đơn hàng mới, chưa duyệt
     */
    NEW,
    /**
     * Chưa lấy hàng
     */
    STOCKING,
    /**
     * Đang giao hàng
     */
    DELIVERING,
    /**
     * Chuyển hoàn
     */
    RETURN,
    /**
     * Hủy
     */
    DENIED,
    /**
     * Thành công
     */
    DELIVERED
}
