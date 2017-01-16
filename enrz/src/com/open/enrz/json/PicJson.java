/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午5:26:09
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.json;

import java.util.ArrayList;
import java.util.List;

import com.open.enrz.bean.PicBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午5:26:09
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class PicJson extends CommonJson {
	private List<PicBean> list = new ArrayList<PicBean>();

	public List<PicBean> getList() {
		return list;
	}

	public void setList(List<PicBean> list) {
		this.list = list;
	}

}
