package com.weiwuu.cloud.wx.domain.wx;

/***
 * 群发接口返回信息
 * 
 * @author hui
 *
 */
public class MassBackMsg {
	// 错误码,为0时成功
	private int errcode;
	// 错误信息
	private String errmsg;
	// 消息ID
	private String msg_id;

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

}
