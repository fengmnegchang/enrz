/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20下午5:25:28
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment.mobile;

import java.util.ArrayList;
import java.util.List;

import android.os.Message;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.open.enrz.adapter.PicAdapter;
import com.open.enrz.bean.PicBean;
import com.open.enrz.bean.mobile.MobileMeiTuBean;
import com.open.enrz.fragment.PicPullGridFragment;
import com.open.enrz.json.PicJson;
import com.open.enrz.jsoup.PicService;
import com.open.enrz.jsoup.mobile.MobileMeiTuJson;
import com.open.enrz.utils.UrlUtils;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20下午5:25:28
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MobileMeiTuPullGridFragment extends PicPullGridFragment {
	public static MobileMeiTuPullGridFragment newInstance(String url, boolean isVisibleToUser) {
		MobileMeiTuPullGridFragment fragment = new MobileMeiTuPullGridFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
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
		mPullToRefreshHeadGridView.setOnRefreshListener(this);
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public PicJson call() throws Exception {
		// TODO Auto-generated method stub
		PicJson mPicJson = new PicJson();
		if(pageNo==1){
			mPicJson.setList(PicService.parsePic(url, pageNo));
		}
		return mPicJson;
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
			if(pageNo==1){
				doAsync(this, this, this);
			}else{
				//http://meitu.enrz.com/sexy/2.shtml
				//http://meitu.enrz.com/sexy
				//解析json
				String href = url+pageNo+".shtml";
				volleyJson(href);
			}
			break;
		default:
			break;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#volleyJson(java.lang.String)
	 */
	public void volleyJson(String href) {
		// TODO Auto-generated method stub
		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		StringRequest jsonObjectRequest = new StringRequest(StringRequest.Method.GET,
				href,new Listener<String>() {
					@Override
					public void onResponse(String response) {
						System.out.println(response);
						String json = "{\"list\":"+response+"}";
						Gson gson = new Gson();
						MobileMeiTuJson mMobileMeiTuJson = gson.fromJson(json, MobileMeiTuJson.class);
						if(mMobileMeiTuJson!=null && mMobileMeiTuJson.getList()!=null && mMobileMeiTuJson.getList().size()>0){
							List<PicBean> list = new ArrayList<PicBean>();
							PicBean pbean;
							for(MobileMeiTuBean mbean:mMobileMeiTuJson.getList()){
								pbean = new PicBean();
								pbean.setHref(mbean.getMurl());
								pbean.setSrc(mbean.getMpicurl());
								pbean.setTitle(mbean.getTitle());
								list.add(pbean);
							}
							PicJson result = new PicJson();
							result.setList(list);
							onCallback(result);
						}
					}
				},this) ;
		requestQueue.add(jsonObjectRequest);
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#onErrorResponse(com.android.volley.VolleyError)
	 */
	@Override
	public void onErrorResponse(VolleyError error) {
		// TODO Auto-generated method stub
		super.onErrorResponse(error);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(PicJson result) {
		// TODO Auto-generated method stub
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

}
