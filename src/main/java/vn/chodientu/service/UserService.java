package vn.chodientu.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import vn.chodientu.component.Validator;
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.District;
import vn.chodientu.entity.db.SellerStock;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.db.UserLock;
import vn.chodientu.entity.db.UserVerify;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.EmailOutboxType;
import vn.chodientu.entity.enu.Gender;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.SellerHistoryType;
import vn.chodientu.entity.form.EditUserForm;
import vn.chodientu.entity.form.SignupForm;
import vn.chodientu.entity.form.UserAvatarForm;
import vn.chodientu.entity.form.UserChangePassForm;
import vn.chodientu.entity.form.UserChangeProfileForm;
import vn.chodientu.entity.input.UserSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Captcha;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.CashRepository;
import vn.chodientu.repository.CityRepository;
import vn.chodientu.repository.DistrictRepository;
import vn.chodientu.repository.UserLockRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.repository.UserVerifyRepository;
import vn.chodientu.util.TextUtils;

/**
 * @since May 15, 2014
 * @author Phu
 */
@Service
public class UserService {

    @Autowired
    private Captcha captcha;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserVerifyRepository userVerifyRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private SellerStockService sellerStockService;
    @Autowired
    private SellerHistoryService sellerHistoryService;
    @Autowired
    private CashService cashService;
    @Autowired
    private CashRepository cashRepository;
    @Autowired
    private UserLockRepository lockRepository;
    @Autowired
    private Viewer viewer;

    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void doSend() {
        while (true) {
            UserLock process = lockRepository.getProcess();
            if (process == null) {
                break;
            }
            User user = userRepository.find(process.getUserId());
            if (user == null) {
                break;
            }
            user.setActive(true);
            user.setUpdateTime(System.currentTimeMillis());
            userRepository.save(user);
        }
    }

    @Async
    public void migrate(List<User> users) {
        for (User u : users) {
            City city = cityRepository.getBySc(u.getCityId());
            District dis = districtRepository.getBySc(u.getDistrictId());

            if (city == null) {
                u.setCityId(null);
            } else {
                u.setCityId(city.getId());
            }
            if (dis == null) {
                u.setDistrictId(null);
            } else {
                u.setDistrictId(dis.getId());
            }
            userRepository.save(u);
            imageService.download(u.getAvatar(), ImageType.AVATAR, u.getId());
        }
    }

    /**
     * Đăng ký
     *
     * @param signupForm
     * @param result
     * @throws java.lang.Exception
     */
    public void signup(SignupForm signupForm, BindingResult result) throws Exception {
        User user = new User();
        if (signupForm.getUsername() != null && !signupForm.getUsername().equals("")) {
            if (signupForm.getUsername().length() < 3 || signupForm.getUsername().length() > 25) {
                result.addError(new FieldError("signupForm", "username", "Tên đăng nhập phải dài từ 3 đến 25 ký tự"));
            }
        }
        if (signupForm.getUsername() != null && userRepository.existsUsername(signupForm.getUsername().trim())) {
            result.addError(new FieldError("signupForm", "username", "Tên đăng nhập này đã được người khác chọn"));
        }
        if (!TextUtils.validateEmailString(signupForm.getEmail().trim())) {
            result.addError(new FieldError("signupForm", "email", "Địa chỉ email không đúng định dạng"));
        } else {
            if (userRepository.existsEmail(signupForm.getEmail().trim())) {
                result.addError(new FieldError("signupForm", "email", "Địa chỉ email này đã được người khác dùng"));
            }
        }
        if (!captcha.validate(signupForm.getCaptcha().trim())) {
            result.addError(new FieldError("signupForm", "captcha", "Hình ảnh xác nhận không chính xác"));
        }
        if (!signupForm.getPassword().trim().equals(signupForm.getConfirmPassword().trim())) {
            result.addError(new FieldError("signupForm", "confirmPassword", "Mật khẩu xác nhận không đúng"));
        }
        if (!signupForm.isConfirm()) {
            result.addError(new FieldError("signupForm", "confirm", "Bạn phải chấp nhận điều khoản của chợ điện tử mới có thể đăng ký"));
        }
        user.setId(userRepository.genId());
        user.setEmail(signupForm.getEmail().trim());
        if (signupForm.getUsername() == null || signupForm.getUsername().equals("")) {
            user.setUsername(null);
            user.setName(signupForm.getEmail().split("@")[0]);
        } else {
            user.setUsername(signupForm.getUsername().trim());
        }
        user.setSalt(RandomStringUtils.randomAlphanumeric(25));
        user.setPassword(DigestUtils.md5DigestAsHex((signupForm.getPassword().trim() + user.getSalt()).getBytes()));
        user.setJoinTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        if (signupForm.getGender() == Gender.MALE) {
            user.setGender(Gender.MALE);
        } else {
            user.setGender(Gender.FEMALE);
        }
        user.setActive(true);
        if (!result.hasErrors()) {
            userRepository.save(user);
            try {
                Cash cash = new Cash();
                cash.setUserId(user.getId());
                cashRepository.save(cash);
                cashService.reward(CashTransactionType.REGISTER, user.getId(), user.getId(), "/user/" + user.getId() + "/ho-so-nguoi-ban.html", null);
            } catch (Exception e) {
            }
            UserVerify uv = new UserVerify();
            uv.setUserId(user.getId());
            uv.setTime(System.currentTimeMillis());
            userVerifyRepository.save(uv);

            Map<String, Object> data = new HashMap<>();
            data.put("code", uv.getId());
            data.put("username", user.getUsername());
            emailService.send(EmailOutboxType.VERIFY, signupForm.getEmail(), "Kích hoạt tài khoản tại Chợ Điện Tử", "verify", data);
        }
    }

