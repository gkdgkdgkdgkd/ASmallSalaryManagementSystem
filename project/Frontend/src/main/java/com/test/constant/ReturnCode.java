package com.test.constant;

/**
 * 返回码枚举类，与后端略有不同。
 * body为携带的内容，两个静态字段供后端返回的String转换为Map并进一步转换为ReturnCode供前端使用
 */
public enum ReturnCode {
    LOGIN_SUCCESS, SAVE_ONE_SUCCESS, DELETE_MANY_ENTITIES_SUCCESS, DELETE_MANY_AVATARS_SUCCESS,
    UPLOAD_ONE_SUCCESS, DOWNLOAD_ONE_SUCCESS,GET_ONE_SUCCESS,GET_ALL_SUCCESS,
    RETRIEVE_PASSWORD_SUCCESS, UPLOAD_ALL_SUCCESS, SAVE_ALL_SUCCESS, DOWNLOAD_ALL_SUCCESS,
    SEND_SMS_SUCCESS,

    EMPTY_WORKER, EMPTY_FILE, EMPTY_CELLPHONE, EMPTY_PASSWORD,EMPTY_DATABASE,

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

    private String body;

    public String body()
    {
        return body;
    }

    public void body(String b)
    {
        body = b;
    }
}
