/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-17上午10:44:26
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
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.open.enrz.R;
import com.open.enrz.adapter.PicExpendExpandableListAdapter;
import com.open.enrz.adapter.SlidePagerAdapter;
import com.open.enrz.bean.GnSubBean;
import com.open.enrz.bean.SlideBean;
import com.open.enrz.json.GnSubJson;
import com.open.enrz.jsoup.PicExpendService;
import com.open.enrz.jsoup.PicService;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-17上午10:44:26
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class PicExpendExpandableListViewFragment extends BaseV4Fragment<GnSubJson, PicExpendExpandableListViewFragment> implements OnPageChangeListener {
	public ExpandableListView mExpandableListView;
	public PicExpendExpandableListAdapter mPicExpendExpandableListAdapter;
	public List<GnSubBean> list = new ArrayList<GnSubBean>();
	
	// headview
	public View headview;
	public ViewPager viewpager;
	public List<SlideBean> listhead = new ArrayList<SlideBean>();
	public SlidePagerAdapter mSlidePagerAdapter;
	public ImageView[] dots;
	public int currentIndex;
	public int size;
	public LinearLayout layout_dot;
		
	public static PicExpendExpandableListViewFragment newInstance(String url, boolean isVisibleToUser) {
		PicExpendExpandableListViewFragment fragment = new PicExpendExpandableListViewFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_common_expendview, container, false);
		mExpandableListView = (ExpandableListView) view.findViewById(R.id.expendablelistview);
		
		headview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_pic_headview_viewpager, null);
		viewpager = (ViewPager) headview.findViewById(R.id.viewpager);
		layout_dot = (LinearLayout) headview.findViewById(R.id.layout_dot);
		return view;
	}
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		super.initValues();
		mExpandableListView.addHeaderView(headview);
		
		mExpandableListView.setGroupIndicator(null);
		mPicExpendExpandableListAdapter = new PicExpendExpandableListAdapter(getActivity(),list);
		mExpandableListView.setAdapter(mPicExpendExpandableListAdapter);
		
		mSlidePagerAdapter = new SlidePagerAdapter(getActivity(), listhead);
		viewpager.setAdapter(mSlidePagerAdapter);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.umei.fragment.BaseV4Fragment#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
		viewpager.setOnPageChangeListener(this);
		mExpandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return true;
			}
		});
		mExpandableListView
				.setOnGroupExpandListener(new OnGroupExpandListener() {
					@Override
					public void onGroupExpand(int groupPosition) {

					}
				});
		mExpandableListView
				.setOnGroupCollapseListener(new OnGroupCollapseListener() {
					@Override
					public void onGroupCollapse(int groupPosition) {

					}
				});
	}
	
	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public GnSubJson call() throws Exception {
		// TODO Auto-generated method stub
		GnSubJson mGnSubJson = new GnSubJson();
		mGnSubJson.setList(PicExpendService.parseExpend(url));
		mGnSubJson.setListhead(PicService.parseExpendHead(url));
		return mGnSubJson;
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(GnSubJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		list.clear();
		list.addAll(result.getList());
		mPicExpendExpandableListAdapter.notifyDataSetChanged();

		for (int i = 0; i < mPicExpendExpandableListAdapter.getGroupCount(); i++) {
			mExpandableListView.expandGroup(i);
		}
		
		//head
		listhead.clear();
		listhead.addAll(result.getListhead());
		mSlidePagerAdapter.notifyDataSetChanged();
		layout_dot.removeAllViews();
		
		size = result.getListhead().size();
		dots = new ImageView[size];
		for (int i = 0; i < size; i++) {
			ImageView img = new ImageView(getActivity());
			img.setImageResource(R.drawable.dot);
			img.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			img.setPadding(15, 15, 15, 15);
			img.setClickable(true);
			dots[i] = img;
			dots[i].setEnabled(true);
			dots[i].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					int position = (Integer) v.getTag();
					setCurView(position);
					setCurDot(position);
				}
			});
			dots[i].setTag(i);

			layout_dot.addView(dots[i]);
		}

		currentIndex = 0;
		dots[currentIndex].setEnabled(false);
		viewpager.setCurrentItem(0);
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#handlerMessage(android.os.Message)
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
	

	/**
	 * 设置当前的引导页
	 */
	private void setCurView(int position) {
		if (position < 0 || position >= size) {
			return;
		}

		viewpager.setCurrentItem(position);
	}

	/**
	 * 这只当前引导小点的选中
	 */
	private void setCurDot(int positon) {
		if (positon < 0 || positon > size - 1 || currentIndex == positon) {
			return;
		}

		dots[positon].setEnabled(false);
		dots[currentIndex].setEnabled(true);

		currentIndex = positon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#
	 * onPageScrollStateChanged(int)
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled
	 * (int, float, int)
	 */
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected
	 * (int)
	 */
	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		setCurDot(arg0);
	}
}
