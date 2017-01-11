package com.source.protocol;


import com.jiongbull.jlog.JLog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.BaseRequest;
import com.source.util.CheckUtil;
import com.source.util.ToastUtil;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zhaoxu2014 on 15/11/17.
 */
public abstract class LibBaseStringProtocol extends LibBaseProtocol {
    public static final String TAG = "---protocol---";
    public static final String MEDIA_TYPE = "application/json; charset=utf-8";


    UUID uuid=null;
    public boolean isCancel = false;
    public boolean useCacheData = false;
    AbsCallback callback;

    public AbsCallback getCallback() {
        if(CheckUtil.isEmpty(callback)){
            callback=new MyStringCallback();
        }
        return callback;
    }
    public class MyStringCallback extends StringCallback
    {
        @Override
        public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
            if(null!=myCallback) {
                myCallback.upProgress(currentSize,  totalSize,  progress,  networkSpeed);
            }

        }

        @Override
        public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
            if(null!=myCallback) {
                myCallback.downloadProgress(currentSize,  totalSize,  progress,  networkSpeed);
            }
        }

        @Override
        public void onSuccess(String content, Call call, Response response) {
            onSucessAll(false,content,call);
        }

        @Override
        public void onCacheSuccess(String content, Call call) {
            onSucessAll(true,content,call);
        }

        @Override
        public void onError(Call call, Response response, Exception e) {
            onErrorAll(false,response,call,e);

        }
        @Override
        public void onCacheError(Call call, Exception e) {
            onErrorAll(true,null,call,e);
        }



        @Override
        public void onAfter(String s, Exception e) {
            cancel(false);
        }

    }

    private void onErrorAll(boolean isFromCache,Response response,Call call,Exception e){
        boolean failResult = false;
        if(!useCacheData) {
            if (isCancel) return;
            JSONObject js = null;
            int statusCode = DEFAULT_CODE;
            String body = "";
            try {
                body = response.body().string();
                js = new JSONObject(body);
                statusCode = js.optInt("code", DEFAULT_CODE);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            NetErrorEntity neterror = NetErrorEntity.fromJSON(js);


            if (CheckUtil.isEmpty(body)) {
                body = "";
            }
            JLog.d(TAG, "联网失败，，访问地址" + getUrl() + "---->statusCode:" + statusCode + " body:" + body);
            if (!CheckUtil.isEmpty(myCallback)) {
                failResult = myCallback.onFailure(isFromCache,call, neterror, e);
            }

            if (!failResult) {
                ToastUtil.showStringToast("获取数据失败");
            }
        }else{
            if (!CheckUtil.isEmpty(myCallback)) {
                failResult = myCallback.onFailure(isFromCache,call, null, e);
            }
            if (!failResult) {
                ToastUtil.showStringToast("获取数据失败");
            }
        }
    }

    private void onSucessAll(boolean isFromCache,String response,Call call){
        if (isCancel) return;
        if (CheckUtil.isEmpty(response)) {
            if (null != myCallback) {
                myCallback.onFailure(false,call,null, null);
            }
        }
        handleResult(isFromCache,response);
    }

    public void handleResult(boolean isFromCache,String response) {
        Object be = null;
        try {
            String result =response;
            JSONObject jsonObject = new JSONObject(result);
            Iterator it = jsonObject.keys();
            for (; it.hasNext(); ) {
                String name = (String) it.next();
                if (name.equals("code")) {
                    code = jsonObject.optInt("code", DEFAULT_CODE);
                } else if (name.equals("msg") || name.equals("message")) {
                    msg = jsonObject.optString(name);
                }

            }
            be = handleJSON(jsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != myCallback) {
                myCallback.onSuccess(isFromCache,isSuccess(), getMsg(), be);

            }

        }

    }


    public void execute( LibBaseCallBack myCallback) {
        execute( false, myCallback);
    }

    public void execute( boolean useCacheData, LibBaseCallBack myCallback) {
        this.useCacheData = useCacheData;
        this.myCallback = myCallback;
        isCancel = false;
        execute();
    }



    @Override
    protected void execute() {
        try {
            if (isCancel) return;
            if (!CheckUtil.isEmpty(myCallback)) {
                myCallback.onBefore();
            }

            BaseRequest request=null;
            if (isGetMode()) {
                JLog.d(TAG, "get方式--》请求地址Url:" + getNetUrl());

                request= OkGo.get(getUrl())
                        .params( getParams());

            }else {
                Object object=getJsonParams();
                if(!CheckUtil.isEmpty(object)&&object instanceof JSONObject){
                    JLog.d(TAG, "post方式--》请求地址url;" + getUrl() + "   -->JSONObject:" + object.toString());
                    request= OkGo.post(getUrl()).upJson(object.toString());
                }else{
                    JLog.d(TAG, "post方式--》请求地址url;" + getUrl() + "   -->getParams:" + getUrlParamsByMap(getParams()));
                    request= OkGo.post(getUrl()).params(getParams());
                }

            }
            uuid= UUID.randomUUID();
            request.headers(getHeaderMap()).tag(uuid).cacheKey(getCacheKey()).cacheMode(getCacheMode());
            request.execute(getCallback());



        } catch (Exception e) {
            JLog.e(TAG, "Action failed: " + e.getMessage());
            if (!CheckUtil.isEmpty(myCallback)) {
                myCallback.onFailure(false,null,null , e);
            }
        }
    }

    public String getCacheKey(){
        return getUrl();
    }
    public CacheMode getCacheMode(){
        if(useCacheData){
            return CacheMode.REQUEST_FAILED_READ_CACHE;
        }else{
            return CacheMode.NO_CACHE;
        }

    }

    private String getNetUrl() throws Exception {
        Object obj=getParams();
        if(isGetMode()&&obj instanceof HttpParams){
            return getUrl()+"?"+ getUrlParamsByMap(  (HttpParams)obj);
        }else {
            return getUrl();
        }

    }
    public static String getUrlParamsByMap(HttpParams map) throws Exception{
        if (CheckUtil.isEmpty(map)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        LinkedHashMap<String, List<String>> urlParamsMap=map.urlParamsMap;
        for(Map.Entry<String,  List<String>> entry:urlParamsMap.entrySet()){
            sb.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue().get(0), "UTF-8"));
            sb.append("&");
        }

        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0,s.length()-1);
        }
        return s;
    }

    public void cancel() {
        cancel(true);
    }
    public void cancel(boolean showLog) {
        if(showLog){
            JLog.d(TAG, "取消 url: " + getUrl() + "的接口");
        }
        isCancel = true;
        if (null != uuid) {
            OkGo.getInstance().cancelTag(uuid);
        }
    }
}
