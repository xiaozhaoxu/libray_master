package com.source.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import com.jiongbull.jlog.JLog;
import com.jiongbull.jlog.constant.LogSegment;
import com.jiongbull.jlog.constant.ZoneOffset;
import com.lzy.okhttputils.OkHttpUtils;
import com.orm.SugarContext;
import com.source.util.BundleUtil;
import com.source.util.StorageUtil;

import java.io.File;
import java.util.Locale;

/**
 * Created by zhaoxu2014 on 15-1-31.
 */
public abstract class BaseApplication extends Application{

    public static String app_identity="ibrightech";
    public static Context context;
    public static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

        //强制设置时区
//        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Locale.setDefault(Locale.US);


        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = info.metaData;
            app_identity = BundleUtil.getStringFormBundle(bundle, "app_identify");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        initDB();

        OkHttpUtils.init(this);
        OkHttpUtils.getInstance()//
                .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)               //全局的连接超时时间
                .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                  //全局的读取超时时间
                .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS);

        JLog.init(this)
                .writeToFile(true)
                .setLogDir(app_identity+File.separator+"log")
                .setZoneOffset(ZoneOffset.P0800);
    }


    public  void initDB(){
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
