package com.test.network.requestBuilder.avatar;

import com.test.constant.AllURL;
import com.test.network.request.avatar.UploadAllAvatarsRequest;

import java.util.HashMap;

public class UploadAllAvatarsRequestBuilder {
    private final UploadAllAvatarsRequest request = new UploadAllAvatarsRequest();

    public UploadAllAvatarsRequestBuilder()
    {
        request.setUrl(AllURL.UPLOAD_ALL_AVATARS_URL);
    }

    public UploadAllAvatarsRequestBuilder images(HashMap<String,String> images)
    {
        request.setAvatars(images);
        return this;
    }

    public UploadAllAvatarsRequest build()
    {
        return request;
    }
}
