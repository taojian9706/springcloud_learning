package com.donglan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.donglan.lb.LoadBalancer;
import com.donglan.pojo.CommonResult;
import com.donglan.pojo.Payment;
import lombok.extern.slf4j.Slf4j;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-15 16:09:36
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/consumer/create")
    public CommonResult create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/add", payment, CommonResult.class);
    }

    @GetMapping("/consumer/getInfo")
    public CommonResult getPayment() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getInfo", CommonResult.class, "");
    }

    @GetMapping("/consumer/getInfo/entity")
    public CommonResult getPaymentForEntity() {
        ResponseEntity<CommonResult> forEntity = restTemplate
            .getForEntity(PAYMENT_URL + "/payment/getInfo", CommonResult.class, "");
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        }
        return CommonResult.fail();
    }

    @GetMapping("/consumer/getPort")
    public String getPort() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.stream().forEach((inst) ->{
            log.info("服务实例：{}", inst);
        });
        ServiceInstance instances1 = loadBalancer.instances(instances);
        return restTemplate.getForObject(instances1.getUri() + "/payment/getPort", String.class);
    }
}
