package vn.chodientu.controller.service;

import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.component.NlClient;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.data.SellerPolicy;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.SellerStock;
import vn.chodientu.entity.db.SellerSupportFee;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.FeeType;
import vn.chodientu.entity.enu.ShipmentType;
import vn.chodientu.entity.input.SellerStockSearch;
import vn.chodientu.entity.input.SellerSupportFeeSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.SearchIndexService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.SellerStockService;
import vn.chodientu.service.SellerSupportFeeService;
import vn.chodientu.service.ShopService;

@Controller("servicePaymentIntegrate")
@RequestMapping("/payment")
public class PaymentIntegrateController extends BaseRest {

    @Autowired
    private SellerService sellerService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private SellerSupportFeeService sellerSupportFeeService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private SellerStockService sellerStockService;
    @Autowired
    private SearchIndexService searchIndexService;
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserController.class);

    @RequestMapping(value = "/nlIntegrate", method = RequestMethod.GET)
    @ResponseBody
    public Response nlIntegrate(
            @RequestParam(value = "type", defaultValue = "nl", required = false) String type
    ) {
        User user = viewer.getUser();
        if (user == null) {
            return new Response(false, "Not allow");
        }
        try {
            String return_url = baseUrl + "/user/cau-hinh-tich-hop.html?itype=" + type;
            String urlNganluong = buildNganluongLinkNew("", user.getId(), "1", return_url);
            return new Response(true, "ok", urlNganluong);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(false, "Not Allow");
    }

    /**
     * service integrate nl
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "/nlUnlinked", method = RequestMethod.GET)
    @ResponseBody
    public Response nlUnlinked(
            @RequestParam(value = "type", defaultValue = "nl", required = false) String type) {
        User user = viewer.getUser();
        if (user == null) {
            return new Response(false, "Not allow");
        }
        try {
            if ("nl".equals(type)) {
                //nl url integrate
                Seller sell = sellerService.getById(user.getId());
                sell.setNlEmail(null);
                sell.setNlId(null);
                sell.setNlIntegrated(false);
                sellerService.update(sell);
                //update false online payment                
                long total = itemService.countBySeller(user.getId());
                int totalPage = (int) total / 100;
                if (total % 100 != 0) {
                    totalPage++;
                }
                logger.info("Nganluong unintegrating... update " + total + " items");
                for (int i = 0; i < totalPage; i++) {
                    processUpdateItem(i, user.getId(), true);
                }
            } else {
                //nl url integrate
                Seller sell = sellerService.getById(user.getId());
                sell.setScIntegrated(false);
                sell.setScEmail(null);
                sell.setScId(null);
                sellerService.update(sell);
                //update false online payment  
                long total = itemService.countBySeller(user.getId());
                int totalPage = (int) total / 100;
                if (total % 100 != 0) {
                    totalPage++;
                }
                logger.info("Nganluong unintegrating... update " + total + " items");
                for (int i = 0; i < totalPage; i++) {
                    processUpdateItem(i, user.getId(), false);
                }
            }

            return new Response(true, "ok", "success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(false, "Not Allow");
    }

    @Async
    private void processUpdateItem(int i, String userId, boolean onlinePayment) throws Exception {
        List<Item> items = itemService.getBySeller(new PageRequest(i, 100), userId);
        for (Item item : items) {
            item.setOnlinePayment(onlinePayment);
            itemService.edit(item, null);
        }
        searchIndexService.processIndexPageItem(items);
        logger.info("page " + i + "\t" + items.size() + "\tupdated");
    }

    @RequestMapping(value = "/addshippingfee", method = RequestMethod.POST)
    @ResponseBody
    public Response addShippingFee(@RequestBody Seller seller,
            @RequestParam(value = "update", defaultValue = "true") boolean update) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(true, "Bạn phải đăng nhập trước!");
        }
        seller.setUserId(viewer.getUser().getId());
        if (update) {
            processUpdateItem(seller.getUserId(), seller.getShipmentType(), seller.getShipmentPrice());
        }
        return sellerService.addShippingFee(seller);
    }

    @Async
    private void processUpdateItem(String userId, ShipmentType shipmentType, int shipmentPrice) throws Exception {
        List<Item> items = itemService.getBSeller(userId);
        for (Item item : items) {
            item.setShipmentType(shipmentType);
            item.setShipmentPrice(shipmentPrice);
            itemService.edit(item, null);
        }
        searchIndexService.processIndexPageItem(items);
    }

    public String buildNganluongLinkNew(String email, String userId, String type, String returnUrl) {
        String url = "https://www.nganluong.vn/?portal=nganluong&page=merchant_login";
        String merchant_id = "15643";
        String merchant_password = "df19aa6d60";
        try {
            returnUrl = URLEncoder.encode(returnUrl, "UTF-8");
        } catch (Exception ex) {
            Logger.getLogger(NlClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        String checksum = DigestUtils.md5Hex(merchant_id + merchant_password + email + userId + type + returnUrl);
        return url + "&merchant_id=" + merchant_id + "&email=" + email + "&cdt_id=" + userId + "&type=" + type + "&return_url=" + returnUrl + "&checksum=" + checksum;
    }

    @RequestMapping(value = "/savesellerpolicy", method = RequestMethod.POST)
    @ResponseBody
    public Response saveSellerPolicy(@RequestBody List<SellerPolicy> sellerpolicy) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        return sellerService.saveSellerPolicy(viewer.getUser().getId(), sellerpolicy);
    }

    @RequestMapping(value = "/loadstock", method = RequestMethod.GET)
    @ResponseBody
    public Response loadStock() {
        SellerStockSearch search = new SellerStockSearch();
        search.setPageIndex(0);
        search.setPageSize(100);
        search.setSellerId(viewer.getUser().getId());
        DataPage<SellerStock> pageStock = sellerStockService.search(search);
        return new Response(true, "OK", pageStock.getData());
    }

    @RequestMapping(value = "/addstock", method = RequestMethod.POST)
    @ResponseBody
    public Response addStock(@RequestBody SellerStock stock) {
        stock.setSellerId(viewer.getUser().getId());
        stock.setPhone(stock.getPhone().substring(1));
        try {
            return sellerStockService.add(stock);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/delstock", method = RequestMethod.GET)
    @ResponseBody
    public Response delStock(@RequestParam(value = "id", defaultValue = "") String id) {
        try {
            return sellerStockService.remove(id);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/hidestock", method = RequestMethod.GET)
    @ResponseBody
    public Response hideStock(@RequestParam(value = "id", defaultValue = "") String id) {
        try {
            SellerStock stock = sellerStockService.active(id);
            return new Response(true, "Thành công!", stock);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/getstock", method = RequestMethod.GET)
    @ResponseBody
    public Response getStock(@RequestParam(value = "id", defaultValue = "") String id) {
        try {
            SellerStock stock = sellerStockService.get(id);
            return new Response(true, "Thông tin kho", stock);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/editstock", method = RequestMethod.POST)
    @ResponseBody
    public Response editStock(@RequestBody SellerStock stock) {
        stock.setSellerId(viewer.getUser().getId());
        stock.setPhone(stock.getPhone().substring(1));
        try {
            return sellerStockService.edit(stock);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/addsupportfee", method = RequestMethod.POST)
    @ResponseBody
    public Response addsupportfee(@RequestBody SellerSupportFee sellerSupportFee, @RequestParam String typeFee) {
        if (typeFee.equals("ONLINEPAYMENT")) {
            sellerSupportFee.setType(FeeType.ONLINEPAYMENT);
        } else {
            sellerSupportFee.setType(FeeType.COD);
        }
        return sellerSupportFeeService.add(sellerSupportFee);
    }

    @RequestMapping(value = "/listsupportfee", method = RequestMethod.GET)
    @ResponseBody
    public Response listsupportfee(@RequestParam String feeType) {
        SellerSupportFeeSearch feeSearch = new SellerSupportFeeSearch();
        feeSearch.setActive(1);
        feeSearch.setSellerId(viewer.getUser().getId());
        feeSearch.setPageIndex(0);
        feeSearch.setPageSize(30);
        if (feeType.equals("ONLINEPAYMENT")) {
            feeSearch.setType(FeeType.ONLINEPAYMENT);
        } else {
            feeSearch.setType(FeeType.COD);
        }
        DataPage<SellerSupportFee> dataPage = sellerSupportFeeService.search(feeSearch);
        return new Response(true, null, dataPage);
    }

    @RequestMapping(value = "/delsupportfee", method = RequestMethod.GET)
    @ResponseBody
    public Response delsupportfee(@RequestParam String id) {
        try {
            return sellerSupportFeeService.del(id);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

}
