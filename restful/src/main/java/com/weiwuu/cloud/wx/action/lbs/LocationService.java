package com.weiwuu.cloud.wx.action.lbs;


import com.weiwuu.cloud.wx.util.wx.MessageUtil;

/***
 * 根据地理位置查找周边信息
 * 
 * @author hui
 *
 */
public class LocationService {
	/***
	 * 
	 * @return
	 */
	public static String getLocationUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(MessageUtil.emoji(0xe148)).append("周边搜索使用指南")
				.append("\n\n");
		buffer.append("1.发送地理位置").append("\n");
		buffer.append("2.发送关键词：附近美食").append("\n");

		return buffer.toString();
	}
}
