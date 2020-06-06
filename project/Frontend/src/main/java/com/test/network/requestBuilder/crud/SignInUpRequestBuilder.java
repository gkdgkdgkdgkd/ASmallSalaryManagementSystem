package com.test.network.requestBuilder.crud;


import com.test.constant.AllURL;
import com.test.network.request.crud.SignInUpRequest;
import com.test.utils.Check;
import com.test.view.MessageBox;

public class SignInUpRequestBuilder {
    private final SignInUpRequest request = new SignInUpRequest();

    public SignInUpRequestBuilder()
    {
        request.setUrl(AllURL.SIGN_IN_UP_URL);
    }

    public SignInUpRequestBuilder cellphone(String cellphone)
    {
        if(Check.isEmpty(cellphone))
        {
            MessageBox.emptyCellphone();
            return null;
        }
        request.setCellphone(cellphone);
        return this;
    }

    public SignInUpRequestBuilder password(String password)
    {
        if(Check.isEmpty(password))
        {
            MessageBox.emptyPassword();
            return null;
        }
        request.setPassword(password);
        return this;
    }

    public SignInUpRequest build()
    {
        return request;
    }
}
