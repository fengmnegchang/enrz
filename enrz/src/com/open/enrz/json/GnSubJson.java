/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-12下午4:21:23
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.json;

import java.util.ArrayList;
import java.util.List;

import com.open.enrz.bean.GnSubBean;
import com.open.enrz.bean.SlideBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-12下午4:21:23
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class GnSubJson extends CommonJson {
	private List<GnSubBean> list = new ArrayList<GnSubBean>();
	private List<SlideBean> listhead  =  new ArrayList<SlideBean>();
	
	public List<GnSubBean> getList() {
		return list;
	}

	public void setList(List<GnSubBean> list) {
		this.list = list;
	}

	public List<SlideBean> getListhead() {
		return listhead;
	}

	public void setListhead(List<SlideBean> listhead) {
		this.listhead = listhead;
	}

}
