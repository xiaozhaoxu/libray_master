package com.source.protocol;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.utils.Md5Utils;
import com.source.protocol.requestbean.IRequest;
import com.source.util.JsonUtil;
import com.source.util.LogUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 接口数据请求基类
 * <p>
 * author: Shawn
 * time  : 2016/10/6 13:56
 */

public abstract class BaseProtocol {

    private static final String TAG = "BaseProtocol";

    protected int user_id;
    protected String api_key;

    /**
     * 给服务器传的 Json 串
     */
    protected String jsonString;

    /**
     * 缓存 Key
     */
    protected String cacheKey;

    /**
     * 随机数
     */
    protected String rand;

    /**
     * 环境变量
     */
    protected String env;

    /**
     * 时间戳
     */
    protected String timeStamp;

    /**
     * 签名
     */
    protected String sign;


    /**
     * Post 请求参数封装
     */
    protected IRequest mRequest;

    /**
     * Post 请求的 Url
     */
    protected String url;

    public BaseProtocol(IRequest request) {
        // 1. 初始化 JsonString
        mRequest = request;
        jsonString = initJsonString(request);
        LogUtil.d(TAG, "jsonString-->" + jsonString);

        // 2. 初始化 post 请求地址的基本参数
        initBasicValue();

        // 3. 创建 sign
        sign = creatSign(jsonString, api_key, user_id, rand, env, timeStamp);

        // 4. 初始化数据请求对的 Url
        url = initUrl();
        LogUtil.d(TAG, "url-->" + url);

        // 5. 创建缓存 Key 值
        cacheKey = initCacheKey(request);
    }

    /**
     * 拼接 Post 请求 Url, 部分账户相关操作无需 拼接 composeGetUrl()，那么则重写该方法覆盖之
     *
     * @return
     */
    protected String initUrl() {
        return getServerHost() + getApiAddress() + composeGetUrl();
    }

    /**
     * 获取接口域名，以下返回为默认值，如果为其它域名，子类需重写该方法
     *
     * @return
     */
    protected String getServerHost() {
        return Api.API_SERVER_HOST;
    }

    /**
     * 获取接口具体地址 子类必须重写
     *
     * @return
     */
    protected abstract String getApiAddress();

    /**
     * 初始化需要传输的 5 个 基本值
     */
    private void initBasicValue() {
        // 以下两个值在登录成功后在 Api 中 存一个常量，然后在本地 sharedPreference 存一份
//todo
//        user_id = App.user_id;
//        api_key = App.api_key;
        // 获取随机数
        rand = getRand();
        // 环境变量 // TODO: 2016/10/10 要根据环境更改
        env = "publish";
        // 获取时间戳
        timeStamp = getTimeStamp();
    }

    /**
     * 6. 做真正的网络请求
     */
    public abstract void doPostString(AbsCallback callback, Object tag);

    /**
     * 初始化 需要上传服务器的 Json 串
     */
    protected String initJsonString(IRequest request) {
        return  JsonUtil.bean2Json(request);
    }

    /**
     * 初始化 缓存 Key 值（域名 + 具体请求地址 + 有效请求参数）
     *
     * @param request
     * @return
     */
    protected String initCacheKey(IRequest request) {
        request.setExt("");
        cacheKey = getServerHost() + getApiAddress() +  JsonUtil.bean2Json(request);
        LogUtil.d(TAG, "CacheKey:" + cacheKey);
        return cacheKey;
    }


    /**
     * 获取签名
     *
     * @param jsonValue   参数 Json 串
     * @param key         登录后返回的 key
     * @param user_id     登陆后返回的 user_id
     * @param rand        随机数（> 8位）
     * @param environment 当前运行环境
     * @param cutTime     当前系统随机戳(= 10位)
     * @return
     */
    protected static String creatSign(String jsonValue, String key, int user_id,
                                      String rand, String environment, String cutTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(jsonValue);
        sb.append(key);
        sb.append(user_id);
        sb.append(rand);
        sb.append(environment);
        sb.append(cutTime);
        LogUtil.d(TAG, "Sign:" + sb.toString());
        return Md5Utils.encode(sb.toString());
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    protected String getTimeStamp() {
        String timeL = "" + System.currentTimeMillis();
        return timeL.substring(0, 10);
    }

    /**
     * 获取随机数
     *
     * @return
     */
    protected static String getRand() {
        Random random = new Random();
        int i = 100000000 + random.nextInt(10000000);
        return String.valueOf(i);

    }

    /**
     * get请求 url 拼接
     *
     * @return
     */
    protected String composeGetUrl() {
        Map<String, Object> map = new HashMap<>();
        map.put("sign", sign);
        map.put("user_id", user_id);
        map.put("time", timeStamp);
        map.put("rand", rand);
        map.put("env", env);

        StringBuffer sb = new StringBuffer();
        sb.append("?");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String temp = entry.getKey() + "=" + entry.getValue();
            sb.append(temp);
            sb.append("&");
        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
