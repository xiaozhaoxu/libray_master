package com.source.protocol.callback;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.source.widget.LibLoadingDialog;

import okhttp3.Call;

/**
 * Created by zhaoxu2014 on 17/5/27.
 */

public abstract class StringDialogCallback extends StringCallback {
    private LibLoadingDialog loadingDialog;

    public StringDialogCallback(Activity activity) {
        loadingDialog = new LibLoadingDialog(activity);
    }

    @Override
    public void onBefore(BaseRequest request, int id) {
        super.onBefore(request, id);
        //网络请求前显示对话框
        if (loadingDialog != null && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }



    @Override
    public void onAfter(@Nullable String s, Exception e, int id) {
        super.onAfter(s, e, id);
        //网络请求结束后关闭对话框
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }


    @Override
    public void onCacheSuccess(String s, Call call, int id) {
        onSuccess(s,call,null,id);
        super.onCacheSuccess(s, call, id);
    }
}
