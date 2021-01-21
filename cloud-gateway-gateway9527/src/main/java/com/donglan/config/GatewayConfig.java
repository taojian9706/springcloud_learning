package com.donglan.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-21 11:23:32
 */
@Configuration
public class GatewayConfig {

    /**
    *@Description 路由配置
    *@Param [builder]
    *@Return org.springframework.cloud.gateway.route.RouteLocator
    *@Author TAOJIAN
    *@Date 2021/1/21
    *@Time 11:29
    */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_no1", r -> r.path("/guonei")
            .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
