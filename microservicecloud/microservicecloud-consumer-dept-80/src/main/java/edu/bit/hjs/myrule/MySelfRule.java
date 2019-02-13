package edu.bit.hjs.myrule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){

        return  new RandomRule_hjs();   //自定义算法,每个服务使用5次
    }

}
