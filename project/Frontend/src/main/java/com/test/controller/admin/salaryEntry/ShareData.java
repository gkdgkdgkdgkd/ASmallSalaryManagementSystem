package com.test.controller.admin.salaryEntry;

import com.test.entity.Worker;
import com.test.network.OKHTTP;
import com.test.network.requestBuilder.crud.GetAllRequestBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 共享数据类
 */

public class ShareData {

    public static Worker worker;
    public static List<Worker> workers;

    public static void init()
    {
        getWorkers();
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

    public static void refresh()
    {

    }
}
