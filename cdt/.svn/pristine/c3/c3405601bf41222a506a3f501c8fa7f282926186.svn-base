package vn.chodientu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.CpFunction;
import vn.chodientu.entity.db.CpFunctionGroup;
import vn.chodientu.entity.enu.CpFunctionType;
import vn.chodientu.repository.AdministratorRoleRepository;
import vn.chodientu.repository.CpFunctionGroupRepository;
import vn.chodientu.repository.CpFunctionRepository;

@Service
public class CpFunctionService {

    @Autowired
    private CpFunctionRepository functionRepository;
    @Autowired
    private CpFunctionGroupRepository functionGroupRepository;
    @Autowired
    private AdministratorRoleRepository roleRepository;

    /**
     * Lấy danh sách tất cả function
     *
     * @return List danh sách function
     */
    public List<CpFunction> getAll() {
        return functionRepository.find(new Query().with(new Sort(Sort.Direction.ASC, "position")));
    }

    /**
     * Lấy danh sách tất cả function group
     *
     * @return List danh sách các group
     */
    public List<CpFunctionGroup> getGroups() {
        return functionGroupRepository.find(new Query().with(new Sort(Sort.Direction.ASC, "position")));
    }

    /**
     * Lưu chức năng vào db
     *
     * @param functions
     */
    public void saveFunctions(List<CpFunction> functions) {
        CpFunction func = functions.get(0);
        CpFunctionGroup group = new CpFunctionGroup();
        group.setName(func.getGroupName());
        group.setPosition(func.getGroupPosition());
        functionGroupRepository.save(group);

        for (CpFunction function : functions) {
            function.setRefUri(func.getRefUri());
            functionRepository.save(function);
        }
    }

    /**
     * Xóa chức năng
     *
     * @param uri uri của chức năng cần xóa
     */
    public void removeFunction(String uri) {
        Query query = new Query(new Criteria("uri").is(uri));
        List<CpFunction> find = functionRepository.find(query);
        if (find != null) {
            CpFunction cpFunction = find.get(0);
            if (cpFunction.getType() == CpFunctionType.ACTION) {
                query = new Query(new Criteria("refUri").is(cpFunction.getRefUri()));
                functionRepository.delete(query);
                roleRepository.delete(query);
            } else {
                functionRepository.delete(cpFunction);
                roleRepository.removeByFunction(uri);
            }
        }
    }

    /**
     * Xóa nhóm chức năng
     *
     * @param name
     * @throws java.lang.Exception
     */
    public void removeGroup(String name) throws Exception {
        if (!functionRepository.find(new Query(new Criteria("groupName").is(name))).isEmpty()) {
            throw new Exception("Không thể xóa nhóm vẫn còn chức năng, phải xóa hoặc chuyển các chức năng sang nhóm khác trước.");
        }
        CpFunctionGroup group = new CpFunctionGroup();
        group.setName(name);
        functionGroupRepository.delete(group);
    }
    
    /**
     * Lấy danh sách function không bị bỏ qua quyền
     *
     * @return List danh sách function
     */
    public List<CpFunction> getFunctionsNotBeSkipped() {
        return functionRepository.findBySkip(false);
    }
}
