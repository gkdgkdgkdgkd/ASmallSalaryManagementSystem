package com.test.controller.admin.userManagement.ui;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

/**
 * 饼图处理类
 */

public class Chart {
    private static final ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    private static PieChart chart;

    public static void init(PieChart pieChart)
    {
        chart = pieChart;
        update();
    }

    public static void refresh() {
        chart.getData().clear();
        data.clear();
        update();
    }

    private static void update() {
        Tree.getDepartmentWorkerNums().forEach((k,v)->data.add(new PieChart.Data(k,v)));
        chart.setData(data);
        data.forEach(t -> t.nameProperty().bind(Bindings.concat(t.getName() + ":" + Math.round(t.getPieValue()) + "人")));
        if(data.size() != 0)
        {
            chart.setLegendSide(Side.BOTTOM);
            chart.setTitle("人数统计");
        }
        else
        {
            chart.setTitle("数据库为空");
        }
    }
}
