package com.test.log;

import org.slf4j.Logger;

public class Invalid {
    private static final Logger l = L.getInstance();
    public static void cellphone(String cellphone)
    {
        l.warn(new FormatterBuilder().title("invalid cellphone").warn().position().time().cellphone(cellphone).build());
    }
}
