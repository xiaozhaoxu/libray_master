package com.source.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.source.common.AppManager;

/**
 * Created by zhaoxu2014 on 15/9/19.
 */
public abstract class BaseLibActivity extends FragmentActivity {
    public Activity context;
    protected View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context=this;
        AppManager.getAppManager().addActivity(this);
        // 修改状态栏颜色，4.4+生效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus();
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);

        tintManager.setStatusBarTintColor(Color.BLUE);//临时设置成了蓝色，外层可以再设置下
        //tintManager.setStatusBarTintResource(R.color.status_bar_bg);//通知栏所需颜色


        if (mContentView == null) {
            initView(savedInstanceState);
            setListener();
            processLogic(savedInstanceState);
        }
        super.onCreate(savedInstanceState);
    }
    @TargetApi(19)
    protected void setTranslucentStatus() {
        Window window = getWindow();
        // Translucent status bar
        window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // Translucent navigation bar
//        window.setFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }


    public void setContentView( int layoutResID) {
        mContentView = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(mContentView);
    }

    /**
     * 初始化View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);
}
