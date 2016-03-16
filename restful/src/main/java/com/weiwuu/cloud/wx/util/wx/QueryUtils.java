package com.weiwuu.cloud.wx.util.wx;

import com.weiwuu.cloud.wx.domain.wx.YuYi.Flight;
import com.weiwuu.cloud.wx.domain.wx.YuYi.Result;
import com.weiwuu.cloud.wx.domain.wx.YuYi.YYin;
import com.weiwuu.cloud.wx.domain.wx.YuYi.YYout;
import com.weiwuu.cloud.wx.domain.wx.YuYi.flight.FlightDetail;
import com.weiwuu.cloud.wx.domain.wx.YuYi.flight.FlightResult;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/***
 * 智能语义接口
 * 
 * @author hui
 *
 */
public class QueryUtils {

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxa33c55ef7bf07dc0";
		// 第三方用户唯一凭证密钥
		String appSecret = "b0b0fd34ebb1ca464ef5f5d621055177";
		// 获取接口访问凭证
		String access_token = WeixinUtil.getAccessToken(appId, appSecret)
				.getToken();

		YYin in = new YYin("查下3小时后北京到上海的航班", "flight,search", null, null, "北京", null,
				"wxa33c55ef7bf07dc0", "od7fZwn3um2OC_VYLhvMsmJJcZSU");
		
		Result result = getResult(access_token, in);
		System.out.println("str:"+result);
	}
	/***
	 * 航班服务
	 * @param yyout
	 * @return
	 */
	public static Result flight(YYout yyout){
		Result result = new Result();
		if(yyout.getAnswer()!=null){
			//html5结果展示
			return result;
		}
		if(yyout.getResult()!=null){
			//结果
			return result;
		}
		//获取查询航班信息
		Flight flight = (Flight) yyout.getSemantic().getDetails();
		if(flight.getAction()!=0){//不是最终结果
			result.setFlag(false);
			result.setDialog(flight.getDialog());
		}else{//是最终结果
			result.setFlag(true);
			result.setDialog(flight.getDialog());
			String requestUrl = null;
			JSONObject jsb = null;
			//根据语义解析的最终结果，查询航班
			if(flight.getFlight_no()!=null){//有航班号
				requestUrl = "http://apis.juhe.cn/plan/s?name=NAME&date=DATE&key=APPKEY";
				requestUrl = requestUrl.replace("NAME", flight.getFlight_no()).replace("DATE", flight.getStart_date().getDate()).replace("APPKEY", "39c9e857eeec9a3ebc0e2c33eab69dae");
				jsb = httpRequest(requestUrl);
			}else{//没有航班号
				requestUrl = "http://apis.juhe.cn/plan/s2s?start=START&end=END&date=DATE&key=APPKEY";
				requestUrl = requestUrl.replace("START", flight.getStart_loc().getCity_simple()).replace("END", flight.getEnd_loc().getCity_simple()).replace("DATE", flight.getStart_date().getDate()).replace("APPKEY", "39c9e857eeec9a3ebc0e2c33eab69dae");
				jsb = httpRequest(requestUrl);
			}
			
			jsb = JSONObject.fromObject(jsb.toString().toLowerCase());
			//对返回结果进行解析
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("result", FlightDetail.class);
			FlightResult fr = (FlightResult) JSONObject.toBean(jsb, FlightResult.class, classMap);
			ArrayList<FlightDetail> list = null;
			list = fr.getResult();
			if(list!=null){
				String startTime = null;
				long lo1 = 0;
				long lo2 = 0;
				for(int i=0;i<list.size();i++){
					startTime = flight.getStart_date().getTime(); 
					//如果是确定时间
					lo1 = DateUtils.strToMill(list.get(i).getDeptime()+":00")/1000;
					lo2 = DateUtils.strToMill(flight.getStart_date().getDate()+" "+startTime)/1000;
					if((lo2-lo1)<1800){
						System.out.println(lo1+"，"+lo2+"，"+(lo2-lo1)+"，"+list.get(i).getDeptime());
						//将筛选出来的结果格式化
						
					}
				}
			}
		}
		return result;
	}
	/***
	 * 根据输入语句，获取返回数据
	 * @param access_token
	 * @param in
	 * @return
	 */
	public static Result getResult(String access_token,YYin in){
		YYout out = null;
		Result result = null;
		//获取传输数据
		String data = getData(in);
		JSONObject jsonObject = httpS(access_token, data);
		System.out.println(jsonObject);
		if(jsonObject.get("errcode")!=null&&jsonObject.get("errcode").equals(0)){
			String type = jsonObject.get("type").toString();
			type = type.replaceFirst(type.substring(0, 1), type.substring(0, 1).toUpperCase());
			// 转换为 实体类
			Class cls = null;
			try {
				cls = Class.forName("so.soke.wx.pojo.YuYi."+type);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("details", cls);
			out = (YYout) JSONObject.toBean(jsonObject, YYout.class, classMap);
		}
		String type = out.getType();
		try {
			Method method = QueryUtils.class.getMethod(type,YYout.class);
			result = (Result)method.invoke(new QueryUtils(),out);      
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	/***
	 * 组装传输数据
	 * @param in
	 * @return
	 */
	public static String getData(YYin in) {
		String data = null;
		StringBuilder sb = new StringBuilder();
		JSONObject jsonData = JSONObject.fromObject(in);
		data = jsonData.toString();
		data = data.substring(1, data.length() - 1);
		String[] strArr = data.split(",");
		sb.append("{");
		for (int i = 0; i < strArr.length; i++) {
			if (!strArr[i].contains("\"\"")) {
				sb.append(strArr[i]);
				sb.append(",");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		return sb.toString();
	}

	/***
	 * 根据请求语句返回json格式数据
	 * 
	 * @param access_token
	 * @return
	 */
	public static JSONObject httpS(String access_token, String data) {
		JSONObject jsonObject = null;
		String requestUrl = "https://api.weixin.qq.com/semantic/semproxy/search?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token);
		jsonObject = WeixinUtil.httpRequest(requestUrl, "POST", data);
		return jsonObject;
	}
	/**
	 * 发起http get请求获取网页源代码
	 * 
	 * @param requestUrl
	 * @return
	 */
	public static JSONObject httpRequest(String requestUrl) {
		StringBuffer buffer = null;
		JSONObject jsonObject = null;
		try {
			// 建立连接
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url
					.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");

			// 获取输入流
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			// 读取返回结果
			buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
