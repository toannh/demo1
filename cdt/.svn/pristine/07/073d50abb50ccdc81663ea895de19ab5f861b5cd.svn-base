package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.chodientu.entity.input.ReportSearch;

@Controller("cpReport")
@RequestMapping("/cp/report")
public class ReportController extends BaseCp {

    @Autowired
    private Gson gson;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map) throws ParseException {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.setTime(date);
        ReportSearch search = new ReportSearch();
        search.setStartTime(sdf.parse(cal.get(Calendar.YEAR)
                + "-" + (cal.get(Calendar.MONTH) + 1) + "-01").getTime());
        search.setEndTime(date.getTime());
        map.put("search", search);
        map.put("clientScript", "report.init();");
        return "cp.report";
    }

}
