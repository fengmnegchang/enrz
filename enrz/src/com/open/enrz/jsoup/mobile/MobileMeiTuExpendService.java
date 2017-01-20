package com.open.enrz.jsoup.mobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.open.enrz.bean.GnSubBean;
import com.open.enrz.bean.PicBean;
import com.open.enrz.bean.SlideBean;
import com.open.enrz.jsoup.CommonService;
import com.open.enrz.utils.UrlUtils;

public class MobileMeiTuExpendService extends CommonService {
	public static final String TAG = MobileMeiTuExpendService.class.getSimpleName();
	public static List<GnSubBean> parseExpend( String href) {
		List<GnSubBean> list = new ArrayList<GnSubBean>();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.enrzAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			GnSubBean gbean;
			List<PicBean> clist ;
			PicBean picbean;
			/**
			 * <div class="p6_1">
                    <div class="tit">
                        <h2>
                            <span>性感写真</span>
                            <span class="toChannel"><a href="/sexy/"></a></span>
                        </h2>
                    </div>
                    <div class="item">
                        <a class="top" href="http://meitu.enrz.com/enrzpic/2016/1129/207966.shtml" title="小嶋阳菜经典写真 S曲线勾人">
                            <img src="http://pic.enrz.cn/enrz/210x280/131/10/likoik4ZgZnw.jpg" alt="小嶋阳菜经典写真 S曲线勾人">
                            <span class="imgNum">15</span>
                        </a>
                        <a href="http://meitu.enrz.com/enrzpic/2016/1129/207966.shtml" class="title" title="小嶋阳菜经典写真 S曲线勾人">小嶋阳菜经典写真 S曲线勾人</a>
                    </div>
                </div>

			 */
			try {
				Elements moduleElements = doc.select("div.p6_1");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						try {
							gbean = new GnSubBean();
							Element aElement = moduleElements.get(i).select("div.tit").first();
							if (aElement != null) {
								String titlea = aElement.select("span").first().text();
								String hrefa = aElement.select("a").first().attr("href");
								Log.i(TAG, "i==" + i + ";titlea==" + titlea + ";hrefa==" + hrefa);
								gbean.setHref(UrlUtils.ENRZ_PIC_MOBILE+hrefa);
								gbean.setTarget(titlea);
								
								clist = new ArrayList<PicBean>();
								try {
									Element itemElement = moduleElements.get(i).select("div.item").first();
									if(itemElement!=null){
										try {
											//左边大图
											/**
											 * <a href="http://pic.enrz.com/2017/0106/215616.shtml" title="风韵美女私房露肩内衣居家性感写真" 
											 * target="_blank"><img src="http://pic.enrz.cn/enrz/300x400/132/372/liK2KdIloIIHI.jpg" 
											 * alt="风韵美女私房露肩内衣居家性感写真" width="300" height="400">
											 * <span>美女私房内衣居家性感写真</span></a>
											 */
											Element c3aElement = itemElement.select("a").first();
											picbean = new PicBean();
											
											String titlec3a = c3aElement.text();
											String hrefc3a = c3aElement.attr("href");
											Log.i(TAG, "i==" + i + ";titlec3a==" + titlec3a + ";hrefc3a==" + hrefc3a);
											picbean.setHref(hrefc3a);
											picbean.setTitle(titlec3a);
											
											Element imgElement = itemElement.select("img").first();
											String src = imgElement.attr("src");
											picbean.setSrc(src);
											clist.add(picbean);
										} catch (Exception e) {
											e.printStackTrace();
										}
										 
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								gbean.setClist(clist);
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
			//p2
			try {
				Elements moduleElements = doc.select("div.p2");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 1; i < moduleElements.size(); i++) {
						try {
							gbean = new GnSubBean();
							Element aElement = moduleElements.get(i).select("div.tit").first();
							if (aElement != null) {
								String titlea = aElement.select("span").first().text();
								String hrefa = aElement.attr("href");
								Log.i(TAG, "i==" + i + ";titlea==" + titlea + ";hrefa==" + hrefa);
								gbean.setHref(hrefa);
								gbean.setTarget(titlea);
								
								clist = new ArrayList<PicBean>();
								try {
									Elements itemElements = moduleElements.get(i).select("div.item");
									if(itemElements!=null && itemElements.size()>0){
										for(int j=0;j<itemElements.size();j++){
											try {
												//左边大图
												/**
												 * <a href="http://pic.enrz.com/2017/0106/215616.shtml" title="风韵美女私房露肩内衣居家性感写真" 
												 * target="_blank"><img src="http://pic.enrz.cn/enrz/300x400/132/372/liK2KdIloIIHI.jpg" 
												 * alt="风韵美女私房露肩内衣居家性感写真" width="300" height="400">
												 * <span>美女私房内衣居家性感写真</span></a>
												 */
												Element c3aElement = itemElements.get(j).select("a").first();
												picbean = new PicBean();
												
												String titlec3a = c3aElement.text();
												String hrefc3a = c3aElement.attr("href");
												Log.i(TAG, "i==" + i + ";titlec3a==" + titlec3a + ";hrefc3a==" + hrefc3a);
												picbean.setHref(hrefc3a);
												picbean.setTitle(titlec3a);
												
												Element imgElement = itemElements.get(j).select("img").first();
												String src = imgElement.attr("src");
												picbean.setSrc(src);
												clist.add(picbean);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
										 
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								gbean.setClist(clist);
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
			Element divElement = doc.select("div.p1").first();
			if(divElement!=null){
				try {
					/**
					 <div class="p1" id="wrapper">
                <ul id="photoAll" style="overflow: hidden;"> <li>
                        <a href="http://pic.enrz.com/2016/1213/210902.shtml" title="质感酮体与挑逗让人觉得心潮爆棚">
                            <img src="http://img1.enrz.cn/enrz/moudlepic/2162_module_images/201612/584fd193cd4d4_678.jpg" alt="质感酮体与挑逗让人觉得心潮爆棚">
                            <p class="desc">质感酮体与挑逗让人觉得心潮爆棚</p>
                        </a>
                    </li> <li>
                        <a href="http://pic.enrz.com/2016/1014/196602.shtml" title="徐冬冬：性感给了我勇气">
                            <img src="http://img1.enrz.cn/enrz/moudlepic/2162_module_images/201611/58183fa92cdb2_630.jpg" alt="徐冬冬：性感给了我勇气">
                            <p class="desc">徐冬冬：性感给了我勇气</p>
                        </a>
                    </li> <li>
                        <a href="http://pic.enrz.com/2016/0728/167075.shtml" title="俄罗斯人体大师镜头下的性感与情色">
                            <img src="http://img1.enrz.cn/enrz/moudlepic/2162_module_images/201608/57c5348391d83_733.jpg" alt="俄罗斯人体大师镜头下的性感与情色">
                            <p class="desc">俄罗斯人体大师镜头下的性感与情色</p>
                        </a>
                    </li></ul><div class="c_nav"><div id="paging" class="paging"><div id="photoAll_0" class="paging_dot_actived"></div><div id="photoAll_1" class="paging_dot"></div><div id="photoAll_2" class="paging_dot"></div></div></div>
            </div>

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
