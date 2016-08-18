package jp.module;

import android.content.Context;
import android.os.Environment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.module.GlideModule;
import com.source.util.StorageUtil;

import java.io.File;

/**
 * Created by zhaoxu2014 on 16/7/29.
 * <application android:label="@string/app_name" android:icon="@drawable/ic_launcher">
     <meta-data
      android:name="jp.module.MyGlideModule"
      android:value="GlideModule" />
 </application>


 用法:
 imageview= (ImageView) findViewById(R.id.imageview);
 String url="http://imgsrc.baidu.com/forum/w%3D580/sign=c49c299a17ce36d3a20483380af23a24/213fb80e7bec54e7a9f8cf5ebb389b504fc26a5e.jpg";

 //        imageview.setScaleType(ImageView.ScaleType.CENTER);
 Glide
 .with( this )
 .load( url)
 //                .placeholder(R.drawable.ic_launcher) //设置占位图
 //                .error(R.drawable.ic_launcher) //设置错误图片
 .crossFade() //设置淡入淡出效果，默认300ms，可以传参
 // .bitmapTransform(new CropCircleTransformation(this))
 .listener(new RequestListener<String, GlideDrawable>() {

@Override
public boolean onException(Exception e, String s, Target<GlideDrawable> target, boolean b) {
return false;
}

@Override
public boolean onResourceReady(GlideDrawable glideDrawable, String s, Target<GlideDrawable> target, boolean b, boolean b1) {

return false;
}
})

 //.into(imageview);
 .into( new SimpleTarget<GlideDrawable>() {
@Override
public void onLoadStarted(Drawable placeholder) {
imageview.setImageDrawable(placeholder);
imageview.setScaleType(ImageView.ScaleType.CENTER);
super.onLoadStarted(placeholder);
}

@Override
public void onLoadFailed(Exception e, Drawable errorDrawable) {
imageview.setImageDrawable(errorDrawable);
super.onLoadFailed(e, errorDrawable);
}

@Override
public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
imageview.setScaleType(ImageView.ScaleType.FIT_XY);
imageview.setImageDrawable(glideDrawable);
}
});
 */
public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions( Context context, GlideBuilder glideBuilder) {
        glideBuilder.setDiskCache(new DiskCache.Factory(){

            @Override
            public DiskCache build() {
                File cacheLocation = new File(StorageUtil.getImageCacheDierctory());
                cacheLocation.mkdirs();
                return DiskLruCacheWrapper.get(cacheLocation, 100 * 1024 * 1024);
            }
        });
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
