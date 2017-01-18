/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午11:10:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment.m;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.open.enrz.R;
import com.open.enrz.adapter.m.MThumbCatePullExpandableAdapter;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午11:10:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MThumbCateHeadExpandListFragment extends MThumbCateExpandListFragment {
	public View headview;
	
	public static MThumbCateHeadExpandListFragment newInstance(String url, boolean isVisibleToUser) {
		MThumbCateHeadExpandListFragment fragment = new MThumbCateHeadExpandListFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_m_thumb_cate_expandlistview, container, false);
		mPullToRefreshExpandableListView = (ExpandableListView) view.findViewById(R.id.pull_expandable_listview);
		headview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_m_thumb_cate_headview, null);
		return view;
	}
	
	 /* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		mPullToRefreshExpandableListView.addHeaderView(headview);
		Fragment fragment = MThumbHeadExpendListFragment.newInstance(url, true);
		getChildFragmentManager().beginTransaction().replace(R.id.layout_m_thumb_cate_head, fragment).commit();
		
		mPullToRefreshExpandableListView.setGroupIndicator(null);
		mMThumbCatePullExpandableAdapter = new MThumbCatePullExpandableAdapter(getActivity(),list); 
		mPullToRefreshExpandableListView.setAdapter(mMThumbCatePullExpandableAdapter);
	}
	 
}
