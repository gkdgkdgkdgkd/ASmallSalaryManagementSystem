package com.test.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class L {
    private static final Logger l = LoggerFactory.getLogger(L.class);
    public static Logger getInstance()
    {
        return l;
    }
    public static void error(String message)
    {
        l.error(message);
    }

    public static void info(String message)
    {
        l.info(message);
    }

    public static void warn(String message)
    {
        l.warn(message);
    }

    public static void pageNotFound()
    {
        NotFound.page();
    }

    //CRUD:CU
    public static void saveOneSuccess(String worker)
    {
        Success.saveOne(worker);
    }

    public static void saveOneFailed(String worker, String message)
    {
        Failed.saveOne(worker,message);
    }

    public static void saveAllSuccess(String cellphones)
    {
        Success.saveAll(cellphones);
    }

    public static void saveAllFailed(String cellphones,String message)
    {
        Failed.saveAll(cellphones,message);
    }

    //CRUD:R
    public static void getOneSuccess(String cellphone)
    {
        Success.getOne(cellphone);
    }

    public static void getOneFailed(String cellphone, String message)
    {
        Failed.getOne(cellphone,message);
    }

    public static void getAllSuccess()
    {
        Success.getAll();
    }

    public static void getAllFailed(String message)
    {
        Failed.getAll(message);
    }

    //CRUD:D
    public static void deleteManyEntitiesSuccess(String cellphone)
    {
        Success.deleteManyEntities(cellphone);
    }

    public static void deleteMantEntitiesFailed(String cellphone, String message)
    {
        Failed.deleteManyEntities(cellphone,message);
    }

    public static void deleteManyAvatarsSuccess(String cellphone)
    {
        Success.deleteManyAvatars(cellphone);
    }

    public static void deleteMantAvatarsFailed(String cellphone, String message) {
        Failed.deleteManyAvatars(cellphone, message);
    }

    public static void loginSuccess(String cellphone,String password)
    {
        Success.login(cellphone,password);
    }

    public static void loginFailed(String cellphone,String password,String message)
    {
        Failed.login(cellphone,password,message);
    }

    public static void backupSuccess()
    {
        Success.backup();
    }

    public static void backupFailed(String message)
    {
        Failed.backup(message);
    }

    //upload
    public static void uploadOneSuccess(String filename)
    {
        Success.upload(filename);
    }

    public static void uploadOneFailed(String filename, String message)
    {
        Failed.uploadOne(filename,message);
    }

    public static void uploadAllSuccess(String cellphones)
    {
        Success.uploadAll(cellphones);
    }

    public static void uploadAllFailed(String message)
    {
        Failed.uploadAll(message);
    }

    //download
    public static void downloadOneSuccess(String filename)
    {
        Success.downloadOne(filename);
    }

    public static void downloadOneFailed(String cellphone,String message)
    {
        Failed.downloadOne(cellphone,message);
    }

    public static void downloadAllSuccess()
    {
        Success.downloadAll();
    }

    public static void downloadAllFailed(String message)
    {
        Failed.downloadAll(message);
    }

    public static void createUploadDirSuccess(String dir)
    {
        Success.createUploadDir(dir);
    }

    public static void createUploadDirFailed(String dir)
    {
        Failed.createUploadDir(dir);
    }

    public static void sendSmsSuccess(String cellphone,String code)
    {
        Success.sendSms(cellphone,code);
    }

    public static void sendSmsFailed(String cellphone,String code,String message)
    {
        Failed.sendSms(cellphone,code,message);
    }

    public static void invalidCellphone(String cellphone)
    {
        Invalid.cellphone(cellphone);
    }

    public static void stringToAvatarFailed(String message)
    {
        Failed.stringToAvatar(message);
    }

    public static void avatarToStringFailed(String message)
    {
        Failed.avatarToString(message);
    }

    public static void stringToAvatarsFailed(String message)
    {
        Failed.stringToAvatars(message);
    }

    public static void retrievePasswordSuccess(String cellphone,String password)
    {
        Success.retrievePassword(cellphone,password);
    }

    public static void retrievePasswordFailed(String cellphone,String password,String message)
    {
        Failed.retrievePassword(cellphone,password,message);
    }


    public static void adminLogin()
    {
        Admin.login();
    }


    public static void fileVisitFailed(String message)
    {
        Failed.fileVisit(message);
    }

    //empty
    public static void emptyWorker()
    {
        Empty.worker();
    }

    public static void emptyCellphone()
    {
        Empty.cellphone();
    }

    public static void emptyAvatar()
    {
        Empty.avatar();
    }

    public static void emptyPassword()
    {
        Empty.password();
    }

}
