package com.weiwuu.cloud.wx.util.wx;

import com.weiwuu.cloud.wx.domain.wx.dkf.KFinfo;
import com.weiwuu.cloud.wx.domain.wx.dkf.OnlineKF;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/***
 * 多客服工具类
 * 
 * @author hui
 *
 */
public class DKFutils
{
	/***
	 * 获取客服基本信息
	 */
	public static List<KFinfo> getkflist(String access_token) {
		List<KFinfo> kfInfoList = null;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token);
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				kfInfoList = JSONArray.toList(
						jsonObject.getJSONArray("kf_list"), KFinfo.class);
			} catch (Exception e) {
				e.printStackTrace();
				kfInfoList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("获取客服基本信息失败" + errorCode + "," + errorMsg);
			}
		}
		return kfInfoList;
	}
	
	/***
	 * 获取在线客服信息
	 */
	public static List<OnlineKF> getonlinekflist(String access_token) {
		List<OnlineKF> onlinekflist = null;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token);
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				onlinekflist = JSONArray.toList(
						jsonObject.getJSONArray("kf_online_list"), OnlineKF.class);
			} catch (Exception e) {
				e.printStackTrace();
				onlinekflist = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("获取在线客服基本信息失败" + errorCode + "," + errorMsg);
			}
		}
		return onlinekflist;
	}
	
	public static void main(String[] args) {
		// 第三方用户唯一凭证
				String appId = "wxa33c55ef7bf07dc0";
				// 第三方用户唯一凭证密钥
				String appSecret = "b0b0fd34ebb1ca464ef5f5d621055177";
				// 获取接口访问凭证
				String accessToken = WeixinUtil.getAccessToken(appId, appSecret).getToken();

				//System.out.println(accessToken);
//				// 获取客服列表
//				List<KFinfo> kfList = getkflist(accessToken);
//				// 循环输出客服列表信息
//				for (KFinfo kf : kfList) {
//					System.out.println("账号："+kf.getKf_account()+"，昵称："+kf.getKf_nick()+"，工号："+kf.getKf_id()+"，头像："+kf.getKf_headimgurl());
//				}
				
				// 获取在线客服列表
				List<OnlineKF> onlinekfList =  getonlinekflist(accessToken);
				// 循环输出客服列表信息
				for (OnlineKF kf : onlinekfList) {
					System.out.println("账号："+kf.getKf_account()+"，正在接待人数："+kf.getAccepted_case()+"，工号："+kf.getKf_id()+"，最大自动接入数："+kf.getAuto_accept());
				}
				
	}
}
