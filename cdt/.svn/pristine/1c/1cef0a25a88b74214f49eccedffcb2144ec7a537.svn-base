/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.Coupon;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.CouponRepository;

/**
 *
 * @author Linhnt
 */
@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private Validator validator;

    public Response add(Coupon coupon) {
        Map<String, String> errors = validator.validate(coupon);

        long timeNow = new Date().getTime();
        long defaultTimeRange = 7 * 24 * 60 * 60 * 1000;
        long startTime;
        long endTime = 0;
        if (coupon.getStartTime() == 0) {
            startTime = timeNow;
            if (coupon.getEndTime() == 0) {
                endTime = startTime + defaultTimeRange;
            } else {
                if (coupon.getEndTime() < timeNow) {
                    errors.put("endTime", "Thời gian kết thúc phải lớn hơn thời gian hiện tại");
                } else {
                    endTime = coupon.getEndTime();
                }
            }
        } else {
            startTime = coupon.getStartTime();
            if (coupon.getStartTime() < timeNow) {
                errors.put("startTime", "Thời gian bắt đầu phải lớn hơn thời gian hiện tại");
            } else {
                if (coupon.getStartTime() > 0 && coupon.getEndTime() == 0) {
                    endTime = coupon.getStartTime() + defaultTimeRange;
                } else {
                    if (coupon.getStartTime() > coupon.getEndTime()) {
                        errors.put("endTime", "Thời gian kết thúc phải lớn hơn thời gian bắt đầu");
                    } else {
                        endTime = coupon.getEndTime();
                    }
                }
            }
        }

        Coupon currentCoupon = checkExistCouponByTime(coupon.getSellerId(), startTime);
        if (currentCoupon != null) {
            errors.put("startTime", "Đã có chương trình khuyến mại coupon trong khoảng thời gian này, bạn hãy chọn khoảng thời gian khác");
        }
        if (coupon.getDiscountPrice() == 0 && coupon.getDiscountPercent() == 0) {
            errors.put("discountPrice", "Bạn phải chọn mức giảm giá");
        }
        if (coupon.getDiscountPrice() < 0) {
            errors.put("discountPrice", "Mức giảm giá theo số tiền phải là số lớn hơn 0");
        }
        if (coupon.getDiscountPercent() > 100) {
            errors.put("discountPrice", "Mức giảm theo % phải nhỏ hơn 100 và lớn hơn 0");
        }
        if (coupon.getUsedquantity() <= 0) {
            errors.put("usedquantity", "Số lượng sử dụng phải là số lớn hơn 0");
        }
        if (coupon.getMinOrderValue() <= 0) {
            errors.put("minOrderValue", "Giá trị đơn hàng phải là số lớn hơn 0");
        }
        if (coupon.getCode() == null || coupon.getCode().equals("")) {
            errors.put("code", "Mã coupon chưa có");
        }
        if (errors.isEmpty()) {
            Coupon c = new Coupon();
            c.setId(couponRepository.genId());
            c.setName(coupon.getName());
            c.setCreateTime(new Date().getTime());
            c.setStartTime(startTime);
            c.setEndTime(endTime);
            c.setSellerId(coupon.getSellerId());
            c.setDiscountPercent(coupon.getDiscountPercent());
            c.setDiscountPrice(coupon.getDiscountPrice());
            c.setMinOrderValue(coupon.getMinOrderValue());
            c.setActive(true);
            c.setCode(coupon.getCode());
            c.setUsedquantity(coupon.getUsedquantity());
            couponRepository.save(c);
            return new Response(true, "Đã thêm thành công");
        } else {
            return new Response(false, "Thêm mới thất bại", errors);
        }

    }

    /**
     * check co coupon nao trong 1 khoang thoi gian
     *
     * @param sellerId
     * @param timeCheck khoang thoi gian can check
     * @return list coupon
     */
    public Coupon checkExistCouponByTime(String sellerId, long timeCheck) {
        return couponRepository.getCouponByTime(sellerId, timeCheck);
    }

    /**
     * Lấy danh sách Coupon theo userId
     *
     * @param userId
     * @return
     */
    public List<Coupon> getListCouponByUserId(String userId) {
        return couponRepository.getListCouponBySellerId(userId);
    }

    /**
     * Dừng coupon by seller
     *
     * @param code
     * @param sellerId
     * @return
     */
    public Response stopCouponByCode(String code, String sellerId) {
        if (code == null || sellerId == null || code.equals("") || sellerId.equals("")) {
            return new Response(false, "Lỗi", "Thông tin xử lý không đầy đủ");
        }
        Coupon coupon = couponRepository.findByCode(code);
        if (coupon == null) {
            return new Response(false, "Lỗi", "Không tồn tại mã coupon: " + code);
        } else {
            if (coupon.getSellerId().equals(sellerId)) {
                if (coupon.isActive()) {
                    coupon.setActive(false);
                    couponRepository.save(coupon);
                    return new Response(true, "OK", code);
                } else {
                    return new Response(false, "Lỗi", "Coupon này hiện đã kết thúc");
                }
            } else {
                return new Response(false, "Lỗi", "Mã coupon không phải của người bán này");
            }
        }
    }

    public DataPage<Coupon> getBySeller(String sellerId, int pageIndex) {
        DataPage<Coupon> page = new DataPage<>();
        if (pageIndex <= 0) {
            pageIndex = 0;
        }
        page.setDataCount(couponRepository.getListCouponBySellerId(sellerId).size());
        page.setData(couponRepository.getListCouponBySellerId(sellerId));
        page.setPageIndex(pageIndex);
        page.setPageSize(2);
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() > 0) {
            page.setPageCount(page.getPageCount() + 1);
        }
        return page;
    }

    /**
     * tìm kiếm sản phẩm trực tiếp mongo
     *
     * @param sellerId
     * @param keyword
     * @param status
     * @param pageIndex
     * @param pageSize
     * @return kết quả là danh sách sản phẩm đủ điều kiện theo trang
     */
    public DataPage<Coupon> search(String sellerId, String keyword, int status, int pageIndex, int pageSize) {
        Criteria criteria = new Criteria();
        criteria.and("sellerId").is(sellerId);
        if (keyword != null && !keyword.equals("")) {
            criteria.and("name").regex(keyword);
        }
        if (status > 0) {
            switch (status) {
                case 1:
                    criteria.and("startTime").lt(System.currentTimeMillis());
                    break;
                case 2:
                    criteria.and("startTime").lte(System.currentTimeMillis()).and("endtime").gte(System.currentTimeMillis());
                    break;
                case 3:
                    criteria.and("active").is(false);
                    break;
            }
        }
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "createTime"));
        query.skip(pageIndex * pageSize).limit(pageSize);

        DataPage<Coupon> page = new DataPage<>();
        page.setPageSize(pageSize);
        page.setPageIndex(pageIndex);
        if (page.getPageSize() <= 0) {
            page.setPageSize(2);
        }
        page.setDataCount(couponRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }

        page.setData(couponRepository.find(query));
        return page;
    }

    public int getCountCouponBySeller(String sellerId) {
        List<Coupon> listCouponBySellerId = couponRepository.getListCouponBySellerId(sellerId);
        return listCouponBySellerId.size();
    }

    /**
     * Validate coupon
     *
     * @param code
     * @param sellerId
     * @return
     */
    public Response validate(String code, String sellerId) {
        Coupon coupon = couponRepository.findByCode(code);
        if (coupon == null) {
            return new Response(false, "Không tìm thấy mã coupon");
        }
        if (coupon.getStartTime() > System.currentTimeMillis()) {
            return new Response(false, "Mã coupon chưa đến hạn sử dụng");
        }
        if (coupon.getEndTime() < System.currentTimeMillis()) {
            return new Response(false, "Mã coupon đã hết hạn sử dụng");
        }
        if (!coupon.getSellerId().equals(sellerId)) {
            return new Response(false, "Mã coupon không thuộc người bán này");
        }
        return new Response(true, "Chi tiết coupon", coupon);
    }

}
