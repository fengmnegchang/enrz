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

public class GnSubPullListService extends CommonService {
	public static final String TAG = GnSubPullListService.class.getSimpleName();

	public static List<ThumbnailBean> parseListThumbnail(String href,int pagerno) {
		List<ThumbnailBean> list = new ArrayList<ThumbnailBean>();
		try {
			if(pagerno>1){
				//http://enrz.com/beauty/page/2
				href = href+"/page/"+pagerno;
			}
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());

			try {
				Element globalnavElement = doc.select("div.mc-content").first();
				Elements moduleElements = globalnavElement.select("section.list-thumbnail");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						ThumbnailBean tbean = new ThumbnailBean();
						try {
							/**
							 * <section class="list-thumbnail">
                    <div class="list-thumbnail-stamp">
                        <a href="http://enrz.com/info/weird">猎奇</a> / 2016年12月26日                    </div>
                    <h4 class="list-thumbnail-title">
                    <a href="http://enrz.com/fhm/2016/12/26/74334.html" target="_blank">为什么日本AV开头有美国FBI的警告?</a></h4>
                    <div class="list-thumbnail-pic"><a href="http://enrz.com/fhm/2016/12/26/74334.html" target="_blank"><img width="300" height="181" src="http://images.enrz.com/wp-content/uploads/2016/12/tt51.jpg" class="attachment-post-thumbnail wp-post-image" alt="tt" /></a></div>
                    <div class="list-thumbnail-desc">
                        <p>你在桃谷绘里香和大桥未久间犹豫了一下，最终选择了大桥未久。首先映入眼帘的，是鲜红的“FBI警告” ( FBI Warning &hellip;</p>
                        <p><a href="http://enrz.com/fhm/2016/12/26/74334.html" class="more" target="_blank">了解更多......</a></p>
                    </div>
                    <div class="cb"></div>
                </section> 
							 */
							try {
								Element stampElement = moduleElements.get(i).select("div.list-thumbnail-stamp").first();
								if (stampElement != null) {
									Element aElement = stampElement.select("a").first();
									String stamptxt = stampElement.text();
									String stamphref = aElement.attr("href");
									Log.i(TAG, "i==" + i + ";stamptxt==" + stamptxt + ";stamphref==" + stamphref);
									tbean.setStamptxt(stamptxt);
									tbean.setStamphref(stamphref);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								//<a href="http://enrz.com/fhm/2016/12/26/74334.html" target="_blank">为什么日本AV开头有美国FBI的警告?</a></h4>
								Element titleElement = moduleElements.get(i).select("h4.list-thumbnail-title").first();
								if (titleElement != null) {
									Element aElement = titleElement.select("a").first();
									String atxt = aElement.text();
									String ahref = aElement.attr("href");
									Log.i(TAG, "i==" + i + ";atxt==" + atxt + ";ahref==" + ahref);
									tbean.setTitle(atxt);
									tbean.setHref(ahref);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								//<a href="http://enrz.com/fhm/2016/12/26/74334.html" target="_blank">
								//<img width="300" height="181" src="http://images.enrz.com/wp-content/uploads/2016/12/tt51.jpg"
								//		class="attachment-post-thumbnail wp-post-image" alt="tt" /></a></div>
								Element picElement = moduleElements.get(i).select("div.list-thumbnail-pic").first();
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
								//<div class="list-thumbnail-desc">
		                       // <p>你在桃谷绘里香和大桥未久间犹豫了一下，最终选择了大桥未久。首先映入眼帘的，是鲜红的“FBI警告” ( FBI Warning &hellip;</p>
		                       // <p><a href="http://enrz.com/fhm/2016/12/26/74334.html" class="more" target="_blank">了解更多......</a></p>
		                    //</div>
								Element descElement = moduleElements.get(i).select("div.list-thumbnail-desc").first();
								if (descElement != null) {
									Element pElement = descElement.select("p").first();
									String desc = pElement.text();
									Log.i(TAG, "i==" + i + ";desc==" + desc  );
									tbean.setDesc(desc);
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
