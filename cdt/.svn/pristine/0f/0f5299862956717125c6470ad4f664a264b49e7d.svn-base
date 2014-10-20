package vn.chodientu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.ScHistory;
import vn.chodientu.entity.input.SCHistorySearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.repository.ScHistoryRepository;

/**
 * @since Aug 12, 2014
 * @author Account
 */
@Service
public class SCHistoryService{
    @Autowired
    private ScHistoryRepository scHistoryRepository;
    
    public DataPage search(SCHistorySearch sCHistorySearch){
        
        Criteria cri = new Criteria();
        if (sCHistorySearch.getId() != null && !sCHistorySearch.getId().trim().equals("")) {
            cri.and("id").regex(".*" + sCHistorySearch.getId() + ".*", "i");
        }
        
        DataPage<ScHistory> scHis = new DataPage<>();
        scHis.setDataCount(scHistoryRepository.count(new Query(cri)));
        scHis.setPageIndex(sCHistorySearch.getPageIndex());
        scHis.setPageSize(sCHistorySearch.getPageSize());
        scHis.setPageCount(scHis.getDataCount() / sCHistorySearch.getPageSize());
        if (scHis.getDataCount() % sCHistorySearch.getPageSize() != 0) {
            scHis.setPageCount(scHis.getPageCount() + 1);
        }
        List<ScHistory> list = scHistoryRepository.list(cri, new PageRequest(scHis.getPageIndex(), scHis.getPageSize()));
        scHis.setData(list);
        return scHis;
    }

    public ScHistory get(String id) {
        return scHistoryRepository.find(id);
    }
}
