package com.weiwuu.cloud.wx.domain.wx.YuYi;

/***
 * 网址服务
 * 
 * @author hui
 *
 */
public class Website {
	// 网址名
	private String name;
	// url
	private String url;
	// action
	private int action;
	// answer
	private String answer;
	// dialog
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
