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

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.open.enrz.R;
import com.open.enrz.adapter.m.MThumbCatePullExpandableAdapter;
import com.open.enrz.bean.m.MThumbCateBean;
import com.open.enrz.fragment.BaseV4Fragment;
import com.open.enrz.json.m.MThumbCateJson;
import com.open.enrz.jsoup.m.MThumbCateService;
import com.open.enrz.jsoup.m.MThumbService;

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
public class MThumbCateExpandListFragment extends BaseV4Fragment<MThumbCateJson, MThumbCateExpandListFragment> {
	public  ExpandableListView mPullToRefreshExpandableListView;
	public MThumbCatePullExpandableAdapter mMThumbCatePullExpandableAdapter;
	public List<MThumbCateBean> list = new ArrayList<MThumbCateBean>();
	
	public static MThumbCateExpandListFragment newInstance(String url, boolean isVisibleToUser) {
		MThumbCateExpandListFragment fragment = new MThumbCateExpandListFragment();
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
		return view;
	}
	
	 /* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		super.initValues();
		mPullToRefreshExpandableListView.setGroupIndicator(null);
		mMThumbCatePullExpandableAdapter = new MThumbCatePullExpandableAdapter(getActivity(),list); 
		mPullToRefreshExpandableListView.setAdapter(mMThumbCatePullExpandableAdapter);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public MThumbCateJson call() throws Exception {
		// TODO Auto-generated method stub
		MThumbCateJson mMThumbCateJson = new MThumbCateJson();
		mMThumbCateJson.setList(MThumbCateService.parseMThumbCate(url));
		return mMThumbCateJson;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(MThumbCateJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		list.clear();
		list.addAll(result.getList());
		mMThumbCatePullExpandableAdapter.notifyDataSetChanged();

		for (int i = 0; i < mMThumbCatePullExpandableAdapter.getGroupCount(); i++) {
			mPullToRefreshExpandableListView.expandGroup(i);
		}

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.umei.fragment.BaseV4Fragment#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
		mPullToRefreshExpandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				return true;
			}
		});
		mPullToRefreshExpandableListView.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {

			}
		});
		mPullToRefreshExpandableListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {

			}
		});
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
