package vn.chodientu.service;

import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.Administrator;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.SellerStock;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.ShopSupport;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.ShipmentType;
import vn.chodientu.entity.form.ShopAvatarImageForm;
import vn.chodientu.entity.form.ShopForm;
import vn.chodientu.entity.input.ShopSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.AdministratorRepository;
import vn.chodientu.repository.SellerRepository;
import vn.chodientu.repository.ShopRepository;
import vn.chodientu.repository.ShopSupportRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;

/**
 * @since Jun 6, 2014
 * @author Phu
 */
@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopSupportRepository supportRepository;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Validator validator;
    @Autowired
    private Viewer viewer;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SellerStockService sellerStockService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private CashService cashService;

    public Response migrate(Shop shop) {
        if (!shopRepository.exists(shop.getUserId())) {
            if (userRepository.exists(shop.getUserId())) {
                imageService.addTmp(shop.getLogo(), ImageType.SHOP_LOGO, shop.getUserId());
                shopRepository.save(shop);
                return new Response(true, "Thêm shop thành công");
            }
            return new Response(false, "User không tồn tại");
        }
        return new Response(false, "Shop đã tồn tại");
    }

    /**
     * Lấy shop theo alias
     *
     * @param alias
     * @return
     */
    public Shop getByAlias(String alias) {
        return shopRepository.getByAlias(alias);
    }

    /**
     * get by Url
     *
     * @param url
     * @return
     */
    public Shop getByUrl(String url) {
        return shopRepository.getByUrl(url);
    }

    /**
     * Lấy danh sách shop theo list id
     *
     * @param shopsId
     * @return
     */
    public List<Shop> getShops(List<String> shopsId) {
        return shopRepository.get(shopsId);
    }

    /**
     * Lấy shop theo id (userId)
     *
     * @param userId
     * @return
     */
    public Shop getShop(String userId) {
        return shopRepository.find(userId);
    }

    /**
     * Thêm mới hop, cấu hình thông tin cơ bản cho shop
     *
     * @param form
     * @return
     */
    public Response addShop(ShopForm form) {
        Map<String, String> error = validator.validate(form);
        if (form.getPhone() != null && !form.getPhone().equals("") && TextUtils.validatePhoneNumber(form.getPhone()) == false) {
            error.put("phone", "Số điện thoại phải là số dài 8-11 kí tự và bắt đầu bằng số 0");
        }
        if (form.getCityId() == null || form.getCityId().equals("0")) {
            error.put("cityId", "Bạn phải chọn thông tin địa phương!");
        }
        if (form.getDistrictId() == null || form.getDistrictId().equals("0")) {
            error.put("districtId", "Bạn phải chọn thông tin quận huyện!");
        }
        if (error.isEmpty()) {
            Shop shop = shopRepository.find(form.getUserId());
            if (shop == null) {
                shop = new Shop();
                shop.setCreateTime(System.currentTimeMillis());
            }
            shop.setUserId(form.getUserId());
            shop.setTitle(form.getTitle());
            shop.setCityId(form.getCityId());
            shop.setDistrictId(form.getDistrictId());
            shop.setAlias(form.getAlias());
            shop.setAddress(form.getAddress());
            shop.setEmail(form.getEmail());
            shop.setPhone(form.getPhone());
            shopRepository.save(shop);
            try {
                SellerStock stock = sellerStockService.getByType(shop.getUserId(), 2);
                if (stock == null) {
                    stock = new SellerStock();
                    stock.setType(2);
                    stock.setName(shop.getAddress());
                    stock.setAddress(shop.getAddress());
                }
                stock.setName(shop.getAddress());
                stock.setAddress(shop.getAddress());
                stock.setSellerId(shop.getUserId());
                stock.setCityId(shop.getCityId());
                stock.setDistrictId(shop.getDistrictId());
                stock.setSellerName(shop.getTitle());
                stock.setPhone(shop.getPhone());
                sellerStockService.add(stock);
            } catch (Exception e) {
            }
            return new Response(true, "Cập nhật thông tin shop thành công!", shop);
        }
        return new Response(false, "Thông tin không hợp lệ!", error);
    }

    /**
     * Upload logo của shop
     *
     * @param shopAvatarImageForm
     * @return
     * @throws java.lang.Exception
     */
    public Response uploadShopLogo(ShopAvatarImageForm shopAvatarImageForm) throws Exception {
        Map<String, String> puts = new HashMap<>();
        try {
            List<String> getImg = imageService.get(ImageType.SHOP_LOGO, shopAvatarImageForm.getShopId());
            if (getImg.size() > 0) {
                imageService.deleteById(ImageType.SHOP_LOGO, shopAvatarImageForm.getShopId(), getImg.get(0));
            }
            Response upload = imageService.upload(shopAvatarImageForm.getImage(), ImageType.SHOP_LOGO, shopAvatarImageForm.getShopId());
            if (upload.isSuccess()) {
                String data = (String) upload.getData();
                String urlImg = imageService.getUrl(data).getUrl();
                puts.put("image", urlImg);
            } else {
                return new Response(false, "File ảnh quá lớn hoặc không đúng định dạng.", upload);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new Response(true, "Upload ảnh thành công !", puts);
    }

    /**
     * Cắt logo shop
     *
     * @param shopAvatarImageForm
     * @return
     */
    public Response cropShopLogo(ShopAvatarImageForm shopAvatarImageForm) {
        int x1 = Math.round(shopAvatarImageForm.getX1()) - 1;
        int y1 = Math.round(shopAvatarImageForm.getY1()) - 1;
        int width = Math.round(shopAvatarImageForm.getWidth());
        int height = Math.round(shopAvatarImageForm.getHeight());
        // Get images
        List<String> images = imageService.get(ImageType.SHOP_LOGO, shopAvatarImageForm.getShopId());
        String crop = imageService.getUrl(images.get(0)).crop(x1, y1, width, height).getUrl();
        Response resp = imageService.download(crop, ImageType.SHOP_LOGO, shopAvatarImageForm.getShopId());
        if (resp.isSuccess()) {
            imageService.deleteByUrl(ImageType.SHOP_LOGO, shopAvatarImageForm.getShopId(), images.get(0));
        }
        return new Response(true, "Cắt hình ảnh thành công.");
    }

    /**
     * Lưu tọa độ địa chỉ shop khi người dùng thay đổi con trỏ trên bản đồ!
     *
     * @param id
     * @param lat
     * @param lng
     * @return
     */
    public Response changeLatAndLng(String id, double lat, double lng) {
        Shop shop = shopRepository.find(id);
        if (shop == null) {
            return new Response(false, "Không tìm thấy shop yêu cầu!");
        }
        shop.setLat(lat);
        shop.setLng(lng);
        shopRepository.save(shop);
        return new Response(true, "Lưu tọa độ địa chỉ shop thành công!", shop);
    }

    /**
     * Cập nhật thông tin shop bao gồm : thông tin giới thiệu, thông tin hướng
     * dẫn,và thông tin phần footer
     *
     * @param id
     * @param type
     * @param value
     * @return
     */
    public Response updateInfo(String id, String type, String value) {
        Shop shop = shopRepository.find(id);
        if (shop == null) {
            return new Response(false, "Bạn chưa có shop, vui lòng cập nhật thông tin shop trước!");
        }
        if (value == null || value.equals("")) {
            return new Response(false, "Bạn chưa nhập thông tin này!");
        }
        if (type != null || !type.equals("")) {
            if (type.equals("infoAbout")) {
                shop.setAbout(value);
            }
            if (type.equals("infoGuide")) {
                shop.setGuide(value);
            }
            if (type.equals("infoFooter")) {
                shop.setFooter(value);
            }
            shopRepository.save(shop);
            return new Response(true, "Cập nhật thành công thông tin shop!");
        } else {
            return new Response(false, "Cập nhật thông tin shop thất bại!");
        }
    }

    /**
     * Tạo mới shop cho user
     *
     * @param user
     * @return
     */
    public Shop createShop(User user) {
        Shop shop = shopRepository.find(user.getId());
        if (shop != null) {
            return shop;
        }
        shop = new Shop();
        shop.setUserId(user.getId());
        shop.setCreateTime(System.currentTimeMillis());
        String title = user.getUsername();
        if (title == null || title.equals("")) {
            title = user.getEmail().toLowerCase().split("@")[0];
        }
        String url = TextUtils.createAlias(title);
        if (shopRepository.exitsByAlias(url)) {
            url += new Date().getTime();
        }
        shop.setTitle(title);
        shop.setAlias(url);
        shop.setPhone(user.getPhone());
        shop.setCityId(user.getCityId());
        shop.setDistrictId(user.getDistrictId());
        shop.setAddress(user.getAddress());
        shop.setEmail(user.getEmail());
        shopRepository.save(shop);
        try {
            Seller seller = sellerService.getById(user.getId());
            seller.setShipmentType(ShipmentType.BYWEIGHT);
            sellerRepository.save(seller);
        } catch (Exception e) {
        }
        try {
            cashService.reward(CashTransactionType.OPEN_SHOP, user.getId(), shop.getUserId(), "/" + shop.getAlias() + "", null);
        } catch (Exception e) {
        }

        return shop;
    }

    /**
     * Update lượt truy cập
     *
     * @param alias
     */
    public void viewCount(String alias) {
        try {
            Shop shop = shopRepository.getByAlias(alias);
            shop.setViewCount(shop.getViewCount() + 1);
            shopRepository.save(shop);
        } catch (Exception e) {
        }
    }

    public DataPage<Shop> search(ShopSearch search) {
        DataPage<Shop> dataPage = new DataPage<>();
        Criteria cri = new Criteria();
        if (search.getAlias() != null && !search.getAlias().equals("")) {
            cri.and("alias").regex(search.getAlias());
        }
        if (search.getTitle() != null && !search.getTitle().equals("")) {
            cri.and("title").regex(search.getTitle());
        }
        if (search.getUrl() != null && !search.getUrl().trim().equals("")) {
            cri.and("url").is(search.getUrl());
        }
        if (search.getUserId() != null && !search.getUserId().trim().equals("")) {
            cri.and("userId").is(search.getUserId());
        }
        if (search.getPhone() != null && !search.getPhone().trim().equals("")) {
            cri.and("phone").is(search.getPhone());
        }
        if (search.getEmail() != null && !search.getEmail().trim().equals("")) {
            cri.and("email").is(search.getEmail());
        }
        if (search.getSupporter() != null && !search.getSupporter().trim().equals("")) {
            cri.and("supporter").is(search.getSupporter());
        }
        if (search.getCityId() != null && !search.getCityId().trim().equals("")) {
            cri.and("cityId").is(search.getCityId());
        }
        if (search.getDistrictId() != null && !search.getDistrictId().trim().equals("")) {
            cri.and("districtId").is(search.getDistrictId());
        }
        if (search.getCreateTimeTo() <= search.getCreateTimeFrom()) {
            if (search.getCreateTimeFrom() > 0) {
                cri.and("createTime").gte(search.getCreateTimeFrom());
            }
        } else if (search.getCreateTimeTo() > search.getCreateTimeFrom()) {
            cri.and("createTime").gte(search.getCreateTimeFrom()).lte(search.getCreateTimeTo());
        }

        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Query query = new Query(cri);
        dataPage.setDataCount(shopRepository.count(query));
        dataPage.setPageIndex(search.getPageIndex());
        dataPage.setPageSize(search.getPageSize());
        dataPage.setPageCount(dataPage.getDataCount() / search.getPageSize());
        if (dataPage.getDataCount() % search.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        dataPage.setData(shopRepository.find(query.limit(search.getPageSize()).skip(search.getPageIndex() * search.getPageSize()).with(sort)));
        return dataPage;
    }

    /**
     * Thêm supporter quản trị cho shop
     *
     * @param shopId
     * @param admin
     * @return
     * @throws Exception
     */
    public Shop addSupporter(String shopId, String admin) throws Exception {
        Shop shop = shopRepository.findByUser(shopId);
        if (shop == null) {
            throw new Exception("Không tìm thấy shop trên hệ thống");
        }
//        if (shop.getSupporter() != null && !shop.getSupporter().equals("")) {
//            throw new Exception("Shop này đã có " + shop.getSupporter() + " chăm sóc");
//        }
        Administrator administrator = administratorRepository.findByEmail(admin);
        if (administrator == null) {
            throw new Exception("Địa chỉ email " + admin + " không tồn tại trên hệ thống");
        }
        shop.setSupporter(admin);
        shopRepository.save(shop);
        return shop;
    }

    public Shop addDomain(String shopId, String domain) throws Exception {
        Shop shop = shopRepository.findByUser(shopId);
        if (shop == null) {
            throw new Exception("Không tìm thấy shop trên hệ thống");
        }
        shop.setUrl(domain);
        shopRepository.save(shop);
        return shop;
    }

    /**
     * Thêm ghi chú cho shop
     *
     * @param shopId mã shop
     * @param note ghi chú nhân viên chăm sóc
     * @param resp khách hàng trả lời, thông tin cập nhật của khách hàng
     * @return
     * @throws Exception
     */
    public Response addSupportNote(String shopId, String note, String resp) throws Exception {
        if (viewer.getAdministrator() == null) {
            throw new Exception("Bạn cần đăng nhập để thực hiện chức năng này");
        }
        Shop shop = shopRepository.findByUser(shopId);
        if (shop == null) {
            throw new Exception("Không tìm thấy shop trên hệ thống");
        }
        ShopSupport support = new ShopSupport();
        support.setCreateTime(System.currentTimeMillis());
        support.setIp(TextUtils.getClientIpAddr(request));
        support.setNote(note);
        support.setResp(resp);
        support.setShopId(shopId);
        support.setSupporter(viewer.getAdministrator().getEmail());
        supportRepository.save(support);
        if (!viewer.getAdministrator().getEmail().equals(viewer.getAdministrator().getEmail())) {
            return new Response(false, "Bạn không phải nhân viên chăm sóc cho shop này", support);
        }
        return new Response(true, "Thông tin chăm sóc đã được cập nhật", support);
    }

    public Shop addNote(String id, String note) throws Exception {
        if (viewer.getAdministrator() == null) {
            throw new Exception("Bạn chưa đăng nhập");
        }
        Shop shop = shopRepository.findByUser(id);
        if (shop == null) {
            throw new Exception("Không tồn tại giao dịch này");
        }
        if (!viewer.getAdministrator().getEmail().equals(shop.getSupporter())) {
            throw new Exception("Giao dịch đang được " + shop.getSupporter() + " chăm sóc");
        }
        shop.setNote(note);
        shopRepository.save(shop);
        return shop;
    }

}
