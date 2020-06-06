package com.test.network.requestBuilder.crud;


import com.test.constant.AllURL;
import com.test.network.request.crud.GetOneRequest;
import com.test.utils.Check;
import com.test.view.MessageBox;

public class GetOneRequestBuilder {
    private final GetOneRequest request = new GetOneRequest();

    public GetOneRequestBuilder()
    {
        request.setUrl(AllURL.GET_ONE_URL);
    }

    public GetOneRequestBuilder cellphone(String cellphone)
    {
        if(Check.isEmpty(cellphone))
        {
            MessageBox.emptyCellphone();
            return null;
        }
        request.setCellphone(cellphone);
        return this;
    }

    public GetOneRequest build()
    {
        return request;
    }

}
