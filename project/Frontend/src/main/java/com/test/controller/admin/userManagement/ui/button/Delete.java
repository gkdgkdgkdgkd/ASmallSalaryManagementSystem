package com.test.controller.admin.userManagement.ui.button;


import com.test.controller.admin.userManagement.ShareData;
import com.test.controller.admin.userManagement.ui.Chart;
import com.test.controller.admin.userManagement.ui.Tree;
import com.test.controller.admin.userManagement.ui.UI;
import com.test.network.OKHTTP;
import com.test.network.requestBuilder.crud.DeleteManyRequestBuilder;
import com.test.utils.Check;

import java.util.ArrayList;
import java.util.List;

/**
 * 删除按钮处理类
 */

public class Delete {

    public static List<String> deletedCellphones = new ArrayList<>();

    public static void delete()
    {
        if(Tree.isSelected())
        {
            deletedCellphones.add(ShareData.worker.getCellphone());
            ShareData.workers.remove(ShareData.worker);
            ShareData.refresh();
            Tree.setUsersChanged();
            Tree.refresh();
            Chart.refresh();
            UI.hideMainLabel();
            UI.hideLabel();
            ModifyAvatar.clearAvatar();
        }
    }

    public static void add(String cellphone)
    {
        deletedCellphones.add(cellphone);
    }

    public static List<String> getData()
    {
        return deletedCellphones;
    }

    public static void syncDatabaseIfChanged()
    {
        if(!Check.isEmpty(deletedCellphones))
        {
            Tree.setUsersChanged();
            OKHTTP.send(new DeleteManyRequestBuilder().cellphone(Delete.getData()).build());
            deletedCellphones = new ArrayList<>();
        }
    }
}
