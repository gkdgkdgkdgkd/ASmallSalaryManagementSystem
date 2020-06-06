package com.test.controller.admin.salaryEntry.ui.button;

import com.test.controller.admin.salaryEntry.ShareData;
import com.test.controller.admin.salaryEntry.Utils;
import com.test.controller.admin.salaryEntry.ui.Chart;
import com.test.controller.admin.salaryEntry.ui.comboBox.Months;
import com.test.controller.admin.salaryEntry.ui.comboBox.Years;
import com.test.controller.admin.salaryEntry.ui.textField.Salary;
import com.test.entity.Worker;
import com.test.network.OKHTTP;
import com.test.network.requestBuilder.crud.SaveAllRequestBuilder;
import com.test.utils.Check;
import com.test.view.GUI;
import com.test.view.MessageBox;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * 工资录入界面的按钮事件处理类
 */

public class SaveButton {
    private static Button button;
    private static List<Worker> changedWorkers = new ArrayList<>();
    public static void init(Button button)
    {
        SaveButton.button = button;
    }

    public static void show()
    {
        button.setOpacity(1);
    }

    public static void hide()
    {
        button.setOpacity(0);
    }

    public static void save()
    {
        if(!Check.isEmpty(Years.getYear()) && !Check.isEmpty(Months.getMonth()) && !Check.isEmpty(Salary.getText()))
        {
            Utils.setSalary(Years.getYear(),Months.getMonth(),Double.parseDouble(Salary.getText()));
            ShareData.worker.setTimeAndSalary(Utils.getTimeAndSalary());
            Utils.setWorker(ShareData.worker);
            changedWorkers.add(ShareData.worker);
            MessageBox.show("保存成功");
            Chart.refresh();
        }
    }

    public static boolean workersHaveChanged()
    {
        return !Check.isEmpty(changedWorkers);
    }

    public static void add(Worker worker)
    {
        changedWorkers.add(worker);
    }

    public static void syncDatabaseIfChanged()
    {
        if(workersHaveChanged())
        {
            GUI.getSalaryEntryController().setUsersChanged();
            OKHTTP.send(new SaveAllRequestBuilder().workers(changedWorkers).build());
            changedWorkers = new ArrayList<>();
        }
    }
}
