package com.source.util;

import android.location.Location;

/**
 * Created by zhaoxu2014 on 15/8/5.
 */
public class DistanceUtil {
    private static final double EARTH_RADIUS = 6378137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     *
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */
    public static String GetDistance2(double lng1, double lat1, double lng2, double lat2) {

        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;

        if(s/1000>1){
           return  df.format(s/1000) +"km";
        }else{
            return  df.format(s) +"m";
        }

    }

    public static String GetDistance(double lon1, double lat1, double lon2, double lat2) {

        float[] results=new float[1];
        Location.distanceBetween(lat1, lon1, lat2, lon2, results);

        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");

        if(results[0]/1000>1){
            return  df.format(results[0]/1000) +"km";
        }else{
            return  df.format(results[0]) +"m";
        }

    }
}
