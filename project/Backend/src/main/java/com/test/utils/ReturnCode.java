package com.test.utils;

public enum ReturnCode {
    LOGIN_SUCCESS, SAVE_ONE_SUCCESS, DELETE_MANY_ENTITIES_SUCCESS, DELETE_MANY_AVATARS_SUCCESS,
    UPLOAD_ONE_SUCCESS, DOWNLOAD_ONE_SUCCESS,GET_ONE_SUCCESS,GET_ALL_SUCCESS,
    RETRIEVE_PASSWORD_SUCCESS, UPLOAD_ALL_SUCCESS, SAVE_ALL_SUCCESS, DOWNLOAD_ALL_SUCCESS,
    SEND_SMS_SUCCESS,

    EMPTY_WORKER, EMPTY_AVATAR, EMPTY_CELLPHONE, EMPTY_PASSWORD,EMPTY_DATABASE,

    PAGE_NOT_FOUND, IMAGE_NOT_FOUND,

    INVALID_CELLPHONE,

    CELLPHONE_NOT_MATCH, PASSWORD_NOT_MATCH,

    STRING_TO_AVATAR_FAILED,AVATAR_TO_STRING_FAILED,FILE_VISIT_FAILED,
    STRING_TO_AVATARS_FAILED,

    CELLPHONE_EXISTS, CELLPHONE_NOT_EXISTS,

    ADMIN,

    UNKNOWN_ERROR;

    public static String STATUS_FIELD = "status";
    public static String BODY_FIELD = "body";

    private String message;
    private String body;
    private String cellphone;

    public String body()
    {
        return body;
    }

    public void body(String b)
    {
        body = b;
    }

    public String message()
    {
        return message;
    }

    public void message(String m)
    {
        message = m;
    }

    public boolean emptyBody()
    {
        return Check.isEmpty(body);
    }

    public void cellphone(String c)
    {
        cellphone = c;
    }

    public String cellphone()
    {
        return cellphone;
    }
}
