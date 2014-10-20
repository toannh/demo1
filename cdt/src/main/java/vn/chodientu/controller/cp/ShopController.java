package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
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
import vn.chodientu.entity.db.AdministratorRole;
import vn.chodientu.entity.db.CashHistory;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.ShopCategory;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.input.CashHistorySearch;
import vn.chodientu.entity.input.ShopSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.AdministratorService;
import vn.chodientu.service.ShopCategoryService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserService;

@Controller("cpShop")
@RequestMapping("/cp/shop")
public class ShopController extends BaseCp {

    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private Gson gson;
    @Autowired
    private ShopCategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
        ShopSearch shopSearch = new ShopSearch();
        if (session.getAttribute("shopSearch") != null && page != 0) {
            shopSearch = (ShopSearch) session.getAttribute("shopSearch");
        } else {
            session.setAttribute("shopSearch", shopSearch);
        }
        shopSearch.setPageIndex(page - 1);
        shopSearch.setPageSize(50);
        DataPage<Shop> dataPage = shopService.search(shopSearch);
        List<String> userId = new ArrayList<>();
        if (dataPage != null && !dataPage.getData().isEmpty()) {
            for (Shop sh : dataPage.getData()) {
                userId.add(sh.getUserId());
            }
        }

        map.put("users", userService.getUserByIds(userId));
        map.put("shopSearch", shopSearch);
        List<AdministratorRole> roles = null;
        try {
            roles = administratorService.getRoles(viewer.getAdministrator().getId());
        } catch (Exception e) {
        }
        map.put("clientScript", "shop.init(" + gson.toJson(roles) + "); var cityId='" + shopSearch.getCityId() + "'; var districtId='" + shopSearch.getDistrictId() + "'; ");
        map.put("dataPage", dataPage);
        return "cp.shop";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(ModelMap map, HttpSession session, @ModelAttribute ShopSearch shopSearch) throws Exception {
        session.setAttribute("shopSearch", shopSearch);
        shopSearch.setPageIndex(0);
        shopSearch.setPageSize(50);
        DataPage<Shop> dataPage = shopService.search(shopSearch);
        List<String> userId = new ArrayList<>();
        if (dataPage != null && !dataPage.getData().isEmpty()) {
            for (Shop sh : dataPage.getData()) {
                userId.add(sh.getUserId());
            }
        }

        map.put("users", userService.getUserByIds(userId));
        map.put("shopSearch", shopSearch);
        List<AdministratorRole> roles = null;
        try {
            roles = administratorService.getRoles(viewer.getAdministrator().getId());
        } catch (Exception e) {
        }
        map.put("clientScript", "shop.init(" + gson.toJson(roles) + "); var cityId='" + shopSearch.getCityId() + "'; var districtId='" + shopSearch.getDistrictId() + "'; ");
        map.put("dataPage", dataPage);
        return "cp.shop";
    }

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public void excelShop(HttpServletResponse response,
            @RequestParam(value = "alias", defaultValue = "") String alias
    ) throws Exception {

        Shop byAlias = shopService.getByAlias(alias);

        Workbook wb = new HSSFWorkbook();
//        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");
        org.apache.poi.ss.usermodel.Row row = sheet.createRow((short) 0);
        row = sheet.createRow((short) 1);

        row.createCell(0).setCellValue("Mã danh mục");
        row.createCell(1).setCellValue("Tên danh mục");
        row.createCell(2).setCellValue("Trạng thái");

        int i = 1;
        List<ShopCategory> byShop = categoryService.getByShop(byAlias.getUserId());
        for (ShopCategory shopCategory : byShop) {
            if (shopCategory.getParentId() == null) {
                i++;
                row = sheet.createRow((short) i);
                row.createCell(0).setCellValue(String.valueOf(shopCategory.getId()));
                row.createCell(1).setCellValue(String.valueOf(shopCategory.getName()));
                if (shopCategory.isActive()) {
                    row.createCell(2).setCellValue(String.valueOf("Hiển thị"));
                } else {
                    row.createCell(2).setCellValue(String.valueOf("Ẩn"));
                }

                for (ShopCategory shopCategorylv1 : byShop) {
                    if (shopCategorylv1.getParentId() != null && !shopCategorylv1.getParentId().equals("")) {
                        if (shopCategorylv1.getParentId().equals(shopCategory.getId())) {
                            i++;
                            row = sheet.createRow((short) i);
                            row.createCell(0).setCellValue(String.valueOf(shopCategorylv1.getId()));
                            row.createCell(1).setCellValue(String.valueOf("  " + shopCategorylv1.getName() + " Cấp I"));
                            if (shopCategorylv1.isActive()) {
                                row.createCell(2).setCellValue(String.valueOf("Hiển thị"));
                            } else {
                                row.createCell(2).setCellValue(String.valueOf("Ẩn"));
                            }
                            for (ShopCategory shopCategorylv2 : byShop) {
                                if (shopCategorylv2.getParentId() != null && !shopCategorylv2.getParentId().equals("")) {
                                    if (shopCategorylv2.getParentId().equals(shopCategorylv1.getId())) {
                                        i++;
                                        row = sheet.createRow((short) i);
                                        row.createCell(0).setCellValue(String.valueOf(shopCategorylv2.getId()));
                                        row.createCell(1).setCellValue(String.valueOf("    " + shopCategorylv2.getName() + " Cấp II"));
                                        if (shopCategorylv2.isActive()) {
                                            row.createCell(2).setCellValue(String.valueOf("Hiển thị"));
                                        } else {
                                            row.createCell(2).setCellValue(String.valueOf("Ẩn"));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-shop" + alias + ".xls");
        wb.write(response.getOutputStream());
    }
}
