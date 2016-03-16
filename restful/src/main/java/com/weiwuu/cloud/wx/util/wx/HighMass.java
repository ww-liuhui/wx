package com.weiwuu.cloud.wx.util.wx;

import com.weiwuu.cloud.wx.domain.wx.*;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/***
 * 高级群发接口实现
 * 
 * @author hui
 *
 */
public class HighMass {

	/***
	 * 上传图文信息素材
	 * 
	 * @param accessToken
	 * @param newsModel
	 *            图文信息模型，最多10条
	 * @return
	 */
	public static HighMassBack uploadnews(String accessToken,
			NewsModel newsModel) {
		HighMassBack back = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = JSONObject.fromObject(newsModel).toString();
		// 上传图文消息素材
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST",
				jsonData);
		System.out.println(jsonObject);
		if (null != jsonObject) {
			try {
				back = new HighMassBack();
				back.setType(jsonObject.getString("type"));
				back.setMedia_id(jsonObject.getString("media_id"));
				back.setCreated_at(jsonObject.getLong("created_at"));
			} catch (Exception e) {
				e.printStackTrace();
				back = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				// log.error("获取关注者列表失败", errorCode, errorMsg);
				System.out.println("上传图文消息素材失败" + errorCode + "," + errorMsg);
			}
		}
		return back;
	}

	/***
	 * 根据分组进行群发
	 * 
	 * @param accessToken
	 * @param groupMass
	 *            根据分组群发
	 * @return
	 */
	public static MassBackMsg sendByGroup(String accessToken,
			GroupMass groupMass) {
		MassBackMsg back = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = JSONObject.fromObject(groupMass).toString();
		// 上传图文消息素材
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST",
				jsonData);
		System.out.println(jsonData);
		if (null != jsonObject) {
			try {
				back = new MassBackMsg();
				back.setErrcode(jsonObject.getInt("errcode"));
				back.setErrmsg(jsonObject.getString("errmsg"));
				back.setMsg_id(jsonObject.getString("msg_id"));
			} catch (Exception e) {
				e.printStackTrace();
				back = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				// log.error("获取关注者列表失败", errorCode, errorMsg);
				System.out.println("根据分组进行群发失败" + errorCode + "," + errorMsg);
			}
		}
		return back;
	}

	/***
	 * 根据OpenID列表群发
	 * 
	 * @param accessToken
	 * @param openIdMass
	 *            根据OpenID列表群发
	 * @return
	 */
	public static MassBackMsg sendByOpenIdList(String accessToken,
			OpenIdMassNews openIdMass) {
		MassBackMsg back = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = JSONObject.fromObject(openIdMass).toString();
		// 上传图文消息素材
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST",
				jsonData);
		System.out.println(jsonData);
		if (null != jsonObject) {
			try {
				back = new MassBackMsg();
				back.setErrcode(jsonObject.getInt("errcode"));
				back.setErrmsg(jsonObject.getString("errmsg"));
				back.setMsg_id(jsonObject.getString("msg_id"));
			} catch (Exception e) {
				e.printStackTrace();
				back = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				// log.error("获取关注者列表失败", errorCode, errorMsg);
				System.out.println("根据openId进行群发失败" + errorCode + ","
						+ errorMsg);
			}
		}
		return back;
	}

	public static void main(String[] args) {
		List<Article> articles = new ArrayList<Article>();
		articles.add(new Article(
				"cq5DmSFAFwcwsIOezFVByw5mh98UhVioRGoRhFbjSS0FlPFosS1H7IBDpYIHLtxX",
				"", "title", "http://www.baidu.com", null, "digest", 0));
		articles.add(new Article(
				"cq5DmSFAFwcwsIOezFVByw5mh98UhVioRGoRhFbjSS0FlPFosS1H7IBDpYIHLtxX",
				"title2", "http://www.baidu.com", ""));

		// articles.add(new Article("title", "desc",
		// "http://a.hiphotos.baidu.com/news/pic/item/6d81800a19d8bc3e99eeb1db818ba61ea8d34538.jpg",
		// "http://www.baidu.com"));

		NewsModel newsModel = new NewsModel();
		newsModel.setArticles(articles);
		// 第三方用户唯一凭证
		String appId = "wx3882dbbff6bb8031";
		// 第三方用户唯一凭证密钥
		String appSecret = "b859bdf5e8d1b4db6a927dbd07b68458";
		// 获取接口访问凭证
		String accessToken = WeixinUtil.getAccessToken(appId, appSecret)
				.getToken();
		HighMassBack back = uploadnews(accessToken, newsModel);
		System.out.println(back.getMedia_id());
		String media_id = back.getMedia_id();

		// 根据分组进行群发
		GroupMass groupMass = new GroupMass();
		groupMass.setMsgtype("mpnews");
		Mpnews mpnews = new Mpnews();
		// 添加MediaId
		mpnews.setMedia_id(media_id);
		groupMass.setMpnews(mpnews);
		// 设置发送群组
		groupMass.setFilter(new Filter("101"));
		MassBackMsg backMsg = sendByGroup(accessToken, groupMass);
		if (backMsg.getErrcode() == 0) {
			System.out.println("群发提交成功！");
		}

		// 根据OpenID列表群发
		/*
		 * OpenIdMassNews openIdMass = new OpenIdMassNews();
		 * openIdMass.setMsgtype("mpnews"); Mpnews mpnews = new Mpnews();
		 * //添加MediaId mpnews.setMedia_id(media_id);
		 * openIdMass.setMpnews(mpnews);
		 * 
		 * List<String> touser = new ArrayList<String>();
		 * touser.add("o74PBjgMo0UFTWolOEywZp8Zjdgc");
		 * openIdMass.setTouser(touser); //发送给指定openId MassBackMsg backMsg =
		 * sendByOpenIdList(accessToken, openIdMass);
		 * if(backMsg.getErrcode()==0){ System.out.println("群发提交成功！"); }
		 */
	}

}
