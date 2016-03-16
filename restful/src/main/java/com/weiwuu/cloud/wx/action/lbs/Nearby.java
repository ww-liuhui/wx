package com.weiwuu.cloud.wx.action.lbs;


import java.sql.ResultSet;

/***
 * 实现周边搜索应用
 * 
 * @author hui
 *
 */
public class Nearby {
	/***
	 * 保存用户地理位置
	 * 
	 * @param
	 * @param openId
	 * @param lng
	 * @param lat
	 * @param bd_lng
	 * @param bd_lat
	 */
	public static void saveUserLocation(String openId, String lng, String lat,
			String bd_lng, String bd_lat) {

	}

	/***
	 * 获取用户最后一次发送的地理位置
	 * 
	 * @param openId
	 * @return
	 */
	public static UserLocation getLastLocation(String openId) {
		UserLocation userLocation = null;

		return userLocation;
	}
}
