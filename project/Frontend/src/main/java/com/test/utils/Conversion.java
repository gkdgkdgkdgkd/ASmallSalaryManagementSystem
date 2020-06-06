package com.test.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import com.test.constant.ReturnCode;
import com.test.entity.Worker;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conversion {
    private static final Gson gson = new Gson();

    public static String workerToJSON(Worker worker)
    {
        return Check.isEmpty(worker) ? null : gson.toJson(worker);
    }

    public static String workerToJSON(List<Worker> workers)
    {
        return Check.isEmpty(workers) ? null : gson.toJson(workers,new TypeToken<List<Worker>>(){}.getType());
    }

    public static Worker JSONToWorker(String json)
    {
        return Check.isEmpty(json) ? null : gson.fromJson(json, Worker.class);
    }

    public static List<Worker> JSONToWorkers(String json)
    {
        if(Check.isEmpty(json))
            return null;
        return gson.fromJson(json,new ParameterizedType()
        {
            @NotNull
            @Override
            public Type[] getActualTypeArguments()
            {
                return new Type[]{Worker.class};
            }

            @NotNull
            @Override
            public Type getRawType()
            {
                return List.class;
            }

            @Override
            public Type getOwnerType()
            {
                return null;
            }

        });
    }

    public static String listToString(List<? extends CharSequence> lists)
    {
        return String.join(",",lists);
    }

    public static String mapToString(Map<String,Double> map)
    {
        if(Check.isEmpty(map))
            return null;
        return gson.toJson(map);
    }

    public static Map<String,Double> stringToMap(String str)
    {
        if(Check.isEmpty(str))
            return null;
        Map<?,?> m = gson.fromJson(str,Map.class);
        Map<String,Double> map = new HashMap<>(m.size());
        m.forEach((k,v)->map.put((String)k,(Double)v));
        return map;
    }

    public static ReturnCode stringToReturnCode(String str)
    {
        if(Check.isEmpty(str))
            return null;
        Map<?,?> map = gson.fromJson(str,Map.class);
        if(Check.isEmpty(map))
            return null;
        ReturnCode s = ReturnCode.valueOf((String)map.get(ReturnCode.STATUS_FIELD));
        s.body((String)map.get(ReturnCode.BODY_FIELD));
        return s;
    }
}
