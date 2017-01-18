/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午10:21:20
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.adapter.m;

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
import com.open.enrz.adapter.CommonAdapter;
import com.open.enrz.bean.m.MThumbBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午10:21:20
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MThumbHeadAdapter extends CommonAdapter<MThumbBean> {

	public MThumbHeadAdapter(Context mContext, List<MThumbBean> list) {
		super(mContext, list);
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_m_thumb_head, parent, false);
			viewHolder.text_excerpt = (TextView) convertView.findViewById(R.id.text_excerpt);
			viewHolder.imageview = (ImageView) convertView.findViewById(R.id.imageview);
			viewHolder.text_title = (TextView) convertView.findViewById(R.id.text_title);
			viewHolder.text_info = (TextView) convertView.findViewById(R.id.text_info);
			viewHolder.text_more = (TextView) convertView.findViewById(R.id.text_more);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final MThumbBean bean = (MThumbBean) getItem(position);
		if (bean != null) {
			viewHolder.text_title.setText(bean.getTitle());
			viewHolder.text_excerpt.setText(bean.getExcerpt());
			viewHolder.text_info.setText(bean.getInfo());

			if (bean.getDataoriginal() != null && bean.getDataoriginal().length() > 0) {
				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.common_v4).showImageForEmptyUri(R.drawable.common_v4).showImageOnFail(R.drawable.common_v4)
						.cacheInMemory().cacheOnDisc().build();
				ImageLoader.getInstance().displayImage(bean.getDataoriginal(), viewHolder.imageview, options, getImageLoadingListener());
			}
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					EnrzWebViewActivity.startEnrzWebViewActivity(mContext, bean.getHref());
					
				}
			});
			viewHolder.text_more.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					EnrzWebViewActivity.startEnrzWebViewActivity(mContext, bean.getHref());
					
				}
			});
			
			viewHolder.text_info.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					EnrzWebViewActivity.startEnrzWebViewActivity(mContext, bean.getCategoryhref());
					
				}
			});
		}
		return convertView;
	}

	class ViewHolder {
		TextView text_title;
		TextView text_excerpt;
		TextView text_info;
		TextView text_more;
		ImageView imageview;
	}

}
