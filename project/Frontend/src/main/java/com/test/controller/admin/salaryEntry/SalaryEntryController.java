package com.test.controller.admin.salaryEntry;


import com.test.controller.admin.salaryEntry.ui.Chart;
import com.test.controller.admin.salaryEntry.ui.Tree;
import com.test.controller.admin.salaryEntry.ui.UI;
import com.test.controller.admin.salaryEntry.ui.button.SaveButton;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;

/**
 * 工资录入界面整体控制类
 */

public class SalaryEntryController {
    @FXML
    private BarChart<String,Double> barChart;
    @FXML
    private TreeView<String> users;

    @FXML
    private ComboBox<Integer> years;
    @FXML
    private ComboBox<Integer> months;
    @FXML
    private TextField salaryText;
    @FXML
    private Button saveButton;

    private boolean isInit = false;
    private boolean usersChanged = false;

    public void init()
    {
        ShareData.init();
        UI.init(years,months, salaryText,saveButton);
        Tree.init(users);
        Chart.init(barChart);
        isInit = true;
    }

    public void refresh()
    {
        if(usersChanged)
        {
            ShareData.refresh();
            UI.hideBoxAndButton();
            Tree.refresh();
            Chart.refresh();
            usersChanged = false;
        }
    }

    public boolean isInit()
    {
        return isInit;
    }

    public void save()
    {
        SaveButton.save();
    }

    public void syncDatabaseIfChanged()
    {
        SaveButton.syncDatabaseIfChanged();
    }

    public void setUsersChanged()
    {
        usersChanged = true;
    }
}
