package com.donglan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-15 14:32:24
 */
@SpringBootApplication
@MapperScan(basePackages = "com.donglan.dao")
@EnableTransactionManagement
@EnableEurekaClient
@EnableDiscoveryClient
public class PaymentApplication8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8001.class, args);
    }
}
