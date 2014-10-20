package vn.chodientu.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.SellerFollow;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.input.SellerFollowSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.SellerFollowRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;

@Service
public class SellerFollowService {

    @Autowired
    private SellerFollowRepository sellerFollowRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Viewer viewer;

    /**
     * Đếm số quan tâm theo người bán
     *
     * @param sellerId
     * @return
     */
    public long countByItem(String sellerId) {
        return sellerFollowRepository.countBySellerId(sellerId);
    }

    /**
     * hành động quan tâm hoặc bỏ quan tâm
     *
     * @param sellerId
     * @param request
     * @return
     * @throws Exception
     */
    public long action(String sellerId, HttpServletRequest request) throws Exception {
        User seller = userRepository.find(sellerId);
        if (seller == null) {
            throw new Exception("Người bán không tồn tại trên hệ thống ChoDienTu");
        }
        if (viewer.getUser() == null) {
            throw new Exception("Bạn cần đăng nhập để thực hiện thao tác này ");
        }
        SellerFollow interest = sellerFollowRepository.find(viewer.getUser().getId(), sellerId);
        if (interest == null) {
            interest = new SellerFollow();
            interest.setId(sellerFollowRepository.genId());
            interest.setActive(true);
            interest.setCreateTime(System.currentTimeMillis());
            interest.setSellerId(seller.getId());
            interest.setUserId(viewer.getUser().getId());
            interest.setSellerName(seller.getName());
        } else {
            interest.setActive(!interest.isActive());
        }
        interest.setUpdateTime(System.currentTimeMillis());
        interest.setIp(TextUtils.getClientIpAddr(request));
        sellerFollowRepository.save(interest);
        return this.countByItem(sellerId);
    }

    /**
     * Xóa người bán theo dõi theo danh sách id
     *
     * @param sellerIds
     */
    public void remove(List<String> sellerIds) {
        sellerFollowRepository.remove(sellerIds, viewer.getUser().getId());
    }

    /**
     * Ghi chú
     *
     * @param id
     * @param note
     * @return
     * @throws Exception
     */
    public SellerFollow note(String id, String note) throws Exception {
        SellerFollow interest = sellerFollowRepository.find(id);
        if (interest == null) {
            throw new Exception("Không tìm thấy người bán quan tâm bạn yêu cầu");
        }
        interest.setNote(note);
        interest.setUpdateTime(System.currentTimeMillis());
        sellerFollowRepository.save(interest);
        return interest;
    }

    /**
     * Tìm kiếm sản phẩm đang quan tâm của người dùng
     *
     * @param search
     * @return
     */
    public DataPage<SellerFollow> search(SellerFollowSearch search) {
        DataPage<SellerFollow> dataPage = new DataPage<>();
        Criteria cri = new Criteria();
        if (search.getUserId() != null && !search.getUserId().equals("")) {
            cri.and("userId").is(search.getUserId());
        }
        if (search.getSellerId() != null && !search.getSellerId().equals("")) {
            cri.and("sellerId").is(search.getSellerId());
        }
        Query query = new Query(cri);
        dataPage.setDataCount(sellerFollowRepository.count(query));
        query.with(new PageRequest(search.getPageIndex(), search.getPageSize()));
        List<SellerFollow> list = sellerFollowRepository.find(query);
        dataPage.setPageIndex(search.getPageIndex());
        dataPage.setPageSize(search.getPageSize());
        dataPage.setPageCount(dataPage.getPageCount());
        dataPage.setData(list);
        dataPage.setPageCount(dataPage.getDataCount() / dataPage.getPageSize());
        if (dataPage.getDataCount() % dataPage.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        return dataPage;
    }

}
