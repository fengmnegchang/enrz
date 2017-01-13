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

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.open.enrz.R;
import com.open.enrz.adapter.LogoThumbnaillAdapter;
import com.open.enrz.bean.ThumbnailBean;
import com.open.enrz.json.ThumbnaillJson;
import com.open.enrz.jsoup.EnrzLogoDefaultService;

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
public class LogoThumbnaillPullListViewFragment extends BaseV4Fragment<ThumbnaillJson, LogoThumbnaillPullListViewFragment> implements   OnRefreshListener<ListView>{
	public PullToRefreshListView mPullToRefreshListView;
	public LogoThumbnaillAdapter mLogoThumbnaillAdapter;
	public List<ThumbnailBean> list = new ArrayList<ThumbnailBean>();
	public View headview;
	public View footview;
	public static LogoThumbnaillPullListViewFragment newInstance(String url, boolean isVisibleToUser) {
		LogoThumbnaillPullListViewFragment fragment = new LogoThumbnaillPullListViewFragment();
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
		headview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_logo_thumbnaill_headview, null);
		footview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_logo_thumbnaill_footview, null);
		return view;
	}
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		super.initValues();
		ListView listview = mPullToRefreshListView.getRefreshableView();
		listview.addHeaderView(headview);
		listview.addFooterView(footview);
		
		Fragment headfragment = SlidePagerFragment.newInstance(url, true);
		getChildFragmentManager().beginTransaction().replace(R.id.layout_logo_thumbnaill_head, headfragment).commit();
		
		Fragment footfragment = SlideFootPagerFragment.newInstance(url, true);
		getChildFragmentManager().beginTransaction().replace(R.id.layout_logo_thumbnaill_foot, footfragment).commit();
		
		mLogoThumbnaillAdapter = new LogoThumbnaillAdapter(getActivity(),list);
		mPullToRefreshListView.setAdapter(mLogoThumbnaillAdapter);
		mPullToRefreshListView.setMode(Mode.BOTH);
	}
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
		mPullToRefreshListView.setOnRefreshListener(this);
	}
	
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public ThumbnaillJson call() throws Exception {
		// TODO Auto-generated method stub
		ThumbnaillJson mThumbnaillJson = new ThumbnaillJson();
		mThumbnaillJson.setList(EnrzLogoDefaultService.parseListThumbnail(url,pageNo));
		return mThumbnaillJson;
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(ThumbnaillJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
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
		mLogoThumbnaillAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
		mPullToRefreshListView.onRefreshComplete();
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#handlerMessage(android.os.Message)
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

	/* (non-Javadoc)
	 * @see com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener#onRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
	 */
	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
		// Update the LastUpdatedLabel
		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
		// Do work to refresh the list here.
		if (mPullToRefreshListView.getCurrentMode() == Mode.PULL_FROM_START) {
			pageNo = 1;
			weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
		} else if (mPullToRefreshListView.getCurrentMode() == Mode.PULL_FROM_END) {
			pageNo++;
			weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
		}
	}

}
