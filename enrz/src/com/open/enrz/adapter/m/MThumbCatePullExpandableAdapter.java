/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午11:14:55
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.adapter.m;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.open.enrz.R;
import com.open.enrz.activity.EnrzWebViewActivity;
import com.open.enrz.activity.m.MArticlePFragmentActivity;
import com.open.enrz.activity.m.MThumbCatePageListFragmentActivity;
import com.open.enrz.adapter.CommonExpandableListAdapter;
import com.open.enrz.bean.m.MArticleBean;
import com.open.enrz.bean.m.MThumbBean;
import com.open.enrz.bean.m.MThumbCateBean;
import com.open.enrz.view.ExpendListView;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午11:14:55
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MThumbCatePullExpandableAdapter extends CommonExpandableListAdapter<MThumbCateBean, MArticleBean> {

	public MThumbCatePullExpandableAdapter(Context mContext, List<MThumbCateBean> list) {
		super(mContext, list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.adapter.CommonExpandableListAdapter#getGroupView(int,
	 * boolean, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getGroupView(int groupPosition, boolean arg1, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		GroupViewHolder mGroupViewHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_m_thumb_cate_expend_group, null);
			mGroupViewHolder = new GroupViewHolder();
			mGroupViewHolder.text_moduleTitle = (TextView) convertView.findViewById(R.id.text_moduleTitle);
			convertView.setTag(mGroupViewHolder);
		} else {
			mGroupViewHolder = (GroupViewHolder) convertView.getTag();
		}
		final MThumbCateBean bean = (MThumbCateBean) getGroup(groupPosition);
		if (bean != null) {
			mGroupViewHolder.text_moduleTitle.setText(bean.getTitle());
			mGroupViewHolder.text_moduleTitle.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					EnrzWebViewActivity.startEnrzWebViewActivity(mContext, bean.getHref());
					MThumbCatePageListFragmentActivity.startMThumbCatePageListFragmentActivity(mContext, bean.getHref());
				}
			});
		}
		return convertView;
	}

	public class GroupViewHolder {
		TextView text_moduleTitle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.tencenttv.adapter.CommonExpandableListAdapter#getChildrenCount
	 * (int)
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		if (getGroup(groupPosition) != null && ((getGroup(groupPosition).getArticlelist() != null && getGroup(groupPosition).getArticlelist().size() > 0))) {
			return 1;
		} else {
			return 0;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.tencenttv.adapter.CommonExpandableListAdapter#getChildView(int,
	 * int, boolean, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getChildView(final int groupPosition, int childPosition, boolean arg2, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ChildViewHolder mChildViewHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_m_thumb_cate_expend_child, null);
			mChildViewHolder = new ChildViewHolder();
			mChildViewHolder.listView = (ExpendListView) convertView.findViewById(R.id.listView);
			mChildViewHolder.text_excerpt = (TextView) convertView.findViewById(R.id.text_excerpt);
			mChildViewHolder.imageview = (ImageView) convertView.findViewById(R.id.imageview);
			mChildViewHolder.text_title = (TextView) convertView.findViewById(R.id.text_title);
			mChildViewHolder.text_info = (TextView) convertView.findViewById(R.id.text_info);
			mChildViewHolder.text_more = (TextView) convertView.findViewById(R.id.text_more);
			convertView.setTag(mChildViewHolder);
		} else {
			mChildViewHolder = (ChildViewHolder) convertView.getTag();
		}
		mChildViewHolder.alist = getGroup(groupPosition).getArticlelist();
		mChildViewHolder.mMAritleAdapter = new MAritleAdapter(mContext, mChildViewHolder.alist);
		mChildViewHolder.listView.setAdapter(mChildViewHolder.mMAritleAdapter);
		mChildViewHolder.mMAritleAdapter.notifyDataSetChanged();

		final MThumbBean bean = getGroup(groupPosition).getThumb();
		if (bean != null) {
			mChildViewHolder.text_title.setText(bean.getTitle());
			mChildViewHolder.text_excerpt.setText(bean.getExcerpt());
			mChildViewHolder.text_info.setText(bean.getInfo());
			if (bean.getDataoriginal() != null && bean.getDataoriginal().length() > 0) {
				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.common_v4).showImageForEmptyUri(R.drawable.common_v4).showImageOnFail(R.drawable.common_v4)
						.cacheInMemory().cacheOnDisc().build();
				ImageLoader.getInstance().displayImage(bean.getDataoriginal(), mChildViewHolder.imageview, options, getImageLoadingListener());
			}
			mChildViewHolder.text_more.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					EnrzWebViewActivity.startEnrzWebViewActivity(mContext, bean.getHref());
					MArticlePFragmentActivity.startMArticlePFragmentActivity(mContext, bean.getHref());
				}
			});

			mChildViewHolder.text_info.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					EnrzWebViewActivity.startEnrzWebViewActivity(mContext, bean.getCategoryhref());
					MThumbCatePageListFragmentActivity.startMThumbCatePageListFragmentActivity(mContext, bean.getCategoryhref());
					
				}
			});
		}
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				EnrzWebViewActivity.startEnrzWebViewActivity(mContext, getGroup(groupPosition).getHref());
				MArticlePFragmentActivity.startMArticlePFragmentActivity(mContext, getGroup(groupPosition).getHref());
			}
		});

		return convertView;
	}

	public class ChildViewHolder {
		ExpendListView listView;
		MAritleAdapter mMAritleAdapter;
		List<MArticleBean> alist = new ArrayList<MArticleBean>();
		TextView text_title;
		TextView text_excerpt;
		TextView text_info;
		TextView text_more;
		ImageView imageview;
	}

}
