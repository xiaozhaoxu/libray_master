package myapp.example.com.myproject;

import android.app.Application;

import com.source.application.LibaryConfigBuilder;

/**
 * Created by zhaoxu2014 on 16/8/18.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LibaryConfigBuilder.getInstance().init(this);
    }

    @Override
    public void onTerminate() {
        LibaryConfigBuilder.getInstance().onTerminate();
        super.onTerminate();
    }
}
