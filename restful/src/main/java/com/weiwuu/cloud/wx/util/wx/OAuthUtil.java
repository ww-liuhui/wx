package com.weiwuu.cloud.wx.util.wx;

import com.weiwuu.cloud.wx.domain.wx.SNSUserInfo;
import com.weiwuu.cloud.wx.domain.wx.WeixinOauth2Token;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/***
 * 网页授权工具类
 * 
 * @author hui
 *
 */
public class OAuthUtil {
	// private static Logger log = LoggerFactory.getLogger(OAuthUtil.class);
	/***
	 * 获取网页授权凭证
	 * 
	 * @param appId
	 *            公众号的唯一标识
	 * @param appSecret
	 *            公众号密钥
	 * @param code
	 * @return
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId,
			String appSecret, String code) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// 获取网页授权凭证
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		System.out.println("requestUrl:" + requestUrl);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));

			} catch (Exception e) {
				e.printStackTrace();
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				// log.error("获取网页授权凭证失败，errcode{} errmsg:{}",errorCode,errorMsg);
				System.out.println("获取网页授权凭证失败," + errorCode + "," + errorMsg);
			}
		}
		return wat;
	}

	/***
	 * 刷新网页授权凭证
	 * 
	 * @param appId
	 * @param refreshToken
	 * @return
	 */
	public static WeixinOauth2Token refreshOauth2AccessToken(String appId,String refreshToken) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		// 刷新网页授权凭证
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				e.printStackTrace();
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				// log.error("刷新网页授权凭证失败，errcode{} errmsg:{}",errorCode,errorMsg);
				System.out.println("刷新网页授权凭证失败," + errorCode + "," + errorMsg);
			}
		}
		return wat;
	}

	/***
	 * 获取用户信息
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
		SNSUserInfo snsUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"OPENID", openId);
		// 通过网页授权获取用户信息
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				// 用户标识
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				// 昵称
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				// 性别（1男，2女，0未知）
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				snsUserInfo.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				snsUserInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				snsUserInfo.setCity(jsonObject.getString("city"));
				// 用户头像
				snsUserInfo.setHeadIngUrl(jsonObject.getString("headimgurl"));
				// 用户特权信息
				snsUserInfo.setPrivilegeList(JSONArray.toList(
						jsonObject.getJSONArray("privilege"), List.class));
				// 用户唯一标识
				snsUserInfo.setUnionid(jsonObject.getString("unionid"));

			} catch (Exception e) {
				e.printStackTrace();
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				// log.error("获取用户信息失败，errcode{} errmsg:{}",errorCode,errorMsg);
				System.out.println("获取用户信息失败," + errorCode + "," + errorMsg);
			}
		}
		return snsUserInfo;
	}

	/***
	 * 获取授权获取用户基本信息的链接
	 * @param source
	 * @param appid
	 * @return
	 */
	public static String getUrlInfo(String source, String appid) {
		String result = WeixinUtil.urlEncodeUTF8(source);
		return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ appid
				+ "&redirect_uri="
				+ result
				+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	}
	/***
	 * 获取授权获取用户唯一标识的链接
	 * @param source
	 * @param appid
	 * @return
	 */
	public static String getUrlBase(String source, String appid) {
		String result = WeixinUtil.urlEncodeUTF8(source);
		//
		return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ appid
				+ "&redirect_uri="
				+ result
				+ "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	}
	public static void main(String[] args) {
		String source = "http://wx.weiwuu.com/wx/list.html";
		String appid = "wx7a3e90e6805f58d3";
		String url = getUrlInfo(source, appid);
		System.out.println("url:" + url);
	}
}
