/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16上午9:48:39
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.open.enrz.R;
import com.open.enrz.adapter.ImagePagerAdapter;
import com.open.enrz.bean.SlideBean;
import com.open.enrz.json.SlideJson;
import com.open.enrz.jsoup.ImageViewPagerService;
import com.open.enrz.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16上午9:48:39
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class EnrzImageViewPagerActivity extends CommonFragmentActivity<SlideJson>{
	private ViewPager viewpager;
	private ImagePagerAdapter mImagePagerAdapter;
	private List<SlideBean> list = new ArrayList<SlideBean>();
	private TextView txt_st_ty;
	private TextView txt_view_intro;
	/* (non-Javadoc)
	 * @see com.open.enrz.activity.CommonFragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enrz_image_viewpager);
		init();
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.activity.CommonFragmentActivity#findView()
	 */
	@Override
	protected void findView() {
		// TODO Auto-generated method stub
		super.findView();
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		txt_st_ty = (TextView) findViewById(R.id.txt_st_ty);
		txt_view_intro = (TextView) findViewById(R.id.txt_view_intro);
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.activity.CommonFragmentActivity#initValue()
	 */
	@Override
	protected void initValue() {
		// TODO Auto-generated method stub
		super.initValue();
		mImagePagerAdapter = new ImagePagerAdapter(this, list);
		viewpager.setAdapter(mImagePagerAdapter);
		if (getIntent().getStringExtra("URL") != null) {
			url = getIntent().getStringExtra("URL");
		}else{
			url = UrlUtils.ENRZ_IMAGE_VIEW_PAGER;
		}
		doAsync(this, this, this);
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.activity.CommonFragmentActivity#bindEvent()
	 */
	@Override
	protected void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.activity.BaseFragmentActivity#call()
	 */
	@Override
	public SlideJson call() throws Exception {
		// TODO Auto-generated method stub
		SlideJson mSlideJson = new SlideJson();
		mSlideJson.setList(ImageViewPagerService.parseViewpager(url));
		return mSlideJson;
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.activity.BaseFragmentActivity#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(SlideJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		list.clear();
		list.addAll(result.getList());
		mImagePagerAdapter.notifyDataSetChanged();
		try {
			if(result.getList().size()>0){
				txt_st_ty.setText(result.getList().get(0).getSt_ty());
				txt_view_intro.setText(result.getList().get(0).getView_intro());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void startEnrzImageViewPagerActivity(Context context, String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, EnrzImageViewPagerActivity.class);
		context.startActivity(intent);
	}
}
