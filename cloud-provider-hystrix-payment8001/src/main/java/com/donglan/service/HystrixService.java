package com.donglan.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-18 21:13:26
 */
@Service
public class HystrixService {

    public String isOk(Integer id) {
        return "线程名称:" + Thread.currentThread().getName() + ",id:" + id + "\t" + "O(∩_∩)O";
    }

    @HystrixCommand(fallbackMethod = "timeoutHandler", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")//超时设置
    })
    public String timeout(Integer id) throws InterruptedException {
        int second = 5;
        TimeUnit.SECONDS.sleep(second);
        return "线程名称:" + Thread.currentThread().getName() + ",id:" + id + "\t" + "timeout";
    }

    public String timeoutHandler(Integer id) {
        return "线程名称:" + Thread.currentThread().getName() + ",id:" + id + "\t" + "运行异常或者超时timeoutHandler/(ㄒoㄒ)/~~";
    }

    //=====服务熔断机制
    @HystrixCommand(fallbackMethod = "payment_fallback_method", commandProperties = {
        @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//开启断路器
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),// 时间窗口器
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        return Thread.currentThread().getName() + "调用成功，流水号:" + IdUtil.simpleUUID();
    }

    public String payment_fallback_method(Integer id) {
        return "id 不能为负数，请稍后再试" + id;
    }
}
