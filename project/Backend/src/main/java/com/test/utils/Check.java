package com.test.utils;

import com.test.entity.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class Check {
    private static String adminCellphone;
    private static String adminPassword;

    @Value("${admin.cellphone}")
    public void setAdminCellphone(String cellphone)
    {
        Check.adminCellphone = cellphone;
    }

    @Value("${admin.password}")
    public void setAdminPassword(String password)
    {
        Check.adminPassword = password;
    }

    public static <K,V> boolean isEmpty(HashMap<K,V> map)
    {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(Worker worker)
    {
        return worker == null || isEmpty(worker.getCellphone()) || isEmpty(worker.getPassword());
    }

    public static boolean isEmpty(IOException e)
    {
        return e == null || isEmpty(e.getMessage());
    }

    public static boolean isEmpty(String t)
    {
        return t == null || t.isEmpty();
    }

    public static <T> boolean isEmpty(List<T> t)
    {
        return t == null || t.isEmpty();
    }

    public static boolean isGetAll(String cellphone)
    {
        return "*".equals(cellphone);
    }

    public static boolean isInvalidCellphone(String cellphone)
    {
        if(Check.isGetAll(cellphone))
            return false;
        String reg = "^[1][358][0-9]{9}$";
        return !(Pattern.compile(reg).matcher(cellphone).matches());
    }

    public static boolean isAdmin(String cellphone,String password)
    {
        return adminCellphone.equals(cellphone) && adminPassword.equals(password);
    }
}
