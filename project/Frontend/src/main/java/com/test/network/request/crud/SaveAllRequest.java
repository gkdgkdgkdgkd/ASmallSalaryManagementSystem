package com.test.network.request.crud;


import com.test.constant.ReturnCode;
import com.test.network.request.BaseRequest;
import com.test.view.MessageBox;

/**
 * 保存实体类
 */

public class SaveAllRequest extends BaseRequest {
    @Override
    public Object handleResult(ReturnCode code) {
        switch (code)
        {
            case EMPTY_WORKER:
                MessageBox.emptyWorker();
                return false;
            case SAVE_ALL_SUCCESS:
//                MessageBox.show("保存成功");
                return true;
            default:
                MessageBox.unknownError(code.name());
                return null;
        }
    }
}
