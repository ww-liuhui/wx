package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 提醒服务
 * @author hui
 *
 */
public class Remind {
	//时间
	private DT datetime;
	//事件
	private String event;
	//类别：0提醒；1闹钟   注：提醒有具体事件，闹钟没有具体事件
	private int remind_type;
	//action
	private int action;
	//answer
	private String answer;
	//dialog
	private String dialog;
	
	public DT getDatetime() {
		return datetime;
	}
	public void setDatetime(DT datetime) {
		this.datetime = datetime;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public int getRemind_type() {
		return remind_type;
	}
	public void setRemind_type(int remind_type) {
		this.remind_type = remind_type;
	}
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
	
	
	
	
	
	
	
}
