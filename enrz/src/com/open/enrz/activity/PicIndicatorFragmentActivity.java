/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13上午11:00:33
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.open.enrz.R;
import com.open.enrz.fragment.GnSubIndicatorFragment;
import com.open.enrz.fragment.PicIndicatorFragment;
import com.open.enrz.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 美图
 * @author :fengguangjing
 * @createTime:2017-1-13上午11:00:33
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class PicIndicatorFragmentActivity extends CommonFragmentActivity {
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
			url = UrlUtils.ENRZ_PIC;
		}
		
		if (getIntent().getStringExtra("TITLE") != null) {
			title = getIntent().getStringExtra("TITLE");
		} else {
			title = "美图";
		}
		Fragment fragment = PicIndicatorFragment.newInstance(title,url, true);
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_indicator, fragment).commit();
	}

	public static void startPicIndicatorFragmentActivity(Context context, String title, String url) {
		Intent intent = new Intent();
		intent.putExtra("TITLE", title);
		intent.putExtra("URL", url);
		intent.setClass(context, PicIndicatorFragmentActivity.class);
		context.startActivity(intent);
	}

}
