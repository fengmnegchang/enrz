/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午1:41:07
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Message;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.open.enrz.R;
import com.open.enrz.adapter.SearchAdapter;
import com.open.enrz.bean.SearchBean;
import com.open.enrz.json.SearchJson;
import com.open.enrz.jsoup.SearchService;
import com.open.enrz.weak.WeakActivityReferenceHandler;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午1:41:07
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class SearchActivity extends CommonFragmentActivity<SearchJson> implements OnRefreshListener<ListView>,
OnItemClickListener{
	private EditText edit_search;
	private Button btn_search;
	private PullToRefreshListView mPullToRefreshListView;
	private List<SearchBean> list = new ArrayList<SearchBean>();
	private SearchAdapter mSearchAdapter;
	/* (non-Javadoc)
	 * @see com.open.enrz.activity.CommonFragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		init();
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.activity.CommonFragmentActivity#findView()
	 */
	@Override
	protected void findView() {
		// TODO Auto-generated method stub
		super.findView();
		edit_search = (EditText) findViewById(R.id.edit_search);
		btn_search = (Button) findViewById(R.id.btn_search);
		mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.activity.CommonFragmentActivity#initValue()
	 */
	@Override
	protected void initValue() {
		// TODO Auto-generated method stub
		super.initValue();
		weakReferenceHandler = new WeakActivityReferenceHandler(this);
		pageNo = 1;
		
		mSearchAdapter = new SearchAdapter(this,list);
		mPullToRefreshListView.setAdapter(mSearchAdapter);
		mPullToRefreshListView.setMode(Mode.BOTH);
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.activity.CommonFragmentActivity#bindEvent()
	 */
	@Override
	protected void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
		btn_search.setOnClickListener(this);
		mPullToRefreshListView.setOnRefreshListener(this);
		mPullToRefreshListView.setOnItemClickListener(this);
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.activity.CommonFragmentActivity#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_search:
			//搜索
			String search = edit_search.getText().toString();
			if(search.length()>0){
				try {
					url =   URLEncoder.encode(search, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
			}
			break;
		default:
			break;
		}
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.activity.BaseFragmentActivity#call()
	 */
	@Override
	public SearchJson call() throws Exception {
		// TODO Auto-generated method stub
		SearchJson mSearchJson = new SearchJson();
		mSearchJson.setList(SearchService.parseSearch(url,pageNo));
		return mSearchJson;
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.activity.BaseFragmentActivity#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(SearchJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		if (mPullToRefreshListView.getCurrentMode() == Mode.PULL_FROM_START) {
			list.clear();
			list.addAll(result.getList());
			pageNo = 1;
		}else {
			if (result.getList() != null && result.getList().size() > 0) {
				list.addAll(result.getList());
			}
		}
		mSearchAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
		mPullToRefreshListView.onRefreshComplete();
	}

	/* (non-Javadoc)
	 * @see com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener#onRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
	 */
	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		String label = DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
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
	
	/* (non-Javadoc)
	 * @see com.open.enrz.activity.BaseFragmentActivity#handlerMessage(android.os.Message)
	 */
	@Override
	public void handlerMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handlerMessage(msg);
		switch (msg.what) {
		case MESSAGE_HANDLER:
			doAsync(this, this, this);
			break;
		default:
			break;
		}
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		if((int)id!=-1 && list!=null && list.size()>0 && list.get((int)id)!=null){
			EnrzWebViewActivity.startEnrzWebViewActivity(this, list.get((int)id).getHref());
		}
	}

}
