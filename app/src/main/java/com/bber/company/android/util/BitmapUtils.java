package com.bber.company.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.amap.api.maps2d.MapView;
import com.bber.company.android.R;
import com.bber.company.android.app.AppManager;
import com.bber.company.android.view.activity.ChatImageActivity;
import com.bber.company.android.widget.MyToast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description：图片压缩
 * <p>
 * Created by Mjj on 2016/12/3.
 */

public class BitmapUtils {

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * 根据Resources压缩图片
     *
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeResource(res, resId, options);
        return src;
    }

    /**
     * 根据地址压缩图片
     *
     * @param pathName
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromFd(String pathName, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeFile(pathName, options);
        return src;
    }


    /***
     * 根据bitmap来保存当前的图片
     * @param bitmap
     */
    public static void savePicture(Bitmap bitmap)
    {
        String SavePath = getSDCardPath()+"/DCIM/papa/";


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
//        String str = formatter.format(curDate);
        long longdata = curDate.getTime();
        //文件
        File file = new File(SavePath);
        if(!file.exists()){
            file.mkdirs();
        }

        String pictureName = SavePath + longdata+".jpg";
        file = new File(pictureName);

        FileOutputStream out;
        try
        {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            //讲道理无论成功失败，都应该通知相册来刷新相册
            //保存本地截图，并且发送广播到系统相册强制刷新
            AppManager.getAppManager().currentActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
            out.flush();
            out.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /***
     * 根据获取到的url得到bitmap
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url)
    {
        Bitmap bitmap = null;
        try
        {
            URL pictureUrl = new URL(url);
            InputStream in = pictureUrl.openStream();
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 获取SDCard的目录路径功能
     * @return
     */
    private static String getSDCardPath(){
        File sdcardDir = null;
        //判断SDCard是否存在
        boolean sdcardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if(sdcardExist){
            sdcardDir = Environment.getExternalStorageDirectory();
        }
        return sdcardDir.toString();
    }
}
