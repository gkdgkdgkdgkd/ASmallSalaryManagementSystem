package com.test.network.requestBuilder.crud;


import com.test.constant.AllURL;
import com.test.network.request.crud.CellphoneExistsRequest;

public class CellphoneExistsRequestBuilder {
    private final CellphoneExistsRequest request = new CellphoneExistsRequest();

    public CellphoneExistsRequestBuilder()
    {
        request.setUrl(AllURL.CELLPHONE_EXISTS_URL);
    }

    public CellphoneExistsRequestBuilder cellphone(String cellphone)
    {
        request.setCellphone(cellphone);
        return this;
    }

    public CellphoneExistsRequest build()
    {
        return request;
    }
}
