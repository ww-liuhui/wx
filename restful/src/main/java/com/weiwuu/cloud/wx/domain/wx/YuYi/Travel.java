package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 旅游服务
 * 
 * intent取值：SEARCH，普通查询；PRICE，价格查询；GUIDE，攻略查询
 * @author hui
 *
 */
public class Travel {
	//旅游目的地
	private LOC location;
	//景点名称
	private String spot;
	//旅游日期
	private DT datetime;
	//旅游类型词
	private String tag;
	//0默认，1自由行，2跟团游
	private int category;
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
	public String getSpot() {
		return spot;
	}
	public void setSpot(String spot) {
		this.spot = spot;
	}
	public DT getDatetime() {
		return datetime;
	}
	public void setDatetime(DT datetime) {
		this.datetime = datetime;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
}
