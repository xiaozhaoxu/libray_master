package com.source.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-4
 * Time: 下午3:10
 * To change this template use File | Settings | File Templates.
 */
public class ActivityUtil {
    /*
     * 启动新的Activity
	 */
    @SuppressWarnings("rawtypes")
    public static void startActivity(Context context, Class target) {
        Intent intent = new Intent(context, target);
        context.startActivity(intent);
    }

    /*
     * 启动新的Activity并传递一定的参数
     */
    public static void startActivity(Context context, Class target,
                                     Bundle bundle) {
        Intent intent = new Intent(context, target);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
