package com.test.controller.admin.userManagement.ui.button;

import com.test.controller.admin.userManagement.ShareData;
import com.test.controller.admin.userManagement.ui.Chart;
import com.test.controller.admin.userManagement.ui.Tree;
import com.test.controller.admin.userManagement.ui.UI;
import com.test.entity.Worker;
import com.test.network.OKHTTP;
import com.test.network.requestBuilder.crud.SaveAllRequestBuilder;
import com.test.utils.Check;

import java.util.ArrayList;
import java.util.List;

/**
 * 修改个人信息按钮处理类
 */

public class ModifyInformation {

    public static List<Worker> changedWorkers = new ArrayList<>();
    private static boolean modifying = false;

    public static void save()
    {
        if(Tree.isSelected())
        {
            UI.hideText();
            UI.checkNoEmptyText();
            UI.modifyInformation.setOnMouseClicked(t -> modify());
            if(ShareData.workerChanged())
            {
                changedWorkers.add(ShareData.worker);
                UI.refreshWorker();
                UI.refreshLabel();
                Tree.setUsersChanged();
                Tree.refresh();
                Chart.refresh();
            }
            UI.modifyInformation.setText("修改信息");
            modifying = false;
        }
    }

    public static void modify()
    {
        if(Tree.isSelected())
        {
            UI.showText();
            UI.modifyInformation.setText("保存信息");
            UI.modifyInformation.setOnMouseClicked(t -> save());
            modifying = true;
        }
    }

    public static void add(Worker worker)
    {
        changedWorkers.add(worker);
    }

    public static List<Worker> getData()
    {
        return changedWorkers;
    }

    public static void syncDatabaseIfChanged()
    {
        if(!Check.isEmpty(changedWorkers))
        {
            Tree.setUsersChanged();
            OKHTTP.send(new SaveAllRequestBuilder().workers(ModifyInformation.getData()).build());
            changedWorkers = new ArrayList<>();
        }
    }

    public static boolean isModifying()
    {
        return modifying;
    }
}
