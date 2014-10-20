/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice.global;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.District;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CityService;
import vn.chodientu.service.DistrictService;

/**
 *
 * @author Admin
 */
@Controller("cpGlobalCityService")
@RequestMapping(value = "/cpservice/global/city")
public class CityController extends BaseRest {

    @Autowired
    private CityService cityService;
    @Autowired
    private DistrictService districtService;

    @RequestMapping(value = "/getcitybyid", method = RequestMethod.GET)
    @ResponseBody
    public Response getbyId(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        return cityService.getCitybyId(id);
    }

    @ResponseBody
    @RequestMapping(value = "/getdistrictbycityid", method = RequestMethod.GET)
    public Response getDistrictByCityId(@RequestParam("id") String id) throws Exception {
        Response<City> citybyId = cityService.getCitybyId(id);
        City data = citybyId.getData();
        List<District> listdistrict = districtService.getAllDistrictByCity(data.getId());
        return new Response(true, "Danh sách quận huyện", listdistrict);
    }

}
