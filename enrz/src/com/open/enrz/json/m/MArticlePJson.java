/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18下午3:39:13
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.json.m;

import com.open.enrz.bean.m.MArticlePBean;
import com.open.enrz.json.CommonJson;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-1-18下午3:39:13
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MArticlePJson extends CommonJson {
	private MArticlePBean marticlep = new MArticlePBean();

	public MArticlePBean getMarticlep() {
		return marticlep;
	}

	public void setMarticlep(MArticlePBean marticlep) {
		this.marticlep = marticlep;
	}

}
