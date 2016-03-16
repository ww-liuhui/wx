package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 航班服务
 * @author hui
 *
 */
public class Flight {
	//航班号
	private String flight_no;
	//出发地
	private LOC start_loc;
	//目的地
	private LOC end_loc;
	//出发日期
	private DT start_date;
	//返回日期
	private DT end_date;
	//航空公司
	private String airline;
	//座位级别（默认无限制）：ECONOMY（经济舱）,BIZ（商务舱）,FIRST（头等舱）
	private String seat;
	//排序类型：0排序无要求（默认），1价格升序，2价格降序，3时间升序，4时间降序
	private int sort;
	
	//action
	private int action;
	//answer
	private String answer;
	//dialog
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
	public String getFlight_no() {
		return flight_no;
	}
	public void setFlight_no(String flight_no) {
		this.flight_no = flight_no;
	}
	public LOC getStart_loc() {
		return start_loc;
	}
	public void setStart_loc(LOC start_loc) {
		this.start_loc = start_loc;
	}
	public LOC getEnd_loc() {
		return end_loc;
	}
	public void setEnd_loc(LOC end_loc) {
		this.end_loc = end_loc;
	}
	public DT getStart_date() {
		return start_date;
	}
	public void setStart_date(DT start_date) {
		this.start_date = start_date;
	}
	public DT getEnd_date() {
		return end_date;
	}
	public void setEnd_date(DT end_date) {
		this.end_date = end_date;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
}
