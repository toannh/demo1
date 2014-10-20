package vn.chodientu.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.report.ReportCash;
import vn.chodientu.entity.db.report.ReportItem;
import vn.chodientu.entity.db.report.ReportLading;
import vn.chodientu.entity.db.report.ReportOrder;
import vn.chodientu.entity.db.report.ReportShop;
import vn.chodientu.entity.db.report.ReportUser;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.ItemSource;
import vn.chodientu.entity.enu.PaymentMethod;
import vn.chodientu.entity.enu.PaymentStatus;
import vn.chodientu.entity.enu.ShipmentStatus;
import vn.chodientu.entity.input.ReportSearch;
import vn.chodientu.entity.output.ItemHistogram;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.CashTransactionRepository;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.LadingRepository;
import vn.chodientu.repository.OrderRepository;
import vn.chodientu.repository.ReportCashRepository;
import vn.chodientu.repository.ReportItemRepository;
import vn.chodientu.repository.ReportLadingRepository;
import vn.chodientu.repository.ReportOrderRepository;
import vn.chodientu.repository.ReportShopRepository;
import vn.chodientu.repository.ReportUserRepository;
import vn.chodientu.repository.ShopRepository;
import vn.chodientu.repository.UserRepository;

@Service
public class ReportService {

    @Autowired
    private ReportUserRepository reportUserRepository;
    @Autowired
    private ReportShopRepository reportShopRepository;
    @Autowired
    private ReportOrderRepository reportOrderRepository;
    @Autowired
    private ReportLadingRepository reportLadingRepository;
    @Autowired
    private ReportItemRepository reportItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private LadingRepository ladingRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CashTransactionRepository cashTransactionRepository;
    @Autowired
    private ReportCashRepository reportCashRepository;

    @Scheduled(fixedDelay = 12 * 60 * 60 * 1000)
    public void run() {
        this.runReportUser();
        this.runReportShop();
        this.runReportOder();
        this.runReportItem();
        this.runReportLading();
        this.runReportCash();
    }

    @Async
    public void runReportItem() {
        long time = this.getTime(System.currentTimeMillis(), false);
        ReportItem report = reportItemRepository.find(time);
        if (report == null) {
            report = new ReportItem();
            report.setCreateTime(System.currentTimeMillis());
            report.setTime(time);
        }
        report.setUpdateTime(System.currentTimeMillis());
        this.cItem(report);
        this.cItemSource(report);
        reportItemRepository.save(report);
    }

    @Async
    public void runReportCash() {
        long time = this.getTime(System.currentTimeMillis(), false);
        ReportCash report = reportCashRepository.find(time);
        if (report == null) {
            report = new ReportCash();
            report.setCreateTime(System.currentTimeMillis());
            report.setTime(time);
        }
        report.setUpdateTime(System.currentTimeMillis());
        this.cCash(report);
        reportCashRepository.save(report);
    }

    @Async
    public void runReportLading() {
        long time = this.getTime(System.currentTimeMillis(), false);
        ReportLading report = reportLadingRepository.find(time);
        if (report == null) {
            report = new ReportLading();
            report.setCreateTime(System.currentTimeMillis());
            report.setTime(time);
        }
        report.setUpdateTime(System.currentTimeMillis());
        this.cLading(report);
        reportLadingRepository.save(report);
    }

    @Async
    public void runReportOder() {
        long time = this.getTime(System.currentTimeMillis(), false);
        ReportOrder reportOrder = reportOrderRepository.find(time);
        if (reportOrder == null) {
            reportOrder = new ReportOrder();
            reportOrder.setCreateTime(System.currentTimeMillis());
            reportOrder.setTime(time);
        }
        reportOrder.setUpdateTime(System.currentTimeMillis());
        this.cOrderStatus(reportOrder);
        this.cOrderMethod(reportOrder);
        this.cOrder(reportOrder);
        reportOrderRepository.save(reportOrder);
    }

