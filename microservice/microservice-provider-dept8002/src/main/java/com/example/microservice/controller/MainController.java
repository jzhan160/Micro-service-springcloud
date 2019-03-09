package com.example.microservice.controller;

 import com.example.microservice.service.DeptService;
 import com.jc.entity.Dept;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.cloud.client.ServiceInstance;
 import org.springframework.cloud.client.discovery.DiscoveryClient;
 import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public @ResponseBody Integer add_dept(@RequestBody Dept dept){
        return deptService.add(dept);
    }


    @RequestMapping(value = "/dept/{id}")
    public @ResponseBody Dept findOne(@PathVariable("id") Integer id){
         Dept dept = deptService.find(id);
        System.out.println(dept);
        return dept;
    }
    @RequestMapping(value = "/dept/list")
    public  @ResponseBody List<Dept> findAll(){
        return deptService.findAll();
    }

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public  @ResponseBody  Object discovery() {
        List<String> list = client.getServices();
        System.out.println(list);
        List<ServiceInstance> instances = client.getInstances("microservice-dept");
        for (int i = 0; i < instances.size(); i++) {
            System.out.println("host:" + instances.get(i).getHost() + " "
                               +"service_id:" + instances.get(i).getServiceId()+ " "
            +"port:"+instances.get(i).getPort()+" "+ "uri:"+instances.get(i).getUri());
        }
        return this.client;
    }
}
