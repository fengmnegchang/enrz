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

import android.os.Message;

import com.open.enrz.fragment.CommentPullListViewFragment;
import com.open.enrz.json.CommentJson;
import com.open.enrz.jsoup.CommentService;

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
	 
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public CommentJson call() throws Exception {
		// TODO Auto-generated method stub
		CommentJson mCommentJson = new CommentJson();
		mCommentJson.setList(CommentService.parseComment(url));
		return mCommentJson;
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
			doAsync(this, this, this);
			break;
		default:
			break;
		}
	}
	 

}
