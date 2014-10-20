package vn.chodientu.controller.market;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Footerkeyword;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.input.FooterKeywordSearch;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.FooterKeywordService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.TagService;
import vn.chodientu.util.UrlUtils;

@Controller("tagController")
public class TagController extends BaseMarket {

    @Autowired
    private TagService tagService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private FooterKeywordService footerKeywordService;

    @RequestMapping({"/lich-su-tim-kiem"})
    public String searchHistory(ModelMap model) {
        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setPageIndex(0);
        itemSearch.setPageSize(7);
        itemSearch.setStatus(1);
        itemSearch.setListingType(ListingType.BUYNOW);
        DataPage<Item> search = itemService.search(itemSearch);
        for (Item it : search.getData()) {
            List<String> img = new ArrayList<>();
            if (it.getImages() != null && !it.getImages().isEmpty()) {
                for (String ig : it.getImages()) {
                    img.add(imageService.getUrl(ig).compress(100).getUrl());
                }
            }
            it.setImages(img);
        }
        model.put("clientScript", "searchhistory.init();");
        model.put("title", "Lịch sử tìm kiếm sản phẩm tại Chodientu.vn");
        model.put("description", "Bạn đang xem lịch sử tìm kiếm các sản phẩm tại Chodientu.vn - Giá rẻ, nhiều khuyến mại, thanh toán online, CoD, giao hàng toàn quốc, bảo vệ người mua.");
        model.put("itemRandom", search.getData());
        return "market.itemsearchhistory.searchhistory";
    }

    @RequestMapping({"/tim-kiem-pho-bien"})
    public String searchKeyCommon(ModelMap model,
            @RequestParam(value = "pageSize", defaultValue = "100") int pageSize,
            @RequestParam(value = "page", defaultValue = "0") int page, HttpServletRequest request,HttpServletResponse response) {
        FooterKeywordSearch keywordSearch = new FooterKeywordSearch();
        if (page > 0) {
            keywordSearch.setPageIndex(page - 1);
        } else {
            keywordSearch.setPageIndex(0);
        }
        if (pageSize > 0) {
            keywordSearch.setPageSize(pageSize);
        } else {
            keywordSearch.setPageSize(10000000);
        }
        keywordSearch.setCommon(1);
        DataPage<Footerkeyword> keywordCommonPage = footerKeywordService.search(keywordSearch);

        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setPageIndex(0);
        itemSearch.setPageSize(7);
        itemSearch.setStatus(1);
        itemSearch.setListingType(ListingType.BUYNOW);
        DataPage<Item> search = itemService.search(itemSearch);
        for (Item it : search.getData()) {
            List<String> img = new ArrayList<>();
            if (it.getImages() != null && !it.getImages().isEmpty()) {
                for (String ig : it.getImages()) {
                    img.add(imageService.getUrl(ig).compress(100).getUrl());
                }
            }
            it.setImages(img);
        }

        String q = request.getQueryString();
        if (q != null && !q.equals("")) {
            q = "?" + q;
        } else {
            q = "";
        }
        String uri = "/tim-kiem-pho-bien.html";
        if (!request.getRequestURI().equals(uri)) {
            String p = request.getQueryString();
            if (p != null && !p.equals("")) {
                p = "?" + p;
            } else {
                p = "";
            }
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            return "redirect:" + uri + p;
        }
        model.put("itemRandom", search.getData());
        model.put("pageSize", pageSize);
        model.put("canonical", "/tim-kiem-pho-bien.html" + q);
        model.put("title", "Tốp từ khóa tìm kiếm nhiều nhất" + (page > 1 ? ",trang " + page + "" : "."));
        model.put("description", "Tốp từ khóa tìm kiếm nhiều nhất tại Chợ Điện Tử - eBay Việt Nam - Giá rẻ, nhiều khuyến mại, thanh toán online, CoD, giao hàng toàn quốc, bảo vệ người mua."+ (page > 1 ? "Trang " + page + "" : ""));
        model.put("dataPage", keywordCommonPage);
        return "market.itemsearchhistory.common";
    }

    @RequestMapping({"/lich-su-tim-kiem-cua-ban"})
    public String usersearchHistory(ModelMap model) {
        if (viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/lich-su-tim-kiem-cua-ban.html";
        }

        model.put("clientScript", "tagsearchhistory.init();");
        return "market.itemsearchhistory.usersearchhistory";
    }

}
