package com.source.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.source.activity.permissions.PermissionsActivity;
import com.source.activity.permissions.PermissionsChecker;
import com.source.common.AppManager;
import com.source.util.CheckUtil;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by zhaoxu2014 on 15/9/19.
 */
public abstract class BaseLibActivity extends SwipeBackActivity {
    private static final int PERMISSIONS_REQUEST_CODE = 0; // 请求码
    public String[] PERMISSIONS = new String[]{};


    public Activity context;
    protected View mContentView;
    private SwipeBackLayout mSwipeBackLayout;//左右划返回上一级页面
    public   PermissionsChecker mPermissionsChecker; // 权限检测器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context=this;
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        mPermissionsChecker = new PermissionsChecker(this);

        initSwipeBack();



        if (mContentView == null) {
            initView(savedInstanceState);
            setListener();
            processLogic(savedInstanceState);
        }
    }


    public void setEdgeTrackingEnabled(int edgeFlags) {
        if (!CheckUtil.isEmpty(mSwipeBackLayout))
            mSwipeBackLayout.setEdgeTrackingEnabled(edgeFlags);
    }
    public void initSwipeBack() {
        initSwipeBack(SwipeBackLayout.EDGE_LEFT);
    }

    public void initSwipeBack(int edgeFlags) {
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(edgeFlags);
        setSwipeBackEnable(true);
//外面可以做一下划动监听
//        mSwipeBackLayout.addSwipeListener(new SwipeBackLayout.SwipeListener() {
//            @Override
//            public void onScrollStateChange(int state, float scrollPercent) {
//
//            }
//
//            @Override
//            public void onEdgeTouch(int edgeFlag) {
//                vibrate(VIBRATE_DURATION); //收到划动
//            }
//
//            @Override
//            public void onScrollOverThreshold() {
//                vibrate(VIBRATE_DURATION);//划动结束
//            }
//        });
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
     * 安卓6.0以下审请权限操作失败
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    protected abstract boolean  permissionsFailed();

    @Override protected void onResume() {
        super.onResume();

        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        }
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, PERMISSIONS_REQUEST_CODE, PERMISSIONS);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == PERMISSIONS_REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            if(!permissionsFailed()) {
                finish();
            }
        }
    }
}
