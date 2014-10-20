package vn.chodientu.controller.cp;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Footerkeyword;
import vn.chodientu.entity.input.FooterKeywordSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.FooterKeywordService;

@Controller("cpFooterKeyword")
@RequestMapping("/cp/footerkeyword")
public class FooterKeywordController extends BaseCp {

    @Autowired
    private FooterKeywordService footerKeywordService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
        FooterKeywordSearch keywordSearch = new FooterKeywordSearch();
        if (session.getAttribute("keywordSearch") != null && page != 0) {
            keywordSearch = (FooterKeywordSearch) session.getAttribute("keywordSearch");
        } else {
            session.setAttribute("keywordSearch", keywordSearch);
        }
        keywordSearch.setPageIndex(page - 1);
        keywordSearch.setPageSize(100);
        DataPage<Footerkeyword> dataPage = footerKeywordService.search(keywordSearch);
        map.put("dataPage", dataPage);
        map.put("keywordSearch", keywordSearch);
        map.put("clientScript", "footerkeyword.init();");
        return "cp.footerkeyword";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String list(ModelMap map, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int page,
            @ModelAttribute FooterKeywordSearch keywordSearch) {
        session.setAttribute("keywordSearch", keywordSearch);

        keywordSearch.setPageIndex(page - 1);
        keywordSearch.setPageSize(100);
        DataPage<Footerkeyword> dataPage = footerKeywordService.search(keywordSearch);
        map.put("dataPage", dataPage);
        map.put("keywordSearch", keywordSearch);
        map.put("clientScript", "footerkeyword.init();");
        return "cp.footerkeyword";
    }

}
