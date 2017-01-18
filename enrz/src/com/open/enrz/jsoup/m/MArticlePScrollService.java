package com.open.enrz.jsoup.m;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.util.Log;

import com.open.enrz.bean.m.MArticlePBean;
import com.open.enrz.jsoup.CommonService;
import com.open.enrz.utils.UrlUtils;

public class MArticlePScrollService extends CommonService {
	public static final String TAG = MArticlePScrollService.class.getSimpleName();

	public static MArticlePBean parseMArticle(String href) {
		MArticlePBean mArticlebean = new MArticlePBean();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				Element contentElement = doc.select("article.entry").first();
				if (contentElement != null) {
					String articlep = contentElement.toString().replace("rel=\"nofollow\"><img", "rel=\"nofollow\"><a");
					mArticlebean.setArticlep(articlep);
					System.out.println(articlep);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				Element crumbsElement = doc.select("article.crumbs-search").first();
				if (crumbsElement != null) {
					String crumbstitle = crumbsElement.select("div.crumbs").first().text();
					mArticlebean.setCrumbstitle(crumbstitle);
					System.out.println(crumbstitle);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				Element crumbsElement = doc.select("div.content-box").first();
				if (crumbsElement != null) {
					String  title = crumbsElement.select("a").first().text();
					mArticlebean.setTitle(title);
					System.out.println(title);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				Element crumbsElement = doc.select("div.content-box").first();
				if (crumbsElement != null) {
					String  category = crumbsElement.select("div.info").first().text();
					mArticlebean.setCategory(category);
					System.out.println(category);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				Element crumbsElement = doc.select("div.pre-next").first();
				if (crumbsElement != null) {
					Element leftElement = crumbsElement.select("div.left").first();
					String  preleft = leftElement.text();
					mArticlebean.setPreleft(preleft);
					System.out.println(preleft);
					mArticlebean.setPrelefthref(leftElement.select("a").first().attr("href"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				Element crumbsElement = doc.select("div.pre-next").first();
				if (crumbsElement != null) {
					Element leftElement = crumbsElement.select("div.right").first();
					String  preleft = leftElement.text();
					mArticlebean.setNextright(preleft);
					System.out.println(preleft);
					mArticlebean.setNextrighthref(leftElement.select("a").first().attr("href"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mArticlebean;
	}
}
