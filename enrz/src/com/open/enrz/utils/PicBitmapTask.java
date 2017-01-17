/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-17下午3:50:06
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageSwitcher;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-17下午3:50:06
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class PicBitmapTask extends AsyncTask<String, Integer, Bitmap> {  
	Context mContext;    
	ImageSwitcher imageswitcher;
    public PicBitmapTask(Context mContext,ImageSwitcher imageswitcher) {  
    	this.mContext = mContext;  
    	this.imageswitcher = imageswitcher;  
    }  
    /** 
     * 处理后台执行的任务，在后台线程执行 
     */  
    @Override  
    protected Bitmap doInBackground(String... params) {  
        Bitmap bitmap;  
        try {  
            URL picUrl = new URL(params[0]);  
            HttpURLConnection urlConn;  
            urlConn = (HttpURLConnection) picUrl.openConnection();  
            urlConn.setConnectTimeout(5000);  
            urlConn.setReadTimeout(5000);  
            InputStream is = urlConn.getInputStream();  
            bitmap = BitmapFactory.decodeStream(is);  
            is.close();  
        } catch (Exception e) {  
            return null;  
        }  
        return bitmap;  
    }  

    /** 
     * 在调用publishProgress之后被调用，在UI线程执行 
     */  
    protected void onProgressUpdate(Integer... progress) {  
        //mProgressBar.setProgress(progress[0]);// 更新进度条的进度  
    }  

    /** 
     * 后台任务执行完之后被调用，在UI线程执行 
     */  
    protected void onPostExecute(Bitmap result) {  
        if (result != null) {  
            Drawable drawable = new BitmapDrawable(result);  
            imageswitcher.setImageDrawable(drawable);  
        } else {  
            //获取图片网络失败  
        }  
    }  

    /** 
     * 在 doInBackground(Params...)之前被调用，在UI线程执行 
     */  
    protected void onPreExecute() {  
    }  

    /** 
     * 在UI线程执行 
     */  
    protected void onCancelled() {  
    }  
}  
