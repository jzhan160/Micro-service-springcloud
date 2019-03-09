package com.microservice.provider.test;

import com.microservice.api.entity.Dept;

public class TestAPI {
    public static void main(String[] args) {
        Dept dept = new Dept(1,"ddd","1");
        System.out.println(dept);

    }
}
