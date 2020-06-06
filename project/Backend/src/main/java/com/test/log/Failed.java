package com.test.log;

import org.slf4j.Logger;

public class Failed {
    private static final Logger l = L.getInstance();

    public static void saveOne(String worker, String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().worker(worker).message(message).build());
    }

    public static void saveAll(String cellhones,String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().cellphone(cellhones).message(message).build());
    }

    public static void deleteManyEntities(String cellphone, String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time()
            .cellphone(cellphone).message(message).build());
    }

    public static void deleteManyAvatars(String cellphone, String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time()
                .cellphone(cellphone).message(message).build());
    }

    public static void login(String cellphone, String password, String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time()
            .cellphone(cellphone).password(password).message(message).build());
    }

    public static void getOne(String cellphone, String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time()
            .cellphone(cellphone).message(message).build());
    }

    public static void getAll(String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().message(message).build());
    }

    public static void backup(String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().message(message).build());
    }

    public static void uploadOne(String cellphone, String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position()
            .time().cellphone(cellphone).message(message).build());
    }

    public static void downloadOne(String cellphone, String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position()
            .time().cellphone(cellphone).message(message).build());
    }

    public static void uploadAll(String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().message(message).build());
    }

    public static void downloadAll(String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().message(message).build());
    }

    public static void createUploadDir(String dir)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().message("create upload dir:"+dir+" failed.").build());
    }

    public static void sendSms(String cellphone,String code,String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().cellphone(cellphone).message(message).code(code).build());
    }

    public static void stringToAvatar(String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().message(message).build());
    }

    public static void avatarToString(String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().message(message).build());
    }

    public static void stringToAvatars(String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().message(message).build());
    }

    public static void fileVisit(String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().time().message(message).build());
    }

    public static void retrievePassword(String cellphone,String password,String message)
    {
        l.warn(new FormatterBuilder().title(getTitle()).warn().position().cellphone(cellphone).password(password).message(message).build());
    }

    private static String getTitle()
    {
        StackTraceElement [] t = Thread.currentThread().getStackTrace();
        return t[2].getMethodName()+" failed";
    }

}

