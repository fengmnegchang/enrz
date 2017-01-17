/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13上午9:34:30
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.open.enrz.R;
import com.open.enrz.json.SlideJson;
import com.open.enrz.jsoup.PicService;
import com.open.enrz.jsoup.SlideFootService;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13上午9:34:30
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class PicHeadPagerFragment extends SlidePagerFragment {

	public static PicHeadPagerFragment newInstance(String url, boolean isVisibleToUser) {
		PicHeadPagerFragment fragment = new PicHeadPagerFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pic_head_viewpager, container, false);
		viewpager = (ViewPager) view.findViewById(R.id.viewpager);
		layout_dot = (LinearLayout) view.findViewById(R.id.layout_dot);
		return view;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public SlideJson call() throws Exception {
		// TODO Auto-generated method stub
		SlideJson mSlideJson = new SlideJson();
		mSlideJson.setList(PicService.parseExpendHead(url));
		return mSlideJson;
	}

}
