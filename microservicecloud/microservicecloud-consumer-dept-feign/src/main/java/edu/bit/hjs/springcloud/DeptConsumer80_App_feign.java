package edu.bit.hjs.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"edu.bit.hjs.springcloud"})     //
public class DeptConsumer80_App_feign {

    public static void main(String[] args) {

        SpringApplication.run(DeptConsumer80_App_feign.class,args);
    }
}