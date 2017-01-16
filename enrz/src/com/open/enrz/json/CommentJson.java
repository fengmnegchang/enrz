/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午2:54:54
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.json;

import java.util.ArrayList;
import java.util.List;

import com.open.enrz.bean.CommentBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午2:54:54
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class CommentJson extends CommonJson {
	private List<CommentBean> list  = new ArrayList<CommentBean>();

	public List<CommentBean> getList() {
		return list;
	}

	public void setList(List<CommentBean> list) {
		this.list = list;
	}

}
