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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.open.enrz.R;
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
public class DetailThumbPullListViewFragment extends LogoThumbnaillPullListViewFragment{
	
	public static DetailThumbPullListViewFragment newInstance(String url, boolean isVisibleToUser) {
		DetailThumbPullListViewFragment fragment = new DetailThumbPullListViewFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_common_pulllistview, container, false);
		mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
		footview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_detail_thumbnaill_footview, null);
		return view;
	}
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		ListView listview = mPullToRefreshListView.getRefreshableView();
		listview.addFooterView(footview);
		
		Fragment footfragment = DetailThumbExpendListFragment.newInstance(url, true);
		getChildFragmentManager().beginTransaction().replace(R.id.layout_detail_thumbnaill_foot, footfragment).commit();
		
		mLogoThumbnaillAdapter = new LogoThumbnaillAdapter(getActivity(),list);
		mPullToRefreshListView.setAdapter(mLogoThumbnaillAdapter);
		mPullToRefreshListView.setMode(Mode.PULL_FROM_START);
	}
 
	
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public ThumbnaillJson call() throws Exception {
		// TODO Auto-generated method stub
		ThumbnaillJson mThumbnaillJson = new ThumbnaillJson();
		mThumbnaillJson.setList(GnSubPullListService.parseDetailThumb(url));
		return mThumbnaillJson;
	}

}
