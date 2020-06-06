package com.test.log;

import org.slf4j.Logger;

public class Success {
    private static final Logger l = L.getInstance();

    public static void saveOne(String worker)
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().worker(worker).build());
    }

    public static void deleteManyEntities(String cellphone)
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().cellphone(cellphone).build());
    }

    public static void deleteManyAvatars(String cellphone)
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().cellphone(cellphone).build());
    }

    public static void login(String cellphone, String password)
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().cellphone(cellphone).password(password).build());
    }

    public static void getOne(String cellphone)
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().cellphone(cellphone).build());
    }

    public static void backup()
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().build());
    }

    public static void upload(String cellphone)
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().cellphone(cellphone).build());
    }

    public static void downloadOne(String cellphone)
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().cellphone(cellphone).build());
    }

    public static void createUploadDir(String dir)
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().message("create upload dir:"+dir+" success.").build());
    }

    public static void sendSms(String cellphone,String code)
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().cellphone(cellphone).code(code).build());
    }

    public static void retrievePassword(String cellphone,String password)
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().cellphone(cellphone).password(password).build());
    }

    public static void getAll()
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().build());
    }

    public static void downloadAll()
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().build());
    }

    public static void uploadAll(String cellphones)
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().cellphone(cellphones).build());
    }

    public static void saveAll(String cellphones)
    {
        l.info(new FormatterBuilder().title(getTitle()).info().position().time().cellphone(cellphones).build());
    }

    private static String getTitle()
    {
        StackTraceElement [] t = Thread.currentThread().getStackTrace();
        return t[2].getMethodName()+" success";
    }
}

