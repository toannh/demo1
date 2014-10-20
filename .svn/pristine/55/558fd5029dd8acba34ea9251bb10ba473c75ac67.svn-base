package vn.chodientu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.api.Request;
import vn.chodientu.entity.db.SellerApi;
import vn.chodientu.entity.db.User;
import vn.chodientu.repository.SellerApiRepository;
import vn.chodientu.repository.UserRepository;

public class BaseApi extends BaseRest {

    @Autowired
    private SellerApiRepository sellerApiRepository;
    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addIndexGlobalAttr(ModelMap map) {
    }

    protected void validate(Request data, ModelMap map) throws Exception {

        if (data == null || data.getEmail() == null || data.getCode() == null || data.getParams() == null) {

            throw new Exception("Thông tin điền chưa chính xác");
        }
        User user = userRepository.getByEmail(data.getEmail());
        if (user == null) {

            throw new Exception("Không tìm thấy tài khoản yêu cầu " + data.getEmail());
        }
        if (!user.isActive()) {
            throw new Exception("Tài khoản " + data.getEmail() + " hiện đang bị khóa");
        }
        SellerApi sellerApi = sellerApiRepository.find(data.getCode().trim().toLowerCase(), user.getId());
        if (sellerApi == null) {
            throw new Exception("Mã code chưa được tạo trên hệ thống ChoDienTu.Vn");
        }
        if (!sellerApi.getCode().equals(sellerApi.getCode())) {
            throw new Exception("Mã code không chính xác");
        }
        map.put("user", user);
        map.put("sellerApi", sellerApi);
    }

}
