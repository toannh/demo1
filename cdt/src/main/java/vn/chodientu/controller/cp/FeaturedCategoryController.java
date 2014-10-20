package vn.chodientu.controller.cp;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.FeaturedCategory;
import vn.chodientu.entity.db.FeaturedCategorySub;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.FeaturedCategoryService;
import vn.chodientu.service.FeaturedCategorySubService;

@Controller("cpFeaturedCategory")
@RequestMapping("/cp/featuredcategory")
public class FeaturedCategoryController extends BaseCp {

    @Autowired
    private FeaturedCategoryService featuredCategoryService;
    @Autowired
    private FeaturedCategorySubService featuredCategorySubService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map, @RequestParam(value = "id", defaultValue = "") String id, @RequestParam(value = "cate", defaultValue = "") String cate, @RequestParam(value = "catesub", defaultValue = "") String catesub) throws Exception {

        if (!id.trim().equals("")) {
            map.put("cateSubTrue", 1);
        }
        if (cate.trim().equals("") && catesub.trim().equals("")) {
            List<FeaturedCategory> listFC = featuredCategoryService.getall(false);
            List<Category> nameCate = new ArrayList<>();
            for (FeaturedCategory fc : listFC) {
                String categoryId = fc.getCategoryId();
                nameCate.add(categoryService.get(categoryId));

            }
            List<FeaturedCategorySub> categorySubs = new ArrayList<>();
            if (!id.trim().equals("")) {
                categorySubs = featuredCategorySubService.getByCategoryId(id);
                FeaturedCategory byCategoryId = featuredCategoryService.getByCategoryId(id);
                map.put("template", byCategoryId.getTemplate());
            }

            map.put("categorySubs", categorySubs);
            map.put("listFC", listFC);
            map.put("clientScript", "featuredcategory.init();");
            return "cp.featuredcategory";
        } else {
            if (!cate.trim().equals("")) {
                List<FeaturedCategory> listFC = featuredCategoryService.getall(false);
                for (FeaturedCategory fc : listFC) {
                    List<FeaturedCategorySub> byCategoryId = featuredCategorySubService.getByCategoryId(fc.getCategoryId());
                    if (byCategoryId == null || byCategoryId.isEmpty()) {
                        fc.setLeaf(true);
                    } else {
                        fc.setLeaf(false);
                    }

                }
                map.put("cate", listFC);
                map.put("clientScript", "featuredcategory.init();");
                return "cp.featuredcategory.cate";
            }
            if (!catesub.trim().equals("")) {
                List<FeaturedCategorySub> categorySubs = new ArrayList<>();
                if (!catesub.trim().equals("")) {
                    categorySubs = featuredCategorySubService.getByCategoryId(catesub);
                }
                map.put("cate", categorySubs);
                map.put("clientScript", "featuredcategory.init();");
                return "cp.featuredcategory.catesub";
            }
        }
        return null;

    }

 

}
