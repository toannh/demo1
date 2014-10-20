package vn.chodientu.repository;

import com.mongodb.DBObject;
import static java.lang.Long.parseLong;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Order;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.enu.PaymentStatus;
import vn.chodientu.entity.enu.ShipmentStatus;

/**
 *
 * @author Phu
 */
@Repository
public class OrderRepository extends BaseRepository<Order> {

    @Autowired
    private MongoTemplate mongoTemplate;

    public OrderRepository() {
        super(Order.class);
    }

    /**
     * Đếm số lượng hóa đơn đơn hàng theo trạng thái thanh toán
     *
     * @param paymentStatus
     * @param seller
     * @param userId
     * @return
     */
    public long countByPaymentStatus(PaymentStatus paymentStatus, boolean seller, String userId) {
        Criteria criteria = new Criteria();
        if (seller == true) {
            if (paymentStatus == PaymentStatus.PAID) {
                criteria.and("sellerId").is(userId);
                criteria.and("markSellerPayment").is(paymentStatus);

            } else {
                criteria.and("sellerId").is(userId);
                criteria.and("markSellerPayment").ne(PaymentStatus.PAID);
            }

        } else {
            if (paymentStatus == PaymentStatus.PAID) {
                criteria.and("buyerId").is(userId);
                criteria.and("markBuyerPayment").is(paymentStatus);

            } else {
                criteria.and("buyerId").is(userId);
                criteria.and("markBuyerPayment").ne(PaymentStatus.PAID);
            }

        }
        criteria.and("remove").is(false);
        return count(new Query(criteria));
    }

    /**
     * *
     * Đếm số lượng hóa đơn đơn hàng theo trạng thái vận chuyển
     *
     * @param shipmentStatus
     * @param seller
     * @param userId
     * @return
     */
    public long countByShipmentStatus(ShipmentStatus shipmentStatus, boolean seller, String userId) {
        Criteria criteria = new Criteria();
        if (seller == true) {
            criteria.and("sellerId").is(userId);
            if (shipmentStatus == ShipmentStatus.DELIVERED) {
                criteria.and("markSellerShipment").is(shipmentStatus);
            } else {
                criteria.and("markSellerShipment").ne(ShipmentStatus.DELIVERED);
            }

        } else {
            criteria.and("buyerId").is(userId);
            if (shipmentStatus == ShipmentStatus.DELIVERED) {
                criteria.and("markBuyerShipment").is(shipmentStatus);
            } else {
                criteria.and("markBuyerShipment").ne(ShipmentStatus.DELIVERED);
            }
        }
        criteria.and("remove").is(false);
        return count(new Query(criteria));
    }

    /**
     * *
     * Đếm số lượng hóa đơn đơn hàng ở thùng rác
     *
     * @param remove
     * @param seller
     * @param userId
     * @return
     */
    public long countByRemove(boolean remove, boolean seller, String userId) {
        Criteria criteria = new Criteria();
        criteria.and("remove").is(remove);
        if (seller == true) {
            criteria.and("sellerId").is(userId);
        } else {
            criteria.and("buyerId").is(userId);
        }
        return count(new Query(criteria));
    }

    /**
     * *
     * Đếm số lượng hóa đơn đơn hàng
     *
     * @param remove
     * @param seller
     * @param userId
     * @return
     */
    public long countByOrderAll(boolean seller, String userId) {
        Criteria criteria = new Criteria();
        criteria.and("remove").is(false);
        if (seller == true) {
            criteria.and("sellerId").is(userId);
        } else {
            criteria.and("buyerId").is(userId);
        }
        return count(new Query(criteria));
    }

    /**
     * @shipmentCreateTime Lấy những đơn hàng có mã đơn hàng dưới 1 tháng
     * @shipmentUpdateTime 2 tiếng cập nhật 1 lần
     * @shipmentStatus đang được vận hành
     * @scId có mã vận đơn
     * @return
     */
    public Order getThread() {
        Date date = new Date();
        date.setMonth(date.getMonth() + 1);
        Criteria cri = new Criteria();
        cri.and("shipmentCreateTime").lte(date.getTime());
        cri.and("shipmentUpdateTime").
                lt(System.currentTimeMillis() - 2 * 60 * 60 * 1000);
        cri.and("scId").ne(null);
        List<ShipmentStatus> shipment = new ArrayList<>();
        shipment.add(ShipmentStatus.RETURN);
        shipment.add(ShipmentStatus.DELIVERED);
        shipment.add(ShipmentStatus.DENIED);
        cri.and("shipmentStatus").nin(shipment);
        return getMongo().findAndModify(new Query(cri).with(new Sort(Sort.Direction.ASC, "shipmentUpdateTime")).limit(1),
                new Update().update("shipmentUpdateTime", System.currentTimeMillis()), getEntityClass());
    }

    /**
     * Khách hàng chưa được add
     *
     * @return
     */
    public Order getCustomer() {
        Criteria cri = new Criteria();
        cri.and("customer").is(false);
        return getMongo().findAndModify(new Query(cri), new Update().update("customer", true), getEntityClass());
    }

    /**
     * lấy thông tin theo thread
     *
     * @param buyerId
     * @return
     */
    public Order getCustomerOrder() {
        Criteria cri = new Criteria();
        cri.and("ignoreCustomer").is(false);
        cri.and("buyerId").ne(null);
        return getMongo().findAndModify(new Query(cri), new Update().update("ignoreCustomer", true), getEntityClass());
    }

    /**
     * Tính tổng tiền theo điều kiện search
     *
     * @param cri
     * @return
     */
    public Map<String, Long> sumPrice(Criteria cri) {
        Aggregation aggregations = Aggregation.newAggregation(
                match(cri),
                group("null").sum("finalPrice").as("finalPrice")
                .sum("totalPrice").as("totalPrice"),
                project("finalPrice", "totalPrice")
        );
        AggregationResults<DBObject> results = mongoTemplate.aggregate(aggregations, "order", DBObject.class);
        List<DBObject> fieldList = results.getMappedResults();
        Map<String, Long> map = new HashMap<>();
        if (fieldList != null && !fieldList.isEmpty()) {
            for (DBObject db : fieldList) {
                map.put("finalPrice", parseLong(db.get("finalPrice").toString()));
                map.put("totalPrice", parseLong(db.get("totalPrice").toString()));
            }
        }
        return map;
    }

    public Order addContact() {
        Criteria cri = new Criteria();
        cri.and("pushC").ne(true);
        return getMongo().findAndModify(new Query(cri),
                new Update().set("pushC", true), new FindAndModifyOptions().returnNew(true), getEntityClass());
    }

    public Order pushPayment() {
        Criteria cri = new Criteria();
        cri.and("pushPayment").ne(true);
        return getMongo().findAndModify(new Query(cri),
                new Update().set("pushPayment", true), new FindAndModifyOptions().returnNew(true), getEntityClass());
    }

}
