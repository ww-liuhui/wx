package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 酒店服务
 * @author hui
 *
 */
public class Hotel {
	//地点
	private LOC location;
	//入住时间
	private DT start_date;
	//离开时间
	private DT end_date;
	//酒店名称
	private String name;
	//价格（单位元）
	private NUMBER price;
	//距离（单位米）
	private NUMBER radius;
	//等级：五星级酒店，四星级酒店，三星级酒店，青年旅社，经济型酒店，公寓式酒店
	private String level;
	//1（有wifi），0（无wifi）
	private int wifi;
	//房间类型：标准间，单人间，双人间，三人间
	private String roomtype;
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
	public DT getStart_date() {
		return start_date;
	}
	public void setStart_date(DT start_date) {
		this.start_date = start_date;
	}
	public DT getEnd_date() {
		return end_date;
	}
	public void setEnd_date(DT end_date) {
		this.end_date = end_date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getWifi() {
		return wifi;
	}
	public void setWifi(int wifi) {
		this.wifi = wifi;
	}
	public String getRoomtype() {
		return roomtype;
	}
	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
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
