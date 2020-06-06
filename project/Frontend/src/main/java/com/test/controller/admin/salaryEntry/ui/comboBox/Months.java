package com.test.controller.admin.salaryEntry.ui.comboBox;

import com.test.controller.admin.salaryEntry.ui.textField.Salary;
import com.test.utils.Check;
import javafx.scene.control.ComboBox;

/**
 * 工资录入界面的月份下拉列表的处理类
 */

public class Months {
    private static ComboBox<Integer> months;
    public static void init(ComboBox<Integer> months)
    {
        Months.months = months;

        months.getSelectionModel().selectedItemProperty().addListener((a,b,month)->
        {
            if(!Check.isEmpty(Years.getYear()))
            {
                Salary.set(Years.getYear(),month);
            }
        });
    }

    public static void show()
    {
        months.setOpacity(1);
        //默认显示12个月供管理员修改
        if(months.getItems().isEmpty())
            months.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
    }

    public static void hide()
    {
        months.setOpacity(0);
    }

    public static Integer getMonth()
    {
        return months.getValue();
    }
}
