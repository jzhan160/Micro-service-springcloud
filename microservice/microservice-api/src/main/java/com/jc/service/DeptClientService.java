package com.jc.service;

import com.jc.entity.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("MICROSERVICE-DEPT")
public interface DeptClientService {
    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public @ResponseBody
    Integer add_dept(@RequestBody Dept dept);

    @RequestMapping(value = "/dept/list")
    public @ResponseBody
    List<Dept> findAll();

    @RequestMapping(value = "/dept/{id}")
    public @ResponseBody
    Dept findOne(@PathVariable("id") Integer id);
}
