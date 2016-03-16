package com.weiwuu.cloud.wx.util.wx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 时间相关方法
 * @author hui
 *
 */
public class DateUtils
{
	/**
	* 日期转换成字符串
	* @param date 
	* @return str
	*/
	public static String dateToStr(Date date) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String str = format.format(date);
	   return str;
	} 

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date strToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	/**
	* 字符串转换成毫秒数
	* @param str
	* @return date
	*/
	public static long strToMill(String str) {
	   long mill = 0;
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
	    date = format.parse(str);
	    mill = date.getTime();
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return mill;
	}
}
