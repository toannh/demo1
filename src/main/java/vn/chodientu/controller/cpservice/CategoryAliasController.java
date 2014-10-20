/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.form.CategoryAliasForm;
import vn.chodientu.entity.form.CategoryAliasTopicForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CategoryAliasService;

/**
 *
 * @author Admin
 */
@Controller("cpCategoryAliasService")
@RequestMapping(value = "/cpservice/categoryalias")
public class CategoryAliasController extends BaseRest {

    @Autowired
    private CategoryAliasService categoryAliasService;

    /**
     *
     * @param aliasForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@ModelAttribute CategoryAliasForm aliasForm) throws Exception {
        return categoryAliasService.add(aliasForm);
    }

    /**
     * Service Sửa thông tin danh mục
     *
     * @param aliasForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@ModelAttribute CategoryAliasForm aliasForm) throws Exception {
        return categoryAliasService.edit(aliasForm);
    }

    /**
     * Lấy chi tiết 1 alias
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        return new Response(true, "alias", categoryAliasService.get(id));
    }

    /**
     * Sửa topics
     *
     * @param aliasTopicForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edittopics", method = RequestMethod.POST)
    @ResponseBody
    public Response editTopics(@ModelAttribute CategoryAliasTopicForm aliasTopicForm) throws Exception {
        return categoryAliasService.editTopics(aliasTopicForm);
    }

    /**
     * Sửa danh sách thương hiệu
     *
     * @param aliasForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editmanufacturers", method = RequestMethod.POST)
    @ResponseBody
    public Response editManufacturers(@RequestBody CategoryAliasForm aliasForm) throws Exception {
        return categoryAliasService.editManufacturer(aliasForm);
    }
    /**
     * Sửa vị trí alias
     *
     * @param aliasId
     * @param position
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editposition", method = RequestMethod.GET)
    @ResponseBody
    public Response editposition(@RequestParam String aliasId, @RequestParam int position) throws Exception {
        return categoryAliasService.changePosition(aliasId,position);
    }

}
