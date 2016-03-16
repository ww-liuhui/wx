package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 股票服务
 * @author hui
 *
 */
public class Stock {
	//股票名称
	private String name;
	//标准股票代码
	private String code;
	//市场：sz,sh,hk,us
	private String category;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public DT getDatetime() {
		return datetime;
	}
	public void setDatetime(DT datetime) {
		this.datetime = datetime;
	}
}
