package vn.chodientu.controller.cpservice;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.CpFunction;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CpFunctionService;

@Controller("cpCpFunctionService")
@RequestMapping(value = "/cpservice/function")
public class FunctionController extends BaseRest {

    @Autowired
    private CpFunctionService cpFunctionService;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Response save(@RequestBody List<CpFunction> functions) {
        cpFunctionService.saveFunctions(functions);
        return new Response(true);
    }

    @ResponseBody
    @RequestMapping("/remove")
    public Response remove(@RequestParam(value = "uri") String uri) {
        cpFunctionService.removeFunction(uri);
        return new Response(true);
    }

    @ResponseBody
    @RequestMapping(value = "/removegroup")
    public Response removeGroup(@RequestParam(value = "name") String name) {
        try {
            cpFunctionService.removeGroup(name);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
        return new Response(true);
    }
}
