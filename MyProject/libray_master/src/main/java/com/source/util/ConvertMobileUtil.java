package com.source.util;


/**
 * Created by yrhr on 2015/7/8.
 * �ֻ��5-8λ*����
 */
public class ConvertMobileUtil {

    public static String convertMobile(String txt_num) {
        String mobile = null;
        String temp = null;
        String mixStar = "";
        if (!StringUtils.isEmpty(txt_num)) {
            temp = txt_num.replaceAll("", "");
            if (temp.length() == 11) {
                mobile = temp.replace(temp.substring(4, 8), "****");
            } else if (temp.length() > 7 && temp.length() != 11) {
                int length = temp.length();
                String before3 = temp.substring(0, 3);
                String end4 = temp.substring(length - 4, length);
//                Lg.d("SUBsTRING:",before3+"----"+end4);
                for (int i = 0; i < length - 7; i++) {
                    String middle = "*";
                    mixStar = mixStar + middle;
                }
                mobile = before3 + mixStar + end4;
//                mobile = temp.replace(temp.substring(4,length-4),"****");
            } else if (temp.length() <= 7) {
                mobile = temp;
            }
        }
        return mobile;

    }
}
