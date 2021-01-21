package com.donglan.lb;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-18 17:13:06
 */
public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> instances);
}
