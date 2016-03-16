package com.weiwuu.cloud.wx.util.wx;

import com.weiwuu.cloud.wx.domain.wx.WeixinUserInfo;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 获取用户基本信息高级接口
 * 
 * @author hui
 *
 */
public class UserInfo {
	/***
	 * 获取用户基本信息
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static WeixinUserInfo getUserInfo(String accessToken, String openId) {
		WeixinUserInfo weixinUserInfo = null;
		// 拼接链接地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"OPENID", openId);
		// 获取用户信息
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				weixinUserInfo = new WeixinUserInfo();
				// 用户的标识
				weixinUserInfo.setOpenId(jsonObject.getString("openid"));
				// 关注状态（1关注，0未关注）未关注时获取不到信息
				weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
				// 用户关注时间
				weixinUserInfo.setSubscribeTime(jsonObject
						.getString("subscribe_time"));
				// 昵称
				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
				// 用户性别（1男，2女）
				weixinUserInfo.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				weixinUserInfo.setCountry(jsonObject.getString("country"));
				// 用户所在地区
				weixinUserInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				weixinUserInfo.setCity(jsonObject.getString("city"));
				// 用户语言，中文为zh_CN
				weixinUserInfo.setLanguag(jsonObject.getString("language"));
				// 用户头像
				weixinUserInfo
						.setHeadImgUrl(jsonObject.getString("headimgurl"));
				// 用户备注
				weixinUserInfo
						.setRemark(jsonObject.getString("remark"));
				// 用户分组ID
				weixinUserInfo
						.setGroupid(jsonObject.getString("groupid"));

				try
				{
					//unionid(只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段)
					weixinUserInfo.setUnionid(jsonObject.getString("unionid"));

				}catch (Exception e){

				}

			} catch (Exception e) {
//				e.printStackTrace();
//				if (0 == weixinUserInfo.getSubscribe()) {
//					System.out.println("用户已经取消关注");
//				} else {
//					int errorCode = jsonObject.getInt("errcode");
//					String errorMsg = jsonObject.getString("errmsg");
//					System.out.println("获取用户信息失败！" + errorCode + " , "
//							+ errorMsg);
//				}
			}

		}
		return weixinUserInfo;
	}

	/***
	 * 设置用户备注名
	 * 
	 * @param accessToken
	 * @param openId
	 * @param remark
	 * @return
	 */
	public static int setUserRemark(String accessToken, String openId,
			String remark) {
		int flag = 0;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"openid\":\"%s\",\"remark\":\"%s\"}";
		System.out.println(String.format(jsonData, openId, remark));
		// 创建分组
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST",
				String.format(jsonData, openId, remark));
		if (null != jsonObject) {
			try {
				int errorCode = jsonObject.getInt("errcode");
				System.out.println("测试：" + errorCode);
				if (errorCode == 0) {
					flag = 1;
				}
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("设置用户备注名失败！" + errorCode + " , " + errorMsg);
			}
		}
		return flag;

	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxa33c55ef7bf07dc0";
		// 第三方用户唯一凭证密钥
		String appSecret = "b0b0fd34ebb1ca464ef5f5d621055177";
		// 获取接口访问凭证
		String accessToken = WeixinUtil.getAccessToken(appId, appSecret)
				.getToken();
		WeixinUserInfo user = getUserInfo(accessToken,
				"od7fZwn3um2OC_VYLhvMsmJJcZSU");

		// user为获取到的信息
		System.out.println(accessToken);
		System.out.println(user.getCity());
		System.out.println(user.getLanguag());
		System.out.println(user.getNickname());
		System.out.println(user.getSex());
		System.out.println(user.getSubscribe());
		System.out.println(user.getSubscribeTime());
		Date date = new Date(Long.parseLong(user.getSubscribeTime()) * 1000L);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("毫秒数转化后的时间为：" + sdf.format(date));

		System.out.println(user.getHeadImgUrl());
		System.out.println(user.getGroupid());
		int i = setUserRemark(accessToken, "od7fZwn3um2OC_VYLhvMsmJJcZSU", "sibulh");
		//System.out.println(i);
	}
}
