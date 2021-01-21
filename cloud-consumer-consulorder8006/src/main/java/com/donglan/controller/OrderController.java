package com.donglan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-18 14:30:13
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    public static final String URL = "HTTP://cloud-provider-payment/payment/consul";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/pay")
    public String pay() {
        String object = restTemplate.getForObject(URL, String.class, "");
        log.info("返回结果:{}", object);
        return object;
    }
}
