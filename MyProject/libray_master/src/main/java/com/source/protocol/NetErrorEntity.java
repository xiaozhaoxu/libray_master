package com.source.protocol;

import com.alibaba.fastjson.JSONObject;
import com.source.util.CheckUtil;
import com.source.util.JsonUtil;

/**
 * {"msg":"你已提交申请","code":400,"data":"","msg_code":"REPEATE","errmsg":""}
 */


public class NetErrorEntity  {

    String msg;//":"你已提交申请"
    String code;//":400,
    String data;
    String msg_code;//":"REPEATE"" +
    String errmsg;

    public static NetErrorEntity fromJSON(JSONObject js) {
        if (null == js) {
            return null;
        }
        NetErrorEntity bean = JsonUtil.json2Bean(js.toString(), NetErrorEntity.class);
        return bean;
    }


    public static String getMsg(NetErrorEntity entity) {

        String msg = "";
        if (CheckUtil.isEmpty(entity)) {
             msg = "";
            return msg;
        }

        if (!CheckUtil.isEmpty(entity.getMsg())) {
            msg = entity.getMsg();
            return msg;
        }

        return msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg_code() {
        return msg_code;
    }

    public void setMsg_code(String msg_code) {
        this.msg_code = msg_code;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
