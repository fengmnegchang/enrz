/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-17上午10:07:40
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
import com.open.enrz.fragment.PicIndicatorFragment;
import com.open.enrz.fragment.PicPullGridFragment;
import com.open.enrz.fragment.PicSubNavIndicatorFragment;
import com.open.enrz.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-17上午10:07:40
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class PicIndicatorSubNavFragmentActivity extends CommonFragmentActivity {

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
			url = UrlUtils.ENRZ_MISS;
		}
		
		if (getIntent().getStringExtra("TITLE") != null) {
			title = getIntent().getStringExtra("TITLE");
		} else {
			title = "美女";
		}
		 
		Fragment fragment = PicSubNavIndicatorFragment.newInstance(title,url, true);
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_indicator, fragment).commit();
	}

	public static void startPicPullGridFragmentActivity(Context context,  String title, String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.putExtra("TITLE", title);
		intent.setClass(context, PicIndicatorSubNavFragmentActivity.class);
		context.startActivity(intent);
	}

}
