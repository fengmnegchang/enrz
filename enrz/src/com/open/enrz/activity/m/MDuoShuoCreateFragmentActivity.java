/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20上午10:15:09
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.activity.m;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.open.enrz.R;
import com.open.enrz.activity.CommonFragmentActivity;
import com.open.enrz.fragment.m.MDuoShuoCreateFragment;
import com.open.enrz.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 评论
 * @author :fengguangjing
 * @createTime:2017-1-20上午10:15:09
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MDuoShuoCreateFragmentActivity extends CommonFragmentActivity {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.enrz.activity.CommonFragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gn_sub_indicator);
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.activity.CommonFragmentActivity#initValue()
	 */
	@Override
	protected void initValue() {
		// TODO Auto-generated method stub
		super.initValue();
		if (getIntent().getStringExtra("URL") != null) {
			url = getIntent().getStringExtra("URL");
		} else {
			url = "http://m.enrz.com/%e8%bf%99%e4%bd%8d%e9%9f%a9%e5%9b%bddj%e7%9a%84%e8%83%b8%e5%b0%86%e6%8a%96%e5%be%97%e4%bd%a0%e6%96%b9%e5%af%b8%e5%a4%a7%e4%b9%b1.html";
		}
		 
		Fragment fragment = MDuoShuoCreateFragment.newInstance(url, true);
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_indicator, fragment).commit();
	}

	public static void startMDuoShuoCreateFragmentActivity(Context context,  String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, MDuoShuoCreateFragmentActivity.class);
		context.startActivity(intent);
	}

}
