package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 数字相关协议
 * @author hui
 *
 */
public class NUMBER {
	//大类型：“NUMBER”
	//NUMBER又细分为如下类别：NUM_PRICE(价格)、NUM_PADIUS(距离)、NUM_DISCOUNT(折扣)、NUM_SEASON（部，季）、NUM_EPI（集相关)、NUM_CHAPTER(章节相关)。
	private String type;
	//开始,如果为“-1”表示无上限或者下限，如果为“-2”，表示该字段无信息。
	private String begin;
	//结束,如果为“-1”表示无上限或者下限，如果为“-2”，表示该字段无信息。
	private String end;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
}
