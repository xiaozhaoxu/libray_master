package com.source.entity;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by zhaoxu2014 on 16/8/2.
 * 用法:
 *
 * <meta-data android:name="DATABASE" android:value="sugar.db" />
 <meta-data android:name="VERSION" android:value="30" />
 <meta-data android:name="QUERY_LOG" android:value="true" />
 *
 * public class App extends Application {
@Override
public void onCreate() {
super.onCreate();
SugarContext.init(this);
}

@Override
public void onTerminate() {
super.onTerminate();
SugarContext.terminate();
}
}

 Book book=new Book();
 book.name="赵旭的书12320人20";
 book.isFinish=true;
 Book.saveInTx(book);
 */
public class SugarBaseEntity extends SugarRecord   implements Serializable {
}
