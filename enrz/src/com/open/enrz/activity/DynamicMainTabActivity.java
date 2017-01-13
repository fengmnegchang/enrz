/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-12下午4:22:20
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.open.enrz.R;
import com.open.enrz.bean.GlobalNavBean;
import com.open.enrz.json.GlobalNavJson;
import com.open.enrz.jsoup.DynamicMainService;
import com.open.enrz.utils.ScreenUtils;
import com.open.enrz.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 动态main tab菜单
 * @author :fengguangjing
 * @createTime:2017-1-12下午4:22:20
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DynamicMainTabActivity extends CommonTabActivity<GlobalNavJson> {
	private TabHost mTabHost;
	private RadioGroup mRadioGroup;
	private String url = UrlUtils.ENRZ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		// 透明导航栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		setContentView(R.layout.activity_tab_main_dynamic);
		init();
	}
	
	@Override
	protected void findView() {
		// TODO Auto-generated method stub
		super.findView();
		mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		mTabHost = getTabHost();
		doAsync(this, this, this);
	}

	@Override
	public GlobalNavJson call() throws Exception {
		// TODO Auto-generated method stub
		GlobalNavJson  mGlobalNavJson = new GlobalNavJson();
		mGlobalNavJson.setList(DynamicMainService.parseMainTab(url));
		return mGlobalNavJson;
	}

	@Override
	public void onCallback(GlobalNavJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		Intent intent;
		for(int i=0;i<result.getList().size();i++){
			GlobalNavBean mbean = result.getList().get(i);
			TabSpec tab_main = mTabHost.newTabSpec(mbean.getTarget());
			
			if(mbean.getTarget().equals("首页")){
				tab_main.setContent(new Intent(this, EnrzLogoDefaultActivity.class)).setIndicator(mbean.getTarget());
			}else if(mbean.getTarget().equals("美图")){
				intent = new Intent(this, EnrzWebViewActivity.class);
				intent.putExtra("URL", mbean.getHref());
				tab_main.setContent(intent).setIndicator(mbean.getTarget());
			}else if(mbean.getTarget().equals("商城")){
				intent = new Intent(this, EnrzWebViewActivity.class);
				intent.putExtra("URL", mbean.getHref());
				tab_main.setContent(intent).setIndicator(mbean.getTarget());
			}else{
				intent = new Intent(this, GnSubIndicatorFragmentActivity.class);
				intent.putExtra("URL", mbean.getHref());
				intent.putExtra("TITLE", mbean.getTarget());
				tab_main.setContent(intent).setIndicator(mbean.getTarget());
			}
			mTabHost.addTab(tab_main);
			
			View viewRadio = LayoutInflater.from(this).inflate(R.layout.layout_tab_main_dynamic_radio, null);
			RadioButton radio = (RadioButton) viewRadio.findViewById(R.id.radio_item);
			radio.setText(mbean.getTarget());
			
			final int position = i;
			radio.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mTabHost.setCurrentTab(position);
					setCurrentTab(position);
				}
			});
			if(i==0){
				radio.setChecked(true);
			}else {
				radio.setChecked(false);
			}
			LayoutParams params = new LayoutParams((int)ScreenUtils.getIntToDip(this, 95), LayoutParams.WRAP_CONTENT);
			mRadioGroup.addView(viewRadio,params);
		}
	}
	
	protected void setCurrentTab(int position){
		for(int i=0;i<mRadioGroup.getChildCount();i++){
			View viewRadio = mRadioGroup.getChildAt(i);
			RadioButton radio = (RadioButton) viewRadio.findViewById(R.id.radio_item);
			if(i!=position){
				radio.setChecked(false);
			} else{
				radio.setChecked(true);
			}
		}
	}
	
	protected int getCurrentTab(){
		int position = 0;
		for(int i=0;i<mRadioGroup.getChildCount();i++){
			View viewRadio = mRadioGroup.getChildAt(i);
			RadioButton radio = (RadioButton) viewRadio.findViewById(R.id.radio_item);
			if(radio.isChecked()){
				position = i;
			}
		}
		return position;
	}

}
