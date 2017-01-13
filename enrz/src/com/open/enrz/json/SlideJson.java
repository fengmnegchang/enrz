/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13上午9:27:01
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.json;

import java.util.ArrayList;
import java.util.List;

import com.open.enrz.bean.SlideBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13上午9:27:01
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class SlideJson extends CommonJson {
	private List<SlideBean> list  =  new ArrayList<SlideBean>();

	public List<SlideBean> getList() {
		return list;
	}

	public void setList(List<SlideBean> list) {
		this.list = list;
	}
	
	
}
