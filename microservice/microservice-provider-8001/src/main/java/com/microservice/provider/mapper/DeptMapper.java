package com.microservice.provider.mapper;

 import com.microservice.api.entity.Dept;

import java.util.List;

public interface DeptMapper {


    int deleteByPrimaryKey(Integer deptNo);

    int insert(Dept record);

    int insertSelective(Dept record);


    Dept selectByPrimaryKey(Integer deptNo);

    List<Dept> selectAll();



    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
}