package com.open.enrz.jsoup.m;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.open.enrz.bean.m.MArticleBean;
import com.open.enrz.bean.m.MThumbBean;
import com.open.enrz.bean.m.MThumbCateBean;
import com.open.enrz.jsoup.CommonService;
import com.open.enrz.utils.UrlUtils;

public class MThumbCateService extends CommonService {
	public static final String TAG = MThumbCateService.class.getSimpleName();

	public static List<MThumbCateBean> parseMThumbCate(String href) {
		List<MThumbCateBean> list = new ArrayList<MThumbCateBean>();
		try {
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			/**
			 */
			try {
				Element globalnavElement = doc.select("section.cms").first();
				Elements moduleElements = globalnavElement.select("article.cate-post");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						MThumbCateBean bean = new MThumbCateBean();
						try {
							try {
								Element divElement = moduleElements.get(i).select("div.cate-title").first();
								if(divElement!=null){
									// <div class="cate-title"><i class="icon-cate"></i><a href="http://m.enrz.com/category/beauty/cover-girl" 
									//rel="category tag">封面明星</a></div>
									Element aElement =divElement.select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										String titlea = aElement.text();
										Log.i(TAG, "i==" + i + ";hrefa==" + hrefa+ ";titlea==" + titlea);
										bean.setHref(hrefa);
										bean.setTitle(titlea);
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							//post-thumb
							MThumbBean sbean = new MThumbBean();
							try {
								Element divElement = moduleElements.get(i).select("div.post-thumb").first();
								if(divElement!=null){
									try {
										Element aElement = divElement.select("a").first();
										if (aElement != null) {
											String hrefa = aElement.attr("href");
											Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
											sbean.setHref(hrefa);
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
									
									try {
										Element aElement = divElement.select("a").first();
										if (aElement != null) {
											String titlea = aElement.attr("title");
											Log.i(TAG, "i==" + i + ";titlea==" + titlea);
											sbean.setTitle(titlea);
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
									try {
										Element imgElement = divElement.select("img").first();
										if (imgElement != null) {
											String dataoriginal = imgElement.attr("data-original");
											Log.i(TAG, "i==" + i + ";dataoriginal==" + dataoriginal);
											sbean.setDataoriginal(dataoriginal);
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
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
								Element infoElement = moduleElements.get(i).select("section.cate-info").first();
								if (infoElement != null) {
									String info = infoElement.text().replace("阅读全文", "");
									Log.i(TAG, "i==" + i + ";info==" + info);
									sbean.setInfo(info);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element infoElement = moduleElements.get(i).select("section.cate-info").first();
								if (infoElement != null) {
									String category = infoElement.select("a").first().attr("href");
									Log.i(TAG, "i==" + i + ";category==" + category);
									sbean.setCategoryhref(category);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							bean.setThumb(sbean);
						} catch (Exception e) {
							e.printStackTrace();
						}
						//cate-list
						try {
							Element ulElement = moduleElements.get(i).select("ul.cate-list").first();
							if (ulElement != null) {
								Elements liElements = ulElement.select("li");
								if(liElements!=null && liElements.size()>0){
									List<MArticleBean> articlelist = new ArrayList<MArticleBean>();
									MArticleBean abean;
									try {
										for(int j=0;j<liElements.size();j++){
											abean = new MArticleBean();
											/**
											 * <li>
							        <h2><a href="http://m.enrz.com/%e5%8d%83%e7%8e%8b%e4%b9%8b%e7%8e%8b%ef%bc%88%e6%9c%ac%e6%96%87%e7%94%b1%e4%ba%8e%e5%b0%ba%e5%ba%a6%e5%a4%aa%e5%a4%a7%e8%a2%ab%e5%b0%81%e4%ba%86%e4%b8%80%e5%b9%b4%e5%8d%8a%ef%bc%89.html"
							         rel="bookmark" target="_blank" 
							         title="详细阅读 千王之王（本文由于“尺度”太大被封了一年半）">
							          千王之王（本文由于“尺度”太大被封了一年半）          </a> </h2>
							        <span class="cate-time">
							        2016-06-02        </span> </li>
											 */
											try {
												Element aElement = liElements.get(j).select("a").first();
												String hrefaa = aElement.attr("href");
												String titleaa = aElement.attr("title");
												Log.i(TAG, "j==" + j+ ";hrefaa==" + hrefaa+ ";titleaa==" + titleaa);
												abean.setHref(hrefaa);
												abean.setTitle(titleaa);
											} catch (Exception e) {
												e.printStackTrace();
											}
											
											try {
												Element aElement = liElements.get(j).select("span.cate-time").first();
												String cate = aElement.text();
												Log.i(TAG, "j==" + j+ ";cate==" + cate);
												abean.setCatetime(cate);
											} catch (Exception e) {
												e.printStackTrace();
											}
											
											articlelist.add(abean);
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
									bean.setArticlelist(articlelist);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						list.add(bean);
						
						
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
