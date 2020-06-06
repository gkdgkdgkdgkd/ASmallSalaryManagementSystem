package com.test.network.requestBuilder.crud;


import com.test.constant.AllURL;
import com.test.network.request.crud.GetAllRequest;

public class GetAllRequestBuilder {
    private final GetAllRequest request = new GetAllRequest();

    public GetAllRequestBuilder()
    {
        request.setUrl(AllURL.GET_ALL_URL);
    }

    public GetAllRequest build()
    {
        return request;
    }
}
