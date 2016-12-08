package com.source.protocol;

import com.lzy.okhttputils.model.HttpHeaders;
import com.lzy.okhttputils.model.HttpParams;

import org.json.JSONObject;

import okhttp3.Call;


/**
 * Created by zhaoxu2014 on 15-1-31.
 */
public abstract class HttpBaseProtocol {
    public static final int DEFAULT_CODE = -1;
    public String url = "";
    public CallBack myCallback;
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



    public interface CallBack {
        public void onStart();//开始联网

        public boolean onUseBufferDataAndCancelNetwork(Object object);//是否采用本地数据并打断联网

        public void onSuccess(boolean isSuccess, String msg, Object object);//联网成功

        public boolean onFailure(Call call, NetErrorEntity errorEntity);//联网失败
        public void onUpProgress(long currentSize, long totalSize, float progress, long networkSpeed);
    }

}
