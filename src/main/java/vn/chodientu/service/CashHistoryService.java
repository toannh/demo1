package vn.chodientu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.db.CashHistory;
import vn.chodientu.entity.input.CashHistorySearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.CashHistoryRepository;
import vn.chodientu.repository.CashRepository;
import vn.chodientu.repository.CashTransactionRepository;
import vn.chodientu.util.TextUtils;

/**
 * @since May 20, 2014
 * @author PhuongDT
 */
@Service
public class CashHistoryService {

    @Autowired
    private CashHistoryRepository cashHistoryRepository;
    @Autowired
    private CashRepository cashRepository;
    @Autowired
    private CashTransactionRepository cashTransactionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private Viewer viewer;

    /**
     * Hành động phạt xèng , đến lần t4 thì trừ toàn bộ số xèng của tài khoản
     *
     * @param id
     * @param note
     * @param unAppro
     * @return
     * @throws Exception
     */
    public CashHistory fine(String id, String note, boolean unAppro) throws Exception {
        CashHistory cashHistory = cashHistoryRepository.find(id);
        if (cashHistory == null) {
            throw new Exception("Không tìm thấy lịch sử cộng xèng theo yêu cầu");
        }
        if (cashHistory.isFine() || cashHistory.isUnAppro()) {
            throw new Exception("Hành động bạn yêu cầu đã bị phạt xèng hoặc không duyệt");
        }

        long firstDayOfWeek = TextUtils.firstDayOfWeek(System.currentTimeMillis());
        long lastDayOfWeek = TextUtils.lastDayOfWeek(System.currentTimeMillis());
        long turn = cashHistoryRepository.totalCash(firstDayOfWeek, lastDayOfWeek, cashHistory.getUserId(), null, 1);
        turn += 1;
        CashTransaction cashTransaction = new CashTransaction();
        cashTransaction.setId(cashTransactionRepository.genId());
        cashTransaction.setTime(System.currentTimeMillis());
        cashTransaction.setType(CashTransactionType.PANALTY_BLANCE);
        cashTransaction.setSpentQuantity(1);
        cashTransaction.setUserId(cashHistory.getUserId());
        cashTransaction.setAmount(cashHistory.getFineBalance());

        Cash cash = cashRepository.getCash(cashHistory.getUserId());
        if (unAppro) {
            //Đánh dấu không duyệt
            cashHistory.setUnAppro(true);
            cashTransaction.setAmount(cashHistory.getBalance());
            long monney = cashTransaction.getAmount() * cashTransaction.getSpentQuantity() * -1;
            Cash topupPaymentDone = cashRepository.topupPaymentDone(cashTransaction.getUserId(), monney);
            cashTransaction.setNewBalance(topupPaymentDone.getBalance());
            cashTransactionRepository.save(cashTransaction);
        } else if (turn <= 3) {
            //phạt số lần * 100 xèng
            if (turn == 2) {
                cashTransaction.setAmount(600);
            } else if (turn == 3) {
                cashTransaction.setAmount(1000);
            } else {
                cashTransaction.setAmount(300);
            }

            long monney = cashTransaction.getAmount() * cashTransaction.getSpentQuantity() * -1;
            note += ", bị phạt " + monney + " (còn " + cash.getBalance() + ") vì vi phạm lần " + turn + " trong 1 tuần (" + firstDayOfWeek + "-" + lastDayOfWeek + ")";
            Cash topupPaymentDone = cashRepository.topupPaymentDone(cashTransaction.getUserId(), monney);
            cashTransaction.setNewBalance(topupPaymentDone.getBalance());
            cashTransactionRepository.save(cashTransaction);

            //Đánh dấu đã phạt
            cashHistory.setFine(true);
        } else {
            //Lần thứ 3 trở đi bị block tài khoản 7 ngày
            note += ", vi phạm lần thứ " + turn + " bị block tài khoản 1 tuần";
            userService.userLock(cashHistory.getUserId(), 0, 7 * 24, "Vi phạm hành vi kiếm xèng lần " + turn);

            //Đánh dấu đã phạt
            cashHistory.setFine(true);
        }

        cashHistory.setNote(note);
        cashHistory.setAdmin(viewer.getAdministrator().getEmail());
        cashHistoryRepository.save(cashHistory);
        return cashHistory;
    }

    /**
     * Lấy danh sách lịch sử hành vi kiếm xèng và trừ xèng
     *
     * @param cashHistorySearch
     * @return
     */
    public DataPage<CashHistory> search(CashHistorySearch cashHistorySearch) {
        Criteria cri = new Criteria();
        if (cashHistorySearch.getUserId() != null && !cashHistorySearch.getUserId().equals("")) {
            cri.and("userId").is(cashHistorySearch.getUserId());
        }
        if (cashHistorySearch.getAdmin() != null && !cashHistorySearch.getAdmin().equals("")) {
            cri.and("admin").is(cashHistorySearch.getAdmin());
        }
        if (cashHistorySearch.getObjectId() != null && !cashHistorySearch.getObjectId().equals("")) {
            cri.and("objectId").is(cashHistorySearch.getObjectId());
        }
        if (cashHistorySearch.getCashTransactionId() != null && !cashHistorySearch.getCashTransactionId().equals("")) {
            cri.and("cashTransactionId").is(cashHistorySearch.getCashTransactionId());
        }
        if (cashHistorySearch.getType() != null) {
            cri.and("type").is(cashHistorySearch.getType().toString());
        }
        if (cashHistorySearch.getEndTime() <= cashHistorySearch.getStartTime()) {
            if (cashHistorySearch.getStartTime() > 0) {
                cri.and("createTime").gte(cashHistorySearch.getStartTime());
            }
        } else if (cashHistorySearch.getEndTime() > cashHistorySearch.getStartTime()) {
            cri.and("createTime").gte(cashHistorySearch.getStartTime()).lte(cashHistorySearch.getEndTime());
        }
        Query query = new Query(cri);
        query.with(new Sort(Sort.Direction.DESC, "createTime"));
        query.skip(cashHistorySearch.getPageIndex() * cashHistorySearch.getPageSize()).limit(cashHistorySearch.getPageSize());
        DataPage<CashHistory> page = new DataPage<>();
        page.setPageSize(cashHistorySearch.getPageSize());
        page.setPageIndex(cashHistorySearch.getPageIndex());
        if (page.getPageSize() <= 0) {
            page.setPageSize(1);
        }

        page.setDataCount(cashHistoryRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }

        page.setData(cashHistoryRepository.find(query));
        return page;
    }

    /**
     * Số lần đã bị phạt
     *
     * @param id
     * @return
     * @throws Exception
     */
    public long getfine(String id) throws Exception {
        CashHistory cashHistory = cashHistoryRepository.find(id);
        if (cashHistory == null) {
            throw new Exception("Không tìm thấy lịch sử cộng xèng theo yêu cầu");
        }
        long firstDayOfWeek = TextUtils.firstDayOfWeek(System.currentTimeMillis());
        long lastDayOfWeek = TextUtils.lastDayOfWeek(System.currentTimeMillis());
        return cashHistoryRepository.totalCash(firstDayOfWeek, lastDayOfWeek, cashHistory.getUserId(), null, 1);
    }

}
