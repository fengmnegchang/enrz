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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.open.enrz.R;
import com.open.enrz.json.m.MThumbJson;
import com.open.enrz.jsoup.m.MThumbService;

/**
 ***************************************************************************************************************************************************************************** 
 * m首页头部list
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午10:16:36
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MSearchPullListFragment extends MThumbPullListFragment {
	public EditText edit_search;
	public Button btn_search;
	public String search;
	public static MSearchPullListFragment newInstance(String url, boolean isVisibleToUser) {
		MSearchPullListFragment fragment = new MSearchPullListFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_m_search_pull_listview, container, false);
		mExpendListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
		txt_title = (TextView) view.findViewById(R.id.txt_title);
		edit_search = (EditText) view.findViewById(R.id.edit_search);
		btn_search = (Button) view.findViewById(R.id.btn_search);
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.m.MThumbPullListFragment#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
		btn_search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				search = edit_search.getText().toString();
				if (search.length() > 0) {
					//http://m.enrz.com/page/2?s=av
					//http://m.enrz.com/?s=av
					try {
						search = URLEncoder.encode(search, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					weakReferenceHandler.sendEmptyMessageDelayed(MESSAGE_HANDLER, 50);
				}
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public MThumbJson call() throws Exception {
		// TODO Auto-generated method stub
		MThumbJson mMThumbJson = new MThumbJson();
		mMThumbJson.setList(MThumbService.parseMSearch(url,search ,pageNo));
		return mMThumbJson;
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
			if (search!=null && search.length() > 0) {
				doAsync(this, this, this);
			}
			break;
		default:
			break;
		}
	}
}
