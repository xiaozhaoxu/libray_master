package com.source.protocol.requestbean;

import com.source.util.JsonUtil;

import java.util.TimeZone;

/**
 * author: Shawn
 * time  : 2016/10/22 11:25
 */

public abstract class BaseRequest implements IRequest {

    /**
     * 用户 Id
     */
    protected int user_id;
    /**
     * 请求的额外信息
     */
    protected String ext;

    public BaseRequest(int user_id) {
        this.ext = JsonUtil.bean2Json(new Ext());
        this.user_id = user_id;
    }

    @Override
    public void setExt(String ext) {
        this.ext = ext;
    }

    /**
     * 封装请求的额外信息
     */
    protected class Ext {
        /**
         * 设备唯一标识
         */
        private String device_uid;
        /**
         * 手机型号
         */
        private String device_type;
        /**
         * Android 系统版本
         */
        private String build_version;
        /**
         * App 当前版本
         */
        private String version;
        /**
         * 时区
         */
        private String time_zone;
        /**
         * 时间戳
         */
        private long time_stamp;

        public Ext() {
//toddo
//            this.device_uid = CommonConstant.DEVICE_UID;
//            this.device_type = CommonConstant.DEVICE_TYPE;
//            this.build_version = CommonConstant.BUILD_VERSION;
//            this.version = CommonConstant.VERSION;
            this.time_zone = TimeZone.getDefault().getID();
            this.time_stamp = Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(0, 10));
        }
    }
}
