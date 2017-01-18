/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午11:20:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.json.m;

import java.util.ArrayList;
import java.util.List;

import com.open.enrz.bean.m.MThumbCateBean;
import com.open.enrz.json.CommonJson;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午11:20:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MThumbCateJson extends CommonJson {

	private List<MThumbCateBean> list = new ArrayList<MThumbCateBean>();

	public List<MThumbCateBean> getList() {
		return list;
	}

	public void setList(List<MThumbCateBean> list) {
		this.list = list;
	}
	
}
