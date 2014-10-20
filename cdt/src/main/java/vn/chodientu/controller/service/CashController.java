/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.PaymentMethod;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CashService;

/**
 *
 * @author ThuNguyen
 */
@Controller("serviceCash")
@RequestMapping("/cash")
public class CashController extends BaseRest {

    @Autowired
    private CashService cashService;

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    @ResponseBody
    public Response login(ModelMap model, @RequestBody CashTransaction cashTransaction) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Đăng nhập lại để thanh toán");
        }
        CashTransaction transaction = cashService.getTransaction(cashTransaction.getId());
        if (transaction == null) {
            cashTransaction.setUserId(viewer.getUser().getId());
            cashTransaction.setType(CashTransactionType.TOPUP_NL);
            cashTransaction.setAmount(cashTransaction.getAmount() * cashTransaction.getSpentQuantity());
            discount(cashTransaction);
        } else {
            cashTransaction = transaction;
        }

        String returnUrl = baseUrl + "/user/thanh-toan-thanh-cong.html?trans=";
        String cancelUrl = baseUrl + "/user/tai-khoan-xeng.html";
        try {
            return new Response(true, "ok", cashService.createTopupNL(cashTransaction, returnUrl, cancelUrl).getNlCheckoutUrl());
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    /**
     * Giảm giá CDT
     *
     * @param cashTransaction
     */
    private void discount(CashTransaction cashTransaction) {
        if (cashTransaction.getPaymentMethod() == null) {
            cashTransaction.setPaymentMethod(PaymentMethod.NL);
        }
        if (cashTransaction.getAmount() >= 1000000 && cashTransaction.getAmount() <= 2000000) {
            cashTransaction.setDiscount(cashTransaction.getAmount() * 0.05);
        } else if (cashTransaction.getAmount() >= 3000000 && cashTransaction.getAmount() <= 5000000) {
            cashTransaction.setDiscount(cashTransaction.getAmount() * 0.1);
        } else if (cashTransaction.getAmount() >= 10000000) {
            cashTransaction.setDiscount(cashTransaction.getAmount() * 0.15);
        }
        //Giảm 20% khi thanh toán băng visa
        if (cashTransaction.getPaymentMethod() == PaymentMethod.VISA) {
            double discount = (cashTransaction.getAmount() - cashTransaction.getDiscount()) * 0.2;
            cashTransaction.setDiscount(cashTransaction.getDiscount() + discount);
        }
    }
}
