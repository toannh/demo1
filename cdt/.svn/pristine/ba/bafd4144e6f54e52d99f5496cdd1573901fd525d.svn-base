package vn.chodientu.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.District;
import vn.chodientu.entity.form.CityForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.CityRepository;
import vn.chodientu.repository.DistrictRepository;
import vn.chodientu.component.Validator;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private DistrictRepository districtRepository;

    /**
     * danh sách toàn bộ city
     *
     * @return
     */
    public List<City> list() {
        List<City> city = cityRepository.getAll();
        return city;
    }

    /**
     * Thêm mới 1 tỉnh
     *
     * @param cityForm
     * @return
     */
    public Response add(CityForm cityForm) {
        Map<String, String> errors = validator.validate(cityForm);
        City city = new City();
        if (errors.isEmpty()) {
            city.setId(cityRepository.genId());
            city.setName(cityForm.getName());
            city.setScId(cityForm.getScId());
            city.setPosition(cityForm.getPosition());
            cityRepository.save(city);
            return new Response(true, "Thêm tỉnh/thành phố thành công!", city);
        } else {
            return new Response(false, "Chưa nhập đủ thông tin", errors);
        }
    }

    /**
     * Xóa 1 tỉnh
     *
     * @param id
     * @return
     */
    public Response detele(String id) {
        City city = cityRepository.find(id);
        if (city != null) {
            boolean flag = true;
            List<District> list = districtRepository.getAll();
            for (District district : list) {
                if (district.getCityId().equals(city.getId())) {
                    flag = false;
                }
            }
            if (flag == true) {
                cityRepository.delete(city);
                return new Response(true, "Xóa thành công!");
            } else {
                return new Response(false, "Tỉnh/thành phố đã tồn tại quận huyện không thể xóa!");
            }
        }
        return new Response(false, "Không tồn tại tình thành này");
    }

    /**
     * thay đổi vị trí sắp xếp các tỉnh/thành phố
     *
     * @param id
     * @param position
     * @return
     */
    public Response changePosition(String id, int position) {
        City city = cityRepository.find(id);
        if (city != null) {
            city.setPosition(position);
            cityRepository.save(city);
            return new Response(true, "Thay đổi vị trí sắp xếp của tỉnh / thành phố thành công!", city);
        }
        return new Response(false, "Tỉnh / thành phố không tồn tại!");
    }

    /**
     * lấy chi tiết 1 tỉnh/thành phố
     *
     * @param id
     * @return
     */
    public Response getCitybyId(String id) {
        City city = cityRepository.find(id);
        if (city != null) {
            return new Response(true, "", city);
        } else {
            return new Response(false, "Tỉnh / thành phố không tồn tại!");
        }
    }

    /**
     * Sửa chi tiết tỉnh/thành phố
     *
     * @param cityForm
     * @return
     */
    public Response editCity(CityForm cityForm) {
        Map<String, String> errors = validator.validate(cityForm);
        City city = cityRepository.find(cityForm.getId());
        if (errors.isEmpty()) {
            city.setName(cityForm.getName());
            city.setScId(cityForm.getScId());
            city.setPosition(cityForm.getPosition());
            cityRepository.save(city);
            return new Response(true, "Thành công!", city);
        }
        return new Response(false, "Chưa nhập đủ thông tin!", errors);
    }

    /**
     * Lấy danh sách city theo ids
     *
     * @param ids
     * @return
     */
    public List<City> getCityByIds(List<String> ids) {
        return cityRepository.get(ids);
    }
;
}
