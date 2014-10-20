package vn.chodientu.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.Administrator;
import vn.chodientu.entity.db.AdministratorRole;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.AdministratorRepository;
import vn.chodientu.repository.AdministratorRoleRepository;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.input.AdministratorSearch;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private AdministratorRoleRepository roleRepository;
    @Autowired
    private Validator validator;

    public Administrator auth(String email) {
        Administrator admin = administratorRepository.findByEmail(email);
        if (admin == null) {
            admin = new Administrator();
            admin.setActive(false);
            admin.setDescription("Hệ thống tự động lưu tài khoản");
            admin.setEmail(email);
            admin.setJoinTime(System.currentTimeMillis());
        }
        admin.setLastActive(System.currentTimeMillis());
        administratorRepository.save(admin);
        return admin;
    }

    /**
     * Thêm mới
     *
     * @param administrator
     * @return
     */
    public Response add(Administrator administrator) {
        Map<String, String> errors = validator.validate(administrator);
        if (administratorRepository.findByEmail(administrator.getEmail()) != null) {
            errors.put("email", "Email đã tồn tại");
        }
        if (!errors.isEmpty()) {
            return new Response(false, "Thêm quản trị viên thất bại", errors);
        } else {
            administrator.setId(administratorRepository.genId());
            administrator.setJoinTime(System.currentTimeMillis());
            administratorRepository.save(administrator);
            return new Response(true, "Thêm quản trị viên thành công", administrator);
        }
    }

    /**
     * Sửa chi tiết
     *
     * @param administrator
     * @return
     */
    public Response edit(Administrator administrator) throws Exception {
        Map<String, String> errors = validator.validate(administrator);
        if (!administratorRepository.exists(administrator.getId())) {
            throw new Exception("Người dùng không tồn tại");
        }
        Administrator findByEmail = administratorRepository.findByEmail(administrator.getEmail());
        if (findByEmail != null && !findByEmail.getId().equals(administrator.getId())) {
            errors.put("email", "Email đã tồn tại");
        }
        if (!errors.isEmpty()) {
            return new Response(false, "Sửa quản trị viên thất bại", errors);
        } else {
            administratorRepository.save(administrator);
            return new Response(true, "Sửa quản trị viên thành công", administrator);
        }
    }

    /**
     * Xóa
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    public Response remove(String id) throws Exception {
        if (!administratorRepository.exists(id)) {
            throw new Exception("Quản trị viên không tồn tại");
        }

        administratorRepository.delete(id);
        roleRepository.removeByAdminId(id);
        return new Response(true, "Xóa thành công");

    }

    /**
     * Tìm kiếm phân trang
     *
     * @param administratorSearch
     * @return
     */
    public DataPage<Administrator> search(AdministratorSearch administratorSearch) {
        DataPage<Administrator> administratorPage = new DataPage<>();

        administratorPage.setPageIndex(administratorSearch.getPageIndex());
        administratorPage.setPageSize(administratorSearch.getPageSize());
        Criteria criteria = new Criteria();
        if (administratorSearch.getEmail() != null && !administratorSearch.getEmail().trim().equals("")) {
            criteria.and("email").is(administratorSearch.getEmail());
        }
        if (administratorSearch.getActive() > 0) {
            criteria.and("active").is(administratorSearch.getActive() == 1);
        }

        administratorPage.setDataCount(administratorRepository.total(criteria));
        administratorPage.setData(administratorRepository.search(criteria, administratorSearch.getPageIndex(), administratorSearch.getPageSize()));

        administratorPage.setPageCount(administratorPage.getDataCount() / administratorPage.getPageSize());
        if (administratorPage.getDataCount() % administratorPage.getPageSize() != 0) {
            administratorPage.setPageCount(administratorPage.getPageCount() + 1);
        }

        return administratorPage;

    }

    /**
     * Lấy chi tiết quản trị viên
     *
     * @param adminId
     * @return
     * @throws Exception
     */
    public Administrator getAdministrator(String adminId) throws Exception {
        Administrator administrator = administratorRepository.find(adminId);
        if (administrator == null) {
            throw new Exception("Quản trị viên không tồn tại");
        }
        return administrator;
    }

    /**
     * Lấy danh sách quyền của quản trị viên
     *
     * @param adminId
     * @return
     * @throws Exception
     */
    public List<AdministratorRole> getRoles(String adminId) throws Exception {
        if (!administratorRepository.exists(adminId)) {
            throw new Exception("Quản trị viên không tồn tại");
        }
        return roleRepository.findByAdminId(adminId);
    }

    /**
     * Lưu danh sách quyền quản trị
     *
     * @param roles
     * @throws Exception
     */
    public void saveRoles(List<AdministratorRole> roles) throws Exception {
        if (roles != null && !roles.isEmpty()) {
            String adminId = roles.get(0).getAdministratorId();
            if (!administratorRepository.exists(adminId)) {
                throw new Exception("Quản trị viên không tồn tại");
            }
            roleRepository.removeByAdminId(adminId);
            for (AdministratorRole role : roles) {
                if (role.getId() == null || role.getId().equals("")) {
                    role.setId(roleRepository.genId());
                    roleRepository.save(role);
                }
            }

        }
    }

    /**
     * Tìm kiếm admin theo Email
     *
     * @param email
     * @return
     * @throws Exception
     */
    public Administrator findByEmail(String email) throws Exception {
        Administrator findByEmail = administratorRepository.findByEmail(email);
        if (findByEmail == null) {
            throw new Exception("Người dùng không tồn tại");
        }
        return findByEmail;
    }

}
