package com.donglan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-21 18:17:16
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class CloudConfigApplication3344 {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApplication3344.class, args);
    }
}
