package com.source.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by zhaoxu2014 on 16/6/17.
 */
public class MoneyUtil {

    //将单位为元的字符串转化位单位为分的整数
    public static int covertStringYuanToIntFen(String string) {
        if (string == null)
            return 0;

        if (string.contains(".")) {
            String[] arrays = string.split(".");
            int yuan = 0;
            int fen = 0;
            if (arrays.length > 0) {
                String value = arrays[0];
                yuan = NumberUtil.parseInt(value, 0);
            }
            if (arrays.length > 1) {
                String value = arrays[1];
                if (value.length() > 2)
                    value = value.substring(0, 2);

                fen = NumberUtil.parseInt(value, 0);
            }

            return yuan * 100 + fen;
        } else {
            return NumberUtil.parseInt(string, 0) * 100;
        }


    }


    //将单位为分的整数转化位单位为元的字符串
    public static String covertIntFenToStringYuan(int value) {
        if (value % 100 != 0) {
            return String.format("%d.%02d", (int) value / 100, (int) value % 100);
        } else {
            return String.format("%d", (int) value / 100);
        }

    }

    //将单位为分的整数转化位单位为元的字符串,并保留两位小数
    public static String covertIntFenToStringYuanWith2w(int value) {
        if (value % 100 != 0) {
            return String.format("%d.%02d", (int) value / 100, (int) value % 100);
        } else {
            return String.format("%d.00", (int) value / 100);
        }

    }

    //将单位为元的整数转化成保留两位数
    public static String covertYuanToString(double value) {
//        Double priceTemp = new BigDecimal(value).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
//        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###");

        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(value);

        //  return covertIntFenToStringYuan(covertDoubleYuanToIntFen(value));
    }

    //将单位为元的整数转化成保留两位数
    public static String covertYuan(double value) {
        Double priceTemp = new BigDecimal(value).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
        java.text.DecimalFormat df = new java.text.DecimalFormat("####");
        return df.format(priceTemp);
    }

    //将单位为元的小数转换为单位为分的整数
    public static int covertDoubleYuanToIntFen(double value) {
        double n_value = value * 100.0;
        int result = (int) n_value;
        if ((n_value - (double) result) > 0.5) {
            result++;
        }
        return result;
    }

    //将单位为分的整数转换为单位为元的小数
    public static double covertIntFenToDoubleYuan(int value) {
        return (value * 1.0) / 100.0;
    }

}
