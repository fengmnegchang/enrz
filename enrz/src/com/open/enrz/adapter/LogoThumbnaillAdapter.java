/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-12下午5:02:00
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
import com.open.enrz.bean.ThumbnailBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-12下午5:02:00
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class LogoThumbnaillAdapter extends CommonAdapter<ThumbnailBean>{

	public LogoThumbnaillAdapter(Context mContext, List<ThumbnailBean> list) {
		super(mContext, list);
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_logo_thumbnaill, parent, false);
			viewHolder.text_stamptxt = (TextView) convertView.findViewById(R.id.text_stamptxt);
			viewHolder.imageview = (ImageView) convertView.findViewById(R.id.imageview);
			viewHolder.text_title = (TextView) convertView.findViewById(R.id.text_title);
			viewHolder.text_desc = (TextView) convertView.findViewById(R.id.text_desc);
			viewHolder.text_more = (TextView) convertView.findViewById(R.id.text_more);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final ThumbnailBean bean = (ThumbnailBean) getItem(position);
		if (bean != null) {
			viewHolder.text_stamptxt.setText(bean.getStamptxt());
			viewHolder.text_title.setText(bean.getTitle());
			viewHolder.text_desc.setText(bean.getDesc());

			if (bean.getPicsrc() != null && bean.getPicsrc().length() > 0) {
				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.common_v4).showImageForEmptyUri(R.drawable.common_v4).showImageOnFail(R.drawable.common_v4)
						.cacheInMemory().cacheOnDisc().build();
				ImageLoader.getInstance().displayImage(bean.getPicsrc(), viewHolder.imageview, options, getImageLoadingListener());
			}
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					EnrzWebViewActivity.startEnrzWebViewActivity(mContext, bean.getHref());
					
				}
			});
		}
		return convertView;
	}

	class ViewHolder {
		TextView text_stamptxt;
		TextView text_title;
		TextView text_desc;
		TextView text_more;
		ImageView imageview;
	}
}
