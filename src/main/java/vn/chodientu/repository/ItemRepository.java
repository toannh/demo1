package vn.chodientu.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.enu.Condition;
import vn.chodientu.entity.enu.ItemSource;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.util.ListUtils;

@Repository
public class ItemRepository extends BaseRepository<Item> {

    @Autowired
    private MongoTemplate mongoTemplate;

    public ItemRepository() {
        super(Item.class);
    }

    @Async
    public void bulkSave(List<Item> items) {
        for (Item item : items) {
            save(item);
        }
    }

    /**
     * Đếm tổng số lương item theo thương hiệu
     *
     * @param manufacturerId
     * @return
     */
    public long countByManufacturer(String manufacturerId) {
        return count(new Query(new Criteria("manufacturerId").is(manufacturerId)));
    }

    /**
     * Đếm tổng số lượng item theo danh mục
     *
     * @param categoryId
     * @return
     */
    public long countByCategory(String categoryId) {
        return count(new Query(new Criteria("categoryId").is(categoryId)));
    }

    /**
     * Đếm tổng số lượng item theo model
     *
     * @param modelId
     * @return
     */
    public long countByModel(String modelId) {
        return count(new Query(new Criteria("modelId").is(modelId)));
    }

    public List<Item> getByCategory(String shopCategoryId, String categoryId, String sellerId) {
        Query query;
        if (shopCategoryId == null) {
            query = new Query(new Criteria("sellerId").is(sellerId)
                    .and("categoryPath").is(categoryId).and("listingType").ne(ListingType.AUCTION)
                    .and("sellPrice").gt(0));
        } else {
            query = new Query(new Criteria("sellerId").is(sellerId)
                    .and("shopCategoryPath").is(shopCategoryId).and("listingType").ne(ListingType.AUCTION)
                    .and("sellPrice").gt(0));
        }
        return getMongo().find(query, getEntityClass());
    }

    public long countBySeller(String sellerId) {
        return getMongo().count(new Query(new Criteria("sellerId").is(sellerId)), getEntityClass());
    }

    public Item findOneBySeller(String sellerId) {
        return getMongo().findOne(new Query(new Criteria("sellerId").is(sellerId)).with(new Sort(Sort.Direction.ASC, "createTime")), getEntityClass());
    }

    public List<Item> getBySeller(Pageable page, String sellerId) {
        Query query = new Query(new Criteria("sellerId").is(sellerId));
//        query.fields().exclude("properties");
        return getMongo().find(query.with(page), getEntityClass());
    }

    public List<Item> getBSeller(String sellerId) {
        Criteria cri = new Criteria("sellerId").is(sellerId);
        Query query = new Query(cri).with(new Sort(Sort.Direction.DESC, "createTime"));
        return getMongo().find(query, getEntityClass());
    }

    public List<Item> getBSellerLimit(String sellerId) {
        Criteria cri = new Criteria("sellerId").is(sellerId);
        Query query = new Query(cri).with(new Sort(Sort.Direction.DESC, "createTime")).limit(4);
        return getMongo().find(query, getEntityClass());
    }

    public List<Item> getBModel(String modelId) {
        Criteria cri = new Criteria("modelId").is(modelId);
        Query query = new Query(cri).with(new Sort(Sort.Direction.ASC, "sellPrice"));
        return getMongo().find(query, getEntityClass());
    }

    /**
     * Lấy danh sách sp theo trang
     *
     * @param pageRequest
     * @return
     */
    public List<Item> list(PageRequest pageRequest) {
        return getMongo().find(new Query().with(pageRequest), getEntityClass());
    }

    /**
     * lấy danh sách sản phẩm theo list id
     *
     * @param ids
     * @return
     */
    public List<Item> get(List<String> ids) {
        Query query = new Query(new Criteria("id").in(ids));
        return ListUtils.orderByArray(getMongo().find(query, getEntityClass()), ids);
    }

    /**
     * Lấy thời gian update lớn nhất của tất cả item, dùng để convert, convert
     * xong sẽ xóa
     *
     * @return
     */
    public long getLastUpdate() {
        Item i = getMongo().findOne(new Query().with(new Sort(Sort.Direction.DESC, "updateTime")), getEntityClass());
        return i == null ? 0 : i.getUpdateTime();
    }

    public void updateCityId(String sellerId, String cityId) {
        getMongo().updateMulti(new Query(new Criteria("sellerId").is(sellerId)),
                new Update().set("cityId", cityId), getEntityClass());
    }

    /**
     * lấy danh sách sản phẩm theo list id
     *
     * @param ids
     * @return
     */
    public List<Item> get(List<String> ids, int active) {
        Criteria cri = new Criteria("id").in(ids);
        if (active == 1) {
            cri.and("active").is(true);
            cri.and("startTime").lte(System.currentTimeMillis());
            cri.and("endTime").gte(System.currentTimeMillis());
            cri.and("completed").is(true);
            cri.and("approve").is(true);
            cri.and("quatity").gt(0);
        }
        Query query = new Query(cri);
        return ListUtils.orderByArray(getMongo().find(query, getEntityClass()), ids);
    }

