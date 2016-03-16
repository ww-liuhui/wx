package com.weiwuu.cloud.wx.domain.wx.YuYi.flight;

import java.util.ArrayList;

/***
 * 航班信息返回结果
 * @author hui
 *
 */
public class FlightResult {
	//	返回码:200，正常
	private String resultcode;
	//结果：Successed!----成功
	private String reason;
	//详细结果
	private ArrayList<FlightDetail> result;
	//错误码:0为正常
	private String error_code;
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public ArrayList<FlightDetail> getResult() {
		return result;
	}
	public void setResult(ArrayList<FlightDetail> result) {
		this.result = result;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
}
