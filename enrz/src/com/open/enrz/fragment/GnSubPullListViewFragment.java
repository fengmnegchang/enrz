/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-12下午5:14:52
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.open.enrz.adapter.LogoThumbnaillAdapter;
import com.open.enrz.json.ThumbnaillJson;
import com.open.enrz.jsoup.GnSubPullListService;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-12下午5:14:52
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class GnSubPullListViewFragment extends LogoThumbnaillPullListViewFragment{
	
	public static GnSubPullListViewFragment newInstance(String url, boolean isVisibleToUser) {
		GnSubPullListViewFragment fragment = new GnSubPullListViewFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

 
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		mLogoThumbnaillAdapter = new LogoThumbnaillAdapter(getActivity(),list);
		mPullToRefreshListView.setAdapter(mLogoThumbnaillAdapter);
		mPullToRefreshListView.setMode(Mode.BOTH);
	}
 
	
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public ThumbnaillJson call() throws Exception {
		// TODO Auto-generated method stub
		ThumbnaillJson mThumbnaillJson = new ThumbnaillJson();
		//http://enrz.com/beauty/page/2
		mThumbnaillJson.setList(GnSubPullListService.parseListThumbnail(url,pageNo));
		return mThumbnaillJson;
	}

}
