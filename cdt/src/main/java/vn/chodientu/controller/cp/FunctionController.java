package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import vn.chodientu.entity.db.CpFunction;
import vn.chodientu.entity.db.CpFunctionGroup;
import vn.chodientu.entity.enu.CpFunctionType;
import vn.chodientu.service.CpFunctionService;

@Controller("cpFunction")
@RequestMapping("/cp/function")
public class FunctionController extends BaseCp {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private CpFunctionService functionService;
    @Autowired
    private Gson gson;

    @RequestMapping
    public String list(ModelMap model) {
        List<CpFunction> unmappedCpFunctions = new ArrayList<>();
        List<CpFunction> invalidCpFunctions = new ArrayList<>();
        List<CpFunction> deletedCpFunctions = new ArrayList<>();
        List<CpFunctionGroup> groups = functionService.getGroups();
        List<CpFunction> functions = functionService.getAll();

        for (RequestMappingInfo hm : requestMappingHandlerMapping.getHandlerMethods().keySet()) {
            for (String uri : hm.getPatternsCondition().getPatterns()) {
                if (!containUri(functions, uri) && !containUri(unmappedCpFunctions, uri)) {
                    if (!uri.startsWith("/cp/auth") && !uri.startsWith("/cp/index")) {
                        if (uri.startsWith("/cp/")) {
                            CpFunction fun = new CpFunction();
                            fun.setUri(uri);
                            fun.setRefUri(uri.split("/")[2]);
                            fun.setType(CpFunctionType.ACTION);
                            unmappedCpFunctions.add(fun);
                        }
                    }
                }
            }
        }
        for (RequestMappingInfo hm : requestMappingHandlerMapping.getHandlerMethods().keySet()) {
            for (String uri : hm.getPatternsCondition().getPatterns()) {
                if (!containUri(functions, uri) && !containUri(unmappedCpFunctions, uri)) {
                    if (uri.startsWith("/cpservice/")) {
                        CpFunction fun = new CpFunction();
                        fun.setUri(uri);
                        fun.setRefUri(uri.split("/")[2]);
                        fun.setType(CpFunctionType.SERVICE);
                        if (containRef(functions, fun.getRefUri(), CpFunctionType.ACTION)) {
                            fun.setName("(Mới thêm chưa map)");
                            functions.add(fun);
                        } else if (containRef(unmappedCpFunctions, fun.getRefUri(), CpFunctionType.ACTION)) {
                            unmappedCpFunctions.add(fun);
                        } else {
                            invalidCpFunctions.add(fun);
                        }
                    }
                }
            }
        }

        boolean flag;
        for (CpFunction function : functions) {
            flag = false;
            for (RequestMappingInfo hm : requestMappingHandlerMapping.getHandlerMethods().keySet()) {
                for (String uri : hm.getPatternsCondition().getPatterns()) {
                    if (function.getUri().equals(uri)) {
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                deletedCpFunctions.add(function);
            }
        }

        model.put("groups", groups);
        model.put("functions", functions);
        model.put("unmappedFunctions", unmappedCpFunctions);
        model.put("invalidFunctions", invalidCpFunctions);
        model.put("deletedFunctions", deletedCpFunctions);

        String clientScript = "func.init();";
        clientScript += "unmappedCpFunctions=" + gson.toJson(unmappedCpFunctions) + ";";
        clientScript += "groups=" + gson.toJson(groups) + ";";
        clientScript += "functions=" + gson.toJson(functions) + ";";

        model.put("clientScript", clientScript);
        return "cp.function";
    }

    private boolean containUri(List<CpFunction> functions, String uri) {
        for (CpFunction f : functions) {
            if (f.getUri().equals(uri)) {
                return true;
            }
        }
        return false;
    }

    private boolean containRef(List<CpFunction> functions, String refUri, CpFunctionType type) {
        for (CpFunction f : functions) {
            if (f.getRefUri().equals(refUri) && f.getType() == type) {
                return true;
            }
        }
        return false;
    }
}
