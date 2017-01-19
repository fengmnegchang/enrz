/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13上午11:03:26
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
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.enrz.R;
import com.open.enrz.adapter.CommonFragmentPagerAdapter;
import com.open.enrz.bean.GnSubBean;
import com.open.enrz.json.GnSubJson;
import com.open.enrz.jsoup.GnSubService;
import com.open.indicator.TabPageIndicator;

/**
 ***************************************************************************************************************************************************************************** 
 * 子tab
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13上午11:03:26
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class GnSubIndicatorFragment extends BaseV4Fragment<GnSubJson, GnSubIndicatorFragment> {
	public ArrayList<GnSubBean> list = new ArrayList<GnSubBean>();
	public ViewPager viewpager;
	public TabPageIndicator indicator;
	public List<String> titleList = new ArrayList<String>();
	public List<Fragment> listRankFragment = new ArrayList<Fragment>();// view数组
	public CommonFragmentPagerAdapter mRankPagerAdapter;

	public static GnSubIndicatorFragment newInstance(String title,String url, boolean isVisibleToUser,int position) {
		GnSubIndicatorFragment fragment = new GnSubIndicatorFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.title = title;
		fragment.url = url;
		fragment.position = position;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_gb_sub_indicator_viewpager, container, false);
		viewpager = (ViewPager) view.findViewById(R.id.viewpager);
		indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.qianbailu.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		super.initValues();
		mRankPagerAdapter = new CommonFragmentPagerAdapter(getChildFragmentManager(), listRankFragment, titleList);
		viewpager.setAdapter(mRankPagerAdapter);
		indicator.setViewPager(viewpager);
	}

	@Override
	public GnSubJson call() throws Exception {
		// TODO Auto-generated method stub
		GnSubJson mGnSubJson = new GnSubJson();
		mGnSubJson.setList(GnSubService.parseGnSub(title,url));
		return mGnSubJson;
	}

	@Override
	public void onCallback(GnSubJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		list.clear();
		list.addAll(result.getList());
		titleList.clear();

		Fragment fragment;
		for (GnSubBean bean : result.getList()) {
			titleList.add(bean.getTarget());
			if(title.equals(bean.getTarget())){
				fragment = GnSubPullListViewFragment.newInstance(bean.getHref(), true);
			}else{
				fragment = GnSubPullListViewFragment.newInstance(bean.getHref(), false);
			}
			listRankFragment.add(fragment);
		}
		mRankPagerAdapter.notifyDataSetChanged();
		indicator.notifyDataSetChanged();
		
		if(position>0){
			weakReferenceHandler.sendEmptyMessageDelayed(MESSAGE_HANDLER_COMPLETE, 2000);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.tencenttv.BaseV4Fragment#handlerMessage(android.os.Message)
	 */
	@Override
	public void handlerMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handlerMessage(msg);
		switch (msg.what) {
		case MESSAGE_HANDLER:
			doAsync(this, this, this);
			break;
		case MESSAGE_HANDLER_COMPLETE:
			viewpager.setCurrentItem(position);
			indicator.setCurrentItem(position);
			break;
		default:
			break;
		}
	}
}
