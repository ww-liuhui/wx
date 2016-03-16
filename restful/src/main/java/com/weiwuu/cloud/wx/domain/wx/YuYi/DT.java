package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 时间
 * @author hui
 *
 */
public class DT {
	//类型
	private String type;
	//日期：格式：YYYY-MM-DD，默认是当天时间
	private String date;
	//date的原始字符串
	private String date_ori;
	//时间：24小时制，格式：HH:MM:SS，默认为00:00:00
	private String time;
	//Time的原始字符串
	private String time_ori;
	
	//结束日期：格式：YYYY-MM-DD，默认是当前时间
	private String end_date;
	//date的原始字符串
	private String end_date_ori;
	//结束时间：24小时制，格式：HH:MM:SS
	private String end_time;
	//Time的原始字符串
	private String end_time_ori;
	
	//重复标记：0000000
	//注：依次代表周日，周六，…，周一；1表示该天要重复，0表示不重复
	private String repeat;
	//date的原始字符串
	private String repeat_ori;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate_ori() {
		return date_ori;
	}

	public void setDate_ori(String date_ori) {
		this.date_ori = date_ori;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime_ori() {
		return time_ori;
	}

	public void setTime_ori(String time_ori) {
		this.time_ori = time_ori;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getEnd_date_ori() {
		return end_date_ori;
	}

	public void setEnd_date_ori(String end_date_ori) {
		this.end_date_ori = end_date_ori;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getEnd_time_ori() {
		return end_time_ori;
	}

	public void setEnd_time_ori(String end_time_ori) {
		this.end_time_ori = end_time_ori;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public String getRepeat_ori() {
		return repeat_ori;
	}

	public void setRepeat_ori(String repeat_ori) {
		this.repeat_ori = repeat_ori;
	}
	
}
