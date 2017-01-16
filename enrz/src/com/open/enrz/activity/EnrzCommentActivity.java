/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16上午10:53:37
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
import com.open.enrz.fragment.CommentPullListViewFragment;
import com.open.enrz.fragment.HoursHotPullListViewFragment;
import com.open.enrz.fragment.RaletionPullListViewFragment;
import com.open.enrz.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 评论
 * @author :fengguangjing
 * @createTime:2017-1-16上午10:53:37
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class EnrzCommentActivity extends CommonFragmentActivity {

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
		setContentView(R.layout.activity_enrz_24hours_hot_fragment);
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
			url = UrlUtils.ENRZ_COMMENT;
		}
		Fragment fragment = CommentPullListViewFragment.newInstance(url, true);
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_hourhot, fragment).commit();
	}

	public static void startEnrzCommentActivity(Context context, String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, EnrzCommentActivity.class);
		context.startActivity(intent);
	}

}
