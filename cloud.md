###1.springcloud组件
服务注册中心:Eureka Zookeeper Consul Nacos

服务调用:Ribbon LoadBalancer Fegin OpenFegin

服务降级:Hystrix resilience4j sentinel

服务网关:Zuul Zuul2 gateway

服务配置:Config Nacos

服务总线:Bus Nacos

todo 24节 discoveryclient 服务发现学习

###2.总结
eureka server 集群环境搭建 
```properties
##应用配置
server.port=7002
## eureka服务实例的名字
eureka.instance.hostname=eureka7002.com
##注册中心配置
eureka.client.register-with-eureka=false
##false表示自己就是注册中心。我的职责就是维护服务实例，并不需要去检索服务
eureka.client.fetch-registry=false
##设置与Eureka Server 交互的地址查询服务和注册服务都需要依赖这个地址
eureka.client.service-url.defaultZone=http://eureka7001.com:7001/eureka,http://eureka7003.com:7003/eureka
```
```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```
eureka client 服务提供者的服务注册功能
```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
springboot 开发必备的依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!--健康度检查-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
目前不知道什么作用的依赖(课程介绍表示非常的重要 作为工具模块必备的开发包)
```xml
<dependency>
  <groupId>cn.hutool</groupId>
  <artifactId>hutool-all</artifactId>
  <version>5.1.0</version>
</dependency>
```
maven的继承、依赖、聚合
dependenciesManagement 标签的作用主要就是对于子模块的依赖管理，
但是着个管理并不是强制的，如果子模块没有自定坐标的版本号，就会使用
父工程的版本，反之不会
properties 标签主要就是定义版本号
scope、optional 这两个标签暂时不知道什么作用，需要了解

##Eureka Server 的自我保护机制(服务掉线时，暂时保存eureka client的信息)
1.eureka server 由于网络拥堵问题短时间内不能收到 client的心跳检测包 并不会立刻注销 服务实例的策略 

2.如何关闭eureka server的自我保护机制 

###Ribbon 进程内的负载均衡
Nginx 是服务器端的负载均衡，客户端的所有请求都是交给了Nginx，然后由nginx实现请求转发。及负载均衡是由服务端实现的

Ribbon 是本地的负载均衡，在调用服务接口的时候，，会在注册中心获取注册服务列表后缓存到JVM本地，从而在本地实现RPC远程服务调用
1.eureka client 已经引入了ribbon组件 所有不需要添加ribbon组件

Ribbon + RestTemplate 实现负载均衡调用

负载均衡算法:rest接口的第几次请求 % 服务器实例数量 = 实际调用服务的下标 ，调用服务重启后 计数从一开始

List = 2 instance

1 % 2 = 1 -> index = list.get(1)

2 % 2 = 0 -> index = list.get(0)

3 % 2 = 1 -> index = list.get(1)

JUC知识盲点

OpenFeign 的使用
@FeignClient @EnableFeignClients(value = "SERVICE-PAYMENT-SERVICE->微服务名称") 
 自带Ribbon 的负载均衡功能
自带的日志功能 增加配置类

##非常强大的豪猪哥(hystrix) 
1.服务降级:不让客户端等待并立刻返回友好提示(fallback)
2.服务熔断:保险丝的概念(break)先熔断再并调用服务降级方法
3.服务限流:秒杀等高并发操作(QPS一秒钟处理几个请求)，严禁一窝蜂的拥挤
确保服务提供方的线程不会长时间被占用

服务器线程数量被沾满是，消费端依然还要请求服务器
解决方案:超时不等待, 出错兜底
1.服务提供方自生的服务降级(@HystrixCommand)
```java

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker//开启断路器
public class HystrixApplication8001 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication8001.class, args);
    }
}

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
       return  "线程名称:" + Thread.currentThread().getName() + ",id:" + id + "\t" + "运行异常或者超时timeoutHandler/(ㄒoㄒ)/~~";
    }

}
```
2.客户端的服务降级处理(fallback可以用在服务调用端，也可以用在服务提供端)
```properties
##开启feign对hystrix的服务调用熔断功能
feign.hystrix.enabled=true
```
```java
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix// 开启服务降级
public class HystrixOrderApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixOrderApplication80.class, args);
    }
}
// 整体的feign接口都添加自己的fallback方法
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE-HYSTRIX", fallback = PaymentHystrixFallBackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/isOk/{id}")
    String isOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/timeout/{id}")
    String timeout(@PathVariable("id") Integer id);
}
```
3.服务端的熔断功能
```java
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
```
##Gateway新一代的网关
1.SpringCloud Gateway是基于WebFlux框架实现的，而WebFlux框架的底层则使用了高性能的Reator模式通信框架Netty
基于Spring Framework 5 ,Project Reactor 和 Spring Boot2.0进行构建
动态路由:能够匹配任意的请求属性;
可以集成Hystrix的断路器功能
集成spring cloud服务发现功能
易于编写Predicate 断言 和Filter 
请求限流功能
支持路径重写过滤器