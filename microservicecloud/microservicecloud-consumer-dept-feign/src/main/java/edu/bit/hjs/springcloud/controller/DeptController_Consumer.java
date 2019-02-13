
package edu.bit.hjs.springcloud.controller;

import edu.bit.hjs.springcloud.entities.Dept;
import edu.bit.hjs.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController_Consumer {

    @Autowired
    private DeptClientService dcs;

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(Dept dept) {

        return dcs.add(dept);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {

        return dcs.get(id);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list() {

        return dcs.list();
    }
}
