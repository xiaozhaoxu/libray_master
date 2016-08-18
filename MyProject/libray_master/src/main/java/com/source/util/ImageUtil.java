package com.source.util;

import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.util.Log;

import java.io.*;

/**
 * Created by zhaoxu2014 on 15/7/29.
 */
public class ImageUtil {

    private static String TAG = ImageUtil.class.getSimpleName();

    public ImageUtil() {
    }

    public static Bitmap createVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();

        try {
            bitmap = ThumbnailUtils.createVideoThumbnail(filePath, 2);
        } catch (IllegalArgumentException var14) {
            ;
        } catch (RuntimeException var15) {
            ;
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException var13) {
                ;
            }

        }

        return bitmap;
    }

    public static Bitmap scaleAndCutThumbnail(String filename, int resizeWidth, int resizeHeight) {
        Bitmap bitmap = null;
        Bitmap resBitmap = null;

        try {
            resBitmap = extractPicture(filename);
            if(resBitmap != null) {
                bitmap = cutImage(resBitmap, resizeWidth, resizeHeight);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            if(resBitmap != null && !resBitmap.equals(bitmap) && !resBitmap.isRecycled()) {
                resBitmap.recycle();
            }

        }

        return bitmap;
    }

    public static Bitmap scaleThumbnail(String filename, int resizeWidth, int resizeHeight) throws Exception {
        Bitmap bmp = null;
        InputStream input = null;

        try {
            input = StorageUtil.openInputStream(filename);
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(input, (Rect) null, opts);
            if(opts.outHeight > resizeHeight || opts.outWidth > resizeWidth) {
                float scaleHeight = (float)opts.outHeight / (float)resizeHeight;
                float scaleWidth = (float)opts.outWidth / (float)resizeWidth;
                int initialSize = (int)scaleHeight;
                if(scaleWidth > scaleHeight) {
                    initialSize = (int)scaleWidth;
                }

                boolean roundedSize = true;
                int roundedSize1;
                if(initialSize <= 8) {
                    for(roundedSize1 = 1; roundedSize1 < initialSize; roundedSize1 <<= 1) {
                        ;
                    }
                } else {
                    roundedSize1 = (initialSize + 7) / 8 * 8;
                }

                IOUtils.closeQuietly(input);
                input = StorageUtil.openInputStream(filename);
                opts.inSampleSize = roundedSize1;
                opts.inJustDecodeBounds = false;
                bmp = BitmapFactory.decodeStream(input, (Rect) null, opts);
            }
        } finally {
            IOUtils.closeQuietly(input);
        }

        return bmp;
    }

    public static Bitmap cutImage(Bitmap bitmap, int resizeWidth, int resizeHeight) {
        if(bitmap == null) {
            return null;
        } else {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if(width == resizeWidth && height == resizeHeight) {
                return bitmap;
            } else {
                float scaleHeight = (float)height / (float)resizeHeight;
                float scaleWidth = (float)width / (float)resizeWidth;
                float scale = scaleHeight > scaleWidth?scaleWidth:scaleHeight;
                int newWidth = (int)((float)resizeWidth * scale);
                int newHeight = (int)((float)resizeHeight * scale);
                int x = (width - newWidth) / 2;
                int y = (height - newHeight) / 2;
                return Bitmap.createBitmap(bitmap, x, y, newWidth, newHeight);
            }
        }
    }

    public static Bitmap extractThumbnail(String filename, int sideLength) throws IOException {
        Bitmap bmp = null;
        InputStream input = null;

        try {
            input = StorageUtil.openInputStream(filename);
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(input, (Rect) null, opts);
            int max = Math.max(opts.outWidth, opts.outHeight);
            int initialSize = max / sideLength;
            boolean roundedSize = true;
            int roundedSize1;
            if(initialSize <= 8) {
                for(roundedSize1 = 1; roundedSize1 < initialSize; roundedSize1 <<= 1) {
                    ;
                }
            } else {
                roundedSize1 = (initialSize + 7) / 8 * 8;
            }

            IOUtils.closeQuietly(input);
            input = StorageUtil.openInputStream(filename);
            opts.inSampleSize = roundedSize1;
            opts.inJustDecodeBounds = false;
            bmp = BitmapFactory.decodeStream(input, (Rect) null, opts);
        } finally {
            IOUtils.closeQuietly(input);
        }

        return bmp;
    }

    public static Bitmap resizeImage(Bitmap bitmap, int newWidth, int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = (float)newWidth / (float)width;
        float scaleHeight = (float)newHeight / (float)height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return resizedBitmap;
    }

    public static void saveBitmap(Bitmap bitmap, String path) {
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
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
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

    public static Bitmap extractPicture(String filename) {
        if(filename == null) {
            return null;
        } else {
            Bitmap bmp = null;
            InputStream input = null;

            try {
                input = StorageUtil.openInputStream(filename);
                BitmapFactory.Options ex = new BitmapFactory.Options();
                ex.inJustDecodeBounds = false;
                bmp = BitmapFactory.decodeStream(input, (Rect) null, ex);
            } catch (Exception var7) {
                Log.e(TAG, var7.toString());
            } finally {
                IOUtils.closeQuietly(input);
            }

            return bmp;
        }
    }

    public static Bitmap convertBitmap(Bitmap _bitmap, float maxSize) {
        int width_tmp = _bitmap.getWidth();
        int height_tmp = _bitmap.getHeight();
        float scale = 1.0F;
        if((float)width_tmp > maxSize || (float)height_tmp > maxSize) {
            if(width_tmp >= height_tmp) {
                scale = (float)width_tmp / maxSize;
            } else {
                scale = (float)height_tmp / maxSize;
            }
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap resizeBmp = Bitmap.createBitmap(_bitmap, 0, 0, _bitmap.getWidth(), _bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }

    public static Bitmap convertBitmap(byte[] data, float maxSize) {
        Bitmap bitmap = null;
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, o);
        int width_tmp = o.outWidth;
        int height_tmp = o.outHeight;
        int scale = 1;
        if((float)width_tmp > maxSize || (float)height_tmp > maxSize) {
            if(width_tmp >= height_tmp) {
                scale = NumberUtil.convertFloatToInt((float)width_tmp / maxSize);
            } else {
                scale = NumberUtil.convertFloatToInt((float)height_tmp / maxSize);
            }
        }

        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inSampleSize = scale;
        bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, op);
        return bitmap;
    }

    public static Bitmap convertBitmap(String file, float maxSize) {
        Bitmap bitmap = null;

        try {
            BitmapFactory.Options e = new BitmapFactory.Options();
            e.inJustDecodeBounds = true;
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            BitmapFactory.decodeStream(fis, (Rect) null, e);
            fis.close();
            int width_tmp = e.outWidth;
            int height_tmp = e.outHeight;
            int scale = 1;
            if((float)width_tmp > maxSize || (float)height_tmp > maxSize) {
                if(width_tmp >= height_tmp) {
                    scale = NumberUtil.convertFloatToInt((float)width_tmp / maxSize);
                } else {
                    scale = NumberUtil.convertFloatToInt((float)height_tmp / maxSize);
                }
            }

            BitmapFactory.Options op = new BitmapFactory.Options();
            op.inSampleSize = scale;
            fis = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(fis, (Rect) null, op);
            fis.close();
        } catch (FileNotFoundException var9) {
            ;
        } catch (IOException var10) {
            ;
        }

        return bitmap;
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        if(bitmap == null) {
            return null;
        } else {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float roundPx;
            float left;
            float top;
            float right;
            float bottom;
            float dst_left;
            float dst_top;
            float dst_right;
            float dst_bottom;
            if(width <= height) {
                roundPx = (float)(width / 2);
                top = 0.0F;
                bottom = (float)width;
                left = 0.0F;
                right = (float)width;
                height = width;
                dst_left = 0.0F;
                dst_top = 0.0F;
                dst_right = (float)width;
                dst_bottom = (float)width;
            } else {
                roundPx = (float)(height / 2);
                float output = (float)((width - height) / 2);
                left = output;
                right = (float)width - output;
                top = 0.0F;
                bottom = (float)height;
                width = height;
                dst_left = 0.0F;
                dst_top = 0.0F;
                dst_right = (float)height;
                dst_bottom = (float)height;
            }

            Bitmap output1 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output1);
            int color = -12434878;
            Paint paint = new Paint();
            Rect src = new Rect((int)left, (int)top, (int)right, (int)bottom);
            Rect dst = new Rect((int)dst_left, (int)dst_top, (int)dst_right, (int)dst_bottom);
            RectF rectF = new RectF(dst);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-12434878);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, src, dst, paint);
            return output1;
        }
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        if(bitmap == null) {
            return null;
        } else {
            try {
                Bitmap e = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(e);
                Paint paint = new Paint();
                Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                RectF rectF = new RectF(new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()));
                paint.setAntiAlias(true);
                canvas.drawARGB(0, 0, 0, 0);
                paint.setColor(-16777216);
                canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                canvas.drawBitmap(bitmap, src, rect, paint);
                return e;
            } catch (Exception var8) {
                return bitmap;
            }
        }
    }

    public static Bitmap getBitMap(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
