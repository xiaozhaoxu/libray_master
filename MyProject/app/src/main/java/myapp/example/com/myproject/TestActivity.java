package myapp.example.com.myproject;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.source.activity.BaseLibActivity;

/**
 * Created by zhaoxu2014 on 17/5/25.
 */

@Route(path = "/test/activity1")
public class TestActivity extends BaseLibActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected boolean permissionsFailed() {
        return false;
    }
}
