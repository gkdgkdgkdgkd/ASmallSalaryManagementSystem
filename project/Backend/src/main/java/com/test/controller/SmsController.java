package com.test.controller;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.test.log.L;
import com.test.utils.Check;
import com.test.utils.Conversion;
import com.test.utils.ReturnCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/")
@CrossOrigin(value = "http://localhost:3000")
public class SmsController {
    @Value("${tencent.secret.id}")
    private String secretId;

    @Value("${tencent.secret.key}")
    private String secretKey;

    @Value("${tencent.app.id}")
    private String appId;

    @Value("${tencent.sign}")
    private String sign;

    @Value("${tencent.template.id}")
    private String templateId;

    @PostConstruct
    public void init()
    {
        sign = new String(sign.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
    }

    @GetMapping("sendSms")
    public @ResponseBody String pageNotFound()
    {
        L.pageNotFound();
        return ReturnCode.PAGE_NOT_FOUND.name();
    }

    @PostMapping("sendSms")
    public @ResponseBody String sendSms(@RequestParam String cellphone)
    {
        String randomCode = RandomStringUtils.randomNumeric(6);
        if(Check.isEmpty(cellphone))
        {
            L.sendSmsFailed("null",randomCode,"cellphone is empty");
            return toStr(ReturnCode.EMPTY_CELLPHONE);
        }
        if(Check.isInvalidCellphone(cellphone))
        {
            L.sendSmsFailed(cellphone,randomCode,"cellphone is not valid.");
            return toStr(ReturnCode.INVALID_CELLPHONE);
        }
        ReturnCode s = ReturnCode.SEND_SMS_SUCCESS;
        try
        {
            SmsClient client = new SmsClient(new Credential(secretId,secretKey),"");
            SendSmsRequest request = new SendSmsRequest();
            request.setSmsSdkAppid(appId);
            request.setSign(sign);
            request.setTemplateID(templateId);

            String [] templateParamSet = {randomCode};
            request.setTemplateParamSet(templateParamSet);

            String [] phoneNumbers = {"+86"+cellphone};
            request.setPhoneNumberSet(phoneNumbers);
            SendSmsResponse response = client.SendSms(request);

            if(response != null && response.getSendStatusSet()[0].getCode().equals("Ok"))
            {
                L.sendSmsSuccess(cellphone,randomCode);
                s.body(randomCode);
            }
        } catch (Exception e) {
            L.sendSmsFailed(cellphone,randomCode,e.getMessage());
            s = ReturnCode.UNKNOWN_ERROR;
        }
        return toStr(s);
    }

    private static String toStr(ReturnCode s)
    {
        return Conversion.returnCodeToString(s);
    }
}
