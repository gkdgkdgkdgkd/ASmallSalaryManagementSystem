package com.test.controller.admin.userManagement.ui;

import com.test.controller.admin.userManagement.ShareData;
import com.test.controller.admin.userManagement.ui.button.ModifyAvatar;
import com.test.entity.Worker;
import com.test.utils.Check;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * 整体UI控制类
 */

public class UI {
    private static boolean showMainLabel = false;
    public static Button modifyInformation;
    public static Button modifyAvatar;
    public static Button deleteUser;

    public static Label cellphoneLabel;
    public static Label nameLabel;
    public static Label positionLabel;
    public static Label departmentLabel;

    public static Label mainCellphoneLabel;
    public static Label mainNameLabel;
    public static Label mainPositionLabel;
    public static Label mainDepartmentLabel;

    public static TextField nameText;
    public static TextField positionText;
    public static TextField departmentText;


    public static void init(Button modifyInformation,Button modifyAvatar,Button deleteUser,
                Label cellphoneLabel,Label nameLabel,Label positionLabel,Label departmentLabel,
                Label mainCellphoneLabel,Label mainNameLabel,Label mainpositionLabel,Label mainDepartmentLabel,
                TextField nameText,TextField positionText,TextField departmentText)
    {
        UI.modifyInformation = modifyInformation;
        UI.modifyAvatar = modifyAvatar;
        UI.deleteUser = deleteUser;
        UI.cellphoneLabel = cellphoneLabel;
        UI.nameLabel = nameLabel;
        UI.positionLabel = positionLabel;
        UI.departmentLabel = departmentLabel;

        UI.mainCellphoneLabel = mainCellphoneLabel;
        UI.mainNameLabel = mainNameLabel;
        UI.mainPositionLabel = mainpositionLabel;
        UI.mainDepartmentLabel = mainDepartmentLabel;

        UI.nameText = nameText;
        UI.positionText = positionText;
        UI.departmentText = departmentText;
    }

    public static boolean isShowMainLabel()
    {
        return showMainLabel;
    }

    public static void showMainLabel()
    {
        setNotTransparentAndEnable(mainCellphoneLabel,mainNameLabel, mainPositionLabel,mainDepartmentLabel);
        showMainLabel = true;
    }

    public static void hideMainLabel()
    {
        setTransparentAndDisable(mainCellphoneLabel,mainNameLabel, mainPositionLabel,mainDepartmentLabel);
        showMainLabel = false;
    }

    public static void hideLabel()
    {
        setTransparentAndDisable(cellphoneLabel,nameLabel, positionLabel,departmentLabel);
    }

    public static void showLabel()
    {
        setNotTransparentAndEnable(cellphoneLabel,nameLabel, positionLabel,departmentLabel);
    }

    public static void showText()
    {
        setTransparentAndDisable(nameLabel, positionLabel,departmentLabel);
        setNotTransparentAndEnable(nameText, positionText,departmentText);
        nameText.setText(ShareData.worker.getName());
        positionText.setText(ShareData.worker.getPosition());
        departmentText.setText(ShareData.worker.getDepartment());
    }

    public static void hideText()
    {
        setTransparentAndDisable(nameText, positionText,departmentText);
        setNotTransparentAndEnable(nameLabel, positionLabel,departmentLabel);
    }

    public static void refreshLabelAndAvatarByWorker(Worker worker)
    {
        if(!Check.isEmpty(worker))
        {
            cellphoneLabel.setText(worker.getCellphone());
            nameLabel.setText(worker.getName());
            positionLabel.setText(worker.getPosition());
            departmentLabel.setText(worker.getDepartment());

            if(ShareData.images.containsKey(worker.getCellphone()))
                ModifyAvatar.setUserAvatar(ShareData.images.get(worker.getCellphone()));
            else
                ModifyAvatar.setDefaultAvatar();
        }
    }

    private static void setTransparentAndDisable(Node... nodes)
    {
        for (Node node : nodes) {
            node.setOpacity(0);
            node.setDisable(true);
        }
    }

    private static void setNotTransparentAndEnable(Node ... nodes)
    {
        for (Node node : nodes) {
            node.setOpacity(1);
            node.setDisable(false);
        }
    }

    public static void refreshWorker()
    {
        ShareData.worker.setName(nameText.getText());
        ShareData.worker.setPosition(positionText.getText());
        ShareData.worker.setDepartment(departmentText.getText());
    }

    public static void refreshLabel()
    {
        nameLabel.setText(nameText.getText());
        positionLabel.setText(positionText.getText());
        departmentLabel.setText(departmentText.getText());
    }

    private static void setEmptyLabel(Label ... labels)
    {
        for(Label label:labels)
            label.setText("");
    }

    public static void checkNoEmptyText()
    {
        if(Check.isEmpty(nameText.getText()))
            nameText.setText("无姓名");
        if(Check.isEmpty(positionText.getText()))
            positionText.setText("无职位");
        if(Check.isEmpty(departmentText.getText()))
            departmentText.setText("无部门");
    }
}
