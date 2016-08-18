package com.source.dialog;

import android.app.Activity;
import android.content.Context;

/**
 * Created by zhaoxu2014 on 16/5/21.
 */
public class LibDialogUtil {
    private static LibDialogUtil instance;

    public static LibDialogUtil getInstance() {
        if (instance == null) {
            instance = new LibDialogUtil();
        }
        return instance;
    }
    public PgyUpdateDialog showUpdateDialog(Context context,String result){
        PgyUpdateDialog dialog=new PgyUpdateDialog(context,result);
        dialog.show();
        return dialog;
    }
}
