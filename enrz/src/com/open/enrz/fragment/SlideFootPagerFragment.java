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

import com.open.enrz.json.SlideJson;
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
public class SlideFootPagerFragment extends SlidePagerFragment {

	public static SlideFootPagerFragment newInstance(String url, boolean isVisibleToUser) {
		SlideFootPagerFragment fragment = new SlideFootPagerFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
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
		mSlideJson.setList(SlideFootService.parseSlide(url));
		return mSlideJson;
	}

}
