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
import android.widget.TextView;

import com.open.enrz.R;
import com.open.enrz.activity.EnrzWebViewActivity;
import com.open.enrz.activity.m.MArticlePFragmentActivity;
import com.open.enrz.adapter.CommonAdapter;
import com.open.enrz.bean.m.MArticleBean;

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
public class MAritleAdapter extends CommonAdapter<MArticleBean> {

	public MAritleAdapter(Context mContext, List<MArticleBean> list) {
		super(mContext, list);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_m_artile, parent, false);
			viewHolder.text_title = (TextView) convertView.findViewById(R.id.text_title);
			viewHolder.text_catetime = (TextView) convertView.findViewById(R.id.text_catetime);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final MArticleBean bean = (MArticleBean) getItem(position);
		if (bean != null) {
			viewHolder.text_title.setText(bean.getTitle());
			viewHolder.text_catetime.setText(bean.getCatetime());
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					EnrzWebViewActivity.startEnrzWebViewActivity(mContext, bean.getHref());
					MArticlePFragmentActivity.startMArticlePFragmentActivity(mContext, bean.getHref());
				}
			});
		}
		return convertView;
	}

	class ViewHolder {
		TextView text_title;
		TextView text_catetime;
	}

}
