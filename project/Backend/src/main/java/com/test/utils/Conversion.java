package com.test.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.entity.Worker;
import com.test.service.AvatarService;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Conversion {

    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

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

    public static List<String> stringToList(String s)
    {
        return Arrays.asList(s.split(","));
    }

    public static String avatarToString(Path path) throws IOException
    {
        return new String(encoder.encode(Files.readAllBytes(path)));
    }

    public static String avatarsToString(HashMap<String,String> map)
    {
        return gson.toJson(map);
    }

    public static void stringToAvatar(String base64Code, String cellphone) throws IOException
    {
        Files.write(AvatarService.getPath(cellphone), decoder.decode(base64Code));
    }

    public static HashMap<String,String> stringToAvatars(String str)
    {
        HashMap<?, ?> map = gson.fromJson(str, HashMap.class);
        if(Check.isEmpty(map))
            return null;
        HashMap<String,String> temp = new HashMap<>();
        map.forEach((k, v)->temp.put((String)k,(String)v));
        return temp;
    }

    public static String returnCodeToString(ReturnCode s)
    {
        Map<String,String> map = new HashMap<>();
        map.put(ReturnCode.STATUS_FIELD,s.name());
        map.put(ReturnCode.BODY_FIELD,s.emptyBody() ? null : s.body());
        return gson.toJson(map);
    }
}
