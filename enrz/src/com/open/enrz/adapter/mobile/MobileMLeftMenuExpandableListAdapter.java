/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-19上午11:27:06
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.adapter.mobile;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.enrz.R;
import com.open.enrz.adapter.CommonExpandableListAdapter;
import com.open.enrz.bean.GlobalNavBean;
import com.open.enrz.bean.GnSubBean;
import com.open.enrz.view.ExpendListView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-19上午11:27:06
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MobileMLeftMenuExpandableListAdapter extends CommonExpandableListAdapter<GlobalNavBean, GnSubBean> {

	public MobileMLeftMenuExpandableListAdapter(Context mContext, List<GlobalNavBean> list) {
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
			convertView = mInflater.inflate(R.layout.adapter_mobile_left_expend_group, null);
			mGroupViewHolder = new GroupViewHolder();
			mGroupViewHolder.text_moduleTitle = (TextView) convertView.findViewById(R.id.text_moduleTitle);
			convertView.setTag(mGroupViewHolder);
		} else {
			mGroupViewHolder = (GroupViewHolder) convertView.getTag();
		}
		GlobalNavBean bean = (GlobalNavBean) getGroup(groupPosition);
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
		if (getGroup(groupPosition) != null && ((getGroup(groupPosition).getGnsub() != null && getGroup(groupPosition).getGnsub().size() > 0))) {
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
			convertView = mInflater.inflate(R.layout.adapter_mobile_left_expend_child, null);
			mChildViewHolder = new ChildViewHolder();
			mChildViewHolder.listview = (ExpendListView) convertView.findViewById(R.id.listview);
			convertView.setTag(mChildViewHolder);
		} else {
			mChildViewHolder = (ChildViewHolder) convertView.getTag();
		}
		mChildViewHolder.glist = getGroup(groupPosition).getGnsub();
		mChildViewHolder.mMobileGnSubListAdapter = new MobileGnSubListAdapter(mContext, mChildViewHolder.glist);
		mChildViewHolder.listview.setAdapter(mChildViewHolder.mMobileGnSubListAdapter);
		mChildViewHolder.mMobileGnSubListAdapter.notifyDataSetChanged();

		return convertView;
	}

	public class ChildViewHolder {
		ExpendListView listview;
		MobileGnSubListAdapter mMobileGnSubListAdapter;
		List<GnSubBean> glist = new ArrayList<GnSubBean>();
	}

}
