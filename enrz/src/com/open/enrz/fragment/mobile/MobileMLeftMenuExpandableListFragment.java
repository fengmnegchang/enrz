/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-19上午11:12:48
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment.mobile;

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
import com.open.enrz.adapter.mobile.MobileMLeftMenuExpandableListAdapter;
import com.open.enrz.bean.GlobalNavBean;
import com.open.enrz.fragment.BaseV4Fragment;
import com.open.enrz.json.GlobalNavJson;
import com.open.enrz.jsoup.mobile.MobileMainService;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-19上午11:12:48
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MobileMLeftMenuExpandableListFragment extends BaseV4Fragment<GlobalNavJson, MobileMLeftMenuExpandableListFragment> {
	public MobileMLeftMenuExpandableListAdapter mMobileMLeftMenuExpandableListAdapter;
	public List<GlobalNavBean> list = new ArrayList<GlobalNavBean>();
	public ExpandableListView mExpandableListView;
	
	public static MobileMLeftMenuExpandableListFragment newInstance(String url, boolean isVisibleToUser) {
		MobileMLeftMenuExpandableListFragment fragment = new MobileMLeftMenuExpandableListFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_common_expendview, container, false);
		mExpandableListView = (ExpandableListView) view.findViewById(R.id.expendablelistview);
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
		mExpandableListView.setGroupIndicator(null);
		mMobileMLeftMenuExpandableListAdapter = new MobileMLeftMenuExpandableListAdapter(getActivity(), list);
		mExpandableListView.setAdapter(mMobileMLeftMenuExpandableListAdapter);
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
		mExpandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				return true;
			}
		});
		mExpandableListView.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {

			}
		});
		mExpandableListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {

			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public GlobalNavJson call() throws Exception {
		// TODO Auto-generated method stub
		GlobalNavJson mGlobalNavJson = new GlobalNavJson();
		mGlobalNavJson.setList(MobileMainService.parseMainTab(url));
		return mGlobalNavJson;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(GlobalNavJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		list.clear();
		list.addAll(result.getList());
		mMobileMLeftMenuExpandableListAdapter.notifyDataSetChanged();

		for (int i = 0; i < mMobileMLeftMenuExpandableListAdapter.getGroupCount(); i++) {
			mExpandableListView.expandGroup(i);
		}

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
