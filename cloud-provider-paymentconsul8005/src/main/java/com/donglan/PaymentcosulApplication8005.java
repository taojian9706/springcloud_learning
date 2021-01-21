package com.donglan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-18 13:54:10
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentcosulApplication8005 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentcosulApplication8005.class, args);
    }
}
