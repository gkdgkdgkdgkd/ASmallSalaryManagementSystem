package com.test.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Worker {

    @Id
    private String cellphone;
    private String password;
    @Setter
    private String name;
    @Setter
    private String department;
    @Setter
    private String position;
    private String timeAndSalary;

    public Worker(String cellphone,String password)
    {
        this.cellphone = cellphone;
        this.password = password;
    }

}
