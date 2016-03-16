package com.weiwuu.cloud.wx.util.wx;

import com.weiwuu.cloud.wx.action.lbs.UserLocation;
import com.weiwuu.cloud.wx.domain.wx.BaiduPlace;
import com.weiwuu.cloud.wx.entity.wx.resp.Article;
import com.weiwuu.cloud.wx.resource.GardenResource;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * 百度地图api操作类
 * 
 * @author hui
 *
 */
public class BaiDuMapUtil
{
	public static void main(String[] args) {
		
		try {
			BaiduPlace bp = nameToNum("昆明德瀛华府");
			System.out.println(bp.getLat()+"，"+bp.getLng());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//String result = httpRequest("https://wx2.qq.com/cgi-bin/mmwebwx-bin/webwxindex?t=chat");
		//System.out.println(result);
//		try {
//			List<BaiduPlace> list = searchPlace("美食", "102.68406584681",
//					"25.04847974672");
//			BaiduPlace bp = NameToNum("昆明市盘龙区东风东路99号");
//			List<BaiduPlace> list1 = numToName(bp.getLng(), bp.getLat());
//			UserLocation ul = convertCoord("102.676170", "25.045696");
//			System.out.println(ul.getBd_lng() + "," + ul.getBd_lat());
//
//			System.out.println(bp.getLng() + "," + bp.getLat() + ","
//					+ list1.get(0).getAddress() + ","
//					+ list1.get(0).getProvince() + "," + list1.get(0).getCity()
//					+ "," + list1.get(0).getDistrict() + ","
//					+ list1.get(0).getStreet());
//
//			double dis = distance("102.676170", "25.045696", "102.68406584681",
//					"25.04847974672");
//			System.out.println(dis);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

		// String result =
		// httpRequest("http://www.baidu.com/s?ie=utf-8&wd=hello");
		// System.out.println(result);
	}

	/***
	 * 两点之间的距离
	 * 
	 * @param lng1
	 *            经度
	 * @param lat1
	 *            纬度
	 * @param lng2
	 *            目标经度
	 * @param lat2
	 *            目标纬度
	 * @return
	 * @throws Exception
	 */
	public static double distance(String lng1, String lat1, String lng2,
			String lat2) throws Exception {
		double distance = 0;
		// 拼接请求地址
		String requestUrl = "http://api.map.baidu.com/telematics/v3/distance?waypoints=LNG1,LAT1;LNG2,LAT2&output=xml&ak=FD495fa61808c196bcc965675d67ac35";
		requestUrl = requestUrl.replace("LAT1", lat1).replace("LNG1", lng1)
				.replace("LAT2", lat2).replace("LNG2", lng2);
		// 调用百度api计算两点之间的距离
		String respXml = httpRequest(requestUrl);
		// 解析返回的XML
		try {
			Document document = DocumentHelper.parseText(respXml);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 从根元素获取<results>
			Element resultsElement = root.element("results");
			// 从result中获取distance
			Element distanceElement = resultsElement.element("distance");
			String dis = distanceElement.getText().trim();
			BigDecimal bg = new BigDecimal(dis);
			distance = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return distance;
	}

	/***
	 * 圆形区域检索
	 * 
	 * @param query
	 *            关键词
	 * @param lng
	 *            经度
	 * @param lat
	 *            纬度
	 * @return
	 * @throws Exception
	 */
	public static List<BaiduPlace> searchPlace(String query, String lng,
			String lat) throws Exception {
		// 拼接请求地址
		String requestUrl = "http://api.map.baidu.com/place/v2/search?&query=QUERY&location=LAT,LNG&radius=2000&output=xml&scope=2&page_size=10&page_num=0&ak=FD495fa61808c196bcc965675d67ac35";
		requestUrl = requestUrl.replace("QUERY", query).replace("LAT", lat)
				.replace("LNG", lng);
		// 调用Place api圆形区域检索
		String respXml = httpRequest(requestUrl);
		// 解析返回的XML
		List<BaiduPlace> baiduPlaces = parsePlaceXml(respXml);
		return baiduPlaces;
	}

	/***
	 * 地理编码：将地名转换为经纬度
	 * 
	 * @param query
	 *            关键词
	 * @return
	 * @throws Exception
	 */
	public static BaiduPlace nameToNum(String query) throws Exception {
		BaiduPlace bp = new BaiduPlace();
		// 拼接请求地址
		String requestUrl = "http://api.map.baidu.com/geocoder/v2/?address=QUERY&output=xml&ak=FD495fa61808c196bcc965675d67ac35";
		requestUrl = requestUrl.replace("QUERY", query);
		// 调用地理编码接口
		String respXml = httpRequest(requestUrl);
		// 解析返回的XML
		try {
			Document document = DocumentHelper.parseText(respXml);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 从根元素获取<result>
			Element resultElement = root.element("result");
			// 从result中获取location
			Element locationElement = resultElement.element("location");
			// 从location中获取lat
			Element latElement = locationElement.element("lat");
			// 从location中获取lng
			Element lngElement = locationElement.element("lng");
			bp.setLat(latElement.getText());
			bp.setLng(lngElement.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bp;
	}

	/***
	 * 反地理编码：将经纬度转换为地名
	 * 
	 * @param lng
	 *            经度
	 * @param lat
	 *            纬度
	 * @return
	 * @throws Exception
	 */
	public static List<BaiduPlace> numToName(String lng, String lat)
			throws Exception {
		List<BaiduPlace> baiduPlaces = new ArrayList<BaiduPlace>();
		// 拼接请求地址
		String requestUrl = "http://api.map.baidu.com/geocoder/v2/?ak=FD495fa61808c196bcc965675d67ac35&location=LAT,LNG&output=xml&pois=1";
		requestUrl = requestUrl.replace("LAT", lat).replace("LNG", lng);
		// 调用地理编码接口
		String respXml = httpRequest(requestUrl);
		// 解析返回的XML
		try {
			Document document = DocumentHelper.parseText(respXml);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 从根元素获取<result>
			Element resultElement = root.element("result");
			// 从result中获取addressComponent
			Element addressComponentElement = resultElement
					.element("addressComponent");
			// 从result中获取formatted_address
			Element formatted_addressElement = resultElement
					.element("formatted_address");

			// 从addressComponent中获取province
			Element provinceElement = addressComponentElement
					.element("province");
			// 从addressComponent中获取city
			Element cityElement = addressComponentElement.element("city");
			// 从addressComponent中获取district
			Element districtElement = addressComponentElement
					.element("district");
			// 从addressComponent中获取street
			Element streetElement = addressComponentElement.element("street");

			// 从result中获取pois
			Element poisElement = resultElement.element("pois");
			// 从pois中获取poi集合
			List<Element> poiElementList = poisElement.elements("poi");
			// 判断poiElementList集合的大小
			if (poiElementList.size() > 0) {
				// POI名称
				Element nameElement = null;
				// POI地址信息
				Element addressElement = null;
				// POI经纬度信息
				Element locationElement = null;
				// POI电话信息
				Element teleElement = null;
				// 距离中心点的距离
				Element distanceElement = null;

				// 遍历result集合
				for (Element poiElement : poiElementList) {
					nameElement = poiElement.element("name");
					addressElement = poiElement.element("addr");
					locationElement = poiElement.element("point");
					teleElement = poiElement.element("tel");
					distanceElement = poiElement.element("distance");

					BaiduPlace place = new BaiduPlace();
					place.setName(nameElement.getText());
					place.setAddress(addressElement.getText());
					place.setLng(locationElement.element("x").getText());
					place.setLat(locationElement.element("y").getText());
					place.setDistance(Integer.parseInt(distanceElement
							.getText()));

					place.setProvince(provinceElement.getText());
					place.setCity(cityElement.getText());
					place.setDistrict(districtElement.getText());
					place.setStreet(streetElement.getText());

					// 当telephone元素存在时获取电话号码
					if (null != teleElement) {
						place.setTelephone(teleElement.getText());
					}
					baiduPlaces.add(place);
				}
				// 按距离由近及远排序
				Collections.sort(baiduPlaces);
			} else {// 如果没有poi，则只提供所在省市
				BaiduPlace place = new BaiduPlace();
				place.setAddress(formatted_addressElement.getText());
				place.setProvince(provinceElement.getText());
				place.setCity(cityElement.getText());
				place.setDistrict(districtElement.getText());
				place.setStreet(streetElement.getText());

				baiduPlaces.add(place);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return baiduPlaces;
	}

	/***
	 * 发送http请求
	 * 
	 * @param requestUrl
	 * @return
	 */
	public static String httpRequest(String requestUrl) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.connect();

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpURLConnection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpURLConnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	/***
	 * 根据百度地图返回的流解析出地址信息
	 * 
	 * @param xml
	 * @return
	 */
	private static List<BaiduPlace> parsePlaceXml(String xml) {
		List<BaiduPlace> baiduPlaces = null;
		try {
			Document document = DocumentHelper.parseText(xml);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 从根元素获取<results>
			Element resultsElement = root.element("results");
			// 从results中获取result集合
			List<Element> resultElementList = resultsElement.elements("result");
			// 判断result集合的大小
			if (resultElementList.size() > 0) {
				baiduPlaces = new ArrayList<BaiduPlace>();
				// POI名称
				Element nameElement = null;
				// POI地址信息
				Element addressElement = null;
				// POI经纬度信息
				Element locationElement = null;
				// POI电话信息
				Element teleElement = null;
				// POI扩展信息
				Element detailInfoElement = null;
				// 距离中心点的距离
				Element distanceElement = null;

				// 遍历result集合
				for (Element resultElement : resultElementList) {
					nameElement = resultElement.element("name");
					addressElement = resultElement.element("address");
					locationElement = resultElement.element("location");
					teleElement = resultElement.element("telephone");
					detailInfoElement = resultElement.element("detail_info");

					BaiduPlace place = new BaiduPlace();
					place.setName(nameElement.getText());
					place.setAddress(addressElement.getText());
					place.setLng(locationElement.element("lng").getText());
					place.setLat(locationElement.element("lat").getText());

					// 当telephone元素存在时获取电话号码
					if (null != teleElement) {
						place.setTelephone(teleElement.getText());
					}
					// 当detail-info存在时获取distance
					if (null != detailInfoElement) {
						distanceElement = detailInfoElement.element("distance");
						if (null != distanceElement) {
							place.setDistance(Integer.parseInt(distanceElement
									.getText()));
						}
					}
					baiduPlaces.add(place);
				}
				// 按距离由近及远排序
				Collections.sort(baiduPlaces);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baiduPlaces;
	}

	/***
	 * 根据place组装图文列表
	 * 
	 * @param placeList
	 * @param bd_lng
	 *            经度
	 * @param bd_lat
	 *            纬度
	 * @return
	 */
	public static List<Article> makeArticleList(List<BaiduPlace> placeList,
			String bd_lng, String bd_lat) {
		// 项目的根路径
		String basePath = "http://wx.weiwuu.com";
		List<Article> list = new ArrayList<>();
		BaiduPlace place = null;
		int id = 0;
		for (int i = 0; i < placeList.size(); i++) {
			id = 0;
			//根据楼盘名称，获取楼盘信息

			place = placeList.get(i);
			Article article = new Article();
			article.setTitle("楼盘："+place.getName() + "\n距离：" + place.getDistance()
					+ "米" + "\n地址：" + place.getAddress());


			article.setUrl(basePath+"/detail.html?id="+id);
			// 将首条图文的图片设置为大图
			if (i == 0) {
				article.setPicUrl(basePath + "images/hhjs.jpg");
			} else {
				article.setPicUrl("");
			}
			list.add(article);
		}
		return list;
	}

	/***
	 * 将微信定位的坐标转换为百度坐标
	 * 
	 * @param lng
	 * @param lat
	 * @return
	 */
	public static UserLocation convertCoord(String lng, String lat) {
		// 百度坐标转化接口
		String convertUrl = "http://api.map.baidu.com/geoconv/v1/?coords=LNG,LAT&output=xml&from=1&ak=FD495fa61808c196bcc965675d67ac35";
		convertUrl = convertUrl.replace("LNG", lng).replace("LAT", lat);

		UserLocation location = new UserLocation();
		try {
			String respXml = httpRequest(convertUrl);

			// 解析返回的XML
			Document document = DocumentHelper.parseText(respXml);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 从根元素获取<result>
			Element resultElement = root.element("result");
			// 从result中获取point
			Element pointElement = resultElement.element("point");
			// 从point中获取x
			Element xElement = pointElement.element("x");
			// 从point中获取y
			Element yElement = pointElement.element("y");
			location.setBd_lng(xElement.getText());
			location.setBd_lat(yElement.getText());

		} catch (Exception e) {
			location = null;
			e.printStackTrace();
		}
		return location;
	}
}
