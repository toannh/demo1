package vn.chodientu.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;

public class BaseRest {

    @Autowired
    protected Viewer viewer;

    @Autowired
    public HttpServletRequest request;
    
    protected String baseUrl;
    protected String staticUrl;
    
    
    @ExceptionHandler
    @ResponseBody
    public Response handleExceptions(Exception ex) {
        ex.printStackTrace();
        return new Response(false, ex.getMessage());
    }
    
    @ModelAttribute
    public void addGlobalAttr(ModelMap map) {
        switch (request.getRequestURI()) {
            case "/":
                baseUrl = request.getRequestURL().substring(0, request.getRequestURL().length() - 1);
                break;
            case "":
                baseUrl = request.getRequestURL().toString();
                break;
            default:
                baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
                break;
        }
        staticUrl = baseUrl + "/static";
        map.put("baseUrl", baseUrl);
        map.put("staticUrl", staticUrl);
        map.put("viewer", viewer);
    }
}
