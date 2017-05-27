package com.lzy.okserver.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zhaoxu2014 on 17/5/12.
 */

public class DownloadUtil {
    private static final int BUFFER_SIZE = 1024 * 8; //读写缓存大小

    private DownloadUtil() {
    }

    public static DownloadUtil getInstance() {
        return new DownloadUtil();
    }

    public byte[] encrypt(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        int len = bytes.length;
        int key = 0x12;
        for (int i = 0; i < len; i++) {
            bytes[i] ^= key;
        }
        return bytes;
    }

    public byte[] decrypt(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        int len = bytes.length;
        int key = 0x12;
        for (int i = len - 1; i > 0; i--) {
            bytes[i] = (byte) (bytes[i] ^ bytes[i - 1]);
        }
        bytes[0] = (byte) (bytes[0] ^ key);
        return bytes;
    }

    public void doCopyFileWithDecrypt(String srcfile, String targetFile) throws IOException {
        File file = new File(srcfile);
        File destFile= new File(targetFile);
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canRead())
                throw new IOException("File '" + file + "' cannot be read");
        } else {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }

        if(destFile.exists()){
            destFile.delete();
        }
        File targetFolder =destFile.getParentFile();
        if(!targetFolder.exists()) {
            targetFolder.mkdirs();
        }
        File[] files = targetFolder.listFiles();
        for (File f : files) {
            if(f.exists()){
                f.delete();
            }
        }


        FileInputStream input = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(targetFile);

        byte[] buffer = new byte[BUFFER_SIZE];
        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);

        int len;
        try {
            while ((len = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                buffer = DownloadUtil.getInstance().encrypt(buffer);
                fos.write(buffer, 0, len);
            }
        } finally {
            try {
                fos.close();
                in.close();
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) throws Exception {
        DownloadUtil downloadUtil = DownloadUtil.getInstance();
        // System.out.println("whoislcj".getBytes());
        byte[] bytes = downloadUtil.encrypt("whoislcj".getBytes());//加密
        //System.out.println(bytes);
        String str1 = new String(downloadUtil.encrypt(bytes));//解密
        System.out.println(str1);

    }
}
