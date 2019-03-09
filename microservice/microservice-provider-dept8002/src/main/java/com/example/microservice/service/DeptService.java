package com.example.microservice.service;

 import com.example.microservice.mapper.DeptMapper;
 import com.jc.entity.Dept;
 import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("deptService")
public class DeptService {
    @Resource
    private DeptMapper deptMapper;

    public int add(Dept dept){
        return deptMapper.insert(dept);
    }

    public Dept find(Integer id){
        return deptMapper.selectByPrimaryKey(id);
    }

    public List<Dept> findAll(){
         return deptMapper.selectAll();
    }
}
