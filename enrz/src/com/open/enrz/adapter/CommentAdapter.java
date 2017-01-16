/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午2:56:01
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.open.enrz.R;
import com.open.enrz.activity.EnrzWebViewActivity;
import com.open.enrz.bean.CommentBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午2:56:01
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class CommentAdapter extends CommonAdapter<CommentBean> {

	public CommentAdapter(Context mContext, List<CommentBean> list) {
		super(mContext, list);
	}
	/* (non-Javadoc)
	 * @see com.open.enrz.adapter.CommonAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder mViewHolder;
		if(convertView==null ){
			convertView = mInflater.inflate(R.layout.adapter_comment, null);
			mViewHolder = new ViewHolder();
			mViewHolder.text_author = (TextView) convertView.findViewById(R.id.text_author);
			mViewHolder.imageview = (ImageView) convertView.findViewById(R.id.imageview);
			mViewHolder.text_content = (TextView) convertView.findViewById(R.id.text_content);
			mViewHolder.text_datetime = (TextView) convertView.findViewById(R.id.text_datetime);
			mViewHolder.text_replay = (TextView) convertView.findViewById(R.id.text_replay);
			mViewHolder.text_up = (TextView) convertView.findViewById(R.id.text_up);
			mViewHolder.text_repeat = (TextView) convertView.findViewById(R.id.text_repeat);
			mViewHolder.text_report = (TextView) convertView.findViewById(R.id.text_report);
			
			mViewHolder.layout_children = (RelativeLayout) convertView.findViewById(R.id.layout_children);
			mViewHolder.text_authorc = (TextView) convertView.findViewById(R.id.text_authorc);
			mViewHolder.imageviewc = (ImageView) convertView.findViewById(R.id.imageviewc);
			mViewHolder.text_contentc = (TextView) convertView.findViewById(R.id.text_contentc);
			mViewHolder.text_replayc = (TextView) convertView.findViewById(R.id.text_replayc);
			mViewHolder.text_upc = (TextView) convertView.findViewById(R.id.text_upc);
			mViewHolder.text_repeatc = (TextView) convertView.findViewById(R.id.text_repeatc);
			convertView.setTag(mViewHolder);
		}else{
			mViewHolder =  (ViewHolder) convertView.getTag();
		}
		CommentBean bean = (CommentBean) getItem(position);
		if(bean!=null){
			if(bean.getChildren()!=null){
				mViewHolder.layout_children.setVisibility(View.VISIBLE);
				mViewHolder.text_authorc.setText(bean.getAuthor()+bean.getDatetime());
				mViewHolder.text_authorc.setTag(bean.getAuthorhref());
				mViewHolder.text_contentc.setText(bean.getContent());
				
				mViewHolder.text_author.setText(bean.getChildren().getAuthor());
				mViewHolder.text_author.setTag(bean.getChildren().getAuthorhref());
				
				mViewHolder.text_content.setText(bean.getChildren().getContent());
				mViewHolder.text_datetime.setText(bean.getChildren().getDatetime());
				
			}else{
				mViewHolder.layout_children.setVisibility(View.GONE);
				mViewHolder.text_author.setText(bean.getAuthor());
				mViewHolder.text_author.setTag(bean.getAuthorhref());
				
				mViewHolder.text_content.setText(bean.getContent());
				mViewHolder.text_datetime.setText(bean.getDatetime());
			}
		}
		mViewHolder.text_author.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mViewHolder.text_author.getTag()!=null && mViewHolder.text_author.getTag().toString().length()>0){
					EnrzWebViewActivity.startEnrzWebViewActivity(mContext, mViewHolder.text_author.getTag().toString());
				}
			}
		});
		mViewHolder.text_authorc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mViewHolder.text_authorc.getTag()!=null &&mViewHolder.text_authorc.getTag().toString().length()>0){
					EnrzWebViewActivity.startEnrzWebViewActivity(mContext, mViewHolder.text_authorc.getTag().toString());
				}
			}
		});
		return convertView; 
	}
	
	private class ViewHolder{
		ImageView imageview;
		TextView text_author;
		TextView text_content;
		TextView text_datetime;
		TextView text_replay;
		TextView text_up;
		TextView text_repeat;
		TextView text_report;
		
		RelativeLayout layout_children;
		ImageView imageviewc;
		TextView text_authorc;
		TextView text_contentc;
		TextView text_replayc;
		TextView text_upc;
		TextView text_repeatc;
	}
}
