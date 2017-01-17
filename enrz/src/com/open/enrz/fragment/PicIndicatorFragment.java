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

import android.support.v4.app.Fragment;

import com.open.enrz.bean.GnSubBean;
import com.open.enrz.json.GnSubJson;

/**
 ***************************************************************************************************************************************************************************** 
 * 美图tab
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13上午11:03:26
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class PicIndicatorFragment extends GnSubIndicatorFragment {

	public static PicIndicatorFragment newInstance(String title, String url, boolean isVisibleToUser) {
		PicIndicatorFragment fragment = new PicIndicatorFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.title = title;
		fragment.url = url;
		return fragment;
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
			if (title.equals(bean.getTarget())) {
				fragment = GnSubPullListViewFragment.newInstance(bean.getHref(), true);
			} else {
				fragment = PicSubNavIndicatorFragment.newInstance(bean.getTarget(),bean.getHref(), false);
			}
			listRankFragment.add(fragment);
		}
		mRankPagerAdapter.notifyDataSetChanged();
		indicator.notifyDataSetChanged();
	}

}