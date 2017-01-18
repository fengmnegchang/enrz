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
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.open.enrz.R;
import com.open.enrz.adapter.m.MThumbHeadAdapter;
import com.open.enrz.json.m.MThumbJson;
import com.open.enrz.jsoup.m.MThumbService;

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
public class MThumbPullListFragment extends MThumbHeadListFragment implements   OnRefreshListener<ListView>{
	public PullToRefreshListView mExpendListView;
	public TextView txt_title;
	
	public static MThumbPullListFragment newInstance(String url, boolean isVisibleToUser) {
		MThumbPullListFragment fragment = new MThumbPullListFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_m_thumb_pull_listview, container, false);
		mExpendListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
		txt_title = (TextView) view.findViewById(R.id.txt_title);
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
		mExpendListView.setMode(Mode.BOTH);
	}
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
		mExpendListView.setOnRefreshListener(this);
	}
	
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public MThumbJson call() throws Exception {
		// TODO Auto-generated method stub
		MThumbJson mMThumbJson = new MThumbJson();
		mMThumbJson.setList(MThumbService.parseMThumb(url,pageNo));
		return mMThumbJson;
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(MThumbJson result) {
		// TODO Auto-generated method stub
		Log.i(TAG, "getMode ===" + mExpendListView.getCurrentMode());
		if (mExpendListView.getCurrentMode() == Mode.PULL_FROM_START) {
			list.clear();
			list.addAll(result.getList());
			pageNo = 1;
		} else {
			if (result.getList() != null && result.getList().size() > 0) {
				list.addAll(result.getList());
			}
		}
		mMThumbHeadAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
		mExpendListView.onRefreshComplete();
		try {
			txt_title.setText(result.getList().get(0).getCrumbstitle());
		} catch (Exception e) {
			e.printStackTrace();
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
		if (mExpendListView.getCurrentMode() == Mode.PULL_FROM_START) {
			pageNo = 1;
			weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
		} else if (mExpendListView.getCurrentMode() == Mode.PULL_FROM_END) {
			pageNo++;
			weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
		}
	}

}
