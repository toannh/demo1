package vn.chodientu.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.SellerSupportFee;
import vn.chodientu.entity.input.SellerSupportFeeSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.SellerRepository;
import vn.chodientu.repository.SellerSupportFeeRepository;
import vn.chodientu.repository.UserRepository;

@Service
public class SellerSupportFeeService {

    @Autowired
    private SellerSupportFeeRepository supportFeeRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private Viewer viewer;

    /**
     * Danh sách hỗ trợ phí vận chuyển theo điều kiện seach
     *
     * @param search
     * @return
     */
    public DataPage<SellerSupportFee> search(SellerSupportFeeSearch search) {
        DataPage<SellerSupportFee> dataPage = new DataPage<>();
        Criteria cri = new Criteria();
        if (search.getSellerId() != null && !search.getSellerId().equals("")) {
            cri.and("sellerId").is(search.getSellerId());
        }
        if (search.getActive() > 0) {
            cri.and("active").is(search.getActive() == 1);
        }
        if (search.getType() != null) {
            cri.and("type").is(search.getType());
        }

        Query query = new Query(cri);
        dataPage.setDataCount(supportFeeRepository.count(query));
        dataPage.setPageIndex(search.getPageIndex());
        dataPage.setPageSize(search.getPageSize());
        dataPage.setPageCount(dataPage.getPageCount());

        query.with(new PageRequest(search.getPageIndex(), search.getPageSize()));
        dataPage.setData(supportFeeRepository.find(query.with(new Sort(Sort.Direction.ASC, "minOrderPrice"))));
        dataPage.setPageCount(dataPage.getDataCount() / dataPage.getPageSize());
        if (dataPage.getDataCount() % dataPage.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        return dataPage;
    }

    /**
     * Thêm mới hỗ trợ phí thanh toán và vận chuyển
     *
     * @param sellerSupportFee
     * @return
     */
    public Response add(SellerSupportFee sellerSupportFee) {
        Map<String, String> errors = validator.validate(sellerSupportFee);
        if (sellerSupportFee.getMinOrderPrice() <= 0) {
            errors.put("minOrderPrice", "Giá trị hóa đơn không được để trống");
        }
        if (!this.getByOrderPrice(sellerSupportFee.getMinOrderPrice()).isEmpty()) {
            errors.put("minOrderPrice", "Giá trị hóa đơn đã có rồi");
        }
        if (!errors.isEmpty()) {
            return new Response(false, null, errors);
        } else {
            SellerSupportFee supportFee = new SellerSupportFee();
            supportFee.setId(supportFeeRepository.genId());
            supportFee.setMinOrderPrice(sellerSupportFee.getMinOrderPrice());
            supportFee.setOrder(sellerSupportFee.getOrder());
            supportFee.setSellerId(viewer.getUser().getId());
            supportFee.setType(sellerSupportFee.getType());
            supportFee.setCreateTime(new Date().getTime());
            supportFee.setActive(true);
            supportFee.setDiscountPercent(sellerSupportFee.getDiscountPercent());
            supportFee.setDiscountPrice(sellerSupportFee.getDiscountPrice());
            supportFeeRepository.save(supportFee);
            return new Response(true, "Đã thêm thành công", supportFee);
        }

    }

    /**
     * Xóa hỗ trợ ví thanh toán vận chuyển
     *
     * @param id
     * @return 
     * @throws java.lang.Exception
     */
    public Response del(String id) throws Exception {
        SellerSupportFee supportFee = supportFeeRepository.find(id);
        if (supportFee == null) {
            throw new Exception("Không tìm thấy hỗ trợ thanh toán or vận chuyển");
        }
        supportFeeRepository.delete(id);
        return new Response(true, null, id);
    }

    /**
     * Lấy hỗ trợ thanh toán theo giá trị hóa đờ
     *
     * @param price
     * @return
     */
    public List<SellerSupportFee> getByOrderPrice(double price) {
        return supportFeeRepository.find(new Query(new Criteria("minOrderPrice").is(price)));
    }

}
