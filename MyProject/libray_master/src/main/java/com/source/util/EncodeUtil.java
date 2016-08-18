package com.source.util;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: SlothMonkey
 * Date: 13-11-11
 * Time: 下午6:38
 * To change this template use File | Settings | File Templates.
 */
public class EncodeUtil {

    public static final String TAG = EncodeUtil.class.getSimpleName();

    /**
     * MD5加密
     *
     * @param pwd
     * @return
     */
    public static String encodeByMD5(String pwd) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");

            byte[] result = digest.digest(pwd.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                int data = (b & 0xff);
                String str = Integer.toHexString(data);
                if (str.length() == 1) {
                    sb.append("0");
                }
                sb.append(str);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // 不会发生异常
            return "";
        }
    }

    /**
     * Base64编码处理
     *
     * @param content 待编码字符
     * @return encoded content
     */
    public static String encodeByBase64(String content) {
        if (content == null) {
            Log.e(TAG, "Encode content is Null!");
            return null;
        }
        return android.util.Base64.encodeToString(content.getBytes(), android.util.Base64.NO_WRAP);
    }

}