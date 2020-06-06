package com.test.controller.admin.salaryEntry.ui;

import com.test.controller.admin.salaryEntry.Department;
import com.test.controller.admin.salaryEntry.ShareData;
import com.test.controller.admin.salaryEntry.Utils;
import com.test.utils.Check;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.HashMap;
import java.util.Map;


/**
 * 饼图处理类
 */

public class Chart {

    private static BarChart<String,Double> chart;
    private static Map<String, Department> departments = new HashMap<>();
    private static final ObservableList<XYChart.Series<String,Double>> data = FXCollections.observableArrayList();

    public static void init(BarChart<String,Double> barChart)
    {
        Chart.chart = barChart;
        update();
    }

    public static void refresh() {
//        chart.getData().clear();
        data.clear();
        update();
    }

    private static void update() {
        ShareData.workers.forEach(w->
        {
            String department = w.getDepartment();
            if(!Check.isEmpty(w.getTimeAndSalary()))
            {
                Utils.setWorker(w);
                if (departments.containsKey(department))
                    departments.get(department).add(w.getPosition(), Utils.getAverageSalary());
                else
                    departments.put(department, new Department(department,w.getPosition(),Utils.getAverageSalary()));
            }
        });
        departments.forEach((k,v)->
        {
            v.finish();
            data.add(v.getSeries());
        });

        chart.setData(data);
        chart.setCategoryGap(0);
        chart.setBarGap(0);
        if(data.size() != 0)
        {
            chart.setLegendSide(Side.BOTTOM);
            chart.setTitle("各部门各职位平均工资");
        }
        else
        {
            chart.setTitle("工资数据库为空，请联系管理员添加记录");
        }
        departments = new HashMap<>();
    }
}
