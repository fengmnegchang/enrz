/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13下午2:33:10
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.html;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.text.Html;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13下午2:33:10
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class URLImageParser implements Html.ImageGetter {
    TextView mTextView;

    public URLImageParser(TextView textView) {
        this.mTextView = textView;
    }

    @Override
    public Drawable getDrawable(String source) {
        final URLDrawable urlDrawable = new URLDrawable();
        Log.d("URLImageParser", source);
        ImageLoader.getInstance().loadImage(source, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                urlDrawable.bitmap = loadedImage;
                urlDrawable.setBounds(0, 0, loadedImage.getWidth(), loadedImage.getHeight());
                mTextView.invalidate();
                mTextView.setText(mTextView.getText()); // 解决图文重叠
            }
        });
        return urlDrawable;
    }
}