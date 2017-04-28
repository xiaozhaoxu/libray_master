package myapp.example.com.myproject;

import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.model.HttpParams;
import com.source.protocol.LibBaseJsonProtocol;

/**
 * Created by zhaoxu2014 on 17/4/7.
 */

public class NetTest extends LibBaseJsonProtocol {
    @Override
    protected String getUrl() {
        return "https://api.getui.com/apiex.htm";
    }

    @Override
    protected HttpParams getParams() throws Exception {
        HttpParams rp=new HttpParams();
        rp.put("provId", "370000");//其他地用
        return rp;
    }

    @Override
    protected JSONObject getJsonParams() throws Exception {
        return null;
    }

    @Override
    protected boolean isGetMode() {
        return false;
    }

    @Override
    protected int getTag() {
        return 0;
    }

    @Override
    protected boolean handlerBufferData(String result) {
        return false;
    }

    @Override
    protected Object handleJSON(JSONObject jsonObject) throws Exception {
        return null;
    }
}
