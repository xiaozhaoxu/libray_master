package com.source.protocol;

import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONObject;

/**
 * Created by zhaoxu2014 on 15-1-31.
 */
public abstract class LibBaseProtocol {
    public static final int DEFAULT_CODE = -1;
    public String url = "";
    public LibBaseCallBack myCallback;
    protected int code;
    protected String msg;

    protected abstract String getUrl();

    protected HttpHeaders getHeaderMap() {
        HttpHeaders map = new HttpHeaders();
        map.put("platform", "android");
        return map;
    }

    protected abstract HttpParams getParams() throws Exception;
    protected abstract JSONObject getJsonParams() throws Exception;

    protected abstract boolean isGetMode();

    protected abstract void execute();


    protected abstract boolean handlerBufferData(String result);

    protected abstract Object handleJSON(JSONObject jsonObject) throws Exception;


    public boolean isSuccess() {
        return code == 200;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }




}
