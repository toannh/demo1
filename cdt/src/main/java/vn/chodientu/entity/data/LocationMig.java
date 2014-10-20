package vn.chodientu.entity.data;

import java.util.List;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.District;

/**
 * @since May 15, 2014
 * @author Phu
 */
public class LocationMig {

    private City city;
    private List<District> districts;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

}
