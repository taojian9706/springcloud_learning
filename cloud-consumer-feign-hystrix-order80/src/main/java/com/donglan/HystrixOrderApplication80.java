package com.donglan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-19 19:19:08
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class HystrixOrderApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixOrderApplication80.class, args);
    }
}
