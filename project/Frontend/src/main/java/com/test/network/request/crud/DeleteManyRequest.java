package com.test.network.request.crud;


import com.test.constant.ReturnCode;
import com.test.network.request.BaseRequest;
import com.test.view.MessageBox;

/**
 * 删除多个实体类
 */

public class DeleteManyRequest extends BaseRequest {
    @Override
    public Object handleResult(ReturnCode code)
    {
        switch (code)
        {
            case DELETE_MANY_AVATARS_SUCCESS:
//                MessageBox.show("删除成功");
                return true;
            case DELETE_MANY_ENTITIES_SUCCESS:
                MessageBox.show("数据库删除成功，头像删除失败");
            case EMPTY_CELLPHONE:
                MessageBox.emptyCellphone();
                return false;
            default:
                MessageBox.unknownError(code.name());
                return false;
        }
    }
}
