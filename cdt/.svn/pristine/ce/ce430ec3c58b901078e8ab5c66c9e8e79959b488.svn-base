package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.AdministratorRole;

@Repository
public class AdministratorRoleRepository extends BaseRepository<AdministratorRole> {

    public AdministratorRoleRepository() {
        super(AdministratorRole.class);
    }

    /**
     * Lấy quyền cho 1 quản trị viên
     *
     * @param adminId
     * @return
     */
    public List<AdministratorRole> findByAdminId(String adminId) {
        return getMongo().find(new Query(new Criteria("administratorId").is(adminId)), getEntityClass());
    }

    /**
     * Xóa quyền của 1 quản trị viên
     *
     * @param adminId
     */
    public void removeByAdminId(String adminId) {
        getMongo().remove(new Query(new Criteria("administratorId").is(adminId)), getEntityClass());
    }

    /**
     * Xóa quyền theo chức năng
     *
     * @param uri
     */
    public void removeByFunction(String uri) {
        getMongo().remove(new Query(new Criteria("functionUri").is(uri)), getEntityClass());
    }
}
