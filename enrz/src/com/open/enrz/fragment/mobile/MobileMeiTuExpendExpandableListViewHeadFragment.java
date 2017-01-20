/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20下午4:41:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment.mobile;

import android.support.v4.app.Fragment;

import com.open.enrz.R;
import com.open.enrz.adapter.PicExpendExpandableListAdapter;
import com.open.enrz.fragment.PicExpendExpandableListViewHeadFragment;
import com.open.enrz.json.GnSubJson;
import com.open.enrz.jsoup.mobile.MobileMeiTuExpendService;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20下午4:41:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MobileMeiTuExpendExpandableListViewHeadFragment extends PicExpendExpandableListViewHeadFragment{

	public static MobileMeiTuExpendExpandableListViewHeadFragment newInstance(String url, boolean isVisibleToUser) {
		MobileMeiTuExpendExpandableListViewHeadFragment fragment = new MobileMeiTuExpendExpandableListViewHeadFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		mExpandableListView.addHeaderView(headview);
		Fragment fragment = MobileMeiTuHeadPagerFragment.newInstance(url, true);
		getChildFragmentManager().beginTransaction().replace(R.id.layout_pic_head, fragment).commit();
		
		mExpandableListView.setGroupIndicator(null);
		mPicExpendExpandableListAdapter = new PicExpendExpandableListAdapter(getActivity(), list);
		mExpandableListView.setAdapter(mPicExpendExpandableListAdapter);

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public GnSubJson call() throws Exception {
		// TODO Auto-generated method stub
		GnSubJson mGnSubJson = new GnSubJson();
		mGnSubJson.setList(MobileMeiTuExpendService.parseExpend(url));
		return mGnSubJson;
	}
}
