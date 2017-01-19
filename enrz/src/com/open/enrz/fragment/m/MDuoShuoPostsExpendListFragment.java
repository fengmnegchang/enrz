/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-19上午9:41:59
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
import android.widget.ExpandableListView;

import com.open.enrz.R;
import com.open.enrz.adapter.CommentAdapter;
import com.open.enrz.fragment.CommentPullListViewFragment;
import com.open.enrz.json.CommentJson;
import com.open.enrz.view.ExpendListView;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-19上午9:41:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MDuoShuoPostsExpendListFragment extends CommentPullListViewFragment {
	public ExpendListView mPullToRefreshListView;

	public static MDuoShuoPostsExpendListFragment newInstance(String url, boolean isVisibleToUser) {
		MDuoShuoPostsExpendListFragment fragment = new MDuoShuoPostsExpendListFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_m_duoshuo_expend_listview, container, false);
		mPullToRefreshListView = (ExpendListView) view.findViewById(R.id.expendlistview);
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		mCommentAdapter = new CommentAdapter(getActivity(), list);
		mPullToRefreshListView.setAdapter(mCommentAdapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(CommentJson result) {
		list.clear();
		list.addAll(result.getList());
		pageNo = 1;
		mCommentAdapter.notifyDataSetChanged();
	}

}
