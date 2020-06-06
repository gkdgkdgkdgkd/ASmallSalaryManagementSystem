package com.test.network.request.avatar;


import com.test.constant.ReturnCode;
import com.test.network.request.BaseRequest;
import com.test.view.MessageBox;

/**
 * 上传单个头像
 */

public class UploadOneAvatarRequest extends BaseRequest {

    @Override
    public Object handleResult(ReturnCode code)
    {
        switch (code)
        {
            case UPLOAD_ONE_SUCCESS:
                MessageBox.show("上传成功");
                return true;
            case EMPTY_FILE:
                MessageBox.emptyFile();
                return false;
            case EMPTY_CELLPHONE:
                MessageBox.emptyCellphone();
                return false;
            case STRING_TO_AVATAR_FAILED:
                MessageBox.stringToAvatarFailed();
                return false;
            default:
                MessageBox.unknownError(code.name());
                return false;
        }
    }
}
