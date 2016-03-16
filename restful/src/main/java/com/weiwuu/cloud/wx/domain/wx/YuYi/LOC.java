package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 地点相关协议
 * @author hui
 *
 */
public class LOC {
	//大类型：“LOC”
	//LOC又细分为如下类别：LOC_COUNTRY、LOC_PROVINCE、LOC_CITY、LOC_TOWN、LOC_POI、NORMAL_POI。
	private String type;
	//国家
	private String country;
	//省全称，例如：广东省
	private String province;
	//省简称，例如：广东|粤
	private String province_simple;
	//市全称，例如：北京市
	private String city;
	//市简称，例如：北京
	private String city_simple;
	//县区全称，例如：海淀区
	private String town;
	//县区简称，例如：海淀
	private String town_simple;
	//poi详细地址
	private String poi;
	//原始地名串
	private String loc_ori;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvince_simple() {
		return province_simple;
	}
	public void setProvince_simple(String province_simple) {
		this.province_simple = province_simple;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity_simple() {
		return city_simple;
	}
	public void setCity_simple(String city_simple) {
		this.city_simple = city_simple;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getTown_simple() {
		return town_simple;
	}
	public void setTown_simple(String town_simple) {
		this.town_simple = town_simple;
	}
	public String getPoi() {
		return poi;
	}
	public void setPoi(String poi) {
		this.poi = poi;
	}
	public String getLoc_ori() {
		return loc_ori;
	}
	public void setLoc_ori(String loc_ori) {
		this.loc_ori = loc_ori;
	}
}
