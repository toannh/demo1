package vn.chodientu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.District;
import vn.chodientu.entity.form.DistrictForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.CityRepository;
import vn.chodientu.repository.DistrictRepository;
import vn.chodientu.component.Validator;

@Service
public class DistrictService {

    @Autowired
    private Validator Validator;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistrictRepository districtRepository;

    public List<District> list() {
        List<District> district = districtRepository.getAll();
        return district;
    }

    public Response delete(String id) {
        District district = districtRepository.find(id);
        if (district != null) {
            districtRepository.delete(district);
            return new Response(true, "Xóa thành công!", district);
        }
        return new Response(false, "Không tồn tại quận / huyện này!");
    }

    public Response add(DistrictForm districtForm) throws Exception {
        Map<String, String> errors = Validator.validate(districtForm);
        District district = new District();
        City cid = cityRepository.find(districtForm.getCityId());

        if (!cityRepository.exists(districtForm.getCityId())) {
            errors.put("cityId", "Tỉnh / thành không tồn tại");
        } else {
            district.setCityId(districtForm.getCityId());
        }
        if (errors.isEmpty()) {
            district.setId(districtRepository.genId());
            district.setName(districtForm.getName());
            district.setPosition(districtForm.getPosition());
            district.setScId(districtForm.getScId());
            districtRepository.save(district);
            return new Response(true, "Thêm mới quận / huyện thành công!", district);
        } else {
            return new Response(false, "Chưa nhập đủ thông tin", errors);
        }
    }

    public Response editDistrict(DistrictForm districtForm) throws Exception {
        Map<String, String> errors = Validator.validate(districtForm);
        District district = districtRepository.find(districtForm.getId());
        if (district == null) {
            return new Response(false, "Quận huyện không tồn tại!");
        }
        if (errors.isEmpty()) {
            district.setName(districtForm.getName());
            district.setPosition(districtForm.getPosition());
            district.setScId(districtForm.getScId());
            districtRepository.save(district);
            return new Response(true, "Sửa thông tin quận/huyện thành công!", district);
        } else {
            return new Response(false, "Chưa nhập đủ thông tin", errors);
        }
    }

    public Response getDistrictById(String id) {
        District district = districtRepository.find(id);
        if (district != null) {
            return new Response(true, "", district);
        }
        return new Response(false, "Không tồn tại quận / huyện này!");
    }

    public Response edit(String id, String name, int position, String scId) {
        District district = districtRepository.find(id);
        if (district != null) {
            district.setName(name);
            district.setPosition(position);
            district.setScId(scId);
            districtRepository.save(district);
            return new Response(true, "Thay đổi thông tin của quận / huyện thành công!", district);
        }
        return new Response(false, "Quận / huyện không tồn tại!");
    }

    public District get(String id) {
        return districtRepository.find(id);
    }

    public List<District> getAll() {
        return districtRepository.getAll();
    }

    public List<District> getAllDistrictByCity(String id) {
        List<District> allDistrictByCity = districtRepository.getAllDistrictByCity(id);
        if (allDistrictByCity == null) {
            return new ArrayList<>();
        }
        return allDistrictByCity;
    }
}
