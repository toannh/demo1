package vn.chodientu.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.SellerStock;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.input.SellerStockSearch;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.SellerRepository;
import vn.chodientu.repository.SellerStockRepository;
import vn.chodientu.repository.UserRepository;

@Service
public class SellerStockService {

    @Autowired
    private SellerStockRepository stockRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Validator validator;

    /**
     * Danh sách kho theo điều kiện search
     *
     * @param search
     * @return
     */
    public DataPage<SellerStock> search(SellerStockSearch search) {
        DataPage<SellerStock> dataPage = new DataPage<>();
        Criteria cri = new Criteria();
        if (search.getSellerId() != null && !search.getSellerId().equals("")) {
            cri.and("sellerId").is(search.getSellerId());
        }
        if (search.getActive() > 0) {
            cri.and("active").is(search.getActive() == 1);
        }
        Sort sort = new Sort(Sort.Direction.ASC, "order");
        Query query = new Query(cri);
        dataPage.setDataCount(stockRepository.count(query));
        dataPage.setPageIndex(search.getPageIndex());
        dataPage.setPageSize(search.getPageSize());
        dataPage.setPageCount(dataPage.getPageCount());

        query.with(new PageRequest(search.getPageIndex(), search.getPageSize(), sort));
        dataPage.setData(stockRepository.find(query));
        dataPage.setPageCount(dataPage.getDataCount() / dataPage.getPageSize());
        if (dataPage.getDataCount() % dataPage.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        return dataPage;
    }

    /**
     * Thêm mới kho
     *
     * @param stock
     * @return
     * @throws Exception
     */
    public Response add(SellerStock stock) throws Exception {
        Map<String, String> error = validator.validate(stock);
        User user = userRepository.find(stock.getSellerId());
        if (user == null) {
            throw new Exception("Không tìm thấy người bán trên hệ thống");
        }
        if (!error.isEmpty()) {
            return new Response(false, "Không thể thêm mới kho vào hệ thống, dữ liệu không chính xác", error);
        }
        stock.setActive(true);
        stockRepository.save(stock);
        return new Response(true, "Kho " + stock.getName() + " được thêm vào danh sách kho", stock);
    }

    /**
     * Cập nhật kho
     *
     * @param stock
     * @return
     * @throws Exception
     */
    public Response edit(SellerStock stock) throws Exception {
        if (stock == null) {
            throw new Exception("Dữ liệu chưa chính xác");
        }
        if (!stockRepository.exists(stock.getId())) {
            throw new Exception("Không tìm thấy kho yêu cầu trên hệ thống");
        }
        User user = userRepository.find(stock.getSellerId());
        if (user == null) {
            throw new Exception("Không tìm thấy người bán trên hệ thống");
        }
        Map<String, String> error = validator.validate(stock);
        if (!error.isEmpty()) {
            return new Response(false, "Không thể thêm mới kho vào hệ thống, dữ liệu không chính xác", error);
        }
        stock.setActive(true);
        stockRepository.save(stock);
        return new Response(true, "Kho " + stock.getName() + " được cập nhật vào danh sách kho", stock);
    }

    /**
     * Cập nhật trạng thái active
     *
     * @param stockId
     * @return
     * @throws Exception
     */
    public SellerStock active(String stockId) throws Exception {
        SellerStock stock = stockRepository.find(stockId);
        if (stock == null) {
            throw new Exception("Không tìm thấy kho trên hệ thống");
        }
        stock.setActive(!stock.isActive());
        stockRepository.save(stock);
        return stock;
    }

    /**
     * Xóa kho
     *
     * @param stockId
     * @return
     * @throws Exception
     */
    public Response remove(String stockId) throws Exception {
        SellerStock stock = stockRepository.find(stockId);
        if (stock == null) {
            throw new Exception("Không tìm thấy kho trên hệ thống");
        }
        stockRepository.delete(stock);
        return new Response(true, "Xoá kho thành công!");
    }

    /**
     * Lấy chi tiết 1 kho
     *
     * @param stockId
     * @return
     * @throws Exception
     */
    public SellerStock get(String stockId) throws Exception {
        SellerStock stock = stockRepository.find(stockId);
        if (stock == null) {
            throw new Exception("Không tìm thấy kho trên hệ thống");
        }
        return stock;
    }

    /**
     * Lấy 1 kho theo type
     *
     * @param type
     * @return
     */
    public SellerStock getByType(String sellerId, int type) {
        return stockRepository.findByType(sellerId, type);
    }
}
