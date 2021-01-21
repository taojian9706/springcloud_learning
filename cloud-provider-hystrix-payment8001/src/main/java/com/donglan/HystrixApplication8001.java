package com.donglan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-18 20:59:45
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class HystrixApplication8001 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication8001.class, args);
    }
    /**
    *@Description 服务监控配置
    *@Param []
    *@Return org.springframework.boot.web.servlet.ServletRegistrationBean
    *@Author TAOJIAN
    *@Date 2021/1/20
    *@Time 20:41
    */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(
            hystrixMetricsStreamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
        
    }
}
