package com.source.util;

/**
 * Created by zhaoxu2014 on 15/7/29.
 */
public class PhoneNumUtil {
    public PhoneNumUtil() {
    }

    public static boolean isPhoneNum(String phoneNum) {
        return phoneNum.matches("^1\\d{10}$") || phoneNum.matches("^+861\\d{10}$") || phoneNum.matches("^861\\d{10}$");
    }
}
