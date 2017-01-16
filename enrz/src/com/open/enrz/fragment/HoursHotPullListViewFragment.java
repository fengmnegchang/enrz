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

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.open.enrz.R;
import com.open.enrz.adapter.LogoThumbnaillAdapter;
import com.open.enrz.json.HourHotJson;
import com.open.enrz.json.ThumbnaillJson;
import com.open.enrz.jsoup.HourHotService;

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
public class HoursHotPullListViewFragment extends LogoThumbnaillPullListViewFragment {
	private String html;
	public static HoursHotPullListViewFragment newInstance(String url, boolean isVisibleToUser) {
		HoursHotPullListViewFragment fragment = new HoursHotPullListViewFragment();
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
		mLogoThumbnaillAdapter = new LogoThumbnaillAdapter(getActivity(), list);
		mPullToRefreshListView.setAdapter(mLogoThumbnaillAdapter);
		mPullToRefreshListView.setMode(Mode.BOTH);
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
		mThumbnaillJson.setList(HourHotService.parseListHourHot(html));
		return mThumbnaillJson;
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#volleyJson(java.lang.String)
	 */
	@Override
	public void volleyJson(String href) {
		// TODO Auto-generated method stub
		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//		StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, href,  
//				new Response.Listener<String>() {
//					@Override
//					public void onResponse(String response) {
//						System.out.println(response);
//					}
//				}, this) ;
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, href,  null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Gson gson = new Gson();
						HourHotJson mHourHotJson = gson.fromJson(response.toString(), HourHotJson.class);
						System.out.println(mHourHotJson.getShowHtml());
						html = mHourHotJson.getShowHtml();
						weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER_COMPLETE);
					}
				}, this) ;
		requestQueue.add(jsonObjectRequest);
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#onErrorResponse(com.android.volley.VolleyError)
	 */
	@Override
	public void onErrorResponse(VolleyError error) {
		// TODO Auto-generated method stub
		super.onErrorResponse(error);
		System.out.println(error);
	}
	
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#handlerMessage(android.os.Message)
	 */
	@Override
	public void handlerMessage(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what) {
		case MESSAGE_HANDLER:
			volleyJson(url);
			break;
		case MESSAGE_HANDLER_COMPLETE:
			doAsync(this, this, this);
			break;
		default:
			break;
		}
	}
}
