package com.donglan.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donglan.pojo.CommonResult;
import com.donglan.pojo.Payment;
import com.donglan.service.HystrixService;
import com.donglan.service.PaymentService;
import jdk.nashorn.internal.objects.annotations.Getter;
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

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private HystrixService hystrixService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/getInfo")
    public CommonResult getInfo() {
        log.info("处理请求的是端口:{}", port);
        return CommonResult.success(paymentService.getList());
    }

    @PostMapping("/add")
    public CommonResult add(@RequestBody Payment payment) {
        log.info("处理请求的是端口:{}", port);
        return CommonResult.success(paymentService.add(payment));
    }

    @GetMapping("/discoveryClientInfo")
    public Object getDiscoveryClient() {
        discoveryClient.getServices().stream().forEach((str) -> log.info("服务名称:{}", str));
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        Map<Integer, List<ServiceInstance>> collect = instances.stream()
            .collect(Collectors.groupingBy((inst) -> inst.getPort()));
        return discoveryClient;
    }

    @GetMapping("/getPort")
    public String getPort() {
        log.info("端口号:{}", port);
        return this.port;
    }

    @GetMapping("/timeout")
    public String timeoutTest() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return this.port;
    }

    @GetMapping("/isOk/{id}")
    public String isOk(@PathVariable Integer id) {
        return hystrixService.isOk(id);
    }

    @GetMapping("/timeout/{id}")
    public String timeout(@PathVariable Integer id) throws InterruptedException {
        return hystrixService.timeout(id);
    }

    @GetMapping("/break/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        return hystrixService.paymentCircuitBreaker(id);
    }

}
