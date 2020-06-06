package com.test.network.requestBuilder.avatar;


import com.test.constant.AllURL;
import com.test.network.request.avatar.DownloadAllAvatarsRequest;

public class DownloadAllAvatarsRequestBuilder {
    private final DownloadAllAvatarsRequest request = new DownloadAllAvatarsRequest();
    public DownloadAllAvatarsRequestBuilder()
    {
        request.setUrl(AllURL.DOWNLOAD_ALL_AVATARS_URL);
    }
    public DownloadAllAvatarsRequest build()
    {
        return request;
    }
}
