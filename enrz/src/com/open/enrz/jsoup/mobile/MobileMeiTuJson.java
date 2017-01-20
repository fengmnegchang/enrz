/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20下午5:36:17
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.jsoup.mobile;

import java.util.ArrayList;
import java.util.List;

import com.open.enrz.bean.mobile.MobileMeiTuBean;
import com.open.enrz.json.CommonJson;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-20下午5:36:17
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MobileMeiTuJson extends CommonJson {
	private List<MobileMeiTuBean> list = new ArrayList<MobileMeiTuBean>();

	public List<MobileMeiTuBean> getList() {
		return list;
	}

	public void setList(List<MobileMeiTuBean> list) {
		this.list = list;
	}

}
