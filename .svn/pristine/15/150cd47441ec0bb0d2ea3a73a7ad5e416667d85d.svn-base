package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import vn.chodientu.entity.db.Email;
import vn.chodientu.entity.input.EmailSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.EmailNewsletterService;

@Controller("cpEmail")
@RequestMapping("/cp/email")
public class EmailController extends BaseCp {

    @Autowired
    private EmailNewsletterService emailNewsletterService;
    @Autowired
    private Gson gson;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map,
            HttpSession session,
            @RequestParam(value = "page", defaultValue = "0") int page) throws Exception {

        EmailSearch search = new EmailSearch();
        if (session.getAttribute("search") != null && page != 0) {
            search = (EmailSearch) session.getAttribute("search");
        } else {
            session.setAttribute("search", search);
        }
        if (page > 0) {
            search.setPageIndex(page - 1);
        } else {
            search.setPageIndex(0);
        }
        search.setPageSize(100);
        DataPage<Email> dataPage = emailNewsletterService.search(search);
        map.put("search", search);
        map.put("page", dataPage);
        map.put("clientScript", "email.init();");
        return "cp.email";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(
            @ModelAttribute("search") EmailSearch search,
            HttpSession session, ModelMap map) {
        search.setPageSize(100);
        search.setPageIndex(0);
        session.setAttribute("search", search);
        DataPage<Email> dataPage = emailNewsletterService.search(search);
        map.put("search", search);
        map.put("page", dataPage);
        map.put("clientScript", "email.init();");
        return "cp.email";
    }

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public void excel(HttpServletResponse response,
            @RequestParam(value = "email", defaultValue = "") String email
    ) throws Exception {
        EmailSearch search = new EmailSearch();
        if (email != null && !email.equals("")) {
            search.setEmail(email);
        }

        Workbook wb = new HSSFWorkbook();
//        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        org.apache.poi.ss.usermodel.Row row = sheet.createRow((short) 0);
        row = sheet.createRow((short) 1);

        row.createCell(0).setCellValue("Email");
        row.createCell(1).setCellValue("Ngày đăng kí");

        int i = 1;
        search.setPageIndex(0);
        search.setPageSize(1000000);
        DataPage<Email> user = emailNewsletterService.search(search);
        for (Email print : user.getData()) {
            Long create = print.getCreateTime();
            Date cre = new Date(create);
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
            String Jointime = dt.format(cre);
            i++;
            row = sheet.createRow((short) i);
            row.createCell(0).setCellValue(String.valueOf(print.getEmail()));
            row.createCell(1).setCellValue(String.valueOf(Jointime));
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=Danh-sach-email-dang-ki-nhan-km.xls");
        wb.write(response.getOutputStream());
    }

}
