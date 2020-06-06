package com.test.network.requestBuilder.other;


import com.test.constant.AllURL;
import com.test.network.request.other.SendSmsRequest;
import com.test.utils.Check;
import com.test.view.MessageBox;

public class SendSmsRequestBuilder {
    private final SendSmsRequest request = new SendSmsRequest();

    public SendSmsRequestBuilder()
    {
        request.setUrl(AllURL.SEND_SMS_URL);
    }

    public SendSmsRequestBuilder cellphone(String cellphone)
    {
        if(Check.isEmpty(cellphone))
        {
            MessageBox.emptyCellphone();
            return null;
        }
        request.setCellphone(cellphone);
        return this;
    }

    public SendSmsRequest build()
    {
        return request;
    }
}
