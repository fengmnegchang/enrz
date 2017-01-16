package com.open.enrz.jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.open.enrz.bean.ThumbnailBean;
import com.open.enrz.utils.UrlUtils;

public class HourHotService extends CommonService {
	public static final String TAG = HourHotService.class.getSimpleName();

	public static List<ThumbnailBean> parseListHourHot(String html) {
		List<ThumbnailBean> list = new ArrayList<ThumbnailBean>();
		try {
//			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			Document doc = Jsoup.parse(html);
			// System.out.println(doc.toString());

			try {
				Elements moduleElements = doc.select("section.list-thumbnail-nodesc");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						ThumbnailBean tbean = new ThumbnailBean();
						try {
							/**
							 * <section class="list-thumbnail-nodesc">
							 * <div class="list-thumbnail-nodesc-pic">
							 * <a href="http://enrz.com/fhm/2017/01/15/77532.html" target="_blank">
							 * <img width="300" height="181" src="http://images.enrz.com/wp-content/uploads/2017/01/tt31.jpg" 
							 * class="attachment-post-thumbnail wp-post-image" alt="tt" /></a>
							 * </div>
							 * <h4 class="list-thumbnail-nodesc-title"><a href="http://enrz.com/fhm/2017/01/15/77532.html" target="_blank">
							 * 过去一年，各国人民的“飙车”习惯</a></h4>
							 * <div class="list-thumbnail-nodesc-stamp"><p>2017年01月15日</p></div><div class="cb"></div>
							 * </section>
							 */
						 
							 
							try {
								Element picElement = moduleElements.get(i).select("div.list-thumbnail-nodesc-pic").first();
								if (picElement != null) {
									Element imgElement = picElement.select("img").first();
									String src = imgElement.attr("src");
									Log.i(TAG, "i==" + i + ";src==" + src  );
									tbean.setPicsrc(src);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element descElement = moduleElements.get(i).select("h4.list-thumbnail-nodesc-title").first();
								if (descElement != null) {
									Element aElement = descElement.select("a").first();
									String titlea = aElement.text();
									Log.i(TAG, "i==" + i + ";titlea==" + titlea);
									tbean.setTitle(titlea);
									tbean.setHref(aElement.attr("href"));
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								Element descElement = moduleElements.get(i).select("div.list-thumbnail-nodesc-stamp").first();
								if (descElement != null) {
									Element aElement = descElement.select("p").first();
									String stamp = aElement.text();
									Log.i(TAG, "i==" + i + ";stamp==" + stamp);
									tbean.setDesc(stamp);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}

						list.add(tbean);

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
