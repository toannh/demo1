package vn.chodientu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.SellerApi;
import vn.chodientu.entity.db.User;
import vn.chodientu.repository.SellerApiRepository;
import vn.chodientu.repository.UserRepository;

@Service
public class SellerApiService {

    @Autowired
    private SellerApiRepository sellerApiRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Tạo mã key
     *
     * @return
     */
    private String getCode() {
        return sellerApiRepository.genId();
    }

    /**
     * lấy thông tin theo code
     *
     * @param code
     * @return
     * @throws Exception
     */
    private SellerApi find(String code) throws Exception {
        SellerApi sellerApi = sellerApiRepository.find(code, null);
        if (sellerApi == null) {
            throw new Exception("Không tìm thấy mã đăng ký");
        }
        return sellerApi;
    }

    /**
     * Tạo code API cho seller
     *
     * @param userId
     * @return
     * @throws java.lang.Exception
     */
    public SellerApi createApiCode(String userId) throws Exception {
        User user = userRepository.find(userId);
        if (user == null) {
            throw new Exception("Người dùng không tồn tại");
        }
        String code = this.getCode();
        SellerApi sellerApi = sellerApiRepository.findByUserId(userId);
        if (sellerApi == null) {
            sellerApi = new SellerApi();
            sellerApi.setUserId(userId);
            sellerApi.setCreateTime(System.currentTimeMillis());
        }
        sellerApi.setCode(code);
        sellerApi.setUpdateTime(System.currentTimeMillis());
        sellerApiRepository.save(sellerApi);
        return sellerApi;
    }

    /**
     * Danh sách API người bán lấy theo userIds
     *
     * @param userIds
     * @return
     */
    public List<SellerApi> findSellerAPIByUserIds(List<String> userIds) {
        return sellerApiRepository.find(userIds);
    }

}
