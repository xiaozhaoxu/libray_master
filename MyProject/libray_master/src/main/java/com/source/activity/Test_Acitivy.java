package com.source.activity;

import android.os.Bundle;
import com.source.R;

/**
 * Created by zhaoxu2014 on 15/9/19.
 */
public class Test_Acitivy extends BaseLibActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.testmain);
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
