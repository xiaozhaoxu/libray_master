package com.source.protocol;

import com.source.util.CheckUtil;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

import java.util.Map;

/**
 * Created by zhaoxu2014 on 15/8/3.
 */
public class HeaderUtils {
    public static Header[] getHeaders(Map<String,String> map){
        if(CheckUtil.isEmpty(map)){
            return null;
        }


        Header[]headers=new Header[map.size()];
        int i=0;
        for ( String key : map.keySet()) {
            final String kk=key;
            final String kValue=map.get(kk);
            Header header= new Header(){
                @Override
                public String getName() {
                    return kk;
                }

                @Override
                public String getValue() {
                    return kValue;
                }

                @Override
                public HeaderElement[] getElements() throws ParseException {
                    return new HeaderElement[0];
                }
            };

            headers[i]=header;
            i++;
        }
        return headers;
    }

}
