package vn.chodientu.controller.user;

import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.SellerCustomer;
import vn.chodientu.entity.input.SellerCustomerSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.CashService;
import vn.chodientu.service.SellerCustomerService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopNewsCategoryService;

/**
 * @since May 20, 2014
 * @author Phu
 */
@Controller
@RequestMapping("/user")
public class SellerCustomerController extends BaseUser {

    @Autowired
    private SellerService sellerService;
    @Autowired
    ShopNewsCategoryService shopNewsCategoryService;
    @Autowired
    private CashService cashService;
    @Autowired
    private SellerCustomerService sellerCustomerService;

    /**
     *
     *
     * @param map
     * @param name
     * @param email
     * @param phone
     * @param page
     * @param cname
     * @param marketing
     * @param option
     * @return
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/custormers.html", method = RequestMethod.GET)
    public String search(ModelMap map, @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "name", defaultValue = "", required = false) String name,
            @RequestParam(value = "email", defaultValue = "", required = false) String email,
            @RequestParam(value = "phone", defaultValue = "", required = false) String phone,
            @RequestParam(value = "cname", defaultValue = "", required = false) String cname,
            @RequestParam(value = "marketing", defaultValue = "", required = false) String marketing,
            @RequestParam(value = "option", defaultValue = "0", required = false) int option
    ) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/custormers.html";
        }
        SellerCustomerSearch customerSearch = new SellerCustomerSearch();
        if (!name.equals("")) {
            customerSearch.setName(name);
        }
        if (!email.equals("")) {
            customerSearch.setEmail(email);
        }
        if (!phone.equals("")) {
            customerSearch.setPhone(phone);
        }
        if (!cname.equals("")) {
            customerSearch.setCname(cname);
        }
        if (option > 0) {
            customerSearch.setOption(option);
        }
        customerSearch.setPageIndex(page - 1);
        customerSearch.setPageSize(10);
        Cash cash = cashService.getCash(viewer.getUser().getId());
        Seller seller = sellerService.getById(viewer.getUser().getId());
        DataPage<SellerCustomer> sellerCustomerPage = sellerCustomerService.search(customerSearch, viewer.getUser());
        String val = null;
        if (!marketing.trim().equals("")) {
            val = marketing;
        }
        map.put("customerSearch", customerSearch);
        map.put("sellerCustomer", sellerCustomerPage);
        map.put("isMarketing", seller.isMarketing());
        map.put("numberCash", cash.getBalance());
        map.put("name", name);
        map.put("email", email);
        map.put("phone", phone);
        map.put("option", option);

        map.put("clientScript", " var marketing='" + val + "'; sellercustomer.init();");

        return "user.custormers";
    }

    @RequestMapping(value = "/exceltemplate", method = RequestMethod.GET)
    public void excelbyseller(HttpServletResponse response) throws Exception {

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow((short) 0);

        row.createCell(0).setCellValue("Tên");
        row.createCell(1).setCellValue("Email");
        row.createCell(2).setCellValue("Số điện thoại");
        row.createCell(3).setCellValue("Địa chỉ");

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=ExcelTemplate.xls");
        wb.write(response.getOutputStream());
    }
}
