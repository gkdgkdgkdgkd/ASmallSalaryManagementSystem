package com.test.controller.admin.salaryEntry.ui;

import com.test.controller.admin.salaryEntry.ui.button.SaveButton;
import com.test.controller.admin.salaryEntry.ui.comboBox.Months;
import com.test.controller.admin.salaryEntry.ui.comboBox.Years;
import com.test.controller.admin.salaryEntry.ui.textField.Salary;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * UI初始化类
 */

public class UI {
    public static ComboBox<Integer> years;
    public static ComboBox<Integer> months;
    public static TextField salaryText;
    public static Button saveButton;
    private static boolean showBoxAndButton = false;

    public static void init(ComboBox<Integer> years,ComboBox<Integer> months,TextField salaryText,Button saveButton)
    {
        Years.init(UI.years = years);
        Months.init(UI.months = months);
        Salary.init(UI.salaryText = salaryText);
        SaveButton.init(UI.saveButton = saveButton);
        hideBoxAndButton();
    }

    public static void showBoxAndButton()
    {
        showBoxAndButton = true;
        Years.show();
        Months.show();
        Salary.show();
        SaveButton.show();
    }

    public static void hideBoxAndButton()
    {
        showBoxAndButton = false;
        Years.hide();
        Months.hide();
        Salary.hide();
        SaveButton.hide();
    }

    public static boolean isShowBoxAndButton()
    {
        return showBoxAndButton;
    }
}
