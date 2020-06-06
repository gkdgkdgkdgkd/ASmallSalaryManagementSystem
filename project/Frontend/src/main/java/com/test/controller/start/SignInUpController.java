package com.test.controller.start;

import com.test.network.OKHTTP;
import com.test.network.requestBuilder.crud.SignInUpRequestBuilder;
import com.test.utils.Check;
import com.test.view.GUI;
import com.test.view.MessageBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * 登录注册界面控制类
 */

public class SignInUpController {
    @FXML
    private TextField cellphone;
    @FXML
    private TextField password;

    public void signInUp()
    {
        String cellphoneStr = cellphone.getText();
        String passwordStr = password.getText();
        if(Check.isEmpty(cellphoneStr))
        {
            MessageBox.show("账号名不能为空");
            return;
        }
        if(Check.isEmpty(passwordStr))
        {
            MessageBox.show("密码不能为空");
            return;
        }
        OKHTTP.send(new SignInUpRequestBuilder().cellphone(cellphoneStr).password(passwordStr).build());
    }

    public void goToRetrievePassword()
    {
        GUI.switchToRetrievePassword();
    }

    public void focusOnCellphoneField()
    {
        cellphone.requestFocus();
    }

}
