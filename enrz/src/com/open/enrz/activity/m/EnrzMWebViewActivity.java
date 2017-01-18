/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午10:03:23
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.activity.m;

import android.content.Context;
import android.content.Intent;

import com.open.enrz.activity.EnrzWebViewActivity;
import com.open.enrz.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午10:03:23
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class EnrzMWebViewActivity extends EnrzWebViewActivity{
	
	
	public void loadUrl(){
		if (getIntent().getStringExtra("URL") != null) {
			url = getIntent().getStringExtra("URL");
		}else{
			url = UrlUtils.ENRZ_M;
		}
		webview.loadUrl(url);
	}
	
	public static void startEnrzMWebViewActivity(Context context, String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, EnrzMWebViewActivity.class);
		context.startActivity(intent);
	}
}
