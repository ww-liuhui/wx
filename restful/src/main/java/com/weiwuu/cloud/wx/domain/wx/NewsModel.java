package com.weiwuu.cloud.wx.domain.wx;

import java.util.List;

/***
 * 图文消息模板
 * 
 * @author hui
 *
 */
public class NewsModel {
	// 多条图文消息信息，默认第一个item为大图
	private List<Article> articles;

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}