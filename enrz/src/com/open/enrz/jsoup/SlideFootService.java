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

public class SlideFootService extends CommonService {
	public static final String TAG = SlideFootService.class.getSimpleName();

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
				Elements moduleElements = doc.select("div.gallery-box");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						SlideBean sbean = new SlideBean();
						try {
							/**
							 * <a href="http://pic.enrz.com/2016/1122/206206.shtml" target="_blank">
                <div class="gallery-box bigger">
                    <div class="gb-pic"><img src="http://images.enrz.com/wp-content/uploads/2016/11/lijing.jpg" border="0" /></div>
                    <div class="gb-mask"></div>
                    <div class="gb-contbox">
                        <div class="gbcb-content">你能想到李倩可以这么性感？</div>
                    </div>
                </div>
            </a>
							 */
							try {
								Element aElement = moduleElements.get(i).parent().select("a").first();
								if (aElement != null) {
									String hrefa = aElement.attr("href");
									Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
									sbean.setHref(hrefa);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

							try {
								Element pElement = moduleElements.get(i).select("div.gbcb-content").first();
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

			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
