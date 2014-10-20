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
import vn.chodientu.entity.db.District;
import vn.chodientu.entity.form.CityForm;
import vn.chodientu.entity.form.DistrictForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CityService;
import vn.chodientu.service.DistrictService;

/**
 *
 * @author Admin
 */
@Controller("cpCityService")
@RequestMapping(value = "/cpservice/city")
public class CityController extends BaseRest {

    @Autowired
    private CityService cityService;
    @Autowired
    private DistrictService districtService;

    @RequestMapping(value = "/addcity", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody CityForm cityForm) throws Exception {
        return cityService.add(cityForm);
    }

    @RequestMapping(value = "/editcity", method = RequestMethod.POST)
    @ResponseBody
    public Response editCity(@RequestBody CityForm cityForm) throws Exception {
        return cityService.editCity(cityForm);
    }

    @RequestMapping(value = "/delcity", method = RequestMethod.GET)
    @ResponseBody
    public Response delCity(@RequestParam(value = "id", defaultValue = "") String id) {
        return cityService.detele(id);
    }

    @ResponseBody
    @RequestMapping(value = "/deldistrict", method = RequestMethod.GET)
    public Response delDistrict(@RequestParam("id") String id) {
        return districtService.delete(id);
    }

    @ResponseBody
    @RequestMapping(value = "/adddistrict", method = RequestMethod.POST)
    public Response add(@RequestBody DistrictForm form) throws Exception {
        return districtService.add(form);
    }

    @ResponseBody
    @RequestMapping(value = "/editdistrict", method = RequestMethod.GET)
    public Response editDistrict(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam(value = "position", defaultValue = "0") int position, @RequestParam(value = "scId", defaultValue = "") String scId) {
        return districtService.edit(id, name, position, scId);
    }

    @ResponseBody
    @RequestMapping(value = "/listcity", method = RequestMethod.GET)
    public Response listcity() {
        return new Response(true, null, cityService.list());
    }

    @ResponseBody
    @RequestMapping(value = "/listdistrictbycity", method = RequestMethod.GET)
    public Response listdistrictbycity(@RequestParam String cityId) {
        List<District> allDistrictByCity = districtService.getAllDistrictByCity(cityId);
        return new Response(true, null, allDistrictByCity);
    }

}
