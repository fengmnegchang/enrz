/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-19上午9:41:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment.m;

import com.open.enrz.fragment.CommentPullListViewFragment;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-19上午9:41:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MDuoShuoPostsFragment extends CommentPullListViewFragment{

	public static MDuoShuoPostsFragment newInstance(String url, boolean isVisibleToUser) {
		MDuoShuoPostsFragment fragment = new MDuoShuoPostsFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

}
