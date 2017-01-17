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
import com.open.enrz.jsoup.GnSubService;

/**
 ***************************************************************************************************************************************************************************** 
 * 美图nav
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13上午11:03:26
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class PicSubNavIndicatorFragment extends GnSubIndicatorFragment {

	public static PicSubNavIndicatorFragment newInstance(String title, String url, boolean isVisibleToUser) {
		PicSubNavIndicatorFragment fragment = new PicSubNavIndicatorFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		fragment.title = title;
		return fragment;
	}
	
	@Override
	public GnSubJson call() throws Exception {
		// TODO Auto-generated method stub
		GnSubJson mGnSubJson = new GnSubJson();
		mGnSubJson.setList(GnSubService.parseNav(title,url));
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
			if (title.equals(bean.getTarget())) {
				fragment = PicPullGridFragment.newInstance(bean.getHref(), true);
			} else {
				fragment = PicPullGridFragment.newInstance(bean.getHref(), false);
			}
			listRankFragment.add(fragment);
		}
		mRankPagerAdapter.notifyDataSetChanged();
		indicator.notifyDataSetChanged();
	}

}
