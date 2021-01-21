package com.donglan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donglan.service.PaymentServiceFeign;
import com.donglan.pojo.CommonResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-18 19:11:44
 */
@RestController
@Slf4j
@RequestMapping("/feign/order")
public class FeignOrderController {

    @Autowired
    private PaymentServiceFeign paymentServiceFeign;

    @GetMapping("/getInfo")
    public CommonResult getInfo() {
        return paymentServiceFeign.getInfo();
    }

    @GetMapping("/timeout")
    public String timeoutTest() throws InterruptedException {
        return paymentServiceFeign.timeoutTest();
    }
}
