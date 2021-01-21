package com.donglan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-15 21:29:36
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication7003 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication7003.class, args);
    }
}
