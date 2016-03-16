package com.weiwuu.cloud.wx.action.lbs;

/***
 * 用户地理位置模型
 * 
 * @author hui
 *
 */
public class UserLocation {
	private String openId;
	private String lng;
	private String lat;
	private String bd_lng;
	private String bd_lat;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getBd_lng() {
		return bd_lng;
	}

	public void setBd_lng(String bd_lng) {
		this.bd_lng = bd_lng;
	}

	public String getBd_lat() {
		return bd_lat;
	}

	public void setBd_lat(String bd_lat) {
		this.bd_lat = bd_lat;
	}
}
