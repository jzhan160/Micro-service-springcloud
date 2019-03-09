package com.example.microservice;

import com.jc.entity.Dept;
import com.jc.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private DeptClientService deptClientService;

    @RequestMapping("/consumer/dept/{id}")
    public @ResponseBody Dept get_dept(@PathVariable("id") Integer id){
         Dept dept = deptClientService.findOne(id);
        System.out.println(dept);
        return dept;
    }

    @RequestMapping("/consumer/dept/list")
    public @ResponseBody
    List<Dept> getAll(){
        return deptClientService.findAll();
    }


}
