package com.test.network.request.avatar;

import com.test.constant.ReturnCode;
import com.test.network.request.BaseRequest;
import com.test.view.MessageBox;

/**
 * 上传所有头像
 */

public class UploadAllAvatarsRequest extends BaseRequest {
    @Override
    public Object handleResult(ReturnCode code) {
        switch (code)
        {
            case UPLOAD_ALL_SUCCESS:
                MessageBox.show("上传成功");
                return true;
            case STRING_TO_AVATARS_FAILED:
                MessageBox.show("后端字符串转换失败");
                return false;
            default:
                MessageBox.unknownError(code.name());
                return false;
        }
    }
}
