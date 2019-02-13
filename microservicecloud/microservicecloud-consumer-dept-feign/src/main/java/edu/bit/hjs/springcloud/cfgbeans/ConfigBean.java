package edu.bit.hjs.springcloud.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
@Configuration
public class ConfigBean {   //boot->spring  applicationContext.xml --- @Configuration配置 ConfigBean = applicationContext.xml

    @Bean("restTemplate")
    @LoadBalanced       //在客户端配置Ribbon负载均衡
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }

    @Bean
    public IRule myRule(){
        //return new RandomRule();    //使用随机算法代替默认的轮询算法
        return new RetryRule();
    }

}
//在applicationContext.xml == ConfigBean(@configuration)
//相当于<bean id="userService" class="edu.bit.hjs.UserServiceimpl"
