package com.source.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaoxu2014 on 15/9/1.
 */
public class EmailUtil {

    // 判断格式是否为email
    public static  boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
