package myapp.example.com.myproject;

import android.app.Application;

import com.source.common.LibaryConfigBuilder;

/**
 * Created by zhaoxu2014 on 16/8/18.
 */
public class App extends Application {
//    //检测内存泄漏
//    public static RefWatcher refWatcher = null;
//    public static RefWatcher getRefWatcher() {
//        return refWatcher;
//    }
    @Override
    public void onCreate() {
        super.onCreate();
        LibaryConfigBuilder.getInstance().init(this);

//        refWatcher = LeakCanary.install(this);
    }

    @Override
    public void onTerminate() {
        LibaryConfigBuilder.getInstance().onTerminate();
        super.onTerminate();
    }
}
