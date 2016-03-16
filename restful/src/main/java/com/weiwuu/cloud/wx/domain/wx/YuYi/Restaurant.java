package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 餐馆服务
 * @author hui
 *
 */
public class Restaurant {
	//地点
	private LOC location;
	//餐馆名称
	private String name;
	//餐馆类型/菜系
	private String category;
	//菜名
	private String special;
	//价格（单位元），类别：NUM_PRICE(价格)
	private NUMBER price;
	//距离（单位米），类别：NUM_PADIUS(距离)
	private NUMBER radius;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
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
