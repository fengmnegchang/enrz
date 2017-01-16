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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
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
public class RaletionPullListViewFragment extends HoursHotPullListViewFragment {
	public static RaletionPullListViewFragment newInstance(String url, boolean isVisibleToUser) {
		RaletionPullListViewFragment fragment = new RaletionPullListViewFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
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
	
	 
}
