package com.open.enrz.jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.open.enrz.bean.SlideBean;
import com.open.enrz.utils.UrlUtils;

public class SlideService extends CommonService {
	public static final String TAG = SlideService.class.getSimpleName();

	public static List<SlideBean> parseSlide(String href) {
		List<SlideBean> list = new ArrayList<SlideBean>();
		try {
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				Element globalnavElement = doc.select("div.slider-wrap").first();
				Elements moduleElements = globalnavElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						SlideBean sbean = new SlideBean();
						try {
							/**
							 * <li>
							 * <a href=
							 * "http://pic.enrz.com/2016/1014/196602.shtml"
							 * class="pic" target="_blank"> <img src=
							 * "http://images.enrz.com/wp-content/uploads/2016/10/285A8322.jpg"
							 * border="0">
							 * <p class="st_ty">
							 * 徐冬冬：性感给了我勇气
							 * </p>
							 * </a> <div class="slide_Bg"></div></li>
							 */
							try {
								Element aElement = moduleElements.get(i).select("a").first();
								if (aElement != null) {
									String hrefa = aElement.attr("href");
									Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
									sbean.setHref(hrefa);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

							try {
								Element pElement = moduleElements.get(i).select("p").first();
								if (pElement != null) {
									String st_ty = pElement.text();
									Log.i(TAG, "i==" + i + ";st_ty==" + st_ty);
									sbean.setSt_ty(st_ty);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

							try {
								Element imgElement = moduleElements.get(i).select("img").first();
								if (imgElement != null) {
									String src = imgElement.attr("src");
									Log.i(TAG, "i==" + i + ";src==" + src);
									sbean.setSrc(src);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

						list.add(sbean);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			// hot
			/**
			 * <section id="promos">
			 * 
			 * <a href="http://enrz.com/fhm/2016/12/22/75313.html"
			 * target="_blank"> <div class="promos-box">
			 * <p class="pb-pic">
			 * <img src=
			 * "http://images.enrz.com/wp-content/uploads/2016/12/tt42.jpg"
			 * border="0" />
			 * </p>
			 * <p class="pb-title">
			 * 赵多娜：我的锁骨是最性感的
			 * </p>
			 * </div> </a>
			 * 
			 * <a href="http://enrz.com/fhm/2016/12/12/74164.html"
			 * target="_blank"> <div class="promos-box">
			 * <p class="pb-pic">
			 * <img src=
			 * "http://images.enrz.com/wp-content/uploads/2016/12/tt22.jpg"
			 * border="0" />
			 * </p>
			 * <p class="pb-title">
			 * 终于，川普的女儿上了《男人装》
			 * </p>
			 * </div> </a>
			 * 
			 * <a href="http://enrz.com/fhm/2016/11/21/72110.html"
			 * target="_blank"> <div class="promos-box">
			 * <p class="pb-pic">
			 * <img src=
			 * "http://images.enrz.com/wp-content/uploads/2016/11/tt30.jpg"
			 * border="0" />
			 * </p>
			 * <p class="pb-title">
			 * 你能想到李倩可以这么性感？
			 * </p>
			 * </div> </a>
			 * 
			 * <div class="cb"></div> </section>
			 * 
			 * <div class="cb"></div>
			 * 
			 * <div id="main-contbox" class="main-web"> <div
			 * class="mc-content fl">
			 */
			try {
				Element divElement = doc.select("div.mc-content").first();
				Element sectionElement = divElement.parent().previousElementSibling().previousElementSibling();
				if (sectionElement != null) {
					Elements aElements = sectionElement.select("a");
					if (aElements != null && aElements.size() > 0) {
						/**
						 * <a href="http://enrz.com/fhm/2016/11/21/72110.html"
						 * target="_blank"> <div class="promos-box">
						 * <p class="pb-pic">
						 * <img src=
						 * "http://images.enrz.com/wp-content/uploads/2016/11/tt30.jpg"
						 * border="0" />
						 * </p>
						 * <p class="pb-title">
						 * 你能想到李倩可以这么性感？
						 * </p>
						 * </div> </a>
						 */
						for (int i = 0; i < aElements.size(); i++) {
							SlideBean sbean = new SlideBean();

							try {
								Element aElement = aElements.get(i).select("a").first();
								if (aElement != null) {
									String hrefa = aElement.attr("href");
									Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
									sbean.setHref(hrefa);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

							try {
								Element pElement = aElements.get(i).select("p.pb-title").first();
								if (pElement != null) {
									String st_ty = pElement.text();
									Log.i(TAG, "i==" + i + ";st_ty==" + st_ty);
									sbean.setSt_ty(st_ty);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

							try {
								Element imgElement = aElements.get(i).select("img").first();
								if (imgElement != null) {
									String src = imgElement.attr("src");
									Log.i(TAG, "i==" + i + ";src==" + src);
									sbean.setSrc(src);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							list.add(sbean);
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
