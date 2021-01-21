package com.donglan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-15 15:52:24
 */
@SpringBootApplication
@EnableEurekaClient
//启动ribbon client 的负载均衡算法
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = com.myrule.MyselfRule.class)
public class OrderApplication80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication80.class, args);
    }
}
