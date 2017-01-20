package com.open.enrz.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.open.enrz.bean.PicBean;
import com.open.enrz.bean.SlideBean;
import com.open.enrz.utils.UrlUtils;

public class PicService extends CommonService {
	public static final String TAG = PicService.class.getSimpleName();

	public static List<PicBean> parsePic(String href,int pagerno) {
		List<PicBean> list = new ArrayList<PicBean>();
		try {

			if(pagerno>1){
				href = href +pagerno+".shtml";
			}
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				Element ulElement = doc.select("ul.li_lu_small").first();
				if(ulElement==null ){
					/**
					 * <li>
						<div class="item">
                            <a class="top" href="http://meitu.enrz.com/enrzpic/2017/0106/215616.shtml" title="风韵美女私房露肩内衣居家性感写真">
                                <img src="http://pic.enrz.cn/enrz/300x400/132/372/liK2KdIloIIHI.jpg" alt="风韵美女私房露肩内衣居家性感写真"/>
                            </a>
                        </div>
					</li>

					 */
					ulElement  = doc.select("ul.log_list").first();
				}
				Elements moduleElements = ulElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						PicBean bean = new PicBean();
						try {
							/**
							  <li>
                <a href="http://pic.enrz.com/2017/0106/215616.shtml" title="风韵美女私房露肩内衣居家性感写真" target="_blank">
                    <img width=185 height=185 src="http://pic.enrz.cn/enrz/185x185/132/372/liK2KdIloIIHI.jpg" alt="">
                    <span>风韵美女私房露肩内衣居家性感写真</span>
                </a>
            </li>
							 */
							try {
								Element aElement = moduleElements.get(i).select("a").first();
								if (aElement != null) {
									String hrefa = aElement.attr("href");
									Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
									bean.setHref(hrefa);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element aElement = moduleElements.get(i).select("a").first();
								if (aElement != null) {
									String titlea = aElement.attr("title");
									Log.i(TAG, "i==" + i + ";titlea==" + titlea);
									bean.setTitle(titlea);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element spanElement = moduleElements.get(i).select("span").first();
								if (spanElement != null) {
									String span = spanElement.text();
									Log.i(TAG, "i==" + i + ";span==" + span);
									bean.setSpan(span);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element imgElement = moduleElements.get(i).select("img").first();
								if (imgElement != null) {
									String src = imgElement.attr("src");
									Log.i(TAG, "i==" + i + ";src==" + src);
									bean.setSrc(src);
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

	public static List<SlideBean> parseHead(String href) {
		List<SlideBean> list = new ArrayList<SlideBean>();
		href = makeURL(href, new HashMap<String, Object>() {
			{
			}
		});
		Log.i(TAG, "url = " + href);

		try {
			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			Element divElement = doc.select("div.li_r").first();
			if(divElement!=null){
				try {
					/**
					 *  <li>
					<span class="o1"></span>
					<div>
						<a href="http://pic.enrz.com/2016/1014/196602.shtml" target="_blank"
						 title="徐冬冬：性感给了我勇气"><img src="http://pic.enrz.cn/enrz/120x120/131/290/livEysEnX8ZvU.jpg" 
						 alt="徐冬冬：性感给了我勇气" width=120 height=120>
						 <span>徐冬冬：性感给了我勇气</span></a>
					</div>
				</li>
					 */
					Elements  liElements = divElement.select("li");
					if(liElements!=null && liElements.size()>0){
						SlideBean bean;
						for(int i=0;i<liElements.size();i++){
							bean = new SlideBean();
							try {
								Element aElement = liElements.get(i).select("a").first();
								String hrefa = aElement.attr("href");
								Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
								bean.setHref(hrefa);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element aElement = liElements.get(i).select("a").first();
								String titlea = aElement.attr("title");
								Log.i(TAG, "i==" + i + ";titlea==" + titlea);
								bean.setSt_ty(titlea);
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								Element aElement = liElements.get(i).select("img").first();
								String src = aElement.attr("src");
								Log.i(TAG, "i==" + i + ";src==" + src);
								bean.setSrc(src);
							} catch (Exception e) {
								e.printStackTrace();
							}
							list.add(bean);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<SlideBean> parseExpendHead(String href) {
		List<SlideBean> list = new ArrayList<SlideBean>();
		href = makeURL(href, new HashMap<String, Object>() {
			{
			}
		});
		Log.i(TAG, "url = " + href);

		try {
			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			Element divElement = doc.select("div.c1_slide").first();
			if(divElement!=null){
				try {
					/**
					 <li><a title="质感酮体与挑逗让人觉得心潮爆棚"
					  href="http://pic.enrz.com/2016/1213/210902.shtml" target="_blank">
					  <img src="http://img1.enrz.cn/enrz/moudlepic/2162_module_images/201612/584fd11db08bd_446.jpg" 
					 alt="质感酮体与挑逗让人觉得心潮爆棚"></a><span>质感酮体与挑逗让人觉得心潮爆棚</span></li>
					 */
					Elements  liElements = divElement.select("li");
					if(liElements!=null && liElements.size()>0){
						SlideBean bean;
						for(int i=0;i<liElements.size();i++){
							bean = new SlideBean();
							try {
								Element aElement = liElements.get(i).select("a").first();
								String hrefa = aElement.attr("href");
								Log.i(TAG, "i==" + i + ";hrefa==" + hrefa);
								bean.setHref(hrefa);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								Element aElement = liElements.get(i).select("a").first();
								String titlea = aElement.attr("title");
								Log.i(TAG, "i==" + i + ";titlea==" + titlea);
								bean.setSt_ty(titlea);
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								Element aElement = liElements.get(i).select("img").first();
								String src = aElement.attr("src");
								Log.i(TAG, "i==" + i + ";src==" + src);
								bean.setSrc(src);
							} catch (Exception e) {
								e.printStackTrace();
							}
							list.add(bean);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
