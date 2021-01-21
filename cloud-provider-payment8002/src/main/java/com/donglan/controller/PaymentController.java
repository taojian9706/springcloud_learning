package com.donglan.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donglan.pojo.CommonResult;
import com.donglan.pojo.Payment;
import com.donglan.service.PaymentService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-15 14:41:46
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/getInfo")
    public CommonResult getInfo() {
        log.info("处理请求的是:{}", port);
        return CommonResult.success(paymentService.getList());
    }

    @PostMapping("/add")
    public CommonResult add(@RequestBody Payment payment) {
        log.info("处理请求的是:{}", port);
        return CommonResult.success(paymentService.add(payment));
    }

    @GetMapping("/getPort")
    public String getPort(){
        log.info("端口号:{}", port);
        return this.port;
    }

    @GetMapping("/timeout")
    public String timeoutTest() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return this.port;
    }
}
