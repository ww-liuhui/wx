package com.weiwuu.cloud.wx.util.wx;

import com.weiwuu.cloud.wx.entity.wx.resp.Article;
import com.weiwuu.cloud.wx.entity.wx.resp.Music;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/***
 * 客服接口实现
 * 
 * @author hui
 *
 */
public class KeFuUtil
{

	/***
	 * 组装文本客服消息
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param content
	 *            文本消息内容
	 * @return
	 */
	public static String makeTextCustomMessage(String openId, String content) {
		// 对消息内容中的双引号转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openId, content);
	}

	/***
	 * 组装图片客服消息
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件ID
	 * @return
	 */
	public static String makeImgCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/***
	 * 组装语音客服消息
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件ID
	 * @return
	 */
	public static String makeVoiceCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/***
	 * 组装视频客服消息
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件ID
	 * @param thumbMediaId
	 *            视频消息缩略图的媒体ID
	 * @return
	 */
	public static String makeVideoCustomMessage(String openId, String mediaId,
			String thumbMediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId, thumbMediaId);
	}

	/***
	 * 组装音乐客服消息
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param music
	 *            音乐对象
	 * @return
	 */
	public static String makeMusicCustomMessage(String openId, Music music) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":%s}";
		jsonMsg = String.format(jsonMsg, openId, JSONObject.fromObject(music)
				.toString());
		// 将jsonMsg中的thumbmediaid替换为thumb_media_id
		jsonMsg = jsonMsg.replace("thumbmediaid", "thumb_media_id");
		return jsonMsg;
	}

	/***
	 * 组装图文客服消息
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param articleList
	 *            图文消息列表
	 * @return
	 */
	public static String makeNewsCustomMessage(String openId,
			List<Article> articleList) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
		jsonMsg = String.format(
				jsonMsg,
				openId,
				JSONArray.fromObject(articleList).toString());/*.replace("\"", "\\\"")*/

		// 将jsonMsg中的picUrl替换为picurl
		jsonMsg = jsonMsg.replace("picUrl", "picurl");
		return jsonMsg;
	}

	/***
	 * 发送客服消息
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param jsonMsg
	 *            json格式的客服消息（包括touser,msgtype,消息内容）
	 * @return
	 */
	public static boolean sendCustomMessage(String accessToken, String jsonMsg) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 发送客服消息
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST",
				jsonMsg);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				System.out.println("客服消息发送成功");
			} else {
				System.out.println("客服消息发送失败，" + errorCode + "," + errorMsg);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxa33c55ef7bf07dc0";
		// 第三方用户唯一凭证密钥
		String appSecret = "b0b0fd34ebb1ca464ef5f5d621055177";
		// 获取接口访问凭证
		String accessToken = WeixinUtil.getAccessToken(appId, appSecret)
				.getToken();
		System.out.println("accessToken:" + accessToken);
//		// 组装文本客服消息
//		String jsonMsg = makeTextCustomMessage(
//				"od7fZwn3um2OC_VYLhvMsmJJcZSU", "test!!");
		String jsonMsg = null;
		String openid = "od7fZwn3um2OC_VYLhvMsmJJcZSU";
		List<Article> articleList = new ArrayList<>();
		String description = "姓名："+"大灰"+"\n\n"+"电话："+"18652415200"+"\n\n"+"时间："+"2015-12-22 12:52"
				+"\n\n"+"出发地点："+"南亚"+"\n\n"+"目标楼盘："+"德瀛华府";
		Article article = new Article("新的约车信息",description,"","");
		articleList.add(article);
		jsonMsg = KeFuUtil.makeNewsCustomMessage(openid,articleList);




		//以指定客服的身份发送信息给用户
		jsonMsg = jsonMsg.substring(0,jsonMsg.length()-1) + ",\"customservice\":{\"kf_account\": \"dahui@weiwuu2015\"}}";
		System.out.println("jsonMsg:"+jsonMsg);
		//组装音乐消息
//		Music music = new Music("music test","描述","url","hurl","id");
//		String jsonMsg = makeMusicCustomMessage("od7fZwn3um2OC_VYLhvMsmJJcZSU",music);

		// 发送客服消息
		sendCustomMessage(accessToken, jsonMsg);
		System.out.println(jsonMsg);
	}
}
