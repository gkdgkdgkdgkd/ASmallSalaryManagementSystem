package com.test.constant;

import com.test.utils.Utils;
import java.util.Properties;

/**
 * OKHTTP指定发送到后端的URL
 */

public final class AllURL {
    public static final String SIGN_IN_UP_URL;
    public static final String DELETE_MANY_URL;
    public static final String SAVE_ONE_URL;
    public static final String GET_ONE_URL;
    public static final String UPLOAD_ONE_AVATAR_URL;
    public static final String DOWNLOAD_ONE_AVATAR_URL;

    public static final String UPLOAD_ALL_AVATARS_URL;
    public static final String DOWNLOAD_ALL_AVATARS_URL;
    public static final String SEND_SMS_URL;
    public static final String RETRIEVE_PASSWORD_URL;
    public static final String CELLPHONE_EXISTS_URL;
    public static final String GET_ALL_URL;
    public static final String SAVE_ALL_URL;

    static
    {
        Properties properties = Utils.getProperties();
        if (properties != null)
        {
            String baseUrl = properties.getProperty("baseurl") + properties.getProperty("port") + "/" + properties.getProperty("projectName");
            SIGN_IN_UP_URL = baseUrl + "signInUp";
            DELETE_MANY_URL = baseUrl + "deleteMany";
            SAVE_ONE_URL = baseUrl + "saveOne";
            GET_ONE_URL = baseUrl + "getOne";
            UPLOAD_ONE_AVATAR_URL = baseUrl + "uploadOneAvatar";
            DOWNLOAD_ONE_AVATAR_URL = baseUrl + "downloadOneAvatar";
            SEND_SMS_URL = baseUrl + "sendSms";
            RETRIEVE_PASSWORD_URL = baseUrl + "retrievePassword";
            CELLPHONE_EXISTS_URL = baseUrl + "cellphoneExists";
            GET_ALL_URL = baseUrl + "getAll";
            UPLOAD_ALL_AVATARS_URL = baseUrl + "uploadAllAvatars";
            DOWNLOAD_ALL_AVATARS_URL = baseUrl + "downloadAllAvatars";
            SAVE_ALL_URL = baseUrl + "saveAll";
        }
        else
            SIGN_IN_UP_URL = DELETE_MANY_URL = SAVE_ONE_URL = GET_ONE_URL =
            UPLOAD_ONE_AVATAR_URL = DOWNLOAD_ONE_AVATAR_URL = SEND_SMS_URL = RETRIEVE_PASSWORD_URL =
            CELLPHONE_EXISTS_URL = GET_ALL_URL = UPLOAD_ALL_AVATARS_URL = DOWNLOAD_ALL_AVATARS_URL =
            SAVE_ALL_URL = null;
    }
}
