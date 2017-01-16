/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午5:27:28
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.adapter;

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
import com.open.enrz.bean.PicBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午5:27:28
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class PicAdapter extends CommonAdapter<PicBean> {

	public PicAdapter(Context mContext, List<PicBean> list) {
		super(mContext, list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.adapter.CommonAdapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mViewHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_pic, null);
			mViewHolder = new ViewHolder();
			mViewHolder.txt_span = (TextView) convertView.findViewById(R.id.txt_span);
			mViewHolder.imageview = (ImageView) convertView.findViewById(R.id.imageview);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		final PicBean bean = (PicBean) getItem(position);
		if (bean != null) {
			if (bean.getSrc() != null && bean.getSrc().length() > 0) {
				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.common_v4).showImageForEmptyUri(R.drawable.common_v4).showImageOnFail(R.drawable.common_v4)
						.cacheInMemory().cacheOnDisc().build();
				ImageLoader.getInstance().displayImage(bean.getSrc(), mViewHolder.imageview, options, null);
			}
			mViewHolder.txt_span.setText(bean.getSpan());
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					EnrzWebViewActivity.startEnrzWebViewActivity(mContext, bean.getHref());
				}
			});
		}
		return convertView;
	}

	private class ViewHolder {
		ImageView imageview;
		TextView txt_span;
	}
}
