package com.open.enrz.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.util.Log;

import com.open.enrz.bean.ContboxBean;
import com.open.enrz.utils.UrlUtils;

public class ContBoxScrollService extends CommonService {
	public static final String TAG = ContBoxScrollService.class.getSimpleName();

	public static ContboxBean parseContbox(String href) {
		ContboxBean contbox = new ContboxBean();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				Element contentElement = doc.select("div.mc-content").first();
				if (contentElement != null) {
					contbox.setCenterp(contentElement.toString());
					System.out.println(contentElement.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contbox;
	}
}
