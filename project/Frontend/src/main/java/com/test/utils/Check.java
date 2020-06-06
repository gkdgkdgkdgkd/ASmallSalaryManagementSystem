package com.test.utils;




import com.test.constant.ReturnCode;
import com.test.entity.Worker;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Check {
    private static final String getAll = "*";

    public static <T> boolean isEmpty(T value)
    {
        return value == null;
    }

    public static boolean isEmpty(Integer value)
    {
        return value == null;
    }

    public static boolean isEmpty(Integer [] value)
    {
        return value == null || value.length == 0;
    }

    public static <K,V> boolean isEmpty(HashMap<K,V> map)
    {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(Double value)
    {
        return value == null || value.isInfinite() || value.isNaN();
    }

    public static <T> boolean isEmpty(ArrayList<T> value)
    {
        return value == null || value.isEmpty();
    }

    public static boolean isEmpty(Path path)
    {
        return path == null || !Files.exists(path);
    }

    public static boolean isEmpty(Worker worker)
    {
        return worker == null || isEmpty(worker.getCellphone()) || isEmpty(worker.getPassword());
    }

    public static boolean isEmpty(File file)
    {
        return file == null || file.isDirectory() || file.length() == 0 || Check.isEmpty(file.getName());
    }

    public static boolean isEmpty(String t)
    {
        return t == null || t.isEmpty();
    }

    public static <T> boolean isEmpty(List<T> t)
    {
        return t == null || t.isEmpty();
    }

    public static boolean isSendSmsFailed(String code)
    {
        return ReturnCode.UNKNOWN_ERROR.name().equals(code) || ReturnCode.INVALID_CELLPHONE.name().equals(code)
                || ReturnCode.EMPTY_CELLPHONE.name().equals(code);
    }

    public static boolean isInvalidCellphone(String cellphone)
    {
        if(Check.isGetAll(cellphone))
            return false;
        String reg = "^[1][358][0-9]{9}$";
        return !(Pattern.compile(reg).matcher(cellphone).matches());
    }

    public static String getAll()
    {
        return getAll;
    }

    public static boolean isGetAll(String cellphone)
    {
        return getAll.equals(cellphone);
    }
}
