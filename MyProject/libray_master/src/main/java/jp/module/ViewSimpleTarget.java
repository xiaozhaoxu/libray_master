package jp.module;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.source.util.CheckUtil;

/**
 * Created by zhaoxu2014 on 16/7/29.
 */
public class ViewSimpleTarget extends SimpleTarget<GlideDrawable> {
    View imageview;

    public ViewSimpleTarget(View imageview) {
        this.imageview = imageview;
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {

        if(CheckUtil.isEmpty(imageview)){
            return;
        }
        if (imageview instanceof ImageView) {
            ((ImageView) imageview).setImageDrawable(placeholder);
            ((ImageView) imageview).setScaleType(ImageView.ScaleType.CENTER);
        } else {
            imageview.setBackground(placeholder);
        }
        super.onLoadStarted(placeholder);
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        if(CheckUtil.isEmpty(imageview)){
            return;
        }
        if (imageview instanceof ImageView) {
            ((ImageView) imageview).setImageDrawable(errorDrawable);
        } else {
            imageview.setBackground(errorDrawable);
        }
        super.onLoadFailed(e, errorDrawable);
    }

    @Override
    public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
        if(CheckUtil.isEmpty(imageview)){
            return;
        }
        if (imageview instanceof ImageView) {
            ((ImageView) imageview).setScaleType(ImageView.ScaleType.FIT_XY);
            ((ImageView) imageview).setImageDrawable(glideDrawable);
        } else {
            imageview.setBackground(glideDrawable);
        }
    }
}
