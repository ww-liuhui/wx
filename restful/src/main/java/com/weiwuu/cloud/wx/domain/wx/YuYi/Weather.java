package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 天气服务
 * @author hui
 *
 */
public class Weather {
	//地点
	private LOC location;
	//时间
	private DT datetime;
	
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
	public DT getDatetime() {
		return datetime;
	}
	public void setDatetime(DT datetime) {
		this.datetime = datetime;
	}
}
