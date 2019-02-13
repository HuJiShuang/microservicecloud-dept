package edu.bit.hjs.springcloud;

import edu.bit.hjs.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient         //声明这是eureka客户端，为Ribbon服务
//在启动该微服务的时候就能去加载我们自定义的Ribbon配置类，从而使配置生效
@RibbonClient(name = "MICROSERVICECLOUD-DEPT",configuration = MySelfRule.class)
public class DeptConsumer80_App {

    public static void main(String[] args) {

        SpringApplication.run(DeptConsumer80_App.class,args);
    }
}
