/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20下午4:59:16
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment.mobile;

import com.open.enrz.fragment.PicHeadPagerFragment;
import com.open.enrz.json.SlideJson;
import com.open.enrz.jsoup.PicService;
import com.open.enrz.jsoup.mobile.MobileMeiTuExpendService;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20下午4:59:16
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MobileMeiTuHeadPagerFragment extends PicHeadPagerFragment{
	
	public static MobileMeiTuHeadPagerFragment newInstance(String url, boolean isVisibleToUser) {
		MobileMeiTuHeadPagerFragment fragment = new MobileMeiTuHeadPagerFragment();
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
		mSlideJson.setList(MobileMeiTuExpendService.parseExpendHead(url));
		return mSlideJson;
	}
}
