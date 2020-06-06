package com.test.controller.admin.salaryEntry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lombok.Getter;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 每个部门组成的用于树型列表处理的类
 */

public class Department {

    private final Map<String,Integer> personNums = new HashMap<>();
    @Getter
    private final XYChart.Series<String, Double> series = new XYChart.Series<>();
    private final Map<String,Double> totalSalary = new HashMap<>();
    private final ObservableList<XYChart.Data<String, Double>> salaries = FXCollections.observableArrayList();
    private final DecimalFormat format = new DecimalFormat("#.00");

    public Department(String department,String position,double n)
    {
        series.setName(department);
        add(position,n);
    }

    public void add(String position,double n)
    {
        totalSalary.put(position,totalSalary.getOrDefault(position,  0.0)+n);
        personNums.put(position,personNums.getOrDefault(position,0)+1);
    }

    public void finish()
    {
        totalSalary.forEach((position,salary)->
            salaries.add(
                new XYChart.Data<>(position,Double.valueOf(
                format.format(salary / personNums.get(position)))
            )
        ));
        series.setData(salaries);
    }
}
