package com.weiwuu.cloud.wx.action.face;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 人脸检测服务
 * 
 * @author hui
 * @date 2015-11-18
 */
public class FaceService {
	/**
	 * 发送http请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @return String
	 */
	private static String httpRequest(String requestUrl) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url
					.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
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
			httpUrlConn.disconnect();

		} catch (Exception e) {
		}
		return buffer.toString();
	}

	/**
	 * 调用Face++ API实现人脸检测
	 * 
	 * @param picUrl
	 *            待检测图片的访问地址
	 * @return List<Face> 人脸列表
	 */
	private static List<Face> faceDetect(String picUrl) {
		List<Face> faceList = new ArrayList<Face>();
		try {
			// 拼接Face++人脸检测的请求地址
			String queryUrl = "http://apicn.faceplusplus.com/v2/detection/detect?url=URL&api_secret=API_SECRET&api_key=API_KEY";
			// 对URL进行编码
			queryUrl = queryUrl.replace("URL",
					java.net.URLEncoder.encode(picUrl, "UTF-8"));
			queryUrl = queryUrl.replace("API_KEY",
					"e1552a305b8607a7bf355b91d893178d");
			queryUrl = queryUrl.replace("API_SECRET",
					"DzGfy9NoYgs_LPHbUfkF4l4JiakiEB25");
			// 调用人脸检测接口
			String json = httpRequest(queryUrl);
			if(null!=json&&0<json.length()){
				// 解析返回json中的Face列表
				JSONArray jsonArray = JSONObject.fromObject(json).getJSONArray(
						"face");
				// 遍历检测到的人脸
				for (int i = 0; i < jsonArray.length(); i++) {
					// face
					JSONObject faceObject = (JSONObject) jsonArray.get(i);
					// attribute
					JSONObject attrObject = faceObject.getJSONObject("attribute");
					// position
					JSONObject posObject = faceObject.getJSONObject("position");
					Face face = new Face();
					face.setFaceId(faceObject.getString("face_id"));
					face.setAgeValue(attrObject.getJSONObject("age")
							.getInt("value"));
					face.setAgeRange(attrObject.getJSONObject("age")
							.getInt("range"));
					face.setGenderValue(genderConvert(attrObject.getJSONObject(
							"gender").getString("value")));
					face.setGenderConfidence(attrObject.getJSONObject("gender")
							.getDouble("confidence"));
					face.setRaceValue(raceConvert(attrObject.getJSONObject("race")
							.getString("value")));
					face.setRaceConfidence(attrObject.getJSONObject("race")
							.getDouble("confidence"));
					face.setSmilingValue(attrObject.getJSONObject("smiling")
							.getDouble("value"));
					face.setCenterX(posObject.getJSONObject("center")
							.getDouble("x"));
					face.setCenterY(posObject.getJSONObject("center")
							.getDouble("y"));
					faceList.add(face);
				}
				// 将检测出的Face按从左至右的顺序排序
				Collections.sort(faceList);
			}

		} catch (Exception e) {
			faceList = null;
		}
		return faceList;
	}

	/**
	 * 性别转换（英文->中文）
	 * 
	 * @param gender
	 * @return
	 */
	private static String genderConvert(String gender) {
		String result = "男性";
		if ("Male".equals(gender))
			result = "男性";
		else if ("Female".equals(gender))
			result = "女性";

		return result;
	}

	/**
	 * 人种转换（英文->中文）
	 * 
	 * @param race
	 * @return
	 */
	private static String raceConvert(String race) {
		String result = "黄色";
		if ("Asian".equals(race))
			result = "黄色";
		else if ("White".equals(race))
			result = "白色";
		else if ("Black".equals(race))
			result = "黑色";
		return result;
	}

	/**
	 * 根据人脸识别结果组装消息
	 * 
	 * @param faceList
	 *            人脸列表
	 * @return
	 */
	private static String makeMessage(List<Face> faceList) {
		StringBuffer buffer = new StringBuffer();
		// 检测到1张脸
		if (1 == faceList.size()) {
			buffer.append("共检测到 ").append(faceList.size()).append(" 个人");
			for (Face face : faceList) {
				buffer.append(face.getRaceValue()).append("人种,");
				buffer.append(face.getGenderValue()).append(",");
				buffer.append(face.getAgeValue()).append("岁左右");
				buffer.append(",")
						.append(face.getSmilingValue() < 30 ? "不开森" : face
								.getSmilingValue() > 66 ? "乐开花了" : "心情不错")
						.append("\n");
			}
		}
		// 检测到2-10张脸
		else if (faceList.size() > 1 && faceList.size() <= 10) {
			buffer.append("共检测到 ").append(faceList.size())
					.append(" 个人，从左至右依次为：").append("\n");
			for (Face face : faceList) {
				buffer.append(face.getRaceValue()).append("人种,");
				buffer.append(face.getGenderValue()).append(",");
				buffer.append(face.getAgeValue()).append("岁左右");
				buffer.append(",")
						.append(face.getSmilingValue() < 30 ? "不开森" : face
								.getSmilingValue() > 66 ? "乐开花了" : "心情不错")
						.append("\n");
			}
		}
		// 检测到10张脸以上
		else if (faceList.size() > 10) {
			buffer.append("共检测到 ").append(faceList.size()).append(" 个人")
					.append("\n");
			// 统计各人种、性别的人数
			int asiaMale = 0;
			int asiaFemale = 0;
			int whiteMale = 0;
			int whiteFemale = 0;
			int blackMale = 0;
			int blackFemale = 0;
			for (Face face : faceList) {
				if ("黄色".equals(face.getRaceValue()))
					if ("男性".equals(face.getGenderValue()))
						asiaMale++;
					else
						asiaFemale++;
				else if ("白色".equals(face.getRaceValue()))
					if ("男性".equals(face.getGenderValue()))
						whiteMale++;
					else
						whiteFemale++;
				else if ("黑色".equals(face.getRaceValue()))
					if ("男性".equals(face.getGenderValue()))
						blackMale++;
					else
						blackFemale++;
			}
			if (0 != asiaMale || 0 != asiaFemale)
				buffer.append("黄人：").append(asiaMale).append("男")
						.append(asiaFemale).append("女").append("\n");
			if (0 != whiteMale || 0 != whiteFemale)
				buffer.append("白人：").append(whiteMale).append("男")
						.append(whiteFemale).append("女").append("\n");
			if (0 != blackMale || 0 != blackFemale)
				buffer.append("黑人：").append(blackMale).append("男")
						.append(blackFemale).append("女").append("\n");
		}
		// 移除末尾空格
		if (buffer.length() > 0)
			buffer = new StringBuffer(buffer.substring(0,
					buffer.lastIndexOf("\n")));

		return buffer.toString();
	}

	/**
	 * 提供给外部调用的人脸检测方法
	 * 
	 * @param picUrl
	 *            待检测图片的访问地址
	 * @return String
	 */
	public static String detect(String picUrl) {
		// 默认回复信息
		String result = "";
		List<Face> faceList = faceDetect(picUrl);
		if (null != faceList && 0 < faceList.size()) {
			result = makeMessage(faceList);
		}

		return result;
	}

	public static void main(String[] args) {
		try
		{
			String picUrl = "http://weiwuu-images.oss-cn-beijing.aliyuncs.com/garden/1ef72075-1a02-48b9-8cde-5cb01a148f45.jpg";
			System.out.println("结果："+detect(picUrl));
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}