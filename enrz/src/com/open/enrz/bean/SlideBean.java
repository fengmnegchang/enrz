/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-13上午9:25:51
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.bean;

/**
 *****************************************************************************************************************************************************************************
 * <li> 
                <a href="http://pic.enrz.com/2016/1014/196602.shtml" class="pic" target="_blank">
                    <img src="http://images.enrz.com/wp-content/uploads/2016/10/285A8322.jpg" border="0">
                    <p class="st_ty">徐冬冬：性感给了我勇气</p>
                </a>
                <div class="slide_Bg"></div>
            </li>
 * @author :fengguangjing
 * @createTime:2017-1-13上午9:25:51
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class SlideBean extends CommonBean{
	private String href;
	private String src;
	private String st_ty;
	private String view_intro;
	
	private String prehref;
	private String presrc;
	private String nexthref;
	private String nextsrc;
	
	
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getSt_ty() {
		return st_ty;
	}
	public void setSt_ty(String st_ty) {
		this.st_ty = st_ty;
	}
	public String getView_intro() {
		return view_intro;
	}
	public void setView_intro(String view_intro) {
		this.view_intro = view_intro;
	}
	public String getPrehref() {
		return prehref;
	}
	public void setPrehref(String prehref) {
		this.prehref = prehref;
	}
	public String getPresrc() {
		return presrc;
	}
	public void setPresrc(String presrc) {
		this.presrc = presrc;
	}
	public String getNexthref() {
		return nexthref;
	}
	public void setNexthref(String nexthref) {
		this.nexthref = nexthref;
	}
	public String getNextsrc() {
		return nextsrc;
	}
	public void setNextsrc(String nextsrc) {
		this.nextsrc = nextsrc;
	}

}
