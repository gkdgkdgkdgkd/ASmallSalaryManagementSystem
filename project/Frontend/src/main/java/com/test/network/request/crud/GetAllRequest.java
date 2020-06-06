package com.test.network.request.crud;


import com.test.constant.ReturnCode;
import com.test.network.request.BaseRequest;
import com.test.utils.Conversion;
import com.test.view.MessageBox;

/**
 * 获取所有实体类
 */

public class GetAllRequest extends BaseRequest {
    @Override
    public Object handleResult(ReturnCode code) {
        switch (code)
        {
            case EMPTY_DATABASE:
                MessageBox.emptyDatabase();
                return false;
            case GET_ALL_SUCCESS:
                return Conversion.JSONToWorkers(code.body());
            default:
                MessageBox.unknownError(code.name());
                return false;
        }
    }
}
