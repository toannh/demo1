package vn.chodientu.controller.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.data.CategoryMig;
import vn.chodientu.entity.data.ItemMig;
import vn.chodientu.entity.data.LocationMig;
import vn.chodientu.entity.data.ModelMig;
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.db.CategoryManufacturer;
import vn.chodientu.entity.db.ModelReviewLike;
import vn.chodientu.entity.db.District;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.db.ModelReview;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.ShopCategory;
import vn.chodientu.entity.db.ShopContact;
import vn.chodientu.entity.db.ShopNews;
import vn.chodientu.entity.db.ShopNewsCategory;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.ModelRepository;
import vn.chodientu.repository.SellerRepository;
import vn.chodientu.service.CashService;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.LocationService;
import vn.chodientu.service.ManufacturerService;
import vn.chodientu.service.ModelReviewLikeService;
import vn.chodientu.service.ModelReviewService;
import vn.chodientu.service.ModelService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopCategoryService;
import vn.chodientu.service.ShopContactService;
import vn.chodientu.service.ShopNewsCategoryService;
import vn.chodientu.service.ShopNewsService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserService;

/**
 * @since May 7, 2014
 * @author Phu
 */
@Controller("migrateService")
@RequestMapping("/migrate")
public class MigrateController extends BaseRest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopNewsService newsServiceService;
    @Autowired
    private ShopNewsCategoryService shopNewsCategoryService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private ShopContactService shopContactService;
    @Autowired
    private ModelReviewService modelReviewService;
    @Autowired
    private ModelReviewLikeService modelCommentLikeService;
    @Autowired
    private CashService cashService;

    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @ResponseBody
    @RequestMapping(value = "/updatecod", method = RequestMethod.POST)
    public Response updateCod() throws Exception {
        long total = sellerRepository.count();
        int totalPage = (int) total / 1000;
        if (total % 100 != 0) {
            totalPage++;
        }
        for (int i = 0; i <= totalPage; i++) {
            List<Seller> sellers = sellerRepository.find(new Query().with(new PageRequest(i, 100)));
            for (Seller seller : sellers) {
                sellerService.processUpdateCodPaymentItem(seller.getUserId(), seller.isNlIntegrated());
                sellerService.processUpdateOnlinePaymentItem(seller.getUserId(), seller.isScIntegrated());
            }
        }
        return new Response(true);
    }

    @ResponseBody
    @RequestMapping(value = "/manufacturer", method = RequestMethod.POST)
    public Response manufacturer(@RequestBody Manufacturer man) throws Exception {
        return manufacturerService.migrate(man);
    }

    @ResponseBody
    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public Response location(@RequestBody List<LocationMig> data) throws Exception {
        for (LocationMig lo : data) {
            locationService.saveCity(lo.getCity());
            for (District di : lo.getDistricts()) {
                di.setCityId(lo.getCity().getId());
                locationService.saveDistrict(di);
            }
        }
        return new Response(true);
    }

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Response user(@RequestBody List<User> data) throws Exception {
        userService.migrate(data);
        return new Response(true);
    }

    @ResponseBody
    @RequestMapping(value = "/seller", method = RequestMethod.POST)
    public Response serller(@RequestBody List<Seller> data) throws Exception {
        sellerService.migrate(data);
        return new Response(true);
    }

    @ResponseBody
    @RequestMapping(value = "/shop", method = RequestMethod.POST)
    public Response shop(@RequestBody Shop data) throws Exception {
        return shopService.migrate(data);
    }

    @ResponseBody
    @RequestMapping(value = "/shopnews", method = RequestMethod.POST)
    public Response shopnews(@RequestBody List<ShopNews> data) throws Exception {
        newsServiceService.migrate(data);
        return new Response(true, null, data.get(0).getUserId());
    }

    @ResponseBody
    @RequestMapping(value = "/shopnewscategory", method = RequestMethod.POST)
    public Response shopnewscategory(@RequestBody List<ShopNewsCategory> data) throws Exception {
        shopNewsCategoryService.migrate(data);
        return new Response(true);
    }

    @ResponseBody
    @RequestMapping(value = "/shopcategory", method = RequestMethod.POST)
    public Response shopcategory(@RequestBody List<ShopCategory> data) throws Exception {
        shopCategoryService.migrate(data);
        return new Response(true);
    }

    @ResponseBody
    @RequestMapping(value = "/shopcontact", method = RequestMethod.POST)
    public Response shopcontact(@RequestBody List<ShopContact> data) throws Exception {
        shopContactService.migrate(data);
        return new Response(true);
    }

    @ResponseBody
    @RequestMapping(value = "/modelreview", method = RequestMethod.POST)
    public Response modelReview(@RequestBody List<ModelReview> data) throws Exception {
        modelReviewService.migrate(data);
        return new Response(true);
    }

    @ResponseBody
    @RequestMapping(value = "/modelcommentlike", method = RequestMethod.POST)
    public Response modelCommentLike(@RequestBody List<ModelReviewLike> data) throws Exception {
        modelCommentLikeService.migrate(data);
        return new Response(true);
    }

    @ResponseBody
    @RequestMapping(value = "/cash", method = RequestMethod.POST)
    public Response cash(@RequestBody List<Cash> data) throws Exception {
        cashService.migrate(data);
        return new Response(true);
    }
}
