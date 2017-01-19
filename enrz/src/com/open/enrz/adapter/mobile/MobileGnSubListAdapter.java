/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-19上午11:22:18
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.adapter.mobile;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.enrz.R;
import com.open.enrz.adapter.CommonAdapter;
import com.open.enrz.bean.GnSubBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-19上午11:22:18
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MobileGnSubListAdapter extends CommonAdapter<GnSubBean> {

	public MobileGnSubListAdapter(Context mContext, List<GnSubBean> list) {
		super(mContext, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GnSubBean bean = (GnSubBean) getItem(position);
		View view;
		view = LayoutInflater.from(mContext).inflate(R.layout.adapter_mobile_gn_sub, null);
		TextView text_name = (TextView) view.findViewById(R.id.text_name);
		text_name.setText(bean.getTarget());
		return view;
	}
}
