package vn.chodientu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.api.Request;
import vn.chodientu.entity.db.ApiHistory;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.ApiHistoryRepository;

@Service
public class ApiHistoryService {

    @Autowired
    private ApiHistoryRepository apiHistoryRepository;

    public void create(Request data, User user, Response response) {
        try {
            ApiHistory ah = new ApiHistory();
            ah.setCode(data.getCode());
            ah.setData(data.getParams());
            ah.setEmail(data.getEmail());
            ah.setResponse(response);
            if (user != null) {
                ah.setUserId(user.getId());
            }
            apiHistoryRepository.save(ah);
        } catch (Exception e) {
        }

    }
}
