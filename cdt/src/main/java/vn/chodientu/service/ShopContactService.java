package vn.chodientu.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.ShopContact;
import vn.chodientu.entity.form.UserContactForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.ShopContactRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;

/**
 * @since Jun 6, 2014
 * @author Phuongdt
 */
@Service
public class ShopContactService {
    
    @Autowired
    private ShopContactRepository shopContactRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Validator validator;
    
    @Async
    public void migrate(List<ShopContact> shopContacts) {
        for (ShopContact shopnew : shopContacts) {
            if (userRepository.exists(shopnew.getSellerId())) {
                shopContactRepository.save(shopnew);
            }
            
        }
    }
    
    public List<ShopContact> getContactById(String id) {
        return shopContactRepository.getShopContactByIdUser(id);
    }

    /**
     * Xóa user hỗ trợ theo id
     *
     * @param id
     */
    public void del(String id) {
        if (shopContactRepository.exists(id)) {
            shopContactRepository.delete(id);
        }
    }

    /**
     * get thông tin user hỗ trợ theo id
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ShopContact getContact(String id) throws Exception {
        ShopContact shopContact = shopContactRepository.find(id);
        if (shopContact == null) {
            throw new Exception("Không tồn tại user hỗ trợ này!");
        }
        return shopContact;
    }

    /**
     * Thêm mới user hỗ trợ cho shop trên online
     *
     * @param form
     * @return
     */
    public Response addUserContact(UserContactForm form) {
        Map<String, String> error = validator.validate(form);
        if (TextUtils.validatePhoneNumber(form.getPhone()) == false) {
            error.put("phone", "Số điện thoại phải là số dài 8-11 kí tự và bắt đầu bằng số 0");
        }
        if (error.isEmpty()) {
            ShopContact contact = new ShopContact();
            contact.setId(shopContactRepository.genId());
            contact.setTitle(form.getTitle());
            contact.setPhone(form.getPhone());
            contact.setSkype(form.getSkype());
            contact.setYahoo(form.getYahoo());
            contact.setEmail(form.getEmail());
            contact.setSellerId(form.getSellerId());
            shopContactRepository.save(contact);
            return new Response(true, "Thêm user hỗ trợ thành công!", contact);
        } else {
            return new Response(false, "Thông tin không hợp lệ!", error);
        }
    }
    
    public Response editUserContact(UserContactForm form) {
        Map<String, String> error = validator.validate(form);
        ShopContact userContact = shopContactRepository.find(form.getId());
        if (userContact == null) {
            return new Response(false, "Không tìm thấy user hỗ trợ này!", error);
        }
        if (TextUtils.validatePhoneNumber(form.getPhone()) == false) {
            error.put("phone", "Số điện thoại phải là số dài 8-11 kí tự và bắt đầu bằng số 0");
        }
        if (error.isEmpty()) {
            userContact.setTitle(form.getTitle());
            userContact.setSkype(form.getSkype());
            userContact.setPhone(form.getPhone());
            userContact.setYahoo(form.getYahoo());
            userContact.setEmail(form.getEmail());
            shopContactRepository.save(userContact);
            return new Response(true, "Sửa user hỗ trợ thành công!", userContact);
        }
        return new Response(false, "Có lỗi sảy ra trong quá trình truyền dữ liệu,dữ liệu nhập chưa đúng!", error);
    }
}
