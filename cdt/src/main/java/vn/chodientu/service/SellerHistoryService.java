package vn.chodientu.service;

import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import vn.chodientu.entity.db.SellerHistory;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.SellerHistoryType;
import vn.chodientu.entity.input.SellerHistorySearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.CashTransactionRepository;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.OrderRepository;
import vn.chodientu.repository.SellerHistoryRepository;

@Service
public class SellerHistoryService {

    @Autowired
    private SellerHistoryRepository sellerHistoryRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CashTransactionRepository cashTransactionRepository;
    @Autowired
    private Viewer viewer;

    /**
     *
     * @param type
     * @param objectId
     * @param status
     * @param action
     */
    public void create(SellerHistoryType type, String objectId, boolean status, int action, String userId) {
        if (viewer.getUser() == null) {
            return;
        }

        String id = DigestUtils.md5DigestAsHex((viewer.getUser().getId() + objectId + type.toString() + action).getBytes());
        SellerHistory history = sellerHistoryRepository.find(id);
        if (history == null) {
            history = new SellerHistory();
            history.setId(id);
            history.setCreateTime(System.currentTimeMillis());
            history.setSellerId(viewer.getUser().getId());
            history.setUsername(viewer.getUser().getUsername());
            history.setEmail(viewer.getUser().getEmail());
            history.setPhone(viewer.getUser().getPhone());
            history.setObjectId(objectId);

            history.setMessage("Tài khoản " + history.getEmail());
            if (type == SellerHistoryType.ITEM) {
                history.setFirst(itemRepository.countBySeller(history.getSellerId()) == 0);
                switch (action) {
                    case 2:
                        history.setMessage(history.getMessage() + " cập nhật");
                        break;
                    case 3:
                        history.setMessage(history.getMessage() + " xóa");
                        break;
                    case 1:
                    default:
                        history.setMessage(history.getMessage() + " đăng bán");
                        break;
                }

                if (history.isFirst()) {
                    history.setMessage(history.getMessage() + " lần đầu");
                }
                history.setMessage(history.getMessage() + " vào lúc " + this.converTime(history.getCreateTime()));
            } else if (type == SellerHistoryType.LADING) {
                history.setSellerId(userId);
                history.setFirst(orderRepository.count(new Query(new Criteria("scId").ne(null).and("sellerId").is(history.getSellerId()))) == 0);
                history.setMessage(history.getMessage() + " tạo vận đơn");
                if (history.isFirst()) {
                    history.setMessage(history.getMessage() + " lần đầu");
                }
                history.setMessage(history.getMessage() + " vào lúc " + this.converTime(history.getCreateTime()));
            } else if (type == SellerHistoryType.ORDER) {
                history.setSellerId(userId);
                history.setFirst(orderRepository.count(new Query(new Criteria("sellerId").is(history.getSellerId()))) == 1);
                history.setMessage(history.getMessage() + " có đơn hàng");
                if (history.isFirst()) {
                    history.setMessage(history.getMessage() + " đầu tiên");
                }
                history.setMessage(history.getMessage() + " vào lúc " + this.converTime(history.getCreateTime()));
            } else if (type == SellerHistoryType.PAYMENT) {
                history.setFirst(orderRepository.count(new Query(new Criteria("nlId").ne(null).and("sellerId").is(history.getSellerId()))) == 1);
                history.setMessage(history.getMessage() + " thanh toán");
                if (history.isFirst()) {
                    history.setMessage(history.getMessage() + " đầu tiên");
                }
                history.setMessage(history.getMessage() + " vào lúc " + this.converTime(history.getCreateTime()));
            } else if (type == SellerHistoryType.USER) {
                history.setFirst(false);

                switch (action) {
                    case 2:
                        history.setMessage(history.getMessage() + " đăng xuất");
                        break;
                    case 3:
                        history.setMessage(history.getMessage() + " thay đổi thông tin cá nhân");
                        break;
                    case 4:
                        history.setMessage(history.getMessage() + " lấy lại mật khẩu");
                        break;
                    case 1:
                        history.setMessage(history.getMessage() + " đăng nhập");
                        break;
                    default:
                        history.setMessage(history.getMessage() + " đăng nhập");
                        break;
                }
                history.setMessage(history.getMessage() + " vào lúc " + this.converTime(System.currentTimeMillis()));
            } else if (type == SellerHistoryType.XENG) {
                Criteria criteria = new Criteria("userId").is(history.getSellerId());
                Criteria c1 = new Criteria("type").is(CashTransactionType.TOPUP_NL.toString());
                Criteria c2 = new Criteria("type").is(CashTransactionType.SMS_NAP.toString());
                criteria.orOperator(c1,c2);
                long count = cashTransactionRepository.count(new Query(criteria));
                history.setFirst(count == 1);
                history.setMessage(history.getMessage() + " mua xèng");
                if (history.isFirst()) {
                    history.setMessage(history.getMessage() + " lần đầu");
                }
                history.setMessage(history.getMessage() + " vào lúc " + this.converTime(history.getCreateTime()));
            }
        }

        history.setUpdateTime(System.currentTimeMillis());
        history.setStatus(status);
        sellerHistoryRepository.save(history);
    }

    private String converTime(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        return cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
    }

    /**
     * Tìm kiếm log
     *
     * @param search
     * @return
     */
    public DataPage<SellerHistory> search(SellerHistorySearch search) {
        DataPage<SellerHistory> dataPage = new DataPage<>();
        Criteria cri = new Criteria();
        if (search.getSellerId() != null && !search.getSellerId().equals("")) {
            cri.and("sellerId").is(search.getSellerId());
        }
        if (search.getFirst() > 0) {
            cri.and("first").is(search.getFirst() == 1);
        }
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Query query = new Query(cri);
        dataPage.setDataCount(sellerHistoryRepository.count(query));
        dataPage.setPageIndex(search.getPageIndex());
        dataPage.setPageSize(search.getPageSize());
        dataPage.setPageCount(dataPage.getPageCount());

        query.with(new PageRequest(search.getPageIndex(), search.getPageSize(), sort));
        dataPage.setData(sellerHistoryRepository.find(query));
        dataPage.setPageCount(dataPage.getDataCount() / dataPage.getPageSize());
        if (dataPage.getDataCount() % dataPage.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        return dataPage;
    }

}
