package com.jc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@SuppressWarnings("serial")
public class Dept implements Serializable{
    private Integer deptNo;
    private String deptName;
    private String dbSource;
    //use lombok to generate properties
}
