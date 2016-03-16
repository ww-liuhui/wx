package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 火车服务
 * @author hui
 *
 */
public class Train {
	//出发日期
	private DT start_date;
	//返回日期
	private DT end_date;
	//起点
	private LOC start_loc;
	//终点
	private LOC end_loc;
	//车次代码，比如：T43等
	private String code;
	//座位级别：YZ（硬座），RZ（软座），YW（硬卧），RW（软卧），YD（一等座），ED（二等座），TD（特等座）
	private String seat;
	//车次类型：G（高铁），D（动车），T（特快），K（快速），Z（直达），L（临时客车），P（普通）
	private String category;
	//类型：DC（单程），WF（往返）
	private String type;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
