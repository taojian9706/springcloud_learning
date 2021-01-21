package com.donglan.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.donglan.pojo.CommonResult;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-18 19:14:18
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentServiceFeign {

    @GetMapping("/payment/getInfo")
    CommonResult getInfo();

    @GetMapping("/payment/timeout")
    String timeoutTest() throws InterruptedException;

}
