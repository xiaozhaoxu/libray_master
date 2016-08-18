package com.source.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class TheImgUtil {

    /**
     * �Կ�Ϊ��׼����̶������������ߴ�����
     * ��ǰ��ͼƬ�Ŀ���ȶ���һ���򷵻�ԭʼ�ĵ�ַ���������?���ؽ���ǿգ���˵����������Ĵ���ʧ��
     *
     * @param fromFile ͼƬ�ı���·��
     * @param w        ͼƬ�����տ��
     * @param toFile   ͼƬ���ź��ŵ�·��
     */
    public static String getTheWidthFile(String fromFile, int w, String toFile) {
        Bitmap bitmap = null;
        float scale;

        if (!CheckUtil.isEmpty(fromFile)) {
            bitmap = ImageUtil.extractPicture(fromFile);
            if (!CheckUtil.isEmpty(bitmap)) {
                int width = bitmap.getWidth();
                scale = (float)w / width;
                LogUtil.d("bitmap", "width==" + bitmap.getWidth() + "==height==" + bitmap.getHeight() + "==scale==" + scale + "==w==" + w);
                if (scale == 1) {
                    return fromFile;
                } else {
                    Matrix matrix = new Matrix();
                    matrix.postScale(scale, scale); //���Ϳ�Ŵ���С�ı���

                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                }
            }
        }

        if (!CheckUtil.isEmpty(bitmap)) {
            ImageUtil.saveBitmap(bitmap, toFile);
            return toFile;
        }

        return null;
    }

    /**
     * ������Ϊ׼���������ȶ��Ĵ�С
     * ��ǰ��ͼƬ�Ŀ���ȶ���һ���򷵻�ԭʼ�ĵ�ַ���������?���ؽ���ǿգ���˵����������Ĵ���ʧ��
     *
     * @param fromFile ͼƬ�ı���·��
     * @param maxSize  ͼƬ�����ߴ�С
     * @param toFile   ͼƬ���ź��ŵ�·��
     */

    public static String getTheScaleFile(String fromFile, int maxSize, String toFile) {
        Bitmap bitmap = null;
        int max;
        float maxsize = maxSize;

        if (!CheckUtil.isEmpty(fromFile)) {
            bitmap = ImageUtil.extractPicture(fromFile);
        }

        if (!CheckUtil.isEmpty(bitmap)) {
            float scale = 1;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            max = Math.max(width, height);
            scale = maxsize / max;
//            Lg.d("bitmap","width=="+bitmap.getWidth()+"==height=="+bitmap.getHeight() +"==scale=="+scale+"==maxSize=="+maxSize);
            if (scale >= 1) {
                return fromFile;
            } else {
                Matrix matrix = new Matrix();
                //���Ϳ�Ŵ���С�ı���,scale����1��ò���bitmap
                matrix.postScale(scale, scale);
//                Lg.d("bitmap","width=="+bitmap.getWidth()+"==height=="+bitmap.getHeight() +"==scale=="+scale+"==maxSize=="+maxSize);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            }
        }

        if (!CheckUtil.isEmpty(bitmap)) {
            saveBitmap(bitmap, toFile, 100);
            return toFile;
        }

        return null;
    }

    public static void saveBitmap(Bitmap bitmap, String path,int quality) {
        FileOutputStream outputStream = null;

        try {
            File e1 = new File(path);
            if(e1.exists()) {
                e1.delete();
            }

            if(!e1.getParentFile().exists()) {
                e1.getParentFile().mkdirs();
            }

            if(e1.createNewFile()) {
                outputStream = new FileOutputStream(e1);
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
                outputStream.flush();
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            try {
                if(outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException var11) {
                var11.printStackTrace();
            }

        }

    }

    public  static  int  computeSampleSize(BitmapFactory.Options options,
                                           int  minSideLength,  int  maxNumOfPixels) {
        int  initialSize = computeInitialSampleSize(options, minSideLength,maxNumOfPixels);
        int  roundedSize;
        if  (initialSize <=  8 ) {
            roundedSize =  1 ;
            while  (roundedSize < initialSize) {
                roundedSize <<=  1 ;
            }
        }  else  {
            roundedSize = (initialSize +  7 ) /  8  *  8 ;
        }

        return  roundedSize;
    }

    private  static  int  computeInitialSampleSize(BitmapFactory.Options options,
                                                   int  minSideLength,  int  maxNumOfPixels) {
        double  w = options.outWidth;
        double  h = options.outHeight;

        int  lowerBound = (maxNumOfPixels == - 1 ) ?  1  :
                ( int ) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int  upperBound = (minSideLength == - 1 ) ?  128  :
                ( int ) Math.min(Math.floor(w / minSideLength),
                        Math.floor(h / minSideLength));

        if  (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return  lowerBound;
        }

        if  ((maxNumOfPixels == - 1 ) &&
                (minSideLength == - 1 )) {
            return  1 ;
        }  else  if  (minSideLength == - 1 ) {
            return  lowerBound;
        }  else  {
            return  upperBound;
        }
    }

        public static Bitmap saveThePath(String imageFile){
        Bitmap bmp = null;
        BitmapFactory.Options opts =  new  BitmapFactory.Options();
        opts.inJustDecodeBounds =  true ;
        BitmapFactory.decodeFile(imageFile, opts);

        opts.inSampleSize = computeSampleSize(opts, - 1 ,  128 * 128 );
        opts.inJustDecodeBounds =  false ;
        try  {
            bmp = BitmapFactory.decodeFile(imageFile, opts);
//            imageView.setImageBitmap(bmp);
        }  catch  (OutOfMemoryError err) {

        }
        return bmp;
    }



}
