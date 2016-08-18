package com.source.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.net.URLEncoder;

/**
 * Created by zhaoxu2014 on 15/7/29.
 */
public class IntentUtil {

    public IntentUtil() {
    }

    public static Intent getSmsIntent(String number) {
        return new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + number));
    }

    public static Intent getSmsIntent(String number, String body) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("smsto:" + number));
        intent.putExtra("sms_body", body);
        return intent;
    }

    public static Intent getCallIntent(String number) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:" + number));
        return intent;
    }

    public static Intent getWebIntent(String url) {

        Uri  uri = Uri.parse(url);
        Intent  intent = new  Intent(Intent.ACTION_VIEW, uri);


        return intent;
    }
    /**
     * 拨打电话，现实拨号界面
     *
     * @param phoneNumber
     */
    public static Intent callDial( String phoneNumber) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        return intent;

    }

    public static Intent getOtherApkStartIntent(String packagePath, String startActivity) {
        Intent mIntent = new Intent();
        ComponentName comp = new ComponentName(packagePath, startActivity);
        mIntent.setComponent(comp);
        mIntent.setAction("android.intent.action.VIEW");
        return mIntent;
    }

    public static Intent makeTakePicturesIntent(File file) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", Uri.fromFile(file));
        return intent;
    }

    public static Intent makeSystemAlbumIntent() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        return intent;
    }

    public static Intent getSelectPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        Intent wrapperIntent = Intent.createChooser(intent, (CharSequence) null);
        return wrapperIntent;
    }

    public static Intent startPhotoZoomIntent(Uri uri, Uri toUri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra("output", toUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        return intent;
    }

    private Intent startCameraZoomIntent(Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("scale", true);
        intent.putExtra("output", uri);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        return intent;
    }

    public static Intent startPhotoZoomIntent(Bitmap bitmap) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        intent.putExtra("data", bitmap);
        return intent;
    }

    public static Intent startSinaWeiboSendIntent(String content) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("sinaweibo://sendweibo?content=" + URLEncoder.encode(content)));
        return intent;
    }

    public static Intent installApk(String fileName) {
        //创建URI
        Uri uri = Uri.fromFile(new File(fileName));
        //创建Intent意图
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //设置Uri和类型
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        //执行意图进行安装
        return intent;
    }
}
