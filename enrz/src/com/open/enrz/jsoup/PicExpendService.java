package com.open.enrz.jsoup;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.open.enrz.bean.GnSubBean;
import com.open.enrz.bean.PicBean;
import com.open.enrz.utils.UrlUtils;

public class PicExpendService extends CommonService {
	public static final String TAG = PicExpendService.class.getSimpleName();
	public static List<GnSubBean> parseExpend( String href) {
		List<GnSubBean> list = new ArrayList<GnSubBean>();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			Element divElement = doc.select("div.c3_b").first();
			Element c2Element = doc.select("div.c2_left").first();
			//c3b
			GnSubBean gbean = new GnSubBean();
			gbean.setHref("");
			gbean.setTarget("视觉");
			List<PicBean> clist = new ArrayList<PicBean>();
			PicBean picbean;
			Elements divElements = divElement.select("a");
			if (divElements != null && divElements.size() > 0) {
				for (int i = 1; i < divElements.size(); i++) {
					try {
						picbean = new PicBean();
						try {
							Element c3aElement = divElements.get(i).select("a").first();
							String titlec3a = c3aElement.text();
							String hrefc3a = c3aElement.attr("href");
							Log.i(TAG, "i==" + i + ";titlec3a==" + titlec3a + ";hrefc3a==" + hrefc3a);
							picbean.setHref(hrefc3a);
							picbean.setTitle(titlec3a);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							Element imgElement = divElements.get(i).select("img").first();
							String src = imgElement.attr("src");
							picbean.setSrc(src);
						} catch (Exception e) {
							e.printStackTrace();
						}
						clist.add(picbean);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			gbean.setClist(clist);
			list.add(gbean);
			
			//c2left
			gbean = new GnSubBean();
			gbean.setHref("");
			gbean.setTarget("编辑推荐");
			clist = new ArrayList<PicBean>();
			Elements c3leftElements = c2Element.select("li");
			if (c3leftElements != null && c3leftElements.size() > 0) {
				for (int i = 1; i < c3leftElements.size(); i++) {
					try {
						/**
						 * <li>
<a href="http://pic.enrz.com/2016/0701/155686.shtml"
 title="清纯美女百看不腻，颜值是关键" target="_blank">
 <img width="190" height="260" 
 src="http://pic.enrz.cn/enrz/210x280/129/245/lit0Fe5mPKcXU.jpg"
  alt="清纯美女百看不腻，颜值是关键"><span>清纯美女百看不腻，颜值是关键</span></a>
</li>
						 */
						picbean = new PicBean();
						try {
							Element c3aElement = c3leftElements.get(i).select("a").first();
							String titlec3a = c3aElement.text();
							String hrefc3a = c3aElement.attr("href");
							Log.i(TAG, "i==" + i + ";titlec3a==" + titlec3a + ";hrefc3a==" + hrefc3a);
							picbean.setHref(hrefc3a);
							picbean.setTitle(titlec3a);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							Element imgElement = c3leftElements.get(i).select("img").first();
							String src = imgElement.attr("src");
							picbean.setSrc(src);
						} catch (Exception e) {
							e.printStackTrace();
						}
						clist.add(picbean);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			gbean.setClist(clist);
			list.add(gbean);
			
			try {
				Elements moduleElements = doc.select("div.com_h2");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						try {
							/**
							  <div class="com_h2">
                <h2 class="com_h2_1">
                    <a href="/miss/" target="_blank" title="美女图片">美女 Girl</a>
                </h2>
					<span>
                       <a href="http://pic.enrz.com/sportsbaby/" target="_blank" title="体育宝贝">体育宝贝</a><a href="http://pic.enrz.com/showgirl/" target="_blank" title="showgirl">showgirl</a><a href="http://pic.enrz.com/carshow/" target="_blank" title="香车美女">香车美女</a><a href="http://pic.enrz.com/sexy/" target="_blank" title="性感写真">性感写真</a>
                        <a href="/miss/" rel="nofollow" target="_blank">More></a>
                    </span>
            </div>
            <div class="c3_l">
							 */
							Element aElement = moduleElements.get(i).select("a").first();
							if (aElement != null) {
								String titlea = aElement.text();
								String hrefa = aElement.attr("href");
								Log.i(TAG, "i==" + i + ";titlea==" + titlea + ";hrefa==" + hrefa);
								  gbean = new GnSubBean();
								gbean.setHref(hrefa);
								gbean.setTarget(titlea);
								
								 clist = new ArrayList<PicBean>();
								try {
									Element c3_lElement = moduleElements.get(i).nextElementSibling();
									if(c3_lElement!=null && c3_lElement.attr("class").equals("c3_l")){
										try {
											//左边大图
											/**
											 * <a href="http://pic.enrz.com/2017/0106/215616.shtml" title="风韵美女私房露肩内衣居家性感写真" 
											 * target="_blank"><img src="http://pic.enrz.cn/enrz/300x400/132/372/liK2KdIloIIHI.jpg" 
											 * alt="风韵美女私房露肩内衣居家性感写真" width="300" height="400">
											 * <span>美女私房内衣居家性感写真</span></a>
											 */
											Element c3aElement = c3_lElement.select("a").first();
											picbean = new PicBean();
											
											String titlec3a = c3aElement.text();
											String hrefc3a = c3aElement.attr("href");
											Log.i(TAG, "i==" + i + ";titlec3a==" + titlec3a + ";hrefc3a==" + hrefc3a);
											picbean.setHref(hrefc3a);
											picbean.setTitle(titlec3a);
											
											Element imgElement = c3_lElement.select("img").first();
											String src = imgElement.attr("src");
											picbean.setSrc(src);
											clist.add(picbean);
										} catch (Exception e) {
											e.printStackTrace();
										}
										
										try {
											Element c3_rElement = c3_lElement.nextElementSibling();
											if(c3_rElement!=null && c3_rElement.attr("class").equals("c3_r")){
												/**
												 * ><li><a href="http://pic.enrz.com/2017/0106/215573.shtml"
												 *  title="阳光美女木崎尤利娅比基尼性感海滩风情写真" target="_blank">
												 *  <img src="http://pic.enrz.cn/enrz/150x200/132/293/litt42Ll24Y.jpg" width="150" height="200" 
												 * alt="阳光美女木崎尤利娅比基尼性感海滩风情写真">
												 * <span>阳光美女比基尼性感海</span></a></li>
												 */
												Elements liElements = c3_rElement.select("li");
												if(liElements!=null && liElements.size()>0){
													try {
														for(int j=0;j<liElements.size();j++){
															try {
																Element c3aElement = liElements.get(j).select("a").first();
																picbean = new PicBean();
																
																String titlec3a = c3aElement.text();
																String hrefc3a = c3aElement.attr("href");
																Log.i(TAG, "i==" + i + ";titlec3a==" + titlec3a + ";hrefc3a==" + hrefc3a);
																picbean.setHref(hrefc3a);
																picbean.setTitle(titlec3a);
																
																Element imgElement = liElements.get(j).select("img").first();
																String src = imgElement.attr("src");
																picbean.setSrc(src);
																clist.add(picbean);
															} catch (Exception e) {
																e.printStackTrace();
															}
														}
													} catch (Exception e) {
														e.printStackTrace();
													}
												}
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								gbean.setClist(clist);
								list.add(gbean);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
