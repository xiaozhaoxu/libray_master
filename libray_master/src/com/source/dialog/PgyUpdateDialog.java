package com.source.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.UpdateManagerListener;
import com.source.R;
import com.source.download.file.DownFileUtil;
import com.source.util.*;

import java.io.File;
import java.util.UUID;

/**
 * Created by zhaoxu2014 on 15/9/1.
 */
public class PgyUpdateDialog extends Dialog implements DialogInterface {


    private static Context tmpContext;
    String result;
    AppBean appBean;

    TextView tv_update_content;


    public PgyUpdateDialog(Context context, String result) {
        super(context, com.source.R.style.dialog_untran);
        this.tmpContext = context;
        this.result=result;
        appBean = UpdateManagerListener.getAppBeanFromString(result);
        init(context);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width  = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes((WindowManager.LayoutParams) params);

    }


    private void init(Context context) {

        View rootView = View.inflate(context, R.layout.pgy_update_dialog, null);
        ButterKnife.bind(this,rootView);
        setContentView(rootView);
        tv_update_content= (TextView) rootView.findViewById(R.id.tv_update_content);
        rootView.findViewById(R.id.bt_update_id_cancel).setOnClickListener(dissClickListener);
        rootView.findViewById(R.id.bt_update_id_close).setOnClickListener(dissClickListener);
        rootView.findViewById(R.id.bt_update_id_ok).setOnClickListener(okClickListener);

        if(!CheckUtil.isEmpty(appBean)) {
            TextViewUtils.setText(tv_update_content, appBean.getReleaseNote());
        }
    }

    View.OnClickListener dissClickListener=new  View.OnClickListener(){

        @Override
        public void onClick(View view) {
            if(isShowing()){
               dismiss();
            }
        }
    };
    View.OnClickListener okClickListener=new  View.OnClickListener(){

        @Override
        public void onClick(View view) {

            if(isShowing()){
                dismiss();
            }
            DownFileUtil.getInstance().downLoad(appBean.getDownloadURL(), StorageUtil.getStorageDirectory(), UUID.randomUUID()+".apk",
                    new DownFileUtil.DownLoadCallBack() {
                        @Override
                        public void onFail() {
                            ToastUtil.showStringToast("下载失败!");
                        }

                        @Override
                        public void onSucceed(File file) {
                            tmpContext.startActivity(IntentUtil.installApk(file.getAbsolutePath()));
                            UpdateManagerListener.updateLocalBuildNumber(result);
                        }
                    });
        }
    };




    @Override
    public void show() {
        super.show();
    }



}