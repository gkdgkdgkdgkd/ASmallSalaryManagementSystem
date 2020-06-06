package com.test.network.request.crud;

import com.test.constant.ReturnCode;
import com.test.entity.Worker;
import com.test.network.OKHTTP;
import com.test.network.request.BaseRequest;
import com.test.network.requestBuilder.crud.GetOneRequestBuilder;
import com.test.view.GUI;
import com.test.view.MessageBox;

/**
 * 登录注册
 */

public class SignInUpRequest extends BaseRequest {
    @Override
    public Object handleResult(ReturnCode code)
    {
        switch (code)
        {
            case LOGIN_SUCCESS:
                MessageBox.show("登录成功");
                GUI.setWorker((Worker) (OKHTTP.send(new GetOneRequestBuilder().cellphone(cellphone).build())));
                GUI.switchToWorker();
                return true;
            case SAVE_ONE_SUCCESS:
                MessageBox.show("注册成功，自动登录");
                GUI.setWorker(new Worker(cellphone,password));
                GUI.switchToWorker();
                return true;
            case EMPTY_CELLPHONE:
                MessageBox.emptyCellphone();
                return false;
            case EMPTY_PASSWORD:
                MessageBox.emptyPassword();
                return false;
            case PASSWORD_NOT_MATCH:
                MessageBox.show("登录失败，密码错误");
                return false;
            case ADMIN:
                MessageBox.show("欢迎回来，管理员大人");
                GUI.switchToAdmin();
                return false;
            case INVALID_CELLPHONE:
                MessageBox.invalidCellphone();
                return false;
            case EMPTY_WORKER:
                MessageBox.emptyWorker();
                return false;
            default:
                MessageBox.unknownError(code.name());
                return false;
        }
    }
}
