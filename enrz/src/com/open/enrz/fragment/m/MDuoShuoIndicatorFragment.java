/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-19上午9:37:56
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment.m;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;

import com.open.enrz.bean.GnSubBean;
import com.open.enrz.fragment.GnSubIndicatorFragment;
import com.open.enrz.fragment.PicPullGridFragment;
import com.open.enrz.json.GnSubJson;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-19上午9:37:56
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MDuoShuoIndicatorFragment extends GnSubIndicatorFragment {

	public static MDuoShuoIndicatorFragment newInstance(  String url, boolean isVisibleToUser) {
		MDuoShuoIndicatorFragment fragment = new MDuoShuoIndicatorFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}
	
	@Override
	public GnSubJson call() throws Exception {
		// TODO Auto-generated method stub
		GnSubJson mGnSubJson = new GnSubJson();
		List<GnSubBean> list = new ArrayList<GnSubBean>();
		GnSubBean bean = new GnSubBean();
		bean.setHref(url);
		bean.setTarget("最新");
		list.add(bean);
		
		bean = new GnSubBean();
		bean.setHref(url);
		bean.setTarget("最早");
		list.add(bean);
		
		bean = new GnSubBean();
		bean.setHref(url);
		bean.setTarget("最热");
		list.add(bean);
		
		mGnSubJson.setList(list);
		return mGnSubJson;
	}
	
	@Override
	public void onCallback(GnSubJson result) {
		// TODO Auto-generated method stub
		list.clear();
		list.addAll(result.getList());
		titleList.clear();

		Fragment fragment;
		for (GnSubBean bean : result.getList()) {
			titleList.add(bean.getTarget());
			if ("最新".equals(bean.getTarget())) {
				fragment = MDuoShuoPostsFragment.newInstance(bean.getHref(), true);
			} else {
				fragment = MDuoShuoPostsFragment.newInstance(bean.getHref(), false);
			}
			listRankFragment.add(fragment);
		}
		mRankPagerAdapter.notifyDataSetChanged();
		indicator.notifyDataSetChanged();
	}

}
