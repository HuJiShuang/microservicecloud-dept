package edu.bit.hjs.springcloud.service.impl;

import edu.bit.hjs.springcloud.entities.Dept;
import edu.bit.hjs.springcloud.dao.DeptDao;
import edu.bit.hjs.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public boolean add(Dept dept) {

        return deptDao.addDept(dept);
    }

    @Override
    public Dept get(Long id) {

       return deptDao.findById(id);

    }

    @Override
    public List<Dept> list() {

       return deptDao.findAll();

    }
}
