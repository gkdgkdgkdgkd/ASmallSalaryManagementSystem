package com.test.log;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class L {
    private static final Logger l = LogManager.getLogger(L.class);

    public static void error(String message)
    {
        l.error(message);
    }

    public static void error(Throwable e)
    {
        l.error(ExceptionUtils.getStackTrace(e));
    }
}
