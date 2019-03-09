package com.example.microservice.mapper;

 import java.util.List;

 import com.jc.entity.Dept;

public interface DeptMapper {


    int deleteByPrimaryKey(Integer deptNo);

    int insert(Dept record);

    int insertSelective(Dept record);


    Dept selectByPrimaryKey(Integer deptNo);

    List<Dept> selectAll();



    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
}