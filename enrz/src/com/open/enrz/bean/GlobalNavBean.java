/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-12下午4:18:05
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.bean;

import java.util.ArrayList;
import java.util.List;

/**
 ***************************************************************************************************************************************************************************** 
 * <li><a target="_blank" href="http://enrz.com/beauty">尤物</a>
 * 
 * @author :fengguangjing
 * @createTime:2017-1-12下午4:18:05
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class GlobalNavBean extends CommonBean {
	private String target;
	private String href;
	private List<GnSubBean> gnsub = new ArrayList<GnSubBean>();

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<GnSubBean> getGnsub() {
		return gnsub;
	}

	public void setGnsub(List<GnSubBean> gnsub) {
		this.gnsub = gnsub;
	}

}
