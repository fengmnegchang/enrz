package com.open.enrz.jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.open.enrz.bean.GnSubBean;
import com.open.enrz.utils.UrlUtils;

public class GnSubService extends CommonService {
	public static final String TAG = GnSubService.class.getSimpleName();

	public static List<GnSubBean> parseGnSub(String title, String href) {
		List<GnSubBean> list = new ArrayList<GnSubBean>();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				GnSubBean gbean = new GnSubBean();
				gbean.setHref(href);
				gbean.setTarget(title);
				list.add(gbean);

				Element globalnavElement = doc.select("div.globalnav").first();
				Elements moduleElements = globalnavElement.select("ul.gn-sub");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						try {
							/**
							 * <li><a target="_blank"
							 * href="http://enrz.com/beauty">尤物</a>
							 * <ul class="gn-sub">
							 * <li><a target="_blank"
							 * href="http://enrz.com/beauty/cover-girl">封面明星</a>
							 * </li>
							 * <li><a target="_blank"
							 * href="http://enrz.com/beauty/news-girl">新闻女郎</a></li>
							 * <li><a target="_blank"
							 * href="http://enrz.com/beauty/incoming-girl"
							 * >Incoming Girl</a></li>
							 * </ul>
							 */
							Element aElement = moduleElements.get(i).parent().select("a").first();
							if (aElement != null) {
								String titlea = aElement.text();
								String hrefa = aElement.attr("href");
								Log.i(TAG, "i==" + i + ";titlea==" + titlea + ";hrefa==" + hrefa);
								if (title.equals(titlea)) {
									Elements liElements = moduleElements.get(i).select("li");
									if (liElements != null && liElements.size() > 0) {
										for (int j = 0; j < liElements.size(); j++) {
											gbean = new GnSubBean();
											try {
												String titlej = liElements.get(j).select("a").first().text();
												String hrefj = liElements.get(j).select("a").first().attr("href");
												Log.i(TAG, "j==" + j + ";titlej==" + titlej + ";hrefj==" + hrefj);
												gbean.setHref(hrefj);
												gbean.setTarget(titlej);
											} catch (Exception e) {
												e.printStackTrace();
											}
											list.add(gbean);
										}
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

	public static List<GnSubBean> parseNav(String title, String href) {
		List<GnSubBean> list = new ArrayList<GnSubBean>();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {

				Element globalnavElement = doc.select("div.lin_nav_c").first();
				Elements moduleElements = globalnavElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						try {
							/**
							 * <li class="li_channel active"><a
							 * href="http://pic.enrz.com/miss/"
							 * title="美女">美女</a></li> <li ><a
							 * href="http://pic.enrz.com/sexy/"
							 * title="性感写真">性感写真</a></li>
							 */
							Element aElement = moduleElements.get(i).select("a").first();
							if (aElement != null) {
								String titlea = aElement.text();
								String hrefa = aElement.attr("href");
								Log.i(TAG, "i==" + i + ";titlea==" + titlea + ";hrefa==" + hrefa);
								GnSubBean gbean = new GnSubBean();
								gbean.setHref(hrefa);
								gbean.setTarget(titlea);
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