    public List<String> getDistincCateByUser(String userId) {
        DBCollection myColl = getMongo().getCollection("item");
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("sellerId", userId);
        gtQuery.put("source", new BasicDBObject("$ne", ItemSource.CRAWL.toString()));
        gtQuery.put("completed", true);
        //gtQuery.put("$sort", new BasicDBObject("createTime", -1));

        List distinct = myColl.distinct("categoryId", gtQuery);

        return distinct;
    }

    /**
     * get by sellerSku
     *
     * @param sellerSku
     * @return
     */
    public Item getBySellerSku(String sellerSku) {
        return getMongo().findOne(new Query(new Criteria("sellerSku").is(sellerSku)), getEntityClass());
    }

    public Item getBySellerSku(String sellerSku, String sellerId) {
        return getMongo().findOne(new Query(new Criteria("sellerSku").is(sellerSku).and("sellerId").is(sellerId)), getEntityClass());
    }

    public long countSellerByModel(Criteria cri) {
        Aggregation aggregations = Aggregation.newAggregation(
                match(cri),
                group("sellerId").sum("quantity").as("quantity"),
                project("sellerId")
        );
        AggregationResults<DBObject> results = mongoTemplate.aggregate(aggregations, "item", DBObject.class);
        List<DBObject> fieldList = results.getMappedResults();
        return fieldList.size();
    }

    public long getNewMaxPrice(String modelId) {
        Item i = getMongo().findOne(new Query(new Criteria("modelId").is(modelId)
                .and("listingType").is(ListingType.BUYNOW.toString())
                .and("startTime").lte(System.currentTimeMillis())
                .and("endTime").gte(System.currentTimeMillis())
                .and("sellPrice").gt(0)
                .and("quantity").gt(0)
                .and("condition").is(Condition.NEW.toString())
                .and("approved").is(true)
                .and("active").is(true)
                .and("completed").is(true))
                .with(new Sort(Sort.Direction.DESC, "sellPrice")), getEntityClass());
        if (i == null) {
            return 0;
        }
        return i.getSellPrice();
    }

    public long getNewMinPrice(String modelId) {
        Item i = getMongo().findOne(new Query(new Criteria("modelId").is(modelId)
                .and("listingType").is(ListingType.BUYNOW.toString())
                .and("startTime").lte(System.currentTimeMillis())
                .and("endTime").gte(System.currentTimeMillis())
                .and("sellPrice").gt(0)
                .and("quantity").gt(0)
                .and("condition").is(Condition.NEW.toString())
                .and("approved").is(true)
                .and("active").is(true)
                .and("completed").is(true))
                .with(new Sort(Sort.Direction.ASC, "sellPrice")), getEntityClass());
        if (i == null) {
            return 0;
        }
        return i.getSellPrice();
    }

    public long getOldMaxPrice(String modelId) {
        Item i = getMongo().findOne(new Query(new Criteria("modelId").is(modelId)
                .and("listingType").is(ListingType.BUYNOW.toString())
                .and("startTime").lte(System.currentTimeMillis())
                .and("endTime").gte(System.currentTimeMillis())
                .and("sellPrice").gt(0)
                .and("quantity").gt(0)
                .and("condition").is(Condition.OLD.toString())
                .and("approved").is(true)
                .and("active").is(true)
                .and("completed").is(true))
                .with(new Sort(Sort.Direction.DESC, "sellPrice")), getEntityClass());
        if (i == null) {
            return 0;
        }
        return i.getSellPrice();
    }

    public long getOldMinPrice(String modelId) {
        Item i = getMongo().findOne(new Query(new Criteria("modelId").is(modelId)
                .and("listingType").is(ListingType.BUYNOW.toString())
                .and("startTime").lte(System.currentTimeMillis())
                .and("endTime").gte(System.currentTimeMillis())
                .and("sellPrice").gt(0)
                .and("quantity").gt(0)
                .and("condition").is(Condition.OLD.toString())
                .and("approved").is(true)
                .and("active").is(true)
                .and("completed").is(true))
                .with(new Sort(Sort.Direction.ASC, "sellPrice")), getEntityClass());
        if (i == null) {
            return 0;
        }
        return i.getSellPrice();
    }

    /**
     * Đếm tổng số lượng item theo model
     *
     * @param modelId
     * @return
     */
    public long countItemByModel(String modelId) {
        return count(new Query(new Criteria("modelId").is(modelId)
                .and("startTime").lte(System.currentTimeMillis())
                .and("endTime").gte(System.currentTimeMillis())
                .andOperator(new Criteria().orOperator(new Criteria("sellPrice").gt(0)), new Criteria("listingType").is(ListingType.BUYNOW.toString()))
                .and("quantity").gt(0)
                .and("approved").is(true)
                .and("active").is(true)
                .and("completed").is(true)));
    }
}
