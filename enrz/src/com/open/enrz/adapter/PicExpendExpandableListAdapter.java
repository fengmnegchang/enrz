/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-17上午10:36:22
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.enrz.R;
import com.open.enrz.bean.GnSubBean;
import com.open.enrz.bean.PicBean;
import com.open.enrz.view.ExpendGridView;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-17上午10:36:22
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class PicExpendExpandableListAdapter extends CommonExpandableListAdapter<GnSubBean, PicBean> {

	public PicExpendExpandableListAdapter(Context mContext, List<GnSubBean> list) {
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
			convertView = mInflater.inflate(R.layout.adapter_pic_expend_group, null);
			mGroupViewHolder = new GroupViewHolder();
			mGroupViewHolder.text_moduleTitle = (TextView) convertView.findViewById(R.id.text_moduleTitle);
			convertView.setTag(mGroupViewHolder);
		} else {
			mGroupViewHolder = (GroupViewHolder) convertView.getTag();
		}
		GnSubBean bean = (GnSubBean) getGroup(groupPosition);
		if (bean != null) {
			mGroupViewHolder.text_moduleTitle.setText(bean.getTarget());
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
		if (getGroup(groupPosition) != null && ((getGroup(groupPosition).getClist() != null && getGroup(groupPosition).getClist().size() > 0))) {
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
	public View getChildView(int groupPosition, int childPosition, boolean arg2, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ChildViewHolder mChildViewHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_pic_expend_child, null);
			mChildViewHolder = new ChildViewHolder();
			mChildViewHolder.gridView = (ExpendGridView) convertView.findViewById(R.id.gridView);
			convertView.setTag(mChildViewHolder);
		} else {
			mChildViewHolder = (ChildViewHolder) convertView.getTag();
		}
		mChildViewHolder.clist = getGroup(groupPosition).getClist();
		mChildViewHolder.mPicAdapter = new PicAdapter(mContext, mChildViewHolder.clist);
		mChildViewHolder.gridView.setAdapter(mChildViewHolder.mPicAdapter);
		mChildViewHolder.mPicAdapter.notifyDataSetChanged();

		return convertView;
	}

	public class ChildViewHolder {
		ExpendGridView gridView;
		PicAdapter mPicAdapter;
		List<PicBean> clist = new ArrayList<PicBean>();
	}

}