    /**
     * Tự động đăng ký cho người dùng tài khoản khi người dùng đăng nhập bằng
     * fb,google,yahoo mà chưa có tài khoản chợ.
     *
     * @param signupForm
     * @return
     */
    public Response signupSso(SignupForm signupForm) throws Exception {
        User user = new User();
        user.setId(userRepository.genId());

        if (signupForm.getEmail() == null || signupForm.getEmail().equals("")) {
            throw new Exception("Tài khoản mạng xã hội của bạn không có email, không thể dùng để đăng nhập");
        }

        user.setEmail(signupForm.getEmail());
        user.setUsername(null);
        user.setName(signupForm.getName());
        if (signupForm.getGender() == Gender.MALE) {
            user.setGender(Gender.MALE);
        } else {
            user.setGender(Gender.FEMALE);
        }
        user.setSalt(RandomStringUtils.randomAlphanumeric(25));
        user.setPassword(DigestUtils.md5DigestAsHex((signupForm.getPassword().trim() + user.getSalt()).getBytes()));
        user.setJoinTime(System.currentTimeMillis());
        user.setActive(true);
        user.setUpdateTime(System.currentTimeMillis());
        user.setPhone(signupForm.getPhone());
        user.setEmailVerified(true);
        user.setAddress(signupForm.getAddress());

        userRepository.save(user);
        try {
            Cash cash = new Cash();
            cash.setUserId(user.getId());
            cashRepository.save(cash);
            cashService.reward(CashTransactionType.REGISTER, user.getId(), user.getId(), "/user/" + user.getId() + "/ho-so-nguoi-ban.html", null);
        } catch (Exception e) {
        }
        UserVerify uv = new UserVerify();
        uv.setUserId(user.getId());
        uv.setTime(System.currentTimeMillis());
        userVerifyRepository.save(uv);
        return new Response(true, "Đăng kí thành công!", user);

    }

