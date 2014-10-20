/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.shop;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpServerErrorException;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.ShopCategory;
import vn.chodientu.entity.db.ShopNewsCategory;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ShopCategoryService;
import vn.chodientu.service.ShopNewsCategoryService;
import vn.chodientu.service.ShopService;

/**
 *
 * @author toannh
 */
@Controller
public class MapController extends BaseShop {

    @RequestMapping(value = "/{alias}/map")
    public String index(@PathVariable String alias, ModelMap model, 
            HttpServletResponse response) {
        initMap(alias, model, response);
        String clientScript = model.get("clientScript").toString();
        Shop shop = (Shop) model.get("shop");
        String addr = "<div style=\"width: 400px;\">"
                + "<b>Cửa hàng " + shop.getTitle() + "</b><br>"
                + "<span style=\"color: rgb(255, 0, 0);\">(Trụ sở chính)</span><br>"
                + "Địa chỉ: " + shop.getAddress() + "<br> "
                + "Điện thoại: " + shop.getPhone() + "</div>";
        clientScript += "map.showMap(" + shop.getLat() + "," + shop.getLng() + ",'" + addr + "');";
        model.put("clientScript", clientScript);

        return "shop.map";
    }
}
