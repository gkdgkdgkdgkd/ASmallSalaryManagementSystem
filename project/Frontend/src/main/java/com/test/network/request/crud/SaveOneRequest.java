package com.test.network.request.crud;


import com.test.constant.ReturnCode;
import com.test.network.request.BaseRequest;
import com.test.view.MessageBox;

/**
 * 保存单个实体类
 */

public class SaveOneRequest extends BaseRequest {
    @Override
    public Object handleResult(ReturnCode code)
    {
        switch (code)
        {
            case SAVE_ONE_SUCCESS:
                MessageBox.show("保存成功");
                return true;
            case EMPTY_WORKER:
                MessageBox.emptyWorker();
                return false;
            default:
                MessageBox.unknownError(code.name());
                return false;
        }
    }
}
