package edu.bit.hjs.springcloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import edu.bit.hjs.springcloud.entities.Dept;
import edu.bit.hjs.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value="/dept/add",method=RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){

        return deptService.add(dept);
    }

    //一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
    @RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_Get")  //
    public Dept get(@PathVariable("id") Long id){

        Dept dept = deptService.get(id);
        if (null == dept){
            throw new RuntimeException("该ID："+id+"没有对应的信息");
        }
        return dept;
    }
    public Dept processHystrix_Get(@PathVariable("id") Long id){

        return new Dept().setDeptno(id).setDname("该ID:"+id+"没有对应的信息,null--@HystrixCommand")
                .setDb_source("no this database in MySQL");
    }

    @RequestMapping(value="/dept/list",method=RequestMethod.GET)
    public List<Dept> list(){

        return deptService.list();

    }

    //用于服务发现
    @RequestMapping(value = "/dept/discovery",method = RequestMethod.GET)
    public Object discovery(){

        List<String> list = client.getServices();
        System.out.println("********" + list);

        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance element: srvList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t"
                    + element.getPort() + "\t" + element.getUri());
        }
        return this.client;
    }
}
