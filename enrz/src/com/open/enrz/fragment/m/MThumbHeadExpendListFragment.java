/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午10:16:36
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment.m;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.enrz.R;
import com.open.enrz.adapter.m.MThumbHeadAdapter;
import com.open.enrz.view.ExpendListView;

/**
 *****************************************************************************************************************************************************************************
 * m首页头部list
 * @author :fengguangjing
 * @createTime:2017-1-18上午10:16:36
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MThumbHeadExpendListFragment extends MThumbHeadListFragment {
	public ExpendListView mExpendListView;
	
	public static MThumbHeadExpendListFragment newInstance(String url, boolean isVisibleToUser) {
		MThumbHeadExpendListFragment fragment = new MThumbHeadExpendListFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_m_thumb_expend_listview, container, false);
		mExpendListView = (ExpendListView) view.findViewById(R.id.expendlistview);
		return view;
	}
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		mMThumbHeadAdapter = new MThumbHeadAdapter(getActivity(),list);
		mExpendListView.setAdapter(mMThumbHeadAdapter);
	}
	 
}
