package vn.chodientu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.chodientu.component.SmsClient;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.SmsInbox;
import vn.chodientu.entity.db.SmsOutbox;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.enu.SmsInboxType;
import vn.chodientu.entity.enu.SmsOutboxType;
import vn.chodientu.entity.input.SmsInboxSearch;
import vn.chodientu.entity.input.SmsOutboxSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.SmsInboxRepository;
import vn.chodientu.repository.SmsOutboxRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;

/**
 * @since May 17, 2014
 * @author Phu
 */
@Service
public class SmsService {

    @Autowired
    private SmsOutboxRepository smsOutboxRepository;
    @Autowired
    private SmsInboxRepository smsInboxRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CashService cashService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UpScheduleService upScheduleService;
    @Autowired
    private SmsClient smsClient;

    @Scheduled(fixedDelay = 60000)
    public void doSend() {
        while (true) {
            SmsOutbox out = smsOutboxRepository.getForSend();
            if (out != null) {
                try {
                    String resp = smsClient.sendSms(out.getContent(), out.getReceiver(), out.getSmsType(), out.getSmsTemp());
                    out.setSuccess(true);
                    out.setResponse(resp);
                } catch (Exception ex) {
                    out.setResponse(ex.getMessage());
                }
                smsOutboxRepository.save(out);
            } else {
                break;
            }
        }
    }

    /**
     * Tìm kiếm sms trong trang tất cả sms
     *
     * @param search
     * @return
     */
    public DataPage<SmsOutbox> search(SmsOutboxSearch search) {
        Criteria criteria = new Criteria();

        if (search.getSuccess() > 0) {
            if (search.getSuccess() == 1) {
                criteria.and("success").is(true);
            } else {
                criteria.and("success").is(false);
            }
        }

        if (search.getSent() > 0) {
            if (search.getSent() == 1) {
                criteria.and("sent").is(true);
            } else {
                criteria.and("sent").is(false);
            }
        }

        if (search.getReceiver() != null && !search.getReceiver().equals("")) {
            criteria.and("receiver").is(search.getReceiver());
        }

        if (search.getType() > 0) {
            switch (search.getType()) {
                case 1:
                    criteria.and("type").is(SmsOutboxType.NEWORDER);
                    break;
                default:
                    criteria.and("type").is(SmsOutboxType.AUTIONWIN);
            }
        }

        if (search.getTimeFrom() > 0 && search.getTimeTo() > 0) {
            criteria.and("time").gte(search.getTimeFrom()).lt(search.getTimeTo());
        } else if (search.getTimeFrom() > 0) {
            criteria.and("time").gte(search.getTimeFrom());
        } else if (search.getTimeTo() > 0) {
            criteria.and("time").lt(search.getTimeTo());
        }

        if (search.getSentTimeFrom() > 0 && search.getSentTimeTo() > 0) {
            criteria.and("sentTime").gte(search.getSentTimeFrom()).lt(search.getSentTimeTo());
        } else if (search.getSentTimeFrom() > 0) {
            criteria.and("sentTime").gte(search.getSentTimeFrom());
        } else if (search.getSentTimeTo() > 0) {
            criteria.and("sentTime").lt(search.getSentTimeTo());
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "sentTime"));
        query.skip(search.getPageIndex() * search.getPageSize()).limit(search.getPageSize());

        DataPage<SmsOutbox> page = new DataPage<>();
        page.setPageSize(search.getPageSize());
        page.setPageIndex(search.getPageIndex());
        if (page.getPageSize() <= 0) {
            page.setPageSize(5);
        }
        page.setDataCount(smsOutboxRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }

