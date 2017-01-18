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

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.open.enrz.R;
import com.open.enrz.adapter.m.MThumbHeadAdapter;
import com.open.enrz.bean.m.MThumbBean;
import com.open.enrz.fragment.BaseV4Fragment;
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
public class MThumbHeadListFragment extends BaseV4Fragment<MThumbJson, MThumbHeadListFragment> {
	public ListView mExpendListView;
	public List<MThumbBean> list = new ArrayList<MThumbBean>();
	public MThumbHeadAdapter mMThumbHeadAdapter;
	
	public static MThumbHeadListFragment newInstance(String url, boolean isVisibleToUser) {
		MThumbHeadListFragment fragment = new MThumbHeadListFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_m_thumb_expend_listview, container, false);
		mExpendListView = (ListView) view.findViewById(R.id.expendlistview);
		return view;
	}
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		super.initValues();
		mMThumbHeadAdapter = new MThumbHeadAdapter(getActivity(),list);
		mExpendListView.setAdapter(mMThumbHeadAdapter);
	}
	
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public MThumbJson call() throws Exception {
		// TODO Auto-generated method stub
		MThumbJson mMThumbJson = new MThumbJson();
		mMThumbJson.setList(MThumbService.parseMThumb(url));
		return mMThumbJson;
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(MThumbJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		list.clear();
		list.addAll(result.getList());
		mMThumbHeadAdapter.notifyDataSetChanged();
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
}
