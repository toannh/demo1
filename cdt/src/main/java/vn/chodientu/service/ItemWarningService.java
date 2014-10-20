package vn.chodientu.service;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemWarning;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.EmailOutboxType;
import vn.chodientu.entity.enu.ItemWarningType;
import vn.chodientu.entity.input.ItemWarningSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.ItemWarningRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;
import vn.chodientu.util.UrlUtils;

@Service
public class ItemWarningService {

    @Autowired
    private ItemWarningRepository warningRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private Viewer viewer;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    /**
     * Người dùng báo
     *
     * @param type
     * @param id
     * @param request
     * @throws Exception
     */
    public void warning(ItemWarningType type, String id, HttpServletRequest request) throws Exception {
        if (type == null) {
            return;
        }
        Item item = itemRepository.find(id);
        if (item == null) {
            throw new Exception("Sản phẩm không tồn tại trên hệ thống ChoDienTu");
        }
        if (viewer.getUser() == null) {
            throw new Exception("Bạn cần <a href='javascript:;' onclick=\"auth.login('" + UrlUtils.item(item.getId(), item.getName()) + "')\" >đăng nhập</a> để thực hiện thao tác này ");
        }
        User seller = userRepository.find(item.getSellerId());
        if (seller == null) {
            throw new Exception("Người bán không tồn tại trên hệ thống ChoDienTu");
        }

        ItemWarning lastWarning = warningRepository.lastWarning(type, id);
        long limitTime = System.currentTimeMillis() - 2 * 60 * 60 * 1000;
        if (lastWarning != null && lastWarning.getCreateTime() > limitTime) {
            throw new Exception("Bạn vừa báo " + (type == ItemWarningType.OUTOFSTOCK ? "hết hàng" : "sai giá") + " cho sản phẩm này");
        }

        ItemWarning warning = new ItemWarning();
        warning.setCreateTime(System.currentTimeMillis());
        warning.setIp(TextUtils.getClientIpAddr(request));
        warning.setItemId(item.getId());
        warning.setItemName(item.getName());
        warning.setSellerId(item.getSellerId());
        warning.setSellerEmail(seller.getEmail());
        warning.setSellerName(seller.getName() != null ? seller.getName() : (seller.getUsername() != null ? seller.getUsername() : seller.getEmail()));
        warning.setType(type);
        warning.setUserId(viewer.getUser().getId());
        warning.setUserEmail(viewer.getUser().getEmail());
        warning.setUserName(viewer.getUser().getName() != null ? viewer.getUser().getName() : (viewer.getUser().getUsername() != null ? viewer.getUser().getUsername() : viewer.getUser().getEmail()));
        warningRepository.save(warning);
        sendMail(warning);
    }

    /**
     * Gửi mail cho người bán
     *
     * @param warning
     */
    private void sendMail(ItemWarning warning) {
        EmailOutboxType type = EmailOutboxType.WARNING_OUTOFSTOCK;
        String subject = "[ChoDienTu] Bạn " + warning.getUserName() + " báo sản phẩm " + warning.getItemName() + " đã hết hàng";
        String message = "Sản phẩm <a href='http://chodientu.vn/" + UrlUtils.item(warning.getItemId(), warning.getItemName()) + "'>" + warning.getItemName() + "</a> đã hết hàng trên hệ thống ChợĐiệnTử. Đề nghị quý khách vào quản trị sửa lại thông tin sản phẩm";
        if (warning.getType() == ItemWarningType.WRONG_PRICE) {
            type = EmailOutboxType.WARNING_WRONG_PRICE;
            subject = "[ChoDienTu] Bạn " + warning.getUserName() + " báo sản phẩm " + warning.getItemName() + " bị sai giá";
            message = "Sản phẩm <a href='http://chodientu.vn/" + UrlUtils.item(warning.getItemId(), warning.getItemName()) + "'>" + warning.getItemName() + "</a> đã bị sai giá trên hệ thống ChợĐiệnTử. Đề nghị quý khách vào quản trị sửa lại thông tin sản phẩm";
        }
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("username", warning.getSellerName());
        data.put("message", message);
        try {
            emailService.send(type, warning.getSellerEmail(), subject, "message", data);
        } catch (Exception ex) {
        }
    }

    /**
     * Tìm kiếm tất cả cảnh báo
     *
     * @param search
     * @return
     */
    public DataPage<ItemWarning> search(ItemWarningSearch search) {
        DataPage<ItemWarning> dataPage = new DataPage<>();
        Criteria cri = new Criteria();
        if (search.getType() != null && !search.getType().equals("")) {
            cri.and("type").is(search.getType());
        }
        Query query = new Query(cri);
        dataPage.setDataCount(warningRepository.count(query));
        dataPage.setPageIndex(search.getPageIndex());
        dataPage.setPageSize(search.getPageSize());
        dataPage.setPageCount(dataPage.getDataCount() / search.getPageSize());
        if (dataPage.getDataCount() % search.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        dataPage.setData(warningRepository.find(query.limit(search.getPageSize()).skip(search.getPageIndex() * search.getPageSize())));
        return dataPage;
    }

}
