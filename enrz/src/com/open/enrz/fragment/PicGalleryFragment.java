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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

import com.open.enrz.R;
import com.open.enrz.activity.EnrzImageViewPagerFragmentActivity;
import com.open.enrz.activity.PicGalleryFragmentActivity;
import com.open.enrz.adapter.PicGalleryAdapter;
import com.open.enrz.bean.SlideBean;
import com.open.enrz.json.SlideJson;
import com.open.enrz.jsoup.ImageViewPagerService;
import com.open.enrz.utils.PicBitmapTask;
import com.open.enrz.utils.TextViewBitmapTask;

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
public class PicGalleryFragment extends BaseV4Fragment<SlideJson, PicGalleryFragment> implements OnItemClickListener, ViewFactory, OnClickListener {
	private PicGalleryAdapter mPicGalleryAdapter;
	private List<SlideBean> list = new ArrayList<SlideBean>();
	public Gallery mGallery;
	public ImageSwitcher imageswitcher;
	private TextView txt_st_ty;
	private TextView txt_view_intro;
	private TextView btn_pre, btn_next;
	private ImageView img_pre, img_next;
	private int currentposition = 0;

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
		txt_st_ty = (TextView) view.findViewById(R.id.txt_st_ty);
		txt_view_intro = (TextView) view.findViewById(R.id.txt_view_intro);
		btn_pre = (TextView) view.findViewById(R.id.btn_pre);
		btn_next = (TextView) view.findViewById(R.id.btn_next);
		img_pre = (ImageView) view.findViewById(R.id.img_pre);
		img_next = (ImageView) view.findViewById(R.id.img_next);
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
		btn_pre.setOnClickListener(this);
		btn_next.setOnClickListener(this);
		img_pre.setOnClickListener(this);
		img_next.setOnClickListener(this);
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
		try {
			if (result.getList().size() > 0) {
				txt_st_ty.setText(result.getList().get(0).getSt_ty());
				txt_view_intro.setText(result.getList().get(0).getView_intro());
				new TextViewBitmapTask(getActivity(), btn_pre).execute(result.getList().get(0).getPresrc());
				new TextViewBitmapTask(getActivity(), btn_next).execute(result.getList().get(0).getNextsrc());

				btn_pre.setTag(result.getList().get(0).getPrehref());
				btn_next.setTag(result.getList().get(0).getNexthref());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		currentposition = 0;
		new PicBitmapTask(getActivity(), imageswitcher).execute(list.get(currentposition).getSrc());
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
		currentposition = arg2;
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
		imageview.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT));
		imageview.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EnrzImageViewPagerFragmentActivity.startEnrzImageViewPagerFragmentActivity(getActivity(), url,currentposition);
			}
		});
		return imageview;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_pre:
			PicGalleryFragmentActivity.startPicGalleryFragmentActivity(getActivity(), btn_pre.getTag().toString());
			break;
		case R.id.btn_next:
			PicGalleryFragmentActivity.startPicGalleryFragmentActivity(getActivity(), btn_next.getTag().toString());
			break;
		case R.id.img_next:
			if(currentposition>=list.size()-1){
				currentposition=list.size()-1;
			}else{
				currentposition++;
			}
			onItemClick(mGallery, null, currentposition, currentposition);
			break;
		case R.id.img_pre:
			if(currentposition==0){
				currentposition=0;
			}else{
				currentposition--;
			}
			onItemClick(mGallery, null, currentposition, currentposition);
		    break;
		default:
			break;
		}

	}
}
