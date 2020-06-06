package com.test.network.request;

import com.test.constant.BuilderKeys;
import com.test.constant.ReturnCode;
import com.test.entity.Worker;
import com.test.utils.AvatarUtils;
import com.test.utils.Check;
import com.test.utils.Conversion;
import com.test.utils.Utils;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public abstract class BaseRequest {
    private FormBody.Builder formBodyBuilder = new FormBody.Builder();
    private String url;
    protected String cellphone;
    protected String password;

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }

    public RequestBody getBody()
    {
        return formBodyBuilder.build();
    }

    public void setWorker(Worker worker)
    {
        add(BuilderKeys.WORKER, Conversion.workerToJSON(worker));
    }

    public void setPassword(String p)
    {
        password = Utils.encrypt(p);
        if(Check.isEmpty(password))
            return;
        add(BuilderKeys.PASSWORD, password);
    }

    public void setWorkers(List<Worker> workers)
    {
        add(BuilderKeys.WORKERS,Conversion.workerToJSON(workers));
    }

    public void setAvatars(HashMap<String,String> avatars)
    {
        add(BuilderKeys.AVATARS, AvatarUtils.avatarsToString(avatars));
    }

    public void setCellphone(String cellphone)
    {
        add(BuilderKeys.CELLPHONE,this.cellphone = cellphone);
    }

    public void setAvatar(File avatar)
    {
        add(BuilderKeys.AVATAR, AvatarUtils.avatarToString(avatar.toPath()));
    }

    private void add(String key,String value)
    {
        formBodyBuilder = formBodyBuilder.add(key,value);
    }

    public abstract Object handleResult(ReturnCode code);
}
