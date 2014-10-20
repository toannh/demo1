/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.input.ReportSearch;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ReportService;

/**
 *
 * @author thunt
 */
@Controller("cpReportService")
@RequestMapping(value = "/cpservice/report")
public class ReportController extends BaseRest {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/shop", method = RequestMethod.POST)
    @ResponseBody
    public Response findDataShop(@RequestBody ReportSearch search) {
        return reportService.findDataShop(search);
    }
    
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseBody
    public Response findDataItem(@RequestBody ReportSearch search) {
        return reportService.findDataItem(search);
    }
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public Response findDataUser(@RequestBody ReportSearch search) {
        return reportService.findDataUser(search);
    }
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseBody
    public Response findDataOrder(@RequestBody ReportSearch search) {
        return reportService.findDataOrder(search);
    }
    
    @RequestMapping(value = "/lading", method = RequestMethod.POST)
    @ResponseBody
    public Response findDataLading(@RequestBody ReportSearch search) {
        return reportService.findDataLading(search);
    }

    @RequestMapping(value = "/gmv", method = RequestMethod.POST)
    @ResponseBody
    public Response findDataGMV(@RequestBody ReportSearch search) {
        return reportService.findDataGMV(search);
    }

    @RequestMapping(value = "/cash", method = RequestMethod.POST)
    @ResponseBody
    public Response findDataCash(@RequestBody ReportSearch search) {
        return reportService.findDataCash(search);
    }

}
