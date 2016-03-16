package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 周边服务
 * @author hui
 *
 */
public class Nearby {
	//地点
	private LOC location;
	//关键词
	private String keyword;
	//限定词
	private String limit;
	//价格（单位元）
	private NUMBER price;
	//距离（单位米）
	private NUMBER radius;
	//是否是服务类：0不是（默认），1是
	private int service;
	//优惠信息：0无（默认），1优惠券，2团购
	private int coupon;
	//排序类型：0距离（默认），1点评高优先级，2服务质量高优先级，3环境高优先级，4价格高到低，5价格低到高
	private int sort;
	//action
	private int action;
	//answer
	private String answer;
	//dialog
	private String dialog;
	
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getDialog() {
		return dialog;
	}
	public void setDialog(String dialog) {
		this.dialog = dialog;
	}
	public LOC getLocation() {
		return location;
	}
	public void setLocation(LOC location) {
		this.location = location;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public NUMBER getPrice() {
		return price;
	}
	public void setPrice(NUMBER price) {
		this.price = price;
	}
	public NUMBER getRadius() {
		return radius;
	}
	public void setRadius(NUMBER radius) {
		this.radius = radius;
	}
	public int getService() {
		return service;
	}
	public void setService(int service) {
		this.service = service;
	}
	public int getCoupon() {
		return coupon;
	}
	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
}