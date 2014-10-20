package vn.chodientu.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.Lading;
import vn.chodientu.entity.enu.ShipmentStatus;
import vn.chodientu.entity.input.LadingSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.repository.LadingRepository;

/**
 * @since Jul 14, 2014
 * @author Account
 */
@Service
public class LadingService {

    @Autowired
    private LadingRepository ladingRepository;

    public DataPage<Lading> search(LadingSearch ladingSearch) {
        Criteria cri = this.buildCriteria(ladingSearch);
        DataPage<Lading> dataPage = new DataPage<>();
        Query query = new Query(cri);
        dataPage.setDataCount(ladingRepository.count(new Query(cri)));
        dataPage.setPageIndex(ladingSearch.getPageIndex());
        dataPage.setPageSize(ladingSearch.getPageSize());
        dataPage.setPageCount(dataPage.getDataCount() / dataPage.getPageSize());
        if (dataPage.getDataCount() % ladingSearch.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        query.with(new PageRequest(ladingSearch.getPageIndex(), ladingSearch.getPageSize(), new Sort(Sort.Direction.DESC, "scId")));
        dataPage.setData(ladingRepository.find(query));
        return dataPage;

    }

    /**
     * Tính tổng tiền theo điều kiện
     *
     * @param ladingSearch
     * @return
     */
    public Map<String, Long> sumPrice(LadingSearch ladingSearch) {
        return ladingRepository.sumCodPrice(this.buildCriteria(ladingSearch));
    }

    public Criteria buildCriteria(LadingSearch ladingSearch) {
        Criteria cri = new Criteria();
        if (ladingSearch.getScId() != null && !ladingSearch.getScId().equals("")) {
            cri.and("scId").is(ladingSearch.getScId());
        }
        if (ladingSearch.getTimeFrom() > 0 || ladingSearch.getTimeTo() > 0) {
            if (ladingSearch.getTimeFrom() > 0 && ladingSearch.getTimeTo() > ladingSearch.getTimeFrom()) {
                cri.and("createTime").lte(ladingSearch.getTimeTo()).gte(ladingSearch.getTimeFrom());
            } else if (ladingSearch.getTimeFrom() > 0) {
                cri.and("createTime").gte(ladingSearch.getTimeFrom());
            } else {
                cri.and("createTime").lte(ladingSearch.getTimeTo());
            }
        }
        if (ladingSearch.getReceiverEmail() != null && !ladingSearch.getReceiverEmail().equals("")) {
            cri.and("receiverEmail").is(ladingSearch.getReceiverEmail());
        }
        if (ladingSearch.getReceiverName() != null && !ladingSearch.getReceiverName().equals("")) {
            cri.and("receiverName").regex(ladingSearch.getReceiverName());
        }
        if (ladingSearch.getReceiverPhone() != null && !ladingSearch.getReceiverPhone().equals("")) {
            cri.and("receiverPhone").is(ladingSearch.getReceiverPhone());
        }
        if (ladingSearch.getSellerPhone() != null && !ladingSearch.getSellerPhone().equals("")) {
            cri.and("sellerPhone").is(ladingSearch.getSellerPhone());
        }
        if (ladingSearch.getSellerName() != null && !ladingSearch.getSellerName().equals("")) {
            cri.and("sellerName").regex(ladingSearch.getSellerName());
        }
        if (ladingSearch.getShipmentStatus() > 0) {
            switch (ladingSearch.getShipmentStatus()) {
                case 1:
                    cri.and("shipmentStatus").is(ShipmentStatus.NEW.toString());
                    break;
                case 2:
                    cri.and("shipmentStatus").is(ShipmentStatus.STOCKING.toString());
                    break;
                case 3:
                    cri.and("shipmentStatus").is(ShipmentStatus.DELIVERING.toString());
                    break;
                case 4:
                    cri.and("shipmentStatus").is(ShipmentStatus.RETURN.toString());
                    break;
                case 5:
                    cri.and("shipmentStatus").is(ShipmentStatus.DENIED.toString());
                    break;
                case 6:
                    cri.and("shipmentStatus").is(ShipmentStatus.DELIVERED.toString());
                    break;
                default:

            }
        }
        return cri;

    }
}
