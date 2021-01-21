package com.donglan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-18 20:05:50
 * feignclient 开启详细的调用日志
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
