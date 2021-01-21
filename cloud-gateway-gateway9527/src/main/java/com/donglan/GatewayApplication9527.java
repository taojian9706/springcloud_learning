package com.donglan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-20 21:21:41
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class GatewayApplication9527 {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication9527.class, args);
    }

}
