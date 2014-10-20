/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.NewsCategory;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.NewsCategoryService;

/**
 *
 * @author Phuongdt
 */
@Controller("cpNewsCategoryService")
@RequestMapping(value = "/cpservice/newscategory")
public class NewsCategoryController extends BaseRest {

    @Autowired
    private NewsCategoryService newsCategoryService;

    /**
     * Thêm danh mục tin tức mới
     *
     * @param category
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody NewsCategory category) throws Exception {
        return newsCategoryService.add(category);
    }

    /**
     * Sửa danh mục tin tức mới
     *
     * @param category
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@RequestBody NewsCategory category) throws Exception {
        return newsCategoryService.edit(category);
    }

    /**
     * Lấy thông tin danh mục tin tức mới
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam String id) throws Exception {
        NewsCategory byId = newsCategoryService.getById(id);
        return new Response(true, null, byId);
    }

    /**
     * Thay đổi trạng thái của chuyên mục
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editstatus", method = RequestMethod.GET)
    @ResponseBody
    public Response editStatus(@RequestParam String id) throws Exception {
        return newsCategoryService.editStatus(id);
    }

    /**
     * Thay đổi vị trí của chuyên mục
     *
     * @param id
     * @param position
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/changeposition", method = RequestMethod.GET)
    @ResponseBody
    public Response changePosition(@RequestParam String id, @RequestParam String position) throws Exception {
        Response r = newsCategoryService.changePosition(id, position);
        return new Response(true, "Sửa trạng thái thành công");
    }

    /**
     * Xóa danh mục tin tức theo ID
     *
     * @param id
     * @param position
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response del(@RequestParam String id) throws Exception {
        return newsCategoryService.delCat(id);

    }

    /**
     * *
     * Lấy các danh mục tin tức con theo Id cha
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getchilds", method = RequestMethod.GET)
    @ResponseBody
    public Response getchilds(@RequestParam String id) throws Exception {
        return newsCategoryService.getChilds(id);
       
    }

}