    @Async
    public void runReportUser() {
        long time = this.getTime(System.currentTimeMillis(), false);
        ReportUser reportUser = reportUserRepository.find(time);
        if (reportUser == null) {
            reportUser = new ReportUser();
            reportUser.setCreateTime(System.currentTimeMillis());
            reportUser.setTime(time);
        }
        //count newbie
        reportUser.setNewbie(this.countNewbie());
        reportUser.setTotalEmailVerified(this.countEmailVerified(true));
        reportUser.setEmailVerified(this.countEmailVerified(false));
        reportUser.setTotalPhoneVerified(this.countPhoneVerified(true));
        reportUser.setPhoneVerified(this.countPhoneVerified(false));
        reportUser.setTotalUser(userRepository.count(new Query(new Criteria())));
        reportUser.setUpdateTime(System.currentTimeMillis());
        reportUserRepository.save(reportUser);

    }

    @Async
    public void runReportShop() {
        long time = this.getTime(System.currentTimeMillis(), false);
        ReportShop reportShop = reportShopRepository.find(time);
        if (reportShop == null) {
            reportShop = new ReportShop();
            reportShop.setCreateTime(System.currentTimeMillis());
            reportShop.setTime(time);
        }
        reportShop.setUpdateTime(System.currentTimeMillis());
        reportShop.setShop(shopRepository.count(new Query(new Criteria())));
        reportShop.setNewshop(this.countNewShop());
        reportShopRepository.save(reportShop);
    }

    private void cLading(ReportLading report) {
        Criteria cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        report.setLading(ladingRepository.count(new Query(cri)));

        cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        cri.and("shipmentStatus").is(ShipmentStatus.NEW.toString());
        report.setNewlading(ladingRepository.count(new Query(cri)));

        cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        cri.and("shipmentStatus").is(ShipmentStatus.DELIVERED.toString());
        report.setDelivered(ladingRepository.count(new Query(cri)));

        cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        cri.and("shipmentStatus").is(ShipmentStatus.DELIVERING.toString());
        report.setDelivering(ladingRepository.count(new Query(cri)));

        cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        cri.and("shipmentStatus").is(ShipmentStatus.DENIED.toString());
        report.setDenied(ladingRepository.count(new Query(cri)));

        cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        cri.and("shipmentStatus").is(ShipmentStatus.RETURN.toString());
        report.setReturnLading(ladingRepository.count(new Query(cri)));

        cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        cri.and("shipmentStatus").is(ShipmentStatus.STOCKING.toString());
        report.setStocking(ladingRepository.count(new Query(cri)));
    }

    private void cOrder(ReportOrder reportOrder) {
        Map<String, Long> countPrice = null;
        Criteria cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        reportOrder.setQuantity(orderRepository.count(new Query(cri)));
        try {
            countPrice = orderRepository.sumPrice(cri);
            reportOrder.setFinalPrice(countPrice.get("finalPrice"));
            reportOrder.setTotalPrice(countPrice.get("totalPrice"));
        } catch (Exception e) {
        }
    }

    private void cCash(ReportCash reportCash) {
        Criteria criteria = new Criteria();
        criteria.and("time").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        criteria.orOperator(new Criteria("type").is(CashTransactionType.SPENT_UPITEM.toString()),
                new Criteria("type").is(CashTransactionType.SPENT_VIPITEM.toString()),
                new Criteria("type").is(CashTransactionType.SPENT_EMAIL.toString()),
                new Criteria("type").is(CashTransactionType.ACTIVE_MARKETING.toString()),
                new Criteria("type").is(CashTransactionType.ACTIVE_QUICK_SUBMIT.toString()),
                new Criteria("type").is(CashTransactionType.CLOSE_ADV.toString()),
                new Criteria("type").is(CashTransactionType.SPENT_SMS.toString()));
        reportCash.setUseBalance(cashTransactionRepository.sumForReport(criteria));
        
        
        criteria = new Criteria();
        criteria.orOperator(new Criteria("type").is(CashTransactionType.TOPUP_NL.toString()).and("nlStatus").ne(2).and("nlPayTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true)),
                new Criteria("type").is(CashTransactionType.SMS_NAP.toString()).and("time").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true)));
        
