/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午5:31:09
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
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.HeaderGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshHeadGridView;
import com.open.enrz.R;
import com.open.enrz.activity.EnrzImageViewPagerFragmentActivity;
import com.open.enrz.adapter.PicAdapter;
import com.open.enrz.adapter.SlidePagerAdapter;
import com.open.enrz.bean.PicBean;
import com.open.enrz.bean.SlideBean;
import com.open.enrz.json.PicJson;
import com.open.enrz.jsoup.PicService;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午5:31:09
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class PicPullGridFragment extends BaseV4Fragment<PicJson, PicPullGridFragment> implements OnRefreshListener<HeaderGridView>, OnPageChangeListener {
	public PullToRefreshHeadGridView mPullToRefreshHeadGridView;
	public List<PicBean> list = new ArrayList<PicBean>();
	public PicAdapter mPicAdapter;

	// headview
	public View headview;
	public ViewPager viewpager;
	public List<SlideBean> listhead = new ArrayList<SlideBean>();
	public SlidePagerAdapter mSlidePagerAdapter;
	public ImageView[] dots;
	public int currentIndex;
	public int size;
	public LinearLayout layout_dot;

	public static PicPullGridFragment newInstance(String url, boolean isVisibleToUser) {
		PicPullGridFragment fragment = new PicPullGridFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_common_pullgridiview, container, false);
		mPullToRefreshHeadGridView = (PullToRefreshHeadGridView) view.findViewById(R.id.pull_refresh_grid);

		headview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_pic_headview_viewpager, null);
		viewpager = (ViewPager) headview.findViewById(R.id.viewpager);
		layout_dot = (LinearLayout) headview.findViewById(R.id.layout_dot);
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
		HeaderGridView gridview = mPullToRefreshHeadGridView.getRefreshableView();
		gridview.addHeaderView(headview);
		
		mPicAdapter = new PicAdapter(getActivity(), list);
		mPullToRefreshHeadGridView.setAdapter(mPicAdapter);
		mPullToRefreshHeadGridView.setMode(Mode.BOTH);

		mSlidePagerAdapter = new SlidePagerAdapter(getActivity(), listhead);
		viewpager.setAdapter(mSlidePagerAdapter);
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
		mPullToRefreshHeadGridView.setOnRefreshListener(this);
		viewpager.setOnPageChangeListener(this);
//		mPullToRefreshHeadGridView.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				if((int)id!=-1 && list!=null && list.size()>0 && list.get((int)id)!=null){
//					EnrzImageViewPagerFragmentActivity.startEnrzImageViewPagerFragmentActivity(getActivity(), list.get((int)id).getHref());
//				}
//			}
//		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public PicJson call() throws Exception {
		// TODO Auto-generated method stub
		PicJson mPicJson = new PicJson();
		mPicJson.setList(PicService.parsePic(url, pageNo));
		mPicJson.setListhead(PicService.parseHead(url));
		return mPicJson;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(PicJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		Log.i(TAG, "getMode ===" + mPullToRefreshHeadGridView.getCurrentMode());
		if (mPullToRefreshHeadGridView.getCurrentMode() == Mode.PULL_FROM_START) {
			list.clear();
			list.addAll(result.getList());
			pageNo = 1;
			
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
		} else {
			if (result.getList() != null && result.getList().size() > 0) {
				list.addAll(result.getList());
			}
		}
		mPicAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
		mPullToRefreshHeadGridView.onRefreshComplete();

		
		
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener
	 * #onRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
	 */
	@Override
	public void onRefresh(PullToRefreshBase<HeaderGridView> refreshView) {
		String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
		// Update the LastUpdatedLabel
		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
		// Do work to refresh the list here.
		if (mPullToRefreshHeadGridView.getCurrentMode() == Mode.PULL_FROM_START) {
			pageNo = 1;
			weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
		} else if (mPullToRefreshHeadGridView.getCurrentMode() == Mode.PULL_FROM_END) {
			pageNo++;
			weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
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
