package com.microservice.api.service;

 import com.microservice.api.entity.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "MICROSERVICE-DEPT",fallbackFactory = DeptClientServiceFallbackFactory.class)
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
