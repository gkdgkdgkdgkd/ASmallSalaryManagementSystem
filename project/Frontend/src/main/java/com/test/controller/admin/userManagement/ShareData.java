package com.test.controller.admin.userManagement;

import com.test.controller.admin.userManagement.ui.UI;
import com.test.entity.Worker;
import com.test.network.OKHTTP;
import com.test.network.requestBuilder.avatar.DownloadAllAvatarsRequestBuilder;
import com.test.network.requestBuilder.crud.GetAllRequestBuilder;
import com.test.utils.AvatarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 共享数据类
 */

public class ShareData {

    public static Worker worker;

    public static List<Worker> workers;

    public static HashMap<String,String> images;

    public static void init()
    {
        getWorkers();
        getImages();
        refresh();
    }

    private static void getWorkers()
    {
        workers = new ArrayList<>();
        Object object = OKHTTP.send(new GetAllRequestBuilder().build());
        if (object instanceof ArrayList<?>) {
            for (Object o : (List<?>) object)
                workers.add((Worker) o);
        }
    }

    private static void getImages()
    {
        images = new HashMap<>();
        Object object = OKHTTP.send(new DownloadAllAvatarsRequestBuilder().build());
        if(object instanceof HashMap<?,?>)
        {
            HashMap<?,?> t = (HashMap<?, ?>) object;
            t.forEach((cellphone,imageStr)->
            {
                images.put((String)cellphone,(String)imageStr);
                AvatarUtils.stringToAvatar((String)imageStr,(String)cellphone);
            });
        }
    }

    public static void refresh()
    {
        update();
    }

    private static void update()
    {
    }

    public static boolean workerChanged()
    {
        return !worker.getName().equals(UI.nameText.getText())
                ||
                !worker.getPosition().equals(UI.positionText.getText())
                ||
                !worker.getDepartment().equals(UI.departmentText.getText());
    }

}
