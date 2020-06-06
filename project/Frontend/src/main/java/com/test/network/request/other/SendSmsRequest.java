package com.test.network.request.other;


import com.test.constant.ReturnCode;
import com.test.network.request.BaseRequest;
import com.test.view.MessageBox;

/**
 * 发送短信
 */

public class SendSmsRequest extends BaseRequest {
    @Override
    public Object handleResult(ReturnCode code) {
        switch (code)
        {
            case SEND_SMS_SUCCESS:
                MessageBox.show("发送验证码成功");
                return code.body();
            case INVALID_CELLPHONE:
                MessageBox.invalidCellphone();
                return false;
            case EMPTY_CELLPHONE:
                MessageBox.emptyCellphone();
                return false;
            default:
                MessageBox.unknownError(code.name());
                return false;
        }
    }
}
