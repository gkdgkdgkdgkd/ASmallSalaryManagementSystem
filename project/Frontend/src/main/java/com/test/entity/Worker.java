package com.test.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Worker {
    private String cellphone;
    private String password;
    private String name = "无姓名";
    private String department = "无部门";
    private String position = "无职位";
    private String timeAndSalary;

    public Worker(String cellphone,String password)
    {
        this.cellphone = cellphone;
        this.password = password;
    }
}
