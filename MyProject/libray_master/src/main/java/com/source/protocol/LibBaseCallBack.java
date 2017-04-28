package com.source.protocol;


import okhttp3.Call;

/**
 * Created by zhaoxu2014 on 17/1/10.
 */

public abstract class LibBaseCallBack {

    public abstract void onBefore(int Tag);//开始联网
    public abstract void onAfter(int Tag);


    public abstract void onSuccess(int Tag,boolean isFromCache,boolean isSuccess, String msg, Object object);//联网成功

    public abstract boolean onFailure(int Tag,boolean isFromCache,Call call, NetErrorEntity errorEntity, Exception e);//联网失败

    public void upProgress(int Tag,long currentSize, long totalSize, float progress, long networkSpeed){
        // UI 线程，文件上传过程中回调，只有请求方式包含请求体才回调（GET,HEAD不会回调）
        // currentSize  当前上传的大小（单位字节）
        // totalSize 　 需要上传的总大小（单位字节）
        // progress     当前上传的进度，范围　0.0f ~ 1.0f
        // networkSpeed 当前上传的网速（单位秒）
    }

    public void downloadProgress(int Tag,long currentSize, long totalSize, float progress, long networkSpeed){
        // UI 线程，文件下载过程中回调
        //参数含义同　上传相同
    }

}
