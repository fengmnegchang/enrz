/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午10:12:00
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.json.m;

import java.util.ArrayList;

import java.util.List;

import com.open.enrz.bean.m.MThumbBean;
import com.open.enrz.json.CommonJson;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18上午10:12:00
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MThumbJson extends CommonJson {
	private List<MThumbBean> list = new ArrayList<MThumbBean>();

	public List<MThumbBean> getList() {
		return list;
	}

	public void setList(List<MThumbBean> list) {
		this.list = list;
	}

}
