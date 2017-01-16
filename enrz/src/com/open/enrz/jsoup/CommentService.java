package com.open.enrz.jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.open.enrz.bean.CommentBean;
import com.open.enrz.utils.UrlUtils;

public class CommentService extends CommonService {
	public static final String TAG = CommentService.class.getSimpleName();

	public static List<CommentBean> parseComment(String href) {
		List<CommentBean> list = new ArrayList<CommentBean>();
		try {

			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				Elements moduleElements = doc.select("li.depth-1");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						CommentBean bean = new CommentBean();
						try {
							/**
							 * <article id="comment-1586" class="comment">
							<footer class="comment-meta">
								<cite class="comment-author vcard">
									<span class="fn">
									<a href='http://t.qq.com/zj2417134218' rel='external nofollow' class='url'>冷斌</a>
									</span> on 
									<a rel="nofollow" href="http://enrz.com/fhm/2015/05/21/2773.html#comment-1586">
									<time pubdate datetime="2015-12-24T10:36:09+00:00">2015年12月24日 at 上午10:36</time></a> 
									<span class="says">said:</span>				
										</cite><!-- .comment-author .vcard -->
							</footer>
							<div class="comment-content"><p>看见这个骚人，就有种想自慰的冲动！</p>
			              </div>
						</article><!-- #comment-## -->
							 */
							try {
								Element fnElement = moduleElements.get(i).select("span.fn").first();
								if (fnElement != null) {
									Element aElement = fnElement.select("a").first();
									String hrefa = aElement.attr("href");
									Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
									bean.setAuthorhref(hrefa);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element fnElement = moduleElements.get(i).select("span.fn").first();
								if (fnElement != null) {
									Element aElement = fnElement.select("a").first();
									String author;
									if(aElement!=null){
										author = aElement.text();
									}else{
										author = fnElement.text();
									}
									Log.i(TAG, "i==" + i + ";author==" + author);
									bean.setAuthor(author);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element fnElement = moduleElements.get(i).select("span.fn").first();
								if (fnElement != null) {
									Element aElement = fnElement.nextElementSibling();
									String nofollowhref = aElement.attr("href");
									Log.i(TAG, "i==" + i + ";nofollowhref==" + nofollowhref);
									bean.setNofollowhref(nofollowhref);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								//<div class="comment-content"><p>看见这个骚人，就有种想自慰的冲动！</p>
								Element fnElement = moduleElements.get(i).select("div.comment-content").first();
								if (fnElement != null) {
									String content = fnElement.text();
									Log.i(TAG, "i==" + i + ";content==" + content);
									bean.setContent(content);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							//<time pubdate datetime="2015-12-24T10:36:09+00:00">2015年12月24日 at 上午10:36</time></a> 
							//<span class="says">said:</span>		
							try {
								Element fnElement = moduleElements.get(i).select("span.says").first();
								if (fnElement != null) {
									Element aElement = fnElement.previousElementSibling();
									String datetime = aElement.text().replace("at", "").replace("年", ".").replace("月", ".").replace("日", "");
									Log.i(TAG, "i==" + i + ";datetime==" + datetime);
									bean.setDatetime(datetime);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							//children
							/**
							 * <ul class="children">
							<li class="comment odd alt depth-2" id="li-comment-2493">
								<article id="comment-2493" class="comment">
									<footer class="comment-meta">
										<cite class="comment-author vcard">
											<span class="fn">啦啦啦</span> on 
											<a rel="nofollow" href="http://enrz.com/fhm/2015/05/21/2773.html#comment-2493">
											<time pubdate datetime="2016-07-07T10:20:08+00:00">
											2016年7月7日 at 上午10:20</time></a> 
											<span class="says">said:</span>					
											</cite><!-- .comment-author .vcard -->
									</footer>
									<div class="comment-content"><p>跟omg有什么关系</p>
								</article><!-- #comment-## -->
							</li><!-- #comment-## -->
							 */
							try {
								Element childrenElement = moduleElements.get(i).select("ul.children").first();
								if (childrenElement != null) {
									CommentBean cbean = new CommentBean();
									try {
										try {
											Element fnElement = childrenElement.select("span.fn").first();
											if (fnElement != null) {
												Element aElement = fnElement.select("a").first();
												String hrefa = aElement.attr("href");
												Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
												cbean.setAuthorhref(hrefa);
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
										try {
											Element timeElement = childrenElement.select("time").first();
											if (timeElement != null) {
												String datetime = timeElement.text().replace("at", "").replace("年", ".").replace("月", ".").replace("日", "");
												Log.i(TAG, "i==" + i + ";datetime==" + datetime);
												cbean.setDatetime(datetime);
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
										try {
											Element fnElement = childrenElement.select("span.fn").first();
											if (fnElement != null) {
												Element aElement = fnElement.select("a").first();
												String author;
												if(aElement!=null){
													author = aElement.text();
												}else{
													author = fnElement.text();
												}
												Log.i(TAG, "i==" + i + ";author==" + author);
												cbean.setAuthor(author);
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
									 
										//<div class="comment-content"><p>跟omg有什么关系</p>
										try {
											Element fnElement = childrenElement.select("div.comment-content").first();
											if (fnElement != null) {
												String content = fnElement.text();
												Log.i(TAG, "i==" + i + ";content==" + content);
												cbean.setContent(content);
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
										
										
									} catch (Exception e) {
										e.printStackTrace();
									}
									bean.setChildren(cbean);
								}
							} catch (Exception e) {
								e.printStackTrace();
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
