package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.input.CashTransactionSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.CashService;
import vn.chodientu.service.UserService;
import vn.chodientu.util.TextUtils;

@Controller("cpCashTransaction")
@RequestMapping("/cp/cashtransaction")
public class CashtransactionController extends BaseCp {

    @Autowired
    private CashService cashService;
    @Autowired
    private UserService userService;
    @Autowired
    private Gson gson;

    /**
     * List danh sách giao dịch xèng
     *
     * @param map
     * @param session
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map,
            HttpSession session,
            @RequestParam(value = "page", defaultValue = "0") int page) throws Exception {
        CashTransactionSearch cashTransactionSearch = new CashTransactionSearch();
        if (session.getAttribute("cpCashTransactionSearch") != null && page != 0) {
            cashTransactionSearch = (CashTransactionSearch) session.getAttribute("cpCashTransactionSearch");
        } else {
            session.setAttribute("cpCashTransactionSearch", cashTransactionSearch);
        }
        if (page > 0) {
            cashTransactionSearch.setPageIndex(page - 1);
        } else {
            cashTransactionSearch.setPageIndex(0);
        }
        cashTransactionSearch.setPageSize(10);
        DataPage<CashTransaction> dataPage = cashService.search(cashTransactionSearch);
        List<String> userId = new ArrayList<>();
        for (CashTransaction cashtr : dataPage.getData()) {
            if (!userId.contains(cashtr.getUserId())) {
                userId.add(cashtr.getUserId());
            }
        }
        List<User> userByIds = userService.getUserByIds(userId);

        map.put("cashSearchs", dataPage);
        map.put("sumCash", cashService.sumCashTransation(cashTransactionSearch));
        map.put("users", userByIds);
        map.put("clientScript", "cash.init();");
        map.put("cpCashTransactionSearch", cashTransactionSearch);
        return "cp.cashtransaction";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String list(ModelMap map,
            HttpSession session, @ModelAttribute CashTransactionSearch cashTransactionSearch) throws Exception {

        session.setAttribute("cpCashTransactionSearch", cashTransactionSearch);
        cashTransactionSearch.setPageIndex(0);
        cashTransactionSearch.setPageSize(10);

        DataPage<CashTransaction> dataPage = cashService.search(cashTransactionSearch);
        List<String> userId = new ArrayList<>();
        for (CashTransaction cashtr : dataPage.getData()) {
            userId.add(cashtr.getUserId());
        }
        List<User> userByIds = userService.getUserByIds(userId);
        map.put("cashSearchs", dataPage);
        map.put("users", userByIds);
        map.put("sumCash", cashService.sumCashTransation(cashTransactionSearch));
        map.put("clientScript", "cash.init();");
        map.put("cpCashTransactionSearch", cashTransactionSearch);
        return "cp.cashtransaction";
    }

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public void excel(HttpServletResponse response,
            @RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "userId", defaultValue = "") String userId,
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "status", defaultValue = "0") int status,
            @RequestParam(value = "type") CashTransactionType type,
            @RequestParam(value = "transactionStatus", defaultValue = "0") int transactionStatus,
            @RequestParam(value = "startTime", defaultValue = "0") long startTime,
            @RequestParam(value = "endTime", defaultValue = "0") long endTime
    ) throws Exception {
        CashTransactionSearch search = new CashTransactionSearch();
        if (id != null && !id.equals("")) {
            search.setId(id);
        }
        if (userId != null && !userId.equals("")) {
            search.setUserId(userId);
        }
        if (email != null && !email.equals("")) {
            search.setEmail(email);
        }
        if (startTime > 0) {
            search.setStartTime(startTime);
        }
        if (endTime > 0) {
            search.setEndTime(endTime);
        }
        if (status > 0) {
            search.setStatus(status);
        }
        if (transactionStatus > 0) {
            search.setTransactionStatus(transactionStatus);
        } else {
            if (type != null) {
                search.setType(type);
            }
        }
        Workbook wb = new HSSFWorkbook();
//        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        org.apache.poi.ss.usermodel.Row row = sheet.createRow((short) 0);
        row = sheet.createRow((short) 1);

        row.createCell(0).setCellValue("Mã");
        row.createCell(1).setCellValue("Ngày giao dịch");
        row.createCell(2).setCellValue("Hoạt động");
        row.createCell(3).setCellValue("Thanh toán");
        row.createCell(4).setCellValue("Số xèng");

        int i = 1;
        search.setPageIndex(0);
        search.setPageSize(1000000);
        DataPage<CashTransaction> user = cashService.search(search);
        for (CashTransaction print : user.getData()) {
            Long create = print.getTime();
            Date cre = new Date(create);
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
            String Jointime = dt.format(cre);
            i++;
            row = sheet.createRow((short) i);
            row.createCell(0).setCellValue(String.valueOf(print.getId()));
            row.createCell(1).setCellValue(String.valueOf(Jointime));
            if (print.getType() != CashTransactionType.TOPUP_NL && print.getType() != CashTransactionType.TOPUP_SMS) {
                row.createCell(2).setCellValue("Sử dụng xèng");
            }
            if (print.getType() == CashTransactionType.TOPUP_NL || print.getType() == CashTransactionType.TOPUP_SMS) {
                row.createCell(2).setCellValue("Nạp xèng");
            }
            if (print.getType() == CashTransactionType.TOPUP_NL) {
                row.createCell(3).setCellValue("Nạp ngân lượng");
            } else if (print.getType() == CashTransactionType.TOPUP_SMS) {
                row.createCell(3).setCellValue("Nạp SMS");
            } else if (print.getType() == CashTransactionType.SPENT_UPITEM) {
                row.createCell(3).setCellValue("Mua lượt up tin");
            } else if (print.getType() == CashTransactionType.SPENT_VIPITEM) {
                row.createCell(3).setCellValue("Mua tin vip");
            } else if (print.getType() == CashTransactionType.SPENT_EMAIL) {
                row.createCell(3).setCellValue("Mua gửi Email");
            } else if (print.getType() == CashTransactionType.SPENT_SMS) {
                row.createCell(3).setCellValue("Mua gửi SMS");
            } else if (print.getType() == CashTransactionType.COMMENT_ITEM_REWARD) {
                row.createCell(3).setCellValue("Comment đánh giá sản phẩm");
            } else if (print.getType() == CashTransactionType.COMMENT_MODEL_REWARD) {
                row.createCell(3).setCellValue("Comment đánh giá model");
            } else if (print.getType() == CashTransactionType.ACTIVE_QUICK_SUBMIT) {
                row.createCell(3).setCellValue("Kích hoạt chức năng đăng nhanh");
            } else if (print.getType() == CashTransactionType.ACTIVE_MARKETING) {
                row.createCell(3).setCellValue("Mở chức năng danh sách người bán");
            } else if (print.getType() == CashTransactionType.CLOSE_ADV) {
                row.createCell(3).setCellValue("Tắt box quảng cáo trang chi tiết sản phẩm");
            }
            row.createCell(4).setCellValue(String.valueOf(print.getAmount() * print.getSpentQuantity()));
        }
        long totalXeng = cashService.sumCashTransation(search);

        String s = Long.toString(totalXeng);
        row = sheet.createRow((short) i + 1);
        row.createCell(3).setCellValue("Tổng xèng: ");
        row.createCell(4).setCellValue(TextUtils.numberFormat(Double.parseDouble(s)));
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=Danh-sach-giao-dich-xeng.xls");
        wb.write(response.getOutputStream());
    }

    @RequestMapping(value = "/excelbyseller", method = RequestMethod.GET)
    public void excelbyseller(HttpServletResponse response,
            @RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "userId", defaultValue = "") String userId,
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "status", defaultValue = "0") int status,
            @RequestParam(value = "type") CashTransactionType type,
            @RequestParam(value = "transactionStatus", defaultValue = "0") int transactionStatus,
            @RequestParam(value = "startTime", defaultValue = "0") long startTime,
            @RequestParam(value = "endTime", defaultValue = "0") long endTime
    ) throws Exception {
        CashTransactionSearch search = new CashTransactionSearch();
        if (id != null && !id.equals("")) {
            search.setId(id);
        }
        if (userId != null && !userId.equals("")) {
            search.setUserId(userId);
        }
        if (email != null && !email.equals("")) {
            search.setEmail(email);
        }
        if (startTime > 0) {
            search.setStartTime(startTime);
        }
        if (endTime > 0) {
            search.setEndTime(endTime);
        }
        if (status > 0) {
            search.setStatus(status);
        }
        if (transactionStatus > 0) {
            search.setTransactionStatus(transactionStatus);
        } else {
            if (type != null) {
                search.setType(type);
            }
        }
        Workbook wb = new HSSFWorkbook();
//        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        org.apache.poi.ss.usermodel.Row row = sheet.createRow((short) 0);
        row = sheet.createRow((short) 1);

        row.createCell(0).setCellValue("Mã Người Bán");
        row.createCell(1).setCellValue("Email");
        row.createCell(2).setCellValue("Số xèng");
        int i = 1;
        search.setPageIndex(0);
        search.setPageSize(1000000);
        List<String> userIds = new ArrayList<>();
        Map<String, Long> mapXeng = cashService.getSellerSumBlance(search);
        for (Map.Entry<String, Long> entry : mapXeng.entrySet()) {
            userIds.add(entry.getKey());
        }
        List<User> users = userService.getUserByIds(userIds);
        long totalXeng = 0;
        for (Map.Entry<String, Long> entry : mapXeng.entrySet()) {
            totalXeng = totalXeng + entry.getValue();
            for (User user : users) {
                if (user.getId().equals(entry.getKey())) {
                    i++;
                    row = sheet.createRow((short) i);
                    row.createCell(0).setCellValue(String.valueOf(entry.getKey()));
                    row.createCell(1).setCellValue(String.valueOf(user.getEmail()));
                    row.createCell(2).setCellValue(String.valueOf(entry.getValue()));
                    break;
                }

            }

        }
        String s = Long.toString(totalXeng);
        row = sheet.createRow((short) i + 1);
        row.createCell(1).setCellValue("Tổng xèng: ");
        row.createCell(2).setCellValue(TextUtils.numberFormat(Double.parseDouble(s)));
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=Danh-sach-giao-dich-xeng.xls");
        wb.write(response.getOutputStream());
    }

}
