/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20下午5:22:00
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.activity.mobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.open.enrz.R;
import com.open.enrz.activity.CommonFragmentActivity;
import com.open.enrz.activity.PicPullGridFragmentActivity;
import com.open.enrz.fragment.PicPullGridFragment;
import com.open.enrz.fragment.mobile.MobileMeiTuPullGridFragment;
import com.open.enrz.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * mobile 图片列表
 * @author :fengguangjing
 * @createTime:2017-1-20下午5:22:00
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MobileMeiTuPullGridFragmentActivity extends CommonFragmentActivity {

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
			url = UrlUtils.ENRZ_MOBILE_SEXY;
		}
		
		 
		Fragment fragment = MobileMeiTuPullGridFragment.newInstance(url, true);
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_indicator, fragment).commit();
	}

	public static void startMobileMeiTuPullGridFragmentActivity(Context context,  String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, MobileMeiTuPullGridFragmentActivity.class);
		context.startActivity(intent);
	}

}