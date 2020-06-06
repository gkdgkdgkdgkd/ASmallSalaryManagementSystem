package com.test.controller.admin.userManagement.ui;

import com.test.controller.admin.userManagement.ShareData;
import com.test.controller.admin.userManagement.Utils;
import com.test.controller.admin.userManagement.ui.button.ModifyInformation;
import com.test.utils.Check;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * 用户树型列表处理类
 */

public class Tree
{
    private static final TreeItem<String> root = new TreeItem<>();

    @Getter
    public static Map<String, Integer> departmentWorkerNums = new HashMap<>();
    public static LinkedHashSet<String> departmentNames = new LinkedHashSet<>();
    public static LinkedHashMap<String, LinkedHashSet<String>> positionNames = new LinkedHashMap<>();
    public static LinkedHashMap<String, LinkedHashMap<String, String>> workerNames = new LinkedHashMap<>();

    private static TreeView<String> users;
    private static boolean usersChanged = false;

    public static void init(TreeView<String> users)
    {
        Tree.users = users;
        users.setRoot(root);
        initEvent();
        update();
    }

    public static void refresh()
    {
        if(usersChanged)
        {
            departmentWorkerNums = new HashMap<>();
            departmentNames = new LinkedHashSet<>();
            positionNames = new LinkedHashMap<>();
            workerNames = new LinkedHashMap<>();

            root.getChildren().clear();
            update();
            usersChanged = false;
        }
    }

    private static void initEvent()
    {
        users.getSelectionModel().selectedItemProperty().addListener((o, ov, newValue) ->
        {
            if ((!Check.isEmpty(newValue) && newValue.getValue().contains("(")) || ModifyInformation.isModifying()) {
                if(!UI.isShowMainLabel())
                {
                    UI.showMainLabel();
                    UI.showLabel();
                }
                UI.refreshLabelAndAvatarByWorker(ShareData.worker = Utils.getWorkerByCombineValue(newValue.getValue()));
            }
        });
    }

    private static void update()
    {
        if(!Check.isEmpty(ShareData.workers))
        {
            ShareData.workers.forEach(worker ->
            {
                String cellphone = worker.getCellphone();
                String name = worker.getName();
                String position = worker.getPosition();
                String department = worker.getDepartment();

                departmentNames.add(department);
                departmentWorkerNums.put(department, departmentWorkerNums.getOrDefault(department,0) + 1);
                if (positionNames.containsKey(department))
                    positionNames.get(department).add(position);
                else {
                    LinkedHashSet<String> temp = new LinkedHashSet<>();
                    temp.add(position);
                    positionNames.put(department, temp);
                }

                String primaryKey = Utils.combinepositionAndDepartment(position, department);
                if (workerNames.containsKey(primaryKey))
                    workerNames.get(primaryKey).put(cellphone, name);
                else {
                    LinkedHashMap<String, String> nameTemp = new LinkedHashMap<>();
                    nameTemp.put(cellphone, name);
                    workerNames.put(primaryKey, nameTemp);
                }
            });
            root.setValue("所有人员");
            departmentNames.forEach(t ->
            {
                TreeItem<String> department = new TreeItem<>(t);
                positionNames.get(t).forEach(d ->
                {
                    TreeItem<String> position = new TreeItem<>(d);
                    workerNames.get(Utils.combinepositionAndDepartment(d, t)).forEach((k, v) ->
                            position.getChildren().add(new TreeItem<>(Utils.combineNameAndCellphone(v, k))));
                    department.getChildren().add(position);
                });
                root.getChildren().add(department);
            });
        }
        else
            root.setValue("数据库为空");
    }

    public static boolean isSelected()
    {
        TreeItem<String> selected = users.getSelectionModel().getSelectedItem();
        return !Check.isEmpty(selected) && selected.getValue().contains("(");
    }

    public static void setUsersChanged()
    {
        usersChanged = true;
    }

}
