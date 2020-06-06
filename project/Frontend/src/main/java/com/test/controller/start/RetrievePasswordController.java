package com.test.controller.start;

import com.test.network.OKHTTP;
import com.test.network.requestBuilder.crud.CellphoneExistsRequestBuilder;
import com.test.network.requestBuilder.crud.RetrievePasswordRequestBuilder;
import com.test.network.requestBuilder.other.SendSmsRequestBuilder;
import com.test.utils.Check;
import com.test.view.MessageBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


/**
 * 找回密码界面控制类
 */

public class RetrievePasswordController {

    @FXML
    private TextField cellphone;
    @FXML
    private TextField code;
    @FXML
    private TextField password1;
    @FXML
    private TextField password2;

    private String returnCode;
    private boolean sendSmsFailed = true;

    public void send()
    {
        if(!Check.isEmpty(cellphone.getText()))
        {
            if(cellphoneExists() && !Check.isInvalidCellphone(cellphone.getText()))
            {
                returnCode = (String) OKHTTP.send(new SendSmsRequestBuilder().cellphone(cellphone.getText()).build());
                sendSmsFailed = Check.isSendSmsFailed(returnCode);
            }
            else if(Check.isInvalidCellphone(cellphone.getText()))
                MessageBox.invalidCellphone();
            else
                MessageBox.show("手机号不存在,请注册该手机号");
        }
        else
            MessageBox.show("请输入电话");
    }

    private boolean isAllFilled()
    {
        return !Check.isEmpty(cellphone.getText()) && !Check.isEmpty(code.getText())
                &&
                !Check.isEmpty(password1.getText()) && !Check.isEmpty(password2.getText());
    }

    public void reset()
    {
        if(isAllFilled())
        {
            if(!cellphoneExists())
                MessageBox.show("手机号不存在,请注册该手机号");
            else if (!sendSmsFailed)
            {
                if (!returnCode.equals(code.getText()))
                {
                    MessageBox.show("验证码错误");
                    return;
                }
                else if (!password1.getText().equals(password2.getText()))
                {
                    MessageBox.show("密码不一致");
                    return;
                }
                OKHTTP.send(new RetrievePasswordRequestBuilder().cellphone(cellphone.getText())
                        .password(password1.getText()).build());
                sendSmsFailed = true;
            }
            else
                MessageBox.show("验证码发送失败");
        }
        else
            MessageBox.show("请不要把信息留空");
    }

    private boolean cellphoneExists()
    {
        Object result = (OKHTTP.send(new CellphoneExistsRequestBuilder().cellphone(cellphone.getText()).build()));
        if(result == null)
            return false;
        return (boolean)result;
    }
}
