package com.test.log;

import org.slf4j.Logger;

public class Admin {
    private static final Logger l = L.getInstance();

    public static void login()
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().build());
    }

    private static String getTitle()
    {
        StackTraceElement [] t = Thread.currentThread().getStackTrace();
        return "admin " + t[2].getMethodName();
    }
}
