package com.donglan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-18 19:09:26
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignOrderApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignOrderApplication80.class, args);
    }
}
