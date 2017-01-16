/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午1:34:39
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.json;

import java.util.ArrayList;
import java.util.List;

import com.open.enrz.bean.SearchBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午1:34:39
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class SearchJson extends CommonJson {
	private List<SearchBean> list = new ArrayList<SearchBean>();

	public List<SearchBean> getList() {
		return list;
	}

	public void setList(List<SearchBean> list) {
		this.list = list;
	}
	
	
}
