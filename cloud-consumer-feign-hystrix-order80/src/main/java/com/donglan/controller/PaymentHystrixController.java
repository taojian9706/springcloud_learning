package com.donglan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donglan.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-19 19:26:42
 */
@RestController
@RequestMapping("/consumer/payment")
//@DefaultProperties(defaultFallback = "timeoutHandler", commandProperties = {
//    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//})
public class PaymentHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/isOk/{id}")
    public String isOk(@PathVariable("id") Integer id) {
        return paymentHystrixService.isOk(id);
    }

    @GetMapping("/timeout/{id}")
    //@HystrixCommand
    public String timeout(@PathVariable("id") Integer id) {
        return paymentHystrixService.timeout(id);
    }

    public String timeoutHandler() {
        return "客户端调用失败!";
    }
}
