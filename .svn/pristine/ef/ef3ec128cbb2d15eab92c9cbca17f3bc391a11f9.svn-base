/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.shop;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.chodientu.entity.db.Shop;

/**
 *
 * @author Phuongdt
 */
@Controller
public class AboutController extends BaseShop {

    @RequestMapping(value = "/{alias}/about")
    public String index(@PathVariable String alias, ModelMap model, HttpServletResponse response) {
        initMap(alias, model, response);
        Shop shop = (Shop) model.get("shop");
        model.put("title", "Giới thiệu " + shop.getTitle());
        model.put("description", "Xem bài giới thiệu từ shop " + shop.getTitle() + " tại chodientu.vn - Giá rẻ, nhiều khuyến mại, thanh toán online, CoD, giao hàng toàn quốc, bảo vệ người mua");
        model.put("keywords", "chodientu, quần áo, thời trang, trang sức, phụ kiện, điện tử, xe hơi, đồ thể thao, điện thoại, máy tính, mobile, laptop máy ảnh kỹ thuật số, đồ mẹ bé, ebay, mua bán, đấu giá");
        model.put("canonical", "/" + shop.getAlias() + "/about.html");
        return "shop.about";
    }
}
