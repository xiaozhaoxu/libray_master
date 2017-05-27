package com.lzy.okgo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

    /**
     * 密码的md5加密
     *
     * @param content
     * @return
     */
    public static String encode(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(content.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                //                int number = b & 0xff - 3;//加盐
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    sb.append("0");
                }
                sb.append(str);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

//    /**
//     * 获取字符串加密
//     *
//     * @param string
//     * @return
//     */
//    public static String encode(String string) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] bs = md.digest(string.getBytes());
//            StringBuilder sb = new StringBuilder();
//            for (byte b : bs) {
//                int num = b & 0xff;
//                String hex = Integer.toHexString(num);
//                if (hex.length() == 1) {
//                    sb.append("0");
//                }
//                sb.append(b);
//            }
//            return sb.toString();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
}
