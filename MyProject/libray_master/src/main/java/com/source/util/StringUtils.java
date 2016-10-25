package com.source.util;

import android.os.Bundle;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;

import com.source.common.LibaryConfigBuilder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: SlothMonkey
 * Date: 12-8-1
 * Time: 下午2:51
 * To change this template use File | Settings | File Templates.
 */
public class StringUtils {

    /**
     * 解决自动换行排版问题
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        if(CheckUtil.isEmpty(input)){
            return "";
        }

        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    public static String getDownloadFileName(String audio_url) {
        int lastPosition = audio_url.lastIndexOf(47);
        String audio_name = audio_url.substring(lastPosition, audio_url.length());
        return audio_name;
    }

    public static boolean isValid(String str) {
        return str != null && str.trim().length() > 0;
    }

    /**
     * 判断字符窜是否不为null和空串
     *
     * @param str String
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /*
       * 通过 Key从Bundle中获取相对应的整型值
       */
    public static int getIntResFormBundle(Bundle bundle, String parm) {
        int res = 0;
        try {
            res = new Integer(bundle.get(parm).toString());
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        return res;
    }




    /*
      * 根据ID获取相应String内容
      */
    public static String getStringByKey(int keyid,
                                        Object... object) {
        String res = "";
        try {
            res = LibaryConfigBuilder.context.getResources().getString(keyid, object);
        } catch (Exception e) {
            e.printStackTrace();
            res = "";
        }
        return res;
    }

    /*
     * 根据ID获取相应String内容然后按Html显示 ,
     */
    public static String getHtmlStringByKey( int keyid,
                                            Object... object) {
        String res = "";
        try {
            res = getStringByKey( keyid, object);
            res = Html.fromHtml(res).toString();
        } catch (Exception e) {
            e.printStackTrace();
            res = "";
        }
        return res;
    }

    public static String getEditTextText(EditText et) {
        String res = "";
        if (et != null) {
            res = et.getText().toString().trim();
        }
        return res;
    }

    public static String getTextViewText(TextView et) {
        String res = "";
        if (et != null) {
            res = et.getText().toString().trim();
        }
        return res;
    }

    public static String getStringFromInputStream(InputStream inputstream) {
        String resultData = "";
        try {

            InputStreamReader in = new InputStreamReader(inputstream, "UTF-8");
            BufferedReader buffer = new BufferedReader(in);
            String inputLine = null;
            // 使用循环来读取获得的数据
            while (((inputLine = buffer.readLine()) != null)) {
                // 我们在每一行后面加上一个"\n"来换行
                resultData += inputLine + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }

    public static InputStream getStringStream(String sInputString) {

        if (sInputString != null && !sInputString.trim().equals("")) {
            try {
                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(
                        sInputString.getBytes());
                return tInputStringStream;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 分割字符串，原理：检测字符串中的分割字符串，然后取子串
     *
     * @param original
     *            需要分割的字符串
     * @param regex
     *            分割字符串
     * @return 分割后生成的字符串数组
     */
    public static String[] StringSplit(String original, String regex) {
        if (original == null || regex == null) {
            return null;
        }

        // 取子串的起始位置
        int startIndex = 0;

        // 将结果数据先放入Vector中
        Vector<String> v = new Vector<String>();

        // 返回的结果字符串数组
        String[] str = new String[1];

        // 存储取子串时起始位置
        int index = 0;

        // 获得匹配子串的位置
        startIndex = original.indexOf(regex);

        // ece.tool.Tools.log("startIndex : " + startIndex);
        if (startIndex == -1) {
            str[0] = original;
            return str;
        }

        // 如果起始字符串的位置小于字符串的长度，则证明没有取到字符串末尾。-1代表取到了末尾
        while (startIndex < original.length() && startIndex != -1) {
            // 取子串
            v.addElement(original.substring(index, startIndex));
            // 设置取子串的起始位置
            index = startIndex + regex.length();
            // 获得匹配子串的位置
            startIndex = original.indexOf(regex, startIndex + regex.length());
        }

        // 取结束的子串
        v.addElement(original.substring(index));

        // 将Vector对象转换成数组
        str = new String[v.size()];
        for (int i = 0; i < v.size(); i++) {
            str[i] = (String) v.elementAt(i);
        }

        // 返回生成的数组
        return str;
    }


    /**
     * 计算时间1-59分钟前，
     *
     * @param date
     * @return
     */
    private static String computingTime(Long date) {
        if (date < 10000)
            return "";
        long currentTime = System.currentTimeMillis();
        float i = ((currentTime - date) / 3600 / 1000);
        if (i < 1) {
            int time = (int) Math.ceil(i * 60);
            return time + 1 + "分钟前";
        } else if (i < 24) {
            return (int) i + "小时前";
        } else if (i < 48)
            return "昨天";
        return DateUtil.getYMDByMS(date);
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @return
     */
    public static int toInt(String str){
     return toInt(str,0);
    }
    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 字符串转float
     *
     * @param str
     * @return
     */
    public static float toFloat(String str){
        return toFloat(str,0);
    }
    /**
     * 字符串转float
     *
     * @param str
     * @param defValue
     * @return
     */
    public static float toFloat(String str, float defValue) {
        try {
            return  Float.parseFloat(str);
        } catch (Exception e) {
        }
        return defValue;
    }


    /**
     * 字符串转float
     *
     * @param str
     * @return
     */
    public static double toDouble(String str){
        return toDouble(str,0);
    }
    /**
     * 字符串转float
     *
     * @param str
     * @param defValue
     * @return
     */
    public static double toDouble(String str, double defValue) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
        }
        return defValue;
    }

}
