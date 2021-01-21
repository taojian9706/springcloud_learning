package com.donglan.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-19 19:23:18
 * 调用端的服务降级处理,统一添加服务降级类
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE-HYSTRIX", fallback = PaymentHystrixFallBackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/isOk/{id}")
    String isOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/timeout/{id}")
    String timeout(@PathVariable("id") Integer id);
}
