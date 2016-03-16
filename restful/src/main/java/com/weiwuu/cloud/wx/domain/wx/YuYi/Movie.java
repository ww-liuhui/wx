package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 电影服务
 * @author hui
 *
 */
public class Movie {
	//电影名
	private String name;
	//主演
	private String actor;
	//导演
	private String director;
	//类型：动作片，剧情片，…
	private String tag;
	//地区：美国，大陆，香港，…
	private String country;
	//电影院
	private String cinema;
	//地点
	private LOC location;
	//时间
	private DT datetime;
	//优惠信息：0无（默认），1优惠券，2团购
	private int coupon;
	//排序类型：0排序无要求（默认），1评价高优先级
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCinema() {
		return cinema;
	}
	public void setCinema(String cinema) {
		this.cinema = cinema;
	}
	public LOC getLocation() {
		return location;
	}
	public void setLocation(LOC location) {
		this.location = location;
	}
	public DT getDatetime() {
		return datetime;
	}
	public void setDatetime(DT datetime) {
		this.datetime = datetime;
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
