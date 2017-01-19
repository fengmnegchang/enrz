/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18下午3:38:27
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.fragment.m;

import java.util.ArrayList;

import net.nightwhistler.htmlspanner.HtmlSpanner;
import net.nightwhistler.htmlspanner.LinkMovementMethodExt;
import net.nightwhistler.htmlspanner.MyImageSpan;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.format.DateUtils;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.open.enrz.R;
import com.open.enrz.activity.HtmlImgPreviewActivity;
import com.open.enrz.bean.m.MArticlePBean;
import com.open.enrz.fragment.BaseV4Fragment;
import com.open.enrz.json.m.MArticlePJson;
import com.open.enrz.jsoup.m.MArticlePScrollService;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18下午3:38:27
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MArticlePPullScrollFragment extends BaseV4Fragment<MArticlePJson, MArticlePPullScrollFragment> implements OnRefreshListener<ScrollView> {
	public PullToRefreshScrollView mPullToRefreshScrollView;
	public TextView  txt_centerp;
	public HtmlSpanner htmlSpanner;
	public ArrayList<String> imglist;
	
	public TextView txt_crumbstitle;
	public TextView txt_title;
	public TextView txt_category;
	public TextView txt_preleft;
	public TextView txt_nextright;
	
	public static MArticlePPullScrollFragment newInstance(String url, boolean isVisibleToUser) {
		MArticlePPullScrollFragment fragment = new MArticlePPullScrollFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}
	

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_m_article_p_scrollview, container, false);
		mPullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.pull_scroll_list);
		txt_centerp = (TextView) view.findViewById(R.id.txt_centerp);
		txt_crumbstitle = (TextView) view.findViewById(R.id.txt_crumbstitle);
		txt_title = (TextView) view.findViewById(R.id.txt_title);
		txt_category = (TextView) view.findViewById(R.id.txt_category);
		txt_preleft = (TextView) view.findViewById(R.id.txt_preleft);
		txt_nextright = (TextView) view.findViewById(R.id.txt_nextright);
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
		mPullToRefreshScrollView.setMode(Mode.PULL_FROM_START);
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		imglist = new ArrayList<String>();
		htmlSpanner = new HtmlSpanner(getActivity(), dm.widthPixels, handler);
		
		Fragment fragment = MDuoShuoIndicatorExpendFragment.newInstance(url, true);
		getChildFragmentManager().beginTransaction().replace(R.id.layout_m_comment, fragment).commit();
	}

	final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:// 获取图片路径列表
				String url = (String) msg.obj;
				Log.e("jj", "url>>" + url);
				if(!url.equals("http://m.enrz.com/wp-content/themes/salong/images/post_loading.gif")){
					imglist.add(url);
				}
				break;
			case 2:// 图片点击事件
				int position = 0;
				MyImageSpan span = (MyImageSpan) msg.obj;
				for (int i = 0; i < imglist.size(); i++) {
					if (span.getUrl().equals(imglist.get(i))) {
						position = i;
						break;
					}
				}
				Log.e("jj", "position>>" + position);
				Intent intent = new Intent(getActivity(), HtmlImgPreviewActivity.class);
				Bundle b = new Bundle();
				b.putInt("position", position);
				b.putStringArrayList("imglist", imglist);
				intent.putExtra("b", b);
				startActivity(intent);
				break;
			}
		}

	};
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.fragment.BaseV4Fragment#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
		mPullToRefreshScrollView.setOnRefreshListener(this);
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#call()
	 */
	@Override
	public MArticlePJson call() throws Exception {
		// TODO Auto-generated method stub
		MArticlePJson mMArticlePJson = new MArticlePJson();
		mMArticlePJson.setMarticlep(MArticlePScrollService.parseMArticle(url));
		return mMArticlePJson;
	}

	/* (non-Javadoc)
	 * @see com.open.enrz.fragment.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(MArticlePJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		final MArticlePBean  marticlep = result.getMarticlep();
		if(marticlep!=null){
			new Thread(new Runnable() {
				@Override
				public void run() {
					final Spannable spannable = htmlSpanner.fromHtml(marticlep.getArticlep());
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							txt_centerp.setText(spannable);
							txt_centerp.setMovementMethod(LinkMovementMethodExt.getInstance(handler, ImageSpan.class));
						}
					});
				}
			}).start();
			 
		 
//			txt_centerp.setText(Html.fromHtml(contbox.getCenterp(), new URLImageParser(txt_centerp), null)); 
			
			try {
				txt_crumbstitle.setText(marticlep.getCrumbstitle());
				txt_title.setText(marticlep.getTitle());
				txt_category.setText(marticlep.getCategory());
				txt_preleft.setText(marticlep.getPreleft());
				txt_preleft.setTag(marticlep.getPrelefthref());
				txt_nextright.setText(marticlep.getNextright());
				txt_nextright.setTag(marticlep.getNextrighthref());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mPullToRefreshScrollView.onRefreshComplete();
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
	public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
		String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
		// Update the LastUpdatedLabel
		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
		// Do work to refresh the list here.
		if (mPullToRefreshScrollView.getCurrentMode() == Mode.PULL_FROM_START) {
			weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
		}
	}

}
