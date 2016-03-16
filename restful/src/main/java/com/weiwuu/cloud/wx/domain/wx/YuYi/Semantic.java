package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 语义表示
 * @author hui
 *
 */
public class Semantic {
	//详细
	private Object details;
	//查询方式：SEARCH,普通查询；ROUTE，路线查询
	private String intent;
	
	public Object getDetails() {
		return details;
	}
	public void setDetails(Object details) {
		this.details = details;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
}
