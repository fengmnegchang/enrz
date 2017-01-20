/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20上午9:45:01
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment.mobile;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.open.enrz.R;
import com.open.enrz.adapter.mobile.MobileGlobalNavMPullAdapter;
import com.open.enrz.fragment.LogoThumbnaillPullListViewFragment;
import com.open.enrz.fragment.SlideFootPagerFragment;
import com.open.enrz.fragment.SlidePagerFragment;
import com.open.enrz.json.ThumbnaillJson;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20上午9:45:01
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MobileGlobalNavMPullFragment extends LogoThumbnaillPullListViewFragment{
	public MobileGlobalNavMPullAdapter mMobileGlobalNavMPullAdapter;
	
	public static MobileGlobalNavMPullFragment newInstance(String url, boolean isVisibleToUser) {
		MobileGlobalNavMPullFragment fragment = new MobileGlobalNavMPullFragment();
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
		// TODO Auto-generated method stub
		ListView listview = mPullToRefreshListView.getRefreshableView();
		listview.addHeaderView(headview);
		listview.addFooterView(footview);
		
		Fragment headfragment = SlidePagerFragment.newInstance(url, true);
		getChildFragmentManager().beginTransaction().replace(R.id.layout_logo_thumbnaill_head, headfragment).commit();
		
		Fragment footfragment = SlideFootPagerFragment.newInstance(url, true);
		getChildFragmentManager().beginTransaction().replace(R.id.layout_logo_thumbnaill_foot, footfragment).commit();
		
		mMobileGlobalNavMPullAdapter = new MobileGlobalNavMPullAdapter(getActivity(),list);
		mPullToRefreshListView.setAdapter(mMobileGlobalNavMPullAdapter);
		mPullToRefreshListView.setMode(Mode.BOTH);
	}
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(ThumbnaillJson result) {
		// TODO Auto-generated method stub
		Log.i(TAG, "getMode ===" + mPullToRefreshListView.getCurrentMode());
		if (mPullToRefreshListView.getCurrentMode() == Mode.PULL_FROM_START) {
			list.clear();
			list.addAll(result.getList());
			pageNo = 1;
		} else {
			if (result.getList() != null && result.getList().size() > 0) {
				list.addAll(result.getList());
			}
		}
		mMobileGlobalNavMPullAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
		mPullToRefreshListView.onRefreshComplete();
	}
}
