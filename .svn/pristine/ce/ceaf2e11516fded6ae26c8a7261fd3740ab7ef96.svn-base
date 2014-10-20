/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.shop;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Phuongdt
 */
@Controller
public class GuideController extends BaseShop {

    @RequestMapping(value = "/{alias}/guide")
    public String index(@PathVariable String alias, ModelMap model,
            HttpServletResponse response) {
        initMap(alias, model, response);

        return "shop.guide";
    }
}