        page.setData(smsOutboxRepository.find(query));
        return page;
    }

    /**
     * Yêu cầu gửi lại
     *
     * @param id
     * @return
     */
    public Response reSend(String id) {
        SmsOutbox smsOutBox = smsOutboxRepository.find(id);
        if (smsOutBox == null) {
            return new Response(false, "Không tìm thấy tin nhắn yêu cầu");
        }
        smsOutBox.setSent(false);
        smsOutBox.setSuccess(false);
        smsOutBox.setTime(new Date().getTime());
        smsOutboxRepository.save(smsOutBox);
        return new Response(true, "Yêu cầu gửi lại đã được cập nhật");
    }

    public void send(String receiver, String content, SmsOutboxType type, long sendTime, int temp) {
        SmsOutbox smsOutbox = new SmsOutbox();
        smsOutbox.setId(smsOutboxRepository.genId());
        smsOutbox.setTime(System.currentTimeMillis());
        smsOutbox.setContent(content);
        smsOutbox.setType(type);
        smsOutbox.setSmsTemp(temp);
        smsOutbox.setSentTime(sendTime);
        smsOutbox.setId(smsOutboxRepository.genId());
        smsOutbox.setSmsType(SmsClient.TYPE_G_Com2_3);

        if (type == SmsOutboxType.SMS_MARKETING || type == SmsOutboxType.SPAM) {
            smsOutbox.setSmsType(SmsClient.TYPE_G_Ad1);
        }
        smsOutbox.setReceiver(receiver);
        smsOutboxRepository.save(smsOutbox);
    }

    public Response addSms8255(String message, String messageId, String phone) {
        if (message.toLowerCase().contains("cdt xm")) {
            List<String> mt = new ArrayList<>();
            String pattern = "cdt xm (.*)";
            mt.add("Chuc mung ban ban da kich hoat tai khoan {username}");
            mt.add("Tai khoan {username} chưa khai bao so dien thoai tren chodientu.vn");
            mt.add("Tai khoan {username} khong co tren he thong chodientu.vn");
            mt.add("So dien thoai {username} khong giong voi so ban khai bao");
            String username = message.trim().toLowerCase().replaceAll(pattern, "$1").trim();
            User user = userRepository.find(username);
            if (user == null) {
                user = userRepository.getByEmail(username);
                if (user == null) {
                    if (user == null) {
                        user = userRepository.findByUsernameLowerCase(username);
                        if (user == null) {
                            createInbox(message, mt.get(2).replace("{username}", username), messageId, phone, "8255", null, user, SmsInboxType.XM, false);
                            return new Response(false, mt.get(2).replace("{username}", username));
                        }
                    }
                }
                username = user.getEmail();
            }
            if (user.getPhone() == null || user.getPhone().equals("")) {
                createInbox(message, mt.get(1).replace("{username}", username), messageId, phone, "8255", null, user, SmsInboxType.XM, false);
                return new Response(false, mt.get(1).replace("{username}", username));
            }

            if (!user.getPhone().trim().equals(phone.trim())) {
                createInbox(message, mt.get(3).replace("{username}", username), messageId, phone, "8255", null, user, SmsInboxType.XM, false);
                return new Response(false, mt.get(3).replace("{username}", username));
            }

            user.setPhoneVerified(true);
            user.setPhoneVerifiedTime(System.currentTimeMillis());
            userRepository.save(user);
            //Cộng xèng sau khi kích hoạt sdt thành công!
            try {
                cashService.reward(CashTransactionType.PHONE_VERIFIED, user.getId(), user.getId(), "/user/"+user.getId()+"/ho-so-nguoi-ban.html",null);
            } catch (Exception e) {
            }
            createInbox(message, mt.get(0).replace("{username}", username), messageId, phone, "8255", null, user, SmsInboxType.XM, true);
            return new Response(true, mt.get(0).replace("{username}", username));
        } else if (message.contains("cdt tk")) {
            List<String> mt = new ArrayList<>();
            String pattern = "cdt tk (.*)";
            mt.add("Ban da thay doi mat khau thanh cong cho tai khoan {username} tai chodientu.vn . Mat khau moi cua ban la {pass}");
            mt.add("Tai khoan {username} chưa khai bao so dien thoai tren chodientu.vn");
            mt.add("Tai khoan {username} khong co tren he thong chodientu.vn");
            mt.add("So dien thoai {username} khong giong vơi so ban khai bao");
            String username = message.trim().toLowerCase().replaceAll(pattern, "$1").trim();
            User user = userRepository.find(username);
            if (user == null) {
                user = userRepository.getByEmail(username);
                if (user == null) {
                    if (user == null) {
                        user = userRepository.findByUsernameLowerCase(username);
                        if (user == null) {
                            createInbox(message, mt.get(2).replace("{username}", username), messageId, phone, "8255", null, user, SmsInboxType.TK, false);
                            return new Response(false, mt.get(2).replace("{username}", username));
                        }
                    }
                }
                username = user.getEmail();
            }
            if (user.getPhone() == null || user.getPhone().equals("")) {
                createInbox(message, mt.get(1).replace("{username}", username), messageId, phone, "8255", null, user, SmsInboxType.TK, false);
                return new Response(false, mt.get(1).replace("{username}", username));
            }
            if (!user.getPhone().trim().equals(phone.trim())) {
                createInbox(message, mt.get(3).replace("{username}", username), messageId, phone, "8255", null, user, SmsInboxType.TK, false);
                return new Response(false, mt.get(3).replace("{username}", username));
            }

            String password = TextUtils.genPasswordRadom();
            user.setPassword(DigestUtils.md5Hex(password + user.getSalt()));
            userRepository.save(user);
            String resp = mt.get(0).replace("{username}", username);
            resp = resp.replace("{pass}", password);
            createInbox(message, resp, messageId, phone, "8255", null, user, SmsInboxType.TK, true);
            return new Response(true, resp);
        } else {
            return new Response(false, "Cu phap khong hop le");
        }
    }

    public Response addSms8755(String message, String messageId, String phone) {
        List<String> mt = new ArrayList<>();
        String pattern = "cdt nap (.*)";
        mt.add("Ban da duoc cong them 6000 xeng vao tai khoan {username}");
        mt.add("Tai khoan {username} khong co tren he thong chodientu.vn");
        String username = message.trim().toLowerCase().replaceAll(pattern, "$1").trim();
        User user = userRepository.find(username);
        if (user == null) {
            user = userRepository.getByEmail(username);
            if (user == null) {
                if (user == null) {
                    user = userRepository.findByUsernameLowerCase(username);
                    if (user == null) {
                        createInbox(message, mt.get(1).replace("{username}", username), messageId, phone, "8755", null, user, SmsInboxType.NAP, false);
                        return new Response(false, mt.get(1).replace("{username}", username));
                    }
                }
            }
            username = user.getEmail();
        }
        cashService.createSmsInbox(user.getId(), 6000);
        createInbox(message, mt.get(0).replace("{username}", username), messageId, phone, "8755", null, user, SmsInboxType.NAP, true);
        return new Response(true, mt.get(0).replace("{username}", username));
    }

    public Response addSms8155(String message, String messageId, String phone) {
        List<String> mt = new ArrayList<>();
        String pattern = "cdt up (.*)";
        mt.add("Ban da up ma san pham {itemId} thanh cong len dau danh muc");
        mt.add("Ma san pham {itemId} khong co trong he thong chodientu.vn");
        mt.add("Ma san pham {itemId} khong du dieu kien len san");
        String itemId = message.trim().toLowerCase().replaceAll(pattern, "$1").trim();
        Item item = itemRepository.find(itemId);
        User user = userRepository.find(item.getSellerId());
        if (item == null) {
            createInbox(message, mt.get(1).replace("{itemId}", itemId), messageId, phone, "8155", itemId, user, SmsInboxType.UP, false);
            return new Response(false, mt.get(1).replace("{itemId}", itemId));
        }

        if ((item.getSellPrice() == 0 && item.getListingType() == ListingType.BUYNOW) || !item.isCompleted() || !item.isActive() || !item.isApproved() || item.getEndTime() < System.currentTimeMillis()) {
            createInbox(message, mt.get(2).replace("{itemId}", itemId), messageId, phone, "8155", itemId, user, SmsInboxType.UP, false);
            return new Response(false, mt.get(2).replace("{itemId}", itemId));
        }
        upScheduleService.upNow(item, false);
        createInbox(message, mt.get(0).replace("{itemId}", itemId), messageId, phone, "8155", itemId, user, SmsInboxType.UP, true);
        return new Response(true, mt.get(0).replace("{itemId}", itemId));
    }

    /**
     * Save sms inbox history
     *
     * @param message
     * @param respMessage
     * @param messageId
     * @param phone
     * @param receiver
     * @param itemId
     * @param user
     * @param type
     * @param success
     */
    public void createInbox(String message, String respMessage, String messageId, String phone, String receiver, String itemId, User user, SmsInboxType type, boolean success) {
        SmsInbox sms = new SmsInbox();
        sms.setCreateTime(System.currentTimeMillis());
        sms.setResponTime(System.currentTimeMillis());
        sms.setItemId(itemId);
        if (user != null) {
            sms.setUsername(user.getUsername());
            sms.setUserId(user.getId());
        }
        sms.setMessage(message);
        sms.setPhone(phone);
        sms.setType(type);
        sms.setResponMessage(respMessage);
        sms.setReceiver(receiver);
        sms.setMessageId(messageId);
        sms.setSuccess(success);
        smsInboxRepository.save(sms);
    }

    /**
     * Lọc tin nhắn trong nhiều tin nhắn gửi về
     *
     * @param search
     * @return
     */
    public DataPage<SmsInbox> search(SmsInboxSearch search) {
        Criteria criteria = new Criteria();

        if (search.getPhone() != null && !search.getPhone().equals("")) {
            criteria.and("phone").regex(search.getPhone());
        }
        if (search.getType() != null && !search.getType().equals("")) {
            criteria.and("type").is(search.getType());
        }
        if (search.getReceiver() != null && !search.getReceiver().equals("")) {
            criteria.and("receiver").is(search.getReceiver());
        }
        if (search.getTimeTo() <= search.getTimeFrom()) {
            if (search.getTimeFrom() > 0) {
                criteria.and("createTime").gte(search.getTimeFrom());
            }
        } else if (search.getTimeTo() > search.getTimeFrom()) {
            criteria.and("createTime").gte(search.getTimeFrom()).lte(search.getTimeTo());
        }
        if (search.getSuccess() > 0 && search.getSuccess() == 1) {
            criteria.and("success").is(true);
        }
        if (search.getSuccess() > 0 && search.getSuccess() == 2) {
            criteria.and("success").is(false);
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "sentTime"));
        query.skip(search.getPageIndex() * search.getPageSize()).limit(search.getPageSize());

        DataPage<SmsInbox> page = new DataPage<>();
        page.setPageSize(search.getPageSize());
        page.setPageIndex(search.getPageIndex());
        if (page.getPageSize() <= 0) {
            page.setPageSize(5);
        }
        page.setDataCount(smsInboxRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }
        page.setData(smsInboxRepository.find(query));
        return page;
    }

}
