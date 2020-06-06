package com.test.log;

import org.slf4j.Logger;

public class Empty {
    private static final Logger l = L.getInstance();

    public static void worker()
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().message(emptyMessage("work")).build());
    }

    public static void avatar()
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().message(emptyMessage("avatar")).build());
    }

    public static void cellphone()
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().message(emptyMessage("cellphone")).build());
    }

    public static void password()
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().message(emptyMessage("password")).build());
    }

    private static String emptyMessage(String s)
    {
        return s + " is empty";
    }

    private static String getTitle()
    {
        StackTraceElement [] t = Thread.currentThread().getStackTrace();
        return t[2].getMethodName()+" empty";
    }
}
