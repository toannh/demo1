/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.VipItem;
import vn.chodientu.entity.form.VipItemForm;
import vn.chodientu.entity.input.VipItemSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.CashRepository;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.VipItemRepository;

/**
 *
 * @author Linhnt
 */
@Service
public class VipItemService {

    @Autowired
    private VipItemRepository vipItemRepository;
    @Autowired
    private CashService cashService;
    @Autowired
    private CashRepository cashRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private Viewer viewer;
    private final long amounts = 30000;

    public Response add(List<VipItemForm> vipItemForms) throws Exception {
        Map<String, String> error = new HashMap<>();
        boolean fag = true;
        SimpleDateFormat dt = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        Calendar c = Calendar.getInstance();

        long timeNow = new Date().getTime();
        c.setTime(new Date(timeNow));
        long dayTime = dt.parse("00:00 " + c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR)).getTime();

        for (VipItemForm vipitem : vipItemForms) {
            c.setTime(new Date(vipitem.getFrom()));
            long startTime = dt.parse("00:00 " + c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR)).getTime();

            if (vipitem.getTo() < vipitem.getFrom()) {
                error.put("error_Time" + vipitem.getItemId(), "Thời gian kết thúc phải lớn hơn thời gian bắt đầu");
            }
            if (vipitem.getFrom() < timeNow && startTime != dayTime) {
                error.put("error_Time" + vipitem.getItemId(), "Thời gian bắt đầu phải lớn hơn hoặc bằng thời gian hiện tại");
            }
            if (vipitem.getTo() < timeNow) {
                error.put("error_Time" + vipitem.getItemId(), "Thời gian kết thúc phải lớn hơn hoặc bằng thời gian hiện tại");
            }

            long cash = cashRepository.getCash(viewer.getUser().getId()).getBalance();
            int totalTime = (int) Math.round((vipitem.getTo() - vipitem.getFrom()) / (double) 86400000);
            totalTime = (totalTime == 0) ? 1 : totalTime;

            long monney = amounts * totalTime;
            if (cash < monney) {
                error.put("error_Monney", "Tài khoản không đủ để thực hiện giao dịch");
            }
            CashTransaction cashTransaction = new CashTransaction();
            if (error.isEmpty()) {
                VipItem vItem = new VipItem();
                Item items = itemRepository.find(vipitem.getItemId());
                vItem.setCategoryPath(items.getCategoryPath());
                String genId = vipItemRepository.genId();
                vItem.setId(genId);
                vItem.setFrom(vipitem.getFrom());
                vItem.setTo(vipitem.getTo());
                vItem.setSellerId(viewer.getUser().getId());
                vItem.setItemId(vipitem.getItemId());
                vItem.setActive(true);
                vItem.setCreateTime(timeNow);
                vipItemRepository.save(vItem);

                cashTransaction.setSpentId(genId);
                cashTransaction.setSpentQuantity(totalTime);
                cashTransaction.setAmount(amounts);
                cashTransaction.setUserId(viewer.getUser().getId());
                cashService.createSpentVipItem(cashTransaction);
            } else {
                fag = false;
            }
        }
        if (fag) {
            return new Response(true, "Đã thêm thành công");
        } else {
            return new Response(false, "Có lỗi đăng tin", error);
        }
    }

    /**
     * Tìm kiếm uptinVip sản phẩm
     *
     * @param vipItemSearch
     * @param extraPage luôn luôn cộng thêm 1 page để load thêm quảng cáo vip
     * trong trang browse
     * @return
     */
    public DataPage<VipItem> search(VipItemSearch vipItemSearch, boolean extraPage) {
        Criteria cri = new Criteria();
        if (vipItemSearch.getUserId() != null && !vipItemSearch.getUserId().equals("")) {
            cri.and("sellerId").is(vipItemSearch.getUserId());
        }
        if (vipItemSearch.getActive() > 0) {
            cri.and("active").is(vipItemSearch.getActive() == 1);
            cri.and("from").lte(System.currentTimeMillis());
            cri.and("to").gte(System.currentTimeMillis());
        }
        if (vipItemSearch.getCategoryId() != null && !vipItemSearch.getCategoryId().equals("")) {
            cri.and("categoryPath").is(vipItemSearch.getCategoryId());
        }
        DataPage<VipItem> postPage = new DataPage<>();
        postPage.setDataCount(vipItemRepository.count(new Query(cri)));
        postPage.setPageIndex(vipItemSearch.getPageIndex());
        postPage.setPageSize(vipItemSearch.getPageSize());
        postPage.setPageCount(postPage.getDataCount() / vipItemSearch.getPageSize());
        if (extraPage && postPage.getDataCount() > 0) {
            postPage.setPageCount(postPage.getPageCount() + 1);
        }
        if (!extraPage && postPage.getDataCount() % postPage.getPageSize() != 0) {
            postPage.setPageCount(postPage.getPageCount() + 1);
        }
        List<VipItem> list = vipItemRepository.list(cri, vipItemSearch.getPageSize(), vipItemSearch.getPageIndex() * vipItemSearch.getPageSize());
        postPage.setData(list);
        return postPage;
    }

}
