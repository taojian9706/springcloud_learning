package com.donglan.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-18 13:56:35
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/consul")
    public String paymentConsul() {
        return "springcloud with consul" + port + UUID.randomUUID().toString();
    }
}
