/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13上午9:28:46
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.adapter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.open.enrz.R;
import com.open.enrz.activity.EnrzWebViewActivity;
import com.open.enrz.bean.SlideBean;
import com.open.enrz.utils.ImageAsyncTask;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13上午9:28:46
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class PicGalleryAdapter extends CommonAdapter<SlideBean> {

	public PicGalleryAdapter(Context mContext, List<SlideBean> list) {
		super(mContext, list);
	}
	
	
	/* (non-Javadoc)
	 * @see com.open.enrz.adapter.CommonAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final SlideBean bean = (SlideBean) getItem(position);
		ViewHolder mViewHolder = null;
		if(convertView==null){
			mViewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_pic_gallery, null);
			mViewHolder.imageview = (ImageView) convertView.findViewById(R.id.imageview);
			convertView.setTag(mViewHolder);
		}else{
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		
		if (bean != null) {
			if (bean.getSrc() != null && bean.getSrc().length() > 0) {
				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.common_v4).showImageForEmptyUri(R.drawable.common_v4).showImageOnFail(R.drawable.common_v4)
						.cacheInMemory().cacheOnDisc().build();
				ImageLoader.getInstance().displayImage(bean.getSrc(), mViewHolder.imageview, options, null);
			}
//			convertView.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					EnrzWebViewActivity.startEnrzWebViewActivity(mContext, bean.getHref());
//				}
//			});
//			
//			final ImageView imageview = mViewHolder.imageview;
//			mViewHolder.imageview.setOnLongClickListener(new OnLongClickListener() {
//				@Override
//				public boolean onLongClick(View v) {
//					 AlertDialog.Builder builder = new AlertDialog.Builder(mContext);  
//		               builder.setItems(new String[]{mContext.getResources().getString(R.string.save_picture)}, new DialogInterface.OnClickListener() {  
//		                   @Override  
//		                   public void onClick(DialogInterface dialog, int which) {  
//		                	   imageview.setDrawingCacheEnabled(true);  
//		                       Bitmap imageBitmap = imageview.getDrawingCache();  
//		                       if (imageBitmap != null) {  
//		                           new ImageAsyncTask(mContext,  imageview,bean.getSrc()).execute(imageBitmap);  
//		                       }  
//		                   }  
//		               });  
//		               builder.show(); 
//					return false;
//				}
//			});
		}
		return convertView;
	}

	 
	private class ViewHolder {
		ImageView imageview;
	}
}
