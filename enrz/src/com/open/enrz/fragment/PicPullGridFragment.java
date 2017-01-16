/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午5:31:09
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
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.open.enrz.R;
import com.open.enrz.adapter.PicAdapter;
import com.open.enrz.bean.PicBean;
import com.open.enrz.json.PicJson;
import com.open.enrz.jsoup.PicService;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午5:31:09
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class PicPullGridFragment extends BaseV4Fragment<PicJson, PicPullGridFragment> implements OnRefreshListener<GridView>{
    public PullToRefreshGridView mPullToRefreshHeadGridView;
    public List<PicBean> list = new ArrayList<PicBean>();
	public PicAdapter mPicAdapter;
   
   
	public static PicPullGridFragment newInstance(String url, boolean isVisibleToUser) {
		PicPullGridFragment fragment = new PicPullGridFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_common_pullgridiview, container, false);
		mPullToRefreshHeadGridView = (PullToRefreshGridView) view.findViewById(R.id.pull_refresh_grid);
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
		mPicAdapter = new PicAdapter(getActivity(), list);
		mPullToRefreshHeadGridView.setAdapter(mPicAdapter);
		mPullToRefreshHeadGridView.setMode(Mode.BOTH);
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
		mPullToRefreshHeadGridView.setOnRefreshListener(this);
	}
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public PicJson call() throws Exception {
		// TODO Auto-generated method stub
		PicJson mPicJson = new PicJson();
		mPicJson.setList(PicService.parsePic(url, pageNo));
		return mPicJson;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(PicJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		Log.i(TAG, "getMode ===" + mPullToRefreshHeadGridView.getCurrentMode());
		if (mPullToRefreshHeadGridView.getCurrentMode() == Mode.PULL_FROM_START) {
			list.clear();
			list.addAll(result.getList());
			pageNo = 1;
		} else {
			if (result.getList() != null && result.getList().size() > 0) {
				list.addAll(result.getList());
			}
		}
		mPicAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
		mPullToRefreshHeadGridView.onRefreshComplete();
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
	public void onRefresh(PullToRefreshBase<GridView> refreshView) {
		String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
		// Update the LastUpdatedLabel
		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
		// Do work to refresh the list here.
		if (mPullToRefreshHeadGridView.getCurrentMode() == Mode.PULL_FROM_START) {
			pageNo = 1;
			weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
		} else if (mPullToRefreshHeadGridView.getCurrentMode() == Mode.PULL_FROM_END) {
			pageNo++;
			weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
		}
	}

}
