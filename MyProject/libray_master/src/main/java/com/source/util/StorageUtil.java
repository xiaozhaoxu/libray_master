package com.source.util;

import android.content.res.AssetManager;
import android.os.Environment;
import com.source.application.config.LibaryConfigBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * @author Nick create at 2011-3-18
 */
public class StorageUtil {

    public static final String ROOT_ASSET = "/android_asset/";

    public static boolean isSdcardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    //应用临时使用的缓存目录（每次使用后应删除）
    public static String getCacheDir() {
        return LibaryConfigBuilder.context.getCacheDir() + "/" + LibaryConfigBuilder.app_identity +"/";
    }

    //获取应用根目录
    public static String getStorageDirectory() {
        return Environment.getExternalStorageDirectory()+ "/" + LibaryConfigBuilder.app_identity+"/";
    }


    public  static String getAPPCacheDierctory(){
        if(isSdcardAvailable()){
            return getStorageDirectory();
        }else{
            return getCacheDir();

        }
    }


    public static String getImageCacheDierctory(){
        return  getAPPCacheDierctory()+"images/";
    }

    public static String getDataCacheDierctory(){

        return  getAPPCacheDierctory()+"data/";
    }

    public static String getImagePath(String destName, boolean isDirectory) {
//        String external = Environment.getExternalStorageDirectory().getPath();
//        external += "/" + Deeper.app_identity + "/Content/Images/" + destName;
//        if (isDirectory) {
//            external += "/";
//        }
        //为适应华为机顶盒内置SD卡无法访问的情况，将图片存储到应用Cache中
        String external = LibaryConfigBuilder.context.getCacheDir().getPath();
        external += "/" + "cover_image_cache" + "/" + destName;
        if (isDirectory) {
            external += "/";
        }

        return external;
    }

    public static void cleanAll() {
        try {
            if (new File(LibaryConfigBuilder.context.getCacheDir() + "/").exists())
                FileUtils.cleanDirectory(new File(LibaryConfigBuilder.context.getCacheDir() + "/"));
            String external = Environment.getExternalStorageDirectory().getPath();
            if (new File(external + "/." + LibaryConfigBuilder.app_identity + "/Content/").exists())
                FileUtils.cleanDirectory(new File(external + "/." + LibaryConfigBuilder.app_identity + "/Content/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void cleanDataCache(){
//        try {
//            File file = new File(getStorageDirectory() + Deeper.EPG_CACHE_PATH);
//            if(file.exists()){
//                FileUtils.cleanDirectory(file);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void cleanImageCache(){
//        try {
//            File file = new File(getStorageDirectory() + Deeper.IMAGE_CACHE_PATH);
//            if(file.exists()){
//                FileUtils.cleanDirectory(file);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public static String[] listFiles(String path, String suffix) {
        if (path.startsWith(ROOT_ASSET)) {
            if (path.endsWith("/")) {
                path = path.substring(0, path.length() - 1);
            }

            AssetManager assetManager = LibaryConfigBuilder.context.getAssets();

            String[] files = new String[0];

            try {
                files = assetManager.list(path.substring(ROOT_ASSET.length()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayList<String> result = new ArrayList<String>();

            for (int i = 0; i < files.length; i++) {
                if (files[i].endsWith(suffix)) {
                    result.add(path + "/" + files[i]);
                }
            }

            return result.toArray(new String[result.size()]);
        } else {

            ArrayList<String> result = new ArrayList<String>();
            Collection<File> files = FileUtils.listFiles(new File(path), suffix);
            for (File file : files) {
                if (!file.getName().startsWith(".")) {
                    result.add(file.getAbsolutePath());
                }
            }
            //对图片地址按照文件名称(数字)进行排序（升序）
            String[] picPathArray = result.toArray(new String[result.size()]);
            Arrays.sort(picPathArray, new Comparator<Object>() {
                public int compare(Object o1, Object o2) {
                    String path1 = (String) o1;
                    String path2 = (String) o2;
                    long pic1NameNum = getPicNameNm(path1);
                    long pic2NameNum = getPicNameNm(path1);
                    if (pic1NameNum > pic2NameNum) {
                        return -1;
                    } else if (pic1NameNum < pic2NameNum) {
                        return 1;
                    } else {
                        return path1.compareTo(path2);
                    }
                }

                private Long getPicNameNm(String path1) {
                    try {
                        int nameStartIndex = path1.lastIndexOf("/");
                        int nameEndIndex = path1.lastIndexOf(".");
                        String fileName = path1.substring(nameStartIndex + 1, nameEndIndex);
                        return Long.parseLong(fileName);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    return -1l;
                }
            });

            return picPathArray;
        }
    }

    public static InputStream openInputStream(String file) throws IOException {
        if (file.startsWith(ROOT_ASSET)) {
            AssetManager assetManager = LibaryConfigBuilder.context.getAssets();
            return assetManager.open(file.substring(ROOT_ASSET.length()));
        } else {
            return FileUtils.openInputStream(new File(file));
        }
    }

    public static boolean deleteFile(String file) {
        if (!file.startsWith(ROOT_ASSET)) {
            return FileUtils.deleteQuietly(new File(file));
        }

        return true;
    }

    public static boolean deleteDirectory(String file) throws IOException {
        if (!file.startsWith(ROOT_ASSET)) {
            FileUtils.forceDelete(new File(file));
        }

        return true;
    }

    public static void cleanCache() throws IOException {
        FileUtils.cleanDirectory(LibaryConfigBuilder.context.getCacheDir());
    }

}
