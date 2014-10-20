/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.FeaturedCategory;
import vn.chodientu.entity.db.FeaturedCategorySub;
import vn.chodientu.entity.form.FeaturedCategoryForm;
import vn.chodientu.entity.form.FeaturedCategoryImageForm;
import vn.chodientu.entity.form.FeaturedCategorySubForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.FeaturedCategoryService;
import vn.chodientu.service.FeaturedCategorySubService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;

/**
 *
 * @author Phuongdt
 */
@Controller("cpFeaturedCategoryService")
@RequestMapping(value = "/cpservice/featuredcategory")
public class FeaturedCategoryController extends BaseRest {

    @Autowired
    private FeaturedCategoryService featuredCategoryService;
    @Autowired
    private FeaturedCategorySubService featuredCategorySubService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemService itemService;

    /**
     * Thêm danh mục nổi bật trang chủ
     *
     * @param featuredCategoriesForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody FeaturedCategoryForm featuredCategoriesForm) throws Exception {
        return featuredCategoryService.add(featuredCategoriesForm);
    }

    /**
     * Thêm danh mục nổi bật trang chủ
     *
     * @param featuredCategorySubForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addsub", method = RequestMethod.POST)
    @ResponseBody
    public Response addSub(@RequestBody FeaturedCategorySubForm featuredCategorySubForm) throws Exception {
        return featuredCategorySubService.add(featuredCategorySubForm);
    }

    /**
     * Lấy danh mục gốc
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getchilds", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getchilds() throws Exception {
        return categoryService.getChilds(null);
    }

    /**
     * Lấy template theo Id
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/gettemplate", method = RequestMethod.GET)
    @ResponseBody
    public Response getTemplate(@RequestParam String id) throws Exception {
        return featuredCategorySubService.getTemplateById(id);
    }

    /**
     * Download ảnh lấy từ url
     *
     * @param url
     * @param itemId
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/downloadimageItem", method = RequestMethod.GET)
    @ResponseBody
    public Response downloadimageItem(@RequestParam String url, @RequestParam String itemId, @RequestParam int width, @RequestParam int height) throws Exception {
        return featuredCategoryService.downloadImageItem(url, itemId, width, height);
    }

    /**
     * Upload image ảnh trong box danh mục nổi bật
     *
     * @param featuredCategoryImageForm
     * @param width
     * @param height
     * @param itemIdDel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadimageItem", method = RequestMethod.POST)
    @ResponseBody
    public Response uploadimageItem(@ModelAttribute FeaturedCategoryImageForm featuredCategoryImageForm,
            @RequestParam(value = "width") int width, @RequestParam(value = "height") int height, @RequestParam(value = "itemIdDel") String itemIdDel) throws Exception {
        featuredCategoryImageForm.setHeight(height);
        featuredCategoryImageForm.setWidth(width);
        featuredCategoryImageForm.setItemIdDel(itemIdDel);
        return featuredCategoryService.uploadImageItem(featuredCategoryImageForm);
    }

    /**
     * Thêm item vào box danh mục nổi bật
     * @param featuredCategorySubForm
     * @param tpl
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/additemtemplate", method = RequestMethod.POST)
    @ResponseBody
    public Response additemtemplate(@RequestBody FeaturedCategorySubForm featuredCategorySubForm, @RequestParam(value = "tpl") int tpl) throws Exception {
        return featuredCategorySubService.addItemTemplate(featuredCategorySubForm, tpl);
    }

    @RequestMapping(value = "/getitemfeaturedcategorysub", method = RequestMethod.GET)
    @ResponseBody
    public Response getItemfeaturedcategorySub(@RequestParam String itemId, @RequestParam String idCategory) throws Exception {
        return featuredCategorySubService.getItemFeatureCategorySub(itemId, idCategory);
    }

    @RequestMapping(value = "/getbannerfeaturedcategorysub", method = RequestMethod.GET)
    @ResponseBody
    public Response getBannerFeaturedCategorySub(@RequestParam String bannerId, @RequestParam String idCategory) throws Exception {
        return featuredCategorySubService.getBannerFeatureCategorySub(bannerId, idCategory);
    }

    @RequestMapping(value = "/getmanufacturerfeaturedcategorysub", method = RequestMethod.GET)
    @ResponseBody
    public Response getManufacturerFeaturedCategorySub(@RequestParam String idCategory) throws Exception {
        return featuredCategorySubService.getManufacturerFeaturedCategorySub(idCategory);
    }

    @RequestMapping(value = "/changepositonfeaturedcategory", method = RequestMethod.GET)
    @ResponseBody
    public Response changepositonfeaturedcategory(@RequestParam String idCategory, @RequestParam int position) throws Exception {
        return featuredCategoryService.changePosition(idCategory, position);
    }
    @RequestMapping(value = "/changestatusfeaturedcategory", method = RequestMethod.GET)
    @ResponseBody
    public Response changestatusfeaturedcategory(@RequestParam String idCategory) throws Exception {
        return featuredCategoryService.editStatus(idCategory);
    }
    @RequestMapping(value = "/delfeaturedcategory", method = RequestMethod.GET)
    @ResponseBody
    public Response delfeaturedcategory(@RequestParam String idCategory) throws Exception {
        return featuredCategoryService.del(idCategory);
    }
    //THay đổi trang thái, vị trí, del của danh mục cấp con
    @RequestMapping(value = "/changepositonfeaturedcategorysub", method = RequestMethod.GET)
    @ResponseBody
    public Response changepositonfeaturedcategorysub(@RequestParam String idCategory, @RequestParam int position) throws Exception {
        return featuredCategorySubService.changePosition(idCategory, position);
    }
    @RequestMapping(value = "/changestatusfeaturedcategorysub", method = RequestMethod.GET)
    @ResponseBody
    public Response changestatusfeaturedcategorysub(@RequestParam String idCategory) throws Exception {
        return featuredCategorySubService.editStatus(idCategory);
    }
    @RequestMapping(value = "/delfeaturedcategorysub", method = RequestMethod.GET)
    @ResponseBody
    public Response delfeaturedcategorysub(@RequestParam String idCategory) throws Exception {
        return featuredCategorySubService.del(idCategory);
    }
    @RequestMapping(value = "/changenamefeaturedcategory", method = RequestMethod.POST)
    @ResponseBody
    public Response changenamefeaturedcategory(@RequestBody FeaturedCategoryForm categoryForm) throws Exception {
        categoryForm.setCategoryId(categoryForm.getCategoryId());
        categoryForm.setCategoryName(categoryForm.getCategoryName());
        return featuredCategoryService.editName(categoryForm);
    }
    @RequestMapping(value = "/changenamefeaturedcategorysub", method = RequestMethod.POST)
    @ResponseBody
    public Response changenamefeaturedcategorysub(@RequestBody FeaturedCategorySubForm categoryForm) throws Exception {
        categoryForm.setCategorySubId(categoryForm.getCategorySubId());
        categoryForm.setCategorySubName(categoryForm.getCategorySubName());
        return featuredCategorySubService.editName(categoryForm);
    }
   
}
