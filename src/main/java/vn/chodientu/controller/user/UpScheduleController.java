package vn.chodientu.controller.user;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.UpSchedule;
import vn.chodientu.entity.db.UpScheduleHistory;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.input.PostItemSearch;
import vn.chodientu.service.CashService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.UpScheduleService;

/**
 * @since May 20, 2014
 * @author Phu
 */
@Controller
@RequestMapping("/user")
public class UpScheduleController extends BaseUser {

    @Autowired
    private CashService cashService;
    @Autowired
    private UpScheduleService upScheduleService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;

    /**
     * Quản lý up tin
     *
     * @param model
     * @param page
     * @return
     * @throws java.lang.Exception
     */
    @RequestMapping(value = {"/posting"}, method = RequestMethod.GET)
    public String posting(ModelMap model, @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "run", defaultValue = "0") int run) throws Exception {

        User user = viewer.getUser();
        if (user == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/posting.html";
        }
        PostItemSearch postItemSearch = new PostItemSearch();
        if (page > 0) {
            postItemSearch.setPageIndex(page - 1);
        } else {
            postItemSearch.setPageIndex(0);
        }
        postItemSearch.setPageSize(15);
        if (run == 1) {
            postItemSearch.setRun(1);
            postItemSearch.setDone(3);
        }
        if (run == 2) {
            postItemSearch.setRun(2);
        }
        if (run == 3) {
            postItemSearch.setDone(1);
        }
        DataPage<UpSchedule> dataPage = upScheduleService.search(postItemSearch, viewer.getUser().getId());
        List<String> itemIds = new ArrayList<>();
        for (UpSchedule upSchedule : dataPage.getData()) {
            if (!itemIds.contains(upSchedule.getItemId())) {
                itemIds.add(upSchedule.getItemId());
            }
        }
        List<Item> items = itemService.list(itemIds);
        List<String> imgs = null;
        for (Item item : items) {
            imgs = new ArrayList<>();
            for (String img : item.getImages()) {
                try {
                    imgs.add(imageService.getUrl(img).thumbnail(100, 100, "outbound").getUrl());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            item.setImages(imgs);
        }
        model.put("dataPage", dataPage);
        model.put("items", items);
        model.put("run", run);
        model.put("postItemSearch", postItemSearch);
        model.put("clientScript", "upschedule.init();");
        model.put("user", cashService.getCash(viewer.getUser().getId()));
        return "user.posting";
    }

    /**
     * Lịch sử up tin
     *
     * @param model
     * @param page
     * @return
     * @throws java.lang.Exception
     */
    @RequestMapping(value = {"/postinghistory"}, method = RequestMethod.GET)
    public String history(ModelMap model, @RequestParam(value = "page", defaultValue = "0") int page) throws Exception {
        User user = viewer.getUser();
        if (user == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/posting.html";
        }
        PostItemSearch itemSearch = new PostItemSearch();
        if (page > 0) {
            itemSearch.setPageIndex(page - 1);
        } else {
            itemSearch.setPageIndex(0);
        }
        itemSearch.setPageSize(15);
        DataPage<UpScheduleHistory> dataPage = upScheduleService.searchHistory(itemSearch, user.getId());
        List<String> itemIds = new ArrayList<>();
        for (UpScheduleHistory upSchedule : dataPage.getData()) {
            if (!itemIds.contains(upSchedule.getItemId())) {
                itemIds.add(upSchedule.getItemId());
            }
        }
        List<Item> items = itemService.list(itemIds);
        List<String> imgs = null;
        for (Item item : items) {
            imgs = new ArrayList<>();
            for (String img : item.getImages()) {
                try {
                    imgs.add(imageService.getUrl(img).thumbnail(100, 100, "outbound").getUrl());
                } catch (Exception e) {
                }
            }
            item.setImages(imgs);
        }
        model.put("dataPage", dataPage);
        model.put("items", items);
        model.put("postItemSearch", itemSearch);
        model.put("clientScript", "upschedule.initHistory();");
        return "user.posting.history";
    }

    /**
     * Lọc lịch sử up tin
     *
     * @param model
     * @param itemSearch
     * @param page
     * @return
     * @throws java.lang.Exception
     */
    @RequestMapping(value = {"/postinghistory"}, method = RequestMethod.POST)
    public String history(ModelMap model, @ModelAttribute PostItemSearch itemSearch, @RequestParam(value = "page", defaultValue = "0") int page) throws Exception {
        User user = viewer.getUser();
        if (user == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/posting.html";
        }
        itemSearch.setPageIndex(0);
        itemSearch.setPageSize(15);
        DataPage<UpScheduleHistory> dataPage = upScheduleService.searchHistory(itemSearch, user.getId());
        List<String> itemIds = new ArrayList<>();
        for (UpScheduleHistory upSchedule : dataPage.getData()) {
            if (!itemIds.contains(upSchedule.getItemId())) {
                itemIds.add(upSchedule.getItemId());
            }
        }
        List<Item> items = itemService.list(itemIds);
        List<String> imgs = null;
        for (Item item : items) {
            imgs = new ArrayList<>();
            for (String img : item.getImages()) {
                try {
                    imgs.add(imageService.getUrl(img).thumbnail(100, 100, "outbound").getUrl());
                } catch (Exception e) {
                }
            }
            item.setImages(imgs);
        }
        model.put("dataPage", dataPage);
        model.put("items", items);
        model.put("postItemSearch", itemSearch);
        model.put("clientScript", "upschedule.initHistory();");
        return "user.posting.history";
    }

}
