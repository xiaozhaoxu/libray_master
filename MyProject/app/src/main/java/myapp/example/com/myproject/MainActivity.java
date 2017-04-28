package myapp.example.com.myproject;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.github.yoojia.inputs.AndroidNextInputs;
import com.github.yoojia.inputs.StaticScheme;
import com.jiongbull.jlog.JLog;
import com.source.activity.BaseLibActivity;
import com.source.util.JsonUtil;
import com.source.widget.image.config.ViewSimpleTarget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import me.imid.swipebacklayout.lib.SwipeBackLayout;

public class MainActivity extends BaseLibActivity {
    private static final int VIBRATE_DURATION = 20;
    @Bind(R.id.image)
    ImageView imageview;
    @Bind(R.id.tv)
    TextView tv;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.content_main);
        ButterKnife.bind(this);
        PERMISSIONS = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
//改的baselibfragment
//        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList,mTitleList);
//        mViewpager.setAdapter(pagerAdapter);




        JSONObject js=new JSONObject();
        js.put("id",1000);
        js.put("name","zhaoxu");
        js.put("addresss","北京");
        js.put("package","abc");

        GroupTest gt= JsonUtil.json2Bean(js.toString(),GroupTest.class);
        JLog.d(js.toJSONString());
        JLog.json(js.toJSONString());

//        mSwipeBackLayout.addSwipeListener(new SwipeBackLayout.SwipeListener() {
//            @Override
//            public void onScrollStateChange(int state, float scrollPercent) {
//
//            }
//
//            @Override
//            public void onEdgeTouch(int edgeFlag) {
//                vibrate(VIBRATE_DURATION);
//            }
//
//            @Override
//            public void onScrollOverThreshold() {
//                vibrate(VIBRATE_DURATION);
//            }
//        });

        List<Person> list=new ArrayList();
        for(int i=0;i<10;i++) {
            Person person = new Person();
            person.setId("zhaoxu"+i);
            person.setAge(100+i);
            person.setName("ddd");

            List<Student> slist=new ArrayList();
            for(int j=0;j<10;j++){
                Student s = new Student();
                s.setId("s"+i);
                s.setAge(100 + i);
                s.setName("sname" + i);
                slist.add(s);
            }

            person.setList(slist);

            list.add(person);
        }

        String ja=JsonUtil.list2Json(list);

        List<Person> plist= JsonUtil.json2List(ja, Person.class);


        final AndroidNextInputs inputs = new AndroidNextInputs(this);
        inputs.add(tv, StaticScheme.ChineseMobile());
        if(!inputs.test()){
            //
        }


        // imageview= (ImageView) findViewById(R.id.image);
        String url = "http://imgsrc.baidu.com/forum/w%3D580/sign=c49c299a17ce36d3a20483380af23a24/213fb80e7bec54e7a9f8cf5ebb389b504fc26a5e.jpg";

        imageview.setScaleType(ImageView.ScaleType.CENTER);
        Glide
                .with(this)
//                .load(R.drawable.ic_launcher)
                .load(url)
                //                .placeholder(R.drawable.ic_launcher) //设置占位图
                //                .error(R.drawable.ic_launcher) //设置错误图片
                .crossFade() //设置淡入淡出效果，默认300ms，可以传参
                .bitmapTransform(new CropCircleTransformation(this))
                .into(new ViewSimpleTarget(imageview));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTintColor(Color.YELLOW);
    }



    @OnClick({R.id.bt_unvalid,R.id.bt_valid,R.id.image})
    public void viewClick(View view){
        switch (view.getId()){
            case R.id.image:{
                setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL);
                Intent intent=new Intent(context,MainActivity.class);
                startActivity(intent);
                break;
            }
//            case  R.id.bt_unvalid:{
//                setSwipeBackEnable(false);
//             break;
//            }
//            case R.id.bt_valid:{
//                setSwipeBackEnable(true);
//                break;
//            }

        }
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected boolean permissionsFailed() {
        return false;
    }
}
