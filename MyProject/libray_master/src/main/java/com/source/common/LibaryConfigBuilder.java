package com.source.common;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.jiongbull.jlog.JLog;
import com.jiongbull.jlog.constant.LogLevel;
import com.jiongbull.jlog.constant.ZoneOffset;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.orm.SugarContext;
import com.source.util.BundleUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by zhaoxu2014 on 15-1-31.
 */
public  class LibaryConfigBuilder {

    public static String app_identity="ibrightech";
    public static Application context;
    public static SharedPreferences preferences;

    private static LibaryConfigBuilder mInstance;                 //单例
    public Context getContext() {
        return context;
    }
    public static LibaryConfigBuilder getInstance() {
        if (mInstance == null) {
            synchronized (LibaryConfigBuilder.class) {
                if (mInstance == null) {
                    mInstance = new LibaryConfigBuilder();
                }
            }
        }
        return mInstance;
    }

    public void init(@NonNull Application application) {
        this.init(application, true);
    }

    public void init(@NonNull Application application, boolean debug) {
        this.context=application;

        //强制设置时区
//        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Locale.setDefault(Locale.US);


        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = info.metaData;
            app_identity = BundleUtil.getStringFormBundle(bundle, "app_identify",app_identity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        initDB();
        intNet(application);

        List<LogLevel> logLevels = new ArrayList<LogLevel>();
        logLevels.add(LogLevel.VERBOSE);
        logLevels.add(LogLevel.DEBUG);
        logLevels.add(LogLevel.INFO);
        logLevels.add(LogLevel.WARN);
        logLevels.add(LogLevel.WTF);
        logLevels.add(LogLevel.ERROR);
        logLevels.add(LogLevel.JSON);
        JLog.init(this.context)
                .setDebug(debug)
                .writeToFile(true)
                .setLogLevelsForFile(logLevels)
                .setLogDir(app_identity+File.separator+"log")
                .setZoneOffset(ZoneOffset.P0800);
    }

    private void intNet(Application application){
        //必须调用初始化
        OkGo.init(application);

        try{
            OkGo.getInstance()
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间
                    .setCacheMode(CacheMode.NO_CACHE)
                    .setRetryCount(3)
                    .setCookieStore(new PersistentCookieStore())
                    .setCertificates();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void initDB(){
        SugarContext.init(this.context);
    }


    //关闭数据库
    public void onTerminate(){
        SugarContext.terminate();
    }
}
