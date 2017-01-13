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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.open.enrz.R;
import com.open.enrz.activity.EnrzWebViewActivity;
import com.open.enrz.bean.SlideBean;

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
public class SlidePagerAdapter extends CommonPagerAdapter<SlideBean> {

	public SlidePagerAdapter(Context mContext, List<SlideBean> list) {
		super(mContext, list);
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		final SlideBean bean = (SlideBean) getItem(position);
		final ViewHolder mViewHolder = new ViewHolder();
		View convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_slide_viewpager, null);
		mViewHolder.imageview = (ImageView) convertView.findViewById(R.id.imageview);
		mViewHolder.txt_st_ty = (TextView) convertView.findViewById(R.id.txt_st_ty);
		if (bean != null) {
			if (bean.getSrc() != null && bean.getSrc().length() > 0) {
				DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.common_v4).showImageForEmptyUri(R.drawable.common_v4).showImageOnFail(R.drawable.common_v4)
						.cacheInMemory().cacheOnDisc().build();
				ImageLoader.getInstance().displayImage(bean.getSrc(), mViewHolder.imageview, options, null);
			}
			mViewHolder.txt_st_ty.setText(bean.getSt_ty());
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					EnrzWebViewActivity.startEnrzWebViewActivity(mContext, bean.getHref());
					
				}
			});
		}
		container.addView(convertView);
		return convertView;
	}
	private class ViewHolder {
		ImageView imageview;
		TextView txt_st_ty;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}

	@Override
	public void startUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		super.startUpdate(container);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}

	

}
