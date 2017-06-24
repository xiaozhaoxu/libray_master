package com.source.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.Window;

import com.source.R;


/**
 * Created by zhaoxu2014 on 17/5/27.
 */

public class LibLoadingDialog extends Dialog {
    public LibLoadingDialog(final Activity activity) {
        super(activity);
        // 设置没有自带标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置对话框的背景色为透明
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        setContentView(R.layout.dialog_loading);
        setCanceledOnTouchOutside(false);
        setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (isShowing()) dismiss();
                    activity.finish();
                }
                return false;
            }
        });
    }
}
