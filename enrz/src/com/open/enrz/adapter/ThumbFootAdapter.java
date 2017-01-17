/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午1:35:46
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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.open.enrz.R;
import com.open.enrz.activity.EnrzWebViewActivity;
import com.open.enrz.bean.ThumbnailBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午1:35:46
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ThumbFootAdapter extends CommonAdapter<ThumbnailBean> {

	public ThumbFootAdapter(Context mContext, List<ThumbnailBean> list) {
		super(mContext, list);
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.adapter.CommonAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mViewHolder;
		if(convertView==null ){
			convertView = mInflater.inflate(R.layout.adapter_search, null);
			mViewHolder = new ViewHolder();
			mViewHolder.text_name = (TextView) convertView.findViewById(R.id.text_name);
			convertView.setTag(mViewHolder);
		}else{
			mViewHolder =  (ViewHolder) convertView.getTag();
		}
		final ThumbnailBean bean = (ThumbnailBean) getItem(position);
		if(bean!=null){
			mViewHolder.text_name.setText(bean.getStamptxt());
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					EnrzWebViewActivity.startEnrzWebViewActivity(mContext, bean.getHref());
					
				}
			});
		}
		return convertView; 
	}
	
	private class ViewHolder{
		TextView text_name;
	}

}
