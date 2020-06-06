package com.test.controller.admin.salaryEntry.ui.comboBox;

import com.test.controller.admin.salaryEntry.ui.textField.Salary;
import com.test.utils.Check;
import javafx.scene.control.ComboBox;

/**
 * 工资录入界面的月份下拉列表的处理类
 */

public class Years {

    private static ComboBox<Integer> years;
    private static final int startYear = 2010;
    private static final int endYear = 2020;
    private static final Integer[] allYears = new Integer[endYear-startYear+1];
    public static void init(ComboBox<Integer> years)
    {
        Years.years = years;

        years.getSelectionModel().selectedItemProperty().addListener((a,b,year)->
        {
            if(!Check.isEmpty(Months.getMonth()))
            {
                Salary.set(year,Months.getMonth());
            }
        });
    }

    static
    {
        for(int i=0;i<=endYear-startYear;++i)
            allYears[i] = startYear+i;
    }

    public static void show()
    {
        years.setOpacity(1);
        years.getItems().addAll(allYears);
    }

    public static void hide()
    {
        years.setOpacity(0);
    }

    public static Integer getYear()
    {
        return years.getValue();
    }
}
