package com.test.network.requestBuilder.crud;







import com.test.constant.AllURL;
import com.test.network.request.crud.DeleteManyRequest;
import com.test.utils.Check;
import com.test.utils.Conversion;
import com.test.view.MessageBox;

import java.util.List;

public class DeleteManyRequestBuilder {
    private final DeleteManyRequest request = new DeleteManyRequest();

    public DeleteManyRequestBuilder()
    {
        request.setUrl(AllURL.DELETE_MANY_URL);
    }

    public DeleteManyRequestBuilder cellphone(List<String> cellphone)
    {
        if(Check.isEmpty(cellphone))
        {
            MessageBox.emptyCellphone();
            return null;
        }
        request.setCellphone(Conversion.listToString(cellphone));
        return this;
    }

    public DeleteManyRequest build()
    {
        return request;
    }
}
