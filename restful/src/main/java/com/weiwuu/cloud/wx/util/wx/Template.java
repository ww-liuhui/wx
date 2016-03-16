package com.weiwuu.cloud.wx.util.wx;

/***
 * 模板消息
 * 
 * @author hui
 *
 */
public class Template {
	/***
	 * 模板消息发送
	 * 
	 * @param accessToken
	 * @param touser
	 * @param template_id
	 * @param url
	 * @param topcolor
	 * @param data
	 * @return
	 */
	public static void sendTemplateMsg(String accessToken, String touser,
			String template_id, String url, String topcolor, String data) {
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"touser\":\"%s\",\"template_id\":\"%s\",\"url\":\"%s\",\"topcolor\":\"%s\",\"data\":%s}";
		System.out.println(String.format(jsonData, touser, template_id, url,
				topcolor, data));
		// 消息发送
		WeixinUtil.httpRequest(requestUrl, "POST", String.format(jsonData,
				touser, template_id, url, topcolor, data));

	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wx7df027ea00f36823";
		// 第三方用户唯一凭证密钥
		String appSecret = "8a98fe16afba309f761a0909cfddff65";
		// 获取接口访问凭证
		String accessToken = WeixinUtil.getAccessToken(appId, appSecret)
				.getToken(); // o74PBjmU6BeTDbrUFfr3Fl3nhr5A

		// String data = "";

		String jsonData = "{\"first\":{\"value\":\"%s\",\"color\":\"%s\"},\"keynote1\":{\"value\":\"%s\",\"color\":\"%s\"},\"keynote2\":{\"value\":\"%s\",\"color\":\"%s\"},\"remark\":{\"value\":\"%s\",\"color\":\"%s\"}}";
		String data = String.format(jsonData, "标题通知", "#FF0000", "到期时间",
				"#FF0000", "说明", "#FF0000", "备注说明", "#FF0000");
		System.out.println(data);
		sendTemplateMsg(accessToken, "o74PBjmU6BeTDbrUFfr3Fl3nhr5A",
				"FLGA6-W_KfNWGIOzo28vdKpGpqr1HX1sCWHrriCalVY",
				"http://weixin.qq.com/download", "#FF0000", data);
		long now = System.currentTimeMillis();
		String s = String.format("%tR", now);
		System.out.println(s);
		// String jsonData = "{\"openid\":\"%s\",\"remark\":\"%s\"}";
		// System.out.println(String.format(jsonData, "sibulh","提示"));
	}

}
