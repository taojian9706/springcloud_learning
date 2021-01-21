package com.donglan.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-21 20:10:38
 */
@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {

    @Value("${config.info}")
    private String value;

    @GetMapping("/getValue")
    public String getValue() {
        return value;
    }
}
