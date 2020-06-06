package com.test.network.request.crud;


import com.test.constant.ReturnCode;
import com.test.network.request.BaseRequest;
import com.test.view.MessageBox;

/**
 * 修改密码
 */

public class RetrievePasswordRequest extends BaseRequest {
    @Override
    public Object handleResult(ReturnCode code) {
        switch (code)
        {
            case RETRIEVE_PASSWORD_SUCCESS:
                MessageBox.show("更改密码成功");
                return true;
            case EMPTY_CELLPHONE:
                MessageBox.emptyCellphone();
                return false;
            case EMPTY_PASSWORD:
                MessageBox.emptyPassword();
                return false;
            default:
                MessageBox.unknownError(code.name());
                return null;
        }
    }
}
