package com.test.controller.admin.userManagement;

import com.test.controller.admin.userManagement.ui.Chart;
import com.test.controller.admin.userManagement.ui.Tree;
import com.test.controller.admin.userManagement.ui.UI;
import com.test.controller.admin.userManagement.ui.button.Delete;
import com.test.controller.admin.userManagement.ui.button.ModifyAvatar;
import com.test.controller.admin.userManagement.ui.button.ModifyInformation;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;

/**
 * 用户控制界面整体控制类
 */

public class UserManagementController {

    @FXML
    private Button modifyInformation;
    @FXML
    private Button modifyAvatar;
    @FXML
    private Button deleteUser;

    @FXML
    private Label cellphoneLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Label departmentLabel;

    @FXML
    private Label mainCellphoneLabel;
    @FXML
    private Label mainNameLabel;
    @FXML
    private Label mainpositionLabel;
    @FXML
    private Label mainDepartmentLabel;

    @FXML
    private ImageView userAvatar;

    @FXML
    private TreeView<String> users;

    @FXML
    private TextField nameText;
    @FXML
    private TextField positionText;
    @FXML
    private TextField departmentText;

    @FXML
    private PieChart pieChart;

    private boolean isInit = false;

    public void init() {
        ShareData.init();
        UI.init(modifyInformation, modifyAvatar, deleteUser, cellphoneLabel, nameLabel, positionLabel, departmentLabel,
                mainCellphoneLabel, mainNameLabel, mainpositionLabel, mainDepartmentLabel,
                nameText, positionText, departmentText);

        Tree.init(users);
        Chart.init(pieChart);
        ModifyAvatar.init(userAvatar);
        isInit = true;
    }

    public void modifyPic() {
        ModifyAvatar.modify();
    }

    public void delete() {
        Delete.delete();
    }

    public void modifyInfo()
    {
        ModifyInformation.modify();
    }

    public void refresh()
    {
        ShareData.refresh();
        Chart.refresh();
        Tree.refresh();
    }

    public void syncDatabaseIfChanged()
    {
        Delete.syncDatabaseIfChanged();
        ModifyAvatar.syncDatabaseIfChanged();
        ModifyInformation.syncDatabaseIfChanged();
    }

    public boolean isInit()
    {
        return isInit;
    }
}
