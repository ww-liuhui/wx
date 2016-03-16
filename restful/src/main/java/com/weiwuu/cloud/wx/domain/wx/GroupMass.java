package com.weiwuu.cloud.wx.domain.wx;

/***
 * 根据分组群发
 * 
 * @author hui
 *
 */
public class GroupMass
{
	// 图文消息的接收者
	private Filter filter;
	// 即将发送的图文消息
	private Mpnews mpnews;
	// 群发的消息类型，图文消息为mpnews
	private String msgtype;

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
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
