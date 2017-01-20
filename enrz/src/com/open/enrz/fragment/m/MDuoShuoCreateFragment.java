/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20上午10:16:09
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment.m;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.open.enrz.R;
import com.open.enrz.fragment.BaseV4Fragment;
import com.open.enrz.json.m.MDuoShuoCreateJson;
import com.open.enrz.utils.UrlUtils;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20上午10:16:09
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MDuoShuoCreateFragment extends BaseV4Fragment<MDuoShuoCreateJson, MDuoShuoCreateFragment> {
	public EditText edit_create;
	public Button btn_summit;

	public static MDuoShuoCreateFragment newInstance(String url, boolean isVisibleToUser) {
		MDuoShuoCreateFragment fragment = new MDuoShuoCreateFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_m_duoshuo_create, container, false);
		edit_create = (EditText) view.findViewById(R.id.edit_create);
		btn_summit = (Button) view.findViewById(R.id.btn_summit);
		return view;
	}
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
		btn_summit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (edit_create.getText()!=null && edit_create.getText().toString().length() > 0) {
					weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
				}
			}
		});
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
			if (edit_create.getText()!=null && edit_create.getText().toString().length() > 0) {
				volleyJson(edit_create.getText().toString());
			}
			break;
		default:
			break;
		}
	}
 

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#volleyJson(java.lang.String)
	 */
	@Override
	public void volleyJson(String create) {
		// TODO Auto-generated method stub
	
//		try {
//			create = URLEncoder.encode(create, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		
		/**
		 * thread_id:6291875030754149122
	parent_id:
	nonce:5865eca717dea
	message:好
	author_name:就
	v:16.6.16
		 */
		final Map<String, String> params = new HashMap<String, String>();
		params.put("thread_id", "6291875030754149122");
		params.put("parent_id", "");
		params.put("nonce", "5865eca717dea");
		params.put("message", create);
		params.put("author_name", "123");
		params.put("v", "16.6.16");
		
		
		/**
		 * Accept: 
Accept-Encoding:gzip, deflate
Accept-Language:zh-CN,zh;q=0.8
Connection:keep-alive
Content-Length:110
Content-Type:application/x-www-form-urlencoded; charset=UTF-8
Cookie:duoshuo_unique=40242c9ac3589a9d
Host:menrz.duoshuo.com
Origin:http://m.enrz.com
Referer:http://m.enrz.com/%e8%bf%99%e4%bd%8d%e9%9f%a9%e5%9b%bddj%e7%9a%84%e8%83%b8%e5%b0%86%e6%8a%96%e5%be%97%e4%bd%a0%e6%96%b9%e5%af%b8%e5%a4%a7%e4%b9%b1.html
User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.75 Safari/537.36 QQBrowser/4.1.4132.400
		 */
		final Map<String, String> header  = new HashMap<String, String>();
//		header.put("Accept", "*/*");
//		header.put("Accept-Encoding", "gzip, deflate");
//		header.put("Accept-Language", "zh-CN,zh;q=0.8");
//		header.put("Connection", "keep-alive");
		header.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		header.put("Cookie", "duoshuo_unique=40242c9ac3589a9d");
		header.put("Host", "menrz.duoshuo.com");
		header.put("Origin", "http://m.enrz.com");
		header.put("Referer", url);
		header.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.75 Safari/537.36 QQBrowser/4.1.4132.400");
		
		StringRequest jsonObjectRequest = new StringRequest(StringRequest.Method.POST,
				UrlUtils.ENRZ_M_CREATE,new Listener<String>() {
					@Override
					public void onResponse(String response) {
						System.out.println(response);
						//{"response":{"post_id":"6377515932004123394","thread_id":"6291875030754149122","status":"approved","source":"duoshuo","type":"duoshuo","message":"成本","created_at":"2017-01-20T10:58:59+08:00","privileges":[],"parent_id":0,"root_id":0,"reposts":0,"comments":0,"author_id":"0","author_key":"0","agent":"Mozilla\/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit\/537.36 (KHTML, like Gecko) Chrome\/50.0.2661.75 Safari\/537.36 QQBrowser\/4.1.4132.400","likes":0,"dislikes":0,"reports":0,"parents":[],"author":{"name":"不v","avatar_url":null,"url":null},"thread":{"thread_id":"6291875030754149122","site_id":1276975,"title":"这位韩国DJ的胸将抖得你方寸大乱","created_at":"2016-08-06T15:00:07+08:00","thread_key":"5621","url":"http:\/\/m.enrz.com\/%e8%bf%99%e4%bd%8d%e9%9f%a9%e5%9b%bddj%e7%9a%84%e8%83%b8%e5%b0%86%e6%8a%96%e5%be%97%e4%bd%a0%e6%96%b9%e5%af%b8%e5%a4%a7%e4%b9%b1.html","meta":{"guid":"http:\/\/m.enrz.com\/?p=5621","comment_status":"open","ping_status":"open"},"agent":"WordPress\/4.1.12|Duoshuo\/1.2","source":"post","author_id":"11351583","comments":6,"dislikes":0,"likes":0,"reposts":0,"views":0,"post_enable":1},"site":{"site_id":"1276975","short_name":"menrz","url":"http:\/\/m.enrz.com","name":"男人装官方网站"},"is_top":0},"code":0}
					}
				},this){
			/* (non-Javadoc)
			 * @see com.android.volley.Request#getParams()
			 */
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				return params;
			}
			
			/* (non-Javadoc)
					 * @see com.android.volley.toolbox.JsonObjectRequest#getHeaders()
					 */
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// TODO Auto-generated method stub
				return header;
			}
		};
		requestQueue.add(jsonObjectRequest);
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#onErrorResponse(com.android.volley.VolleyError)
	 */
	@Override
	public void onErrorResponse(VolleyError error) {
		// TODO Auto-generated method stub
		super.onErrorResponse(error);
		System.out.println(error.toString());
	}

}
