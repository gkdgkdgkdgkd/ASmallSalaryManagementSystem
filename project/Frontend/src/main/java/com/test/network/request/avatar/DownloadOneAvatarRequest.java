package com.test.network.request.avatar;

import com.test.constant.ReturnCode;
import com.test.network.request.BaseRequest;
import com.test.utils.AvatarUtils;
import com.test.view.MessageBox;

/**
 * 下载单个头像
 */

public class DownloadOneAvatarRequest extends BaseRequest {
    @Override
    public Object handleResult(ReturnCode code)
    {
        switch (code)
        {
            case EMPTY_CELLPHONE:
                MessageBox.emptyCellphone();
                return false;
            case IMAGE_NOT_FOUND:
//                MessageBox.show("找不到图片");
                return false;
            case DOWNLOAD_ONE_SUCCESS:
                AvatarUtils.stringToAvatar(code.body(),cellphone);
                return true;
            case AVATAR_TO_STRING_FAILED:
                MessageBox.avatarToStringFailed();
                return false;
            default:
                MessageBox.unknownError(code.name());
                return false;
        }
    }
}
