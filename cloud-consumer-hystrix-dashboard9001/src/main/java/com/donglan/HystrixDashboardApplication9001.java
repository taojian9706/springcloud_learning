package com.donglan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-20 20:13:25
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApplication9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication9001.class, args);
    }
}
