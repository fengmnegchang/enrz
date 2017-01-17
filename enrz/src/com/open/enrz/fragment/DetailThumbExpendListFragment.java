/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-17下午2:44:27
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.open.enrz.R;
import com.open.enrz.adapter.ThumbFootAdapter;
import com.open.enrz.bean.ThumbnailBean;
import com.open.enrz.json.ThumbnaillJson;
import com.open.enrz.jsoup.EnrzLogoDefaultService;
import com.open.enrz.jsoup.GnSubPullListService;
import com.open.enrz.view.ExpendListView;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-17下午2:44:27
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class DetailThumbExpendListFragment extends BaseV4Fragment<ThumbnaillJson, DetailThumbExpendListFragment> {
	public ExpendListView mExpendListView;
	public List<ThumbnailBean> list = new ArrayList<ThumbnailBean>();
	public ThumbFootAdapter mThumbFootAdapter;

	public static DetailThumbExpendListFragment newInstance(String url, boolean isVisibleToUser) {
		DetailThumbExpendListFragment fragment = new DetailThumbExpendListFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_detail_thumb_foot_expend_listview, container, false);
		mExpendListView = (ExpendListView) view.findViewById(R.id.expendlistview);
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
		super.initValues();
		mThumbFootAdapter = new ThumbFootAdapter(getActivity(), list);
		mExpendListView.setAdapter(mThumbFootAdapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public ThumbnaillJson call() throws Exception {
		// TODO Auto-generated method stub
		ThumbnaillJson mThumbnaillJson = new ThumbnaillJson();
		mThumbnaillJson.setList(GnSubPullListService.parseThumbFoot(url));
		return mThumbnaillJson;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(ThumbnaillJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		list.clear();
		list.addAll(result.getList());
		mThumbFootAdapter.notifyDataSetChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.enrz.fragment.BaseV4Fragment#handlerMessage(android.os.Message)
	 */
	@Override
	public void handlerMessage(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what) {
		case MESSAGE_HANDLER:
			doAsync(this, this, this);
			break;
		default:
			break;
		}
	}

}
