/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18下午3:38:58
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.bean.m;

import com.open.enrz.bean.CommonBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18下午3:38:58
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MArticlePBean extends CommonBean {
	private String crumbstitle;
	private String title;
	private String category;
	private String preleft;
	private String prelefthref;
	private String nextright;
	private String nextrighthref;
	private String articlep;

	public String getArticlep() {
		return articlep;
	}

	public void setArticlep(String articlep) {
		this.articlep = articlep;
	}

	public String getCrumbstitle() {
		return crumbstitle;
	}

	public void setCrumbstitle(String crumbstitle) {
		this.crumbstitle = crumbstitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPreleft() {
		return preleft;
	}

	public void setPreleft(String preleft) {
		this.preleft = preleft;
	}

	public String getPrelefthref() {
		return prelefthref;
	}

	public void setPrelefthref(String prelefthref) {
		this.prelefthref = prelefthref;
	}

	public String getNextright() {
		return nextright;
	}

	public void setNextright(String nextright) {
		this.nextright = nextright;
	}

	public String getNextrighthref() {
		return nextrighthref;
	}

	public void setNextrighthref(String nextrighthref) {
		this.nextrighthref = nextrighthref;
	}

}
