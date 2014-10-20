/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Footerkeyword;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.FooterKeywordService;

/**
 *
 * @author Phuongdt
 */
@Controller("cpFooterKeywordService")
@RequestMapping(value = "/cpservice/footerkeyword")
public class FooterKeywordController extends BaseRest {

    @Autowired
    private FooterKeywordService footerKeywordService;

    /**
     * Thêm mới từ khóa vào xu hướng tìm kiếm
     *
     * @param footerkeyword
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody Footerkeyword footerkeyword) {
        return footerKeywordService.add(footerkeyword);
    }

    /**
     * Sửa từ khóa vào xu hướng tìm kiếm
     *
     * @param footerkeyword
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@RequestBody Footerkeyword footerkeyword) {
        return footerKeywordService.edit(footerkeyword);
    }

    /**
     * Thay đổi trạng thái từ khóa
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/changestatus", method = RequestMethod.GET)
    @ResponseBody
    public Response changestatus(@RequestParam String id) {
        return footerKeywordService.changeActive(id);
    }

    /**
     * Thay đổi trạng thái phổ biến của từ khóa
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/changecommon", method = RequestMethod.GET)
    @ResponseBody
    public Response changeCommon(@RequestParam String id) {
        return footerKeywordService.changeCommon(id);
    }

    /**
     * Thay đổi trạng thái từ khóa
     *
     * @param id
     * @param position
     * @return
     */
    @RequestMapping(value = "/changepositon", method = RequestMethod.GET)
    @ResponseBody
    public Response changepositon(@RequestParam String id, @RequestParam int position) {
        return footerKeywordService.changePosition(id, position);
    }

    /**
     * Thay đổi từ khóa
     *
     * @param footerkeyword
     * @return
     */
    @RequestMapping(value = "/changekeyword", method = RequestMethod.POST)
    @ResponseBody
    public Response changekeyword(@RequestBody Footerkeyword footerkeyword) {
        return footerKeywordService.changeKeyword(footerkeyword.getId(), footerkeyword.getKeyword());
    }

    /**
     * Thay đổi link cho từ khóa
     *
     * @param footerkeyword
     * @return
     */
    @RequestMapping(value = "/changeurl", method = RequestMethod.POST)
    @ResponseBody
    public Response changeurl(@RequestBody Footerkeyword footerkeyword) {
        return footerKeywordService.changeUrl(footerkeyword.getId(), footerkeyword.getUrl());
    }

    /**
     * Xóa từ khóa
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response del(@RequestParam String id) {
        return footerKeywordService.del(id);
    }

}
