package com.weiwuu.cloud.wx.domain.wx.YuYi.flight;
/***
 * 航班信息详细数据
 * @author hui
 *
 */
public class FlightDetail {
	//航班号
	private String name;
	//航空公司
	private String complany;
	//全程距离
	private String airvoyage;
	//飞机年限
	private String airage;
	//飞机型号
	private String airmodel;
	//出发地
	private String start;
	//目的地
	private String end;
	//起飞城市国标三字码
	private String depcode;
	//降落城市国标三字码
	private String arrcode;
	//起飞城市国标四字码
	private String depcode4;
	//降落城市国标四字码
	private String arrcode4;
	//起飞机场名称
	private String depairport;
	//降落机场名称
	private String arrairport;
	//出发机场天气
	private String depweather;
	//到达机场天气
	private String arrweather;
	//起飞航站楼
	private String depterminal;
	//降落航站楼
	private String arrterminal;
	//飞机状态
	private String status;
	//计划起飞时间
	private String deptime;
	//计划到达时间
	private String arrtime;
	//预计起飞时间
	private String dexpected;
	//预计到达时间
	private String aexpected;
	//实时起飞时间
	private String dactual;
	//实时到达时间
	private String aactual;
	//有无餐食:1,有；0,无
	private String food;
	//起飞位置:经纬度
	private String deppoint;
	//降落位置:经纬度
	private String arrpoint;
	//当前位置
	private String nowpoint;
	//准点率
	private String ontimerate;
	//历史准点率
	private String ontimeratehistory;
	//飞行时间：分钟数
	private String flytime;
	//离开时间
	private String leavetime;
	//起飞状况
	private String depdelay;
	//降落zhuangk
	private String arrdelay;
	//起飞机场状况
	private String deptrafficstate;
	//起飞机场温度
	private String deptemperature;
	//降落机场状况
	private String arrtrafficstate;
	//降落机场温度
	private String arrtemperature;
	//起飞机场电话
	private String deptel;
	//降落机场电话
	private String arrtel;
	//航空公司电话
	private String airlinetel;
	//已经飞行
	private String onflight;
	//经停
	private String alljingting;
	//登机门
	private String boardinggate;
	//
	private String zjtable;
	//
	private String bagclaim;
	//航班描述
	private String infocontent;
	//
	private String vzdeptime;
	//
	private String zdinfocontent;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComplany() {
		return complany;
	}
	public void setComplany(String complany) {
		this.complany = complany;
	}
	public String getAirvoyage() {
		return airvoyage;
	}
	public void setAirvoyage(String airvoyage) {
		this.airvoyage = airvoyage;
	}
	public String getAirage() {
		return airage;
	}
	public void setAirage(String airage) {
		this.airage = airage;
	}
	public String getAirmodel() {
		return airmodel;
	}
	public void setAirmodel(String airmodel) {
		this.airmodel = airmodel;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getDepcode() {
		return depcode;
	}
	public void setDepcode(String depcode) {
		this.depcode = depcode;
	}
	public String getArrcode() {
		return arrcode;
	}
	public void setArrcode(String arrcode) {
		this.arrcode = arrcode;
	}
	public String getDepcode4() {
		return depcode4;
	}
	public void setDepcode4(String depcode4) {
		this.depcode4 = depcode4;
	}
	public String getArrcode4() {
		return arrcode4;
	}
	public void setArrcode4(String arrcode4) {
		this.arrcode4 = arrcode4;
	}
	public String getDepairport() {
		return depairport;
	}
	public void setDepairport(String depairport) {
		this.depairport = depairport;
	}
	public String getArrairport() {
		return arrairport;
	}
	public void setArrairport(String arrairport) {
		this.arrairport = arrairport;
	}
	public String getDepweather() {
		return depweather;
	}
	public void setDepweather(String depweather) {
		this.depweather = depweather;
	}
	public String getArrweather() {
		return arrweather;
	}
	public void setArrweather(String arrweather) {
		this.arrweather = arrweather;
	}
	public String getDepterminal() {
		return depterminal;
	}
	public void setDepterminal(String depterminal) {
		this.depterminal = depterminal;
	}
	public String getArrterminal() {
		return arrterminal;
	}
	public void setArrterminal(String arrterminal) {
		this.arrterminal = arrterminal;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeptime() {
		return deptime;
	}
	public void setDeptime(String deptime) {
		this.deptime = deptime;
	}
	public String getArrtime() {
		return arrtime;
	}
	public void setArrtime(String arrtime) {
		this.arrtime = arrtime;
	}
	public String getDexpected() {
		return dexpected;
	}
	public void setDexpected(String dexpected) {
		this.dexpected = dexpected;
	}
	public String getAexpected() {
		return aexpected;
	}
	public void setAexpected(String aexpected) {
		this.aexpected = aexpected;
	}
	public String getDactual() {
		return dactual;
	}
	public void setDactual(String dactual) {
		this.dactual = dactual;
	}
	public String getAactual() {
		return aactual;
	}
	public void setAactual(String aactual) {
		this.aactual = aactual;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public String getDeppoint() {
		return deppoint;
	}
	public void setDeppoint(String deppoint) {
		this.deppoint = deppoint;
	}
	public String getArrpoint() {
		return arrpoint;
	}
	public void setArrpoint(String arrpoint) {
		this.arrpoint = arrpoint;
	}
	public String getNowpoint() {
		return nowpoint;
	}
	public void setNowpoint(String nowpoint) {
		this.nowpoint = nowpoint;
	}
	public String getOntimerate() {
		return ontimerate;
	}
	public void setOntimerate(String ontimerate) {
		this.ontimerate = ontimerate;
	}
	public String getOntimeratehistory() {
		return ontimeratehistory;
	}
	public void setOntimeratehistory(String ontimeratehistory) {
		this.ontimeratehistory = ontimeratehistory;
	}
	public String getFlytime() {
		return flytime;
	}
	public void setFlytime(String flytime) {
		this.flytime = flytime;
	}
	public String getLeavetime() {
		return leavetime;
	}
	public void setLeavetime(String leavetime) {
		this.leavetime = leavetime;
	}
	public String getDepdelay() {
		return depdelay;
	}
	public void setDepdelay(String depdelay) {
		this.depdelay = depdelay;
	}
	public String getArrdelay() {
		return arrdelay;
	}
	public void setArrdelay(String arrdelay) {
		this.arrdelay = arrdelay;
	}
	public String getDeptrafficstate() {
		return deptrafficstate;
	}
	public void setDeptrafficstate(String deptrafficstate) {
		this.deptrafficstate = deptrafficstate;
	}
	public String getDeptemperature() {
		return deptemperature;
	}
	public void setDeptemperature(String deptemperature) {
		this.deptemperature = deptemperature;
	}
	public String getArrtrafficstate() {
		return arrtrafficstate;
	}
	public void setArrtrafficstate(String arrtrafficstate) {
		this.arrtrafficstate = arrtrafficstate;
	}
	public String getArrtemperature() {
		return arrtemperature;
	}
	public void setArrtemperature(String arrtemperature) {
		this.arrtemperature = arrtemperature;
	}
	public String getDeptel() {
		return deptel;
	}
	public void setDeptel(String deptel) {
		this.deptel = deptel;
	}
	public String getArrtel() {
		return arrtel;
	}
	public void setArrtel(String arrtel) {
		this.arrtel = arrtel;
	}
	public String getAirlinetel() {
		return airlinetel;
	}
	public void setAirlinetel(String airlinetel) {
		this.airlinetel = airlinetel;
	}
	public String getOnflight() {
		return onflight;
	}
	public void setOnflight(String onflight) {
		this.onflight = onflight;
	}
	public String getAlljingting() {
		return alljingting;
	}
	public void setAlljingting(String alljingting) {
		this.alljingting = alljingting;
	}
	public String getBoardinggate() {
		return boardinggate;
	}
	public void setBoardinggate(String boardinggate) {
		this.boardinggate = boardinggate;
	}
	public String getZjtable() {
		return zjtable;
	}
	public void setZjtable(String zjtable) {
		this.zjtable = zjtable;
	}
	public String getBagclaim() {
		return bagclaim;
	}
	public void setBagclaim(String bagclaim) {
		this.bagclaim = bagclaim;
	}
	public String getInfocontent() {
		return infocontent;
	}
	public void setInfocontent(String infocontent) {
		this.infocontent = infocontent;
	}
	public String getVzdeptime() {
		return vzdeptime;
	}
	public void setVzdeptime(String vzdeptime) {
		this.vzdeptime = vzdeptime;
	}
	public String getZdinfocontent() {
		return zdinfocontent;
	}
	public void setZdinfocontent(String zdinfocontent) {
		this.zdinfocontent = zdinfocontent;
	}
}
