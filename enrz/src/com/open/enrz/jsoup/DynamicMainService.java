package com.open.enrz.jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.open.enrz.bean.GlobalNavBean;
import com.open.enrz.utils.UrlUtils;

public class DynamicMainService extends CommonService {
	public static final String TAG = DynamicMainService.class.getSimpleName();

	public static List<GlobalNavBean> parseMainTab(String href) {
		List<GlobalNavBean> list = new ArrayList<GlobalNavBean>();
		try {
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
//			System.out.println(doc.toString());

			try {
				GlobalNavBean gbean = new GlobalNavBean();
				Element liElement = doc.select("li.logo").first();
				if (liElement != null) {
					// <li class="logo"></li><li><a
					// href="http://enrz.com">首页</a></li>
					Element eElement = liElement.nextElementSibling();
					String titlea = eElement.text();
					String hrefa = eElement.attr("href");
					Log.i(TAG, ";titlea==" + titlea + ";hrefa==" + hrefa);
					gbean.setHref(hrefa);
					gbean.setTarget(titlea);
					if (titlea.equals("首页")) {
						list.add(gbean);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Element globalnavElement = doc.select("div.globalnav").first();
				Elements moduleElements = globalnavElement.select("ul.gn-sub");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						GlobalNavBean gbean = new GlobalNavBean();
						try {
							/**
							 * <li><a target="_blank" href="http://enrz.com/beauty">尤物</a>
							<ul class="gn-sub">
								<li><a target="_blank" href="http://enrz.com/beauty/cover-girl">封面明星</a></li>
								<li><a target="_blank" href="http://enrz.com/beauty/news-girl">新闻女郎</a></li>
								<li><a target="_blank" href="http://enrz.com/beauty/incoming-girl">Incoming Girl</a></li>
							</ul>
							 */
							Element titleLeftElement = moduleElements.get(i).parent().select("a").first();
							if (titleLeftElement != null) {
								String titlea = titleLeftElement.text();
								String hrefa = titleLeftElement.attr("href");
								Log.i(TAG, "i==" + i + ";titlea==" + titlea + ";hrefa==" + hrefa);
								gbean.setHref(hrefa);
								gbean.setTarget(titlea);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

						list.add(gbean);
						
						try {
							//<li><a target="_blank" href="http://shop.enrz.com/">商城</a></li>
							if(i==moduleElements.size()-1){
								Element liElement;
								try {
									gbean = new GlobalNavBean();
								    liElement = moduleElements.get(i).parent().previousElementSibling();
									if (liElement != null) {
										// <li class="cur active"><a target="_blank" href="http://enrz.com/special">专题</a></li>
										String titlea = liElement.select("a").first().text();
										String hrefa = liElement.select("a").first().attr("href");
										Log.i(TAG, ";titlea==" + titlea + ";hrefa==" + hrefa);
										gbean.setHref(hrefa);
										gbean.setTarget(titlea);
										if (titlea.equals("专题")) {
											list.add(gbean);
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								gbean = new GlobalNavBean();
								liElement = moduleElements.get(i).parent().nextElementSibling();
								if (liElement != null) {
									String titlea = liElement.select("a").first().text();
									String hrefa = liElement.select("a").first().attr("href");
									Log.i(TAG, ";titlea==" + titlea + ";hrefa==" + hrefa);
									gbean.setHref(hrefa);
									gbean.setTarget(titlea);
									if (titlea.equals("商城")) {
										list.add(gbean);
									}
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
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
