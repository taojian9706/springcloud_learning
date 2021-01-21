package com.donglan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-18 14:29:23
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsumerconsul {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsumerconsul.class, args);
    }
}
