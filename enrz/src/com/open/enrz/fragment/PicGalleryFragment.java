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

import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.open.enrz.R;
import com.open.enrz.adapter.PicGalleryAdapter;
import com.open.enrz.bean.SlideBean;
import com.open.enrz.json.SlideJson;
import com.open.enrz.jsoup.ImageViewPagerService;
import com.open.enrz.utils.PicBitmapTask;

import android.widget.Gallery.LayoutParams;
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
public class PicGalleryFragment extends BaseV4Fragment<SlideJson, PicGalleryFragment> implements OnItemClickListener, ViewFactory {
	private PicGalleryAdapter mPicGalleryAdapter;
	private List<SlideBean> list = new ArrayList<SlideBean>();
	public Gallery mGallery;
	public ImageSwitcher imageswitcher;

	public static PicGalleryFragment newInstance(String url, boolean isVisibleToUser) {
		PicGalleryFragment fragment = new PicGalleryFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pic_gallery, container, false);
		mGallery = (Gallery) view.findViewById(R.id.gallery);
		imageswitcher = (ImageSwitcher) view.findViewById(R.id.imageswitcher);
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
		mPicGalleryAdapter = new PicGalleryAdapter(getActivity(), list);
		mGallery.setAdapter(mPicGalleryAdapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
		imageswitcher.setFactory(this);
		mGallery.setOnItemClickListener(this);
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
		mPicGalleryAdapter.notifyDataSetChanged();

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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		 new PicBitmapTask(getActivity(), imageswitcher).execute(list.get(arg2).getSrc());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ViewSwitcher.ViewFactory#makeView()
	 */
	@Override
	public View makeView() {
		ImageView imageview = new ImageView(getActivity());
		imageview.setScaleType(ImageView.ScaleType.FIT_XY);
		imageview.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT));
		return imageview;
	}
}
