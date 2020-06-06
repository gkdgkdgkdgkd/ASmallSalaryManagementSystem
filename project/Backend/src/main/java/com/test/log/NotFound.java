package com.test.log;

import org.slf4j.Logger;

public class NotFound {
    private static final Logger l = L.getInstance();
    public static void page()
    {
        l.warn(new FormatterBuilder().title("page not found").warn().position().time().build());
    }
}
