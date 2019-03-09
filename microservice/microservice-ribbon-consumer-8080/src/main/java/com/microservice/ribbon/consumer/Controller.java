package com.microservice.ribbon.consumer;


 import com.microservice.api.entity.Dept;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private RestTemplate restTemplate; //http client

    private final static String URL_PREFIX = "http://MICROSERVICE-DEPT"; //provider path

    @RequestMapping(value = "/consumer/dept/add",method = RequestMethod.POST)
    public Boolean add_dept(Dept dept){
        return restTemplate.postForObject(URL_PREFIX+"/dept/add", dept,Boolean.class);
    }

    @RequestMapping("/consumer/dept/{id}")
    public @ResponseBody Dept get_dept(@PathVariable("id") Integer id){
        Dept dept = restTemplate.getForObject(URL_PREFIX+"/dept/"+id,Dept.class);
        System.out.println(dept);
        return restTemplate.getForObject(URL_PREFIX+"/dept/"+id,Dept.class);
    }

    @RequestMapping("/consumer/dept/list")
    public @ResponseBody
    List<Dept> getAll(){
        return restTemplate.getForObject(URL_PREFIX+"/dept/list",List.class);
    }

    @RequestMapping("/consumer/dept/discovery")
    public  @ResponseBody Object discovery(){
        return restTemplate.getForObject(URL_PREFIX+"/dept/discovery",Object.class);
    }
}
