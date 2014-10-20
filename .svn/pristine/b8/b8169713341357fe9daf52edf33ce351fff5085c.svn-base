package vn.chodientu.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.UpSchedule;
import vn.chodientu.entity.db.UpScheduleHistory;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.form.UpScheduleForm;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.input.PostItemSearch;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.UpScheduleHistoryRepository;
import vn.chodientu.repository.UpScheduleRepository;

/**
 *
 * @author thanhvv
 */
@Service
public class UpScheduleService {

    @Autowired
    private UpScheduleRepository upScheduleRepository;
    @Autowired
    private UpScheduleHistoryRepository historyRepository;
    @Autowired
    private CashService cashService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SearchIndexService searchIndexService;

    @Scheduled(fixedDelay = 1000)
    public void doRun() {
        while (true) {
            Calendar datenow = Calendar.getInstance();
            datenow.setTime(new Date(System.currentTimeMillis()));
            UpSchedule upS = upScheduleRepository.getByThread(datenow.get(Calendar.DAY_OF_WEEK));
            if (upS == null) {
                break;
            }

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd.MM.yyyy");
                Date parse = sdf.parse(datenow.get(Calendar.HOUR_OF_DAY) + ":"
                        + datenow.get(Calendar.MINUTE) + " "
                        + datenow.get(Calendar.DAY_OF_MONTH) + "."
                        + (datenow.get(Calendar.MONTH) + 1) + "."
                        + datenow.get(Calendar.YEAR));
                if (upS.getNextTurn() == parse.getTime()) {
                    this.action(upS);
                } else {
                    this.getNextTurn(upS);
                    upS.setLock(false);
                    upS.setLastTime(System.currentTimeMillis());
                    upScheduleRepository.save(upS);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Async
    private void action(UpSchedule upSchedule) {
        Item item = itemRepository.find(upSchedule.getItemId());
        if (item != null) {
            item.setUpTime(System.currentTimeMillis());
            itemRepository.save(item);
            searchIndexService.processIndexItem(item);

            UpScheduleHistory history = new UpScheduleHistory();
            history.setCreateTime(System.currentTimeMillis());
            history.setScheduleTime(System.currentTimeMillis());
            history.setItemId(upSchedule.getItemId());
            history.setSellerId(upSchedule.getSellerId());
            history.setDone(true);
            history.setUpScheduleId(upSchedule.getId());
            historyRepository.save(history);
            try {
                upSchedule.setLock(false);
                this.getNextTurn(upSchedule);
            } catch (Exception e) {
            }
            upSchedule.setUseQuantity(upSchedule.getUseQuantity() + 1);
            if (upSchedule.getUseQuantity() >= upSchedule.getQuantity()) {
                upSchedule.setDone(true);
            }
            upSchedule.setRun(true);
            upSchedule.setLastTime(System.currentTimeMillis());
            upScheduleRepository.save(upSchedule);
        }
    }

    public void getNextTurn(UpSchedule upSchedule) throws ParseException {
        long uTime = -1, uDay = 0;
        Calendar c = Calendar.getInstance();
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("00:00 dd.MM.yyyy");
        Date currentTimeDay = sdf.parse(sdf.format(new Date(currentTimeMillis)));
        long currentHour = currentTimeMillis - currentTimeDay.getTime();
        currentHour -= 7 * 60 * 60 * 1000;
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        Collections.sort(upSchedule.getUpTime());
        Collections.sort(upSchedule.getUpDay());
        for (Long upTime : upSchedule.getUpTime()) {
            if (upTime > currentHour) {
                uTime = upTime;
                break;
            }
        }

        for (Integer upDay : upSchedule.getUpDay()) {
            if (upDay >= currentDay) {
                if (uTime == -1 && upDay == currentDay) {
                    try {
                        uTime = upSchedule.getUpTime().get(0);
                    } catch (Exception e) {
                        uTime = 0;
                    }
                    continue;
                }
                uDay = upDay - currentDay;
                if (uDay < 0) {
                    uDay = 7 + uDay;
                }
                break;
            }
        }
        uTime = uTime + (7 * 3600000);
        long time = currentTimeDay.getTime() + uTime + (uDay * 86400000);
        upSchedule.setNextTurn(time);
    }

    /**
     * Cài đặt lịch up tin
     *
     * @param scheduleForm
     * @param user
     * @return
     */
    public Response add(UpScheduleForm scheduleForm, User user) {
        try {
            List<UpSchedule> upSchedule = scheduleForm.getUpSchedule();
            if (upSchedule == null || upSchedule.isEmpty()) {
                return new Response(false, "Chọn sản phẩm để thực hiện chức năng uptin");
            }
            List<String> itemIds = new ArrayList<>();
            for (UpSchedule schedule : upSchedule) {
                schedule.setSellerId(user.getId());
                itemIds.add(schedule.getItemId());
                if (scheduleForm.getType() != 0) {
                    if (schedule.getUpDay() == null || schedule.getUpDay().isEmpty()) {
                        return new Response(false, "Ngày up tin trong tuần chưa được cấu hình");
                    }
                    if (schedule.getUpTime() == null || schedule.getUpTime().isEmpty()) {
                        return new Response(false, "Thời gian uptin trong ngày chưa được cấu hình");
                    }
                }
            }

            List<Item> items = itemRepository.get(itemIds);
            if (scheduleForm.getType() != 0) {
                return this.upSchedule(scheduleForm.getUpSchedule(), items);
            }
            return this.upNow(scheduleForm.getUpSchedule(), items);

        } catch (Exception e) {
            return new Response(false, "Tài khoản của bạn không đủ xèng để thực hiện chức năng này");
        }
    }

    /**
     * Cài đặt up tin ngay
     *
     * @param uses
     * @return
     */
    private Response upNow(List<UpSchedule> uses, List<Item> items) {

        CashTransaction cashTransaction = new CashTransaction();
        cashTransaction.setSpentQuantity(items.size());
        cashTransaction.setSpentId("upNow");
        cashTransaction.setAmount(1000);
        cashTransaction.setUserId(uses.get(0).getSellerId());

        try {
            Response trans = cashService.createSpentUpItem(cashTransaction);
            if (!trans.isSuccess()) {
                return trans;
            }
            Date now = new Date();
            for (Item item : items) {
                item.setUpTime(System.currentTimeMillis());
            }
            searchIndexService.processIndexPageItem(items);
            UpScheduleHistory history = null;
            for (UpSchedule upSchedule : uses) {
                history = new UpScheduleHistory();
                history.setCreateTime(now.getTime());
                history.setScheduleTime(now.getTime());
                history.setItemId(upSchedule.getItemId());
                history.setSellerId(upSchedule.getSellerId());
                history.setDone(true);
                historyRepository.save(history);
            }
            return new Response(true, "Sản phẩm đã được đưa lên đầu!");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    /**
     * Up now by sms
     *
     * @param item
     */
    public void upNow(Item item, boolean free) {
        item.setUpTime(System.currentTimeMillis());
        searchIndexService.processIndexItem(item);
        UpScheduleHistory history = new UpScheduleHistory();
        history.setCreateTime(System.currentTimeMillis());
        history.setScheduleTime(System.currentTimeMillis());
        history.setItemId(item.getId());
        history.setSellerId(item.getSellerId());
        history.setDone(true);
        history.setFree(free);
        historyRepository.save(history);
    }

    /**
     * Cài đặt up tin theo lịch
     *
     * @param uses
     * @return
     */
    private Response upSchedule(List<UpSchedule> uses, List<Item> items) {
        int quantity = uses.get(0).getQuantity();
        quantity = quantity * items.size();
        CashTransaction cashTransaction = new CashTransaction();
        cashTransaction.setUserId(uses.get(0).getSellerId());
        cashTransaction.setSpentQuantity(quantity);
        cashTransaction.setSpentId("upSchedule");
        cashTransaction.setAmount(500);
        try {
            Response trans = cashService.createSpentUpItem(cashTransaction);
            if (!trans.isSuccess()) {
                return trans;
            }
            for (UpSchedule upSchedule : uses) {
                upSchedule.setCreateTime(System.currentTimeMillis());
                upSchedule.setId(upScheduleRepository.genId());
                this.getNextTurn(upSchedule);
                upSchedule.setRun(false);
                upSchedule.setDone(false);
                upSchedule.setLock(false);
                upSchedule.setLastTime(System.currentTimeMillis());
                upScheduleRepository.save(upSchedule);
            }
            return new Response(true, "Sản phẩm đã được cài đặt lịch up tin!");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    /**
     * Search upSchedule
     *
     * @param postItem
     * @param userId
     * @return
     */
    public DataPage<UpSchedule> search(PostItemSearch postItem, String userId) {
        Criteria cri = new Criteria();
        cri.and("sellerId").is(userId);
        if (postItem.getRun() == 1 && postItem.getDone() == 3) {
            cri.and("run").is(true);
            cri.and("done").is(false);
        }
        if (postItem.getRun() == 2) {
            cri.and("run").is(false);
        }
        if (postItem.getDone() == 1) {
            cri.and("done").is(true);
        }
        DataPage<UpSchedule> postPage = new DataPage<>();
        postPage.setDataCount(upScheduleRepository.count(new Query(cri)));
        postPage.setPageSize(postItem.getPageSize() < 1 ? 0 : postItem.getPageSize());
        postPage.setPageIndex(postItem.getPageIndex() < 0 ? 0 : postItem.getPageIndex());
        postPage.setPageCount(postPage.getDataCount() / postItem.getPageSize());
        if (postPage.getDataCount() % postItem.getPageSize() != 0) {
            postPage.setPageCount(postPage.getPageCount() + 1);
        }
        List<UpSchedule> list = upScheduleRepository.list(cri, postItem.getPageSize(), postItem.getPageIndex() * postItem.getPageSize());
        postPage.setData(list);
        return postPage;
    }

    /**
     *
     * @param postItem
     * @param userId
     * @return
     */
    public DataPage<UpScheduleHistory> searchHistory(PostItemSearch postItem, String userId) {
        Criteria cri = new Criteria();
        if (postItem.getStartTime() > 0 && postItem.getEndTime() > 0) {
            cri.and("createTime").gte(postItem.getStartTime()).lt(postItem.getEndTime());
        } else if (postItem.getStartTime() > 0) {
            cri.and("createTime").gte(postItem.getStartTime());
        } else if (postItem.getEndTime() > 0) {
            cri.and("createTime").lt(postItem.getEndTime());
        }
        cri.and("sellerId").is(userId);
        DataPage<UpScheduleHistory> postPage = new DataPage<>();
        postPage.setDataCount(historyRepository.count(new Query(cri)));
        postPage.setPageIndex(postItem.getPageIndex());
        postPage.setPageSize(postItem.getPageSize());
        postPage.setPageCount(postPage.getDataCount() / postItem.getPageSize());
        if (postPage.getDataCount() % postItem.getPageSize() != 0) {
            postPage.setPageCount(postPage.getPageCount() + 1);
        }
        List<UpScheduleHistory> list = historyRepository.list(cri, postItem.getPageSize(), postItem.getPageIndex() * postItem.getPageSize());
        postPage.setData(list);
        return postPage;
    }

    /**
     *
     * @param ids
     * @throws Exception
     */
    public void removesByIds(List<String> ids) throws Exception {
        for (String id : ids) {
            UpSchedule find = upScheduleRepository.find(id);
            if (find == null) {
                throw new Exception("Không tồn tại lịch tin up");
            }
            upScheduleRepository.delete(id);
        }
    }

    /**
     *
     * @param upScheduleId
     * @return
     */
    public Response getByUpSchedule(String upScheduleId) {
        List<UpScheduleHistory> byUpScheduleId = historyRepository.getByUpScheduleId(upScheduleId);
        return new Response(true, "Danh sách lịch sử", byUpScheduleId);
    }

    /**
     *
     * @param startTime
     * @param endTime
     * @param sellerId
     * @return
     */
    public long upFreeDay(long startTime, long endTime, String sellerId) {
        return historyRepository.totalUpFreeByDay(startTime, endTime, sellerId);
    }
}
