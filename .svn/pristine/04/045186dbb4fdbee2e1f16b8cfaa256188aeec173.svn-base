/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.CategoryManufacturer;
import vn.chodientu.entity.db.CategoryProperty;
import vn.chodientu.entity.db.CategoryPropertyValue;
import vn.chodientu.entity.input.ManufacturerSearch;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ManufacturerService;

/**
 *
 * @author Admin
 */
@Controller("cpCategoryService")
@RequestMapping(value = "/cpservice/category")
public class CategoryController extends BaseRest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ManufacturerService manufacturerService;

    /**
     *
     * @param category
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody Category category) throws Exception {
        return categoryService.addCategory(category);
    }

    /**
     * Service Sửa thông tin danh mục
     *
     * @param category
     * @param categoryFrom
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(@RequestBody Category category) throws Exception {
        return categoryService.edit(category);
    }

    /**
     * Xóa danh mục
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public Response del(@RequestParam(value = "id", defaultValue = "") String id) {
        try {
            categoryService.remove(id);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
        return new Response(true, "Đã xóa thành công");
    }

    /**
     * Thêm thuộc tính mới.
     *
     * @param categoryProperty
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addproperty", method = RequestMethod.POST)
    public Response addProperty(@RequestBody CategoryProperty categoryProperty) {
        try {
            return categoryService.addProperty(categoryProperty);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }

    }

    /**
     * service Xóa Thuộc tính
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delproperty", method = RequestMethod.GET)
    public Response delproperty(@RequestParam(value = "id", defaultValue = "") String id) {
        try {

            categoryService.removeProperty(id);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
        return new Response(true, "Xóa thành công");
    }

    /**
     * service Sửa thuộc tính
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editproperty", method = RequestMethod.POST)
    public Response editproperty(@RequestBody CategoryProperty categoryProperty) {
        try {
            categoryService.editProperty(categoryProperty);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
        return new Response(true, "Sửa thành công");
    }

    /**
     * Thêm giá trị thuộc tính mới.
     *
     * @param categoryPropertyValue
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addpropertyvalue", method = RequestMethod.POST)
    public Response addProperty(@RequestBody CategoryPropertyValue categoryPropertyValue) {
        try {
            return categoryService.addPropertyValue(categoryPropertyValue);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }

    }

    /**
     * Sửa thông tin giá trị thuộc tính
     *
     * @param categoryPropertyValue
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editpropertyvalue", method = RequestMethod.POST)
    public Response editPropertyValue(@RequestBody CategoryPropertyValue categoryPropertyValue) {
        try {
            categoryService.editPropertyValue(categoryPropertyValue);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
        return new Response(true, "Sửa thành công");
    }

    /**
     * service Xóa Giá Trị Thuộc tính
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delpropertyvalue", method = RequestMethod.GET)
    public Response delPropertyValue(@RequestParam(value = "id", defaultValue = "") String id) {
        try {

            categoryService.removePropertyValue(id);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
        return new Response(true, "Xóa thành công");
    }

    /**
     * service Lấy danh sách thương hiệu theo mã danh mục sản phẩm
     *
     * @param id
     * @param manufacturerId
     * @param manufacturerName
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/viewmanufacturer", method = RequestMethod.GET)
    public Response viewManuFacturerByIdCategory(
            @RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "manufacturerId", defaultValue = "") String manufacturerId,
            @RequestParam(value = "manufacturerName", defaultValue = "") String manufacturerName,
            @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        ManufacturerSearch manufacturerSearch = new ManufacturerSearch();
        manufacturerSearch.setCategoryId(id);
        manufacturerSearch.setManufacturerId(manufacturerId);
        if (pageIndex == 0) {
            manufacturerSearch.setPageIndex(pageIndex);
        } else {
            manufacturerSearch.setPageIndex(pageIndex - 1);
        }
        manufacturerSearch.setPageSize(pageSize);
        if (!manufacturerName.equals("")) {
            manufacturerSearch.setName(manufacturerName);
        }
        return new Response(true, "Load thành công", manufacturerService.search(manufacturerSearch));

    }

    /**
     * service Xóa Giá Trị Thuộc tính
     *
     * @param catid
     * @param manufacturerId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delmapmanufacturer", method = RequestMethod.GET)
    public Response delMapManuFacturer(@RequestParam(value = "catid", defaultValue = "") String catid, @RequestParam(value = "manufacturerId", defaultValue = "") String manufacturerId) {
        try {
            CategoryManufacturer CM = new CategoryManufacturer();
            CM.setCategoryId(catid);
            CM.setManufacturerId(manufacturerId);
            manufacturerService.removeMap(CM);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
        return new Response(true, "Đã xóa thành công thương hiệu đã map");
    }

    /**
     * map mới 1 thương hiệu và danh mục
     *
     * @param manuId
     * @param cateId
     * @return
     */
    @RequestMapping(value = "/addmapmanufacturer", method = RequestMethod.GET)
    @ResponseBody
    public Response addMap(@RequestParam(value = "manuId", defaultValue = "") String manuId,
            @RequestParam(value = "cateId", defaultValue = "") String cateId) {
        CategoryManufacturer map = new CategoryManufacturer();
        map.setCategoryId(cateId);
        map.setManufacturerId(manuId);
        try {
            return manufacturerService.addMap(map);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * Index toàn bộ category
     *
     * @throws Exception
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public Response index() throws Exception {
        categoryService.index();
        return new Response(true, "Đang xử lý index ...");
    }

    /**
     * Xóa toàn bộ index
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unindex")
    @ResponseBody
    public Response unindex() throws Exception {
        categoryService.unindex();
        return new Response(true, "Xóa index thành công");
    }

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
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
        List<Category> listAll = categoryService.listAll();
        for (Category print : listAll) {
            if (print.getName().length() > 19) {
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
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=Danh-sach-danh-muc.xls");
        wb.write(response.getOutputStream());
    }

}
