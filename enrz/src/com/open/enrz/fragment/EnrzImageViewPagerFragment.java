/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16上午10:40:27
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.enrz.R;
import com.open.enrz.adapter.ImagePagerAdapter;
import com.open.enrz.bean.SlideBean;
import com.open.enrz.json.SlideJson;
import com.open.enrz.jsoup.ImageViewPagerService;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16上午10:40:27
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class EnrzImageViewPagerFragment extends BaseV4Fragment<SlideJson, EnrzImageViewPagerFragment> {
	private ViewPager viewpager;
	private ImagePagerAdapter mImagePagerAdapter;
	private List<SlideBean> list = new ArrayList<SlideBean>();
	private TextView txt_st_ty;
	private TextView txt_view_intro;
	private int position;
	
	public static EnrzImageViewPagerFragment newInstance(String url, boolean isVisibleToUser,int position) {
		EnrzImageViewPagerFragment fragment = new EnrzImageViewPagerFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		fragment.position = position;
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_enrz_image_viewpager, container, false);
		viewpager = (ViewPager) view.findViewById(R.id.viewpager);
		txt_st_ty = (TextView) view.findViewById(R.id.txt_st_ty);
		txt_view_intro = (TextView) view.findViewById(R.id.txt_view_intro);
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		super.initValues();
		mImagePagerAdapter = new ImagePagerAdapter(getActivity(), list);
		viewpager.setAdapter(mImagePagerAdapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.activity.BaseFragmentActivity#call()
	 */
	@Override
	public SlideJson call() throws Exception {
		// TODO Auto-generated method stub
		SlideJson mSlideJson = new SlideJson();
		mSlideJson.setList(ImageViewPagerService.parseViewpager(url));
		return mSlideJson;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.enrz.activity.BaseFragmentActivity#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(SlideJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		list.clear();
		list.addAll(result.getList());
		mImagePagerAdapter.notifyDataSetChanged();
		try {
			if (result.getList().size() > 0) {
				txt_st_ty.setText(result.getList().get(0).getSt_ty());
				txt_view_intro.setText(result.getList().get(0).getView_intro());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		viewpager.setCurrentItem(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.enrz.fragment.BaseV4Fragment#handlerMessage(android.os.Message)
	 */
	@Override
	public void handlerMessage(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what) {
		case MESSAGE_HANDLER:
			doAsync(this, this, this);
			break;
		default:
			break;
		}
	}
}
