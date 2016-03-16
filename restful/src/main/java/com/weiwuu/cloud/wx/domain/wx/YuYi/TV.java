package com.weiwuu.cloud.wx.domain.wx.YuYi;

/***
 * 电视节目服务
 * 
 * @author hui
 *
 */
public class TV {
	// 播放时间
	private DT datetime;
	// 电视台名称
	private String tv_name;
	// 电视频道名称
	private String tv_channel;
	// 节目名称
	private String show_name;
	// 节目类型
	private String category;
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

	public DT getDatetime() {
		return datetime;
	}

	public void setDatetime(DT datetime) {
		this.datetime = datetime;
	}

	public String getTv_name() {
		return tv_name;
	}

	public void setTv_name(String tv_name) {
		this.tv_name = tv_name;
	}

	public String getTv_channel() {
		return tv_channel;
	}

	public void setTv_channel(String tv_channel) {
		this.tv_channel = tv_channel;
	}

	public String getShow_name() {
		return show_name;
	}

	public void setShow_name(String show_name) {
		this.show_name = show_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
