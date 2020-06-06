package com.test.network.requestBuilder.avatar;


import com.test.constant.AllURL;
import com.test.network.request.avatar.DownloadOneAvatarRequest;
import com.test.utils.Check;
import com.test.view.MessageBox;

public class DownloadOneAvatarRequestBuilder {
    private final DownloadOneAvatarRequest request = new DownloadOneAvatarRequest();

    public DownloadOneAvatarRequestBuilder()
    {
        request.setUrl(AllURL.DOWNLOAD_ONE_AVATAR_URL);
    }

    public DownloadOneAvatarRequestBuilder cellphone(String cellphone)
    {
        if(Check.isEmpty(cellphone))
        {
            MessageBox.emptyCellphone();
            return null;
        }
        request.setCellphone(cellphone);
        return this;
    }

    public DownloadOneAvatarRequest build()
    {
        return request;
    }
}
