package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import vn.chodientu.entity.db.Category;
import vn.chodientu.service.CategoryService;

@Controller("cpCategory")
@RequestMapping("/cp/category")
public class CategoryController extends BaseCp {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private Gson gson;

    @RequestMapping("")
    public String list(@RequestParam(value = "id", defaultValue = "") String id, ModelMap model) {
        Category category = new Category();
        category.setId(id);
        List<Category> ancestors;
        List<Category> list = new ArrayList<>();
        if (id.trim().equals("")) {
            ancestors = new ArrayList<>();
            list = categoryService.getChilds(null);
        } else {
            try {
                list = categoryService.getChilds(id);
                ancestors = categoryService.getAncestors(id);
            } catch (Exception ex) {
                throw new HttpServerErrorException(HttpStatus.NOT_FOUND, ex.getMessage());
            }
        }
        model.put("ancestors", ancestors);
        model.put("listCategories", list);
        model.put("category", category);
        model.put("clientScript", "category.init();");
        model.put("countInElasticseach", categoryService.countElastic());
        model.put("realCategoryCount", categoryService.count());

        return "cp.category";
    }

    @RequestMapping(value = "/excelbyleaf", method = RequestMethod.GET)
    public void excel(HttpServletResponse response) throws Exception {
        Workbook wb = new HSSFWorkbook();
//        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");
        org.apache.poi.ss.usermodel.Row row = sheet.createRow((short) 0);
        row = sheet.createRow((short) 1);
        row.createCell(0).setCellValue("Mã");
        row.createCell(1).setCellValue("Tên");
        row.createCell(2).setCellValue("Mã Path");
        row.createCell(3).setCellValue("Tên path");
        row.createCell(4).setCellValue("Cấp danh mục");

        int i = 1;
        List<Category> listAll = categoryService.listByLeafDisplay();
        for (Category print : listAll) {
            i++;
            row = sheet.createRow((short) i);
            row.createCell(0).setCellValue(String.valueOf(print.getId()));
            row.createCell(1).setCellValue(String.valueOf(print.getName()));
            row.createCell(2).setCellValue(String.valueOf(print.getPath()));
            String path = "";
            for (String categoryId : print.getPath()) {
                Category get = categoryService.get(categoryId);
                path += get.getName() + " >>";
            }
            row.createCell(3).setCellValue(String.valueOf(path));
            row.createCell(4).setCellValue(String.valueOf(print.getLevel()));
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=Danh-sach-danh-muc-cap-la.xls");
        wb.write(response.getOutputStream());
    }

}
