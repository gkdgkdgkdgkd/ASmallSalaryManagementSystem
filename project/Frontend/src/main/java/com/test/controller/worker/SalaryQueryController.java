package com.test.controller.worker;

import com.test.entity.Worker;
import com.test.utils.Check;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

/**
 * 工资查询界面控制类
 */

public class SalaryQueryController {
    @FXML
    private BarChart<String,Double> barChart;

    @FXML
    private ComboBox<Integer> years;
    private Worker worker;

    public void init(Worker worker)
    {
        this.worker = worker;
        if(!Check.isEmpty(worker.getTimeAndSalary())) {
            Utils.setWorker(worker);
            if(years.getItems().size() == 0) {
                Integer[] allYears = Utils.getAllYears();
                years.setVisibleRowCount(4);
                years.getItems().addAll(allYears);
                years.setValue(allYears[0]);
            }
            updateChart();
            updateLabel();
        }
        else
        {
            barChart.setTitle("工资数据库为空，请联系管理员添加记录");
        }
        years.getSelectionModel().selectedItemProperty().addListener((a, b, newValue) ->
        {
            barChart.setAnimated(false);
            hideYears();
            if(years.getItems().size() != 0)
            {
                updateChart();
                updateLabel();
            }
        });
    }

    private void updateChart()
    {
        barChart.getData().clear();

        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        ObservableList<XYChart.Data<String, Double>> salaries = FXCollections.observableArrayList();
        series.setName(years.getValue().toString());

        Utils.getMonthsAndSalariesByYears(years.getValue()).forEach((k, v) ->
                salaries.add(new XYChart.Data<>(k.toString(), v)));
        series.setData(salaries);
        data.add(series);
        barChart.setData(data);
    }

    public void showYears()
    {
        if(!Check.isEmpty(worker.getTimeAndSalary()))
        {
            years.setOpacity(1);
            barChart.setTitle("");
        }
    }

    private void updateLabel()
    {
        barChart.setTitle(years.getValue()+"年工资总览");
    }

    public void hideYears()
    {
        if(!Check.isEmpty(worker.getTimeAndSalary()))
        {
            years.setOpacity(0);
        }
    }

    public void clear()
    {
        years.getItems().clear();
        barChart.getData().clear();
    }

}
