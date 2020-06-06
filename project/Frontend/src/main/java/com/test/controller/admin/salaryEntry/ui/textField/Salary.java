package com.test.controller.admin.salaryEntry.ui.textField;

import com.test.controller.admin.salaryEntry.ShareData;
import com.test.controller.admin.salaryEntry.Utils;
import com.test.controller.admin.salaryEntry.ui.comboBox.Months;
import com.test.controller.admin.salaryEntry.ui.comboBox.Years;
import com.test.utils.Check;
import javafx.scene.control.TextField;

/**
 * 工资输入框处理类
 */

public class Salary {
    private static TextField salary;
    public static void init(TextField salary)
    {
        Salary.salary = salary;
    }

    public static void show()
    {
        salary.setOpacity(1);
        if(!Check.isEmpty(Years.getYear()) && !Check.isEmpty(Months.getMonth()))
        {
            set(Years.getYear(),Months.getMonth());
        }
    }

    public static void hide()
    {
        salary.setOpacity(0);
    }

    public static void set(Integer year,Integer month)
    {
        Utils.setWorker(ShareData.worker);
        Double value = Utils.getSalary(year,month);
        salary.setText(Check.isEmpty(value) ? "" : value.toString());
    }

    public static String getText()
    {
        return salary.getText();
    }
}
