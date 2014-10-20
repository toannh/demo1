package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.input.SellerSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserService;

@Controller("cpSeller")
@RequestMapping("/cp/seller")
public class SellerController extends BaseCp {

    @Autowired
    private SellerService sellerService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private Gson gson;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
        SellerSearch sellerSearch = new SellerSearch();
        if (session.getAttribute("sellerSearch") != null && page != 0) {
            sellerSearch = (SellerSearch) session.getAttribute("sellerSearch");
        } else {
            session.setAttribute("sellerSearch", sellerSearch);
        }
        sellerSearch.setPageIndex(page - 1);
        sellerSearch.setPageSize(50);
        DataPage<Seller> dataPage = sellerService.search(sellerSearch);
        List<String> userId = new ArrayList<>();
        if (dataPage != null && !dataPage.getData().isEmpty()) {
            for (Seller sl : dataPage.getData()) {
                userId.add(sl.getUserId());
            }
        }

        map.put("users", userService.getUserByIds(userId));
        map.put("sellerSearch", sellerSearch);
        map.put("clientScript", "userIds=" + gson.toJson(userId) + "; seller.init();");
        map.put("dataPage", dataPage);
        return "cp.seller";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(ModelMap map, HttpSession session, @ModelAttribute SellerSearch sellerSearch) {
        session.setAttribute("sellerSearch", sellerSearch);
        sellerSearch.setPageIndex(0);
        sellerSearch.setPageSize(50);

        DataPage<Seller> dataPage = sellerService.search(sellerSearch);
        List<String> userId = new ArrayList<>();
        if (dataPage != null && !dataPage.getData().isEmpty()) {
            for (Seller sl : dataPage.getData()) {
                userId.add(sl.getUserId());
            }
        }

        map.put("users", userService.getUserByIds(userId));
        map.put("sellerSearch", sellerSearch);
        map.put("clientScript", "userIds=" + gson.toJson(userId) + "; seller.init();");
        map.put("dataPage", dataPage);
        return "cp.seller";
    }

    @RequestMapping(value = "/excelshop", method = RequestMethod.GET)
    public void excelbyseller(HttpServletResponse response,
            @RequestParam(value = "userId", defaultValue = "") String userId
    ) throws Exception {
        SellerSearch search = new SellerSearch();

        Workbook wb = new HSSFWorkbook();
//        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        org.apache.poi.ss.usermodel.Row row = sheet.createRow((short) 0);
        row = sheet.createRow((short) 1);

        row.createCell(0).setCellValue("Mã Người Bán");
        row.createCell(1).setCellValue("Email");
        row.createCell(2).setCellValue("Phone");
        int i = 1;
        search.setPageIndex(0);
        search.setPageSize(100000000);
        search.setNlIntegrated(1);
        search.setScIntegrated(1);
        DataPage<Seller> dataPage = sellerService.search(search);
        List<String> userIds = new ArrayList();
        for (Seller seller : dataPage.getData()) {
            if (!userIds.contains(seller.getUserId())) {
                userIds.add(seller.getUserId());
            }
        }
        List<Shop> shops = shopService.getShops(userIds);
        for (Seller seller : dataPage.getData()) {
            for (Shop shop : shops) {
                if (shop.getUserId().equals(seller.getUserId())) {
                    i++;
                    row = sheet.createRow((short) i);
                    row.createCell(0).setCellValue(String.valueOf(shop.getUserId()));
                    row.createCell(1).setCellValue(String.valueOf(shop.getEmail()));
                    row.createCell(2).setCellValue(String.valueOf(shop.getPhone()));
                    break;
                }
            }
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-seller.xls");
        wb.write(response.getOutputStream());
    }

}
