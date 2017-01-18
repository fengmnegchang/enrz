/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午11:19:39
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.bean.m;

import java.util.ArrayList;
import java.util.List;

import com.open.enrz.bean.CommonBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午11:19:39
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MThumbCateBean extends CommonBean {
	private List<MArticleBean> articlelist = new ArrayList<MArticleBean>();
	private String href;
	private String title;
	private MThumbBean thumb = new MThumbBean();

	public List<MArticleBean> getArticlelist() {
		return articlelist;
	}

	public void setArticlelist(List<MArticleBean> articlelist) {
		this.articlelist = articlelist;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MThumbBean getThumb() {
		return thumb;
	}

	public void setThumb(MThumbBean thumb) {
		this.thumb = thumb;
	}

}
