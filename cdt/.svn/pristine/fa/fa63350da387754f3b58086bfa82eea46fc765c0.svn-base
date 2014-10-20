package vn.chodientu.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.District;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.CityRepository;
import vn.chodientu.repository.DistrictRepository;
import vn.chodientu.component.Validator;

/**
 * @since May 15, 2014
 * @author Phu
 */
@Service
public class LocationService {
    
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    Validator validator;

    /**
     * Lưu thông tin tỉnh thành
     *
     * @param city
     * @return
     */
    public Response saveCity(City city) {
        Map<String, String> errors = validator.validate(city);
        
        if (errors.isEmpty()) {
            cityRepository.save(city);
            return new Response(true, null, city);
        } else {
            return new Response(false, null, errors);
        }
    }

    /**
     * Lưu thông tin quận huyện
     *
     * @param district
     * @return
     */
    public Response saveDistrict(District district) {
        Map<String, String> errors = validator.validate(district);
        
        if (cityRepository.count(new Query(new Criteria("id").is(district.getCityId()))) <= 0) {
            errors.put("cityId", "Tỉnh/thành phố không tồn tại");
        }
        
        if (errors.isEmpty()) {
            districtRepository.save(district);
            return new Response(true, null, district);
        } else {
            return new Response(false, null, errors);
        }
    }

    /**
     * Lấy danh sách tỉnh/thành
     *
     * @return
     */
    public List<City> getCities() {
        return cityRepository.find(new Query().with(new Sort(Sort.Direction.ASC, "position")));
    }

    /**
     * Lấy danh sách quận/huyện
     *
     * @return
     */
    public List<District> getDistricts() {
        return districtRepository.find(new Query().with(new Sort(Sort.Direction.ASC, "position")));
    }

    /**
     * Lấy danh sách quận/huyện theo tỉnh/thành
     *
     * @param cityId
     * @return
     */
    public List<District> getDistricts(String cityId) {
        return districtRepository.find(new Query(new Criteria("cityId").is(cityId)).with(new Sort(Sort.Direction.ASC, "position")));
    }
}
