package com.test.network.requestBuilder.crud;





import com.test.constant.AllURL;
import com.test.entity.Worker;
import com.test.network.request.crud.SaveAllRequest;

import java.util.List;

public class SaveAllRequestBuilder {
    private final SaveAllRequest request = new SaveAllRequest();

    public SaveAllRequestBuilder()
    {
        request.setUrl(AllURL.SAVE_ALL_URL);
    }

    public SaveAllRequestBuilder workers(List<Worker> workers)
    {
        request.setWorkers(workers);
        return this;
    }

    public SaveAllRequest build()
    {
        return request;
    }
}
