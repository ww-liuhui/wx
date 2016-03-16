package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 地图服务
 * @author hui
 *
 */
public class Map {
	//起点区域
	private LOC start_area;
	//起点位置
	private LOC start_loc;
	//终点区域
	private LOC end_area;
	//终点位置
	private LOC end_loc;
	//出行方式：walk（步行）, taxi（打车）, bus（公交）, subway（地铁）, drive（自驾）
	private String route_type;
	//公交车号
	private int bus_num;
	//地铁线
	private String subway_num;
	//排序类型：0较快捷（默认），1少换乘，2少步行
	private int type;
	//关键词
	private String keyword;
	
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
	public LOC getStart_area() {
		return start_area;
	}
	public void setStart_area(LOC start_area) {
		this.start_area = start_area;
	}
	public LOC getStart_loc() {
		return start_loc;
	}
	public void setStart_loc(LOC start_loc) {
		this.start_loc = start_loc;
	}
	public LOC getEnd_area() {
		return end_area;
	}
	public void setEnd_area(LOC end_area) {
		this.end_area = end_area;
	}
	public LOC getEnd_loc() {
		return end_loc;
	}
	public void setEnd_loc(LOC end_loc) {
		this.end_loc = end_loc;
	}
	public String getRoute_type() {
		return route_type;
	}
	public void setRoute_type(String route_type) {
		this.route_type = route_type;
	}
	public int getBus_num() {
		return bus_num;
	}
	public void setBus_num(int bus_num) {
		this.bus_num = bus_num;
	}
	public String getSubway_num() {
		return subway_num;
	}
	public void setSubway_num(String subway_num) {
		this.subway_num = subway_num;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
