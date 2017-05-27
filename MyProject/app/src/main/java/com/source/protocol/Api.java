package com.source.protocol;

/**
 * author: Shawn
 * time  : 2016/10/6 18:39
 */

public class Api {



    /**
     * test
     */
    public static final int ENV_TEST = 3;

    /**
     * publish 正式上线
     */
    public static final int ENV_PUBLISH = 4;

    /**
     * 环境变量
     * todo 环境变量更改只需要改此处
     */

    public static int env = ENV_PUBLISH;


    /**
     * 服务器主机地址
     */
    public static String API_SERVER_HOST;



    public static void initHost() {
        if (env == ENV_PUBLISH) {

        } else if (env == ENV_TEST) {

        }
    }



    /**
     * 视频地址 （offline cache）
     */
    public static final String GET_MEDIA = "/index/get_media";
    public static final int ID_GET_MEDIA = 2007;

}
