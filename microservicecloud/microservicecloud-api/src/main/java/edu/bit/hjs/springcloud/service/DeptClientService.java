package edu.bit.hjs.springcloud.service;

import edu.bit.hjs.springcloud.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 修改米承认欧式而vicecloud-api工程,根据已经有的DeptClientService接口
 * 新建一个实现了FallbackFactory接口的类DeptClientServiceFallbackFactory
 */

//@FeignClient(value = "MICROSERVICECLOUD-DEPT")      //针对哪一个微服务进行接口编程
@FeignClient(value = "MICROSERVICECLOUD-DEPT",fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

    @RequestMapping(value="/dept/add",method= RequestMethod.POST)
    public boolean add(@RequestBody Dept dept);

    @RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id);

    @RequestMapping(value="/dept/list",method=RequestMethod.GET)
    public List<Dept> list();
}
