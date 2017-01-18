package com.open.enrz.jsoup.m;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.open.enrz.bean.m.MThumbBean;
import com.open.enrz.jsoup.CommonService;
import com.open.enrz.utils.UrlUtils;

public class MThumbService extends CommonService {
	public static final String TAG = MThumbService.class.getSimpleName();

	public static List<MThumbBean> parseMThumb(String href) {
		List<MThumbBean> list = new ArrayList<MThumbBean>();
		try {
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			/**
			 * <div class="post-thumb">
    <a href="http://m.enrz.com/%e5%81%b7%e7%aa%a5%e7%95%8c%e7%9a%848%e5%a4%a7%e4%bc%a0%e5%a5%87%ef%bc%88%e5%8f%aa%e6%9c%89%e8%80%81%e5%a4%a7%e8%bf%98%e6%b2%a1%e8%bf%9b%e5%b1%80%e5%ad%90%ef%bc%89.html" target="_blank" title="偷窥界的8大传奇（只有老大还没进局子）"><img src="http://m.enrz.com/wp-content/themes/salong/includes/functions/timthumb.php?src=http://m.enrz.com/wp-content/themes/salong/images/thumb_loading.jpg&amp;h=225&amp;w=400&amp;zc=1" data-original="http://m.enrz.com/wp-content/themes/salong/includes/functions/timthumb.php?src=http://m.enrz.com/wp-content/uploads/2016/06/偷窥题图.png&amp;h=225&amp;w=400&amp;zc=1" alt="偷窥界的8大传奇（只有老大还没进局子）" /></a>  </div>
  <header class="post-head">
    <h2><a href="http://m.enrz.com/%e5%81%b7%e7%aa%a5%e7%95%8c%e7%9a%848%e5%a4%a7%e4%bc%a0%e5%a5%87%ef%bc%88%e5%8f%aa%e6%9c%89%e8%80%81%e5%a4%a7%e8%bf%98%e6%b2%a1%e8%bf%9b%e5%b1%80%e5%ad%90%ef%bc%89.html" rel="bookmark" target="_blank" title="详细阅读 偷窥界的8大传奇（只有老大还没进局子）">
      偷窥界的8大传奇（只有老大还没进局子）      </a></h2>
  </header>
  <!-- 摘要 -->
  <div class="excerpt">
    以下是偷窥界带有传奇色彩的8位“偷窥圣手”，他们思路缜密、器好活细，但全都折在了一些奇怪的巧合上……都别YY了，像下面这...  </div>
  <!-- 摘要end --> 
  <div class="info">
    <div class="up">
    <span><i class="icon-category"></i><a href="http://m.enrz.com/category/info/weird" rel="category tag">猎奇</a></span>
    <span><i class="icon-date"></i>2016-08-12</span>
        <a class="read-more right" href="http://m.enrz.com/%e5%81%b7%e7%aa%a5%e7%95%8c%e7%9a%848%e5%a4%a7%e4%bc%a0%e5%a5%87%ef%bc%88%e5%8f%aa%e6%9c%89%e8%80%81%e5%a4%a7%e8%bf%98%e6%b2%a1%e8%bf%9b%e5%b1%80%e5%ad%90%ef%bc%89.html" title="详细阅读 偷窥界的8大传奇（只有老大还没进局子）" rel="bookmark" target="_blank">阅读全文</a> </div>

      </div>
			 */
			try {
				Element globalnavElement = doc.select("section.new-post").first();
				Elements moduleElements = globalnavElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						MThumbBean sbean = new MThumbBean();
						try {
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
									String titlea = aElement.attr("title");
									Log.i(TAG, "i==" + i + ";titlea==" + titlea);
									sbean.setTitle(titlea);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element imgElement = moduleElements.get(i).select("img").first();
								if (imgElement != null) {
									String dataoriginal = imgElement.attr("data-original");
									Log.i(TAG, "i==" + i + ";dataoriginal==" + dataoriginal);
									sbean.setDataoriginal(dataoriginal);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element excerptElement = moduleElements.get(i).select("div.excerpt").first();
								if (excerptElement != null) {
									String excerpt = excerptElement.text();
									Log.i(TAG, "i==" + i + ";excerpt==" + excerpt);
									sbean.setExcerpt(excerpt);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element infoElement = moduleElements.get(i).select("div.info").first();
								if (infoElement != null) {
									String info = infoElement.text().replace("阅读全文", "");
									Log.i(TAG, "i==" + i + ";info==" + info);
									sbean.setInfo(info);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element infoElement = moduleElements.get(i).select("div.info").first();
								if (infoElement != null) {
									String category = infoElement.select("a").first().attr("href");
									Log.i(TAG, "i==" + i + ";category==" + category);
									sbean.setCategoryhref(category);
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
	
	public static List<MThumbBean> parseMThumb(String href,int pageno) {
		List<MThumbBean> list = new ArrayList<MThumbBean>();
		try {
			if(pageno>1){
				href = href + "/page/"+pageno;
			}
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			/**
			 * <div class="post-thumb">
    <a href="http://m.enrz.com/%e5%81%b7%e7%aa%a5%e7%95%8c%e7%9a%848%e5%a4%a7%e4%bc%a0%e5%a5%87%ef%bc%88%e5%8f%aa%e6%9c%89%e8%80%81%e5%a4%a7%e8%bf%98%e6%b2%a1%e8%bf%9b%e5%b1%80%e5%ad%90%ef%bc%89.html" target="_blank" title="偷窥界的8大传奇（只有老大还没进局子）"><img src="http://m.enrz.com/wp-content/themes/salong/includes/functions/timthumb.php?src=http://m.enrz.com/wp-content/themes/salong/images/thumb_loading.jpg&amp;h=225&amp;w=400&amp;zc=1" data-original="http://m.enrz.com/wp-content/themes/salong/includes/functions/timthumb.php?src=http://m.enrz.com/wp-content/uploads/2016/06/偷窥题图.png&amp;h=225&amp;w=400&amp;zc=1" alt="偷窥界的8大传奇（只有老大还没进局子）" /></a>  </div>
  <header class="post-head">
    <h2><a href="http://m.enrz.com/%e5%81%b7%e7%aa%a5%e7%95%8c%e7%9a%848%e5%a4%a7%e4%bc%a0%e5%a5%87%ef%bc%88%e5%8f%aa%e6%9c%89%e8%80%81%e5%a4%a7%e8%bf%98%e6%b2%a1%e8%bf%9b%e5%b1%80%e5%ad%90%ef%bc%89.html" rel="bookmark" target="_blank" title="详细阅读 偷窥界的8大传奇（只有老大还没进局子）">
      偷窥界的8大传奇（只有老大还没进局子）      </a></h2>
  </header>
  <!-- 摘要 -->
  <div class="excerpt">
    以下是偷窥界带有传奇色彩的8位“偷窥圣手”，他们思路缜密、器好活细，但全都折在了一些奇怪的巧合上……都别YY了，像下面这...  </div>
  <!-- 摘要end --> 
  <div class="info">
    <div class="up">
    <span><i class="icon-category"></i><a href="http://m.enrz.com/category/info/weird" rel="category tag">猎奇</a></span>
    <span><i class="icon-date"></i>2016-08-12</span>
        <a class="read-more right" href="http://m.enrz.com/%e5%81%b7%e7%aa%a5%e7%95%8c%e7%9a%848%e5%a4%a7%e4%bc%a0%e5%a5%87%ef%bc%88%e5%8f%aa%e6%9c%89%e8%80%81%e5%a4%a7%e8%bf%98%e6%b2%a1%e8%bf%9b%e5%b1%80%e5%ad%90%ef%bc%89.html" title="详细阅读 偷窥界的8大传奇（只有老大还没进局子）" rel="bookmark" target="_blank">阅读全文</a> </div>

      </div>
			 */
			try {
				Element globalnavElement = doc.select("div.content-wrap").first();
				Elements moduleElements = globalnavElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						MThumbBean sbean = new MThumbBean();
						try {
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
							
							/**
							 * <article class="crumbs-search wrapper" > 
    <!-- 面包屑 -->
    <div class="crumbs left">当前位置：<a href="http://m.enrz.com">首页</a> - 
    <a href="http://m.enrz.com/category/info">资讯</a> - 猎奇 (第 2 页)</div>
							 */
							try {
								Element articleElement = doc.select("article.crumbs-search").first();
								if (articleElement != null) {
									String crumbs = articleElement.select("div.crumbs").first().text();
									Log.i(TAG, "i==" + i + ";crumbs==" + crumbs);
									sbean.setCrumbstitle(crumbs);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element aElement = moduleElements.get(i).select("a").first();
								if (aElement != null) {
									String titlea = aElement.attr("title");
									Log.i(TAG, "i==" + i + ";titlea==" + titlea);
									sbean.setTitle(titlea);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element imgElement = moduleElements.get(i).select("img").first();
								if (imgElement != null) {
									String dataoriginal = imgElement.attr("data-original");
									Log.i(TAG, "i==" + i + ";dataoriginal==" + dataoriginal);
									sbean.setDataoriginal(dataoriginal);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element excerptElement = moduleElements.get(i).select("div.excerpt").first();
								if (excerptElement != null) {
									String excerpt = excerptElement.text();
									Log.i(TAG, "i==" + i + ";excerpt==" + excerpt);
									sbean.setExcerpt(excerpt);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element infoElement = moduleElements.get(i).select("div.info").first();
								if (infoElement != null) {
									String info = infoElement.text().replace("阅读全文", "");
									Log.i(TAG, "i==" + i + ";info==" + info);
									sbean.setInfo(info);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element infoElement = moduleElements.get(i).select("div.info").first();
								if (infoElement != null) {
									String category = infoElement.select("a").first().attr("href");
									Log.i(TAG, "i==" + i + ";category==" + category);
									sbean.setCategoryhref(category);
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
	
	public static List<MThumbBean> parseMSearch(String href,String search,int pageno) {
		List<MThumbBean> list = new ArrayList<MThumbBean>();
		try {
			//http://m.enrz.com/page/2?s=av
			//http://m.enrz.com/?s=av
			if(pageno>1){
				href = href + "/page/"+pageno+ "?s="+search;
			}else{
				href = href + "?s="+search;
			}
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			/**
			 * <div class="post-thumb">
    <a href="http://m.enrz.com/%e5%81%b7%e7%aa%a5%e7%95%8c%e7%9a%848%e5%a4%a7%e4%bc%a0%e5%a5%87%ef%bc%88%e5%8f%aa%e6%9c%89%e8%80%81%e5%a4%a7%e8%bf%98%e6%b2%a1%e8%bf%9b%e5%b1%80%e5%ad%90%ef%bc%89.html" target="_blank" title="偷窥界的8大传奇（只有老大还没进局子）"><img src="http://m.enrz.com/wp-content/themes/salong/includes/functions/timthumb.php?src=http://m.enrz.com/wp-content/themes/salong/images/thumb_loading.jpg&amp;h=225&amp;w=400&amp;zc=1" data-original="http://m.enrz.com/wp-content/themes/salong/includes/functions/timthumb.php?src=http://m.enrz.com/wp-content/uploads/2016/06/偷窥题图.png&amp;h=225&amp;w=400&amp;zc=1" alt="偷窥界的8大传奇（只有老大还没进局子）" /></a>  </div>
  <header class="post-head">
    <h2><a href="http://m.enrz.com/%e5%81%b7%e7%aa%a5%e7%95%8c%e7%9a%848%e5%a4%a7%e4%bc%a0%e5%a5%87%ef%bc%88%e5%8f%aa%e6%9c%89%e8%80%81%e5%a4%a7%e8%bf%98%e6%b2%a1%e8%bf%9b%e5%b1%80%e5%ad%90%ef%bc%89.html" rel="bookmark" target="_blank" title="详细阅读 偷窥界的8大传奇（只有老大还没进局子）">
      偷窥界的8大传奇（只有老大还没进局子）      </a></h2>
  </header>
  <!-- 摘要 -->
  <div class="excerpt">
    以下是偷窥界带有传奇色彩的8位“偷窥圣手”，他们思路缜密、器好活细，但全都折在了一些奇怪的巧合上……都别YY了，像下面这...  </div>
  <!-- 摘要end --> 
  <div class="info">
    <div class="up">
    <span><i class="icon-category"></i><a href="http://m.enrz.com/category/info/weird" rel="category tag">猎奇</a></span>
    <span><i class="icon-date"></i>2016-08-12</span>
        <a class="read-more right" href="http://m.enrz.com/%e5%81%b7%e7%aa%a5%e7%95%8c%e7%9a%848%e5%a4%a7%e4%bc%a0%e5%a5%87%ef%bc%88%e5%8f%aa%e6%9c%89%e8%80%81%e5%a4%a7%e8%bf%98%e6%b2%a1%e8%bf%9b%e5%b1%80%e5%ad%90%ef%bc%89.html" title="详细阅读 偷窥界的8大传奇（只有老大还没进局子）" rel="bookmark" target="_blank">阅读全文</a> </div>

      </div>
			 */
			try {
				Element globalnavElement = doc.select("div.content-wrap").first();
				Elements moduleElements = globalnavElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						MThumbBean sbean = new MThumbBean();
						try {
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
							
							/**
							 * <article class="crumbs-search wrapper" > 
    <!-- 面包屑 -->
    <div class="crumbs left">当前位置：<a href="http://m.enrz.com">首页</a> - 
    <a href="http://m.enrz.com/category/info">资讯</a> - 猎奇 (第 2 页)</div>
							 */
							try {
								Element articleElement = doc.select("article.crumbs-search").first();
								if (articleElement != null) {
									String crumbs = articleElement.select("div.crumbs").first().text();
									Log.i(TAG, "i==" + i + ";crumbs==" + crumbs);
									sbean.setCrumbstitle(crumbs);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element aElement = moduleElements.get(i).select("a").first();
								if (aElement != null) {
									String titlea = aElement.attr("title");
									Log.i(TAG, "i==" + i + ";titlea==" + titlea);
									sbean.setTitle(titlea);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element imgElement = moduleElements.get(i).select("img").first();
								if (imgElement != null) {
									String dataoriginal = imgElement.attr("data-original");
									Log.i(TAG, "i==" + i + ";dataoriginal==" + dataoriginal);
									sbean.setDataoriginal(dataoriginal);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element excerptElement = moduleElements.get(i).select("div.excerpt").first();
								if (excerptElement != null) {
									String excerpt = excerptElement.text();
									Log.i(TAG, "i==" + i + ";excerpt==" + excerpt);
									sbean.setExcerpt(excerpt);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element infoElement = moduleElements.get(i).select("div.info").first();
								if (infoElement != null) {
									String info = infoElement.text().replace("阅读全文", "");
									Log.i(TAG, "i==" + i + ";info==" + info);
									sbean.setInfo(info);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element infoElement = moduleElements.get(i).select("div.info").first();
								if (infoElement != null) {
									String category = infoElement.select("a").first().attr("href");
									Log.i(TAG, "i==" + i + ";category==" + category);
									sbean.setCategoryhref(category);
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
