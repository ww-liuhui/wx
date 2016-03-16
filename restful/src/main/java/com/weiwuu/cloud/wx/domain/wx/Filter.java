package com.weiwuu.cloud.wx.domain.wx;

/***
 * 图文消息的接收者
 * 
 * @author hui
 *
 */
public class Filter {
	// 图文信息的接收者
	private String group_id;

	public Filter() {
	}

	public Filter(String group_id) {
		super();
		this.group_id = group_id;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
}
