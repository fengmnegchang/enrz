/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午2:35:22
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.enrz.bean;

/**
 ***************************************************************************************************************************************************************************** 
 * <li class="comment odd alt thread-even depth-1" id="li-comment-2208">
 * <article id="comment-2208" class="comment"> <footer class="comment-meta">
 * <cite class="comment-author vcard"> <span class="fn"> <a
 * href='http://t.qq.com/hg19860202' rel='external nofollow' class='url'>黄建辉</a>
 * </span> on <a rel="nofollow"
 * href="http://enrz.com/fhm/2015/05/21/2773.html#comment-2208"> <time pubdate
 * datetime="2016-04-22T20:51:45+00:00">2016年4月22日 at 下午8:51</time></a> <span
 * class="says">said:</span> </cite> </footer> <div class="comment-content">
 * <p>
 * 女神
 * </p>
 * </div> </article></li>
 * 
 * @author :fengguangjing
 * @createTime:2017-1-16下午2:35:22
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class CommentBean extends CommonBean {
	/**
	<li class="comment even thread-even depth-1" id="li-comment-214">
			<article id="comment-214" class="comment">
				<footer class="comment-meta">
					<cite class="comment-author vcard">
						<span class="fn"><a href='http://blog.iyaxi.com/' rel='external nofollow' class='url'>飞鸟</a></span> on <a rel="nofollow" href="http://enrz.com/fhm/2015/05/21/2773.html#comment-214"><time pubdate datetime="2015-05-26T22:41:00+00:00">2015年5月26日 at 下午10:41</time></a> <span class="says">said:</span>					</cite><!-- .comment-author .vcard -->
				</footer>
	
				<div class="comment-content"><p>OMG 大幂幂</p>
</div>
				
			</article><!-- #comment-## -->
		<ul class="children">
		<li class="comment odd alt depth-2" id="li-comment-2493">
			<article id="comment-2493" class="comment">
				<footer class="comment-meta">
					<cite class="comment-author vcard">
						<span class="fn">啦啦啦</span> on <a rel="nofollow" href="http://enrz.com/fhm/2015/05/21/2773.html#comment-2493"><time pubdate datetime="2016-07-07T10:20:08+00:00">2016年7月7日 at 上午10:20</time></a> <span class="says">said:</span>					</cite><!-- .comment-author .vcard -->
				</footer>
	
				<div class="comment-content"><p>跟omg有什么关系</p>
</div>
				
			</article><!-- #comment-## -->
		</li><!-- #comment-## -->
</ul><!-- .children -->
	 */
	private String content;
	private String author;
	private String authorhref;
	private String nofollowhref;
	private String datetime;
	
	private CommentBean children;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorhref() {
		return authorhref;
	}

	public void setAuthorhref(String authorhref) {
		this.authorhref = authorhref;
	}

	public String getNofollowhref() {
		return nofollowhref;
	}

	public void setNofollowhref(String nofollowhref) {
		this.nofollowhref = nofollowhref;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public CommentBean getChildren() {
		return children;
	}

	public void setChildren(CommentBean children) {
		this.children = children;
	}

}
