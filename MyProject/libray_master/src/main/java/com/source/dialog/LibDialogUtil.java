package com.source.dialog;

import android.content.Context;

import com.source.util.CheckUtil;

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
        if (CheckUtil.isEmpty(context)) return null;
        PgyUpdateDialog dialog=new PgyUpdateDialog(context,result);
        if(dialog.isUpdate()){
            dialog.show();
        }
        return dialog;
    }
}
