package myapp.example.com.myproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.yoojia.inputs.AndroidNextInputs;
import com.github.yoojia.inputs.StaticScheme;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.module.ViewSimpleTarget;

public class MainActivity extends Activity {
    @Bind(R.id.image)
    ImageView imageview;
    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ButterKnife.bind(this);

        for(int i=0;i<10;i++) {
            Person person = new Person();
            person.setId("zhaoxu"+i);
            person.setAge(100+i);
            person.setName("ddd");
            person.save();
        }


        List<Person> personlist= Person.listAll(Person.class);

        Person pp=personlist.get(0);
//        pp.setId("12003");
//        pp.save();


       // OkHttpUtils.get("httpwww.baidu.com");

//        Person.delete(pp);

        pp.setName("0-------");
        pp.update();
        List<Person> personlist2= Person.listAll(Person.class);

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
                .load(url)
                        //                .placeholder(R.drawable.ic_launcher) //设置占位图
                        //                .error(R.drawable.ic_launcher) //设置错误图片
                .crossFade() //设置淡入淡出效果，默认300ms，可以传参
                        // .bitmapTransform(new CropCircleTransformation(this))

                .into(new ViewSimpleTarget(imageview));

    }
}
