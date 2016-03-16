package com.weiwuu.cloud.wx.domain.wx;

import java.util.List;

/***
 * 根据OpenId群发
 * 
 * @author hui
 *
 */
public class OpenIdMassNews {
	// 图文消息的接收者
	private List<String> touser;
	// 即将发送的图文消息
	private Mpnews mpnews;
	// 群发的消息类型，图文消息为mpnews
	private String msgtype;

	public List<String> getTouser() {
		return touser;
	}

	public void setTouser(List<String> touser) {
		this.touser = touser;
	}

	public Mpnews getMpnews() {
		return mpnews;
	}

	public void setMpnews(Mpnews mpnews) {
		this.mpnews = mpnews;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
}
