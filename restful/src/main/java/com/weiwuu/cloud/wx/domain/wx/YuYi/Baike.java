package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 百科服务
 * @author hui
 *
 */
public class Baike {
	//百科关键词
	private String keyword;
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
