package com.source.download.file;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zhaoxu2014 on 16/5/23.
 */
public class DownFileUtil {
    private static DownFileUtil instance;

    public static DownFileUtil getInstance() {
        if (instance == null) {
            instance = new DownFileUtil();
        }
        return instance;
    }

    public void downLoad(String url ,String targetParent, String targetPath, final DownLoadCallBack callBack) {
        OkGo.get(url)
                .execute(new FileCallback(targetParent,targetPath){
                    @Override
                    public void onSuccess(File file, Call call, Response response, int id) {
                        callBack.onSucceed(file,id);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e, int id) {
                        callBack.onFail(id);
                        super.onError(call, response, e, id);
                    }

                });

    }

    public interface DownLoadCallBack {
        void onFail( int id);

        void onSucceed(File file, int id);
    }
}
