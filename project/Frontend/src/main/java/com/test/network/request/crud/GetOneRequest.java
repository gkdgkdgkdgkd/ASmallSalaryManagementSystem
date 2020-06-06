package com.test.network.request.crud;


import com.test.constant.ReturnCode;
import com.test.network.request.BaseRequest;
import com.test.utils.Conversion;
import com.test.view.MessageBox;

/**
 * 获取单个实体类
 */

public class GetOneRequest extends BaseRequest {
    @Override
    public Object handleResult(ReturnCode code)
    {
        switch (code)
        {
            case EMPTY_CELLPHONE:
                MessageBox.emptyCellphone();
                return false;
            case INVALID_CELLPHONE:
                MessageBox.invalidCellphone();
                return false;
            case CELLPHONE_NOT_MATCH:
                MessageBox.show("获取失败，电话号码不匹配");
                return false;
            case EMPTY_WORKER:
                MessageBox.emptyWorker();
                return false;
            case GET_ONE_SUCCESS:
                return Conversion.JSONToWorker(code.body());
            default:
                MessageBox.unknownError(code.name());
                return false;
        }
    }
}