        reportCash.setTopup(cashTransactionRepository.sumForReport(criteria));

        criteria = new Criteria();
        criteria.and("time").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        Criteria cri1 = new Criteria("type").is(CashTransactionType.COMMENT_MODEL_REWARD.toString());
        Criteria cri2 = new Criteria("type").is(CashTransactionType.COMMENT_ITEM_REWARD.toString());
        Criteria cri3 = new Criteria("type").is(CashTransactionType.SELLER_POST_NEWS.toString());
        Criteria cri4 = new Criteria("type").is(CashTransactionType.VIEW_PAGE.toString());
        Criteria cri5 = new Criteria("type").is(CashTransactionType.SIGNIN.toString());
        Criteria cri6 = new Criteria("type").is(CashTransactionType.REGISTER.toString());
        Criteria cri7 = new Criteria("type").is(CashTransactionType.PAYMENT_SUSSESS_NL.toString());
        Criteria cri9 = new Criteria("type").is(CashTransactionType.INTEGRATED_NL.toString());
        Criteria cri10 = new Criteria("type").is(CashTransactionType.INTEGRATED_COD.toString());
        Criteria cri11 = new Criteria("type").is(CashTransactionType.SELLER_POST_ITEM.toString());
        Criteria cri12 = new Criteria("type").is(CashTransactionType.OPEN_SHOP.toString());
        Criteria cri13 = new Criteria("type").is(CashTransactionType.SELLER_CREATE_PROMOTION.toString());
        Criteria cri14 = new Criteria("type").is(CashTransactionType.BROWSE_LADING.toString());
        Criteria cri15 = new Criteria("type").is(CashTransactionType.EMAIL_VERIFIED.toString());
        Criteria cri16 = new Criteria("type").is(CashTransactionType.PHONE_VERIFIED.toString());
        criteria.orOperator(cri1, cri2, cri3, cri4, cri5, cri6, cri7, cri9, cri10, cri11, cri12, cri13, cri14, cri15, cri16);
        reportCash.setReward(cashTransactionRepository.sumForReport(criteria));
        reportCash.setUpSchedule(cashTransactionRepository.sumForReport(new Criteria().and("time").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true)).orOperator(new Criteria("type").is(CashTransactionType.SPENT_UPITEM.toString()))));
        reportCash.setVipItem(cashTransactionRepository.sumForReport(new Criteria().and("time").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true)).orOperator(new Criteria("type").is(CashTransactionType.SPENT_VIPITEM.toString()))));
        reportCash.setActiveCustomer(cashTransactionRepository.count(new Query(new Criteria().and("time").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true)).orOperator(new Criteria("type").is(CashTransactionType.ACTIVE_MARKETING.toString())))));
        reportCash.setActiveQuickBooking(cashTransactionRepository.count(new Query(new Criteria().and("time").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true)).orOperator(new Criteria("type").is(CashTransactionType.ACTIVE_QUICK_SUBMIT.toString())))));
    }

    private void cOrderStatus(ReportOrder reportOrder) {
        Map<String, Long> countPrice = null;
        Criteria cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        cri.and("paymentStatus").is(PaymentStatus.NEW.toString());
        reportOrder.setNewStatus(orderRepository.count(new Query(cri)));
        try {
            countPrice = orderRepository.sumPrice(cri);
            reportOrder.setFinalPriceNewStatus(countPrice.get("finalPrice"));
            reportOrder.setTotalPriceNewStatus(countPrice.get("totalPrice"));
        } catch (Exception e) {
        }

        cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        cri.and("paymentStatus").is(PaymentStatus.PAID.toString());
        reportOrder.setPaidStatus(orderRepository.count(new Query(cri)));
        try {
            countPrice = orderRepository.sumPrice(cri);
            reportOrder.setFinalPricePaidStatus(countPrice.get("finalPrice"));
            reportOrder.setTotalPricePaidStatus(countPrice.get("totalPrice"));
        } catch (Exception e) {
        }

        cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        cri.and("paymentStatus").is(PaymentStatus.PENDING.toString());
        reportOrder.setPadingStatus(orderRepository.count(new Query(cri)));
        try {
            countPrice = orderRepository.sumPrice(cri);
            reportOrder.setFinalPricePadingStatus(countPrice.get("finalPrice"));
            reportOrder.setTotalPricePadingStatus(countPrice.get("totalPrice"));
        } catch (Exception e) {
        }
    }

    private void cOrderMethod(ReportOrder reportOrder) {
        Map<String, Long> countPrice = null;
        Criteria cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        cri.and("paymentMethod").is(PaymentMethod.NONE.toString());
        reportOrder.setNonePayment(orderRepository.count(new Query(cri)));
        try {
            countPrice = orderRepository.sumPrice(cri);
            reportOrder.setFinalPriceNonePayment(countPrice.get("finalPrice"));
            reportOrder.setTotalPriceNonePayment(countPrice.get("totalPrice"));
        } catch (Exception e) {
        }

        cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        cri.and("paymentMethod").is(PaymentMethod.COD.toString());
        reportOrder.setCodPayment(orderRepository.count(new Query(cri)));
        try {
            countPrice = orderRepository.sumPrice(cri);
            reportOrder.setFinalPriceCodPayment(countPrice.get("finalPrice"));
            reportOrder.setTotalPriceCodPayment(countPrice.get("totalPrice"));
        } catch (Exception e) {
        }

        cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        cri.and("paymentMethod").is(PaymentMethod.NL.toString());
        reportOrder.setNlPayment(orderRepository.count(new Query(cri)));
        try {
            countPrice = orderRepository.sumPrice(cri);
            reportOrder.setFinalPriceNlPayment(countPrice.get("finalPrice"));
            reportOrder.setTotalPriceNlPayment(countPrice.get("totalPrice"));
        } catch (Exception e) {
        }

        cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        cri.andOperator(new Criteria("paymentMethod").is(PaymentMethod.VISA.toString()), new Criteria("paymentMethod").is(PaymentMethod.MASTER.toString()));
        reportOrder.setVisaPayment(orderRepository.count(new Query(cri)));
        try {
            countPrice = orderRepository.sumPrice(cri);
            reportOrder.setFinalPriceVisaPayment(countPrice.get("finalPrice"));
            reportOrder.setTotalPriceVisaPayment(countPrice.get("totalPrice"));
        } catch (Exception e) {
        }
    }

    private long countNewShop() {
        Criteria cri = new Criteria();
        cri.and("createTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        return shopRepository.count(new Query(cri));
    }

    private long countNewbie() {
        Criteria cri = new Criteria();
        cri.and("joinTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        return userRepository.count(new Query(cri));
    }

    private long countEmailVerified(boolean all) {
        Criteria cri = new Criteria();
        if (!all) {
            cri.and("emailVerifiedTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        }
        cri.and("emailVerified").is(true);
        return userRepository.count(new Query(cri));
    }

    private long countPhoneVerified(boolean all) {
        Criteria cri = new Criteria();
        if (!all) {
            cri.and("phoneVerifiedTime").gte(getTime(System.currentTimeMillis(), false)).lte(getTime(System.currentTimeMillis(), true));
        }
        cri.and("phoneVerified").is(true);
        return userRepository.count(new Query(cri));
    }

    private long getTime(long time, boolean endday) {
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            cal.setTime(new Date(time));
            return sdfTime.parse(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH) + (endday ? " 23:59" : " 00:00")).getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    private void cItem(ReportItem report) {
        List<ItemHistogram> histograms = itemService.getItemStatusHistogram(null);
        for (ItemHistogram itemHistogram : histograms) {
            switch (itemHistogram.getType()) {
                case "unapproved":
                    report.setUnapproved(itemHistogram.getCount());
                    break;
                case "outDate":
                    report.setOutDate(itemHistogram.getCount());
                    break;
                case "outOfStock":
                    report.setOutOfStock(itemHistogram.getCount());
                    break;
                case "uncompleted":
                    report.setUncompleted(itemHistogram.getCount());
                    break;
                case "recycle":
                    report.setRecycle(itemHistogram.getCount());
                    break;
                case "all":
                    report.setTotal(itemHistogram.getCount());
                    break;
                case "selling":
                    report.setSelling(itemHistogram.getCount());
                    break;
            }
        }
    }

    private void cItemSource(ReportItem report) {
        Criteria cri = new Criteria();
        cri.and("source").is(ItemSource.SELLER.toString());
        report.setSeller(itemRepository.count(new Query(cri)));

        cri = new Criteria();
        cri.and("source").is(ItemSource.API.toString());
        report.setApi(itemRepository.count(new Query(cri)));

        cri = new Criteria();
        cri.and("source").is(ItemSource.CRAWL.toString());
        report.setCrawl(itemRepository.count(new Query(cri)));
    }

    /**
     * build query
     *
     * @param search
     * @return
     */
    private Criteria buildSearch(ReportSearch search) {
        Criteria cri = new Criteria();
        long startTime = (search.getStartTime() > 0) ? search.getStartTime() : new Date().getTime();
        long endTime = (search.getEndTime() > 0) ? search.getEndTime() : new Date().getTime();
        cri.and("time").gte(startTime).lt(endTime);
        return cri;
    }

    private String converTime(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        return cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1);
    }

    //Service get data
    public Response findDataShop(ReportSearch search) {
        HashMap<String, Object> resp = new HashMap<String, Object>();
        //build query
        Criteria cri = this.buildSearch(search);
        //find data
        List<ReportShop> report = reportShopRepository.find(new Query(cri).with(new Sort(Sort.Direction.ASC, "time")));
        //create data report
        List<Object> data = new ArrayList<>();
        List<Object> row = new ArrayList<>();
        row.add("Ngày/Tháng");
        row.add("Tổng shop");
        row.add("Shop mới");
        data.add(row);
        for (ReportShop reportShop : report) {
            row = new ArrayList<>();
            row.add(this.converTime(reportShop.getTime()));
            row.add(reportShop.getShop());
            row.add(reportShop.getNewshop());
            data.add(row);
        }

        resp.put("chart", data);
        return new Response(true, "Thống kê dữ liệu shop", resp);
    }

    //Service get data
    public Response findDataItem(ReportSearch search) {
        HashMap<String, Object> resp = new HashMap<String, Object>();
        //build query
        Criteria cri = this.buildSearch(search);
        //find data
        List<ReportItem> report = reportItemRepository.find(new Query(cri).with(new Sort(Sort.Direction.ASC, "time")));
        //create data report
        List<Object> data = new ArrayList<>();
        List<Object> row = new ArrayList<>();
        row.add("Ngày/Tháng");
        row.add("Sản phẩm Crawl");
        row.add("Sản phẩm từ người bán đăng");
        row.add("Sản phẩm lấy từ API");
        row.add("Sản phẩm đang bán");
        row.add("Sản phẩm không được duyệt");
        row.add("Sản phẩm bị xóa");
        data.add(row);
        long crawl = 0;
        long seller = 0;
        long api = 0;
        long selling = 0;
        long unapproved = 0;
        long recycle = 0;
        for (ReportItem reportItem : report) {
            row = new ArrayList<>();
            row.add(this.converTime(reportItem.getTime()));
            row.add(reportItem.getCrawl());
            row.add(reportItem.getSeller());
            row.add(reportItem.getApi());
            row.add(reportItem.getSelling());
            row.add(reportItem.getUnapproved());
            row.add(reportItem.getRecycle());
            data.add(row);
            crawl += reportItem.getCrawl();
            seller += reportItem.getSeller();
            api += reportItem.getApi();
            selling += reportItem.getSelling();
            unapproved += reportItem.getUnapproved();
            recycle += reportItem.getRecycle();
        }
        resp.put("chart", data);

        row = new ArrayList<>();
        row.add("Sản phẩm Crawl: " + (crawl));
        row.add("Sản phẩm từ người bán đăng: " + (seller));
        row.add("Sản phẩm lấy từ API: " + (api));
        row.add("Sản phẩm không được duyệt: " + (unapproved));
        row.add("Sản phẩm bị xóa: " + (recycle));

        resp.put("dataSearch", row);
        List<ReportItem> report1 = reportItemRepository.find(new Query(new Criteria()).with(new PageRequest(0, 1)).with(new Sort(Sort.Direction.DESC, "time")));
        row = new ArrayList<>();
        row.add("Tổng số sản phẩm trên CĐT: " + report1.get(0).getTotal());
        row.add("Sản phẩm Crawl: " + report1.get(0).getCrawl());
        row.add("Sản phẩm từ người bán đăng: " + report1.get(0).getSeller());
        row.add("Sản phẩm lấy từ API: " + report1.get(0).getApi());
        row.add("Sản phẩm không được duyệt: " + report1.get(0).getUnapproved());
        row.add("Sản phẩm bị xóa: " + report1.get(0).getRecycle());

        resp.put("dataNow", row);
        resp.put("timeNow", report1.get(0).getTime());

        return new Response(true, "Thống kê dữ liệu item", resp);
    }

    //Service get data
    public Response findDataUser(ReportSearch search) {
        HashMap<String, Object> resp = new HashMap<String, Object>();
        //build query
        Criteria cri = this.buildSearch(search);
        //find data
        List<ReportUser> report = reportUserRepository.find(new Query(cri).with(new Sort(Sort.Direction.ASC, "time")));
        //create data report
        List<Object> data = new ArrayList<>();
        List<Object> row = new ArrayList<>();
        row.add("Ngày/Tháng");
        row.add("Người dùng mới");
        row.add("Người dùng kích hoạt email");
        row.add("Người dùng kích hoạt phone");
        data.add(row);
        for (ReportUser reportUser : report) {
            row = new ArrayList<>();
            row.add(this.converTime(reportUser.getTime()));
            row.add(reportUser.getNewbie());
            row.add(reportUser.getEmailVerified());
            row.add(reportUser.getPhoneVerified());
            data.add(row);
        }
        resp.put("chart", data);
        row = new ArrayList<>();
        row.add("Total User: " + report.get(report.size() - 1).getTotalUser());
        row.add("Total User EmailVerified: " + report.get(report.size() - 1).getTotalEmailVerified());
        row.add("Total User PhoneVerified: " + report.get(report.size() - 1).getTotalPhoneVerified());
        resp.put("dataRow", row);
        return new Response(true, "Thống kê dữ liệu người dùng", resp);
    }

    public Response findDataOrder(ReportSearch search) {
        HashMap<String, Object> resp = new HashMap<String, Object>();
        //build query
        Criteria cri = this.buildSearch(search);
        List<ReportOrder> reportOrders = reportOrderRepository.find(new Query(cri).with(new Sort(Sort.Direction.ASC, "time")));
        List<Object> data = new ArrayList<>();
        List<Object> row = new ArrayList<>();
        row.add("Ngày/Tháng");
        row.add("Chưa thanh toán");
        row.add("Đã thanh toán");
        row.add("Chờ thanh toán");
        data.add(row);
        for (ReportOrder reportOrder : reportOrders) {
            row = new ArrayList<>();
            row.add(this.converTime(reportOrder.getTime()));
            row.add(reportOrder.getNewStatus());
            row.add(reportOrder.getPaidStatus());
            row.add(reportOrder.getPadingStatus());
            data.add(row);
        }
        resp.put("chart", data);
        Map<String, Long> reportSumOrderFinal = reportOrderRepository.reportSumOrderFinal(cri);
        Map<String, Long> reportSumOrder = reportOrderRepository.reportSumOrder(cri);
        resp.put("dataFinal", reportSumOrderFinal);
        resp.put("dataTotal", reportSumOrder);
        return new Response(true, "Thống kê dữ liệu đơn hàng", resp);
    }

    public Response findDataLading(ReportSearch search) {
        HashMap<String, Object> resp = new HashMap<String, Object>();
        //build query
        Criteria cri = this.buildSearch(search);
        //find data
        List<ReportLading> report = reportLadingRepository.find(new Query(cri).with(new Sort(Sort.Direction.ASC, "time")));
        //create data report
        List<Object> data = new ArrayList<>();
        List<Object> row = new ArrayList<>();
        row.add("Ngày/Tháng");
        row.add("Hàng đã tới tay người mua");
        row.add("Chưa duyệt");
        row.add("Chưa lấy hàng");
        row.add("Đang giao hàng");
        row.add("Chuyển hoàn");
        row.add("Đã hủy");
        data.add(row);
        for (ReportLading reportLading : report) {
            row = new ArrayList<>();
            row.add(this.converTime(reportLading.getTime()));
            row.add(reportLading.getDelivered());
            row.add(reportLading.getNewlading());
            row.add(reportLading.getStocking());
            row.add(reportLading.getDelivering());
            row.add(reportLading.getReturnLading());
            row.add(reportLading.getDenied());
            data.add(row);
        }
        resp.put("chart", data);
        row = new ArrayList<>();
        row.add("Vận đơn vận chuyển: " + ladingRepository.count(new Query(new Criteria("type").ne(PaymentMethod.COD.toString()).and("createTime").gte(search.getStartTime()).lte(search.getEndTime()))));
        row.add("Vận đơn COD: " + ladingRepository.count(new Query(new Criteria("type").is(PaymentMethod.COD.toString()).and("createTime").gte(search.getStartTime()).lte(search.getEndTime()))));
        resp.put("dataSearch", row);

        row = new ArrayList<>();
        row.add("Tổng vận đơn: " + ladingRepository.count());
        row.add("Vận đơn vận chuyển: " + ladingRepository.count(new Query(new Criteria("type").ne(PaymentMethod.COD.toString()))));
        row.add("Vận đơn COD: " + ladingRepository.count(new Query(new Criteria("type").is(PaymentMethod.COD.toString()))));
        resp.put("dataNow", row);
        return new Response(true, "Thống kê dữ liệu vận đơn", resp);
    }

    public Response findDataCash(ReportSearch search) {

        HashMap<String, Object> resp = new HashMap<String, Object>();
        //build query
        Criteria cri = this.buildSearch(search);
        //find data
        List<ReportCash> report = reportCashRepository.find(new Query(cri).with(new Sort(Sort.Direction.ASC, "time")));
        //create data report
        List<Object> data = new ArrayList<>();
        List<Object> row = new ArrayList<>();
        row.add("Ngày/Tháng");
        row.add("Nạp xèng");
        row.add("Tiêu xèng");
        row.add("Kiếm xèng");
        row.add("Tiêu mua Uptin");
        row.add("Tiêu mua tin VIP");
        row.add("Kích hoạt chức năng đăng nhanh");
        row.add("Kích hoạt danh sách khách hàng");
        data.add(row);
        long topUp = 0;
        long useBalance = 0;
        long reward = 0;
        long upSchedule = 0;
        long vipItem = 0;
        long activeQuickBooking = 0;
        long activeCustomer = 0;
        for (ReportCash reportCash : report) {
            row = new ArrayList<>();
            row.add(this.converTime(reportCash.getTime()));
            row.add(reportCash.getTopup());
            row.add(reportCash.getUseBalance());
            row.add(reportCash.getReward());
            row.add(reportCash.getUpSchedule());
            row.add(reportCash.getVipItem());
            row.add(reportCash.getActiveQuickBooking());
            row.add(reportCash.getActiveCustomer());
            data.add(row);
            topUp += reportCash.getTopup();
            useBalance += reportCash.getUseBalance();
            reward += reportCash.getReward();
            upSchedule += reportCash.getUpSchedule();
            vipItem += reportCash.getVipItem();
            activeQuickBooking += reportCash.getActiveQuickBooking();
            activeCustomer += reportCash.getActiveCustomer();
        }
        resp.put("chart", data);
        row = new ArrayList<>();

        row.add("Nạp xèng: " + topUp);
        row.add("Tiêu xèng: " + useBalance);
        row.add("Kiếm xèng: " + reward);
        row.add("Tiêu mua Uptin: " + upSchedule);
        row.add("Tiêu mua tin VIP: " + vipItem);
        row.add("Kích hoạt chức năng đăng nhanh: " + activeQuickBooking);
        row.add("Kích hoạt danh sách khách hàng: " + activeCustomer);

        resp.put("dataSearch", row);
        List<ReportCash> report1 = reportCashRepository.find(new Query(new Criteria()));
        long topUpS = 0;
        long useBalanceS = 0;
        long rewardS = 0;
        long upScheduleS = 0;
        long vipItemS = 0;
        long activeQuickBookingS = 0;
        long activeCustomerS = 0;
        for (ReportCash reportCash : report1) {
            topUpS += reportCash.getTopup();
            useBalanceS += reportCash.getUseBalance();
            rewardS += reportCash.getReward();
            upScheduleS += reportCash.getUpSchedule();
            vipItemS += reportCash.getVipItem();
            activeQuickBookingS += reportCash.getActiveQuickBooking();
            activeCustomerS += reportCash.getActiveCustomer();
        }
        row = new ArrayList<>();
        row.add("Nạp xèng: " + topUpS);
        row.add("Tiêu xèng: " + useBalanceS);
        row.add("Kiếm xèng: " + rewardS);
        row.add("Tiêu mua Uptin: " + upScheduleS);
        row.add("Tiêu mua tin VIP: " + vipItemS);
        row.add("Kích hoạt chức năng đăng nhanh: " + activeQuickBookingS);
        row.add("Kích hoạt danh sách khách hàng: " + activeCustomerS);

        resp.put("dataNow", row);
        resp.put("timeNow", report1.get(0).getTime());
        return new Response(true, "Thống kê dữ liệu xèng", resp);
    }

    public Response findDataGMV(ReportSearch search) {
        HashMap<String, Object> resp = new HashMap<String, Object>();
        //build query
        Criteria cri = this.buildSearch(search);
        List<ReportOrder> reportOrders = reportOrderRepository.find(new Query(cri).with(new Sort(Sort.Direction.ASC, "time")));
        List<Object> data = new ArrayList<>();
        List<Object> row = new ArrayList<>();
        row.add("Ngày/Tháng");
        row.add("GMV đặt hàng");
        row.add("GMV đặt hàng NL");
        row.add("GMV đặt hàng Cod");
        row.add("GMV thanh toán thành công");
        data.add(row);
        for (ReportOrder reportOrder : reportOrders) {
            row = new ArrayList<>();
            row.add(this.converTime(reportOrder.getTime()));
            row.add(reportOrder.getQuantity());
            row.add(reportOrder.getNlPayment());
            row.add(reportOrder.getCodPayment());
            row.add(reportOrder.getPaidStatus());
            data.add(row);
        }
        resp.put("chart", data);
        Map<String, Long> reportSumGMV = reportOrderRepository.reportSumGMV(cri);
        Map<String, Long> reportSumGMVTime = reportOrderRepository.reportSumGMVTime(cri);
        resp.put("dataGMV", reportSumGMV);
        resp.put("dataGMVTime", reportSumGMVTime);
        return new Response(true, "Thống kê dữ liệu GMV", resp);
    }

}
