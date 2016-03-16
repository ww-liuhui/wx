package com.weiwuu.cloud.wx.entity.wx.resp;

/***
 * 发送给指定客服的消息
 * 
 * @author hui
 *
 */
public class CustomerMessage extends BaseMessage {
	// 指定会话接入的客服账号
	private TransInfo transInfo;

	public TransInfo getTransInfo() {
		return transInfo;
	}

	public void setTransInfo(TransInfo transInfo) {
		this.transInfo = transInfo;
	}

}