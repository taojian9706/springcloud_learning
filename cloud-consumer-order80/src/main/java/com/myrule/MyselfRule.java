package com.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-18 16:17:59
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule() {
        return new RandomRule();//随机算法
    }
}
