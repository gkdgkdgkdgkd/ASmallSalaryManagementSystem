package com.test.network.requestBuilder.crud;


import com.test.constant.AllURL;
import com.test.network.request.crud.RetrievePasswordRequest;
import com.test.utils.Check;
import com.test.view.MessageBox;

public class RetrievePasswordRequestBuilder {
    private final RetrievePasswordRequest request = new RetrievePasswordRequest();

    public RetrievePasswordRequestBuilder()
    {
        request.setUrl(AllURL.RETRIEVE_PASSWORD_URL);
    }

    public RetrievePasswordRequestBuilder cellphone(String cellphone)
    {
        if(Check.isEmpty(cellphone))
        {
            MessageBox.emptyCellphone();
            return null;
        }
        request.setCellphone(cellphone);
        return this;
    }

    public RetrievePasswordRequestBuilder password(String password)
    {
        if(Check.isEmpty(password))
        {
            MessageBox.emptyPassword();
            return null;
        }
        request.setPassword(password);
        return this;
    }

    public RetrievePasswordRequest build()
    {
        return request;
    }
}
