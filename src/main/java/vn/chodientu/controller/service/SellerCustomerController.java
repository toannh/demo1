/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.db.SellerCustomer;
import vn.chodientu.entity.db.SellerEmailMarketing;
import vn.chodientu.entity.db.SellerSmsMarketing;
import vn.chodientu.entity.form.FileBean;
import vn.chodientu.entity.input.SellerCustomerSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CashService;
import vn.chodientu.service.SellerCustomerService;
import vn.chodientu.service.SellerMarketingService;

/**
 *
 * @author Phuongdt
 */
@Controller("serviceSellerCustomer")
@RequestMapping("/sellercustomer")
public class SellerCustomerController extends BaseRest {

    @Autowired
    private SellerCustomerService customerService;
    @Autowired
    private SellerMarketingService sellerMarketingService;
    @Autowired
    private CashService cashService;

    @RequestMapping(value = "/activenow", method = RequestMethod.POST)
    @ResponseBody
    public Response activeNow() throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        return customerService.active(viewer.getUser());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody SellerCustomer sellerCustomer) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        return customerService.add(sellerCustomer, viewer.getUser());
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@RequestBody SellerCustomer sellerCustomer) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        return customerService.edit(sellerCustomer, viewer.getUser());
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam String id) throws Exception {
        return customerService.getById(id);
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response del(@RequestParam String id) throws Exception {
        return customerService.remove(id);
    }

    @RequestMapping(value = "/removeall", method = RequestMethod.POST)
    @ResponseBody
    public Response removeall(@RequestBody List<String> ids) throws Exception {
        return customerService.remove(ids);
    }

    @RequestMapping(value = "/getlistsendemail", method = RequestMethod.GET)
    @ResponseBody
    public Response getlistsendemail() throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        List<SellerEmailMarketing> emailVerified = sellerMarketingService.emailVerified(viewer.getUser().getId());
        return new Response(true, null, emailVerified);
    }

    @RequestMapping(value = "/getlistsendsms", method = RequestMethod.GET)
    @ResponseBody
    public Response getlistsmsemail() throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        return new Response(true, null, sellerMarketingService.smsVerified(viewer.getUser().getId()));
    }

    @RequestMapping(value = "/getxeng", method = RequestMethod.GET)
    @ResponseBody
    public Response getxeng() throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        Cash cash = cashService.getCash(viewer.getUser().getId());
        Map<String, Long> map = new HashMap<>();
        map.put("xeng", cash.getBalance());
        map.put("costs", sellerMarketingService.xeng_email);
        return new Response(true, null, map);
    }

    @RequestMapping(value = "/getxengsms", method = RequestMethod.GET)
    @ResponseBody
    public Response getxengsms() throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        Cash cash = cashService.getCash(viewer.getUser().getId());
        Map<String, Long> map = new HashMap<>();
        map.put("xeng", cash.getBalance());
        map.put("costs", sellerMarketingService.xeng_sms);
        return new Response(true, null, map);
    }

    @RequestMapping(value = "/addemailcustomer", method = RequestMethod.POST)
    @ResponseBody
    public Response addEmailCustomer(@RequestBody SellerEmailMarketing emailMarketing) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        if (emailMarketing.getId().equals("0")) {
            return new Response(false, "Bạn chưa chọn danh sách Email");
        }
        List<String> email = emailMarketing.getEmail();
        List<String> emails = new ArrayList<>();
        if (email != null && email.size() > 0) {
            List<SellerCustomer> sellerCustomers = customerService.getById(email);
            for (SellerCustomer sellerCustomer : sellerCustomers) {
                emails.add(sellerCustomer.getEmail());
            }
        }
        String id = emailMarketing.getId();
        return sellerMarketingService.addEmailCustomer(emails, id, viewer.getUser());

    }

    @RequestMapping(value = "/addsmscustomer", method = RequestMethod.POST)
    @ResponseBody
    public Response addsmscustomer(@RequestBody SellerSmsMarketing sellerSmsMarketing) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        if (sellerSmsMarketing.getId().equals("0")) {
            return new Response(false, "Bạn chưa chọn danh sách SMS");
        }
        List<String> phone = sellerSmsMarketing.getPhone();
        List<String> phones = new ArrayList<>();
        if (phone != null && phone.size() > 0) {
            List<SellerCustomer> sellerCustomers = customerService.getById(phone);
            for (SellerCustomer sellerCustomer : sellerCustomers) {
                phones.add(sellerCustomer.getPhone());
            }
        }
        String id = sellerSmsMarketing.getId();
        return sellerMarketingService.addPhoneCustomer(phones, id, viewer.getUser());

    }

    @RequestMapping(value = "/listcustomer", method = RequestMethod.POST)
    @ResponseBody
    public Response listcustomer(@RequestBody SellerSmsMarketing sellerSmsMarketing) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        List<SellerCustomer> sellerCustomers = customerService.getById(sellerSmsMarketing.getPhone());
        if (sellerCustomers != null && sellerCustomers.size() > 0) {

        }
        return new Response(true, null, sellerCustomers);

    }

    /**
     * Lấy danh sách mã khách hàng theo Id marketingEmail
     *
     * @param idMarketingEmail
     */
    @RequestMapping(value = "/listcustomerids", method = RequestMethod.GET)
    @ResponseBody
    public Response getIdCustomerByEmails(@RequestParam String idMarketingEmail) throws Exception {
        SellerEmailMarketing email = sellerMarketingService.getEmail(idMarketingEmail);
        List<String> emails = new ArrayList<>();
        if (email != null) {
            List<String> list = email.getEmail();
            List<SellerCustomer> customerByEmails = new ArrayList<>();
            if (list != null && list.size() > 0) {
                customerByEmails = customerService.getCustomerByEmails(list);
                for (SellerCustomer sellerCustomer : customerByEmails) {
                    emails.add(sellerCustomer.getId());
                }
            }
        }
        return new Response(true, null, emails);

    }

    @RequestMapping(value = "/listcustomerbyseller", method = RequestMethod.GET)
    @ResponseBody
    public Response listcustomerbyseller(@ModelAttribute SellerCustomerSearch customerSearch) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        customerSearch.setPageSize(5);
        customerSearch.setPageIndex(customerSearch.getPageIndex() > 0 ? customerSearch.getPageIndex() - 1 : 0);
        DataPage<SellerCustomer> search = customerService.search(customerSearch, viewer.getUser());

        return new Response(true, null, search);

    }

    @RequestMapping(value = "/uploadexcel", method = RequestMethod.POST)
    @ResponseBody
    public Response uploadExcel(@ModelAttribute FileBean fileBean) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn cần đăng nhập lại để thực hiện tiếp thao tác");
        }
        if (fileBean == null || fileBean.getFileData() == null) {
            return new Response(false, "Bạn chưa chọn file excel");
        } else {
            ByteArrayInputStream bis = new ByteArrayInputStream(fileBean.getFileData().getBytes());
            Workbook workbook;
            try {
                if (fileBean.getFileData().getOriginalFilename().endsWith("xls") || fileBean.getFileData().getOriginalFilename().endsWith("xlsx")) {
                    workbook = new HSSFWorkbook(bis);
                } else {
                    return new Response(false, "File phải là file excel");
                }

                for (Row row : workbook.getSheetAt(0)) {
                    SellerCustomer sellerCustomer = new SellerCustomer();
                    try {
                        sellerCustomer.setName(row.getCell(0).getStringCellValue());
                        sellerCustomer.setEmail(row.getCell(1).getStringCellValue());
                        sellerCustomer.setPhone(row.getCell(2).getStringCellValue());
                        sellerCustomer.setAddress(row.getCell(3).getStringCellValue());
                        customerService.add(sellerCustomer, viewer.getUser());
                    } catch (Exception e) {
                    }
                }
                return new Response(true, "Thêm danh sách thành công");

            } catch (IOException e) {
            }

            return new Response(false, "Có lỗi xảy ra. Vui lòng kiểm tra lại định dạng file và thử lại");
        }
    }

}