    /**
     * Kích hoạt email
     *
     * @param code
     * @throws java.lang.Exception
     */
    public void verify(String code) throws Exception {
        UserVerify uv = userVerifyRepository.find(code);
        if (uv == null) {
            throw new Exception("Mã kích hoạt không hợp lệ.");
        }
        if (System.currentTimeMillis() > (uv.getTime() + 24 * 60 * 60 * 1000)) {
            throw new Exception("Thời gian kích hoạt lâu hơn 24 tiếng nên đường link kích hoạt đã bị vô hiệu hóa.");
        }
        User user = userRepository.find(uv.getUserId());
        user.setEmailVerified(true);
        user.setEmailVerifiedTime(System.currentTimeMillis());
        userRepository.save(user);
        userVerifyRepository.delete(code);
        try {
            cashService.reward(CashTransactionType.EMAIL_VERIFIED, user.getId(), user.getId(), "/user/" + user.getId() + "/ho-so-nguoi-ban.html", null);
        } catch (Exception e) {
        }
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("username", user.getUsername() == null ? user.getEmail() : user.getUsername());
            String content = "<p>Chúc mừng bạn đã kích hoạt thành công tài khoản trên ChợĐiệnTử.</p>";
            content += "<br/><p>Tài khoản của bạn: " + (user.getUsername() == null ? user.getEmail() : user.getUsername()) + "</p>";
            data.put("message", content);
            emailService.send(EmailOutboxType.WELCOME, user.getEmail(), code, "message", data);
        } catch (Exception e) {
        }
    }

    /**
     * Yêu cầu gửi lại mail kích hoạt
     *
     * @param email
     * @param password
     * @param captcha
     * @throws Exception
     */
    public void requestVerify(String email, String password, String captcha) throws Exception {
        User user = userRepository.getByEmail(email);

        if (user == null) {
            throw new Exception("Địa chỉ email này chưa đăng ký tài khoản trên Chợ Điện Tử!");
        }
        if (!DigestUtils.md5DigestAsHex((password + user.getSalt()).getBytes()).equals(user.getPassword())) {
            throw new Exception("Mật khẩu nhập không đúng!");
        }
        if (!this.captcha.validate(captcha)) {
            throw new Exception("Mã xác nhận không đúng!");
        }

        UserVerify uv = new UserVerify();
        uv.setUserId(user.getId());
        uv.setTime(System.currentTimeMillis());
        userVerifyRepository.save(uv);

        Map<String, Object> data = new HashMap<>();
        data.put("code", uv.getId());
        data.put("username", user.getUsername());
        emailService.send(EmailOutboxType.VERIFY, email, "Kích hoạt tài khoản tại Chợ Điện Tử", "verify", data);
    }

    /**
     * Đăng nhập bằng email
     *
     * @param email
     * @return
     */
    public User ssoSignin(String email) {
        User user = userRepository.getByEmail(email);
        if (user != null) {
            user.setEmailVerified(true);
            userRepository.save(user);
        }
        return user;
    }

    /**
     * Tìm kiếm user trong trang tất cả user
     *
     * @param search
     * @return
     */
    public DataPage<User> search(UserSearch search) {
        Criteria criteria = new Criteria();

        if (search.getActive() > 0) {
            criteria.and("active").is(search.getActive() == UserSearch.TRUE);
        }

        if (search.getEmailVerified() > 0) {
            criteria.and("emailVerified").is(search.getEmailVerified() == UserSearch.TRUE);
        }

        if (search.getPhoneVerified() > 0) {
            criteria.and("phoneVerified").is(search.getPhoneVerified() == UserSearch.TRUE);
        }

        if (search.getCityId() != null && !search.getCityId().equals("0")) {
            criteria.and("cityId").is(search.getCityId());
        }
        if (search.getDistrictId() != null && !search.getDistrictId().equals("0")) {
            criteria.and("districtId").is(search.getDistrictId());
        }
        if (search.getEmail() != null && !search.getEmail().equals("")) {
            criteria.and("email").is(search.getEmail());
        }
        if (search.getPhone() != null && !search.getPhone().equals("")) {
            criteria.and("phone").is(search.getPhone());
        }
        if (search.getUsername() != null && !search.getUsername().equals("")) {
            criteria.orOperator(new Criteria("_id").is(search.getUsername()), new Criteria("username").is(search.getUsername()));
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "updateTime"));
        query.skip(search.getPageIndex() * search.getPageSize()).limit(search.getPageSize());

        DataPage<User> page = new DataPage<>();
        page.setPageSize(search.getPageSize());
        page.setPageIndex(search.getPageIndex());
        if (page.getPageSize() <= 0) {
            page.setPageSize(5);
        }
        page.setDataCount(userRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }

        page.setData(userRepository.find(query));
        return page;
    }

    /**
     * Thay đổi trạng thái hoạt động của user
     *
     * @param userId
     * @param active
     * @return
     * @throws java.lang.Exception
     */
    public Response changeActive(String userId, boolean active) throws Exception {
        User user = userRepository.find(userId);
        if (user == null) {
            throw new Exception("Không tìm thấy tài khoản có mã " + userId);
        }
        if (user.isActive() != active) {
            user.setActive(active);
            user.setUpdateTime(System.currentTimeMillis());
            userRepository.save(user);
        }
        return new Response(true, "Trạng thái tài khoản " + user.getName() + " đã " + ((active) ? "được mở khóa" : "bị khóa"), user);
    }

    /**
     * Thay đổi trạng thái kích hoạt email của user
     *
     * @param userId
     * @param emailVerified
     * @return
     * @throws java.lang.Exception
     */
    public Response changeEmailVerified(String userId, boolean emailVerified) throws Exception {
        User user = userRepository.find(userId);
        if (user == null) {
            throw new Exception("Không tìm thấy tài khoản có mã " + userId);
        }
        if (user.isEmailVerified() != emailVerified) {
            user.setEmailVerified(emailVerified);
            user.setUpdateTime(System.currentTimeMillis());
            userRepository.save(user);
        }
        return new Response(true, "Tài khoản " + user.getName() + " đã " + ((emailVerified) ? "được kích hoạt email" : "bị khóa"), user);
    }

    /**
     * Thay đổi trạng thái hoạt động của user
     *
     * @param userId
     * @param phoneVerified
     * @return
     * @throws java.lang.Exception
     */
    public Response changePhoneVerified(String userId, boolean phoneVerified) throws Exception {
        User user = userRepository.find(userId);
        if (user == null) {
            throw new Exception("Không tìm thấy tài khoản có mã " + userId);
        }
        if (user.isPhoneVerified() != phoneVerified) {
            user.setPhoneVerified(phoneVerified);
            user.setUpdateTime(System.currentTimeMillis());
            userRepository.save(user);
        }
        return new Response(true, "Tài khoản " + user.getName() + " đã " + ((phoneVerified) ? "được kích hoạt số điện thoại" : "bị khóa"), user);
    }

    /**
     * Sửa thông tin tài khoản
     *
     * @param form
     * @return
     * @throws java.lang.Exception
     */
    public Response edit(EditUserForm form) throws Exception {
        User user = userRepository.find(form.getId());
        if (user == null) {
            throw new Exception("Không tìm thấy tài khoản có mã " + form.getId());
        }

        Map<String, String> error = validator.validate(form);

        if (form.getPassword() != null && !form.getPassword().equals("")) {
            if (form.getRePassword() == null || !form.getPassword().trim().equals(form.getRePassword().trim())) {
                error.put("rePassword", "Mật khẩu nhập lại chưa chính xác");
            }
        }

        if (error.isEmpty()) {
            if (form.getGender() == Gender.FEMALE) {
                user.setGender(Gender.FEMALE);
            } else {
                user.setGender(Gender.MALE);
            }

            if (form.getPassword() != null && !form.getPassword().equals("")) {
                String salt = (user.getSalt() == null) ? TextUtils.genPasswordRadom() : user.getSalt();
                user.setPassword(DigestUtils.md5DigestAsHex((form.getPassword().trim() + salt).getBytes()));
            }

            user.setCityId(form.getCityId());
            user.setDistrictId(form.getDistrictId());
            user.setName(form.getName());
            if (user.getPhone() != null && form.getPhone() != null && !user.getPhone().equals(form.getPhone())) {
                user.setPhone(form.getPhone());
                user.setPhoneVerified(false);
            } else {
                user.setPhone(form.getPhone());
            }
            user.setAddress(form.getAddress());
            user.setDob(form.getDob());
            user.setSkype(form.getSkype());
            user.setYahoo(form.getYahoo());
            user.setUpdateTime(new Date().getTime());
            userRepository.save(user);
            return new Response(true, "Tài khoản " + user.getUsername() + " cập nhật thành công", error);
        }
        return new Response(false, "Dữ liệu không chính xác", error);
    }

    /**
     * Kiểm tra đăng nhập
     *
     * @param username Tên đăng nhập
     * @param password Mật khẩu
     * @return hoặc message thành công hoặc báo lỗi
     * @throws java.lang.Exception
     */
    public Response signin(String username, String password) throws Exception {
        if (TextUtils.validateEmailString(username)) {
            User user = userRepository.getByEmail(username);
            if (username.equals("") || password.equals("")) {
                return new Response(false, "Bạn chưa nhập đủ thông tin!");
            }
            if (user == null) {
                return new Response(false, "Email không tồn tại!");
            }
            if (!DigestUtils.md5DigestAsHex((password + user.getSalt()).getBytes()).equals(user.getPassword())) {
                return new Response(false, "Mật khẩu nhập không đúng!");
            }
            if (!user.isActive()) {
                UserLock lockIsRunByUserId = lockRepository.getLockIsRunByUserId(user.getId());
                if (lockIsRunByUserId != null) {
                    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
                    String endtime = dt.format(lockIsRunByUserId.getEndTime());
                    return new Response(false, "Tài khoản có email " + user.getEmail() + " đang bị khóa" + (lockIsRunByUserId.getNote() != null ? ",do :" + lockIsRunByUserId.getNote().toLowerCase() + ", bị khóa đến ngày " + endtime + "." : "!"));
                } else {
                    return new Response(false, "Tài khoản có email " + user.getEmail() + " đang bị khóa!");
                }
            }
            if (!user.isEmailVerified()) {
                throw new Exception("Tài khoản của bạn chưa kích hoạt email");
            }
            user.setUpdateTime(System.currentTimeMillis());
            userRepository.save(user);
            sellerHistoryService.create(SellerHistoryType.USER, user.getId(), true, 1, null);
            try {
                cashService.reward(CashTransactionType.SIGNIN, user.getId(), user.getId(), "/user/" + user.getId() + "/ho-so-nguoi-ban.html", null);
            } catch (Exception e) {
            }
            return new Response(true, "Đăng nhập thành công!", user);
        } else {
            User user = userRepository.findByUsername(username);
            if (username.equals("") || password.equals("")) {
                return new Response(false, "Bạn chưa nhập đủ thông tin!");
            }
            if (user == null) {
                return new Response(false, "Tên đăng nhập không tồn tại!");
            }
            String checPass = DigestUtils.md5DigestAsHex((password + user.getSalt()).getBytes());
            if (!checPass.equals(user.getPassword())) {
                return new Response(false, "Mật khẩu nhập không đúng!");
            }
            if (!user.isActive()) {
                UserLock lockIsRunByUserId = lockRepository.getLockIsRunByUserId(user.getId());
                if (lockIsRunByUserId != null) {
                    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
                    String endtime = dt.format(lockIsRunByUserId.getEndTime());
                    return new Response(false, "Tài khoản " + user.getUsername() + " đang bị khóa" + (lockIsRunByUserId.getNote() != null ? ",do :" + lockIsRunByUserId.getNote().toLowerCase() + ", bị khóa đến ngày " + endtime + "." : "!"));
                } else {
                    return new Response(false, "Tài khoản " + user.getUsername() + " đang bị khóa!");
                }
            }
            if (!user.isEmailVerified()) {
                throw new Exception("Tài khoản của bạn chưa kích hoạt email");
            }
            user.setUpdateTime(System.currentTimeMillis());
            userRepository.save(user);
            sellerHistoryService.create(SellerHistoryType.USER, user.getId(), true, 1, null);
            try {
                cashService.reward(CashTransactionType.SIGNIN, user.getId(), user.getId(), "/user/" + user.getId() + "/ho-so-nguoi-ban.html", null);
            } catch (Exception e) {
            }
            return new Response(true, "Đăng nhập thành công!", user);
        }
    }

    /**
     * setRemember
     *
     * @param userId
     * @return Cookie
     */
    public Cookie setRemember(String userId) {
        Cookie cookie = null;
        User user = userRepository.find(userId);
        if (user != null) {
            String strRandom = RandomStringUtils.randomAlphanumeric(25).toLowerCase();
            strRandom = DigestUtils.md5DigestAsHex(strRandom.getBytes());
            String remember = new String(Base64.encodeBase64((user.getId() + "-" + strRandom).getBytes()));
            cookie = new Cookie("remember", remember);
            cookie.setPath("/");
            cookie.setMaxAge(365 * 24 * 3600);
            user.setRememberKey(strRandom);
            userRepository.save(user);
        }
        return cookie;
    }

    /**
     * Lấy thông tin user theo id
     *
     * @param id
     * @return Response
     */
    public Response getById(String id) {
        User user = userRepository.find(id);
        if (user == null) {
            return new Response(false, "Không tồn tại người dùng này!");
        } else {
            return new Response(true, "", user);
        }
    }

    /**
     * Lấy thông tin user theo id
     *
     * @param id
     * @return Response
     */
    public User get(String id) throws Exception {
        User user = userRepository.find(id);
        if (user == null) {
            throw new Exception("Không tìm thấy tài khoản");
        }
        return user;
    }

    /**
     * Lấy thông tin user theo username
     *
     * @param username
     * @return Response
     */
    public Response getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new Response(true, "", user);
        } else {
            return new Response(false, "Không tồn tại người dùng này!");
        }
    }

    /**
     * Lấy thông tin user theo email
     *
     * @param username
     * @return Response
     */
    public Response getByEmail(String email) {
        User user = userRepository.getByEmail(email);
        if (user != null) {
            return new Response(true, "", user);
        } else {
            return new Response(false, "Không tồn tại người dùng này!");
        }
    }

    /**
     * Reset password
     *
     * @param email
     * @param captcha
     * @throws java.lang.Exception
     */
    public void forgotPass(String email, String captcha) throws Exception {
        if (email.trim().equals("")) {
            throw new Exception("Bạn chưa nhập email!");
        }
        if (captcha.trim().equals("")) {
            throw new Exception("Bạn chưa nhập mã xác nhận!");
        }
        if (!this.captcha.validate(captcha)) {
            throw new Exception("Mã xác nhận không đúng!");
        }
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new Exception("Địa chỉ email không tồn tại trong hệ thống!");
        }

        UserVerify uv = new UserVerify();
        uv.setUserId(user.getId());
        uv.setTime(System.currentTimeMillis());
        userVerifyRepository.save(uv);

        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("code", uv.getId());
        emailService.send(EmailOutboxType.RESETPASSWORD, email, "Quên mật khẩu", "resetpassword", data);
    }

    public void resetPass(String code) throws Exception {
        UserVerify uv = userVerifyRepository.find(code);
        if (uv == null) {
            throw new Exception("Mã xác nhận lấy lại mật khẩu không hợp lệ.");
        }
        if (System.currentTimeMillis() > (uv.getTime() + 8 * 3600 * 1000)) {
            throw new Exception("Thời gian lấy lại mật khẩu lâu hơn 08 tiếng nên đường link đã bị vô hiệu hóa.");
        }
        User user = userRepository.find(uv.getUserId());
        String newPass = RandomStringUtils.randomAlphanumeric(12).toLowerCase();
        String salt = RandomStringUtils.randomAlphanumeric(25);
        user.setSalt(salt);
        user.setPassword(DigestUtils.md5DigestAsHex((newPass + salt).getBytes()));
        user.setUpdateTime(System.currentTimeMillis());

        Map<String, Object> data = new HashMap<>();
        data.put("pass", newPass);
        data.put("username", user.getUsername());
        emailService.send(EmailOutboxType.NEWPASSWORD, user.getEmail(), "Lấy lại mật khẩu!", "newpass", data);

        userRepository.save(user);
        userVerifyRepository.delete(code);
    }

    public Response changePassword(UserChangePassForm changePassForm) {
        Map<String, String> errors = validator.validate(changePassForm);
        User user = userRepository.getByEmail(changePassForm.getEmail());
        if (user == null) {
            errors.put("email", "Tài khoản không tồn tại!");
        }
        String checkPass = DigestUtils.md5DigestAsHex((changePassForm.getOldPass() + user.getSalt()).getBytes());
        if (!user.getPassword().equals(checkPass)) {
            errors.put("oldPass", "Mật khẩu cũ không đúng!");
        }
        if (!changePassForm.getNewPass().equals(changePassForm.getConfirmPass())) {
            errors.put("confirmPass", "Nhập lại mật khẩu không chính xác!");
        }
        if (errors.isEmpty()) {
            checkPass = DigestUtils.md5DigestAsHex((changePassForm.getNewPass() + user.getSalt()).getBytes());
            user.setPassword(checkPass);
            user.setUpdateTime(System.currentTimeMillis());
            user.setRememberKey("");
            userRepository.save(user);
            return new Response(true, "Đổi mật khẩu thành công!", user);
        } else {
            return new Response(false, "Có lỗi sảy ra!", errors);
        }
    }

    public Response updateProfile(UserChangeProfileForm profileForm) {
        Map<String, String> error = validator.validate(profileForm);
        User user = userRepository.find(profileForm.getId());
        if (user == null) {
            return new Response(false, "Không tìm thấy thông tin của bạn!");
        }
        if (TextUtils.validatePhoneNumber(profileForm.getPhone()) == false) {
            error.put("phone", "Số điện thoại phải là số dài 8-11 kí tự và bắt đầu bằng số 0");
        }
        if (profileForm.getCityId() == null || profileForm.getCityId().equals("0")) {
            error.put("cityId", "Bạn phải chọn thông tin địa phương!");
        }
        if (profileForm.getDistrictId() == null || profileForm.getDistrictId().equals("0")) {
            error.put("districtId", "Bạn phải chọn thông tin quận huyện!");
        }
        if (profileForm.getName() == null || profileForm.getName().equals("")) {
            error.put("name", "Bạn phải nhập tên đầy đủ!");
        }
        if (profileForm.getDob() == 0) {
            error.put("dob", "Ngày sinh phải chọn!");
        }
        if (error.isEmpty()) {
            user.setName(profileForm.getName());
            user.setCityId(profileForm.getCityId());
            user.setDistrictId(profileForm.getDistrictId());
            if (user.getPhone() != null && profileForm.getPhone() != null && !user.getPhone().equals(profileForm.getPhone())) {
                user.setPhone(profileForm.getPhone());
                user.setPhoneVerified(false);
            } else {
                user.setPhone(profileForm.getPhone());
            }
            user.setGender(profileForm.getGender());
            user.setAddress(profileForm.getAddress());
            user.setDob(profileForm.getDob());
            user.setYahoo(profileForm.getYahoo());
            user.setUpdateTime(System.currentTimeMillis());
            user.setSkype(profileForm.getSkype());
            userRepository.save(user);
            try {
                itemService.updateSellerCityId(user.getId());
            } catch (Exception ex) {
            }
            try {
                SellerStock stock = sellerStockService.getByType(user.getId(), 1);
                if (stock == null) {
                    stock = new SellerStock();
                    stock.setType(1);
                    stock.setName(user.getAddress());
                    stock.setAddress(user.getAddress());
                }
                stock.setName(user.getAddress());
                stock.setAddress(user.getAddress());
                stock.setSellerId(user.getId());
                stock.setCityId(user.getCityId());
                stock.setDistrictId(user.getDistrictId());
                stock.setSellerName(user.getName());
                stock.setPhone(user.getPhone());
                sellerStockService.add(stock);
            } catch (Exception e) {
            }
            return new Response(true, "Bạn vừa cập nhật thông tin cá nhân thành công.", user);
        } else {
            return new Response(false, "Có lỗi sảy ra!", error);
        }
    }

    public Response uploadAvatar(UserAvatarForm avatarForm) {
        Map<String, String> error = new HashMap<>();
        User user = userRepository.find(avatarForm.getUserId());
        if (user != null) {
            if (avatarForm.getAvatar().getSize() > 0) {
                List<String> get = imageService.get(ImageType.AVATAR, user.getId());
                Response response = imageService.upload(avatarForm.getAvatar(), ImageType.AVATAR, user.getId());
                if (response.isSuccess()) {
                    if (get.size() > 0) {
                        imageService.deleteById(ImageType.AVATAR, user.getId(), get.get(0));
                    }
                    return new Response(true, "Chỉnh sửa avatar thành công !");
                } else {
                    return new Response(false, "Vui lòng chọn hình ảnh có dung lượng dưới 1MB !", error);
                }
            } else {
                return new Response(false);
            }
        } else {
            return new Response(false, "Không tìm thấy thông tin của bạn!", error);
        }
    }

    public List<User> getUserByIds(List<String> ids) {
        return userRepository.get(ids);
    }

    /**
     * Cập nhật số điện thoại cho user
     *
     * @param userId
     * @param phone
     * @return
     */
    public Response updatePhone(String userId, String phone) {
        HashMap<String, String> error = new HashMap<>();
        User user = userRepository.find(userId);
        if (user == null) {
            return new Response(false, "Không tìm thấy tài khoản của bạn!");
        }
        if (TextUtils.validatePhoneNumber(phone) == false) {
            error.put("phone", "Số điện thoại phải là số dài 8-11 kí tự và bắt đầu bằng số 0");
        }
        if (error.isEmpty()) {
            user.setPhone(phone);
            user.setUpdateTime(System.currentTimeMillis());
            userRepository.save(user);
            return new Response(true, "Cập nhật số điện thoại thành công!", user);
        } else {
            return new Response(false, "Có lỗi sảy ra!", error);
        }
    }

    /**
     * Upload avatar từ máy
     *
     * @param userAvatarForm
     * @return
     */
    public Response uploadImageAvatar(UserAvatarForm userAvatarForm) {
        String data = null;
        if (userAvatarForm.getUserId() != null) {
            List<String> getImg = imageService.get(ImageType.AVATAR, userAvatarForm.getUserId());
            if (getImg.size() > 0) {
                imageService.deleteById(ImageType.AVATAR, userAvatarForm.getUserId(), getImg.get(0));
            }
        }
        Response upload = imageService.upload(userAvatarForm.getAvatar(), ImageType.AVATAR, userAvatarForm.getUserId());
        if (upload == null || !upload.isSuccess()) {
            return new Response(false, "Lỗi up ảnh: " + upload.getMessage());
        }
        data = (String) upload.getData();
        String urlImg = imageService.getUrl(data).getUrl();
        Map<String, String> puts = new HashMap<>();
        puts.put("image", urlImg);
        puts.put("userId", userAvatarForm.getUserId());
        return new Response(true, null, puts);

    }

    /**
     * Download avatar từ url
     *
     * @param url
     * @param userId
     * @return
     */
    public Response downloadImageAvatar(String url, String userId) {
        String data = null;
        if (!userId.equals("") && userId != null) {
            List<String> getImg = imageService.get(ImageType.AVATAR, userId);
            if (getImg.size() > 0) {
                imageService.deleteById(ImageType.AVATAR, userId, getImg.get(0));
            }
        }
        Response download = imageService.download(url, ImageType.AVATAR, userId);
        if (download == null || !download.isSuccess()) {
            return new Response(false, "Lỗi up ảnh: " + download.getMessage());
        } else {
            data = (String) download.getData();
            String urlImg = imageService.getUrl(data).getUrl();
            return new Response(true, null, urlImg);
        }

    }

    public Response changeAvatarCrop(String userId, int width, int height, int x, int y) {
        if (!userId.equals("") && userId != null) {
            List<String> getImg = imageService.get(ImageType.AVATAR, userId);
            if (getImg.size() > 0) {
                ImageUrl imageUrl = imageService.getUrl(getImg.get(0)).crop(x, y, width, height);
                String url = imageUrl.getUrl();
                Response download = imageService.download(url, ImageType.AVATAR, "tmpAvatar");
                imageService.deleteById(ImageType.AVATAR, userId, getImg.get(0));
                if (!download.isSuccess()) {
                    return new Response(false, "Lỗi up ảnh: " + download.getMessage());
                } else {
                    List<String> img = imageService.get(ImageType.AVATAR, "tmpAvatar");
                    if (img.size() > 0) {
                        imageService.download(imageService.getUrl(img.get(0)).getUrl(), ImageType.AVATAR, userId);
                        imageService.deleteById(ImageType.AVATAR, "tmpAvatar", img.get(0));
                    }
                }
            }
        }
        return new Response(true, "Thay đổi avatar thành công");
    }

    /**
     * Cập nhật user
     *
     * @param user
     */
    public void sigoutUpdate(User user) {
        User us = userRepository.getByEmail(user.getEmail());
        us.setUpdateTime(System.currentTimeMillis());
        userRepository.save(us);
    }

    /**
     *
     * Khóa tài khoản
     *
     * @param userId
     * @param time
     * @param hour
     * @param note
     * @throws Exception
     */
    public void userLock(String userId, long time, int hour, String note) throws Exception {
        User user = userRepository.find(userId);
        if (user == null) {
            throw new Exception("Tài khoản không tồn tại trên hệ thống");
        }
        if (viewer.getAdministrator() == null) {
            throw new Exception("Bạn cần đăng nhập lại hệ thống quản trị để thực hiện hành động này");
        }
        UserLock userLock = new UserLock();
        userLock.setAdmin(viewer.getAdministrator().getEmail());
        userLock.setCreateTime(System.currentTimeMillis());
        userLock.setUpdateTime(System.currentTimeMillis());
        userLock.setDone(false);
        userLock.setRun(false);
        userLock.setNote(note);
        if (hour > 0) {
            time = System.currentTimeMillis() + hour * 60 * 60 * 1000;
        }
        userLock.setStartTime(System.currentTimeMillis());
        userLock.setEndTime(time);
        userLock.setUserId(user.getId());

        //Khóa tài khoản
        user.setUpdateTime(System.currentTimeMillis());
        user.setActive(false);
        userRepository.save(user);
        lockRepository.save(userLock);
    }

}
