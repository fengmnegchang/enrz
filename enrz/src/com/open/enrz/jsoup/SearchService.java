package com.open.enrz.jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.open.enrz.bean.SearchBean;
import com.open.enrz.utils.UrlUtils;

public class SearchService extends CommonService {
	public static final String TAG = SearchService.class.getSimpleName();

	public static List<SearchBean> parseSearch(String s,int pagerno) {
		List<SearchBean> list = new ArrayList<SearchBean>();
		try {
			//http://enrz.com/index.php?s=%E7%94%B5%E8%84%91
			//http://enrz.com/page/2?s=%E7%94%B5%E8%84%91
			String href = null;
			if(pagerno==1){
				href = UrlUtils.ENRZ_SEARCH+s;
			}else if(pagerno>1){
				href = UrlUtils.ENRZ_SEARCH_PAGENO+pagerno+"?s="+s;
			}
			
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				Element globalnavElement = doc.select("ul.ulstyledeci").first();
				Elements moduleElements = globalnavElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						SearchBean sbean = new SearchBean();
						try {
							//   <li><a href="http://enrz.com/fhm/2016/08/16/60846.html">因为自拍成瘾，这姑娘的生命受到威胁</a></li>
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
								Element aElement = moduleElements.get(i).select("a").first();
								if (aElement != null) {
									String titlea = aElement.text();
									Log.i(TAG, "i==" + i + ";titlea==" + titlea);
									sbean.setTitle(titlea);
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

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
