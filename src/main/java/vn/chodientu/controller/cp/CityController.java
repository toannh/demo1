/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.District;
import vn.chodientu.service.CityService;
import vn.chodientu.service.DistrictService;

@Controller("cpCity")
@RequestMapping(value = "/cp/city")
public class CityController extends BaseCp {

    @Autowired
    private CityService cityService;
    @Autowired
    private DistrictService districtService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<City> city = cityService.list();

        model.put("city", city);
        model.put("clientScript", "city.init();");
        return "cp.city";
    }
}
