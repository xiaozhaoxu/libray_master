package myapp.example.com.myproject;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zhaoxu2014 on 16/10/31.
 */
public class GroupTest {
    long id ;
    String name;
    @JSONField(serialize = false)
    String addresss;
    @JSONField(name="package")
    String package_name;
}
