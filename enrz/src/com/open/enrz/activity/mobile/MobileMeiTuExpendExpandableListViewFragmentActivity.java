/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20下午4:39:51
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
import com.open.enrz.fragment.PicExpendExpandableListViewHeadFragment;
import com.open.enrz.fragment.mobile.MobileMeiTuExpendExpandableListViewHeadFragment;
import com.open.enrz.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * mobile 美图首页
 * @author :fengguangjing
 * @createTime:2017-1-20下午4:39:51
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MobileMeiTuExpendExpandableListViewFragmentActivity  extends CommonFragmentActivity {

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
			url = UrlUtils.ENRZ_PIC_MOBILE;
		}
		
		 
//		Fragment fragment = PicExpendExpandableListViewFragment.newInstance(url, true);
		Fragment fragment = MobileMeiTuExpendExpandableListViewHeadFragment.newInstance(url, true);
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_indicator, fragment).commit();
	}

	public static void startMobileMeiTuExpendExpandableListViewFragmentActivity(Context context,  String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, MobileMeiTuExpendExpandableListViewFragmentActivity.class);
		context.startActivity(intent);
	}


}