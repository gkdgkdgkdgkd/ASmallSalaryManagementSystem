package com.test.network.request.crud;


import com.test.constant.ReturnCode;
import com.test.network.request.BaseRequest;
import com.test.view.MessageBox;

/**
 * 判断电话是否存在
 */

public class CellphoneExistsRequest extends BaseRequest {
    @Override
    public Object handleResult(ReturnCode code) {
        switch (code)
        {
            case EMPTY_CELLPHONE:
                MessageBox.emptyCellphone();
                return false;
            case CELLPHONE_EXISTS:
                return true;
            case CELLPHONE_NOT_EXISTS:
                return false;
            default:
                MessageBox.unknownError(code.name());
                return false;
        }
    }
}
