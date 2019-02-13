package edu.bit.hjs.springcloud.controller;

import edu.bit.hjs.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptController_Consumer {

    //Ribbon和Eureka整合后Consumer可以直接调用微服务名称不需要再关心地址和端口号
    //private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";
    /**
     * 使用restTemplate访问restful接口非常的简单粗暴，
     * （url,requestMap,ResponseBean.class）这三个参数分别代表REST请求地址，请求参数，HTTP响应转换被转换成的对象类型
     *
     */
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(Dept dept)
    {
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list()
    {
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }

    @RequestMapping(value = "/consumer/dept/discovery")
    public Object discovery(){

        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery",Object.class);
    }

}
