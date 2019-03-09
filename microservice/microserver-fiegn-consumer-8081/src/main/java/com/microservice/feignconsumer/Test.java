package com.microservice.feignconsumer;

import com.microservice.api.entity.Dept;

public class Test {
    public static void main(String[] args) {
        Dept dept = new Dept(1,"S","DD");
        System.out.println(dept);
    }
}
