package com.weiwuu.cloud.wx.util.wx;

import com.weiwuu.cloud.wx.domain.wx.WeixinQRCode;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

/***
 * 创建二维码方法封装
 * 
 * @author hui
 *
 */
public class CreateCode
{
	/**
	 * 创建临时带参数二维码
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param expireSeconds
	 *            二维码有效时间，最长不超过1800s
	 * @param sceneId
	 *            场景ID
	 * @return
	 */
	public static WeixinQRCode createTemporaryQRCode(String accessToken,
			int expireSeconds, int sceneId) {
		WeixinQRCode weixinQRCode = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonMsg = "{\"expire_seconds\":%d,\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%d}}}";
		// 创建临时带参数二维码
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST",
				String.format(jsonMsg, expireSeconds, sceneId));
		if (null != jsonObject) {
			try {
				weixinQRCode = new WeixinQRCode();
				weixinQRCode.setTicket(jsonObject.getString("ticket"));
				weixinQRCode.setExpireSeconds(jsonObject
						.getInt("expire_seconds"));
				weixinQRCode.setUrl(jsonObject.getString("url"));

			} catch (Exception e) {
				weixinQRCode = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("创建临时二维码失败！" + errorCode + " , " + errorMsg);

			}
		}
		return weixinQRCode;
	}

	/**
	 * 创建永久带参数二维码
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param sceneId
	 *            场景ID
	 * @return ticket
	 */
	public static String createPermanentQRCode(String accessToken, int sceneId,String sceneStr) {
		String ticket = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String jsonMsg = null;
		JSONObject jsonObject = null;
		if(sceneId>0){
			// 需要提交的json数据
			jsonMsg = "{\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%d}}}";
			// 创建永久带参数二维码
			jsonObject = WeixinUtil.httpRequest(requestUrl, "POST",
					String.format(jsonMsg, sceneId));
		}else if(null != sceneStr && sceneStr.length() > 0){
			// 需要提交的json数据
			jsonMsg = "{\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\":{\"scene\":{\"scene_str\":%d}}}";
			// 创建永久带参数二维码
			jsonObject = WeixinUtil.httpRequest(requestUrl, "POST",String.format(jsonMsg, sceneStr));
		}else{
			return null;
		}

		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
				System.out.println("创建临时二维码成功！" + ticket);
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("创建临时二维码失败！" + errorCode + " , " + errorMsg);

			}
		}
		return ticket;
	}

	/***
	 * 根据ticket换取二维码
	 * 
	 * @param ticket
	 * @param savePath
	 * @return
	 */
	public static String getQRCode(String ticket, String savePath) {

		System.out.println("ticket:" + ticket);

		String filePath = null;
		// 拼接请求地址
		String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		requestUrl = requestUrl.replace("TICKET",
				WeixinUtil.urlEncodeUTF8(ticket));
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 将ticket作为文件名
			filePath = savePath + ticket + ".jpg";

			// 将微信服务器返回的输入流写入文件
			BufferedInputStream bis = new BufferedInputStream(
					conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.close();
			bis.close();

			conn.disconnect();
			System.out.println("根据ticket换取二维码成功，路径：" + filePath);

		} catch (Exception e) {
			filePath = null;
			System.out.println("换取二维码失败！" + e);
		}
		return filePath;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wx7a3e90e6805f58d3";
		// 第三方用户唯一凭证密钥
		String appSecret = "eb74d05412043f88371f80670c60b5bd";
		// 获取接口访问凭证
		String accessToken = WeixinUtil.getAccessToken(appId, appSecret)
				.getToken();

		// 创建临时二维码
		WeixinQRCode weixinQRCode = createTemporaryQRCode(accessToken, 800, 1);
		// 临时二维码的ticket
		System.out.println(weixinQRCode.getTicket());

		String ticketLS = weixinQRCode.getTicket();
		// 临时二维码的有效时间
		System.out.println(weixinQRCode.getExpireSeconds());

//		// 创建永久二维码
//		String ticket = createPermanentQRCode(accessToken, 520);
//		System.out.println("永久性二维码ticket：" + ticket);

		// 根据ticket换取二维码
		String savePath = "d:\\QRCode";
//		getQRCode(ticket, savePath);
		getQRCode(ticketLS, savePath);
	}

}
