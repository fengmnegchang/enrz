/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13下午1:52:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.open.enrz.R;
import com.open.enrz.bean.ContboxBean;
import com.open.enrz.html.URLImageParser;
import com.open.enrz.json.ContboxJson;
import com.open.enrz.jsoup.ContBoxScrollService;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13下午1:52:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class ContboxPullScrollFragment extends BaseV4Fragment<ContboxJson, ContboxPullScrollFragment> implements OnRefreshListener<ScrollView> {
	PullToRefreshScrollView mPullToRefreshScrollView;
	TextView  txt_centerp;
	public static ContboxPullScrollFragment newInstance(String url, boolean isVisibleToUser) {
		ContboxPullScrollFragment fragment = new ContboxPullScrollFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contbox_scrollview, container, false);
		mPullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.pull_scroll_list);
		txt_centerp = (TextView) view.findViewById(R.id.txt_centerp);
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
		mPullToRefreshScrollView.setMode(Mode.PULL_FROM_START);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
		mPullToRefreshScrollView.setOnRefreshListener(this);
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public ContboxJson call() throws Exception {
		// TODO Auto-generated method stub
		ContboxJson mContboxJson = new ContboxJson();
		mContboxJson.setContbox(ContBoxScrollService.parseContbox(url));
		return mContboxJson;
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(ContboxJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		ContboxBean  contbox = result.getContbox();
		if(contbox!=null){
			txt_centerp.setText(Html.fromHtml(contbox.getCenterp(), new URLImageParser(txt_centerp), null));  
		}
		mPullToRefreshScrollView.onRefreshComplete();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener
	 * #onRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
	 */
	@Override
	public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
		String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
		// Update the LastUpdatedLabel
		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
		// Do work to refresh the list here.
		if (mPullToRefreshScrollView.getCurrentMode() == Mode.PULL_FROM_START) {
			weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
		}
	}

}
