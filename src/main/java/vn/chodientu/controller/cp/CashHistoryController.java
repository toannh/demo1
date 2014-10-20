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
import vn.chodientu.entity.db.CashHistory;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.input.CashHistorySearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.CashHistoryService;
import vn.chodientu.service.UserService;

@Controller("cpHistory")
@RequestMapping("/cp/cashhistory")
public class CashHistoryController extends BaseCp {

    @Autowired
    private CashHistoryService cashHistoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private Gson gson;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int page) {
        CashHistorySearch cashHistorySearch = new CashHistorySearch();
        if (session.getAttribute("cashHistorySearch") != null && page != 0) {
            cashHistorySearch = (CashHistorySearch) session.getAttribute("cashHistorySearch");
        } else {
            session.setAttribute("cashHistorySearch", cashHistorySearch);
        }

        cashHistorySearch.setPageIndex(page - 1);
        cashHistorySearch.setPageSize(100);

        DataPage<CashHistory> dataPage = cashHistoryService.search(cashHistorySearch);
        List<String> listID = new ArrayList<>();

        for (CashHistory hts : dataPage.getData()) {
            if (!listID.contains(hts.getUserId())) {
                listID.add(hts.getUserId());
            }
        }

        model.put("dataPage", dataPage);
        model.put("clientScript", "var userIds = " + gson.toJson(listID) + ";" + " cashhistory.init();");
        model.put("cashHistorySearch", cashHistorySearch);

        return "cp.cashhistory";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(ModelMap model, HttpSession session, @ModelAttribute CashHistorySearch cashHistorySearch) {
        session.setAttribute("cashHistorySearch", cashHistorySearch);
        cashHistorySearch.setPageIndex(0);
        cashHistorySearch.setPageSize(100);

        DataPage<CashHistory> dataPage = cashHistoryService.search(cashHistorySearch);

        List<String> listID = new ArrayList<String>();
        for (CashHistory hts : dataPage.getData()) {
            if (!listID.contains(hts.getUserId())) {
                listID.add(hts.getUserId());
            }
        }

        model.put("dataPage", dataPage);
        model.put("clientScript", "var userIds = " + gson.toJson(listID) + ";" + " cashhistory.init();");
        model.put("cashHistorySearch", cashHistorySearch);
        return "cp.cashhistory";
    }

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public void excelcashHistory(HttpServletResponse response,
            @RequestParam(value = "type", defaultValue = "") CashTransactionType type,
            @RequestParam(value = "objectId", defaultValue = "") String objectId,
            @RequestParam(value = "admin", defaultValue = "") String admin,
            @RequestParam(value = "startTime", defaultValue = "0") long startTime,
            @RequestParam(value = "endTime", defaultValue = "0") long endTime
    ) throws Exception {
        CashHistorySearch search = new CashHistorySearch();
        if (type != null && !type.equals("")) {
            search.setType(type);
        }
        if (objectId != null && !objectId.equals("")) {
            search.setObjectId(objectId);
        }
        if (admin != null && !admin.equals("")) {
            search.setAdmin(admin);
        }
        if (startTime > 0) {
            search.setStartTime(startTime);
        }
        if (endTime > 0) {
            search.setEndTime(endTime);
        }
        Workbook wb = new HSSFWorkbook();
//        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        org.apache.poi.ss.usermodel.Row row = sheet.createRow((short) 0);
        row = sheet.createRow((short) 1);

        row.createCell(0).setCellValue("Người thao tác");
        row.createCell(1).setCellValue("Kiểu");
        row.createCell(2).setCellValue("Số xèng");
        row.createCell(3).setCellValue("Thời gian");
        row.createCell(4).setCellValue("Đối tượng");
        row.createCell(5).setCellValue("Ghi chú");
        row.createCell(6).setCellValue("Message");
        row.createCell(7).setCellValue("Người duyệt");
        row.createCell(8).setCellValue("Trạng thái");
        int i = 1;
        search.setPageIndex(0);
        search.setPageSize(100000000);
        DataPage<CashHistory> dataPage = cashHistoryService.search(search);
        List<String> list = new ArrayList();

        for (CashHistory cashHistory : dataPage.getData()) {
            if (!list.contains(cashHistory.getUserId())) {
                list.add(cashHistory.getUserId());
            }
        }

        List<User> userByIds = userService.getUserByIds(list);

        for (CashHistory cashHistory : dataPage.getData()) {
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
            String createTime = dt.format(cashHistory.getCreateTime());
            i++;
            row = sheet.createRow((short) i);
            for (User user : userByIds) {
                if (cashHistory.getUserId().equals(user.getId())) {
                    row.createCell(0).setCellValue(String.valueOf(user.getEmail()));
                }
            }
            row.createCell(1).setCellValue(String.valueOf(cashHistory.getType()));
            row.createCell(2).setCellValue(String.valueOf(cashHistory.getBalance()));
            row.createCell(3).setCellValue(createTime);
            row.createCell(4).setCellValue(String.valueOf(cashHistory.getObjectId()));
            row.createCell(5).setCellValue(String.valueOf(cashHistory.getNote()));
            row.createCell(6).setCellValue(String.valueOf(cashHistory.getMessage()));
            row.createCell(7).setCellValue(String.valueOf(cashHistory.getAdmin()));
            if (cashHistory.isFine() && cashHistory.isUnAppro()) {
                row.createCell(8).setCellValue(String.valueOf("Bị phạt"));
            } else if (!cashHistory.isFine() && !cashHistory.isUnAppro()) {
                row.createCell(8).setCellValue(String.valueOf("Đã Duyệt"));
            } else if (!cashHistory.isFine() && cashHistory.isUnAppro()) {
                row.createCell(8).setCellValue(String.valueOf("Không duyệt"));
            }
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-cashhistory.xls");
        wb.write(response.getOutputStream());
    }
}
