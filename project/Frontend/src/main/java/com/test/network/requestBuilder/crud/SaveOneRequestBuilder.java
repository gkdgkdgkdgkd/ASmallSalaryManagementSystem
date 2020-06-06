package com.test.network.requestBuilder.crud;


import com.test.constant.AllURL;
import com.test.entity.Worker;
import com.test.network.request.crud.SaveOneRequest;
import com.test.utils.Check;
import com.test.view.MessageBox;

public class SaveOneRequestBuilder {
    private final SaveOneRequest request = new SaveOneRequest();

    public SaveOneRequestBuilder()
    {
        request.setUrl(AllURL.SAVE_ONE_URL);
    }

    public SaveOneRequestBuilder worker(Worker worker)
    {
        if(Check.isEmpty(worker))
        {
            MessageBox.emptyWorker();
            return null;
        }
        request.setWorker(worker);
        return this;
    }

    public SaveOneRequest build()
    {
        return request;
    }
}
