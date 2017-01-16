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

public class ImageViewPagerService extends CommonService {
	public static final String TAG = ImageViewPagerService.class.getSimpleName();

	public static List<SlideBean> parseViewpager(String href) {
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
				Element globalnavElement = doc.select("div.detail_thumb_cm").first();
				Elements moduleElements = globalnavElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						SlideBean sbean = new SlideBean();
						try {
							/**
							<li  class="active">
								<a href="/2016/1014/196606.shtml" title="徐冬冬：性感给了我勇气">
								<img src="http://pic.enrz.cn/enrz/90x64/131/289/liDknDohEFDwU.jpg"
								 rel="http://pic.enrz.cn/enrz/985x695/131/289/liDknDohEFDwU.jpg" 
								 rev="http://pic.enrz.cn/enrz/985x695/131/289/liDknDohEFDwU.jpg"
								  alt=" 徐冬冬：性感给了我勇气"></a>		
								</li><li >
								<a href="/2016/1014/196608.shtml" title="徐冬冬：性感给了我勇气">
								<img src="http://pic.enrz.cn/enrz/90x64/131/291/liaMXaHUQULVk.jpg"
								 rel="http://pic.enrz.cn/enrz/985x695/131/289/liDknDohEFDwU.jpg" 
								 rev="http://pic.enrz.cn/enrz/985x695/131/289/liDknDohEFDwU.jpg" 
								 alt=" 徐冬冬：性感给了我勇气"></a>		
								</li><li >
								<a href="/2016/1014/196607.shtml" title="徐冬冬：性感给了我勇气"><img src="http://pic.enrz.cn/enrz/90x64/131/290/livEysEnX8ZvU.jpg" rel="http://pic.enrz.cn/enrz/985x695/131/289/liDknDohEFDwU.jpg" rev="http://pic.enrz.cn/enrz/985x695/131/289/liDknDohEFDwU.jpg" alt=" 徐冬冬：性感给了我勇气"></a>		
								</li><li >
								<a href="/2016/1014/196605.shtml" title="徐冬冬：性感给了我勇气"><img src="http://pic.enrz.cn/enrz/90x64/131/288/li5w79QjOTTo2.jpg" rel="http://pic.enrz.cn/enrz/985x695/131/289/liDknDohEFDwU.jpg" rev="http://pic.enrz.cn/enrz/985x695/131/289/liDknDohEFDwU.jpg" alt=" 徐冬冬：性感给了我勇气"></a>		
								</li><li >
								<a href="/2016/1014/196604.shtml" title="徐冬冬：性感给了我勇气"><img src="http://pic.enrz.cn/enrz/90x64/131/287/lieC8QQCU3Uc.jpg" rel="http://pic.enrz.cn/enrz/985x695/131/289/liDknDohEFDwU.jpg" rev="http://pic.enrz.cn/enrz/985x695/131/289/liDknDohEFDwU.jpg" alt=" 徐冬冬：性感给了我勇气"></a>		
								</li></ul><ul><li >
								<a href="/2016/1014/196603.shtml" title="徐冬冬：性感给了我勇气"><img src="http://pic.enrz.cn/enrz/90x64/131/286/liLW9ey53hVRo.jpg" rel="http://pic.enrz.cn/enrz/985x695/131/289/liDknDohEFDwU.jpg" rev="http://pic.enrz.cn/enrz/985x695/131/289/liDknDohEFDwU.jpg" alt=" 徐冬冬：性感给了我勇气"></a>		
								</li>*/
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
								Element aElement = doc.select("div.detail_m").first();
								if (aElement != null) {
									String view_intro = aElement.select("p").first().text();
									Log.i(TAG, "i==" + i + ";view_intro==" + view_intro);
									sbean.setView_intro(view_intro);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element aElement = moduleElements.get(i).select("a").first();
								if (aElement != null) {
									String title =  aElement.attr("title");
									Log.i(TAG, "i==" + i + ";title==" + title);
									sbean.setSt_ty(title);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

							try {
								Element imgElement = moduleElements.get(i).select("img").first();
								if (imgElement != null) {
									String src = imgElement.attr("src").replace("90x64", "985x695");
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
