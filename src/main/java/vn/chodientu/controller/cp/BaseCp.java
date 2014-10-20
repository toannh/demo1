package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import vn.chodientu.controller.BaseWeb;
import vn.chodientu.service.AdministratorService;
import vn.chodientu.service.CpFunctionService;

public class BaseCp extends BaseWeb {

    @Autowired
    private CpFunctionService functionService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private Gson gson;

    @ModelAttribute
    public void addIndexGlobalAttr(ModelMap map) {
        if (viewer != null && viewer.getAdministrator() != null) {
            try {
                map.put("roles", administratorService.getRoles(viewer.getAdministrator().getId()));
            } catch (Exception ex) {
                Logger.getLogger(BaseCp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        map.put("cpFunctions", functionService.getAll());
        map.put("cpFunctionGroups", functionService.getGroups());
    }
}
