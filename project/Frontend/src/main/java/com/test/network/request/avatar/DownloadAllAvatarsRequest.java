package com.test.network.request.avatar;

import com.test.constant.ReturnCode;
import com.test.network.request.BaseRequest;
import com.test.utils.AvatarUtils;
import com.test.view.MessageBox;

/**
 * 下载所有头像
 */

public class DownloadAllAvatarsRequest extends BaseRequest {
    @Override
    public Object handleResult(ReturnCode code)
    {
        switch (code)
        {
            case FILE_VISIT_FAILED:
                MessageBox.show("服务器文件遍历失败");
                return false;
            case DOWNLOAD_ALL_SUCCESS:
                return AvatarUtils.stringToAvatars(code.body());
            default:
                MessageBox.unknownError(code.name());
                return false;
        }
    }
}
