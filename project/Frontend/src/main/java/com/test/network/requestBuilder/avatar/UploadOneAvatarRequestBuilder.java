package com.test.network.requestBuilder.avatar;






import com.test.constant.AllURL;
import com.test.network.request.avatar.UploadOneAvatarRequest;
import com.test.utils.Check;
import com.test.view.MessageBox;

import java.io.File;

public class UploadOneAvatarRequestBuilder {
    private final UploadOneAvatarRequest request = new UploadOneAvatarRequest();

    public UploadOneAvatarRequestBuilder()
    {
        request.setUrl(AllURL.UPLOAD_ONE_AVATAR_URL);
    }

    public UploadOneAvatarRequestBuilder file(File file)
    {
        if(Check.isEmpty(file))
        {
            MessageBox.emptyFile();
            return null;
        }
        request.setAvatar(file);
        return this;
    }

    public UploadOneAvatarRequestBuilder cellphone(String cellphone)
    {
        if(Check.isEmpty(cellphone))
        {
            MessageBox.emptyCellphone();
            return null;
        }
        request.setCellphone(cellphone);
        return this;
    }

    public UploadOneAvatarRequest build()
    {
        return request;
    }
}
